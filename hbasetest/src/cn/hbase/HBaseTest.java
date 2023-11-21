package cn.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.util.Bytes;

public class HBaseTest {
	private static Configuration cfg = null;	
	static {
		cfg = HBaseConfiguration.create();
		cfg.set("hbase.zookeeper.quorum", "master");
		cfg.set("hbase.zookeeper.property.clientPort", "2181");		
	}
	// 列出数据库中所有表
	public static void list() throws IOException {
		// 创建数据库链接		
		
		Connection conn = ConnectionFactory.createConnection(cfg);		
		// Admin用于管理HBase数据库的表信息
		Admin admin = conn.getAdmin();
		System.out.println("===list Namespace===");
		NamespaceDescriptor[] ns = admin.listNamespaceDescriptors();
	        for(NamespaceDescriptor n : ns){
	            System.out.println(n.getName());
	        }

			System.out.println("===list tables:===");
		for (TableName tn : admin.listTableNames())
			System.out.println(tn);
		conn.close();// 关闭连接
	}

	// 创建表
	public static void create(String tableName, String... familyNames)
			throws IOException {		
		Connection conn = ConnectionFactory.createConnection(cfg);
		Admin admin = conn.getAdmin();
		// TableName:以POJO对象来封装表的名字
		TableName tn = TableName.valueOf(tableName);
		if (admin.tableExists(tn)) { // 如果存在先删除
			admin.disableTable(tn);// 先使表无效
			admin.deleteTable(tn);
		}
		// HTableDescriptor包含了表的名字及其对应的列族
		HTableDescriptor htd = new HTableDescriptor(tn);
		for (String family : familyNames)
			htd.addFamily(new HColumnDescriptor(family));
		admin.createTable(htd);
		conn.close();
		System.out.println("create success!");
	}

	// 修改表-增加列族
	public static void addColumnFamily(String tableName, String... familyNames)
			throws IOException {
		Connection conn = ConnectionFactory.createConnection(cfg);
		Admin admin = conn.getAdmin();
		TableName tn = TableName.valueOf(tableName);
		HTableDescriptor htd = admin.getTableDescriptor(tn);
		for (String family : familyNames)
			htd.addFamily(new HColumnDescriptor(family));
		admin.modifyTable(tn, htd);
		conn.close();
		System.out.println("modify success!");
	}

	// 修改表-删减列族
	public static void removeColumnFamily(String tableName,
			String... familyNames) throws IOException {
		Connection conn = ConnectionFactory.createConnection(cfg);
		Admin admin = conn.getAdmin();
		TableName tn = TableName.valueOf(tableName);
		HTableDescriptor htd = admin.getTableDescriptor(tn);
		for (String family : familyNames)
			htd.removeFamily(Bytes.toBytes(family));// 删减指定列族
		admin.modifyTable(tn, htd);
		conn.close();
		System.out.println("remove success!");
	}

	// 查看表结构
	public static void describe(String tableName) throws IOException {
		Connection conn = ConnectionFactory.createConnection(cfg);
		Admin admin = conn.getAdmin();
		TableName tn = TableName.valueOf(tableName);
		HTableDescriptor htd = admin.getTableDescriptor(tn);
		System.out.println("===decribe " + tableName + ":===");
		for (HColumnDescriptor hcd : htd.getColumnFamilies()) {
			System.out.println(hcd.getNameAsString());
		}
		System.out.println("===============");
		conn.close();
	}

	public static void main(String[] args) throws IOException {
		
		//create("scores", "grade", "course");// 创建表并指定2个列族
		//describe("scores");
		//addColumnFamily("scores", "f1", "f2");// 新增2个列族
		//describe("scores");
		//removeColumnFamily("scores", "f1");// 删除1个列族
		//describe("scores");
		list();
		
		
	}
}
