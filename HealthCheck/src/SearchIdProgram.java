import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class SearchIdProgram extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField enterName;
	private JTextField enterPh;
	JButton ok = new JButton("확인");
	private JTextField textField;
	private JTextArea textArea;
	private JButton gotologinFrame;
	Mysql sql = new Mysql();
	
	public SearchIdProgram() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 393);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JLabel searchIdLabel = new JLabel("계정 찾기");
		searchIdLabel.setBounds(237, 10, 125, 49);
		searchIdLabel.setFont(new Font("굴림", Font.PLAIN, 26));
		contentPane.add(searchIdLabel);

		setContentPane(contentPane);
		
		JLabel nameLabel = new JLabel("이름");
		nameLabel.setFont(new Font("굴림", Font.BOLD, 12));
		nameLabel.setBounds(120, 91, 43, 32);
		contentPane.add(nameLabel);
		
		JLabel phLabel = new JLabel("전화번호");
		phLabel.setBounds(120, 139, 66, 32);
		contentPane.add(phLabel);
		
		enterName = new JTextField();
		enterName.setBounds(200, 98, 196, 21);
		contentPane.add(enterName);
		enterName.setColumns(10);
		
		enterPh = new JTextField();
		enterPh.setColumns(10);
		enterPh.setBounds(200, 143, 196, 21);
		contentPane.add(enterPh);
		
		ok.setFont(new Font("굴림", Font.PLAIN, 24));
		ok.setBounds(420, 98, 91, 69);
		ok.addActionListener(this);
		contentPane.add(ok);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("굴림", Font.PLAIN, 18));
		textArea.setBounds(120, 210, 396, 93);
		contentPane.add(textArea);
		
		gotologinFrame = new JButton("로그인 \r\n페이지로 이동");
		gotologinFrame.setBounds(222, 314, 161, 32);
		gotologinFrame.addActionListener(this);
		contentPane.add(gotologinFrame);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		LoginProgram lf = new LoginProgram();
		String name = enterName.getText();
		String phnum = enterPh.getText();
		String selectId;
		String selectPw;
		
		if(e.getSource() == ok) {
			try {
				sql.con = DriverManager.getConnection(sql.url, sql.user, sql.passwd);
				sql.stmt = sql.con.createStatement();
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				ResultSet result = sql.stmt.executeQuery("SELECT id, password FROM signData WHERE name = '" + name + "' AND phnum ="
						+ "'" + phnum +"'");
				
				while (result.next()) {
					selectId = result.getString("id");    // signdata 테이블에 있는 아이디 불러오기
					selectPw = result.getString("password");    // signdata 테이블에 있는 비밀번호 불러오기
					textArea.setText("아이디 : " + selectId + "\n\n비밀번호 : " + selectPw);    // textArea에 아이디 비밀번호 출력
				}
				
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == gotologinFrame) {
			dispose();    // SigninProgram 안보이게
			lf.setVisible(true);    // loginFrame 보이게
		}
		
	}
}
