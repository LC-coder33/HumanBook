package managerFrame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import customerDTO.CustomerDTO;
import eDTO.EbookDTO;
import ebookDAO.Ebdao;
import managerDAO.midao;
import menuFrame.MenuFrame;
import orderedDTO.OrderedDTO;
import paperDAO.Pdbao;

public class ManagereModFrame extends JFrame implements ActionListener {
	MenuFrame mframe = null;
	ManagereFrame meframe = null;
	OrderedDTO odto = null;
	CustomerDTO cdto = null;
	Pdbao pdao = null;
	midao mdao = null;
	
	private String ecode = null;
	
	private JPanel contentPane;
	private JTextField name;
	private JTextField author;
	private JTextField price;
	private JTextField page;
	
	JButton modbtn2 = new JButton("수정하기");
	
	public ManagereModFrame(ManagereFrame meframe, Ebdao edao, midao mdao, String ecode, String ename2, String eauthor2, double eprice2, int epage2) {
		this.meframe = meframe;
		this.ecode = ecode;
		this.mdao = mdao;
		setBounds(430, 300, 450, 300);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(100, 30));
		
		JPanel north_p = new JPanel();
		contentPane.add(north_p, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("수정할 정보를 입력하세요.");
		north_p.add(lblNewLabel);
		
		JPanel center_p = new JPanel();
		contentPane.add(center_p, BorderLayout.CENTER);
		center_p.setLayout(new GridLayout(4, 2, -100, 10));
		
		JLabel ename = new JLabel("책 제목: ");
		center_p.add(ename);
		
		name = new JTextField();
		center_p.add(name);
		
		JLabel eauthor = new JLabel("작가명: ");
		center_p.add(eauthor);
		
		author = new JTextField();
		center_p.add(author);
		
		JLabel eprice = new JLabel("가격: ");
		center_p.add(eprice);
		
		price = new JTextField();
		center_p.add(price);
		price.setColumns(10);
		
		JLabel epage = new JLabel("총 페이지 수: ");
		center_p.add(epage);
		
		page = new JTextField();
		center_p.add(page);
		page.setColumns(10);
		
		
		JPanel south_p = new JPanel();
		contentPane.add(south_p, BorderLayout.SOUTH);
		
		JPanel west_p = new JPanel();
		contentPane.add(west_p, BorderLayout.WEST);
		
		JPanel east_p = new JPanel();
		contentPane.add(east_p, BorderLayout.EAST);
		
		south_p.add(modbtn2);
		modbtn2.addActionListener(this);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == modbtn2) {
			String modname = name.getText();
			String modauthor = author.getText();
			int modprice = Integer.parseInt(price.getText());
			int modepage = Integer.parseInt(page.getText());
			EbookDTO edto = new EbookDTO();
			edto.setEcode(ecode);
			edto.setEname(modname);
			edto.setEauthor(modauthor);
			edto.setEprice((int)modprice);
			edto.setEpage(modepage);
			
			mdao.emod(edto);
			
			int selectedRow = meframe.eordered.getSelectedRow();
	        meframe.getEorderedlist().setValueAt(modname, selectedRow, 1);
	        meframe.getEorderedlist().setValueAt(modauthor, selectedRow, 2);
	        meframe.getEorderedlist().setValueAt(modprice, selectedRow, 3);
	        meframe.getEorderedlist().setValueAt(modepage, selectedRow, 4);
	        meframe.getEorderedlist().fireTableDataChanged();
			meframe.eordered.repaint();
			this.dispose();
			meframe.setVisible(true);
			
		}
		
	}
}
