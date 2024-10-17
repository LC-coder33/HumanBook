package connectionDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDAO {
	private String username = "system";
	private String password = "11111111";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String driverName = "oracle.jdbc.driver.OracleDriver";
	public Connection conn = null;
	
	public static ConnectionDAO cdao = null;
	
	public static ConnectionDAO getInstance() {
		if(cdao == null) {
			cdao = new ConnectionDAO();
		}
		return cdao;
	}
	public ConnectionDAO() {
		init();
	}
	public void init() {
		try {
			Class.forName(driverName);
			System.out.println("오라클 드라이버 로드 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public boolean conn() {
		try {
			conn = DriverManager.getConnection(url,username,password);
			System.out.println("커넥션 자원 획득 성공");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("커넥션 자원 획득 실패");
		return false;
	}
}
