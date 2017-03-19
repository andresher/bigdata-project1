package edu.uprm.cse.bigdata.getreplies;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class TwitterGRDriver {

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: TwitterRCDriver <input path> <output path>");
            System.exit(-1);
        }
        Job job = new Job();
        job.setJarByClass(edu.uprm.cse.bigdata.getreplies.TwitterGRDriver.class);
        job.setJobName("Get Replies of Tweets");

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(edu.uprm.cse.bigdata.getreplies.TwitterGRMapper.class);
        job.setReducerClass(edu.uprm.cse.bigdata.getreplies.TwitterGRReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

}
