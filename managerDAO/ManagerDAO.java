package managerDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectionDAO.ConnectionDAO;
import eDTO.EbookDTO;
import paperDTO.PaperDTO;

public class ManagerDAO implements midao {
	private ConnectionDAO cdao = ConnectionDAO.getInstance();
	public static ManagerDAO mdao = null;
	
	public static ManagerDAO getInstance() {
		if(mdao == null) {
			mdao = new ManagerDAO();
		}
		return mdao;
	}
	
	public void pinsert(PaperDTO pdto) {
		if(cdao.conn()) {
			try {
				String sql = "INSERT INTO paperbook VALUES ('p' || paper_seq.NEXTVAL, ?,?,?,?)";
				PreparedStatement psmt = cdao.conn.prepareStatement(sql);
				psmt.setString(1, pdto.getPname());
				psmt.setString(2, pdto.getPauthor());
				psmt.setInt(3, pdto.getPprice());
				psmt.setInt(4, pdto.getPquantity());
				int resultInt = psmt.executeUpdate();
				if(resultInt > 0) {
					cdao.conn.commit();
				}else {
					cdao.conn.rollback();
				}
			} catch(SQLException e) {
				
			}
		}
	}
	
	public String pcodereturn(PaperDTO pdto) {
		String pcode = null;
		if(cdao.conn()) {
			try {
				String pcodeSql = "SELECT pcode FROM paperbook WHERE pname = ? AND pauthor = ? ORDER BY pcode ASC";
				PreparedStatement psmt = cdao.conn.prepareStatement(pcodeSql);
				psmt = cdao.conn.prepareStatement(pcodeSql);
				psmt.setString(1, pdto.getPname());
				psmt.setString(2, pdto.getPauthor());
				ResultSet rs = psmt.executeQuery();
				if (rs.next()) {
					pcode = rs.getString("pcode");
				}
			} catch(SQLException e) {
				
			}
		}
		return pcode;
	}
	
	public PaperDTO selectPaper(String selcode) {
		if(cdao.conn()) {
			try {
				String sql="select * from paperbook where pcode = ? ORDER BY pcode ASC";
				PreparedStatement psmt = cdao.conn.prepareStatement(sql);
				psmt.setString(1, selcode);
				ResultSet rs = psmt.executeQuery();
				if(rs.next()) {
					PaperDTO pTemp = new PaperDTO();
					pTemp.setPcode(rs.getString("pcode"));
					pTemp.setPname(rs.getString("pname"));
					pTemp.setPauthor(rs.getString("pauthor"));
					pTemp.setPprice(rs.getInt("pprice"));
					pTemp.setPquantity(rs.getInt("pquantity"));
					return pTemp;
				}
			} catch (Exception e) {
				
			} finally {
				if(cdao.conn != null) {
					try {
						cdao.conn.close();
					} catch(Exception e) {
						
					}
				}
			}
		}
		return null;
	}
	
	public void pmod(PaperDTO pdto) {
		if(cdao.conn()) {
			try {
				String sql = 
				"update paperbook set pname=?, pauthor=?, pprice=?, pquantity=? where pcode=?";
				PreparedStatement psmt = cdao.conn.prepareStatement(sql);
				psmt.setString(1, pdto.getPname());
				psmt.setString(2, pdto.getPauthor());
				psmt.setInt(3, pdto.getPprice());
				psmt.setInt(4, pdto.getPquantity());
				psmt.setString(5, pdto.getPcode());
				psmt.executeUpdate();
			} catch (Exception e) {
				
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
	
	public void pdel(String delcode) {
		if(cdao.conn()) {
			try {
				String sql = "delete from paperbook where pcode=?";
				PreparedStatement psmt =cdao.conn.prepareStatement(sql);
				psmt.setString(1, delcode);
				psmt.executeUpdate();
			} catch(Exception e) {
				
			} finally {
				try {
					if(cdao.conn !=null) cdao.conn.close();
				} catch (Exception e2) {
					
				}
			}
		}
	}

	
	
	public void einsert(EbookDTO edto) {
		if(cdao.conn()) {
			try {
				String sql = "INSERT INTO ebook VALUES ('e' || ebook_seq.NEXTVAL, ?,?,?,?)";
				PreparedStatement psmt = cdao.conn.prepareStatement(sql);
				psmt.setString(1, edto.getEname());
				psmt.setString(2, edto.getEauthor());
				psmt.setInt(3, edto.getEprice());
				psmt.setInt(4, edto.getEpage());
				int resultInt = psmt.executeUpdate();
				if(resultInt > 0) {
					cdao.conn.commit();
				}else {
					cdao.conn.rollback();
				}
			} catch(SQLException e) {
				
			}
		}
	}
	
	public EbookDTO selectEbook(String selecode) {
		if(cdao.conn()) {
			try {
				String sql="select * from ebook where ecode = ?";
				PreparedStatement psmt = cdao.conn.prepareStatement(sql);
				psmt.setString(1, selecode);
				ResultSet rs = psmt.executeQuery();
				if(rs.next()) {
					EbookDTO eTemp = new EbookDTO();
					eTemp.setEcode(rs.getString("ecode"));
					eTemp.setEname(rs.getString("ename"));
					eTemp.setEauthor(rs.getString("eauthor"));
					eTemp.setEprice(rs.getInt("eprice"));
					eTemp.setEpage(rs.getInt("epage"));
					return eTemp;
				}
			} catch (Exception e) {
				
			} finally {
				if(cdao.conn != null) {
					try {
						cdao.conn.close();
					} catch(Exception e) {
						
					}
				}
			}
		}
		return null;
	}
	
	public void emod(EbookDTO edto) {
		if(cdao.conn()) {
			try {
				String sql = 
				"update ebook set ename=?, eauthor=?, eprice=?, epage=? where ecode=?";
				PreparedStatement psmt = cdao.conn.prepareStatement(sql);
				psmt.setString(1, edto.getEname());
				psmt.setString(2, edto.getEauthor());
				psmt.setInt(3, edto.getEprice());
				psmt.setInt(4, edto.getEpage());
				psmt.setString(5, edto.getEcode());
				psmt.executeUpdate();
			} catch (Exception e) {
				
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
	
	public void edel(String delecode) {
		if(cdao.conn()) {
			try {
				String sql = "delete from ebook where ecode=?";
				PreparedStatement psmt =cdao.conn.prepareStatement(sql);
				psmt.setString(1, delecode);
				psmt.executeUpdate();
			} catch(Exception e) {
				
			} finally {
				try {
					if(cdao.conn !=null) cdao.conn.close();
				} catch (Exception e2) {
					
				}
			}
		}
	}
}
