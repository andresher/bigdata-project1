package edu.uprm.cse.bigdata.retweetcount;

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

public class TwitterRCMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String rawTweet = value.toString();

        try {
            Status status = TwitterObjectFactory.createStatus(rawTweet);
            context.write(new Text(Long.toString(status.getId())), new IntWritable(status.getRetweetCount()));
        }
        catch(TwitterException e){

        }

    }
}
