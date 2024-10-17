package ebookMge;

import java.util.ArrayList;
import java.util.Scanner;

import eDTO.EbookDTO;
import eReadDTO.EreadDTO;
import ebookDAO.EbookDAO;
import login.LoggingIn;
import orderedDTO.OrderedDTO;

public class EbookMge {
	private EbookDAO edao = EbookDAO.getInstance();
	private LoggingIn lg;
		
		public EbookMge(LoggingIn lg) {
			this.lg = lg;
		}

	public void menu() {
		Scanner in = new Scanner(System.in);
		boolean flag = true;
		while(flag) {
			System.out.println("1.전체 보기. 2.구매 3.구매목록보기 4.구매한 도서 읽기 5.종료");
			int selNum = in.nextInt();
			in.nextLine();
			switch(selNum) {
			case 1: elist(); break;
			case 2: ebuy(); break;
			case 3: eblist(); break;
			case 4: eread(); break;
			case 5: flag=false; break;
			}
		}
	}
	
	private void elist() {
		ArrayList<EbookDTO> ebooklist = edao.selectAll();
		for(EbookDTO e : ebooklist) {
			System.out.println(e.toString());
		}
	}
	
	private void ebuy() {
		if (lg.getId() == null) {
			System.out.println("로그인 후 구매할 수 있습니다.");
			return;
		}
		System.out.println("현재 구매하시는 분 은 " + lg.getId() + "입니다.");
		ArrayList<EbookDTO> ebooklist = edao.selectAll();
		for(EbookDTO e : ebooklist) {
			System.out.println(e.toString());
		}
		System.out.println("구매할 이북 코드를 입력하세요.");
		Scanner in = new Scanner(System.in);
		String ecode = in.nextLine();
		OrderedDTO odto = new OrderedDTO();
		odto.setCid(lg.getId());
		odto.setEcode(ecode);
		edao.insert(odto);
		EreadDTO erdto = new EreadDTO();
		erdto.setCeid(lg.getId());
		erdto.setEcode(ecode);
		edao.cinsert(erdto);
		
	}
	
	private void eblist() {
		if(lg.getId() == null) {
			return;
		}
		String ceid = lg.getId();
		System.out.println("현재 조회하시는 분은 " + ceid + "입니다.");
		ArrayList<EreadDTO> eblist = edao.cselectAll(ceid);
		for(EreadDTO er : eblist) {
			System.out.println(er.toString());
		}
	}
	
	private void eread() {
		if (lg.getId() == null) {
			System.out.println("로그인 후 조회할 수 있습니다.");
			return;
		}
		System.out.println("읽을 책을 입력하세요.");
		Scanner in = new Scanner(System.in);
		String ecode = in.nextLine();
		boolean reading = true;
		EreadDTO erdto = new EreadDTO();
		while(reading) {
			System.out.println("1. 페이지 넘기기 2.이전 페이지로 가기 3. 원하는 페이지 지정");
			int selNum = in.nextInt();
			in.nextLine();
			switch(selNum) {
			case 1: 
			erdto.setCeid(lg.getId());
			erdto.setEcode(ecode);
			edao.readplus(erdto); break;
			case 2: 
			erdto.setCeid(lg.getId());
			erdto.setEcode(ecode);
			edao.readminus(erdto); break;
			case 3: 
			System.out.println("수정할 현재 페이지를 입력하세요.");
			int newEfpage = in.nextInt();
			in.nextLine();
			erdto.setCeid(lg.getId());
			erdto.setEcode(ecode);
			erdto.setEfpage(newEfpage);
			edao.readebook(erdto); break;
			}
		}
	}
}
