package test;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterTrendingTopics {
	
	static private void createFileWithTrends(Trends trends, int numOfTopTrends) {
		String fileName = "dictionary/twitter_trends.txt";

		try {
			// Assume default encoding.
			FileWriter fileWriter = new FileWriter(fileName);

			// Always wrap FileWriter in BufferedWriter.
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			// Note that write() does not automatically
			// append a newline character.
			int count = 0;
			String allTopTrends = "";
	        for (Trend trend : trends.getTrends()) {
	            if (count < numOfTopTrends) {
	                allTopTrends += trend.getName() + "\n";
	                count++;
	            }
	        }
			bufferedWriter.write(allTopTrends);
			// Always close files.
			bufferedWriter.close();
		} catch (IOException ex) {
			System.out.println("Error writing to file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
	}
	
	static public void writeTwitterTrendingTopics() throws TwitterException {
		int numOfTopTrends = 15;
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("3jmA1BqasLHfItBXj3KnAIGFB")
                .setOAuthConsumerSecret("imyEeVTctFZuK62QHmL1I0AUAMudg5HKJDfkx0oR7oFbFinbvA")
                .setOAuthAccessToken("265857263-pF1DRxgIcxUbxEEFtLwLODPzD3aMl6d4zOKlMnme")
                .setOAuthAccessTokenSecret("uUFoOOGeNJfOYD3atlcmPtaxxniXxQzAU4ESJLopA1lbC");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        Trends trends = twitter.getPlaceTrends(1);
        createFileWithTrends(trends, numOfTopTrends);
        
	}
}
