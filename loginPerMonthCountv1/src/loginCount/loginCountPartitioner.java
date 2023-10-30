package loginCount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class loginCountPartitioner extends Partitioner<memberLogin, IntWritable> {

	@Override
	public int getPartition(memberLogin key, IntWritable value, int numPartitions) {
		String date = key.getLoginDate();
		int month = Integer.parseInt(date.substring(5));
		return month % numPartitions;
	}
}
