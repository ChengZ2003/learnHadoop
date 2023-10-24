package loginShuffle;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ScoreShuffleReducer extends Reducer<DoubleWritable, Text, Text, DoubleWritable>{
	protected void reduce(DoubleWritable key, Iterable<Text> values,Context context) throws IOException, InterruptedException {
		//定义一个计数器
//		int count = 0;
		//通过value这个迭代器，遍历这一组kv中所有的value，进行累加
//		for(IntWritable value:values){
//			count+=value.get();
//		}value
		
		//输出这个单词的统计结果
		for(Text value:values) {
//			context.write((Text) value,key);
			context.write(value,key);
		}
		
	}
}
