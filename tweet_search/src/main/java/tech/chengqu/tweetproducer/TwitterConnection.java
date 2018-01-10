package tech.chengqu.tweetproducer;

import twitter4j.FilterQuery;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterConnection {
	private ConfigurationBuilder getCB() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("QduINbA2bOihzNgWUrqQYA2OT")
		  .setOAuthConsumerSecret("hJv8i2nVEjgqOu41Mo2uGCNwU2oHzaVisnTD1S0Tlg8VIEI3eJ")
		  .setOAuthAccessToken("354800992-q2U7M0eskz8pihInBmAQleD4YdEvC2yq6p6T1Cm6")
		  .setOAuthAccessTokenSecret("bm2dBjMjwu0jHGnbfGIZANe7PPNJQndJYqIbPU7GICfWQ");
		return cb;
	}
	
	TwitterConnection(){
		
	}
	
	
	
	public Twitter getTwitterInstance() {
		TwitterFactory tf = new TwitterFactory(getCB().build());
		Twitter twitter = tf.getInstance();
		return twitter;
	}
	
	public TwitterStream getStreamByName(String[] name, StatusListener listener) {
		 TwitterStream twitterStream = new TwitterStreamFactory(getCB().build()).getInstance();
		 FilterQuery tweetFilterQuery = new FilterQuery();
		 twitterStream.addListener(listener);
		 tweetFilterQuery.track(name); 
		 tweetFilterQuery.language(new String[]{"en"}); // Note that language does not work properly on Norwegian tweets 
		 twitterStream.filter(tweetFilterQuery);
		 return twitterStream;
	}
}
