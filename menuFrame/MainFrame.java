package menuFrame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.white);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("휴먼문고");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("한국외대체 M", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(439, 41, 175, 69);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("아이디: ");
		lblNewLabel.setBounds(12, 330, 57, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(69, 327, 136, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("비밀번호: ");
		lblNewLabel_1.setBounds(12, 355, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(69, 352, 136, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("로그인");
		btnNewButton.setBounds(108, 383, 97, 23);
		
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("회원가입");
		btnNewButton_1.setBounds(12, 383, 97, 23);
		contentPane.add(btnNewButton_1);
		
		ImageIcon originalpaper = new ImageIcon(getClass().getResource("/images/paperbook.png"));
		Image paperbook = originalpaper.getImage();

		Image scaledpaper = paperbook.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		ImageIcon scaledp = new ImageIcon(scaledpaper);

		JLabel paperLabel = new JLabel(scaledp);
		paperLabel.setBounds(260, 226, 200, 200);
		contentPane.add(paperLabel);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("한국외대체 M", Font.PLAIN, 20));
		textField_2.setText("도서");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setBounds(260, 436, 200, 38);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setBorder(BorderFactory.createEmptyBorder());
		
		ImageIcon originalebook = new ImageIcon(getClass().getResource("/images/ebook.png"));
		Image ebook = originalebook.getImage();

		Image scaledebook = ebook.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		ImageIcon scalede = new ImageIcon(scaledebook);

		JLabel ebookLabel = new JLabel(scalede);
		ebookLabel.setBounds(434, 226, 200, 200);
		contentPane.add(ebookLabel);
		
		textField_3 = new JTextField();
		textField_3.setText("전자책");
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("한국외대체 M", Font.PLAIN, 20));
		textField_3.setColumns(10);
		textField_3.setBorder(BorderFactory.createEmptyBorder());
		textField_3.setBounds(434, 436, 200, 38);
		contentPane.add(textField_3);
		
		ImageIcon originalusedbook = new ImageIcon(getClass().getResource("/images/usedbook.png"));
		Image usedbook = originalusedbook.getImage();

		Image scaledusedbook = usedbook.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		ImageIcon scaledu = new ImageIcon(scaledusedbook);

		JLabel usedbookLabel = new JLabel(scaledu);
		usedbookLabel.setBounds(634, 226, 200, 200);
		contentPane.add(usedbookLabel);
		
		textField_4 = new JTextField();
		textField_4.setText("중고서적");
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setFont(new Font("한국외대체 M", Font.PLAIN, 20));
		textField_4.setColumns(10);
		textField_4.setBorder(BorderFactory.createEmptyBorder());
		textField_4.setBounds(634, 436, 200, 38);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setBounds(492, 605, 103, 21);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JButton mloginbtn = new JButton("관리자 로그인");
		mloginbtn.setFont(new Font("한국외대체 L", Font.PLAIN, 12));
		mloginbtn.setBackground(Color.WHITE);
		mloginbtn.setContentAreaFilled(false);
		mloginbtn.setOpaque(true);
		mloginbtn.setBorder(new LineBorder(new Color(0, 0, 0)));
		mloginbtn.setBounds(492, 626, 103, 23);
		contentPane.add(mloginbtn);
		
		JButton mMenubtn = new JButton("관리자 메뉴");
		mMenubtn.setVisible(false);
		mMenubtn.setOpaque(true);
		mMenubtn.setFont(new Font("한국외대체 L", Font.PLAIN, 12));
		mMenubtn.setContentAreaFilled(false);
		mMenubtn.setBorder(new LineBorder(new Color(0, 0, 0)));
		mMenubtn.setBackground(Color.WHITE);
		mMenubtn.setBounds(492, 648, 103, 23);
		contentPane.add(mMenubtn);
		
		JLabel lblNewLabel_3 = new JLabel("잘못된 아이디 혹은 비밀번호입니다.");
		lblNewLabel_3.setFont(new Font("한국외대체 L", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(12, 416, 193, 23);
		contentPane.add(lblNewLabel_3);
		
		
	}
}
