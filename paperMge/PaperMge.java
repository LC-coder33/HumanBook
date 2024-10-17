package paperMge;

import java.util.ArrayList;
import java.util.Scanner;

import login.LoggingIn;
import orderedDTO.OrderedDTO;
import paperDAO.PaperDAO;
import paperDTO.PaperDTO;

public class PaperMge {
	private PaperDAO pdao = PaperDAO.getInstance();
	private LoggingIn lg;
	
	public PaperMge(LoggingIn lg) {
		this.lg = lg;
	}

	public void menu() {
		Scanner in = new Scanner(System.in);
		boolean flag = true;
		while(flag) {
			System.out.println("1.전체보기 2.구매 3.구매목록보기 4.종료");
			int selNum = in.nextInt();
			in.nextLine();
			switch(selNum) {
				case 1: plist(); break;
				case 2: pbuy(); break;
				case 3: pblist(); break;
				case 4: flag=false; break;
			}
		}
	}
	
	private void plist() {
		ArrayList<PaperDTO> paperlist = pdao.selectAll();
		for(PaperDTO p : paperlist) {
			System.out.println(p.toString());
		}
	}
	
	private void pbuy() {
		 if (lg.getId() == null) {
		        System.out.println("로그인 후 구매할 수 있습니다.");
		        return;
		    }
		System.out.println("현재 구매하시는 분은 " + lg.getId() + "입니다.");
		ArrayList<PaperDTO> paperlist = pdao.selectAll();
		for(PaperDTO p : paperlist) {
			System.out.println(p.toString());
		}
		System.out.println("구매할 서적 코드를 입력하세요.");
		Scanner in = new Scanner(System.in);
		String bcode = in.nextLine();
		System.out.println("구매할 수량을 입력하세요.");
		int quantity = in.nextInt();
		in.nextLine();
		OrderedDTO odto = new OrderedDTO();
		odto.setCid(lg.getId());
		odto.setPcode(bcode);
		odto.setQuantity(quantity);
		pdao.insert(odto);
	}
	
	private void pblist() {
		ArrayList<OrderedDTO> orderlist = pdao.selectOne(lg.getId());
		for(OrderedDTO o : orderlist) {
			System.out.println(o.toString());
		}
	}
}
