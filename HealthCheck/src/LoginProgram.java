import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.StringTokenizer;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;

public class LoginProgram extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField idInput;
	private JTextField pwInput;
	JLabel loginFail = new JLabel("");
	JButton loginButton = new JButton("로그인");
	JButton signIn = new JButton("회원가입");
	
	Mysql sql = new Mysql();
	String name;
	
	public LoginProgram() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 393);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("건강관리 프로그램");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 26));
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setBounds(203, 30, 235, 49);
		contentPane.add(lblNewLabel);
		
		idInput = new JTextField();    // 아이디 입력
		idInput.setBackground(Color.LIGHT_GRAY);
		idInput.setBounds(203, 120, 217, 30);
		contentPane.add(idInput);
		idInput.setColumns(10);
		
		JLabel idText = new JLabel("아이디");
		idText.setBounds(123, 127, 50, 15);
		contentPane.add(idText);
		
		JLabel pwText = new JLabel("비밀번호");
		pwText.setBounds(123, 185, 50, 15);
		contentPane.add(pwText);
		
		pwInput = new JTextField();    // 비밀번호 입력
		pwInput.setColumns(10);
		pwInput.setBackground(Color.LIGHT_GRAY);
		pwInput.setBounds(203, 178, 217, 30);
		contentPane.add(pwInput);
		
		loginButton.setBounds(446, 178, 91, 30);    // 로그인 버튼
		loginButton.addActionListener(this);
		contentPane.add(loginButton);
		
		loginFail.setBounds(209, 288, 235, 23);    // 로그인 실패 또는 성공시 메세지
		contentPane.add(loginFail);
		
		signIn.setBounds(329, 235, 91, 23);    // 회원가입 버튼
		signIn.addActionListener(this);
		contentPane.add(signIn);
		
		JButton searchId = new JButton("계정찾기");
		searchId.setBounds(203, 235, 91, 23);
		contentPane.add(searchId);
		
	}
	
	int loginCheck() {	
		String uid = idInput.getText();    // 사용자가 입력한 아이디
		String upass = pwInput.getText();    // 사용자가 입력한 비밀번호
		String idpwCheck = "Fail";
		String selectPass = "SELECT password FROM signData WHERE id='" + uid + "'";    // 사용자가 입력한 아이디에 맞는 비밀번호가 있는지 확인
		String selectName = "SELECT name FROM signData WHERE id='" + uid + "'";    // 사용자가 입력한 아이디에 맞는 이름이 있는지 확인
		
		try {
			sql.con = DriverManager.getConnection(sql.url, sql.user, sql.passwd);    // MySQL 접속
			sql.stmt = sql.con.createStatement();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			ResultSet rs = sql.stmt.executeQuery(selectPass);    // 사용자가 입력한 아이디에 해당하는 비밀번호 찾기
			rs.next();
			String str = rs.getString("password");
			rs = sql.stmt.executeQuery(selectName);
			rs.next();
			name = rs.getString("name");
			
			if(upass.equals(str)) {    // 사용자가 입력한 비밀번호가 DB의 id에 해당하는 password 가 맞는지 확인
				try {
					sql.stmt.close();    // 닫기
					sql.con.close();
				}catch(Exception e) {
					System.out.println(e.toString());
				}
				return 1;
			}
		}
		catch(Exception e) {    // 사용자가 입력한 아이디가 DB에 없을때
			loginFail.setText("아이디 또는 비밀번호가 틀렸습니다.");
		}
		return 0;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		LoginProgram login = new LoginProgram();
		SignInProgram signProgram = new SignInProgram();
		
		if(e.getSource() == loginButton) {
			if(loginCheck() == 1) {    // 로그인 버튼을 눌렀을 때 로그인 성공시
				MainProgram mp = new MainProgram(name);
				mp.setVisible(true);    // MainFrame 실행
				dispose();    // loginProgram 화면 안보이게
			}
		}
		else if(e.getSource() == signIn) {    // 회원가입 버튼을 눌렀을때
			//signProgram.setVisible(true);    // signProgram 실행
			//dispose();    // loginFrame 화면 안보이게
		}
	}
}
