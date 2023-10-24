package loginCount;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class memberLogin implements WritableComparable<memberLogin> {
	private String Name;
	private String LoginDate;

	public memberLogin() {
	}

	public memberLogin(String name, String loginDate) {
		super();
		Name = name;
		LoginDate = loginDate;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public void setLoginDate(String LoginDate) {
		this.LoginDate = LoginDate;
	}

	public String getName() {
		return Name;
	}

	public String getLoginDate() {
		return LoginDate;
	}

	public void readFields(DataInput input) throws IOException {
		this.Name = input.readUTF();
		this.LoginDate = input.readUTF();
	}

	public void write(DataOutput out) throws IOException {
		out.writeUTF(this.Name);
		out.writeUTF(this.LoginDate);
	}

	@Override
	public int compareTo(memberLogin o) {
		return (this.Name + this.LoginDate).compareTo(o.Name + o.LoginDate);
	}

	@Override
	public String toString() {
		return this.Name + "," + this.LoginDate;
	}

}
