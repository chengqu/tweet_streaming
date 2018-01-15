package tech.chengqu.tweetconsumer;

import java.util.Arrays;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.storm.Config;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

import tech.chengqu.avro.Tweet;
import tech.chengqu.config.KafkaConfig;

@SuppressWarnings("serial")
public class TwitterSpout extends BaseRichSpout {
   SpoutOutputCollector _collector;
		
   	private KafkaConfig kafkaConfig;
	KafkaConsumer<String,Tweet> consumer;
	private String[] keywords;
	private String topic;
	private String broker;
		
   public TwitterSpout(String[] keywords, String broker) {
	   this.keywords = keywords;
		this.topic = String.join("_", keywords).replaceAll(" ", "-");
		this.broker = broker;
   }
		
		
   @Override
   public void open(Map conf, TopologyContext context,
      SpoutOutputCollector collector) {
	   _collector = collector;
	   this.kafkaConfig = new KafkaConfig(this.keywords,this.broker);
	   this.consumer = new KafkaConsumer<>(kafkaConfig.consumerConfig());
	   this.consumer.subscribe(Arrays.asList(new String[] {topic}));
   }
			
   @Override
   public void nextTuple() {
	   ConsumerRecords<String, Tweet> records = consumer.poll(1000);
      
				
      if (records == null) {
         Utils.sleep(50);
      } else {
    	  for(ConsumerRecord<String,Tweet> record : records) {
    		  _collector.emit(new Values(record.value()));
    	  }
      }
   }
			
   @Override
   public void close() {
      consumer.close();
   }
			
   @Override
   public Map<String, Object> getComponentConfiguration() {
      Config ret = new Config();
      ret.setMaxTaskParallelism(1);
      return ret;
   }
			
   @Override
   public void ack(Object id) {}
			
   @Override
   public void fail(Object id) {}
			
   @Override
   public void declareOutputFields(OutputFieldsDeclarer declarer) {
      declarer.declare(new Fields("tweet"));
   }
}