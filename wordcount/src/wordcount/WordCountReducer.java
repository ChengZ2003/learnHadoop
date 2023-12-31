package wordcount;
import java.io.IOException;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
//											< k2     v2 >       < k3     v3   >			
//                                           input  input        output output
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	@Override
	/*
	 * reduce方法提供给reduce task进程来调用
	 * 
	 * reduce task会将shuffle阶段分发过来的大量kv数据对进行聚合，聚合的机制是相同key的kv对聚合为一组
	 * 然后reduce task对每一组聚合kv调用一次我们自定义的reduce方法
	 * 比如：<hello,1><hello,1><hello,1><tom,1><tom,1><tom,1>
	 *  hello组会调用一次reduce方法进行处理，tom组也会调用一次reduce方法进行处理
	 *  调用时传递的参数：
	 *  		key：一组kv中的key
	 *  		values：一组kv中所有value的迭代器
	 */
	// shuffle                      <hello,<1,1,....>>
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