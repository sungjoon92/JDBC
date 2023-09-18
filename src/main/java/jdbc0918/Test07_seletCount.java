package jdbc0918;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test07_seletCount {

	public static void main(String[] args) {
		// sungjuk 테이블의 전체 행의 갯수 출력

		Connection con = null;
		PreparedStatement pstmt = null;
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
			sql.append(" SELECT COUNT(*) AS cnt ");
			sql.append(" FROM sungjuk ");

			pstmt = con.prepareStatement(sql.toString());

			rs = pstmt.executeQuery(); // select

			if (rs.next()) { // cursor가 있는지?
				System.out.println("자료있음~~");
			} else {
				System.out.println("자료없음~~");
			} // if end

			// 1) 칼럼 순서 접근
			// -> select 칼럼1, 칼럼2, 칼럼3 ~~
			// 0> 자료형을 일치하면서 가져온다

			System.out.println("전체 행 갯수 : " + rs.getInt(1));

			// 2) 칼럼명으로 접근

			System.out.println("전체 행 갯수 : " + rs.getInt("cnt"));

		} catch (Exception e) {
			System.out.println("오라클 JDBC 조회 실패 : " + e);
		}finally {
			try {
				if(rs!=null) {rs.close(); }
			}catch (Exception e2) {}
			try {
				if(pstmt!=null) {rs.close(); }
			}catch (Exception e2) {}
			try {
				if(con!=null) {rs.close(); }
			}catch (Exception e2) {}
			
		}// end
	}// main end
}// class end
