import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.StringTokenizer;

public class Mysql {
	Connection con = null;
	Statement stmt = null;
	String url = "jdbc:mysql://localhost/nationalhealth?serverTimezone=Asia/Seoul"; // MySQL 접속 url
	String user = "root"; // MySQl 접속 아이디
	String passwd = "doudu10170%"; // MySQL 접속 비밀번호

	File dataFile = new File("signData.txt"); // 사용자 정보가 들어있는 txt파일

	public void createTable(String ddl) {
		try {
			con = DriverManager.getConnection(url, user, passwd); // MySQL 접속
			stmt = con.createStatement();
			stmt.executeUpdate(ddl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void SignDataInsert() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(dataFile));
			String readData;
			StringTokenizer st;
			String id; // txt 파일에서 읽은 id
			String pass; // txt 파일에서 읽은 password
			String name;
			String phnum;
			String commend;

			Class.forName("com.mysql.cj.jdbc.Driver");

			// 테이블에 내용이 없다면 내용 추가
			try {
				ResultSet result = stmt.executeQuery("SELECT id FROM signData");
				result.next();
				String str = result.getString("id") + "\t";
			} catch (Exception e) { // 내용이 없다면 에러 발생하고 catch로 잡아서 테이블에 내용 추가
				while ((readData = br.readLine()) != null) {
					st = new StringTokenizer(readData, " "); // " "기준으로 문자열 자르기
					id = st.nextToken();
					pass = st.nextToken();
					name = st.nextToken();
					phnum = st.nextToken();
					// user 테이블에 id, password, name, phnum 추가하는 sql문
					commend = "INSERT INTO signData VALUES ('" + id + "', '" + pass + "', '" + name + "', '" + phnum + "')";
					stmt.executeUpdate(commend);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void userDataInsert() {
		try {
			dataFile = new File("userData.txt");
			Category category = new Category();
			BufferedReader br = new BufferedReader(new FileReader(dataFile));
			String readData;
			StringTokenizer st;
			String commend;
			String systolicBloodPre; // 수축기 혈압
			String diastolicBloodPre; // 이완기 혈압
			String preMealBloodSugar; // 식전혈당
			String totalCholesterol; // 총 콜레스테롤
			String triglyceride; // 트리글리세라이드
			String hdl; // HDL 콜레스테롤
			String ldl; // LDL 콜레스테롤
			String hemoglobin; // 혈색소
			String proteinInUrine; // 요단백
			String serumCreatine; // 혈청크레아티닌
			String ast; // (혈청지오티)AST
			String alt; // (혈청지오티)ALT
			String gtp; // 감마지티피
			String date; // 날짜

			Class.forName("com.mysql.cj.jdbc.Driver");

			// 테이블에 내용이 없다면 내용 추가
			try {
				ResultSet result = stmt.executeQuery("SELECT systolicBloodPre FROM userData");
				result.next();
				String str = result.getString("systolicBloodPre") + "\t";
			} catch (Exception e) { // 내용이 없다면 에러 발생하고 catch로 잡아서 테이블에 내용 추가
				while ((readData = br.readLine()) != null) {
					st = new StringTokenizer(readData, "\t"); // " "기준으로 문자열 자르기
					systolicBloodPre = st.nextToken(); // txt 파일 문자열로 읽어와서 잘린부분
					category.systolicBloodPre = Float.valueOf(systolicBloodPre); // 잘린부분을 double형으로 변환
					diastolicBloodPre = st.nextToken();
					category.diastolicBloodPre = Float.valueOf(diastolicBloodPre);
					preMealBloodSugar = st.nextToken();
					category.preMealBloodSugar = Float.valueOf(preMealBloodSugar);
					totalCholesterol = st.nextToken();
					category.totalCholesterol = Float.valueOf(totalCholesterol);
					triglyceride = st.nextToken();
					category.triglyceride = Float.valueOf(triglyceride);
					hdl = st.nextToken();
					category.hdl = Float.valueOf(hdl);
					ldl = st.nextToken();
					category.ldl = Float.valueOf(ldl);
					hemoglobin = st.nextToken();
					category.hemoglobin = Float.valueOf(hemoglobin);
					proteinInUrine = st.nextToken();
					category.proteinInUrine = Float.valueOf(proteinInUrine);
					serumCreatine = st.nextToken();
					category.serumCreatine = Float.valueOf(serumCreatine);
					ast = st.nextToken();
					category.ast = Float.valueOf(ast);
					alt = st.nextToken();
					category.alt = Float.valueOf(alt);
					gtp = st.nextToken();
					category.gtp = Float.valueOf(gtp);
					commend = "INSERT INTO healthdata VALUES ('" + category.systolicBloodPre + "', '"
							+ category.diastolicBloodPre + "', '" + category.preMealBloodSugar + "', '"
							+ category.totalCholesterol + "', '" + category.triglyceride + "', '" + category.hdl
							+ "', '" + category.ldl + "', '" + category.hemoglobin + "', '" + category.proteinInUrine
							+ "', '" + category.serumCreatine + "', '" + category.ast + "', '" + category.alt
							+ "', '" + category.gtp + "')"; // user 테이블에 id와 password 추가 sql문
					stmt.executeUpdate(commend);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
