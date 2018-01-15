package tech.chengqu.tweetconsumer;

import org.apache.storm.Config;
import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

import tech.chengqu.bolt.HashtagCounterBolt;
import tech.chengqu.bolt.HashtagReaderBolt;
import tech.chengqu.bolt.IntermediateRankingsBolt;
import tech.chengqu.bolt.RollingCountBolt;
import tech.chengqu.bolt.TotalRankingsBolt;
import tech.chengqu.config.KafkaConfig;

public class Topologies {
	Config config;
	public Topologies(){
		this.config = new Config();
	}
	
	TopologyBuilder rollingTopHashtagTopology(KafkaConfig kafkaConfig, int TOP_N) {
		String topologyName = "slidingWindowCounts";
	    TopologyBuilder tp = new TopologyBuilder();
	    String hashtagReaderId = "twitter-hashtag-reader-bolt";
	    String hashtagCounterId = "twitter-hashtag-counter-bolt";
	    String spoutId = "twitter-spout";
	    String counterId = "counter";
	    String intermediateRankerId = "intermediateRanker";
	    String totalRankerId = "finalRanker";
	    tp.setSpout(spoutId, new KafkaSpout<>(kafkaConfig.kafkaSpoutConfig()), 5);
		tp.setBolt(hashtagReaderId, new HashtagReaderBolt())
        .shuffleGrouping(spoutId);

//		tp.setBolt(hashtagCounterId, new HashtagCounterBolt())
//        .fieldsGrouping(hashtagReaderId, new Fields("hashtag"));
	    tp.setBolt(counterId, new RollingCountBolt(9, 3), 4).fieldsGrouping(hashtagReaderId, new Fields("hashtag"));
	    tp.setBolt(intermediateRankerId, new IntermediateRankingsBolt(TOP_N), 4).fieldsGrouping(counterId,
	        new Fields("obj"));
	    tp.setBolt(totalRankerId, new TotalRankingsBolt(TOP_N)).globalGrouping(intermediateRankerId);
	    return tp;
	}
}
