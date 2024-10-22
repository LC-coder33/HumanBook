package login;

public class LoggingIn implements Logginginterface {
	
	private String id = null;
	private boolean loggedIn = false;
	private boolean manager = false;
	
	@Override
	public void login(String id) {
		loggedIn = true;
	}
	
	public void log() {
		loggedIn = true;
		System.out.println("로그인 하였습니다.");
	}

	@Override
	public void logOut() {
		id = null;
		loggedIn = false;
		System.out.println("로그아웃되었습니다.");
	}
	
	@Override
	public void managerIn() {
		manager = true;
	}
	
	@Override
	public void managerOut() {
		manager = false;
	}

	public boolean isloggedIn() {
		return loggedIn;
	}

	@Override
	public boolean isManager() {
		return manager;
	}

	@Override
	public void setManager(boolean manager) {
		this.manager = manager;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

}
