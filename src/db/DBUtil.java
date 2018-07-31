/**
 * 
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 * @author 佳阳
 * 
 */
public class DBUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DBUtil  util=new DBUtil();
	    Connection conn=util.openConnection();
	    System.out.println(conn);
	}
	
	//连接数据库
	public Connection openConnection(){
		Properties prop = new Properties();
		String driver = null;
		String url = null;
		String username=null;
		String password=null;
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("DBConfig.properties"));
			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			Class.forName(driver);
			return DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//关闭数据库
	public void closeConnection(Connection conn){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
