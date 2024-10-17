package orderedDTO;

public class OrderedDTO {
	private String tid = null;
	private String cid = null;
	private String pcode = null;
	private String ecode = null;
	private String ucode = null;
	private String bname = null;
	private int quantity = 0;
	private int fullprice = 0;
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public String getEcode() {
		return ecode;
	}
	public void setEcode(String ecode) {
		this.ecode = ecode;
	}
	public String getUcode() {
		return ucode;
	}
	public void setUcode(String ucode) {
		this.ucode = ucode;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getFullprice() {
		return fullprice;
	}
	public void setFullprice(int fullprice) {
		this.fullprice = fullprice;
	}
	@Override
	public String toString() {
		return "OrderedDTO [tid=" + tid + ", cid=" + cid + ", pcode=" + pcode + ", ecode=" + ecode + ", ucode=" + ucode
				+ ", bname=" + bname + ", quantity=" + quantity + ", fullprice=" + fullprice + "]";
	}
	
	
}
