package cn.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

public class HBasePut {
	static Configuration cfg = HBaseConfiguration.create();
	
	static void put() throws IOException{		
		Connection conn = ConnectionFactory.createConnection(cfg);
		Table tb=conn.getTable(TableName.valueOf("scores"));
		Put p=new Put(Bytes.toBytes("jason"));
		p.addColumn(Bytes.toBytes("grade"), Bytes.toBytes(""), Bytes.toBytes("2"));
		p.addColumn(Bytes.toBytes("course"), Bytes.toBytes("math"), Bytes.toBytes("57"));
		p.addColumn(Bytes.toBytes("course"), Bytes.toBytes("art"), Bytes.toBytes("87"));		
		tb.put(p);
		p=new Put(Bytes.toBytes("tom"));
		p.addColumn(Bytes.toBytes("grade"), Bytes.toBytes(""), Bytes.toBytes("1"));
		p.addColumn(Bytes.toBytes("course"), Bytes.toBytes("math"), Bytes.toBytes("89"));
		p.addColumn(Bytes.toBytes("course"), Bytes.toBytes("art"), Bytes.toBytes("80"));		
		tb.put(p);
		conn.close();
	}	
	
	public static void main(String[] args) throws IOException{
		put();		
	}
}