package managerDAO;

import eDTO.EbookDTO;
import paperDTO.PaperDTO;

public interface midao {
	public void pinsert(PaperDTO pdto);
	public String pcodereturn(PaperDTO pdto);
	public PaperDTO selectPaper(String selcode);
	public void pmod(PaperDTO pdto);
	public void pdel(String delcode);
	public void einsert(EbookDTO edto);
	public EbookDTO selectEbook(String selecode);
	public void emod(EbookDTO edto);
	public void edel(String delecode);
	public String ecodereturn(EbookDTO edto);
}
