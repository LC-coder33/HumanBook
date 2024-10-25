package managerFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import eDTO.EbookDTO;
import ebookDAO.Ebdao;
import login.Logginginterface;
import loginDAO.LgDbdao;
import managerDAO.midao;
import menuFrame.MenuFrame;
import orderedDTO.OrderedDTO;
import paperDAO.Pdbao;

public class ManagereFrame extends JFrame implements ActionListener {
	MenuFrame mframe = null;
	LgDbdao ldbao = null;
	OrderedDTO odto = null;
	CustomerDTO cdto = null;
	Pdbao pbao = null;
	Ebdao edao = null;
	midao mdao = null;
	ManagereModFrame mmeframe = null;
	
	private JPanel contentPane;
	JPanel southP = new JPanel();
	
	JTextField textename = new JTextField();
	JTextField texteauthor = new JTextField();
	JTextField texteprice = new JTextField();
	JTextField textepage = new JTextField();
	
	JButton storebtn = new JButton("입고");
	JButton modbtn = new JButton("수정");
	JButton delbtn = new JButton("삭제");
	
	private DefaultTableModel eorderedlist;
	JTable eordered = null;
	
	private JButton backbtn = new JButton("뒤로가기");
	
	public ManagereFrame(LgDbdao ldbao, Logginginterface lg, Pdbao pb, Ebdao eb, midao mdao, MenuFrame mframe, ManagereModFrame mmeframe) {
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
		
		JLabel dummy = new JLabel("이북 관리 용 ");
		north_e.add(dummy);
		
		JPanel center_p = new JPanel();
		contentPane.add(center_p, BorderLayout.CENTER);
		center_p.setLayout(new BorderLayout(0, 0));
		
		JPanel center_c = new JPanel();
		center_p.add(center_c, BorderLayout.CENTER);
		center_c.setBackground(Color.WHITE);
		
		setEorderedlist(new DefaultTableModel(
	            new Object[][] {},
	            new String[] { "이북코드", "이북 제목", "작가", "가격", "페이지 수" }
	        ));
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
					"system","11111111");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ecode, ename, eauthor, eprice, epage FROM ebook ORDER BY ecode ASC");
			
			while (rs.next()) {
                String ecode = rs.getString("ecode");
                String ename = rs.getString("ename");
                String eauthor = rs.getString("eauthor");
                double eprice = rs.getDouble("eprice");
                int epage = rs.getInt("epage");

                getEorderedlist().addRow(new Object[] { ecode, ename, eauthor, eprice, epage });
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
		
		eordered = new JTable(getEorderedlist());
		eordered.setBorder(new LineBorder(new Color(0, 0, 0)));
		eordered.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		

		JScrollPane scrollPane = new JScrollPane(eordered);
		center_pcn.add(scrollPane);
		scrollPane.setPreferredSize(new Dimension(800, 400));
		scrollPane.setBackground(Color.white);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);
		
		JPanel center_pcc = new JPanel();
		center_pcc.setBackground(Color.white);
		center_c.add(center_pcc, BorderLayout.CENTER);
		center_pcc.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel pname = new JLabel("이북 제목: ");
		center_pcc.add(pname);
		
		center_pcc.add(textename);
		textename.setColumns(10);
		
		JLabel pauthor = new JLabel("작가: ");
		center_pcc.add(pauthor);
		
		center_pcc.add(texteauthor);
		texteauthor.setColumns(10);
		
		JLabel pprice = new JLabel("가격: ");
		center_pcc.add(pprice);
		
		center_pcc.add(texteprice);
		texteprice.setColumns(10);
		
		JLabel epage = new JLabel("총 페이지 수: ");
		center_pcc.add(epage);
		
		center_pcc.add(textepage);
		textepage.setColumns(10);
		
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
		
		JLabel lblNewLabel_2 = new JLabel("이북 목록");
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
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == storebtn) {
			String ename = textename.getText();
			String eauthor = texteauthor.getText();
			int eprice = Integer.parseInt(texteprice.getText());
			int epage = Integer.parseInt(textepage.getText());
			EbookDTO edto = new EbookDTO();
			edto.setEname(ename);
			edto.setEauthor(eauthor);
			edto.setEprice(eprice);
			edto.setEpage(epage);			
			mdao.einsert(edto);
			getEorderedlist().fireTableDataChanged();
			
			String ecode = mdao.ecodereturn(edto);
			getEorderedlist().addRow(new Object[] {ecode, ename, eauthor, eprice, epage});
			  
			textename.setText("");
		    texteauthor.setText("");
		    texteprice.setText("");
		    textepage.setText("");
			
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
			int selrow = eordered.getSelectedRow();
			String delcode = String.valueOf(getEorderedlist().getValueAt(selrow, 0));
			mdao.edel(delcode);
			getEorderedlist().removeRow(selrow);
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
			 int selrow = eordered.getSelectedRow();
		        if (selrow != -1) {
		            String ecode = String.valueOf(getEorderedlist().getValueAt(selrow, 0));
		            String ename = String.valueOf(getEorderedlist().getValueAt(selrow, 1));
		            String eauthor = String.valueOf(getEorderedlist().getValueAt(selrow, 2));
		            double eprice = (Double) getEorderedlist().getValueAt(selrow, 3);
		            int epage = (Integer) getEorderedlist().getValueAt(selrow, 4);
		            
		            mmeframe = new ManagereModFrame(this, edao, mdao, ecode, ename, eauthor, eprice, epage);
		            mmeframe.setVisible(true);
		            
		        } else {
		            System.out.println("수정할 서적을 선택하지 않았습니다.");
		        }
		    }
		if(e.getSource() == backbtn) {
			this.dispose();
		}
	}
	
	
	public DefaultTableModel getEorderedlist() {
		return eorderedlist;
	}

	public void setEorderedlist(DefaultTableModel eorderedlist) {
		this.eorderedlist = eorderedlist;
	}
	
}
