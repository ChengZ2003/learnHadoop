package maxAndAverageScore;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class maxAndAverageScoreReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	protected void reduce(Text key, Iterable<IntWritable> values,Context context) throws IOException, InterruptedException {
		//定义一个计数器
		int[] Score = {0,0};
		int count = 0;
		//通过value这个迭代器，遍历这一组kv中所有的value，进行累加
		for(IntWritable value:values){
			if(value.get() > Score[1]) {
				Score[1] = value.get();
			}
			Score[0] += value.get();
			count ++;
		}
		
		context.write(key, new IntWritable(Score[0] / count));
		context.write(key, new IntWritable(Score[1]));
	}
}