import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;

public class NearbySearch extends JFrame implements ActionListener{
	JPanel contentPane;
	JButton backButton;
	JButton ok;
	JComboBox<String> citySelect;
	JComboBox<String> citySelect_detail;
	ArrayList<String> hospitalName = new ArrayList<>();
	ArrayList<String> hospitalPhNum = new ArrayList<>();
	ArrayList<String> hospitalLocal = new ArrayList<>();
	
	String name;
	String id;
	String selectCityName;    // 콤보박스에서 선택한 시도명
	String selectCityName_deatil;    // 콤보박스에서 선택한 시군구명
	String cityNameList[] = new String[17];    // 시도명
	String cityCodeList[] = new String[17];    // 시도코드
	String cityNameList_detail[] = new String[50];    // 시군구명
	String cityCodeList_detail[] = new String[50];    // 시군구코드
	String colName[] = {"병원 이름", "전화번호", "위치"};
	
	File dataFile = new File("cityCode1.txt");    // 시도 정보가 들어있는 txt파일
	File dataFile_detail = new File("cityCode2.txt");    // 시군구 정보가 들어있는 txt파일
	private JTable table;
	
	public NearbySearch(String name, String id) {
		this.name = name;
		this.id = id;
		
		try {    // citycode1.txt파일 읽어와서 배열에 삽입
			BufferedReader br = new BufferedReader(new FileReader(dataFile));
			StringTokenizer st;
			String readData;
			String cityName;
			String cityCode;
			int i = 0;
			
			while ((readData = br.readLine()) != null) {    // 한줄씩 읽어서 배열에 넣기위함
				st = new StringTokenizer(readData, "\t");    // " "기준으로 문자열 자르기
				cityName = st.nextToken();
				cityNameList[i] = cityName;
				cityCode = st.nextToken();
				cityCodeList[i] = cityCode; i++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 970, 556);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel selectPanel = new JPanel();
		selectPanel.setBackground(Color.WHITE);
		selectPanel.setBounds(12, 38, 252, 471);
		contentPane.add(selectPanel);
		selectPanel.setLayout(null);
		
		JLabel selectLabel = new JLabel("시도 선택");
		selectLabel.setFont(new Font("굴림", Font.BOLD, 15));
		selectLabel.setBounds(12, 31, 117, 29);
		selectPanel.add(selectLabel);
		
		JLabel selectLabel_detail = new JLabel("시군구 선택");
		selectLabel_detail.setFont(new Font("굴림", Font.BOLD, 15));
		selectLabel_detail.setBounds(12, 122, 117, 29);
		selectPanel.add(selectLabel_detail);
		
		citySelect = new JComboBox(cityNameList);
		citySelect.setBounds(12, 64, 228, 23);
		citySelect.addActionListener(this);
		selectPanel.add(citySelect);
	
		citySelect_detail = new JComboBox(cityNameList_detail);
		citySelect_detail.setBounds(12, 157, 228, 23);
		citySelect_detail.addActionListener(this);
		selectPanel.add(citySelect_detail);
		
		ok = new JButton("조회");
		ok.setBounds(73, 219, 91, 23);
		ok.addActionListener(this);
		selectPanel.add(ok);
		
		JPanel tablePanel = new JPanel();
		tablePanel.setBackground(Color.WHITE);
		tablePanel.setBounds(276, 38, 668, 471);
		contentPane.add(tablePanel);
		tablePanel.setLayout(null);
		
		table = new JTable();
		table.setEnabled(false);
		table.setFont(new Font("굴림", Font.BOLD, 13));
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 668, 471);
		tablePanel.add(scrollPane);
		
		backButton = new JButton("뒤로가기");
		backButton.addActionListener(this);
		backButton.setBounds(12, 2, 94, 30);
		contentPane.add(backButton);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int cityIndex = 0;
		int city_detailIndex = 0;
		
		if (e.getSource() == backButton) {    // MainProgram으로 돌아가기
			MainProgram mp = new MainProgram(name, id);
			mp.setVisible(true);
			dispose();
		}
		else if(e.getSource() == citySelect) {
			citySelect_detail.removeAllItems();    // comboBox 초기화
			Arrays.fill(cityNameList_detail, null);    // cityNameList_detail을 null 값으로 초기화
			Arrays.fill(cityCodeList_detail, null);    // cityCodeList_detail을 null 값으로 초기화
			
			selectCityName = citySelect.getSelectedItem().toString();    // 콤보박스에서 선택한 시도 문자열로 받기
			for(cityIndex = 0; cityIndex<cityNameList.length; cityIndex++) {    // 시도의 코드번호를 알기위한 검색
				if(cityNameList[cityIndex].equals(selectCityName)) {
					
					try {    // cirtCode2.txt파일 읽어와서 배열에 삽입
						BufferedReader br = new BufferedReader(new FileReader(dataFile_detail));
						StringTokenizer st;
						String readData;
						String cityName;
						String cityCode;
						int j = 0;
						
						while ((readData = br.readLine()) != null) {    // 한줄씩 읽어서 배열에 넣기위함
							st = new StringTokenizer(readData, "\t");    // "\t"기준으로 문자열 자르기
							cityName = st.nextToken();
							cityCode = st.nextToken();
							if(cityCode.substring(0, 2).equals(cityCodeList[cityIndex])) {    // 시군구 코드 앞 2개와 시도코드 비교해서 같은것만 삽입
								cityNameList_detail[j] = cityName;
								cityCodeList_detail[j] = cityCode.substring(2);
								j++;
							}
						}
					}catch(Exception e1) {
						e1.printStackTrace();
					}
				}
			}
			for(int i = 0; i<cityNameList_detail.length; i++) {    // 콤보박스에 삽입
				citySelect_detail.addItem(cityNameList_detail[i]);
			}
		}
		else if(e.getSource() == ok) {
			selectCityName_deatil = citySelect_detail.getSelectedItem().toString();    // 콤보박스에서 선택한 시군구 문자열로 받기
			try {
				for(cityIndex = 0; cityIndex<cityNameList.length; cityIndex++) {    // 시도의 코드번호를 알기위한 검색
					if(cityNameList[cityIndex].equals(selectCityName)) break;
				}
				for(city_detailIndex = 0; city_detailIndex<cityNameList_detail.length; city_detailIndex++) {  // 시군구의 코드번호 알기위해 검색
					if(cityNameList_detail[city_detailIndex].equals(selectCityName_deatil)) break;
				}
				table.removeAll();
				
				ApiExplorer(cityCodeList[cityIndex], cityCodeList_detail[city_detailIndex]);    // 코드번호 넘겨주기
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void ApiExplorer(String cityCode, String cityCode_detail) throws IOException {
		StringTokenizer st;
		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi1.nhis.or.kr/openapi/service/rest/HmcSearchService/getRegnHmcList"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
				+ "=W0%2BkPELsi0L%2Fk2JUD5%2F10rzAwUl2lQxhRTKXuFcLSKNuTPck251M7cd%2FsLSJd%2Fl3HDL4ijMvmKEId%2B8YLemiIw%3D%3D"); /*
																																 * Service
																																 * Key
																																 */
		urlBuilder.append(
				"&" + URLEncoder.encode("siDoCd", "UTF-8") + "=" + URLEncoder.encode(cityCode, "UTF-8")); /* 시도코드 */
		urlBuilder.append("&" + URLEncoder.encode("siGunGuCd", "UTF-8") + "="
				+ URLEncoder.encode(cityCode_detail, "UTF-8")); /* 시군구코드 */
		URL url = new URL(urlBuilder.toString()); // 문자열을 URL로 만들기
		String url2 = urlBuilder.toString();
		HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // Http를 사용하여 연결 시작
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String str;
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}

		line = new String(sb);
		st = new StringTokenizer(line, ">");

		int check = 1;
		
		while (st.hasMoreTokens()) {
			String content = st.nextToken();
			if (content.contains("locAddr") && content.length() > 10) { // 병원 위치 찾기
				hospitalLocal.add(content.substring(0, content.length() - 9));
			} else if (content.contains("hmcTelNo") && content.length() > 11) { // 병원 전화번호 찾기
				hospitalPhNum.add(content.substring(0, content.length() - 10));
			} else if (content.contains("hmcNm") && content.length() > 8) { // 병원 이름 찾기
				hospitalName.add(content.substring(0, content.length() - 7));
			}
		}
		
		Object hospitalInformation[][] = new Object[hospitalName.size()][3];
		for(int i = 0; i<hospitalName.size(); i++) {
			hospitalInformation[i][0] = hospitalName.get(i);
			hospitalInformation[i][1] = hospitalPhNum.get(i);
			hospitalInformation[i][2] = hospitalLocal.get(i);
		}
		
		DefaultTableModel tableModel = new DefaultTableModel(hospitalInformation, colName); // tableModel 데이터 갱신
		table.setModel(tableModel); // 갱신된 데이터 적용
		
		rd.close();
	}
}
