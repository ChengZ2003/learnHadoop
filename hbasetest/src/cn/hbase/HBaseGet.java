package cn.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

public class HBaseGet {
	private static Configuration cfg = null;	
	static {
		cfg = HBaseConfiguration.create();
		cfg.set("hbase.zookeeper.quorum", "master");
		cfg.set("hbase.zookeeper.property.clientPort", "2181");		
	}
	/**
	 * 	 按行键获取
	 */
	static void get(String tableName, String rowKey) throws IOException {
		Connection conn = ConnectionFactory.createConnection(cfg);
		Table tb = conn.getTable(TableName.valueOf(tableName));
		Get g = new Get(Bytes.toBytes(rowKey));
		Result result = tb.get(g);
		System.out.println(String.format(
				"result.value=%s,result.toString():%s",
				Bytes.toString(result.value()), result));		
		conn.close();
	}
	/**
	 * 	 按行键，列族获取
	 */
	static void get(String tableName, String rowKey, String family)
			throws IOException {
		Connection conn = ConnectionFactory.createConnection(cfg);
		Table tb = conn.getTable(TableName.valueOf(tableName));
		Get g = new Get(Bytes.toBytes(rowKey));
		g.addFamily(Bytes.toBytes(family));
		Result result = tb.get(g);
		System.out.println(String.format(
				"result.value=%s,result.toString():%s",
				Bytes.toString(result.value()), result));
		conn.close();
	}
	/**
	 * 	 按行键，列族，列修饰符获取
	 */
	static void get(String tableName, String rowKey, String family,
			String qualifier) throws IOException {
		Connection conn = ConnectionFactory.createConnection(cfg);
		Table tb = conn.getTable(TableName.valueOf(tableName));
		Get g = new Get(Bytes.toBytes(rowKey));
		g.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier));
		Result result = tb.get(g);
		System.out.println(String.format(
				"result.value=%s,result.toString():%s",
				Bytes.toString(result.value()), result));
		conn.close();
	}
	
	static void scan(String tableName) throws IOException{		
		Connection conn = ConnectionFactory.createConnection(cfg);
		Table tb = conn.getTable(TableName.valueOf(tableName));		
		ResultScanner scanner=tb.getScanner(new Scan());//全表扫描
		for(Result row : scanner){
			System.out.println(row);
			for (Cell cell : row.listCells()) {
				System.out
						.println("Rowkey:" + Bytes.toString(row.getRow())
								+ "	Familiy:"
								+ Bytes.toString(CellUtil.cloneFamily(cell))
								+ "	Quilifier:"
								+ Bytes.toString(CellUtil.cloneQualifier(cell))
								+ "	Value:"
								+ Bytes.toString(CellUtil.cloneValue(cell)));
			}
		}
		conn.close();
	}

	public static void main(String[] args) throws IOException {
		get("test","1");
		//get("scores", "jason", "course");
		//get("scores", "jason", "course", "math");
		//scan("scores");
		scan("test");

		
		
	}

}
