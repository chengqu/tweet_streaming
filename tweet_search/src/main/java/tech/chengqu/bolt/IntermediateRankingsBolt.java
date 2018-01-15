package tech.chengqu.bolt;

import org.apache.log4j.Logger;
import org.apache.storm.tuple.Tuple;

import tech.chengqu.util.Rankable;
import tech.chengqu.util.RankableObjectWithFields;

public final class IntermediateRankingsBolt extends AbstractRankerBolt {

	  private static final long serialVersionUID = -1369800530256637409L;
	  private static final Logger LOG = Logger.getLogger(IntermediateRankingsBolt.class);

	  public IntermediateRankingsBolt() {
	    super();
	  }

	  public IntermediateRankingsBolt(int topN) {
	    super(topN);
	  }

	  public IntermediateRankingsBolt(int topN, int emitFrequencyInSeconds) {
	    super(topN, emitFrequencyInSeconds);
	  }

	  @Override
	  void updateRankingsWithTuple(Tuple tuple) {
	    Rankable rankable = RankableObjectWithFields.from(tuple);
	    super.getRankings().updateWith(rankable);
	  }

	  @Override
	  Logger getLogger() {
	    return LOG;
	  }
	}