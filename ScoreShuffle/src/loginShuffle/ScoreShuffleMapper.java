package loginShuffle;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class ScoreShuffleMapper extends Mapper<LongWritable,   Text, DoubleWritable,  Text>{
	protected void map(LongWritable key, Text value,Context context) throws IOException, InterruptedException {
		//拿到一行文本内容，转换成String 类型
		String line = value.toString();
		//将这行文本切分成单词
		String[] words = line.split("\t");
		
		//输出<单词，1>
//		for(String word:words){				  // int
//			context.write(new Text(word), new IntWritable(1));
//						//<   key       ,     value>
//		}
//		context.write(new Text(words[0]), new IntWritable(1));
		context.write(new DoubleWritable(Double.parseDouble(words[1])), new Text(words[0]));
	}
}