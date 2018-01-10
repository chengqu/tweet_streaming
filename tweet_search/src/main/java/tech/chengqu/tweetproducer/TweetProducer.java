package tech.chengqu.tweetproducer;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import com.twitter.bijection.Injection;
import com.twitter.bijection.avro.GenericAvroCodecs;

import tech.chengqu.avro.AvroSerializer;
import tech.chengqu.avro.Tweet;
import tech.chengqu.kafka.KafkaConfig;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.ByteArrayOutputStream;
import java.util.Properties;
public class TweetProducer implements Runnable{
	String topic;
	String[] keywords;
	TwitterConnection con;
	Twitter twitter;
	TwitterStream twitterStream;
	KafkaConfig kafkaConfig;
	KafkaProducer<String, Tweet> producer;
	
	public TweetProducer(String[] keywords, String server){
		this.keywords = keywords;
		this.topic = String.join("_", keywords).replaceAll(" ", "-");
		this.kafkaConfig = new KafkaConfig(keywords, server);
		this.con = new TwitterConnection();
		this.twitter = con.getTwitterInstance();
		this.producer = new KafkaProducer<>(kafkaConfig.producerConfig());
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		StatusListener listener = new StatusListener(){
            public void onStatus(Status status) {
            	Tweet tweet = new Tweet();
            	tweet.setUsername(status.getUser().getName());
            	tweet.setText(status.getText());
            	tweet.setTimestamp(status.getCreatedAt().getTime());
            	ProducerRecord<String, Tweet> record = new ProducerRecord<>(topic, tweet);
            	producer.send(record);
                try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

                //System.out.println(status.getUser().getName() + " : " + status.getText()+ "  Tweeted AT: " + status.getCreatedAt());
            }
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
            @Override
            public void onScrubGeo(long arg0, long arg1) {
                // TODO Auto-generated method stub
                
            }
            @Override
            public void onStallWarning(StallWarning arg0) {
                // TODO Auto-generated method stub
                
            }            
        };
		twitterStream = con.getStreamByName(keywords,listener);
		//producer.close();
	}
}
