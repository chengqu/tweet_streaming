package tech.chengqu.tweetconsumer;

import java.util.Arrays;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

import tech.chengqu.avro.AvroDeserializer;
import tech.chengqu.avro.Tweet;
import tech.chengqu.bolt.HashtagCounterBolt;
import tech.chengqu.bolt.HashtagReaderBolt;
import tech.chengqu.config.KafkaConfig;
public class TweetConsumer implements Runnable{
	int TOP_N = 10;
	private KafkaConfig kafkaConfig;
	KafkaConsumer<String,Tweet> consumer;
	private String[] keywords;
	private String topic;
	TopologyBuilder rollingTopHashtagTP;
	Topologies tps;
	Config config;
	public TweetConsumer(String[] keywords, String broker) {
		this.keywords = keywords;
		this.topic = String.join("_", keywords).replaceAll(" ", "-");
		this.kafkaConfig = new KafkaConfig(keywords,broker);
		this.consumer = new KafkaConsumer<>(kafkaConfig.consumerConfig());
		this.consumer.subscribe(Arrays.asList(new String[] {topic}));
		this.tps = new Topologies();
		this.rollingTopHashtagTP = this.tps.rollingTopHashtagTopology(kafkaConfig, TOP_N);  
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
			Config config = new Config();
//			//config.setDebug(true);
//			

				
			LocalCluster cluster = new LocalCluster();
			cluster.submitTopology("TwitterHashtagStorm", config,
	        rollingTopHashtagTP.createTopology());
//			try {
//				Thread.sleep(10000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			cluster.shutdown();
//			return;
//	     
		
	}
	
	public void flush() {
		while(true) {
		ConsumerRecords<String, Tweet> records = consumer.poll(1000);
		for(ConsumerRecord<String,Tweet> record : records) {
			System.out.printf("offset = %d, topic = %s, value = %s \n", record.offset(), record.topic(), record.value());
		}
		
	}
	}
}
