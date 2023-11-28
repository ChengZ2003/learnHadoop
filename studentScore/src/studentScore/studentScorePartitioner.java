package studentScore;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class studentScorePartitioner extends Partitioner<studentScores, DoubleWritable> {

//	@Override
//	public int getPartition(studentScores key, IntWritable value, int numPartitions) {
//		String date = key.getLoginDate();
//		int month = Integer.parseInt(date.substring(5));
//		return month % numPartitions;
//	}

	@Override
	public int getPartition(studentScores key, DoubleWritable value, int numPartitions) {
		int classNo = Integer.parseInt(key.getClassNo());
		return (classNo - 12101);
	}
}
