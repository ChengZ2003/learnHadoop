package studentScore;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class studentScoreComparator extends WritableComparator{

	protected studentScoreComparator() {
		super(studentScores.class,true);
	}
	
	
	public int compare(WritableComparable w1,WritableComparable w2) {
		
		studentScores k1 = (studentScores)w1;
		studentScores k2 = (studentScores)w2;
		
		if(k1.getAverageScore() != k2.getAverageScore())
			return -1 * (k1.getAverageScore() > k2.getAverageScore() ? 1 : (k1.getAverageScore() == k2.getAverageScore() ? 0 : -1));
		else 
			return -1 * (k1.getStudentNo().compareTo(k2.getStudentNo()));	
	}
}
