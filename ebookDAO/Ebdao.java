package ebookDAO;

import java.util.ArrayList;

import eDTO.EbookDTO;
import eReadDTO.EreadDTO;
import orderedDTO.OrderedDTO;

public interface Ebdao {
	public ArrayList<EbookDTO> selectAll();
	public void insert(OrderedDTO odto);
	public void cinsert(EreadDTO erdto);
	public ArrayList<EreadDTO> cselectAll(String ceid);
	public void readebook(EreadDTO erdto);
	public void readplus(EreadDTO erdto);
	public void readminus(EreadDTO erdto);
}
