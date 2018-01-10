package tech.chengqu.tweetconsumer;

import java.util.Arrays;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.apache.storm.topology.TopologyBuilder;

import tech.chengqu.kafka.KafkaConfig;
import tech.chengqu.avro.AvroDeserializer;
import tech.chengqu.avro.Tweet;
public class TweetConsumer implements Runnable{
	private KafkaConfig kafkaConfig;
	KafkaConsumer<String,Tweet> consumer;
	private String[] keywords;
	private String topic;
	TopologyBuilder tp;
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
		while(true) {
			tp.setSpout("kafka_spout", new KafkaSpout<>(KafkaSpoutConfig.builder(kafkaConfig.host, kafkaConfig.topic).setKey(StringDeserializer.class).setValue(AvroDeserializer.class).build()), 1);
			tp.setBolt("twitter_bolt", new HashtagReaderBolt()).shuffleGrouping("twitter_spout");
			/*ConsumerRecords<String, Tweet> records = consumer.poll(1000);
			for(ConsumerRecord<String,Tweet> record : records) {
				System.out.printf("offset = %d, topic = %s, value = %s \n", record.offset(), record.topic(), record.value());
			}
			*/
		}
	}
}
