package edu.uprm.cse.bigdata.retweetcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TwitterRCReducer extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {

        String retweets = "[";

        for (Text value : values){
            retweets += ", "+value.toString();
        }
        context.write(key, new Text(retweets + "]"));
    }
}
