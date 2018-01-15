package tech.chengqu.bolt;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.apache.storm.Config;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.TupleUtils;

import tech.chengqu.util.NthLastModifiedTimeTracker;
import tech.chengqu.util.SlidingWindowCounter;

public class RollingCountBolt extends BaseRichBolt {

	  private static final long serialVersionUID = 5537727428628598519L;
	  private static final Logger LOG = Logger.getLogger(RollingCountBolt.class);
	  private static final int NUM_WINDOW_CHUNKS = 5;
	  private static final int DEFAULT_SLIDING_WINDOW_IN_SECONDS = NUM_WINDOW_CHUNKS * 60;
	  private static final int DEFAULT_EMIT_FREQUENCY_IN_SECONDS = DEFAULT_SLIDING_WINDOW_IN_SECONDS / NUM_WINDOW_CHUNKS;
	  private static final String WINDOW_LENGTH_WARNING_TEMPLATE =
	      "Actual window length is %d seconds when it should be %d seconds"
	          + " (you can safely ignore this warning during the startup phase)";

	  private final SlidingWindowCounter<Object> counter;
	  private final int windowLengthInSeconds;
	  private final int emitFrequencyInSeconds;
	  private OutputCollector collector;
	  private NthLastModifiedTimeTracker lastModifiedTracker;

	  public RollingCountBolt() {
	    this(DEFAULT_SLIDING_WINDOW_IN_SECONDS, DEFAULT_EMIT_FREQUENCY_IN_SECONDS);
	  }

	  public RollingCountBolt(int windowLengthInSeconds, int emitFrequencyInSeconds) {
	    this.windowLengthInSeconds = windowLengthInSeconds;
	    this.emitFrequencyInSeconds = emitFrequencyInSeconds;
	    counter = new SlidingWindowCounter<Object>(deriveNumWindowChunksFrom(this.windowLengthInSeconds,
	        this.emitFrequencyInSeconds));
	  }

	  private int deriveNumWindowChunksFrom(int windowLengthInSeconds, int windowUpdateFrequencyInSeconds) {
	    return windowLengthInSeconds / windowUpdateFrequencyInSeconds;
	  }

	  @SuppressWarnings("rawtypes")
	  @Override
	  public void prepare(Map topoConf, TopologyContext context, OutputCollector collector) {
	    this.collector = collector;
	    lastModifiedTracker = new NthLastModifiedTimeTracker(deriveNumWindowChunksFrom(this.windowLengthInSeconds,
	        this.emitFrequencyInSeconds));
	  }

	  @Override
	  public void execute(Tuple tuple) {
	    if (TupleUtils.isTick(tuple)) {
	      System.out.println("Received tick tuple, triggering emit of current window counts");
	      emitCurrentWindowCounts();
	    }
	    else {
	      countObjAndAck(tuple);
	    }
	  }

	  private void emitCurrentWindowCounts() {
	    Map<Object, Long> counts = counter.getCountsThenAdvanceWindow();
	    int actualWindowLengthInSeconds = lastModifiedTracker.secondsSinceOldestModification();
	    lastModifiedTracker.markAsModified();
	    if (actualWindowLengthInSeconds != windowLengthInSeconds) {
	      LOG.warn(String.format(WINDOW_LENGTH_WARNING_TEMPLATE, actualWindowLengthInSeconds, windowLengthInSeconds));
	    }
	    emit(counts, actualWindowLengthInSeconds);
	  }

	  private void emit(Map<Object, Long> counts, int actualWindowLengthInSeconds) {
	    for (Entry<Object, Long> entry : counts.entrySet()) {
	      Object obj = entry.getKey();
	      Long count = entry.getValue();
	      collector.emit(new Values(obj, count, actualWindowLengthInSeconds));
	    }
	  }

	  private void countObjAndAck(Tuple tuple) {
	    Object obj = tuple.getValue(0);
	    counter.incrementCount(obj);
	    collector.ack(tuple);
	  }

	  @Override
	  public void declareOutputFields(OutputFieldsDeclarer declarer) {
	    declarer.declare(new Fields("obj", "count", "actualWindowLengthInSeconds"));
	  }

	  @Override
	  public Map<String, Object> getComponentConfiguration() {
	    Map<String, Object> conf = new HashMap<String, Object>();
	    conf.put(Config.TOPOLOGY_TICK_TUPLE_FREQ_SECS, emitFrequencyInSeconds);
	    return conf;
	  }
	}