package paperDTO;

public class PaperDTO {
	private String pcode = null;
	private String pname = null;
	private String pauthor = null;
	private int pprice = 0;
	private int pquantity = 0;
	public String getPcode() {
		return pcode;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPauthor() {
		return pauthor;
	}
	public void setPauthor(String pauthor) {
		this.pauthor = pauthor;
	}
	public int getPprice() {
		return pprice;
	}
	public void setPprice(int pprice) {
		this.pprice = pprice;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public int getPquantity() {
		return pquantity;
	}
	public void setPquantity(int pquantity) {
		this.pquantity = pquantity;
	}
	@Override
	public String toString() {
		return "PaperDTO [pcode=" + pcode + ", pname=" + pname + ", pauthor=" + pauthor + ", pprice=" + pprice
				+ ", pquantity=" + pquantity + "]";
	}
	
}