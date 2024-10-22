package paperFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import orderedDTO.OrderedDTO;
import paperDAO.Pdbao;

public class PaperCus extends JFrame implements ActionListener {

	Pdbao pdao = null;
	OrderedDTO odto = null;
	DefaultTableModel mypage = null;
	private JTable mytable = null;
	String realid = null;
	
	JButton out = new JButton("나가기");
	
	private JPanel contentPane = new JPanel();
	
	public PaperCus(Pdbao pdao, OrderedDTO odto, PaperFrame2 pf) {
		this.pdao = pdao;
		this.odto = odto;
		realid = pf.getRealid();
		
		setBounds(100,100, 720, 480);
		contentPane.setBackground(Color.WHITE);
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		JPanel northP = new JPanel();
		contentPane.add(northP, BorderLayout.NORTH);
		northP.setBackground(Color.WHITE);
		northP.setLayout(new BorderLayout());
		JLabel m = new JLabel("도서 마이페이지");
		northP.add(m);
		
		JPanel centerP = new JPanel();
		contentPane.add(centerP, BorderLayout.CENTER);
		
		JPanel southP = new JPanel();
		contentPane.add(southP, BorderLayout.SOUTH);
		southP.add(out);
		out.addActionListener(this);
		
		mypage = new DefaultTableModel(
			new Object[][] {},
			new String[] {"주문 번호","아이디","도서 코드","도서 제목","주문 수량","총 가격"}
		);
		
		
		String cid = realid;
		ArrayList<OrderedDTO> orders = pdao.selectOne(cid);
		    for (OrderedDTO order : orders) {
		        mypage.addRow(new Object[]{
		            order.getTid(), 
		            order.getCid(), 
		            order.getPcode(), 
		            order.getBname(), 
		            order.getQuantity(), 
		            order.getFullprice()
		        });
		    }
		    mytable = new JTable(mypage);
		    JScrollPane scrollPane = new JScrollPane(mytable);
		    contentPane.add(scrollPane, BorderLayout.CENTER);

		    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == out) {
			this.dispose();
		}
	}
	
	
}
