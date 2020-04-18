package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
		private static String driver = "com.mysql.cj.jdbc.Driver";
		private	static	String	url	= "jdbc:mysql://localhost:3306/activitysystem?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";
		private static String username = "root";
		private static String password = "123456";
		private static Connection conn = null;
		public static Connection getConnection() throws SQLException, ClassNotFoundException
		{
		if(conn == null || conn.isClosed()) {
			Class.forName(driver);
		conn = DriverManager.getConnection(url, username, password);
		}
		return conn;
		}
}


