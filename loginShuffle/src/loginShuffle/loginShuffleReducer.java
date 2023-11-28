package loginShuffle;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class loginShuffleReducer extends Reducer<IntWritable, Text, Text, IntWritable>{
	protected void reduce(IntWritable key, Iterable<Text> values,Context context) throws IOException, InterruptedException {
		//输出这个单词的统计结果
		for(Text value:values) {
			context.write((Text) value,key);
		}
	}
}
