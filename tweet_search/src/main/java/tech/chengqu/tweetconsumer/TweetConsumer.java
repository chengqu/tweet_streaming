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

import tech.chengqu.kafka.KafkaConfig;
import tech.chengqu.avro.AvroDeserializer;
import tech.chengqu.avro.Tweet;
public class TweetConsumer implements Runnable{
	private KafkaConfig kafkaConfig;
	KafkaConsumer<String,Tweet> consumer;
	private String[] keywords;
	private String topic;
	TopologyBuilder tp;
	Config config;
	public TweetConsumer(String[] keywords, String broker) {
		this.keywords = keywords;
		this.topic = String.join("_", keywords).replaceAll(" ", "-");
		this.kafkaConfig = new KafkaConfig(keywords,broker);
		this.consumer = new KafkaConsumer<>(kafkaConfig.consumerConfig());
		this.consumer.subscribe(Arrays.asList(new String[] {topic}));
		tp = new TopologyBuilder(); 
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		/*while(true) {
			ConsumerRecords<String, Tweet> records = consumer.poll(1000);
			for(ConsumerRecord<String,Tweet> record : records) {
				System.out.printf("offset = %d, topic = %s, value = %s \n", record.offset(), record.topic(), record.value());
			}
			
		}*/
		
			Config config = new Config();
			//config.setDebug(true);
			tp.setSpout("twitter-spout", new KafkaSpout<>(KafkaSpoutConfig.builder(kafkaConfig.host, kafkaConfig.topic).setFirstPollOffsetStrategy(KafkaSpoutConfig.FirstPollOffsetStrategy.UNCOMMITTED_EARLIEST)
					.setGroupId(kafkaConfig.groupID).setKey(StringDeserializer.class).setValue(AvroDeserializer.class).build()), 1);
			tp.setBolt("twitter-hashtag-reader-bolt", new HashtagReaderBolt())
	         .shuffleGrouping("twitter-spout");

			tp.setBolt("twitter-hashtag-counter-bolt", new HashtagCounterBolt())
	         .fieldsGrouping("twitter-hashtag-reader-bolt", new Fields("hashtag"));
				
			LocalCluster cluster = new LocalCluster();
			cluster.submitTopology("TwitterHashtagStorm", config,
	        tp.createTopology());
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cluster.shutdown();
		
			return;
	     
		
	}
}
