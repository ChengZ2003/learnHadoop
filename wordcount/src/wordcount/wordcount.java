package wordcount;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
public class wordcount {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				Configuration conf = new Configuration();
				String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
				Job wordCountJob = Job.getInstance(conf,"word count");
				
				//重要：指定本job所在的jar包
				wordCountJob.setJarByClass(wordcount.class);		// 类反射
				
				//设置wordCountJob所用的mapper逻辑类为哪个类
				wordCountJob.setMapperClass(WordCountMapper.class);
				//设置wordCountJob所用的reducer逻辑类为哪个类
				wordCountJob.setReducerClass(WordCountReducer.class);
				
				//设置map阶段输出的kv数据类型
				wordCountJob.setMapOutputKeyClass(Text.class);
				wordCountJob.setMapOutputValueClass(IntWritable.class);
				
				//设置最终输出的kv数据类型
				wordCountJob.setOutputKeyClass(Text.class);
				wordCountJob.setOutputValueClass(IntWritable.class);
				
				//设置要处理的文本数据所存放的路径
				FileInputFormat.setInputPaths(wordCountJob, new Path(otherArgs[0]));
				FileOutputFormat.setOutputPath(wordCountJob, new Path(otherArgs[1]));
				
				//提交job给hadoop集群(Yarn)
				wordCountJob.waitForCompletion(true);
	}

}
