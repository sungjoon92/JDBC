package jdbc0918;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Test04_delete {

	public static void main(String[] args) {
		// sungjuk 테이블 행 삭제 연습

		try {

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "1234";
			String driver = "oracle.jdbc.driver.OracleDriver"; // ojdbc8.jar
			Class.forName(driver);

			// 3) 오라클 DB 서버 연결
			Connection con = DriverManager.getConnection(url, user, password);

			System.out.println("오라클  JDBC 서버 연결 성공!!: ");
			StringBuilder sql = new StringBuilder();
			sql.append(" DELETE sungjuk WHERE sno = 2");

			PreparedStatement pstmt = con.prepareStatement(sql.toString());

			int cnt = pstmt.executeUpdate(); // 실행 했을떄 행의 갯수가 변환
			if (cnt == 0) {
				System.out.println("행 삭제 실패!!");
			} else {
				System.out.println("행 삭제 성공!!");
			} // if end
 
			// 자료 반납 (순서주의)
			pstmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println("오라클 JDBC 실패 : " + e);
		} // end
	}// main end
}// class end
