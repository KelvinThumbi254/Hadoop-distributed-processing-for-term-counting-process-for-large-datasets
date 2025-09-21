import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class CountSpecificTerm {

  public static class TokenizerMapper
    	extends Mapper<Object, Text, Text, IntWritable>{

	private final static IntWritable one = new IntWritable(1);
	private Text fileNameKey = new Text();

	public void map(Object key, Text value, Context context
                 ) throws IOException, InterruptedException {
	  StringTokenizer itr = new StringTokenizer(value.toString());
	  String fileName = ((org.apache.hadoop.mapreduce.lib.input.FileSplit) context.getInputSplit()).getPath().getName();
	  while (itr.hasMoreTokens()) {
		String token = itr.nextToken();
		if ("GO:0030420".equals(token)) {
		  fileNameKey.set(fileName);
		  context.write(fileNameKey, one);
		}
	  }
      }
  }

  public static class IntSumReducer
    	extends Reducer<Text, IntWritable, Text, IntWritable> {
	private IntWritable result = new IntWritable();

	public void reduce(Text key, Iterable<IntWritable> values,
                    Context context
                    ) throws IOException, InterruptedException {
	  int sum = 0;
	  for (IntWritable val : values) {
		sum += val.get();
	  }
	  result.set(sum);
	  context.write(key, result);
       }
  }

  public static void main(String[] args) throws Exception {
	Configuration conf = new Configuration();
	Job job = Job.getInstance(conf, "count specific term");
	job.setJarByClass(CountSpecificTerm.class);
	job.setMapperClass(TokenizerMapper.class);
	job.setCombinerClass(IntSumReducer.class);
	job.setReducerClass(IntSumReducer.class);
	job.setOutputKeyClass(Text.class);
	job.setOutputValueClass(IntWritable.class);
	FileInputFormat.addInputPath(job, new Path(args[0]));
	FileOutputFormat.setOutputPath(job, new Path(args[1]));
	System.exit(job.waitForCompletion(true) ? 0 : 1);
  }

}
