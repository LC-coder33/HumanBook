package menuFrame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import customerDTO.CustomerDTO;
import loginDAO.LgDbdao;

public class FindFrame extends JFrame implements ActionListener {
	LgDbdao ldbao = null;
	CustomerDTO cdto = null;
	
	private JPanel contentPane;
	
	JButton findid = new JButton("아이디 찾기");
	JButton findpw = new JButton("비밀번호 리셋");
	
	JLabel cname = new JLabel("이름: ");
	JTextField nametext;
	
	JLabel id = new JLabel("아이디: ");
	JTextField idtext;
	
	String info[] = {"초등학교 6학년 선생님의 이름은?", "가장 좋았던 여행지는?","가장 존경하는 인물은?","최근 가장 인상깊었던 책은?"};
	JComboBox questiontext = new JComboBox(info);
	
	JLabel answer = new JLabel("답변: ");
	JTextField answertext;
	
	JTextField pwtext;
	
	
	private String pwpattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$";
	
	public FindFrame(LgDbdao ldbao, CustomerDTO cdto) {
		this.ldbao = ldbao;
		this.cdto = cdto;
		
		setBounds(100,100,480,360);
		contentPane = new JPanel();
		contentPane.setBackground(Color.white);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		findid.setBounds(111, 138, 120, 23);
		contentPane.add(findid);
		
		findpw.setBounds(231, 138, 120, 23);
		contentPane.add(findpw);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
