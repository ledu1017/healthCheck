import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;

public class ButtonAction extends JFrame implements ActionListener {
	Mysql sql = new Mysql();
	
	JButton Enter;
	JButton delEnter;
	JButton reviseEnter;
	private JPanel contentPane;
	private JTextField sysInput;
	private JTextField diasInput;
	private JTextField preMealInput;
	private JTextField choInput;
	private JTextField triInput;
	private JTextField hdlInput;
	private JTextField ldlInput;
	private JTextField hemoInput;
	private JTextField proteinInput;
	private JTextField serumInput;
	private JTextField astInput;
	private JTextField altInput;
	private JTextField gtpInput;
	private JTextField dateInput;
	private JLabel lblNewLabel_5;
	
	String str;
	String id;
	String name;
	private JLabel lblNewLabel_6;
	private JTextField delDate;
	
	public ButtonAction() {
		sql.connectMysql();
	}

	public void addInformation(String name, String id) {
		this.id = id;
		this.name = name;
		
		setBounds(100, 100, 321, 499);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("1. 수축기 혈압");
		lblNewLabel.setBounds(33, 48, 89, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("2. 이완기 혈압");
		lblNewLabel_1.setBounds(33, 73, 100, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("3. 식전혈당");
		lblNewLabel_2.setBounds(33, 98, 100, 15);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("4. 총 콜레스테롤");
		lblNewLabel_3.setBounds(33, 123, 100, 15);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("5. 트리글리세라이트");
		lblNewLabel_4.setBounds(33, 148, 120, 15);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblHdl = new JLabel("6. HDL 콜레스테롤");
		lblHdl.setBounds(33, 173, 100, 15);
		contentPane.add(lblHdl);
		
		JLabel lblLdl = new JLabel("7. LDL 콜레스테롤");
		lblLdl.setBounds(33, 198, 100, 15);
		contentPane.add(lblLdl);
		
		JLabel lblNewLabel_7 = new JLabel("8. 혈색소");
		lblNewLabel_7.setBounds(33, 223, 100, 15);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("9. 요단백");
		lblNewLabel_8.setBounds(33, 248, 100, 15);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("10. 혈청크레아티닌");
		lblNewLabel_9.setBounds(33, 273, 120, 15);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblAst = new JLabel("11. AST");
		lblAst.setBounds(33, 298, 100, 15);
		contentPane.add(lblAst);
		
		JLabel lblAlt = new JLabel("12. ALT");
		lblAlt.setBounds(33, 323, 100, 15);
		contentPane.add(lblAlt);
		
		JLabel lblGtp = new JLabel("13. GTP");
		lblGtp.setBounds(33, 348, 100, 15);
		contentPane.add(lblGtp);
		
		JLabel lblyymmdd = new JLabel("14. 날짜(YY.MM.DD)");
		lblyymmdd.setBounds(33, 373, 120, 15);
		contentPane.add(lblyymmdd);
		
		sysInput = new JTextField();
		sysInput.setColumns(10);
		sysInput.setBounds(184, 45, 96, 15);
		contentPane.add(sysInput);
		
		diasInput = new JTextField();
		diasInput.setColumns(10);
		diasInput.setBounds(184, 70, 96, 15);
		contentPane.add(diasInput);
		
		preMealInput = new JTextField();
		preMealInput.setColumns(10);
		preMealInput.setBounds(184, 95, 96, 15);
		contentPane.add(preMealInput);
		
		choInput = new JTextField();
		choInput.setColumns(10);
		choInput.setBounds(184, 120, 96, 15);
		contentPane.add(choInput);
		
		triInput = new JTextField();
		triInput.setColumns(10);
		triInput.setBounds(184, 145, 96, 15);
		contentPane.add(triInput);
		
		hdlInput = new JTextField();
		hdlInput.setColumns(10);
		hdlInput.setBounds(184, 170, 96, 15);
		contentPane.add(hdlInput);
		
		ldlInput = new JTextField();
		ldlInput.setColumns(10);
		ldlInput.setBounds(184, 195, 96, 15);
		contentPane.add(ldlInput);
		
		hemoInput = new JTextField();
		hemoInput.setColumns(10);
		hemoInput.setBounds(184, 220, 96, 15);
		contentPane.add(hemoInput);
		
		proteinInput = new JTextField();
		proteinInput.setColumns(10);
		proteinInput.setBounds(184, 245, 96, 15);
		contentPane.add(proteinInput);
		
		serumInput = new JTextField();
		serumInput.setColumns(10);
		serumInput.setBounds(184, 270, 96, 15);
		contentPane.add(serumInput);
		
		astInput = new JTextField();
		astInput.setColumns(10);
		astInput.setBounds(184, 295, 96, 15);
		contentPane.add(astInput);
		
		altInput = new JTextField();
		altInput.setColumns(10);
		altInput.setBounds(184, 320, 96, 15);
		contentPane.add(altInput);
		
		gtpInput = new JTextField();
		gtpInput.setColumns(10);
		gtpInput.setBounds(184, 345, 96, 15);
		contentPane.add(gtpInput);
		
		dateInput = new JTextField();
		dateInput.setColumns(10);
		dateInput.setBounds(184, 370, 96, 15);
		contentPane.add(dateInput);
		
		Enter = new JButton("확인");
		Enter.addActionListener(this);
		Enter.setBounds(101, 400, 91, 23);
		contentPane.add(Enter);
		
		lblNewLabel_5 = new JLabel("수치를 모른다면 0 입력");
		lblNewLabel_5.setBounds(86, 10, 141, 15);
		contentPane.add(lblNewLabel_5);
	}
	
	public void delInformation(String name, String id) {
		this.id = id;
		this.name = name;
		
		setBounds(300, 200, 500, 232);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel_10 = new JLabel("삭제하고싶은 정보의 날짜를 입력해주세요.(YY.MM.DD)");
		lblNewLabel_10.setBounds(65, 32, 323, 15);
		getContentPane().add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("입력 : ");
		lblNewLabel_11.setBounds(63, 68, 50, 15);
		getContentPane().add(lblNewLabel_11);
		
		delDate = new JTextField();
		delDate.setBounds(125, 65, 224, 21);
		getContentPane().add(delDate);
		delDate.setColumns(10);
		
		delEnter = new JButton("확인");
		delEnter.addActionListener(this);
		delEnter.setBounds(175, 111, 91, 23);
		getContentPane().add(delEnter);
	}
	
	public void reviseInformation(String name, String id) {
		this.id = id;
		this.name = name;
		
		setBounds(100, 100, 321, 499);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("1. 수축기 혈압");
		lblNewLabel.setBounds(33, 48, 89, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("2. 이완기 혈압");
		lblNewLabel_1.setBounds(33, 73, 100, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("3. 식전혈당");
		lblNewLabel_2.setBounds(33, 98, 100, 15);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("4. 총 콜레스테롤");
		lblNewLabel_3.setBounds(33, 123, 100, 15);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("5. 트리글리세라이트");
		lblNewLabel_4.setBounds(33, 148, 120, 15);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblHdl = new JLabel("6. HDL 콜레스테롤");
		lblHdl.setBounds(33, 173, 100, 15);
		contentPane.add(lblHdl);
		
		JLabel lblLdl = new JLabel("7. LDL 콜레스테롤");
		lblLdl.setBounds(33, 198, 100, 15);
		contentPane.add(lblLdl);
		
		JLabel lblNewLabel_7 = new JLabel("8. 혈색소");
		lblNewLabel_7.setBounds(33, 223, 100, 15);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("9. 요단백");
		lblNewLabel_8.setBounds(33, 248, 100, 15);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("10. 혈청크레아티닌");
		lblNewLabel_9.setBounds(33, 273, 120, 15);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblAst = new JLabel("11. AST");
		lblAst.setBounds(33, 298, 100, 15);
		contentPane.add(lblAst);
		
		JLabel lblAlt = new JLabel("12. ALT");
		lblAlt.setBounds(33, 323, 100, 15);
		contentPane.add(lblAlt);
		
		JLabel lblGtp = new JLabel("13. GTP");
		lblGtp.setBounds(33, 348, 100, 15);
		contentPane.add(lblGtp);
		
		JLabel lblyymmdd = new JLabel("14. 날짜(YY.MM.DD)");
		lblyymmdd.setBounds(33, 373, 120, 15);
		contentPane.add(lblyymmdd);
		
		sysInput = new JTextField();
		sysInput.setColumns(10);
		sysInput.setBounds(184, 45, 96, 15);
		contentPane.add(sysInput);
		
		diasInput = new JTextField();
		diasInput.setColumns(10);
		diasInput.setBounds(184, 70, 96, 15);
		contentPane.add(diasInput);
		
		preMealInput = new JTextField();
		preMealInput.setColumns(10);
		preMealInput.setBounds(184, 95, 96, 15);
		contentPane.add(preMealInput);
		
		choInput = new JTextField();
		choInput.setColumns(10);
		choInput.setBounds(184, 120, 96, 15);
		contentPane.add(choInput);
		
		triInput = new JTextField();
		triInput.setColumns(10);
		triInput.setBounds(184, 145, 96, 15);
		contentPane.add(triInput);
		
		hdlInput = new JTextField();
		hdlInput.setColumns(10);
		hdlInput.setBounds(184, 170, 96, 15);
		contentPane.add(hdlInput);
		
		ldlInput = new JTextField();
		ldlInput.setColumns(10);
		ldlInput.setBounds(184, 195, 96, 15);
		contentPane.add(ldlInput);
		
		hemoInput = new JTextField();
		hemoInput.setColumns(10);
		hemoInput.setBounds(184, 220, 96, 15);
		contentPane.add(hemoInput);
		
		proteinInput = new JTextField();
		proteinInput.setColumns(10);
		proteinInput.setBounds(184, 245, 96, 15);
		contentPane.add(proteinInput);
		
		serumInput = new JTextField();
		serumInput.setColumns(10);
		serumInput.setBounds(184, 270, 96, 15);
		contentPane.add(serumInput);
		
		astInput = new JTextField();
		astInput.setColumns(10);
		astInput.setBounds(184, 295, 96, 15);
		contentPane.add(astInput);
		
		altInput = new JTextField();
		altInput.setColumns(10);
		altInput.setBounds(184, 320, 96, 15);
		contentPane.add(altInput);
		
		gtpInput = new JTextField();
		gtpInput.setColumns(10);
		gtpInput.setBounds(184, 345, 96, 15);
		contentPane.add(gtpInput);
		
		dateInput = new JTextField();
		dateInput.setColumns(10);
		dateInput.setBounds(184, 370, 96, 15);
		contentPane.add(dateInput);
		
		reviseEnter = new JButton("확인");
		reviseEnter.addActionListener(this);
		reviseEnter.setBounds(101, 400, 91, 23);
		contentPane.add(reviseEnter);
		
		lblNewLabel_5 = new JLabel("수치를 모른다면 0 입력");
		lblNewLabel_5.setBounds(86, 10, 141, 15);
		contentPane.add(lblNewLabel_5);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == Enter) {    // 건강정보 추가
			float sys = Float.valueOf(sysInput.getText());
			float dias = Float.valueOf(diasInput.getText());
			float preMeal = Float.valueOf(preMealInput.getText());
			float cho = Float.valueOf(choInput.getText());
			float tri = Float.valueOf(triInput.getText());
			float hdl = Float.valueOf(hdlInput.getText());
			float ldl = Float.valueOf(ldlInput.getText());
			float hemo = Float.valueOf(hemoInput.getText());
			float protein = Float.valueOf(proteinInput.getText());
			float serum = Float.valueOf(serumInput.getText());
			float ast = Float.valueOf(astInput.getText());
			float alt = Float.valueOf(altInput.getText());
			float gtp = Float.valueOf(gtpInput.getText());
			
			str = "insert into userdata values('" + id + "', '" + sys +"', '" + dias +"', "    // 추가용 sql문
					+ "'" + preMeal +"', '" + cho + "', '" + tri +"', "
					+ "'" + hdl + "', '" + ldl + "', '" + hemo + "', "
					+ "'" + protein + "', '" + serum + "', '" + ast + "', "
					+ "'" + alt + "', '" + gtp + "', '" + dateInput.getText() +"', '" + name + "')";
			
			try {
				sql.stmt.executeUpdate(str);
				lblNewLabel_6 = new JLabel("추가 되었습니다.");
				lblNewLabel_6.setBounds(103, 437, 124, 15);
				contentPane.add(lblNewLabel_6);
			}catch(Exception e1) {
				e1.printStackTrace();
				lblNewLabel_6 = new JLabel("실패 하였습니다.");
				lblNewLabel_6.setBounds(103, 437, 124, 15);
				contentPane.add(lblNewLabel_6);
			}
			
			MyHealthInformation mi = new MyHealthInformation(name, id);
			mi.setVisible(true);
		}
		else if(e.getSource() == delEnter) {    // 건강정보 삭제		
			str = "delete from userdata where date = '" + delDate.getText() + "'";    // 삭제를 위해 날짜 입력 후 sql 검색문
			
			try {
				sql.stmt.executeUpdate(str);
				JLabel lblNewLabel_12 = new JLabel("삭제되었습니다");
				lblNewLabel_12.setBounds(181, 156, 116, 15);
				getContentPane().add(lblNewLabel_12);
			}catch(Exception e2) {
				e2.printStackTrace();
				JLabel lblNewLabel_12 = new JLabel("삭제실패했습니다.");
				lblNewLabel_12.setBounds(181, 156, 116, 15);
				getContentPane().add(lblNewLabel_12);
			}
			
			dispose();
			MyHealthInformation mi = new MyHealthInformation(name, id);
			mi.setVisible(true);
		}
		else if(e.getSource() == reviseEnter) {    // 건강정보 수정
			float sys = Float.valueOf(sysInput.getText());
			float dias = Float.valueOf(diasInput.getText());
			float preMeal = Float.valueOf(preMealInput.getText());
			float cho = Float.valueOf(choInput.getText());
			float tri = Float.valueOf(triInput.getText());
			float hdl = Float.valueOf(hdlInput.getText());
			float ldl = Float.valueOf(ldlInput.getText());
			float hemo = Float.valueOf(hemoInput.getText());
			float protein = Float.valueOf(proteinInput.getText());
			float serum = Float.valueOf(serumInput.getText());
			float ast = Float.valueOf(astInput.getText());
			float alt = Float.valueOf(altInput.getText());
			float gtp = Float.valueOf(gtpInput.getText());
			
			// 건강정보 수정을 위한 sql문
			
			str = "update userdata set id = '" + id + "' systolicBloodPre = " + sys + " diastolicBloodPre = " + dias + ""
					+ " preMealBloodSugar = " + preMeal + " totalCholesterol = " + cho + " triglyceride = " + tri + ""
					+ " hdl = " + hdl + " ldl = " + ldl + " hemoglobin = " + hemo + " proteinInUrine = " + protein + ""
					+ " serumCreatine = " + serum + " ast = " + ast + " alt = " + alt + " gtp = " + gtp +""
					+ " date = '" + dateInput.getText() + "' name = '" + name +"' where date = '" + dateInput.getText() + "'";
			System.out.println(str);
			try {
				sql.stmt.executeUpdate(str);
			}catch(Exception e2) {
				e2.printStackTrace();
			}
			
			dispose();
			MyHealthInformation mi = new MyHealthInformation(name, id);
			mi.setVisible(true);		
		}
	}
}
