package eReadDTO;

public class EreadDTO {
	private String ceid = null;
	private String ecode = null;
	private String ename = null;
	private int eapage = 0;
	private int efpage = 0;
	public String getCeid() {
		return ceid;
	}
	public void setCeid(String ceid) {
		this.ceid = ceid;
	}
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
	public int getEapage() {
		return eapage;
	}
	public void setEapage(int eapage) {
		this.eapage = eapage;
	}
	public int getEfpage() {
		return efpage;
	}
	public void setEfpage(int efpage) {
		this.efpage = efpage;
	}
	@Override
	public String toString() {
		return "EreadDTO [ceid=" + ceid + ", ecode=" + ecode + ", ename=" + ename + ", eapage=" + eapage + ", efpage="
				+ efpage + "]";
	}

}
