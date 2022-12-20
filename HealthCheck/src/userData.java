import java.util.ArrayList;

public class userData {
	ArrayList<Category> userData = new ArrayList<>();    // 사용자가 입력한 아이디에 대한 건강 정보
	ArrayList<Category> averData = new ArrayList<>();    // 대한민국 평균 건강정보 
	userDataReader userDataReader = new userDataReader();
	
	String name;
	
	public void userInformation(String id) {
		userDataReader.readuserData(this, id);    // 사용자의 정보를 찾기위해 아이디 보내기
	}
}
