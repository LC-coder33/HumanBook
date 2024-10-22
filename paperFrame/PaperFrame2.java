package paperFrame;

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
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import ebookDAO.Ebdao;
import login.Logginginterface;
import loginDAO.LgDbdao;
import managerDAO.midao;
import menuFrame.MenuFrame;
import orderedDTO.OrderedDTO;
import paperDAO.Pdbao;
import paperDTO.PaperDTO;

public class PaperFrame2 extends JFrame implements ActionListener, MouseListener {
	LgDbdao ldbao = null;
	midao mdao = null;
	Pdbao pbao = null;
	Ebdao edao = null;
	OrderedDTO odto = null;
	MenuFrame mf = null;
	Logginginterface lg = null;
	PaperCus pc = null;
	String realid = null;

		private static final long serialVersionUID = 1L;
		private String pcode = null;
		private JPanel contentPane;
		private JTextField searchText;			
		JTextField textpname = new JTextField();
		JTextField textpquantity = new JTextField();
		JButton backbtn = new JButton("뒤로가기");
		
		DefaultTableModel porderedlist = null;
		

		JButton searchbtn = new JButton("검색");			
		JButton buybtn = new JButton("구매");			
		JButton mypbtn = new JButton("내가 구매한 책 보기");
		
		JLabel howmuch = new JLabel("도서 선택 후 개수를 적은 뒤 구매하세요.");
		
		public PaperFrame2(Pdbao pbao, OrderedDTO odto, MenuFrame mf, Logginginterface lg, PaperCus pc) {
			this.pbao = pbao;
			this.odto = odto;
			this.mf = mf;
			this.lg = lg;
			this.pc = pc;
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
			
			JLabel dummy = new JLabel("도서 구매 용 ");
			north_e.add(dummy);
			
			JPanel center_p = new JPanel();
			contentPane.add(center_p, BorderLayout.CENTER);
			center_p.setLayout(new BorderLayout(0, 0));
			
			JPanel center_c = new JPanel();
			center_p.add(center_c, BorderLayout.CENTER);
			center_c.setBackground(Color.WHITE);
			
			
			porderedlist = new DefaultTableModel(
		            new Object[][] {},
		            new String[] { "도서코드", "도서 제목", "작가", "가격", "재고" }
		        );
			
			try {
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
						"system","11111111");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT pcode, pname, pauthor, pprice, pquantity FROM paperbook");
				
				while (rs.next()) {
	                String pcode = rs.getString("pcode");
	                String pname = rs.getString("pname");
	                String pauthor = rs.getString("pauthor");
	                double pprice = rs.getDouble("pprice");
	                int pquantity = rs.getInt("pquantity");

	                // 모델에 데이터 추가
	                porderedlist.addRow(new Object[] { pcode, pname, pauthor, pprice, pquantity });
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
			
			JTable pordered = new JTable(porderedlist);
			pordered.setBorder(new LineBorder(new Color(0, 0, 0)));
			pordered.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			pordered.addMouseListener(this);
			

			JScrollPane scrollPane = new JScrollPane(pordered);
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

			center_pcc.add(textpname);
			textpname.setColumns(10);
			
			JLabel pquantity = new JLabel("개수: ");
			center_pcc.add(pquantity);
			
			center_pcc.add(textpquantity);
			textpquantity.setColumns(5);

			center_pcc.add(buybtn);
			buybtn.addActionListener(this);
			
			JPanel center_pcs = new JPanel();
			center_pcs.setBackground(Color.white);
			center_c.add(center_pcs, BorderLayout.SOUTH);

			center_pcs.add(mypbtn);
			mypbtn.addActionListener(this);
			
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
				String namep = null;
				namep = searchText.getText();
				pbao.oneSelect(namep);
				ArrayList<PaperDTO> results = pbao.oneSelect(namep);
				porderedlist.setRowCount(0);
				for (PaperDTO paper : results) {
				    porderedlist.addRow(new Object[] { paper.getPcode(), paper.getPname(), paper.getPauthor(), paper.getPprice(), paper.getPquantity() });
				}
				contentPane.revalidate();
				contentPane.repaint();
			}
			
			if(e.getSource() == buybtn && lg.isloggedIn() == true) {
				String pcode2 = pcode;
				String pname = textpname.getText();
				int quantity = Integer.parseInt(textpquantity.getText());
				odto = new OrderedDTO();
				odto.setCid(mf.getRealid());
				odto.setPcode(pcode2);
				odto.setBname(pname);
				odto.setQuantity(quantity);
				pbao.insert(odto);
				howmuch.setText("총 " + odto.getQuantity() + "권 구매하셨습니다.");
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
				setBounds(100,100,1080,720);
				mf = new MenuFrame(ldbao, lg, pbao, edao, mdao, odto);
				mf.setVisible(true);
				mf.returnedcustomer();
			}
			
			if(e.getSource() == mypbtn) {
				pc = new PaperCus(pbao, odto, this);
				pc.setVisible(true);
			}
		}


		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource() instanceof JTable) {
				JTable table = (JTable) e.getSource();
				int selrow = table.getSelectedRow();
				pcode = String.valueOf(table.getValueAt(selrow, 0));
				String pname = String.valueOf(table.getValueAt(selrow, 1));
				textpname.setText(pname);
			}
		}


		@Override
		public void mousePressed(MouseEvent e) {
			
		}


		@Override
		public void mouseReleased(MouseEvent e) {
			
		}


		@Override
		public void mouseEntered(MouseEvent e) {
			
		}


		@Override
		public void mouseExited(MouseEvent e) {
			
		}

	}
