package loginDAO;

import customerDTO.CustomerDTO;

public interface LgDbdao {
	public void signUp(CustomerDTO cd);
	public void duplicateId(String id);
	public boolean LogIn(String id, String pw);
	
	
}
