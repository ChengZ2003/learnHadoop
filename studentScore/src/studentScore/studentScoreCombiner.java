package studentScore;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class studentScoreCombiner extends Reducer<studentScores, DoubleWritable, studentScores, DoubleWritable>{
	protected void reduce(studentScores key, DoubleWritable values,Context context) throws IOException, InterruptedException {
		context.write(key, values);
	}
}