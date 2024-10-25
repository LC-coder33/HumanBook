package ebookDAO;

import java.util.ArrayList;

import eDTO.EbookDTO;
import eReadDTO.EreadDTO;
import orderedDTO.OrderedDTO;

public interface Ebdao {
	public ArrayList<EbookDTO> selectAll();
	public ArrayList<EbookDTO> oneSelect(String ename);
	public ArrayList<OrderedDTO> selectOne(String cid);
	public void insert(OrderedDTO odto);
	public void cinsert(EreadDTO erdto);
	public ArrayList<EreadDTO> cselectAll(String ceid);
	public void readebook(EreadDTO erdto);
	public void readplus(EreadDTO erdto);
	public void readminus(EreadDTO erdto);
}
