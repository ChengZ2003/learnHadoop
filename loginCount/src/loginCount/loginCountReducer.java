package loginCount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class loginCountReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	protected void reduce(Text key, Iterable<IntWritable> values,Context context) throws IOException, InterruptedException {
		//定义一个计数器
		int count = 0;
		//通过value这个迭代器，遍历这一组kv中所有的value，进行累加
		for(IntWritable value:values){
			count+=value.get();
		}
		//输出这个单词的统计结果
		context.write(key, new IntWritable(count));
	}
}