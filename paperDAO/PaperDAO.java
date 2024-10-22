package paperDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectionDAO.ConnectionDAO;
import orderedDTO.OrderedDTO;
import paperDTO.PaperDTO;

public class PaperDAO implements Pdbao {
	private ConnectionDAO cdao = ConnectionDAO.getInstance();
	public static PaperDAO pdao = null;
	
	public static PaperDAO getInstance() {
		if(pdao == null) {
			pdao = new PaperDAO();
		}
		return pdao;
	}
	
	public ArrayList<PaperDTO> selectAll() {
		ArrayList<PaperDTO> plist = new ArrayList<PaperDTO>();
		if(cdao.conn()) {
			try {
				String sql = "select * from paperbook order by pcode asc";
				PreparedStatement psmt = cdao.conn.prepareStatement(sql);
				ResultSet rs = psmt.executeQuery();
				while(rs.next()) {
					PaperDTO pTemp = new PaperDTO();
					pTemp.setPcode(rs.getString("pcode"));
					pTemp.setPname(rs.getString("pname"));
					pTemp.setPauthor(rs.getString("pauthor"));
					pTemp.setPprice(rs.getInt("pprice"));
					pTemp.setPquantity(rs.getInt("pquantity"));
					plist.add(pTemp);
				}
			} catch(SQLException e) {e.printStackTrace();
			}
		}
		return plist;
	}
	
	public ArrayList<PaperDTO> oneSelect(String pname) {
		ArrayList<PaperDTO> plist = new ArrayList<PaperDTO>();
		if(cdao.conn()) {
			try {
				String sql = "select * from paperbook where pname LIKE ? order by pcode ASC";
				PreparedStatement psmt = cdao.conn.prepareStatement(sql);
				psmt.setString(1, "%" + pname + "%");
				ResultSet rs = psmt.executeQuery();
				while(rs.next()) {
					PaperDTO pTemp = new PaperDTO();
					pTemp.setPcode(rs.getString("pcode"));
					pTemp.setPname(rs.getString("pname"));
					pTemp.setPauthor(rs.getString("pauthor"));
					pTemp.setPprice(rs.getInt("pprice"));
					pTemp.setPquantity(rs.getInt("pquantity"));
					plist.add(pTemp);
				}
			} catch(SQLException e) {e.printStackTrace();
			}
		}
		return plist;
	}
	
	public ArrayList<OrderedDTO> selectOne(String cid) {
		ArrayList<OrderedDTO> olist = new ArrayList<OrderedDTO>();
		if(cdao.conn()) {
			try {
				String sql = 
				"select * from ordered where cid = ? AND pcode is not null";
				PreparedStatement psmt = cdao.conn.prepareStatement(sql);
				psmt.setString(1, cid);
				ResultSet rs = psmt.executeQuery();
				while(rs.next()) {
					OrderedDTO oTemp = new OrderedDTO();
					oTemp.setTid(rs.getString("tid"));
					oTemp.setCid(rs.getString("cid"));
					oTemp.setPcode(rs.getString("pcode"));
					oTemp.setEcode(rs.getString("ecode"));
					oTemp.setUcode(rs.getString("ucode"));
					oTemp.setBname(rs.getString("bname"));
					oTemp.setQuantity(rs.getInt("quantity"));
					oTemp.setFullprice(rs.getInt("fullprice"));
					olist.add(oTemp);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return olist;
	}
	
	public void insert(OrderedDTO odto) {
		if(cdao.conn()) {
			try {
				String sql =
				"insert into ordered values ('o p' || ordered_seq.NEXTVAL, ?,?,?,?,"
				+ "(SELECT pname FROM paperbook WHERE pcode = ?),?,"
				+ "(SELECT pprice FROM paperbook where pcode = ?)*?)";
				PreparedStatement psmt = cdao.conn.prepareStatement(sql);
				psmt.setString(1, odto.getCid());
				psmt.setString(2, odto.getPcode());
				psmt.setString(3, null);
				psmt.setString(4, null);
				psmt.setString(5, odto.getPcode());
				psmt.setInt(6, odto.getQuantity());
				psmt.setString(7, odto.getPcode());
				psmt.setInt(8, odto.getQuantity());
				int resultInt = psmt.executeUpdate();
				
				 String sqlUpdate = "UPDATE paperbook SET pquantity = pquantity - ? WHERE pcode = ?";
		            PreparedStatement psmtUpdate = cdao.conn.prepareStatement(sqlUpdate);
		            psmtUpdate.setInt(1, odto.getQuantity()); // 구매한 수량
		            psmtUpdate.setString(2, odto.getPcode()); // 책 코드
		            int resultUpdate = psmtUpdate.executeUpdate();
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
	
}
