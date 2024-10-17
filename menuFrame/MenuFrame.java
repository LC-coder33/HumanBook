package menuFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import loginDAO.LgDbdao;

public class MenuFrame extends JFrame implements ActionListener, ItemListener {
	private JPanel title_m = new JPanel();
	private JLabel m = new JLabel("휴먼문고");
	private JPanel center_m = new JPanel();
	private JPanel center_left = new JPanel();
	private JPanel center_center = new JPanel();
	private JPanel center_right = new JPanel();
	
	LgDbdao ldbao = null;
	
	JLabel lid = new JLabel("아이디: ");
	JLabel lpw = new JLabel("비밀번호: ");
	JPanel leftlog = new JPanel();
	JButton login = new JButton("로그인");
	JButton signup = new JButton("회원가입");
	JTextField textid = new JTextField();
	JTextField textpw = new JTextField();
	
	JPanel centerchoice = new JPanel();
	JLabel paperbook = new JLabel("종이책");
	JLabel ebook = new JLabel("e-book");
	JLabel usedbook = new JLabel("중고책");
	
	JPanel managerBot = new JPanel();
	JLabel manager = new JLabel("관리자 로그인");
	JLabel managerMenu = new JLabel("관리자 메뉴");
	JTextField managercode = new JTextField();
	
	public MenuFrame(LgDbdao ldbao) {
		this.ldbao = ldbao;
		
		this.setBounds(100,100,720,480);
		title_m.add(m);
		center_m.setBackground(Color.white);
		this.add(title_m,"North");
		this.add(center_m,"Center");
		center_left.setBackground(Color.black);
		center_center.setBackground(Color.cyan);
		center_right.setBackground(Color.red);
		
		center_m.setLayout(new GridLayout());
		center_m.add(center_left);
		center_m.add(center_center);
		center_m.add(center_right);
		
		// center_left 작업
		leftlog.setBackground(Color.gray);
		leftlog.setLayout(new GridLayout(2,2));
		leftlog.add(lid);
		leftlog.add(textid);
		leftlog.add(lpw);
		leftlog.add(textpw);
		center_left.setLayout(new BorderLayout());
		center_left.add(login,"Right");
		center_left.add(signup, "South");
		
		// login.addActionListener(null);
		// signup.addActionListener(null);
		// 액션을 넣고 이야기 해보자
		
		// center_center 작업
		centerchoice.setLayout(new GridLayout(2,2));
		centerchoice.add(paperbook, "Left");
		centerchoice.add(ebook, "Center");
		centerchoice.add(usedbook, "Right");
		center_center.add(centerchoice, "Center");
		// paperbook.addMouseListener(null);
		// ebook.addMouseListener(null);
		// usedbook.addMouseListener(null);
		
		managerBot.setLayout(new GridLayout(2,2));
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
