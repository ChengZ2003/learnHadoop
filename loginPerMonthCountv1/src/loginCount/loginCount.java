package loginCount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import loginCount.loginCountMapper;
import loginCount.loginCountReducer;
import loginCount.loginCount;

public class loginCount {
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				Configuration conf = new Configuration();
				String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
				Job loginCount = Job.getInstance(conf,"login count");
				
				//重要：指定本job所在的jar包
				loginCount.setJarByClass(loginCount.class);		// 类反射
				
				//设置wordCountJob所用的mapper逻辑类为哪个类wordcount
				loginCount.setMapperClass(loginCountMapper.class);
				//设置wordCountJob所用的reducer逻辑类为哪个类
				loginCount.setReducerClass(loginCountReducer.class);
				
				//设置map阶段输出的kv数据类型
				loginCount.setMapOutputKeyClass(memberLogin.class);
				loginCount.setMapOutputValueClass(IntWritable.class);
				
				//设置最终输出的kv数据类型
				loginCount.setOutputKeyClass(memberLogin.class);
				loginCount.setOutputValueClass(IntWritable.class);
				loginCount.setCombinerClass(loginCountCombiner.class);
				loginCount.setPartitionerClass(loginCountPartitioner.class);
				loginCount.setNumReduceTasks(12);
				
				//设置要处理的文本数据所存放的路径
				FileInputFormat.setInputPaths(loginCount, new Path(otherArgs[0]));
				FileOutputFormat.setOutputPath(loginCount, new Path(otherArgs[1]));
				
				//提交job给hadoop集群(Yarn)
				loginCount.waitForCompletion(true);
}
}