import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTable;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;

public class InformationFullScreen extends JFrame implements ActionListener{
	ArrayList<String> informationText = new ArrayList<>();
	ArrayList<String> date = new ArrayList<>();
	userData udt = new userData();
	
	JPanel contentPane;
	JTable table;
	JButton backButton;
	JComboBox selectYear;
	JScrollPane scrollPane1;
	JScrollPane scrollPane2;
	JTextField textField;
	
	String columName[] = {"이름", "정상수치", "내 수치"}; // 테이블의 행 목록
	String category[] = {"수축기 혈압", "이완기 혈압", "식전혈당", "총콜레스테롤", "트리글리세라이드", "HDL콜레스테롤", "LDL콜레스테롤", "혈색소", "요단백",
			"크레아티닌", "AST", "ALT", "GTP"}; // 검사항목 이름
	Object[][] uData = new Object[13][14];
	float gap[] = new float[13];
	
	String name, id;
	File dataFile = new File("information.txt");
	JTextArea informationTxt;
	
	public InformationFullScreen(String name, String id, int year) {
		udt.userInformation(id);
		this.name = name;
		this.id = id;
		String content = "\n";
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 970, 556);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel tablePanel = new JPanel();
		tablePanel.setBounds(12, 38, 462, 471);
		contentPane.add(tablePanel);

		table = new JTable();
		table.setEnabled(false);
		table.setFont(new Font("굴림", Font.BOLD, 16));
		tablePanel.add(table);
		table.setRowHeight(34);    // 칸마다 높이 조정
		
		scrollPane1 = new JScrollPane(table);
		scrollPane1.setPreferredSize(new Dimension(462, 471));    // 스크롤팬을 페널 크기에 맞추기
		tablePanel.add(scrollPane1);

		backButton = new JButton("뒤로가기");
		backButton.addActionListener(this);
		backButton.setBounds(12, 2, 94, 30);
		contentPane.add(backButton);
		
		JPanel informationPanel = new JPanel();
		informationPanel.setBounds(486, 69, 462, 440);
		contentPane.add(informationPanel);
		informationPanel.setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("굴림", Font.BOLD, 16));
		textField.setText("비정상 수치\r\n");
		textField.setBounds(486, 40, 462, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		for (int i = 0; i < udt.userData.size(); i++) {    // Table에 있는 최근 날짜부터 date 리스트에 넣기
			date.add(udt.userData.get(i).date);
		}

		selectYear = new JComboBox(date.toArray(new String[date.size()]));    // date 리스트에 있는 날짜를 comboBox에 삽입
		selectYear.addActionListener(this);
		selectYear.setBounds(365, 12, 109, 20);
		contentPane.add(selectYear);
		
		insertTable(year);
		informationTextPanel();
		
		for(int i = 0; i<informationText.size(); i++) {    // 건강문제에 대한 추천사항 문자열 삽입
			content = (content + informationText.get(i));
			content = content + "\n\n";
		}
		
		informationTxt = new JTextArea(content);
		informationTxt.setWrapStyleWord(true);
		informationTxt.setForeground(new Color(0, 0, 0));
		informationTxt.setEditable(false);
		informationTxt.setFont(new Font("굴림", Font.BOLD, 15));
		informationTxt.setLineWrap(true);
		// 내용이 길어질 수 있기에 스크롤팬 추가
		scrollPane2 = new JScrollPane(informationTxt, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		informationPanel.add(scrollPane2, BorderLayout.CENTER);
	}

	public void insertTable(int year) {
		int k = 0;
		int j = 0;
		int count = 0;
		
		for(k = 0; k<13; k++) uData[k][j] = (Object)(category[k]);
		
		if (udt.userData.get(year).id.equals(udt.userData.get(udt.userData.size() - 1).id)) {    // 정상수치 삽입
			k = 0;
			j = 1;
			uData[k][j] = (Object) (udt.averData.get(0).systolicBloodPre);
			k++;
			uData[k][j] = (Object) (udt.averData.get(0).diastolicBloodPre);
			k++;
			uData[k][j] = (Object) (udt.averData.get(0).preMealBloodSugar);
			k++;
			uData[k][j] = (Object) (udt.averData.get(0).totalCholesterol);
			k++;
			uData[k][j] = (Object) (udt.averData.get(0).triglyceride);
			k++;
			uData[k][j] = (Object) (udt.averData.get(0).hdl);
			k++;
			uData[k][j] = (Object) (udt.averData.get(0).ldl);
			k++;
			uData[k][j] = (Object) (udt.averData.get(0).hemoglobin);
			k++;
			uData[k][j] = (Object) (udt.averData.get(0).proteinInUrine);
			k++;
			uData[k][j] = (Object) (udt.averData.get(0).serumCreatine);
			k++;
			uData[k][j] = (Object) (udt.averData.get(0).ast);
			k++;
			uData[k][j] = (Object) (udt.averData.get(0).alt);
			k++;
			uData[k][j] = (Object) (udt.averData.get(0).gtp);
			k++;
		}
		
		if (udt.userData.get(year).id.equals(udt.userData.get(udt.userData.size() - 1).id)) {    // 내 수치 삽입
			k = 0;
			j = 2;
			uData[k][j] = (Object) (udt.userData.get(year).systolicBloodPre);
			k++;
			uData[k][j] = (Object) (udt.userData.get(year).diastolicBloodPre);
			k++;
			uData[k][j] = (Object) (udt.userData.get(year).preMealBloodSugar);
			k++;
			uData[k][j] = (Object) (udt.userData.get(year).totalCholesterol);
			k++;
			uData[k][j] = (Object) (udt.userData.get(year).triglyceride);
			k++;
			uData[k][j] = (Object) (udt.userData.get(year).hdl);
			k++;
			uData[k][j] = (Object) (udt.userData.get(year).ldl);
			k++;
			uData[k][j] = (Object) (udt.userData.get(year).hemoglobin);
			k++;
			uData[k][j] = (Object) (udt.userData.get(year).proteinInUrine);
			k++;
			uData[k][j] = (Object) (udt.userData.get(year).serumCreatine);
			k++;
			uData[k][j] = (Object) (udt.userData.get(year).ast);
			k++;
			uData[k][j] = (Object) (udt.userData.get(year).alt);
			k++;
			uData[k][j] = (Object) (udt.userData.get(year).gtp);
			k++;
		}
		
		DefaultTableModel tableModel = new DefaultTableModel(uData, columName); // tableModel 데이터 갱신
		table.setModel(tableModel); // 갱신된 데이터 적용
	}
	
	public void informationTextPanel() {
		try {
			int count = 0;
			BufferedReader br = new BufferedReader(new FileReader(dataFile));
			String readData;
			while((readData = br.readLine()) != null) {
				if(((float)uData[0][2] > 120 || (float)uData[1][2] > 80) && count == 0) {
					gap[count] =(float) ((float)uData[0][1] - 120.0);
					informationText.add(readData.replace("\t", "\n"));    // 탭으로 구분해둔 내용 엔터로 바꿔서 리스트에 삽입
				}
				else if(((float)uData[0][2] < 100 || (float)uData[1][2] < 60) && count == 1) {
					gap[count] = (float) ((float)uData[1][1] - 80.0);
					informationText.add(readData.replace("\t", "\n"));
				}
				else if((float)uData[2][2] > 110 && count == 2) {
					gap[count] = (float) ((float)uData[2][2] - 110.0);
					informationText.add(readData.replace("\t", "\n"));
				}
				else if((float)uData[2][2] < 70 && count == 3) {
					gap[count] = (float)(70.0 - (float)uData[2][2]);
					informationText.add(readData.replace("\t", "\n"));
				}
				else if((float)uData[3][2] > 240 && count == 4) {
					gap[count] = (float)((float)uData[3][2] - 240.0);
					informationText.add(readData.replace("\t", "\n"));
				}
				else if((float)uData[3][2] < 200 && count == 5) {
					gap[count] = (float)(200.0 - (float)uData[3][2]);
					informationText.add(readData.replace("\t", "\n"));
				}
				else if((float)uData[4][2] > 150 && count == 6) {
					gap[count] = (float)((float)uData[4][2] - 150.0);
					informationText.add(readData.replace("\t", "\n"));
				}
				else if((float)uData[5][2] < 40 && count == 7) {
					gap[count] = (float)(40.0 - (float)uData[5][2]);
					informationText.add(readData.replace("\t", "\n"));
				}
				else if((float)uData[6][2] > 130 && count == 8) {
					gap[count] = (float)((float)uData[6][2] - 130.0);
					informationText.add(readData.replace("\t", "\n"));
				}
				else if((float)uData[7][2] > 18 && count == 9) {
					gap[count] = (float)((float)uData[7][2] - 18.0);
					informationText.add(readData.replace("\t", "\n"));
				}
				else if((float)uData[7][2] < 12 && count == 10) {
					gap[count] = (float)(12.0 - (float)uData[7][2]);
					informationText.add(readData.replace("\t", "\n"));
				}
				else if((float)uData[8][2] > 150 && count == 11) {
					gap[count] = (float)((float)uData[8][2] - 150.0);
					informationText.add(readData.replace("\t", "\n"));
				}
				else if((float)uData[9][2] > 1.04 && count == 12) {
					gap[count] = (float)((float)uData[9][2] - 1.04);
					informationText.add(readData.replace("\t", "\n"));
				}
				else if((float)uData[9][2] < 0.61 && count == 13) {
					gap[count] = (float)(0.61 - (float)uData[6][2]);
					informationText.add(readData.replace("\t", "\n"));
				}
				else if((float)uData[10][2] > 40 && count == 14) {
					gap[count] = (float)((float)uData[10][2] - 40.0);
					informationText.add(readData.replace("\t", "\n"));
				}
				else if((float)uData[11][2] > 33 && count == 15) {
					gap[count] = (float)((float)uData[6][2] - 33.0);
					informationText.add(readData.replace("\t", "\n"));
				}
				else if((float)uData[12][2] > 40 && count == 8) {
					gap[count] = (float)((float)uData[12][2] - 40.0);
					informationText.add(readData.replace("\t", "\n"));
				}
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backButton) {
			MyHealthInformation mi = new MyHealthInformation(name, id);
			mi.setVisible(true);
			dispose();
		}
		else if(e.getSource() == selectYear) {    // 날짜선택
			String selectCombo = selectYear.getSelectedItem().toString(); // 콤보박스에서 선택한 날짜 문자열로 받기
			for(int i = 0; i < udt.userData.size(); i++) {    // 선택한 콤보박스 날짜의 인덱스값 찾기
				if(date.get(i).equals(selectCombo)) {
					dispose();
					InformationFullScreen ifFullScreen = new InformationFullScreen(name, id, i);
					ifFullScreen.setVisible(true);
					udt.userData.clear();    // userData 배열에 있던 값 초기화
				}
			}
		}
	}
}
