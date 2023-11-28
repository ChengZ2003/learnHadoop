package studentScore;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import studentScore.JarUtil;
import studentScore.studentScore;
import studentScore.studentScoreMapper;
import studentScore.studentScoreReducer;

public class studentScore {

	public static void main(String[] args) throws Exception{
				Configuration conf = new Configuration();
//				String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
				conf.set("mapreduce.job.jar",JarUtil.jar(studentScore.class));
				Job loginCount = Job.getInstance(conf,"student score");
				
				//重要：指定本job所在的jar包
				loginCount.setJarByClass(studentScore.class);		// 类反射
				
				//设置wordCountJob所用的mapper逻辑类为哪个类wordcount
				loginCount.setMapperClass(studentScoreMapper.class);
				//设置wordCountJob所用的reducer逻辑类为哪个类
				loginCount.setReducerClass(studentScoreReducer.class);
				
				//设置map阶段输出的kv数据类型
				loginCount.setMapOutputKeyClass(studentScores.class);
				loginCount.setMapOutputValueClass(DoubleWritable.class);
				
				//设置最终输出的kv数据类型
				loginCount.setOutputKeyClass(studentScores.class);
				loginCount.setOutputValueClass(DoubleWritable.class);
				
				loginCount.setCombinerClass(studentScoreCombiner.class);
				loginCount.setPartitionerClass(studentScorePartitioner.class);
				loginCount.setNumReduceTasks(2);
				loginCount.setSortComparatorClass(studentScoreComparator.class);
				
				//设置要处理的文本数据所存放的路径
				FileInputFormat.setInputPaths(loginCount, new Path("hdfs://master:8020/student1.txt"));
				FileOutputFormat.setOutputPath(loginCount, new Path("hdfs://master:8020/output6"));
				
				//提交job给hadoop集群(Yarn)
				loginCount.waitForCompletion(true);
}
}