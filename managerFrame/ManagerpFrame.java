package managerFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import customerDTO.CustomerDTO;
import ebookDAO.Ebdao;
import login.Logginginterface;
import loginDAO.LgDbdao;
import managerDAO.midao;
import menuFrame.MenuFrame;
import orderedDTO.OrderedDTO;
import paperDAO.Pdbao;
import paperDTO.PaperDTO;

public class ManagerpFrame extends JFrame implements ActionListener, ItemListener {
	MenuFrame mframe = null;
	LgDbdao ldbao = null;
	Logginginterface lg = null;
	OrderedDTO odto = null;
	CustomerDTO cdto = null;
	Pdbao pdao = null;
	Ebdao edao = null;
	midao mdao = null;
	ManagerModFrame mmframe = null;
	
	// 맨 위 페이지
	private JPanel contentPane;
	JPanel southP = new JPanel();

	JTextField textpname = new JTextField();
	JTextField textpauthor = new JTextField();
	JTextField textpprice = new JTextField();
	JTextField textpquantity = new JTextField();
	
	JButton storebtn = new JButton("입고");
	JButton modbtn = new JButton("수정");
	JButton delbtn = new JButton("삭제");
	
	private DefaultTableModel porderedlist;
	JTable pordered = null;
	
	//다른 기능들
	private JButton backbtn = new JButton("뒤로가기");

	
	//private JButton einsert = new JButton("이북 입고");
	//private JButton emod = new JButton("이북 수정");
	//private JButton edel = new JButton("이북 삭제");
	
	public ManagerpFrame(LgDbdao ldbao, Logginginterface lg, Pdbao pb, Ebdao eb, midao mdao, MenuFrame mframe, ManagerModFrame mmframe) {
		this.mdao=mdao;
		setBounds(100,100,1080,720);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel northP = new JPanel();
		contentPane.add(northP, BorderLayout.NORTH);
		northP.setBackground(Color.WHITE);
		northP.setLayout(new BorderLayout(0, 0));
		
		JPanel north_c = new JPanel();
		north_c.setBackground(Color.WHITE);
		northP.add(north_c);
		
		JLabel managerlabel = new JLabel("관리자 계정");
		north_c.add(managerlabel);
		managerlabel.setFont(new Font("한컴 고딕", Font.PLAIN, 30));
		
		JPanel north_w = new JPanel();
		north_w.setBackground(Color.white);
		northP.add(north_w, BorderLayout.WEST);
		
		backbtn.setBackground(Color.white);
		north_w.add(backbtn);
		
		JPanel north_e = new JPanel();
		north_e.setBackground(Color.white);
		northP.add(north_e, BorderLayout.EAST);
		
		JLabel dummy = new JLabel("도서 관리 용 ");
		north_e.add(dummy);
		
		JPanel center_p = new JPanel();
		contentPane.add(center_p, BorderLayout.CENTER);
		center_p.setLayout(new BorderLayout(0, 0));
		
		JPanel center_c = new JPanel();
		center_p.add(center_c, BorderLayout.CENTER);
		center_c.setBackground(Color.WHITE);
		
		
		setPorderedlist(new DefaultTableModel(
	            new Object[][] {},
	            new String[] { "도서코드", "도서 제목", "작가", "가격", "재고" }
	        ));
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
					"system","11111111");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT pcode, pname, pauthor, pprice, pquantity FROM paperbook ORDER BY pcode ASC");
			
			while (rs.next()) {
                String pcode = rs.getString("pcode");
                String pname = rs.getString("pname");
                String pauthor = rs.getString("pauthor");
                double pprice = rs.getDouble("pprice");
                int pquantity = rs.getInt("pquantity");

                // 모델에 데이터 추가
                getPorderedlist().addRow(new Object[] { pcode, pname, pauthor, pprice, pquantity });
            }
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		center_c.setLayout(new BorderLayout(0, 0));
		
		JPanel center_pcn = new JPanel();
		center_c.add(center_pcn, BorderLayout.NORTH);
		center_pcn.setBackground(Color.white);
		
		pordered = new JTable(getPorderedlist());
		pordered.setBorder(new LineBorder(new Color(0, 0, 0)));
		pordered.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		

		JScrollPane scrollPane = new JScrollPane(pordered);
		center_pcn.add(scrollPane);
		scrollPane.setPreferredSize(new Dimension(800, 400));
		scrollPane.setBackground(Color.white);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);
		
		JPanel center_pcc = new JPanel();
		center_pcc.setBackground(Color.white);
		center_c.add(center_pcc, BorderLayout.CENTER);
		center_pcc.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel pname = new JLabel("도서 제목: ");
		center_pcc.add(pname);
		
		center_pcc.add(textpname);
		textpname.setColumns(10);
		
		JLabel pauthor = new JLabel("작가: ");
		center_pcc.add(pauthor);
		
		center_pcc.add(textpauthor);
		textpauthor.setColumns(10);
		
		JLabel pprice = new JLabel("가격: ");
		center_pcc.add(pprice);
		
		center_pcc.add(textpprice);
		textpprice.setColumns(10);
		
		JLabel pquantity = new JLabel("재고: ");
		center_pcc.add(pquantity);
		
		center_pcc.add(textpquantity);
		textpquantity.setColumns(10);
		
		JPanel center_south = new JPanel();
		center_south.setBackground(Color.WHITE);
		center_p.add(center_south, BorderLayout.SOUTH);
		center_south.setLayout(new BorderLayout(0, 0));
		
		JPanel cs_north = new JPanel();
		cs_north.setBackground(Color.white);
		center_south.add(cs_north, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		center_south.add(panel, BorderLayout.CENTER);
		panel.setBackground(Color.white);
		
		JPanel panela = new JPanel();
		center_south.add(panela, BorderLayout.SOUTH);
		panela.setBackground(Color.white);
		
		JPanel center_north = new JPanel();
		center_north.setBackground(Color.WHITE);
		center_p.add(center_north, BorderLayout.NORTH);
		
		JLabel lblNewLabel_2 = new JLabel("도서 목록");
		lblNewLabel_2.setFont(new Font("한국외대체 B", Font.PLAIN, 24));
		center_north.add(lblNewLabel_2);
	
		contentPane.add(southP, BorderLayout.SOUTH);
		
		cs_north.add(storebtn);
		cs_north.add(modbtn);
		cs_north.add(delbtn);
		
		storebtn.addActionListener(this);
		delbtn.addActionListener(this);
		modbtn.addActionListener(this);
		backbtn.addActionListener(this);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}





	@Override
	public void itemStateChanged(ItemEvent e) {
		
	}





	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == storebtn) {
			String pname = textpname.getText();
			String pauthor = textpauthor.getText();
			int pprice = Integer.parseInt(textpprice.getText());
			int pquantity = Integer.parseInt(textpquantity.getText());
			PaperDTO pdto = new PaperDTO();
			pdto.setPname(pname);
			pdto.setPauthor(pauthor);
			pdto.setPprice(pprice);
			pdto.setPquantity(pquantity);			
			mdao.pinsert(pdto);
			getPorderedlist().fireTableDataChanged();
			
			String pcode = mdao.pcodereturn(pdto);
			getPorderedlist().addRow(new Object[] {pcode, pname, pauthor, pprice, pquantity});
			  
			textpname.setText("");
		    textpauthor.setText("");
		    textpprice.setText("");
		    textpquantity.setText("");
			
			contentPane.revalidate();
			contentPane.repaint();
			
			JLabel footerp = new JLabel("입고 완료");
			southP.add(footerp);
			Timer timer = new Timer(3000, sec3 -> {
		        contentPane.remove(footerp);
		        contentPane.revalidate();
		        contentPane.repaint();
		        });
		        timer.setRepeats(false);
		        timer.start();
		}
		if(e.getSource() == delbtn) {
			int selrow = pordered.getSelectedRow();
			String delcode = String.valueOf(getPorderedlist().getValueAt(selrow, 0));
			mdao.pdel(delcode);
			getPorderedlist().removeRow(selrow);
			contentPane.revalidate();
			contentPane.repaint();
			
			JLabel footerp = new JLabel("삭제 완료");
			southP.add(footerp);
			Timer timer = new Timer(3000, sec3 -> {
		        contentPane.remove(footerp);
		        contentPane.revalidate();
		        contentPane.repaint();
		        });
		        timer.setRepeats(false);
		        timer.start();
		}
		if(e.getSource() == modbtn) {
			 int selrow = pordered.getSelectedRow();
		        if (selrow != -1) {
		            String pcode = String.valueOf(getPorderedlist().getValueAt(selrow, 0));
		            String pname = String.valueOf(getPorderedlist().getValueAt(selrow, 1));
		            String pauthor = String.valueOf(getPorderedlist().getValueAt(selrow, 2));
		            double pprice = (Double) getPorderedlist().getValueAt(selrow, 3);
		            int pquantity = (Integer) getPorderedlist().getValueAt(selrow, 4);
		            
		            mmframe = new ManagerModFrame(this, pdao, mdao, pcode, pname, pauthor, pprice, pquantity);
		            mmframe.setVisible(true);
		            
		        } else {
		            System.out.println("수정할 서적을 선택하지 않았습니다.");
		        }
		    }
		if(e.getSource() == backbtn) {
			this.dispose();
			setBounds(100,100,1080,720);
			mframe = new MenuFrame(ldbao, lg, pdao, edao, mdao, odto);
			mframe.setVisible(true);
			mframe.returned();
		}
	}





	public DefaultTableModel getPorderedlist() {
		return porderedlist;
	}





	public void setPorderedlist(DefaultTableModel porderedlist) {
		this.porderedlist = porderedlist;
	}

}