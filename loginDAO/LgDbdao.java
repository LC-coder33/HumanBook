package loginDAO;

import customerDTO.CustomerDTO;

public interface LgDbdao {
	public void signUp(CustomerDTO cd);
	public boolean duplicateId(String id);
	public boolean logIn(String id, String pw);
	
}
