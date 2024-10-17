package mainMenu;

import java.util.Scanner;

import customerDTO.CustomerDTO;
import ebookMge.EbookMge;
import login.LoggingIn;
import loginDAO.LogInDAO;
import managerAccess.ManagerAccess;
import managerMenu.ManagerMenu;
import paperMge.PaperMge;
import usedMge.UsedMge;

public class MainMenu {
	CustomerDTO cd = null;
	private PaperMge pmge = null;
	private EbookMge emge = null;
	private UsedMge umge = null;
	private ManagerMenu mMenu = null;
	private LoggingIn lg = null;
	private ManagerAccess ma = null;
	private LogInDAO logdao = LogInDAO.getInstance();
	public MainMenu() {
		if(lg == null) {
			lg = new LoggingIn();		
		}
		if(pmge == null) {
			pmge = new PaperMge(lg);
		}
		if(emge == null) {
			emge = new EbookMge(lg);
		}
		if(umge == null) {
			umge = new UsedMge();
		}
		if(mMenu == null) {
			mMenu = new ManagerMenu();
		}
		if(ma == null) {
			ma = new ManagerAccess();
		}
		menu();
	}
	public void menu() {
		Scanner in = new Scanner(System.in);
		boolean flag = true;
		while(flag) {
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.println("3. 관리자 로그인");
			System.out.println("4. 휴먼문고");
			System.out.println("5. e-book");
			System.out.println("6. 온라인중고");
			System.out.println("7. 관리자 메뉴");
			System.out.println("8. 프로그램 종료");
			int selNum = in.nextInt();
			in.nextLine();
			switch(selNum) {
			case 1: signUp(); break;
			case 2: logIn(); break;
			case 3: mgeLogin(); break;
			case 4: pmge.menu(); break;
			case 5: emge.menu(); break;
			case 6: umge.menu(); break;
			case 7: if(lg.isManager()) {
				mMenu.menu(); break;
				} else {
					System.out.println("관리자 계정이 아닙니다."); break;}
			case 8: flag = false; break;
			}
		}
		in.close();
	}
	private void signUp() {
		Scanner in = new Scanner(System.in);
		System.out.println("아이디를 입력하세요.");
		String id = in.nextLine();
		logdao.duplicateId(id);
		System.out.println("이름을 입력하세요.");
		String name = in.nextLine();
		System.out.println("비밀번호를 입력하세요.");
		String pw = in.nextLine();
		cd = new CustomerDTO();
		cd.setCid(id);
		cd.setCname(name);
		cd.setCpassword(pw);
		logdao.signUp(cd);
	}
	
	private void logIn() {
		Scanner in = new Scanner(System.in);
		System.out.println("아이디를 입력하세요.");
		String id = in.nextLine();
		System.out.println("비밀번호를 입력하세요.");
		String pw = in.nextLine();
		if(logdao.logIn(id, pw)) {
			lg.setId(id);
			System.out.println("환영합니다 " + lg.getId() + " 님");
		} else {
			System.out.println("아이디 또는 비밀번호가 잘못되었습니다.");
		}
	}
	private void mgeLogin() {
		if(lg.getId() != null) {
		lg.logOut();
		System.out.println(" ");
		}
		System.out.println("관리자 계정 코드를 입력하세요.");
		Scanner in = new Scanner(System.in);
		String macode = in.nextLine();
		if(macode.equals(ma.getMcode())) {
		lg.managerIn();
		System.out.println("관리자 계정으로 로그인하였습니다.");
		} else {
			lg.managerOut();
			System.out.println("관리자 계정으로 로그아웃하였습니다.");
		}
	}
}
