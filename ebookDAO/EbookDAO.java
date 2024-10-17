package ebookDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectionDAO.ConnectionDAO;
import eDTO.EbookDTO;
import eReadDTO.EreadDTO;
import orderedDTO.OrderedDTO;

public class EbookDAO {
	private ConnectionDAO cdao = ConnectionDAO.getInstance();
	public static EbookDAO edao = null;
	
	public static EbookDAO getInstance() {
		if(edao == null) {
			edao = new EbookDAO();
		}
		return edao;
	}
	
	public ArrayList<EbookDTO> selectAll() {
		ArrayList<EbookDTO> elist = new ArrayList<EbookDTO>();
		if(cdao.conn()) {
			try {
				String sql = "select * from ebook order by ecode asc";
				PreparedStatement psmt = cdao.conn.prepareStatement(sql);
				ResultSet rs = psmt.executeQuery();
				while(rs.next()) {
					EbookDTO eTemp = new EbookDTO();
					eTemp.setEcode(rs.getString("ecode"));
					eTemp.setEname(rs.getString("ename"));
					eTemp.setEauthor(rs.getString("eauthor"));
					eTemp.setEprice(rs.getInt("eprice"));
					eTemp.setEpage(rs.getInt("epage"));
					elist.add(eTemp);
				}	
			} catch(SQLException e) { e.printStackTrace();
			}
		}
		return elist;
	}
	public void insert(OrderedDTO odto) {
		if(cdao.conn()) {
			try {
				String sql = 
				"insert into ordered values ('o e' || ordered_seq.NEXTVAL, ?,?,?,?,"
				+ "(SELECT ename FROM ebook where ecode = ?),?,"
				+ "(SELECT eprice FROM ebook where ecode = ?))";
				PreparedStatement psmt = cdao.conn.prepareStatement(sql);
				psmt.setString(1, odto.getCid());
				psmt.setString(2, null);
				psmt.setString(3, odto.getEcode());
				psmt.setString(4, null);
				psmt.setString(5, odto.getEcode());
				psmt.setInt(6, 1);
				psmt.setString(7, odto.getEcode());
				int resultInt = psmt.executeUpdate();
				if(resultInt > 0) {
					cdao.conn.commit();
				} else {
					cdao.conn.rollback();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void cinsert(EreadDTO erdto) {
		if(cdao.conn()) {
			try {
				String sql = "INSERT INTO currentebook VALUES (?, ?, "
						+ "(SELECT ename FROM ebook WHERE ecode = ?), "
						+ "(SELECT epage FROM ebook WHERE ecode = ?), ?)";
				PreparedStatement psmt = cdao.conn.prepareStatement(sql);
				psmt.setString(1, erdto.getCeid());
				psmt.setString(2, erdto.getEcode());
				psmt.setString(3, erdto.getEcode());
				psmt.setString(4, erdto.getEcode());
				psmt.setInt(5, 1);
				int resultInt = psmt.executeUpdate();
				if(resultInt > 0) {
					cdao.conn.commit();
				} else {
					cdao.conn.rollback();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<EreadDTO> cselectAll(String ceid) {
		ArrayList<EreadDTO> erlist = new ArrayList<EreadDTO>();
				if(cdao.conn()) {
					try {
						String sql = 
						"SELECT * FROM currentEbook WHERE ceid = ? AND ecode IS NOT NULL";
						PreparedStatement psmt = cdao.conn.prepareStatement(sql);
						psmt.setString(1, ceid);
						ResultSet rs = psmt.executeQuery();
						while(rs.next()) {
							EreadDTO eTemp = new EreadDTO();
							eTemp.setCeid(rs.getString("ceid"));
							eTemp.setEcode(rs.getString("ecode"));
							eTemp.setEname(rs.getString("ename"));
							eTemp.setEapage(rs.getInt("eapage"));
							eTemp.setEfpage(rs.getInt("efpage"));
							erlist.add(eTemp);
						}
					} catch(SQLException e) {
						e.printStackTrace();
					}
				}
				return erlist;
	}
	
	public void readebook(EreadDTO erdto) {
		if(cdao.conn()) {
			try {
				String sql = "UPDATE currentebook set efpage = ? where ceid = ? AND ecode = ?";
				PreparedStatement psmt = cdao.conn.prepareStatement(sql);
				psmt.setInt(1, erdto.getEfpage());
				psmt.setString(2, erdto.getCeid());
				psmt.setString(3, erdto.getEcode());
				int resultInt = psmt.executeUpdate();
				if(resultInt > 0) {
					cdao.conn.commit();
					System.out.println("페이지를 변경합니다.");
				} else {
					cdao.conn.rollback();
					System.out.println("페이지 변경에 실패했습니다.");
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void readplus(EreadDTO erdto) {
		if(cdao.conn()) {
			try {
				String selectSql = "SELECT efpage FROM currentebook WHERE ceid = ? AND ecode = ?";
	            PreparedStatement selectPsmt = cdao.conn.prepareStatement(selectSql);
	            selectPsmt.setString(1, erdto.getCeid());
	            selectPsmt.setString(2, erdto.getEcode());
	            ResultSet rs = selectPsmt.executeQuery();

	            int currentPage = 0;
	            if (rs.next()) {
	                currentPage = rs.getInt("efpage");
	            }
				
				String sql = "UPDATE currentebook set efpage = ? where ceid = ? AND ecode = ?";
				PreparedStatement psmt = cdao.conn.prepareStatement(sql);
				psmt.setInt(1, currentPage + 1);
				psmt.setString(2, erdto.getCeid());
				psmt.setString(3, erdto.getEcode());
				int resultInt = psmt.executeUpdate();
				int page = erdto.getEfpage();
				if(resultInt > 0) {
					cdao.conn.commit();
					System.out.println("페이지: " + (currentPage + 1));
				} else {
					cdao.conn.rollback();
					System.out.println("페이지 변경에 실패했습니다.");
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void readminus(EreadDTO erdto) {
		if(cdao.conn()) {
			try {
				String selectSql = "SELECT efpage FROM currentebook WHERE ceid = ? AND ecode = ?";
	            PreparedStatement selectPsmt = cdao.conn.prepareStatement(selectSql);
	            selectPsmt.setString(1, erdto.getCeid());
	            selectPsmt.setString(2, erdto.getEcode());
	            ResultSet rs = selectPsmt.executeQuery();

	            int currentPage = 0;
	            if (rs.next()) {
	                currentPage = rs.getInt("efpage");
	            }
				
				String sql = "UPDATE currentebook set efpage = ? where ceid = ? AND ecode = ?";
				PreparedStatement psmt = cdao.conn.prepareStatement(sql);
				psmt.setInt(1, currentPage - 1);
				psmt.setString(2, erdto.getCeid());
				psmt.setString(3, erdto.getEcode());
				int resultInt = psmt.executeUpdate();
				int page = erdto.getEfpage();
				if(resultInt > 0) {
					cdao.conn.commit();
					System.out.println("페이지: " + (currentPage - 1));
				} else {
					cdao.conn.rollback();
					System.out.println("페이지 변경에 실패했습니다.");
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
