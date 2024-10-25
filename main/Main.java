package main;

import ebookDAO.Ebdao;
import ebookDAO.EbookDAO;
import login.LoggingIn;
import login.Logginginterface;
import loginDAO.LgDbdao;
import loginDAO.LogInDAO;
import managerAccess.ManagerAccess;
import managerDAO.ManagerDAO;
import managerDAO.midao;
import menuFrame.MenuFrame;
import orderedDTO.OrderedDTO;
import paperDAO.PaperDAO;
import paperDAO.Pdbao;

public class Main {

	public static void main(String[] args) {
		LgDbdao dbdao = new LogInDAO();
		Logginginterface lg = new LoggingIn();
		Pdbao pdao = new PaperDAO();
		Ebdao edao = new EbookDAO();
		midao mdao = new ManagerDAO();
		OrderedDTO odto = new OrderedDTO();
		ManagerAccess ma = new ManagerAccess();
		
		//new MainMenu();
		MenuFrame mf = new MenuFrame(dbdao, lg, pdao, edao, mdao, odto, ma);
		//ManagerpFrame mnf = new ManagerpFrame(dbdao, lg, pdao, edao, mdao);
	}

}
