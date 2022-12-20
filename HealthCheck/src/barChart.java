import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class barChart extends JPanel {
	userData udt = new userData();
	String id;
	int index, i, j = 100000;
	int chartType;

	barChart(String id, int chartType, int index) {
		this.id = id;
		this.chartType = chartType;
		this.index = index;
	}

	public void paint(Graphics g) {
		udt.userInformation(id);
		
		i = udt.userData.size()-1;    // 작은 그래프 그릴때 사용
		j = udt.userData.size()-1-index;    // 막대그래프 전체화면 볼 때 사용
		
		g.clearRect(0, 0, getWidth(), getHeight());
		g.drawLine(20, getHeight() - 29, getWidth() - 11, getHeight() - 29);    // y축
		g.drawLine(20, 20, 20, getHeight() - 29);    // x축

		if (chartType == 0) {    // 10, 20 등 가로줄 그리기
			g.setFont(new Font("굴림", Font.PLAIN, 10));
			for (int cnt = 1; cnt < 11; cnt++) {
				g.drawString(cnt * 10 + "", 0, 255 - 22 * cnt);
				g.drawLine(20, 250 - 22 * cnt, 630, 250 - 22 * cnt);
			}
			g.drawString("수축기혈압  이완기혈압  식전혈당  총콜레스테롤  트리글리세라이드   HDL   LDL   혈색소   요단백  혈청그레아티닌  AST   ALT   GTP", 10,
					265);    // 그래프 하단에 들어갈 목록

			// 그래프 그리기 fillRect(x좌표, y좌표, 사각형 가로길이, 사각형 세로길이)를 그래프에 맞게 작성
			if (udt.userData.get(0).systolicBloodPre > 0) {    // 작은 그래프
				g.setColor(Color.BLUE);
				g.fillRect(30, (int) (250 - (int) (udt.userData.get(i).systolicBloodPre / 10) * 2.2), 10,
						(int) ((udt.userData.get(i).systolicBloodPre / 10) * 2.2));
				g.setColor(Color.RED);
				g.fillRect(40, (int) (250 - (int) (udt.averData.get(0).systolicBloodPre / 10) * 2.2), 10,
						(int) ((udt.averData.get(0).systolicBloodPre / 10) * 2.2));
			}
			if (udt.userData.get(0).diastolicBloodPre > 0) {
				g.setColor(Color.BLUE);
				g.fillRect(85, (int) (250 - (int) udt.userData.get(i).diastolicBloodPre * 2.2), 10,
						(int) (udt.userData.get(i).diastolicBloodPre * 2.2));
				g.setColor(Color.RED);
				g.fillRect(95, (int) (250 - (int) udt.averData.get(0).diastolicBloodPre * 2.2), 10,
						(int) (udt.averData.get(0).diastolicBloodPre * 2.2));
			}
			if (udt.userData.get(0).preMealBloodSugar > 100) {
				g.setColor(Color.BLUE);
				g.fillRect(135, (int) (250 - (int) (udt.userData.get(i).preMealBloodSugar / 10) * 2.2), 10,
						(int) ((udt.userData.get(i).preMealBloodSugar / 10) * 2.2));
				g.setColor(Color.RED);
				g.fillRect(145, (int) (250 - (int) (udt.averData.get(0).preMealBloodSugar / 10) * 2.2), 10,
						(int) ((udt.averData.get(0).preMealBloodSugar / 10) * 2.2));
			} else {
				g.setColor(Color.BLUE);
				g.fillRect(135, (int) (250 - (int) udt.userData.get(i).preMealBloodSugar * 2.2), 10,
						(int) (udt.userData.get(i).preMealBloodSugar * 2.2));
				g.setColor(Color.RED);
				g.fillRect(145, (int) (250 - (int) udt.averData.get(0).preMealBloodSugar * 2.2), 10,
						(int) (udt.averData.get(0).preMealBloodSugar * 2.2));
			}
			if (udt.userData.get(0).totalCholesterol > 0) {
				g.setColor(Color.BLUE);
				g.fillRect(195, (int) (250 - (int) (udt.userData.get(i).totalCholesterol / 10) * 2.2), 10,
						(int) ((udt.userData.get(i).totalCholesterol / 10) * 2.2));
				g.setColor(Color.RED);
				g.fillRect(205, (int) (250 - (int) (udt.averData.get(0).totalCholesterol / 10) * 2.2), 10,
						(int) ((udt.averData.get(0).totalCholesterol / 10) * 2.2));
			}
			if (udt.userData.get(0).triglyceride > 100) {
				g.setColor(Color.BLUE);
				g.fillRect(260, (int) (250 - (int) (udt.userData.get(i).triglyceride / 10) * 2.2), 10,
						(int) ((udt.userData.get(i).triglyceride / 10) * 2.2));
				g.setColor(Color.RED);
				g.fillRect(270, (int) (250 - (int) (udt.averData.get(0).triglyceride / 10) * 2.2), 10,
						(int) ((udt.averData.get(0).triglyceride / 10) * 2.2));
			} else {
				g.setColor(Color.BLUE);
				g.fillRect(260, (int) (250 - (int) udt.userData.get(i).triglyceride * 2.2), 10,
						(int) (udt.userData.get(i).triglyceride * 2.2));
				g.setColor(Color.RED);
				g.fillRect(270, (int) (250 - (int) udt.averData.get(0).triglyceride * 2.2), 10,
						(int) (udt.averData.get(0).triglyceride * 2.2));
			}
			if (udt.userData.get(0).hdl > 0) {
				g.setColor(Color.BLUE);
				g.fillRect(333, (int) (250 - (int) udt.userData.get(i).hdl * 2.2), 10,
						(int) (udt.userData.get(i).hdl * 2.2));
				g.setColor(Color.RED);
				g.fillRect(343, (int) (250 - (int) udt.averData.get(0).hdl * 2.2), 10,
						(int) (udt.averData.get(0).hdl * 2.2));
			}
			if (udt.userData.get(0).ldl > 100) {
				g.setColor(Color.BLUE);
				g.fillRect(363, (int) (250 - (int) (udt.userData.get(i).ldl / 10) * 2.2), 10,
						(int) ((udt.userData.get(i).ldl / 10) * 2.2));
				g.setColor(Color.RED);
				g.fillRect(373, (int) (250 - (int) (udt.averData.get(0).ldl / 10) * 2.2), 10,
						(int) ((udt.averData.get(0).ldl / 10) * 2.2));
			} else {
				g.setColor(Color.BLUE);
				g.fillRect(363, (int) (250 - (int) udt.userData.get(i).ldl * 2.2), 10,
						(int) (udt.userData.get(i).ldl * 2.2));
				g.setColor(Color.RED);
				g.fillRect(373, (int) (250 - (int) udt.averData.get(0).ldl * 2.2), 10,
						(int) (udt.averData.get(0).ldl * 2.2));
			}
			if (udt.userData.get(0).hemoglobin > 0) {
				g.setColor(Color.BLUE);
				g.fillRect(394, (int) (250 - (int) udt.userData.get(i).hemoglobin * 2.2), 10,
						(int) (udt.userData.get(i).hemoglobin * 2.2));
				g.setColor(Color.RED);
				g.fillRect(404, (int) (250 - (int) udt.averData.get(0).hemoglobin * 2.2), 10,
						(int) (udt.averData.get(0).hemoglobin * 2.2));
			}
			if (udt.userData.get(0).proteinInUrine > 0) {
				g.setColor(Color.BLUE);
				g.fillRect(440, (int) (250 - (int) udt.userData.get(i).proteinInUrine * 2.2), 10,
						(int) (udt.userData.get(i).proteinInUrine * 2.2));
				g.setColor(Color.RED);
				g.fillRect(450, (int) (250 - (int) udt.averData.get(0).proteinInUrine * 2.2), 10,
						(int) (udt.averData.get(0).proteinInUrine * 2.2));
			}
			if (udt.userData.get(0).ast > 0) {
				g.setColor(Color.BLUE);
				g.fillRect(550, (int) (250 - (int) udt.userData.get(i).ast * 2.2), 10,
						(int) (udt.userData.get(i).ast * 2.2));
				g.setColor(Color.RED);
				g.fillRect(560, (int) (250 - (int) udt.averData.get(0).ast * 2.2), 10,
						(int) (udt.averData.get(0).ast * 2.2));
			}
			if (udt.userData.get(0).alt > 0) {
				g.setColor(Color.BLUE);
				g.fillRect(580, (int) (250 - (int) udt.userData.get(i).alt * 2.2), 10,
						(int) (udt.userData.get(i).alt * 2.2));
				g.setColor(Color.RED);
				g.fillRect(590, (int) (250 - (int) udt.averData.get(0).alt * 2.2), 10,
						(int) (udt.averData.get(0).alt * 2.2));
			}
			if (udt.userData.get(0).gtp > 0) {
				g.setColor(Color.BLUE);
				g.fillRect(610, (int) (250 - (int) udt.userData.get(i).gtp * 2.2), 10,
						(int) (udt.userData.get(i).gtp * 2.2));
				g.setColor(Color.RED);
				g.fillRect(620, (int) (250 - (int) udt.averData.get(0).gtp * 2.2), 10,
						(int) (udt.averData.get(0).gtp * 2.2));
			}
		} else {    // 막대그래프 전체보기에서 사용
			g.setFont(new Font("굴림", Font.PLAIN, 13));
			for (int cnt = 1; cnt < 22; cnt++) {
				if(cnt % 2 == 0) g.drawString(cnt * 10 + "", 0, 444 - 20 * cnt);
				g.drawLine(20, 444 - 20 * cnt, 930, 444 - 20 * cnt);
			}
			g.drawString(
					"수축기혈압     이완기혈압     식전혈당     총콜레스테롤    트리글리세라이드     HDL"
							+ "      LDL       혈색소      요단백      혈청그레아티닌      AST     ALT     GTP",
					20, getHeight() - 14);
			// 그래프 그리기 fillRect(x좌표, y좌표, 사각형 가로길이, 사각형 세로길이)를 그래프에 맞게 작성
			if (udt.userData.get(0).systolicBloodPre > 0) {
				g.setColor(Color.BLUE);
				g.fillRect(40, (int) (444 - (int) (udt.userData.get(j).systolicBloodPre) * 2), 10,
						(int) ((udt.userData.get(j).systolicBloodPre) * 2));
				g.setColor(Color.RED);
				g.fillRect(50, (int) (444 - (int) (udt.averData.get(0).systolicBloodPre) * 2), 10,
						(int) ((udt.averData.get(0).systolicBloodPre) * 2));
			}
			if (udt.userData.get(0).diastolicBloodPre > 0) {
				g.setColor(Color.BLUE);
				g.fillRect(127, (int) (444 - (int) udt.userData.get(j).diastolicBloodPre * 2), 10,
						(int) (udt.userData.get(j).diastolicBloodPre * 2));
				g.setColor(Color.RED);
				g.fillRect(137, (int) (444 - (int) udt.averData.get(0).diastolicBloodPre * 2), 10,
						(int) (udt.averData.get(0).diastolicBloodPre * 2));
			}
			if (udt.userData.get(0).preMealBloodSugar > 0) {
				g.setColor(Color.BLUE);
				g.fillRect(207, (int) (444 - (int) (udt.userData.get(j).preMealBloodSugar) * 2), 10,
						(int) ((udt.userData.get(j).preMealBloodSugar) * 2));
				g.setColor(Color.RED);
				g.fillRect(217, (int) (444 - (int) (udt.averData.get(0).preMealBloodSugar) * 2), 10,
						(int) ((udt.averData.get(0).preMealBloodSugar) * 2));
			}
			if (udt.userData.get(0).totalCholesterol > 0) {
				g.setColor(Color.BLUE);
				g.fillRect(290, (int) (444 - (int) (udt.userData.get(j).totalCholesterol) * 2), 10,
						(int) ((udt.userData.get(j).totalCholesterol) * 2));
				g.setColor(Color.RED);
				g.fillRect(300, (int) (444 - (int) (udt.averData.get(0).totalCholesterol) * 2), 10,
						(int) ((udt.averData.get(0).totalCholesterol) * 2));
			}
			if (udt.userData.get(0).triglyceride > 0) {
				g.setColor(Color.BLUE);
				g.fillRect(390, (int) (444 - (int) (udt.userData.get(j).triglyceride) * 2), 10,
						(int) ((udt.userData.get(j).triglyceride) * 2));
				g.setColor(Color.RED);
				g.fillRect(400, (int) (444 - (int) (udt.averData.get(0).triglyceride) * 2), 10,
						(int) ((udt.averData.get(0).triglyceride) * 2));
			}
			if (udt.userData.get(0).hdl > 0) {
				g.setColor(Color.BLUE);
				g.fillRect(475, (int) (444 - (int) udt.userData.get(j).hdl * 2), 10,
						(int) (udt.userData.get(j).hdl * 2));
				g.setColor(Color.RED);
				g.fillRect(485, (int) (444 - (int) udt.averData.get(0).hdl * 2), 10,
						(int) (udt.averData.get(0).hdl * 2));
			}
			if (udt.userData.get(0).ldl > 0) {
				g.setColor(Color.BLUE);
				g.fillRect(523, (int) (444 - (int) (udt.userData.get(j).ldl) * 2), 10,
						(int) ((udt.userData.get(j).ldl) * 2));
				g.setColor(Color.RED);
				g.fillRect(533, (int) (444 - (int) (udt.averData.get(0).ldl) * 2), 10,
						(int) ((udt.averData.get(0).ldl) * 2));
			}
			if (udt.userData.get(0).hemoglobin > 0) {
				g.setColor(Color.BLUE);
				g.fillRect(580, (int) (444 - (int) udt.userData.get(j).hemoglobin * 2), 10,
						(int) (udt.userData.get(j).hemoglobin * 2));
				g.setColor(Color.RED);
				g.fillRect(590, (int) (444 - (int) udt.averData.get(0).hemoglobin * 2), 10,
						(int) (udt.averData.get(0).hemoglobin * 2));
			}
			if (udt.userData.get(0).proteinInUrine > 0) {
				g.setColor(Color.BLUE);
				g.fillRect(645, (int) (444 - (int) udt.userData.get(j).proteinInUrine * 2), 10,
						(int) (udt.userData.get(j).proteinInUrine * 2));
				g.setColor(Color.RED);
				g.fillRect(655, (int) (444 - (int) udt.averData.get(0).proteinInUrine * 2), 10,
						(int) (udt.averData.get(0).proteinInUrine * 2));
			}
			if(udt.userData.get(0).serumCreatine > 0) {
				g.setColor(Color.BLUE);
				g.fillRect(730, (int) (444 - (int) udt.userData.get(j).serumCreatine * 20), 10,
						(int) (udt.userData.get(j).serumCreatine * 2));
				g.setColor(Color.RED);
				g.fillRect(740, (int) (444 - (int) udt.averData.get(0).serumCreatine * 20), 10,
						(int) (udt.averData.get(0).serumCreatine * 2));
			}
			if (udt.userData.get(0).ast > 0) {
				g.setColor(Color.BLUE);
				g.fillRect(812, (int) (444 - (int) udt.userData.get(j).ast * 2), 10,
						(int) (udt.userData.get(j).ast * 2));
				g.setColor(Color.RED);
				g.fillRect(822, (int) (444 - (int) udt.averData.get(0).ast * 2), 10,
						(int) (udt.averData.get(0).ast * 2));
			}
			if (udt.userData.get(0).alt > 0) {
				g.setColor(Color.BLUE);
				g.fillRect(855, (int) (444 - (int) udt.userData.get(j).alt * 2), 10,
						(int) (udt.userData.get(j).alt * 2));
				g.setColor(Color.RED);
				g.fillRect(865, (int) (444 - (int) udt.averData.get(0).alt * 2), 10,
						(int) (udt.averData.get(0).alt * 2));
			}
			if (udt.userData.get(0).gtp > 0) {
				g.setColor(Color.BLUE);
				g.fillRect(900, (int) (444 - (int) udt.userData.get(j).gtp * 2), 10,
						(int) (udt.userData.get(j).gtp * 2));
				g.setColor(Color.RED);
				g.fillRect(910, (int) (444 - (int) udt.averData.get(0).gtp * 2), 10,
						(int) (udt.averData.get(0).gtp * 2));
			}
		}
	}

	public void barChartYear(int j) {
		this.j = j;    // ComboBox에서 선택한 날짜를 index로 변환
	}
}
