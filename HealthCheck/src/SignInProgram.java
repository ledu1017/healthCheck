import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class SignInProgram extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField newIdInput;
	private JTextField newPwInput;
	JButton ok = new JButton("확인");
	JButton gotologinFrame = new JButton("로그인 \r\n페이지로 이동");
	private JLabel signInFail;

	Mysql sql = new Mysql();
	private JTextField newNameInput;
	private JTextField newPhNumInput;

	public SignInProgram() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 393);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel newId = new JLabel("아이디");
		newId.setBackground(Color.WHITE);
		newId.setBounds(123, 81, 50, 15);
		contentPane.add(newId);

		JLabel newPasswd = new JLabel("비밀번호");
		newPasswd.setBounds(123, 128, 50, 15);
		contentPane.add(newPasswd);

		newIdInput = new JTextField();    // 사용자가 입력한 새로운 아이디
		newIdInput.setBackground(Color.WHITE);
		newIdInput.setBounds(203, 74, 217, 30);
		contentPane.add(newIdInput);
		newIdInput.setColumns(10);

		newPwInput = new JTextField();    // 사용자가 입력한 새로운 비밀번호
		newPwInput.setBackground(Color.WHITE);
		newPwInput.setBounds(203, 121, 217, 30);
		contentPane.add(newPwInput);
		newPwInput.setColumns(10);

		JLabel signInLabel = new JLabel("회원가입");
		signInLabel.setFont(new Font("굴림", Font.PLAIN, 26));
		signInLabel.setBounds(255, 10, 106, 49);
		contentPane.add(signInLabel);

		ok.setFont(new Font("굴림", Font.PLAIN, 24));    // ok 버튼
		ok.setBounds(458, 190, 91, 59);
		contentPane.add(ok);

		signInFail = new JLabel("");    // 로그인 실패 또는 성공시 멘트
		signInFail.setHorizontalAlignment(SwingConstants.CENTER);
		signInFail.setBounds(193, 259, 253, 23);
		contentPane.add(signInFail);
		
		gotologinFrame.setBounds(229, 287, 161, 59);
		gotologinFrame.addActionListener(this);
		contentPane.add(gotologinFrame);

		JLabel signinSuccess = new JLabel("");
		signinSuccess.setHorizontalAlignment(SwingConstants.CENTER);
		signinSuccess.setBounds(220, 267, 170, 15);
		contentPane.add(signinSuccess);
		
		JLabel newName = new JLabel("이름");
		newName.setBounds(123, 179, 50, 15);
		contentPane.add(newName);
		
		JLabel newPhNum = new JLabel("전화번호");
		newPhNum.setBounds(123, 228, 50, 15);
		contentPane.add(newPhNum);
		
		newNameInput = new JTextField();
		newNameInput.setColumns(10);
		newNameInput.setBackground(Color.WHITE);
		newNameInput.setBounds(203, 172, 217, 30);
		contentPane.add(newNameInput);
		
		newPhNumInput = new JTextField();
		newPhNumInput.setColumns(10);
		newPhNumInput.setBackground(Color.WHITE);
		newPhNumInput.setBounds(203, 219, 217, 30);
		contentPane.add(newPhNumInput);
		ok.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		LoginProgram lf = new LoginProgram();
		String id = newIdInput.getText();    // 사용자가 입력한 아이디
		String pass = newPwInput.getText();    // 사용자가 입력한 비밀번호
		String name = newNameInput.getText();
		String phnum = newPhNumInput.getText();
		
		String commend;
		int sameCheck = 0;    // 입력한 아이디 또는 비밀번호가 있는지 확인용

		try {
			sql.con = DriverManager.getConnection(sql.url, sql.user, sql.passwd);
			sql.stmt = sql.con.createStatement();
			Class.forName("com.mysql.cj.jdbc.Driver");

			ResultSet result = sql.stmt.executeQuery("SELECT * FROM signData");
			if (e.getSource() == ok) {
				while (result.next()) {
					String selectId = result.getString("id");    // signdata 테이블에 있는 아이디 불러오기
					String selectPw = result.getString("password");    // signdata 테이블에 있는 비밀번호 불러오기
					if (selectId.equals(id) || selectPw.equals(pass)) {    // 만약 사용자가 입력한것과 signdata테이블에서 불러온게 같다면
						sameCheck = 1;    // 입력한 아이디 또는 비밀번호가 이미 존재한다면 1
						signInFail.setText("이미 존재하는 아이디 또는 비밀번호 입니다.");
						break;
					}
				}
				if (sameCheck == 0) {    // 동일한 아이디 또는 비밀번호가 존재하지 않는다면
					commend = "INSERT INTO signData VALUES ('" + id + "', '" + pass + "'"
							+ ", '" + name + "', '" + phnum + "')";    // 사용자가 입력한 id와 pass 추가하는 sql문
					sql.stmt.executeUpdate(commend); 
					signInFail.setText("회원가입에 성공하였습니다.");
				}
			}
			else if(e.getSource() == gotologinFrame) {
				dispose();    // SigninProgram 안보이게
				lf.setVisible(true);    // loginFrame 보이게
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
