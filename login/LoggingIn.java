package login;

public class LoggingIn {
	
	private String id = null;
	private boolean loggedIn = false;
	private boolean manager = false;
	
	public void login(String id) {
		loggedIn = true;
	}

	public void logOut() {
		id = null;
		loggedIn = false;
		System.out.println("로그아웃되었습니다.");
	}
	
	public void managerIn() {
		manager = true;
	}
	
	public void managerOut() {
		manager = false;
	}

	public boolean isloggedIn() {
		return loggedIn;
	}

	public boolean isManager() {
		return manager;
	}

	public void setManager(boolean manager) {
		this.manager = manager;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
