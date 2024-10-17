package managerMenu;

import java.util.ArrayList;
import java.util.Scanner;

import eDTO.EbookDTO;
import ebookDAO.EbookDAO;
import managerDAO.ManagerDAO;
import paperDAO.PaperDAO;
import paperDTO.PaperDTO;

public class ManagerMenu {
	private ManagerDAO mdao = ManagerDAO.getInstance();
	private PaperDAO pdao = PaperDAO.getInstance();
	private EbookDAO edao = EbookDAO.getInstance();
	
	public ManagerMenu() {
		
	}

	public void menu() {
		Scanner in = new Scanner(System.in);
		boolean flag = true;
		while(flag) {
			System.out.println("1. 종이책 입고 2.종이책 수정 3.종이책 삭제 "
					+ "4.e-book 입고 5.e-book 수정 6.ebook 삭제 7. 종료");
			int selNum = in.nextInt();
			in.nextLine();
			switch(selNum) {
			case 1: addpaper(); break;
			case 2: modpaper(); break;
			case 3: delpaper(); break;
			case 4: addebook(); break;
			case 5: modebook(); break;
			case 6: delebook(); break;
			case 7: flag = false; break;
			}
		}
	}
	
	private void addpaper() {
		System.out.println("입고 할 도서 이름을 입력하세요.");
		Scanner in = new Scanner(System.in);
		String pname = in.nextLine();
		System.out.println("작가 이름을 입력하세요.");
		String pauthor = in.nextLine();
		System.out.println("가격을 입력하세요.");
		int pprice = in.nextInt();
		in.nextLine();
		System.out.println("재고를 입력하세요.");
		int pquantity = in.nextInt();
		in.nextLine();
		PaperDTO pdto = new PaperDTO();
		pdto.setPname(pname);
		pdto.setPauthor(pauthor);
		pdto.setPprice(pprice);
		pdto.setPquantity(pquantity);
		mdao.pinsert(pdto);
	}
	
	private void modpaper() {
		ArrayList<PaperDTO> paperlist = pdao.selectAll();
		for(PaperDTO p : paperlist) {
			System.out.println(p.toString());
		}
		System.out.println("수정할 서적의 코드를 입력해주세요.");
		Scanner in = new Scanner(System.in);
		String selcode = in.nextLine();
		PaperDTO pmoddto = mdao.selectPaper(selcode);
		System.out.println("선택한 서적");
		System.out.println(pmoddto.toString());
		System.out.println("제목을 수정하세요.");
		String pname = in.nextLine();
		System.out.println("작가 이름을 수정하세요.");
		String pauthor = in.nextLine();
		System.out.println("가격을 수정하세요.");
		int pprice = in.nextInt();
		in.nextLine();
		System.out.println("재고현황을 수정하세요.");
		int pquantity = in.nextInt();
		in.nextLine();
		pmoddto.setPname(pname);
		pmoddto.setPauthor(pauthor);
		pmoddto.setPprice(pprice);
		pmoddto.setPquantity(pquantity);
		mdao.pmod(pmoddto);
	}
	
	private void delpaper() {
		ArrayList<PaperDTO> paperlist = pdao.selectAll();
		for(PaperDTO p : paperlist) {
			System.out.println(p.toString());
		}
		System.out.println("삭제할 서적의 코드를 입력해주세요.");
		Scanner in = new Scanner(System.in);
		String delcode = in.nextLine();
		PaperDTO pdeldto = mdao.selectPaper(delcode);
		System.out.println("선택할 서적을 삭제하였습니다.");
		mdao.pdel(delcode);
	}
	
	private void addebook() {
		System.out.println("등록 할 이북 이름을 입력하세요.");
		Scanner in = new Scanner(System.in);
		String ename = in.nextLine();
		System.out.println("작가 이름을 입력하세요.");
		String eauthor = in.nextLine();
		System.out.println("가격을 입력하세요.");
		int eprice = in.nextInt();
		in.nextLine();
		System.out.println("총 페이지 수를 입력하세요.");
		int epage = in.nextInt();
		in.nextLine();
		EbookDTO edto = new EbookDTO();
		edto.setEname(ename);
		edto.setEauthor(eauthor);
		edto.setEprice(eprice);
		edto.setEpage(epage);
		mdao.einsert(edto);
	}
	
	private void modebook() {
		ArrayList<EbookDTO> ebooklist = edao.selectAll();
		for(EbookDTO e : ebooklist) {
			System.out.println(e.toString());
		}
		System.out.println("수정할 서적의 코드를 입력해주세요.");
		Scanner in = new Scanner(System.in);
		String selecode = in.nextLine();
		EbookDTO emoddto = mdao.selectEbook(selecode);
		System.out.println("선택한 서적");
		System.out.println(emoddto.toString());
		System.out.println("제목을 수정하세요.");
		String ename = in.nextLine();
		System.out.println("작가 이름을 수정하세요.");
		String eauthor = in.nextLine();
		System.out.println("가격을 수정하세요.");
		int eprice = in.nextInt();
		in.nextLine();
		System.out.println("페이지 수를 수정하세요.");
		int epage = in.nextInt();
		in.nextLine();
		emoddto.setEname(ename);
		emoddto.setEauthor(eauthor);
		emoddto.setEprice(eprice);
		emoddto.setEpage(epage);
		mdao.emod(emoddto);
	}
	
	private void delebook() {
		ArrayList<EbookDTO> ebooklist = edao.selectAll();
		for(EbookDTO e : ebooklist) {
			System.out.println(e.toString());
		}
		System.out.println("삭제할 이북의 코드를 입력해주세요.");
		Scanner in = new Scanner(System.in);
		String delecode = in.nextLine();
		EbookDTO edeldto = mdao.selectEbook(delecode);
		System.out.println("선택할 이북을 삭제하였습니다.");
		mdao.edel(delecode);
	}
}
