package loginShuffle;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class loginShuffleMapper extends Mapper<LongWritable,   Text, IntWritable,  Text>{
	protected void map(LongWritable key, Text value,Context context) throws IOException, InterruptedException {
		//拿到一行文本内容，转换成String 类型
		String line = value.toString();
		//将这行文本切分成单词
		String[] words = line.split("\t");
		context.write(new IntWritable(Integer.parseInt(words[1])), new Text(words[0]));
	}
}