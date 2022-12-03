import java.io.File;
import java.sql.DriverManager;

public class Main {
	
	public static void main(String[] args) {
		Mysql sql = new Mysql();
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
		
		sql.SignDataInsert();
		sql.healthDataInsert();
		sql.userDataInsert();
		
		LoginProgram login = new LoginProgram();
		login.setVisible(true);
	}
}
