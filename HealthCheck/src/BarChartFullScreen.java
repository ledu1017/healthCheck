import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

public class BarChartFullScreen extends JFrame implements ActionListener {
	ArrayList<String> date = new ArrayList<>();
	userData udt = new userData();
	
	private JPanel contentPane;
	JButton backButton;
	JComboBox selectYear;
	String selectCombo;
	String id;
	String name;
	
	public BarChartFullScreen(String name, String id, int index) {
		barChart barChart = new barChart(id, 1, index);
		udt.userInformation(id);
		this.id = id;
		this.name = name;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 970, 556);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		barChart.setBackground(Color.WHITE); 
		barChart.setBounds(12, 36, 932, 473);
		contentPane.add(barChart);
		 

		backButton = new JButton("뒤로가기");
		backButton.addActionListener(this);
		backButton.setBounds(12, 2, 94, 30);
		contentPane.add(backButton);

		for (int i = udt.userData.size()-1; i > 0; i--) {    // Table에 있는 최근 날짜부터 date 리스트에 넣기
			date.add(udt.userData.get(i).date);
		}

		selectYear = new JComboBox(date.toArray(new String[date.size()]));    // date 리스트에 있는 날짜를 comboBox에 삽입
		selectYear.addActionListener(this);
		selectYear.setBounds(800, 5, 109, 20);
		contentPane.add(selectYear);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
		if(e.getSource() == backButton) {
			MyHealthInformation mi = new MyHealthInformation(name, id);
			mi.setVisible(true);
			dispose();
		}
		
		if(e.getSource() == selectYear) {
			selectCombo = selectYear.getSelectedItem().toString(); // 콤보박스에서 선택한 날짜 문자열로 받기
			for(int i = 0; i < udt.userData.size(); i++) {    // 선택한 콤보박스 날짜의 인덱스값 찾기
				if(date.get(i).equals(selectCombo)) {
					dispose();
					BarChartFullScreen bf = new BarChartFullScreen(name, id, i);
					bf.setVisible(true);
					udt.userData.clear();    // userData 배열에 있던 값 초기화
				}
			}
		}
	}
}
