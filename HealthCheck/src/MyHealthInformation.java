import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MyHealthInformation extends JFrame implements ActionListener {
	Mysql sql = new Mysql();
	userData udt = new userData();

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel tableModel;
	String columName[] = { "수축기 혈압", "이완기 혈압", "식전혈당", "총콜레스테롤", "트리글리세라이드", "HDL콜레스테롤", "LDL콜레스테롤", "혈색소", "요단백",
			"크레아티닌", "AST", "ALT", "GTP", "날짜" }; // 테이블의 행 목록
	String name;
	String id;

	JButton addButton;
	JButton deleteButton;
	JButton reviseButton;
	JButton backButton;
	private JLabel label;
	private JButton barChartFullScreen;    // 막대그래프 전체화면 버튼
	private JButton informationFullScreen;    // 비정상수치 전체화면 버튼
	private JTextArea textArea;
	private JTextArea textArea_1;
	private JTextField sysTxt;    // 건강정보 입력용 TextField
	private JTextField diasTxt;
	private JTextField preMealTxt;
	private JTextField choTxt;
	private JTextField triTxt;
	private JTextField hdlTxt;
	private JTextField ldlTxt;
	private JTextField hemoTxt;
	private JTextField proteinTxt;
	private JTextField serumTxt;
	private JTextField astTxt;
	private JTextField altTxt;
	private JTextField gtpTxt;

	public MyHealthInformation(String name, String id) {
		this.name = name;
		this.id = id;
		barChart barChart = new barChart(id, 0, 0);
		udt.userInformation(id);
		int q = udt.userData.size() - 1;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 970, 556);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		table = new JTable();
		table.setEnabled(false);
		table.setFont(new Font("굴림", Font.BOLD, 16));
		table.setBounds(12, 36, 932, 163);
		contentPane.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 36, 932, 163);
		contentPane.add(scrollPane);

		addButton = new JButton("추가");
		addButton.addActionListener(this);
		addButton.setBounds(752, 2, 63, 30);
		contentPane.add(addButton);

		deleteButton = new JButton("삭제");
		deleteButton.addActionListener(this);
		deleteButton.setBounds(817, 2, 63, 30);
		contentPane.add(deleteButton);

		reviseButton = new JButton("수정");
		reviseButton.addActionListener(this);
		reviseButton.setBounds(881, 2, 63, 30);
		contentPane.add(reviseButton);

		backButton = new JButton("뒤로가기");
		backButton.addActionListener(this);
		backButton.setBounds(12, 2, 94, 30);
		contentPane.add(backButton);

		// 이거 있으면 윈도우 빌더가 안뜸
		barChart.setBackground(Color.WHITE); 
		barChart.setBounds(12, 230, 641, 282);
		contentPane.add(barChart);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(665, 230, 279, 286);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		sysTxt = new JTextField();
		sysTxt.setEditable(false);
		sysTxt.setBackground(Color.WHITE);
		if (udt.userData.get(q).systolicBloodPre > 90 && udt.userData.get(q).systolicBloodPre < 130)
			sysTxt.setText("1. 수축기 혈압\t\t:  정상");
		else
			sysTxt.setText("1. 수축기 혈압\t\t:  비정상");
		sysTxt.setBounds(12, 15, 255, 21);
		panel_1.add(sysTxt);
		sysTxt.setColumns(10);

		diasTxt = new JTextField();
		diasTxt.setBackground(Color.WHITE);
		diasTxt.setEditable(false);
		if (udt.userData.get(q).diastolicBloodPre > 60 && udt.userData.get(q).diastolicBloodPre < 80)
			diasTxt.setText("2. 이완기 혈압\t\t:  정상");
		else
			diasTxt.setText("2. 이완기 혈압\t\t:  비정상");
		diasTxt.setColumns(10);
		diasTxt.setBounds(12, 35, 255, 21);
		panel_1.add(diasTxt);

		preMealTxt = new JTextField();
		preMealTxt.setBackground(Color.WHITE);
		preMealTxt.setEditable(false);
		if (udt.userData.get(q).preMealBloodSugar < 101)
			preMealTxt.setText("3. 식전혈당\t\t:  정상");
		else
			preMealTxt.setText("3. 식전혈당\t\t:  비정상");
		preMealTxt.setColumns(10);
		preMealTxt.setBounds(12, 55, 255, 21);
		panel_1.add(preMealTxt);

		choTxt = new JTextField();
		choTxt.setBackground(Color.WHITE);
		choTxt.setEditable(false);
		if (udt.userData.get(q).totalCholesterol < 200)
			choTxt.setText("4. 총 콜레스테롤\t:  정상");
		else
			choTxt.setText("4. 총 콜레스테롤\t:  비정상");
		choTxt.setColumns(10);
		choTxt.setBounds(12, 75, 255, 21);
		panel_1.add(choTxt);

		triTxt = new JTextField();
		triTxt.setBackground(Color.WHITE);
		triTxt.setEditable(false);
		if (udt.userData.get(q).triglyceride < 151)
			triTxt.setText("5. 트리글리세라이드\t:  정상");
		else
			triTxt.setText("5. 트리글리세라이드\t:  비정상");
		triTxt.setColumns(10);
		triTxt.setBounds(12, 95, 255, 21);
		panel_1.add(triTxt);

		hdlTxt = new JTextField();
		hdlTxt.setBackground(Color.WHITE);
		hdlTxt.setEditable(false);
		if (udt.userData.get(q).hdl > 40 && udt.userData.get(q).hdl < 70)
			hdlTxt.setText("6. HDL 콜레스테롤\t:  정상");
		else
			hdlTxt.setText("6. HDL 콜레스테롤\t:  비정상");
		hdlTxt.setColumns(10);
		hdlTxt.setBounds(12, 115, 255, 21);
		panel_1.add(hdlTxt);

		ldlTxt = new JTextField();
		ldlTxt.setBackground(Color.WHITE);
		ldlTxt.setEditable(false);
		if (udt.userData.get(q).ldl > 130)
			ldlTxt.setText("7. LDL 콜레스테롤\t:  비정상");
		else
			ldlTxt.setText("7. LDL 콜레스테롤\t:  정상");
		ldlTxt.setColumns(10);
		ldlTxt.setBounds(12, 135, 255, 21);
		panel_1.add(ldlTxt);

		hemoTxt = new JTextField();
		hemoTxt.setBackground(Color.WHITE);
		hemoTxt.setEditable(false);
		if (udt.userData.get(q).hemoglobin > 11 && udt.userData.get(q).hemoglobin < 19)
			hemoTxt.setText("8. 혈색소\t\t:  정상");
		else
			hemoTxt.setText("8. 혈색소\t\t:  비정상");
		hemoTxt.setColumns(10);
		hemoTxt.setBounds(12, 155, 255, 21);
		panel_1.add(hemoTxt);

		proteinTxt = new JTextField();
		if (udt.userData.get(q).proteinInUrine < 150)
			proteinTxt.setText("9. 요단백\t\t:  정상");
		else
			proteinTxt.setText("9. 요단백\t\t:  비정상");
		proteinTxt.setColumns(10);
		proteinTxt.setBounds(12, 175, 255, 21);
		panel_1.add(proteinTxt);

		serumTxt = new JTextField();
		if (udt.userData.get(q).serumCreatine > 0.49 && udt.userData.get(q).serumCreatine < 1.41)
			serumTxt.setText("10. 혈청 크레아티닌\t:  정상");
		else
			serumTxt.setText("10. 혈청 크레아티닌\t:  비정상");
		serumTxt.setColumns(10);
		serumTxt.setBounds(12, 195, 255, 21);
		panel_1.add(serumTxt);

		astTxt = new JTextField();
		if (udt.userData.get(q).ast < 41)
			astTxt.setText("11. AST\t\t:  정상");
		else
			astTxt.setText("11. AST\t\t:  비정상");
		astTxt.setColumns(10);
		astTxt.setBounds(12, 215, 255, 21);
		panel_1.add(astTxt);

		altTxt = new JTextField();
		if (udt.userData.get(q).alt < 41)
			altTxt.setText("12. ALT\t\t:  정상");
		else
			altTxt.setText("12. ALT\t\t:  비정상");
		altTxt.setColumns(10);
		altTxt.setBounds(12, 235, 255, 21);
		panel_1.add(altTxt);

		gtpTxt = new JTextField();
		if (udt.userData.get(q).gtp < 64 && udt.userData.get(q).gtp > 10)
			gtpTxt.setText("13. 감마지티피\t\t:  정상");
		else
			gtpTxt.setText("13. 감마지티피\t\t:  비정상");
		gtpTxt.setColumns(10);
		gtpTxt.setBounds(12, 255, 255, 21);
		panel_1.add(gtpTxt);

		barChartFullScreen = new JButton("전체화면");
		barChartFullScreen.addActionListener(this);
		barChartFullScreen.setBounds(569, 203, 84, 23);
		contentPane.add(barChartFullScreen);

		informationFullScreen = new JButton("전체화면");
		informationFullScreen.addActionListener(this);
		informationFullScreen.setBounds(860, 203, 84, 23);
		contentPane.add(informationFullScreen);

		textArea = new JTextArea();
		textArea.setForeground(Color.BLUE);
		textArea.setText(" 사용자");
		textArea.setBounds(12, 210, 56, 23);
		contentPane.add(textArea);

		textArea_1 = new JTextArea();
		textArea_1.setText("평균");
		textArea_1.setForeground(Color.RED);
		textArea_1.setBounds(68, 210, 35, 23);
		contentPane.add(textArea_1);
		
		insertTable();
	}

	public void insertTable() {
		Object[][] uData = new Object[udt.userData.size()][14];
		int k = 0;

		for (int i = udt.userData.size() - 1; i >= 0; i--) {    // JTable에 넣기 위해 userData 리스트에 있는 값 배열에 추가하기
			int j = 0;
			if (udt.userData.get(i).id.equals(udt.userData.get(udt.userData.size() - 1).id)) {
				uData[k][j] = (Object) (udt.userData.get(i).systolicBloodPre);
				j++;
				uData[k][j] = (Object) (udt.userData.get(i).diastolicBloodPre);
				j++;
				uData[k][j] = (Object) (udt.userData.get(i).preMealBloodSugar);
				j++;
				uData[k][j] = (Object) (udt.userData.get(i).totalCholesterol);
				j++;
				uData[k][j] = (Object) (udt.userData.get(i).triglyceride);
				j++;
				uData[k][j] = (Object) (udt.userData.get(i).hdl);
				j++;
				uData[k][j] = (Object) (udt.userData.get(i).ldl);
				j++;
				uData[k][j] = (Object) (udt.userData.get(i).hemoglobin);
				j++;
				uData[k][j] = (Object) (udt.userData.get(i).proteinInUrine);
				j++;
				uData[k][j] = (Object) (udt.userData.get(i).serumCreatine);
				j++;
				uData[k][j] = (Object) (udt.userData.get(i).ast);
				j++;
				uData[k][j] = (Object) (udt.userData.get(i).alt);
				j++;
				uData[k][j] = (Object) (udt.userData.get(i).gtp);
				j++;
				uData[k][j] = (Object) (udt.userData.get(i).date);
				j++;
				k++;
			}
		}
		DefaultTableModel tableModel = new DefaultTableModel(uData, columName); // tableModel 데이터 갱신
		table.setModel(tableModel); // 갱신된 데이터 적용
		table.getColumn("요단백").setPreferredWidth(45);    // JTable 크기 조정
		table.getColumn("날짜").setPreferredWidth(95);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backButton) {    // MainProgram으로 돌아가기
			MainProgram mp = new MainProgram(name, id);
			mp.setVisible(true);
			dispose();
		} else if (e.getSource() == barChartFullScreen) {    // 막대그래프 전체보기
			BarChartFullScreen bf = new BarChartFullScreen(name, id, 0);
			bf.setVisible(true);
			dispose();
		} else if(e.getSource() == informationFullScreen) {    // 정상, 비정상수치 전체보기
			InformationFullScreen information = new InformationFullScreen(name, id, udt.userData.size()-1);
			information.setVisible(true);
			dispose();
		}
		else if(e.getSource() == addButton) {    // 내용 추가
			ButtonAction bt = new ButtonAction();
			bt.addInformation(name, id);
			bt.setVisible(true);
		}
		else if(e.getSource() == deleteButton) {    // 내용 삭제
			ButtonAction bt = new ButtonAction();
			bt.delInformation(name, id);
			bt.setVisible(true);
		}
		else if(e.getSource() == reviseButton) {    // 내용 수정
			ButtonAction bt = new ButtonAction();
			bt.reviseInformation(name, id);
			bt.setVisible(true);
		}
	}
}
