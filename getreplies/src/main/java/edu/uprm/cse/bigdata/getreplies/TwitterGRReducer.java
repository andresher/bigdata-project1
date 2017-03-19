package edu.uprm.cse.bigdata.getreplies;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TwitterGRReducer extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {

        String replies = "";

        for (Text value : values){
            replies += value.toString()+" ";
        }
        context.write(key, new Text(replies));
    }
}
