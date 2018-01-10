package tech.chengqu.producertest;

import tech.chengqu.tweetconsumer.TweetConsumer;
import tech.chengqu.tweetproducer.TweetProducer;

public class StreamTest {
	
	static String[] keywords = {"donald trump"};
	static String host = "localhost:9092";
	public static void main(String[] args) {
		// Start User Producer Thread
	    TweetProducer producerThread = new TweetProducer(keywords,host);
	    Thread t1 = new Thread(producerThread);
	    t1.start();
	 
	    // Start group of User Consumer Thread
	    TweetConsumer consumerThread = new TweetConsumer(keywords,host);
	    Thread t2 = new Thread(consumerThread);
	    t2.start();
	 
	    try {
	      Thread.sleep(100000);
	    } catch (InterruptedException ie) {
	 
	    }
	    
	}
}
