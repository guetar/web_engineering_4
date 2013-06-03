package tuwien.big.formel0.twitter;

import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.*;
import twitter4j.auth.AccessToken;

/**
 *
 * @author guetar
 */
public class TwitterClient implements ITwitterClient {
    
    private final static String CONSUMER_KEY = "GZ6tiy1XyB9W0P4xEJudQ";
    private final static String CONSUMER_KEY_SECRET = "gaJDlW0vf7en46JwHAOkZsTHvtAiZ3QUd2mD1x26J9w";
    private final static String CONSUMER_TOKEN = "1366513208-MutXEbBMAVOwrbFmZtj1r4Ih2vcoHGHE2207002";
    private final static String CONSUMER_TOKEN_SECRET = "RMPWOePlus3xtURWRVnv1TgrjTyK7Zk33evp4KKyA";
    
    private Twitter twitter;
 
    public void publishUuid(TwitterStatusMessage message) throws Exception {
 
        twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_KEY_SECRET);
        twitter.setOAuthAccessToken(new AccessToken(CONSUMER_TOKEN, CONSUMER_TOKEN_SECRET));
        
        twitter.updateStatus(message.getTwitterPublicationString());
    }
}
