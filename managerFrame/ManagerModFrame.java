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
import managerDAO.midao;
import menuFrame.MenuFrame;
import orderedDTO.OrderedDTO;
import paperDAO.Pdbao;
import paperDTO.PaperDTO;

public class ManagerModFrame extends JFrame implements ActionListener {
	MenuFrame mframe = null;
	ManagerpFrame mpframe = null;
	OrderedDTO odto = null;
	CustomerDTO cdto = null;
	Pdbao pdao = null;
	midao mdao = null;
	
	private String pcode = null;
	
	private JPanel contentPane;
	private JTextField name;
	private JTextField author;
	private JTextField price;
	private JTextField quantity;
	
	JButton modbtn2 = new JButton("수정하기");
	
	public ManagerModFrame(ManagerpFrame mpframe, Pdbao pb, midao mdao, String pcode, String pname2, String pauthor2, double pprice2, int pquantity2) {
		this.mpframe = mpframe;
		this.pcode = pcode;
		this.mdao = mdao;
		setBounds(100, 100, 450, 300);
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
		
		JLabel pname = new JLabel("책 제목: ");
		center_p.add(pname);
		
		name = new JTextField();
		center_p.add(name);
		
		JLabel pauthor = new JLabel("작가명: ");
		center_p.add(pauthor);
		
		author = new JTextField();
		center_p.add(author);
		
		JLabel pprice = new JLabel("가격: ");
		center_p.add(pprice);
		
		price = new JTextField();
		center_p.add(price);
		price.setColumns(10);
		
		JLabel pquantity = new JLabel("재고: ");
		center_p.add(pquantity);
		
		quantity = new JTextField();
		center_p.add(quantity);
		quantity.setColumns(10);
		
		
		JPanel south_p = new JPanel();
		contentPane.add(south_p, BorderLayout.SOUTH);
		
		JPanel west_p = new JPanel();
		contentPane.add(west_p, BorderLayout.WEST);
		
		JPanel east_p = new JPanel();
		contentPane.add(east_p, BorderLayout.EAST);
		
		south_p.add(modbtn2);
		modbtn2.addActionListener(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == modbtn2) {
			String modname = name.getText();
			String modauthor = author.getText();
			double modprice = Double.parseDouble(price.getText());
			int modquantity = Integer.parseInt(quantity.getText());
			PaperDTO pdto = new PaperDTO();
			pdto.setPcode(pcode);
			pdto.setPname(modname);
			pdto.setPauthor(modauthor);
			pdto.setPprice((int)modprice);
			pdto.setPquantity(modquantity);
			
			mdao.pmod(pdto);
			
			int selectedRow = mpframe.pordered.getSelectedRow();
	        mpframe.getPorderedlist().setValueAt(modname, selectedRow, 1);
	        mpframe.getPorderedlist().setValueAt(modauthor, selectedRow, 2);
	        mpframe.getPorderedlist().setValueAt(modprice, selectedRow, 3);
	        mpframe.getPorderedlist().setValueAt(modquantity, selectedRow, 4);
	        mpframe.getPorderedlist().fireTableDataChanged();
			mpframe.pordered.repaint();
			this.dispose();
			mpframe.setVisible(true);
			
		}
	}
}
