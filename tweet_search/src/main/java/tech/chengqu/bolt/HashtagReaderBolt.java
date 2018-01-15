package tech.chengqu.bolt;

import java.util.HashMap;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import tech.chengqu.avro.Hashtag;
import tech.chengqu.avro.Tweet;
import twitter4j.*;
import twitter4j.conf.*;



public class HashtagReaderBolt implements IRichBolt {
   private OutputCollector collector;
   @Override
   public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
      this.collector = collector;
   }

   @Override
   public void execute(Tuple tuple) {
      Tweet tweet = (Tweet) tuple.getValueByField("value");
      
      for(Hashtag ht : tweet.getEntities().getHashtags()) {
    	  	 System.out.println("Hashtag: " + ht.getText());
         this.collector.emit(new Values(ht.getText()));
      }
   }

   @Override
   public void cleanup() {}

   @Override
   public void declareOutputFields(OutputFieldsDeclarer declarer) {
      declarer.declare(new Fields("hashtag"));
   }
	
   @Override
   public Map<String, Object> getComponentConfiguration() {
      return null;
   }
	
}