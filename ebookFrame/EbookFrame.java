package ebookFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import eDTO.EbookDTO;
import ebookDAO.Ebdao;
import login.Logginginterface;
import loginDAO.LgDbdao;
import managerDAO.midao;
import menuFrame.MenuFrame;
import orderedDTO.OrderedDTO;
import paperDAO.Pdbao;

public class EbookFrame extends JFrame implements ActionListener, MouseListener {

	LgDbdao ldbao = null;
	midao mdao = null;
	Pdbao pbao = null;
	Ebdao edao = null;
	OrderedDTO odto = null;
	MenuFrame mf = null;
	Logginginterface lg = null;
	EbookCus ec = null;
	String realid = null;
	
	int page = 0;
	private String ecode = null;
	private JPanel contentPane;
	private JTextField searchText;
	JTextField textename = new JTextField();
	//JTextField textcepage = new JTextField();
	
	DefaultTableModel eorderedlist = null;
	
	JButton backbtn = new JButton("뒤로가기");
	JButton searchbtn = new JButton("검색");
	JButton buybtn = new JButton("구매");			
	JButton myebtn = new JButton("내가 구매한 이북 보기");
	
	JLabel howmuch = new JLabel("도서 선택 후 구매하세요.");
	
	public EbookFrame(Ebdao edao, OrderedDTO odto, MenuFrame mf, Logginginterface lg, EbookCus ec) {
		this.edao = edao;
		this.odto = odto;
		this.mf = mf;
		this.lg = lg;
		this.ec = ec;
		realid = mf.getRealid();
		
		setBounds(100, 100, 1080, 720);
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
		
		JLabel title = new JLabel("휴먼문고");
		north_c.add(title);
		title.setFont(new Font("한컴 고딕", Font.PLAIN, 30));
		
		JPanel north_w = new JPanel();
		north_w.setBackground(Color.white);
		northP.add(north_w, BorderLayout.WEST);
		
		backbtn.setBackground(Color.white);
		north_w.add(backbtn);
		backbtn.addActionListener(this);
		
		JPanel north_e = new JPanel();
		north_e.setBackground(Color.white);
		northP.add(north_e, BorderLayout.EAST);
		
		JLabel dummy = new JLabel("이북 구매 용 ");
		north_e.add(dummy);
		
		JPanel center_p = new JPanel();
		contentPane.add(center_p, BorderLayout.CENTER);
		center_p.setLayout(new BorderLayout(0, 0));
		
		JPanel center_c = new JPanel();
		center_p.add(center_c, BorderLayout.CENTER);
		center_c.setBackground(Color.WHITE);
		
		
		eorderedlist = new DefaultTableModel(
	            new Object[][] {},
	            new String[] { "도서코드", "도서 제목", "작가", "가격", "총 페이지 수" }
	            );
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
					"system","11111111");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ecode, ename, eauthor, eprice, epage FROM ebook Order by ecode ASC");
			
			while (rs.next()) {
                String ecode = rs.getString("ecode");
                String ename = rs.getString("ename");
                String eauthor = rs.getString("eauthor");
                double eprice = rs.getDouble("eprice");
                int epage = rs.getInt("epage");

                // 모델에 데이터 추가
                eorderedlist.addRow(new Object[] { ecode, ename, eauthor, String.format("%,.0f원", eprice), epage});
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
		
		JTable eordered = new JTable(eorderedlist);
		eordered.setBorder(new LineBorder(new Color(0, 0, 0)));
		eordered.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		eordered.addMouseListener(this);
		

		JScrollPane scrollPane = new JScrollPane(eordered);
		center_pcn.add(scrollPane);
		scrollPane.setPreferredSize(new Dimension(800, 400));
		scrollPane.setBackground(Color.white);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);
		
		JPanel center_pcc = new JPanel();
		center_pcc.setBackground(Color.white);
		center_c.add(center_pcc, BorderLayout.CENTER);
		center_pcc.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 50));
		
		JLabel pname = new JLabel("도서 이름: ");
		center_pcc.add(pname);

		center_pcc.add(textename);
		textename.setColumns(10);
		
		//JLabel epage = new JLabel("페이지 수: ");
		//center_pcc.add(epage);
		
		//center_pcc.add(textcepage);
		//textcepage.setColumns(5);

		center_pcc.add(buybtn);
		buybtn.addActionListener(this);
		
		JPanel center_pcs = new JPanel();
		center_pcs.setBackground(Color.white);
		center_c.add(center_pcs, BorderLayout.SOUTH);

		center_pcs.add(myebtn);
		myebtn.addActionListener(this);
		
		JPanel center_south = new JPanel();
		center_south.setBackground(Color.WHITE);
		center_p.add(center_south, BorderLayout.SOUTH);
		center_south.setLayout(new BorderLayout(0, 0));
		
		JPanel cs_north = new JPanel();
		cs_north.setBackground(Color.white);
		center_south.add(cs_north, BorderLayout.NORTH);
		
		JPanel center_north = new JPanel();
		center_north.setBackground(Color.WHITE);
		center_p.add(center_north, BorderLayout.NORTH);
		
		JLabel searchlabel = new JLabel("제목 검색: ");
		center_north.add(searchlabel);
		
		searchText = new JTextField();
		center_north.add(searchText);
		searchText.setColumns(10);
		
		center_north.add(searchbtn);
		searchbtn.addActionListener(this);
		
		JPanel southP = new JPanel();
		contentPane.add(southP, BorderLayout.SOUTH);
		
		southP.add(howmuch);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public String getRealid() {
		return realid;
	}


	public void setRealid(String realid) {
		this.realid = realid;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == searchbtn) {
			String namee = null;
			namee = searchText.getText();
			edao.oneSelect(namee);
			ArrayList<EbookDTO> results = edao.oneSelect(namee);
			eorderedlist.setRowCount(0);
			for (EbookDTO ebook : results) {
			    eorderedlist.addRow(new Object[] { ebook.getEcode(), ebook.getEname(), ebook.getEauthor(), ebook.getEprice(), ebook.getEpage() });
			}
			contentPane.revalidate();
			contentPane.repaint();
		}
		
		if(e.getSource() == buybtn && lg.isloggedIn() == true) {
			String ecode2 = ecode;
			String ename = textename.getText();
				odto = new OrderedDTO();
				odto.setCid(mf.getRealid());
				odto.setEcode(ecode2);
				odto.setBname(ename);
				edao.insert(odto);
				howmuch.setText(odto.getBname() + "을 구매하셨습니다.");
			contentPane.revalidate();
			contentPane.repaint();
			
			Timer timer = new Timer(3000, sec3 -> {
				howmuch.setText("");
				contentPane.revalidate();
				contentPane.repaint();
            });
            timer.setRepeats(false);
            timer.start();
		}  else {
			howmuch.setText("로그인 후 이용해주세요.");
	    	contentPane.revalidate();
	    	contentPane.repaint();
	    	Timer timer = new Timer(3000, sec3 -> {
                howmuch.setText("");
                contentPane.revalidate();
                contentPane.repaint();
            });
            timer.setRepeats(false);
            timer.start();
		}
		
		if(e.getSource() == backbtn) {
			this.dispose();
			}
		
		if(e.getSource() == myebtn && realid != null) {
			ec = new EbookCus(edao, odto, this);
			ec.setVisible(true);
		} else if(e.getSource() == myebtn && realid == null) {
			JOptionPane.showMessageDialog(this, "로그인 후 이용하세요.", "경고", JOptionPane.WARNING_MESSAGE);
	    	return;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() instanceof JTable) {
			JTable table = (JTable) e.getSource();
			int selrow = table.getSelectedRow();
			ecode = String.valueOf(table.getValueAt(selrow, 0));
			String ename = String.valueOf(table.getValueAt(selrow, 1));
			textename.setText(ename);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
