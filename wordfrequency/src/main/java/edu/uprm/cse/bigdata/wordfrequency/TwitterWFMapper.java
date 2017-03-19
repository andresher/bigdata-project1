package edu.uprm.cse.bigdata.wordfrequency;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;
import java.util.Arrays;
import java.util.List;

import java.io.IOException;

public class TwitterWFMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private List<String> stopWords = Arrays.asList("A", "AN", "AND", "ARE", "AS", "AT", "BE", "BY", "FOR", "FROM", "HAS", "HE", "IN", "IS", "IT", "ITS", "OF", "ON", "THAT", "THE", "TO", "WAS", "WERE", "WILL", "WITH");

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String rawTweet = value.toString();

        try {
            Status status = TwitterObjectFactory.createStatus(rawTweet);
            String tweet = status.getText().toUpperCase();
            String[] tweetWordsArray = tweet.split(" ");
            List<String> tweetWords = Arrays.asList(tweetWordsArray);
            for(String word: tweetWords){
                if (!stopWords.contains(word)){
                    context.write(new Text(word), new IntWritable(1));
                }
            }
        }
        catch(TwitterException e){

        }

    }
}
