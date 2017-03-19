package edu.uprm.cse.bigdata.statusbyuser;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TwitterSBUReducer extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {

        String tweets = "";

        for (Text value : values){
            tweets += value.toString()+" ";
        }
        context.write(key, new Text(tweets));
    }
}
