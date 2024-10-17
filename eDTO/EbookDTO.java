package eDTO;

public class EbookDTO {
	private String ecode = null;
	private String ename = null;
	private String eauthor = null;
	private int eprice = 0;
	private int epage = 0;
	private int equantity = 1;
	public String getEcode() {
		return ecode;
	}
	public void setEcode(String ecode) {
		this.ecode = ecode;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getEauthor() {
		return eauthor;
	}
	public void setEauthor(String eauthor) {
		this.eauthor = eauthor;
	}
	public int getEprice() {
		return eprice;
	}
	public void setEprice(int eprice) {
		this.eprice = eprice;
	}
	public int getEpage() {
		return epage;
	}
	public void setEpage(int epage) {
		this.epage = epage;
	}
	public int getEquantity() {
		return equantity;
	}
	public void setEquantity(int equantity) {
		this.equantity = equantity;
	}
	@Override
	public String toString() {
		return "EbookDTO [ecode=" + ecode + ", ename=" + ename + ", eauthor=" + eauthor + ", eprice=" + eprice
				+ ", epage=" + epage + ", equantity=" + equantity + "]";
	}
	
}
