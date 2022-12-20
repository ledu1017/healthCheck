// 개발자 : 이동욱
// 개발내용 : 사용자 주변의 건강검진 검사소의 위치를 찾고, 사용자가 입력한 건강검진 정보에서 문제가 되는 부분찾기
// 개발목적 : 많은 사람들이 건강검진을 받아도 그 내용이 뭘 의미하는지 의사에게 설명들을때는 알지만 집에 와서는 기억을 잘 하지 못하는 부분이 있다.
//          그래서 건강검진 데이터를 입력하면 어떤 부분이 문제가 되는지 쉽게 알려줄 수 있는 프로그램을 제작하고자 하였고,
//          추가로 사용자 편의성을 위해 근처의 병원을 찾아주는것 까지 제작하게 되었다.
// 개발기간 : 2022.11.28 ~ 2022.12.20
// 최종 수정 : 2022.12.20 16:45

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;

public class Main {
	
	public static void main(String[] args){
		Mysql sql = new Mysql();
		
		// MySQL에 테이블이 있는지 확인하고 없다면 추가하는 sql문
		String signDataTable = "CREATE TABLE IF NOT EXISTS signdata (id varchar(20) not null, password varchar(20) not null, "
				+ "name varchar(10) not null, phnum varchar(12) not null)";
		String healthDataTable = "CREATE TABLE IF NOT EXISTS healthdata (systolicBloodPre FLOAT not null,"
				+ " diastolicBloodPre FLOAT not null, preMealBloodSugar FLOAT not null, totalCholesterol FLOAT not null,"
				+ " triglyceride FLOAT not null, hdl FLOAT not null, ldl FLOAT not null, hemoglobin FLOAT not null,"
				+ " proteinInUrine FLOAT not null, serumCreatine FLOAT not null, ast FLOAT not null, alt FLOAT not null,"
				+ " gtp FLOAT not null)";
		String userDataTable = "CREATE TABLE IF NOT EXISTS userdata (id varchar(20) not null, systolicBloodPre FLOAT not null,"
				+ " diastolicBloodPre FLOAT not null, preMealBloodSugar FLOAT not null, totalCholesterol FLOAT not null,"
				+ " triglyceride FLOAT not null, hdl FLOAT not null, ldl FLOAT not null, hemoglobin FLOAT not null,"
				+ " proteinInUrine FLOAT not null, serumCreatine FLOAT not null, ast FLOAT not null, alt FLOAT not null,"
				+ " gtp FLOAT not null, date varchar(10) not null, name varchar(10) not null)";
		
		sql.createTable(signDataTable);
		sql.createTable(healthDataTable);
		sql.createTable(userDataTable);
		
		// 맨 처음 실행할 때 txt파일을 읽어 테이블에 데이터 추가
		sql.SignDataInsert();
		sql.healthDataInsert();
		sql.userDataInsert();
		
		// 로그인 프로그램 실행
		LoginProgram login = new LoginProgram();
		login.setVisible(true);
	}
}
