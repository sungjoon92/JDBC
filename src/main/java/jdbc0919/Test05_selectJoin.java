package jdbc0919;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test05_selectJoin {

	public static void main(String[] args) {
	   
		String hakno = "g1001";
		//문제) 학번 g1001이 수강신청한 과목을 과목코드별로 조회하시오
		/*     
        g1001      d001     HTML
        g1001      p001     JAVA
        g1001      p002     Oracle
        g1001      p003     JSP
        g1001      p004     Python
        g1001      p005     AJAX      
		 */

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
			sql.append(" select ST.hakno, SU.gcode, gname ");
			sql.append(" from tb_student ST join tb_sugang SU ");
			sql.append(" on ST.hakno = SU.hakno join tb_gwamok GW ");
			sql.append(" on SU.gcode = GW.gcode ");
			sql.append(" where ST.hakno = ? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, hakno);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("자료있음~~");
				do {
					System.out.print(rs.getString("hakno") + " ");
					System.out.print(rs.getString("gcode") + " ");
					System.out.print(rs.getString("gname") + " ");
					System.out.println();
				} while (rs.next());

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
			} catch (Exception e2) {}
			try {
				if (pstmt != null) {
					rs.close();
				}
			} catch (Exception e2) {}
			try {
				if (con != null) {
					rs.close();
				}
			} catch (Exception e2) {}

		} // try ~ catch end

	}// main end
}// class end
