package menuFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import customerDTO.CustomerDTO;
import ebookDAO.Ebdao;
import ebookFrame.EbookFrame;
import login.Logginginterface;
import loginDAO.LgDbdao;
import managerDAO.midao;
import managerFrame.ManagerModFrame;
import managerFrame.ManagerpFrame;
import orderedDTO.OrderedDTO;
import paperDAO.Pdbao;
import paperFrame.PaperCus;
import paperFrame.PaperFrame2;

public class MenuFrame extends JFrame implements ActionListener, MouseListener {
	LgDbdao ldbao = null;
	Logginginterface lg = null;
	CustomerDTO cdto = null;
	Pdbao pdao = null;
	Ebdao edao = null;
	midao mdao = null;
	ManagerModFrame mmframe = null;
	OrderedDTO odto = null;
	PaperCus pc = null;
	
	private JPanel contentPane = new JPanel();
	private JLabel m = new JLabel("휴먼문고");
	private JTextField textpaper;
	private JTextField textebook;
	private JTextField textusedbook;
	private JTextField textmanager;
	
	String realid = null;
	
	public String getRealid() {
		return realid;
	}

	public void setRealid(String realid) {
		this.realid = realid;
	}

	JLabel lid = new JLabel("아이디: ");
	JLabel lpw = new JLabel("비밀번호: ");
	JButton login = new JButton("로그인");
	JButton signup = new JButton("회원가입");
	JTextField textid = new JTextField();
	JPasswordField textpw = new JPasswordField();
	JLabel signupid = new JLabel("아이디: ");
	JLabel signuppw = new JLabel("비밀번호: ");
    JLabel signupNameLabel = new JLabel("이름: ");
	JButton duplicate = new JButton("중복 확인");
	JButton signupcomplete = new JButton("회원가입완료");
	JTextField stextid = new JTextField();
	JPasswordField stextpw = new JPasswordField();
	JTextField stextName = new JTextField();
	JButton logout = new JButton("로그아웃");
	JButton backButton = new JButton("뒤로가기");
	JButton managerLogin = new JButton("관리자 로그인");
	JButton menumanager = new JButton("도서 메뉴");
	JButton menuemanager = new JButton("이북 메뉴");
	JButton notmanager = new JButton("매니저 로그아웃");
	JLabel ismanager = null;
	
	ImageIcon originalpaper = new ImageIcon(getClass().getResource("/images/paperbook.png"));
	Image paperbook = originalpaper.getImage();

	Image scaledpaper = paperbook.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
	ImageIcon scaledp = new ImageIcon(scaledpaper);

	JLabel paperLabel = new JLabel(scaledp);
	
	ImageIcon originalebook = new ImageIcon(getClass().getResource("/images/ebook.png"));
	Image ebook = originalebook.getImage();

	Image scaledebook = ebook.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
	ImageIcon scalede = new ImageIcon(scaledebook);

	JLabel ebookLabel = new JLabel(scalede);
	
	ImageIcon originalusedbook = new ImageIcon(getClass().getResource("/images/usedbook.png"));
	Image usedbook = originalusedbook.getImage();

	Image scaledusedbook = usedbook.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
	ImageIcon scaledu = new ImageIcon(scaledusedbook);

	JLabel usedbookLabel = new JLabel(scaledu);
	
	JPanel managerBot = new JPanel();
	JLabel manager = new JLabel("관리자 로그인");
	JLabel managerMenu = new JLabel("관리자 메뉴");
	JTextField managercode = new JTextField();
	
	public MenuFrame(LgDbdao ldbao, Logginginterface lg, Pdbao pb, Ebdao eb, midao md, OrderedDTO odto) {
		this.ldbao = ldbao;
		this.lg = lg;
		this.pdao = pb;
		this.edao = eb;
		this.mdao = md;
		this.odto = odto;
		setBounds(100,100,1080,720);
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setBackground(Color.white);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		m.setHorizontalAlignment(SwingConstants.CENTER);
		m.setFont(new Font("한국외대체 M", Font.PLAIN, 30));
		m.setBounds(439, 41, 175, 69);
		contentPane.add(m);
		
		lid.setBounds(12, 330, 57, 15);
		contentPane.add(lid);
		
		textid.setBounds(69, 327, 136, 21);
		contentPane.add(textid);
		textid.setColumns(10);
		
		lpw.setBounds(12, 355, 57, 15);
		contentPane.add(lpw);
		
		textpw.setBounds(69, 352, 136, 21);
		contentPane.add(textpw);
		textpw.setColumns(10);
		
		login.setBounds(108, 383, 97, 23);
		contentPane.add(login);
		login.addActionListener(this);
		
		signup.setBounds(12, 383, 97,23);
		contentPane.add(signup);
		signup.addActionListener(this);
		
		paperLabel.setBounds(260, 226, 200, 200);
		contentPane.add(paperLabel);
		paperLabel.addMouseListener(this);
		
		textpaper = new JTextField();
		textpaper.setEditable(false);
		textpaper.setBackground(Color.WHITE);
		textpaper.setFocusable(false);
		textpaper.setFont(new Font("한국외대체 M", Font.PLAIN, 20));
		textpaper.setText("도서");
		textpaper.setHorizontalAlignment(SwingConstants.CENTER);
		textpaper.setBounds(260, 436, 200, 38);
		textpaper.setColumns(10);
		textpaper.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(textpaper);
		
		ebookLabel.setBounds(434, 226, 200, 200);
		contentPane.add(ebookLabel);
		ebookLabel.addMouseListener(this);
		
		textebook = new JTextField();
		textebook.setEditable(false);
		textebook.setBackground(Color.WHITE);
		textebook.setFocusable(false);
		textebook.setText("전자책");
		textebook.setHorizontalAlignment(SwingConstants.CENTER);
		textebook.setFont(new Font("한국외대체 M", Font.PLAIN, 20));
		textebook.setColumns(10);
		textebook.setBorder(BorderFactory.createEmptyBorder());
		textebook.setBounds(434, 436, 200, 38);
		contentPane.add(textebook);
		
		usedbookLabel.setBounds(634, 226, 200, 200);
		contentPane.add(usedbookLabel);
		
		textusedbook = new JTextField();
		textusedbook.setEditable(false);
		textusedbook.setBackground(Color.WHITE);
		textusedbook.setFocusable(false);
		textusedbook.setText("중고서적");
		textusedbook.setHorizontalAlignment(SwingConstants.CENTER);
		textusedbook.setFont(new Font("한국외대체 M", Font.PLAIN, 20));
		textusedbook.setColumns(10);
		textusedbook.setBorder(BorderFactory.createEmptyBorder());
		textusedbook.setBounds(634, 436, 200, 38);
		contentPane.add(textusedbook);
		
		textmanager = new JTextField();
		textmanager.setBounds(492, 555, 103, 21);
		contentPane.add(textmanager);
		textmanager.setColumns(10);
		
		managerLogin.setContentAreaFilled(false);
		managerLogin.setOpaque(true);
		managerLogin.setBorder(new LineBorder(new Color(0, 0, 0)));
		managerLogin.setBackground(Color.WHITE);
		managerLogin.setFont(new Font("한국외대체 L", Font.PLAIN, 12));
		managerLogin.setBounds(492, 576, 103, 23);
		contentPane.add(managerLogin);
		managerLogin.addActionListener(this);
		
		menumanager.setContentAreaFilled(false);
		menumanager.setVisible(false);
		menumanager.setOpaque(true);
		menumanager.setBorder(new LineBorder(new Color(0,0,0)));
		menumanager.setBackground(Color.WHITE);
		menumanager.setFont(new Font("한국외대체 L", Font.PLAIN, 12));
		menumanager.setBounds(492, 576, 103, 23);
		contentPane.add(menumanager);
		menumanager.addActionListener(this);
		
		menuemanager.setContentAreaFilled(false);
		menuemanager.setVisible(false);
		menuemanager.setOpaque(true);
		menuemanager.setBorder(new LineBorder(new Color(0,0,0)));
		menuemanager.setBackground(Color.WHITE);
		menuemanager.setFont(new Font("한국외대체 L", Font.PLAIN, 12));
		menuemanager.setBounds(492, 598, 103, 23);
		contentPane.add(menuemanager);
		
		notmanager.setContentAreaFilled(false);
		notmanager.setVisible(false);
		notmanager.setOpaque(true);
		notmanager.setBorder(new LineBorder(new Color(0,0,0)));
		notmanager.setBackground(Color.WHITE);
		notmanager.setFont(new Font("한국외대체 L", Font.PLAIN, 12));
		notmanager.setBounds(492, 620, 103, 23);
		contentPane.add(notmanager);
		notmanager.addActionListener(this);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void showsignup() {
		contentPane.removeAll();
    	signupid.setBounds(440, 200, 100, 25);
	    stextid.setBounds(520, 200, 150, 25);
	    duplicate.setBounds(680, 200, 120, 25); 
	    signuppw.setBounds(440, 240, 100, 25);
	    stextpw.setBounds(520, 240, 150, 25);
	    signupNameLabel.setBounds(440, 280, 100, 25); 
	    stextName.setBounds(520, 280, 150, 25);
	    backButton.setBounds(12, 12, 100, 25);
	    
	    contentPane.add(signupid);
	    contentPane.add(stextid);
	    contentPane.add(duplicate);
	    duplicate.addActionListener(this);
	    
	    contentPane.add(signupNameLabel);
	    contentPane.add(stextName);
	    
	    contentPane.add(signuppw);
	    contentPane.add(stextpw);
	    contentPane.add(backButton);
	    backButton.addActionListener(this);
         
        contentPane.revalidate();
        contentPane.repaint();
	}
	
	private void initializeMenuFrame() {
	    contentPane.removeAll();
	    
	    m.setHorizontalAlignment(SwingConstants.CENTER);
	    m.setFont(new Font("한국외대체 M", Font.PLAIN, 30));
	    m.setBounds(439, 41, 175, 69);
	    contentPane.add(m);
	    
	    contentPane.add(lid);
	    contentPane.add(textid);
	    contentPane.add(lpw);
	    contentPane.add(textpw);
	    contentPane.add(login);
	    contentPane.add(signup);
	    
	    contentPane.add(paperLabel);
	    contentPane.add(textpaper);
	    contentPane.add(ebookLabel);
	    contentPane.add(textebook);
	    contentPane.add(usedbookLabel);
	    contentPane.add(textusedbook);
	    
	    contentPane.add(textmanager);
	    contentPane.add(managerLogin);
	    contentPane.add(menumanager);

	    contentPane.revalidate();
	    contentPane.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    System.out.println("안녕하세요.");
	    if(e.getSource() == login) {
	        String id = textid.getText();
	        String pw = String.valueOf(textpw.getPassword());
	        if(ldbao.logIn(id, pw)) {
	        	lg.managerOut();
	            lg.setId(id);
	            lg.log();
	            System.out.println("환영합니다 " + lg.getId() + " 님");
	            realid = id;
	            
	            lid.setVisible(false);
	            lpw.setVisible(false);
	            textid.setVisible(false);
	            textpw.setVisible(false);
	            login.setVisible(false);
	            signup.setVisible(false);

	            JLabel welcomeLabel = new JLabel("환영합니다 " + lg.getId() + " 님");
	            welcomeLabel.setFont(new Font("한국외대체 L", Font.PLAIN, 18));
	            welcomeLabel.setBounds(12, 330, 500, 50);
	            contentPane.add(welcomeLabel);
	            
	            logout.setBounds(12, 380, 100, 25);
	            contentPane.add(logout);
	            logout.addActionListener(this);
	            contentPane.revalidate(); 
	            contentPane.repaint();
	        } else {
	            System.out.println("아이디 또는 비밀번호가 잘못되었습니다.");
	            JLabel wronglogin = new JLabel("아이디나 비밀번호가 잘못 되었습니다.");
	            wronglogin.setFont(new Font("한국외대체 L", Font.PLAIN, 12));
	            wronglogin.setBounds(12, 416, 193, 23);
	            contentPane.add(wronglogin);
	            contentPane.revalidate();
	            contentPane.repaint();
	            
	            Timer timer = new Timer(5000, sec5 -> {
	                contentPane.remove(wronglogin);
	                contentPane.revalidate();
	                contentPane.repaint();
	            });
	            timer.setRepeats(false);
	            timer.start();
	        }   
	    }
	    if(e.getSource() == signup) {
	    	contentPane.removeAll();
	    	setBounds(100,100,720,480);
	    	signupid.setBounds(200, 150, 100, 25);
		    stextid.setBounds(270, 150, 150, 25);
		    duplicate.setBounds(440, 150, 120, 25); 
		    signuppw.setBounds(200, 190, 100, 25);
		    stextpw.setBounds(270, 190, 150, 25);
		    signupNameLabel.setBounds(200, 230, 100, 25); 
		    stextName.setBounds(270, 230, 150, 25);
		    backButton.setBounds(12, 12, 100, 25);
		    
		    contentPane.add(signupid);
		    contentPane.add(stextid);
		    contentPane.add(duplicate);
		    duplicate.addActionListener(this);
		    
		    contentPane.add(signupNameLabel);
		    contentPane.add(stextName);
		    
		    contentPane.add(signuppw);
		    contentPane.add(stextpw);
		    contentPane.add(backButton);
		    backButton.addActionListener(this);
	         
	        contentPane.revalidate();
	        contentPane.repaint();
	    }
	    if(e.getSource() == duplicate) {
	        String sid = stextid.getText();
	        boolean isDuplicate = ldbao.duplicateId(sid);
	       
	        String message;
	        if(isDuplicate) {
	            message = "중복된 아이디입니다. 다시 입력하세요.";
	            contentPane.remove(signupcomplete);
	        } else {
	            message = "해당 아이디는 사용 가능합니다.";
	            signupcomplete.setBounds(270, 270, 150, 25);
	            contentPane.add(signupcomplete);
	            signupcomplete.addActionListener(this);
	        }
	        
	        JLabel messageLabel = new JLabel(message);
	        messageLabel.setFont(new Font("한국외대체 L", Font.PLAIN, 12));
	        messageLabel.setBounds(270, 300, 300, 25); // 크기 조절
	        contentPane.add(messageLabel);
	        
	        contentPane.revalidate();
	        contentPane.repaint();
	        
	        Timer timer = new Timer(3000, sec5 -> {
	        contentPane.remove(messageLabel);
	        contentPane.revalidate();
	        contentPane.repaint();
	        });
	        timer.setRepeats(false);
	        timer.start();
	    }
	    if(e.getSource() == signupcomplete) {
	    	String sid = stextid.getText();
	    	String sname = stextName.getText();
	    	String spw = String.valueOf(stextpw.getPassword());
			cdto = new CustomerDTO();
			cdto.setCid(sid);
			cdto.setCname(sname);
			cdto.setCpassword(spw);
			ldbao.signUp(cdto);
			
			initializeMenuFrame();
	    }
	    if(e.getSource() == backButton) {
	    	setBounds(100,100,1080,720);
	    	initializeMenuFrame();
	    }
	    if(e.getSource() == logout) {
	    	lg.logOut();
	    	initializeMenuFrame();
	    	lid.setVisible(true);
            lpw.setVisible(true);
            textid.setVisible(true);
            textpw.setVisible(true);
            login.setVisible(true);
            signup.setVisible(true);
            textid.setText("");
            textpw.setText("");
            realid = null;
	    }
	    if(e.getSource() == managerLogin) {
	    	String mcode = textmanager.getText();
	    	if(mcode.equals("asdf")) {
	    		lg.managerIn();
	    		System.out.println("관리자 계정으로 로그인하였습니다.");
	    		ismanager = new JLabel("관리자 계정으로 로그인하였습니다.");
	    		managerLogin.setVisible(false);
	    		textmanager.setVisible(false);
	    		menumanager.setVisible(true);
	    		menuemanager.setVisible(true);
	    		notmanager.setVisible(true);
	    		//ManagerpFrame mframe = new ManagerpFrame(ldbao, lg, pdao, edao, mdao);
	    		//mframe.setVisible(true);
	    		//this.dispose();
	    	} else {
	    		lg.managerOut();
	    		System.out.println("관리자 계정 코드가 틀렸습니다.");
	    		ismanager = new JLabel("관리자 코드가 틀렸습니다.");
	    	}
	    	ismanager.setFont(new Font("한국외대체 L", Font.PLAIN, 14));
	    	ismanager.setBounds(472, 525, 300, 21);
	    	contentPane.add(ismanager);
	    	contentPane.revalidate();
	    	contentPane.repaint();
	    	Timer timer = new Timer(3000, sec3 -> {
                contentPane.remove(ismanager);
                contentPane.revalidate();
                contentPane.repaint();
            });
            timer.setRepeats(false);
            timer.start();
	    }
	    if(e.getSource() == notmanager) {
	    	lg.managerOut();
	    	menumanager.setVisible(false);
	    	menuemanager.setVisible(false);
	    	notmanager.setVisible(false);
	    	managerLogin.setVisible(true);
	    	textmanager.setVisible(true);
	    	contentPane.revalidate();
	    	contentPane.repaint();
	    }
	    if(e.getSource() == menumanager) {
	    	ManagerpFrame mpframe = new ManagerpFrame(ldbao, lg, pdao, edao, mdao, this, mmframe);
	    	mpframe.setVisible(true);
	    	this.setVisible(false);
	    }
	}
	public void returned() {
		managerLogin.setVisible(false);
		textmanager.setVisible(false);
		menumanager.setVisible(true);
		menuemanager.setVisible(true);
		notmanager.setVisible(true);
	}
	
	public void returnedcustomer() {
		managerLogin.setVisible(true);
		textmanager.setVisible(true);
		menumanager.setVisible(false);
		menuemanager.setVisible(false);
		notmanager.setVisible(false);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == paperLabel) {
			PaperFrame2 pframe = new PaperFrame2(pdao, odto, this, lg, pc);
			pframe.setVisible(true);
			this.setVisible(false);

		}
		
		if(e.getSource() == ebookLabel && lg.isloggedIn() == true) {
			EbookFrame eframe = new EbookFrame(edao);
			eframe.setVisible(true);
			this.setVisible(false);
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
