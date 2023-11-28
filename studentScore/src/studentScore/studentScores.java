package studentScore;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class studentScores implements WritableComparable<studentScores> {
	private String studentName;
	private String studentNo;
	private String classNo;
	private int subject1Score;
	private int subject2Score;
	private int subject3Score;

	public studentScores() {
	}

	public studentScores(String studentName, String studentNo, String classNo, int subject1Score, int subject2Score,
			int subject3Score) {
		super();
		this.studentName = studentName;
		this.studentNo = studentNo;
		this.classNo = classNo;
		this.subject1Score = subject1Score;
		this.subject2Score = subject2Score;
		this.subject3Score = subject3Score;
	}

	public String getStudentName() {
		return this.studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentNo() {
		return this.studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getClassNo() {
		return this.classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	public int getSubject1Score() {
		return this.subject1Score;
	}

	public void setSubject1Score(int subject1Score) {
		this.subject1Score = subject1Score;
	}

	public int getSubject2Score() {
		return this.subject2Score;
	}

	public void setSubject2Score(int subject2Score) {
		this.subject2Score = subject2Score;
	}

	public int getSubject3Score() {
		return this.subject3Score;
	}

	public void setSubject3Score(int subject3Score) {
		this.subject3Score = subject3Score;
	}

	public double getAverageScore() {
		return (this.subject1Score + this.subject2Score + this.subject3Score) / 3.0;
	}

	public void readFields(DataInput input) throws IOException {
		this.studentName = input.readUTF();
		this.studentNo = input.readUTF();
		this.classNo = input.readUTF();
		this.subject1Score = input.readInt();
		this.subject2Score = input.readInt();
		this.subject3Score = input.readInt();
	}

	public void write(DataOutput out) throws IOException {
		out.writeUTF(this.studentName);
		out.writeUTF(this.studentNo);
		out.writeUTF(this.classNo);
		out.writeInt(this.subject1Score);
		out.writeInt(this.subject2Score);
		out.writeInt(this.subject3Score);
	}

	@Override
	public int compareTo(studentScores o) {
		return this.getAverageScore() > o.getAverageScore() ? 1 : (this.getAverageScore() == o.getAverageScore() ? 0 : -1);
	}

	@Override
	public String toString() {
		return this.studentNo + "," + this.studentName + "," + this.classNo;
	}

}
