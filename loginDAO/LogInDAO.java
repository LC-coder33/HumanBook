package loginDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectionDAO.ConnectionDAO;
import customerDTO.CustomerDTO;

public class LogInDAO implements LgDbdao {
	private ConnectionDAO cdao = ConnectionDAO.getInstance();
	public static LogInDAO logdao = null;

	public static LogInDAO getInstance() {
		if(logdao == null) {
			logdao = new LogInDAO();
		}
		return logdao;
	}

	@Override
	public void signUp(CustomerDTO cd) {
//		if (cd.getCid() == null || cd.getCid().trim().isEmpty()) {
//			System.out.println("아이디를 입력하지 않으셨습니다.");
//			return;
//		} else if(cd.getCname() == null || cd.getCname().trim().isEmpty()) {
//			System.out.println("이름을 입력하지 않으셨습니다.");
//			return;
//		} else if(cd.getCpassword() == null || cd.getCpassword().trim().isEmpty()) {
//		        System.out.println("비밀번호를 입력하지 않으셨습니다.");
//		        return;
//		    }	당장은 쓸 필요 x
		if(cdao.conn()) {
			try {
				String sql = "insert into customer values (?,?,?)";
				PreparedStatement psmt = cdao.conn.prepareStatement(sql);
				psmt.setString(1, cd.getCid());
				psmt.setString(2, cd.getCname());
				psmt.setString(3, cd.getCpassword());
				int resultInt = psmt.executeUpdate();
				if(resultInt == 0) {
					cdao.conn.rollback();
				}else {
					cdao.conn.commit();
				}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				if(cdao.conn != null) {
					try {
						cdao.conn.close();
					} catch(Exception e) {
						
					}
				}
			}
		}
	}
	
	@Override
	public boolean duplicateId(String id) {
		boolean dup = true;
		if(cdao.conn()) {
				try {
				String sql = "select count(*) from customer where cid = ?";
					PreparedStatement psmt = cdao.conn.prepareStatement(sql);
					psmt.setString(1, id);
					ResultSet rs = psmt.executeQuery();
					
					if(rs.next() && rs.getInt(1) > 0) {
						System.out.println("중복된 아이디입니다. 다시 입력하세요.");
						dup = true;
					} else {
						System.out.println("해당 아이디를 사용합니다.");
						dup = false;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} 
		} return dup;
	}
	
	@Override
	public boolean logIn(String id, String pw) {
		boolean valid = false;
		if(cdao.conn()) {
			try {
				String sql = "select count(*) from customer where cid = ? AND cpassword = ?";
				PreparedStatement psmt = cdao.conn.prepareStatement(sql);
				psmt.setString(1, id);
				psmt.setString(2, pw);
				ResultSet rs = psmt.executeQuery();
				if(rs.next() && rs.getInt(1) > 0) {
					valid = true;
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}finally {
				if(cdao.conn != null) {
					try {
						cdao.conn.close();
					} catch (Exception e) {
						
					}
				}
			}
		} return valid;
	} 
}
