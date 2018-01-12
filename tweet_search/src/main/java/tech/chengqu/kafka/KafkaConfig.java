package tech.chengqu.kafka;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import tech.chengqu.avro.AvroDeserializer;
import tech.chengqu.avro.AvroSerializer;
import tech.chengqu.avro.Tweet;

public class KafkaConfig {
	public String host;
	public String topic;
	public String[] topics;
	private String clientID = "client001";
	public String groupID = "twitter-spout";
	public KafkaConfig(String[] keywords, String host){
		this.topic = String.join("_", keywords).replaceAll(" ", "-");
		this.topics = new String[] {topic};
		this.host = host;
	}
	
	public Properties producerConfig() {
		Properties props = new Properties();
		props.put("bootstrap.servers", host);
		props.put("client.id", clientID);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	    props.put("value.serializer", "tech.chengqu.avro.AvroSerializer");
		return props;
	}
	
	public Properties consumerConfig() {
		Properties props = new Properties();
		props.put("bootstrap.servers", host);
		props.put("client.id", clientID);
	    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	    props.put("value.deserializer", "tech.chengqu.avro.AvroDeserializer");
	    props.put("group.id", groupID);
	    props.put("enable.auto.commit", "true");
	    props.put("auto.commit.interval.ms", "1000");
	    props.put("session.timeout.ms", "30000");
	    return props;
	}
	
	
	/*public KafkaSpoutConfig spoutConfig() {
		KafkaSpoutConfig spoutConf =  KafkaSpoutConfig.builder(bootstrapServers, topic)
		        .setGroupId(consumerGroupId)
		        .setOffsetCommitPeriodMs(10_000)
		        .setFirstPollOffsetStrategy(UNCOMMITTED_LATEST)
		        .setMaxUncommittedOffsets(1000000)
		        .setRetry(kafkaSpoutRetryService)
		        .setRecordTranslator
		                (new TupleBuilder(), outputFields, topic )
		        .build();
		return spoutConf;
	}
	*/
}
