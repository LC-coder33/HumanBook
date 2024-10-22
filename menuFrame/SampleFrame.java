package menuFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class SampleFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable orderedlist;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField searchText;


	public SampleFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JLabel lblNewLabel = new JLabel("휴먼문고");
		north_c.add(lblNewLabel);
		lblNewLabel.setFont(new Font("한컴 고딕", Font.PLAIN, 30));
		
		JPanel north_w = new JPanel();
		north_w.setBackground(Color.white);
		northP.add(north_w, BorderLayout.WEST);
		
		JButton backbtn = new JButton("뒤로가기");
		backbtn.setBackground(Color.white);
		north_w.add(backbtn);
		
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
		
		
		DefaultTableModel porderedlist = new DefaultTableModel(
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
		
		JLabel pname = new JLabel("도서 코드: ");
		center_pcc.add(pname);
		
		JTextField textpname = new JTextField();
		center_pcc.add(textpname);
		textpname.setColumns(10);
		
		JLabel pquantity = new JLabel("개수: ");
		center_pcc.add(pquantity);
		
		JTextField textpquantity = new JTextField();
		center_pcc.add(textpquantity);
		textpquantity.setColumns(5);
		
		JButton btnNewButton_2 = new JButton("구매");
		center_pcc.add(btnNewButton_2);
		
		JPanel center_pcs = new JPanel();
		center_pcs.setBackground(Color.white);
		center_c.add(center_pcs, BorderLayout.SOUTH);
		
		JButton mypbtn = new JButton("내가 구매한 책 보기");
		center_pcs.add(mypbtn);
		
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
		
		JButton searchbtn = new JButton("검색");
		center_north.add(searchbtn);
		
		JPanel southP = new JPanel();
		contentPane.add(southP, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_1 = new JLabel("구매 성공하셨습니다.");
		southP.add(lblNewLabel_1);
	}

}
