import java.io.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.StringTokenizer;

public class userDataReader {
	Mysql sql = new Mysql();
	
	void readuserData(userData udt, String id) {
		int i = 0;
		String select = "SELECT * FROM userData WHERE id='"+id+"' OR id='avg'";    // 사용자가 입력한 아이디에 맞는 데이터 찾거나 대한민국 평균 읽기
		sql.connectMysql();

		try {
			ResultSet rs = sql.stmt.executeQuery(select);
			while(rs.next()) {
				Category category = new Category();    // 카테고리에 맞게 추가
				category.id = rs.getString(1);
				category.systolicBloodPre = rs.getFloat(2);
				category.diastolicBloodPre = rs.getFloat(3);
				category.preMealBloodSugar = rs.getFloat(4);
				category.totalCholesterol = rs.getFloat(5);
				category.triglyceride = rs.getFloat(6);
				category.hdl = rs.getFloat(7);
				category.ldl = rs.getFloat(8);
				category.hemoglobin = rs.getFloat(9);
				category.proteinInUrine = rs.getFloat(10);
				category.serumCreatine = rs.getFloat(11);
				category.ast = rs.getFloat(12);
				category.alt = rs.getFloat(13);
				category.gtp = rs.getFloat(14);
				category.date = rs.getString(15);
				category.name = rs.getString(16);
				if(i == 0) udt.averData.add(category);    // userdata 테이블의 첫번째 데이터는 대한민국 평균
				else udt.userData.add(category);    // 첫번째 이후로는 사용자 데이터
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(i == 1) {    // 평균 데이터만 읽어왔다는 표시
			Category category = new Category();    // 해당 사용자의 데이터가 없다면 0 추가
			category.id = "Non_Data";
			category.systolicBloodPre = 0;
			category.diastolicBloodPre = 0;
			category.preMealBloodSugar = 0;
			category.totalCholesterol = 0;
			category.triglyceride = 0;
			category.hdl = 0;
			category.ldl = 0;
			category.hemoglobin = 0;
			category.proteinInUrine = 0;
			category.serumCreatine = 0;
			category.ast = 0;
			category.alt = 0;
			category.gtp = 0;
			category.date = "00.00.00";
			category.name = "Non_Data";
			udt.userData.add(category);
		}
	}
}
