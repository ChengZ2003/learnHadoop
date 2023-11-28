package studentScore;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class studentScoreMapper extends Mapper<LongWritable,   Text, studentScores,  DoubleWritable>{
	protected void map(LongWritable key, Text value,Context context) throws IOException, InterruptedException {
		//拿到一行文本内容，转换成String 类型
		String data = value.toString();
		//将这行文本切分成单词
		String[] words = data.split(",");
		studentScores s = new studentScores(words[1],words[0],words[2],Integer.parseInt(words[3]),
											Integer.parseInt(words[4]),Integer.parseInt(words[5]));
		context.write(s, new DoubleWritable(s.getAverageScore()));
	}
}
