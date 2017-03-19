import twitterconfig as cfg
import tweepy
from tweepy import Stream
from tweepy import OAuthHandler
from tweepy.streaming import StreamListener

auth = OAuthHandler(cfg.consumer_key, cfg.consumer_secret)
auth.set_access_token(cfg.access_token, cfg.access_secret)

api = tweepy.API(auth)

class MyListener(StreamListener):
    def on_data(self, data):
        try:
            with open('trump.json', 'a') as f:
                f.write(data)
                return True
        except BaseException as e:
            print('Error on_data: % s' % str(e))
            return True

        def on_error(self, status):
            print(status)
            return True

twitter_stream = Stream(auth, MyListener())
twitter_stream.filter(track=['Trump'])