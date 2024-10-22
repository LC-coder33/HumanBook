package login;

public interface Logginginterface {
	
	public void login(String id);
	public void log();
	public void logOut();
	public void managerIn();
	public void managerOut();
	public boolean isloggedIn();
	public boolean isManager();
	public void setManager(boolean manager);
	public String getId();
	public void setId(String id);
}
