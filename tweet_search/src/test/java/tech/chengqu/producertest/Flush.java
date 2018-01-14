package tech.chengqu.producertest;

import tech.chengqu.tweetconsumer.TweetConsumer;

public class Flush {
	static String[] keywords = {"donald trump"};
	static String host = "localhost:9092";
	
	public static void main(String[] args) {
		TweetConsumer tc = new TweetConsumer(keywords, host);
		tc.flush();
	}
}
