import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.DropMode;

public class MainProgram extends JFrame implements ActionListener{

	ArrayList<String> healthComment = new ArrayList<>();
	
	userData udt = new userData();
	Mysql sql = new Mysql();
	File dataFile = new File("healthComment.txt");
	
	private JPanel contentPane;
	JButton exit;
	JButton logOut;
	JButton userHealthButton;
	JButton nearbySearchButton;
	String id;
	String name;
	
	public MainProgram(String name, String id) {
		int count = 0;
		this.name = name;
		this.id = id;
		udt.userInformation(id);
		Random random = new Random();
		
		try {    // 오늘의 건강정보 추천사항에 들어갈 문장 갯수 읽어와서 랜덤으로 걸린 문장 출력하기 위함
			BufferedReader br = new BufferedReader(new FileReader(dataFile));
			String readData;
			
			while ((readData = br.readLine()) != null) {
				healthComment.add(readData);
				count++;
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String date = udt.userData.get(udt.userData.size()-1).date;    // 검사기록이 추가될 때 Table 가장 밑에서부터 추가되서 마지막 날짜 읽기
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 970, 556);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel westPanel = new JPanel();
		westPanel.setBackground(Color.WHITE);
		westPanel.setBounds(10, 10, 336, 499);
		contentPane.add(westPanel);
		westPanel.setLayout(null);

		JLabel userName = new JLabel(name);
		userName.setFont(new Font("굴림", Font.BOLD, 35));
		userName.setHorizontalAlignment(SwingConstants.CENTER);
		userName.setBounds(12, 28, 127, 65);
		westPanel.add(userName);

		JLabel testDate = new JLabel(date);
		testDate.setFont(new Font("굴림", Font.BOLD, 15));
		testDate.setBounds(151, 54, 96, 26);
		westPanel.add(testDate);
		
		JLabel nearTestTxt = new JLabel("최근 검사일");
		nearTestTxt.setFont(new Font("굴림", Font.BOLD, 12));
		nearTestTxt.setBounds(151, 32, 94, 26);
		westPanel.add(nearTestTxt);
		
		JLabel deco01 = new JLabel("─────────────────────────────────────────────");
		deco01.setBounds(12, 92, 312, 15);
		westPanel.add(deco01);
		
		JLabel deco02 = new JLabel("─────────────────────────────────────────────");
		deco02.setBounds(12, 412, 312, 15);
		westPanel.add(deco02);
		
		exit = new JButton("종료");
		exit.addActionListener(this);
		exit.setFont(new Font("굴림", Font.BOLD, 17));
		exit.setBounds(34, 434, 111, 55);
		westPanel.add(exit);
		
		logOut = new JButton("로그아웃");
		logOut.addActionListener(this);
		logOut.setFont(new Font("굴림", Font.BOLD, 17));
		logOut.setBounds(193, 434, 111, 55);
		westPanel.add(logOut);

		JPanel eastPanel = new JPanel();
		eastPanel.setBackground(Color.WHITE);
		eastPanel.setBounds(358, 10, 586, 499);
		contentPane.add(eastPanel);
		eastPanel.setLayout(null);

		nearbySearchButton = new JButton("주변 검사소 찾기");
		nearbySearchButton.addActionListener(this);
		nearbySearchButton.setFont(new Font("굴림", Font.BOLD, 21));
		nearbySearchButton.setBounds(299, 10, 275, 394);
		eastPanel.add(nearbySearchButton);

		userHealthButton = new JButton("내 건강정보 조회");
		userHealthButton.addActionListener(this);
		userHealthButton.setFont(new Font("굴림", Font.BOLD, 21));
		userHealthButton.setBounds(12, 10, 275, 394);
		eastPanel.add(userHealthButton);
		
		JTextArea healthMent = new JTextArea(healthComment.get(random.nextInt(count)));
		healthMent.setWrapStyleWord(true);
		healthMent.setForeground(new Color(0, 0, 0));
		healthMent.setEditable(false);
		healthMent.setFont(new Font("굴림", Font.BOLD, 15));
		healthMent.setLineWrap(true);
		healthMent.setBounds(144, 425, 430, 51);
		eastPanel.add(healthMent);
		
		JLabel healthMentLabel = new JLabel(" 건강정보:");
		healthMentLabel.setFont(new Font("굴림", Font.BOLD, 18));
		healthMentLabel.setBounds(41, 410, 93, 51);
		eastPanel.add(healthMentLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		LoginProgram login = new LoginProgram();
		MyHealthInformation myHealthInformation = new MyHealthInformation(name, id);
		
		if(e.getSource() == exit) {
			System.exit(0);
		}
		else if(e.getSource() == logOut) {
			udt.userData.clear();    // userData 배열에 있던 값 초기화
			login.setVisible(true);
			dispose();
		}
		else if(e.getSource() == userHealthButton) {
			myHealthInformation.setVisible(true);
			dispose();
		}
		
	}
}
