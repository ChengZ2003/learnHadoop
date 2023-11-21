package hivetest;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 

import org.apache.log4j.Logger; 


public class hivetest {
	 private static String driverName = "org.apache.hive.jdbc.HiveDriver"; 
	 private static String url = "jdbc:hive2://master:10000/default;auth=noSasl"; 
	 private static String user = "jkx"; 
	 private static String password = "jkx"; 
	 private static String sql = ""; 
	 private static ResultSet res; 
	 private static final Logger log = Logger.getLogger(hivetest.class); 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try { 
            Class.forName(driverName); // 注册JDBC驱动 

            //默认使用端口10000, 使用默认数据库，用户名密码默认 
            Connection conn = DriverManager.getConnection(url, user, password);
            //    Statement用来执行SQL语句
            Statement stmt = conn.createStatement();
            String databaseName = "test"; 
            sql = "use " + databaseName;
            stmt.execute(sql);
            // 表名 
            String tableName = "temperature"; 
            // 执行“describe table”操作 
            sql = "describe " + tableName; 
            System.out.println("Running:" + sql); 
            res = stmt.executeQuery(sql); 
            System.out.println("执行“describe table”运行结果:"); 
            while (res.next()) { 
                System.out.println(res.getString(1) + "\t" + res.getString(2)); 
            } 
            // 执行“select * query”操作 
            sql = "select count(*) from " + tableName; 
            System.out.println("Running:" + sql); 
            res = stmt.executeQuery(sql); 
            System.out.println("执行“select count(*) ”运行结果:"); 
            while (res.next()) { 
                System.out.println(res.getString(1)); 
            }          
            conn.close(); 
            conn = null; 
            System.out.println("结束"); 
        } catch (ClassNotFoundException e) { 
            e.printStackTrace(); 
            log.error(driverName + " not found!", e); 
            System.exit(1); 
        } catch (SQLException e) { 
            e.printStackTrace(); 
            log.error("Connection error!", e); 
            System.exit(1); 
        } 

	}

}
