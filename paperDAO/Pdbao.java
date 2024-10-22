package paperDAO;

import java.util.ArrayList;

import orderedDTO.OrderedDTO;
import paperDTO.PaperDTO;

public interface Pdbao {
	public ArrayList<PaperDTO> selectAll();
	public ArrayList<OrderedDTO> selectOne(String cid);
	public void insert(OrderedDTO odto);
	public ArrayList<PaperDTO> oneSelect(String pname);
}
