package jdbc0919;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test02_selectAvg {

	public static void main(String[] args) {
		// 주소가 서울인 행들의 국영수 평균값 구하기
		// 소수점은 반올림해서 둘째자리 값까지 표현

		String addr = "Seoul";
		Connection con = null; // DB를 연결하는 함수
		PreparedStatement pstmt = null; // sql문으로 변환시켜주는 함수
		ResultSet rs = null; // select문을 실행한 결과(테이블)을 저장

		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "1234";
			String driver = "oracle.jdbc.driver.OracleDriver"; // ojdbc8.jar
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);

			// 3) 오라클 DB 서버 연결
			System.out.println("오라클  JDBC 서버 연결 성공!!: ");

			StringBuilder sql = new StringBuilder();
			sql.append(" ");
			sql.append(" SELECT  ROUND(AVG(kor),2) AS avg_kor ");
			sql.append(" ,ROUND(AVG(eng),2) AS avg_eng ");
			sql.append(" ,ROUND(AVG(mat),2) AS avg_mat ");
			sql.append(" FROM sungjuk ");
			sql.append(" WHERE addr = ? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, addr);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("자료있음~~");
				System.out.println("국어평균 : " + rs.getDouble("avg_kor") + " ");
				System.out.println("영어평균 : " + rs.getDouble("avg_eng") + " ");
				System.out.println("수학평균 : " + rs.getDouble("avg_mat") + " ");
				System.out.println();

			} else {
				System.out.println("자료없음~~");
			} // if end

		} catch (Exception e) {
			System.out.println("오라클 JDBC 조회 실패 : " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {
			}
			try {
				if (pstmt != null) {
					rs.close();
				}
			} catch (Exception e2) {
			}
			try {
				if (con != null) {
					rs.close();
				}
			} catch (Exception e2) {
			}

		} // try ~ catch end

	}// main end
}// class end
