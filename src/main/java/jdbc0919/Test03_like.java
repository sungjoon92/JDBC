package jdbc0919;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test03_like {

	public static void main(String[] args) {
		// like 연산자
		// 이름에 '나' 문자가 있는행 조회
		String col = "uname"; // 검색칼럼 keyfield
		String word = "%나%"; // 검색어 keyword

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
			sql.append(" SELECT sno, uname, kor, eng, mat, tot, aver, addr, wdate ");
			sql.append(" FROM sungjuk ");

			// 검색어가 존재하는지
			word = word.trim();
			if (word.length() > 0) {
				// where uname like '%박%'
				String where = " WHERE " + col + " like '%" + word + "%'";
				sql.append(where);
			} // if end

			System.out.println();
			pstmt = con.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("자료있음~~");
				do {
					System.out.print(rs.getInt("sno") + " ");
					System.out.print(rs.getString("uname") + " ");
					System.out.print(rs.getInt("kor") + " ");
					System.out.print(rs.getInt("eng") + " ");
					System.out.print(rs.getInt("mat") + " ");
					System.out.print(rs.getInt("tot") + " ");
					System.out.print(rs.getInt("aver") + " ");
					System.out.print(rs.getString("addr") + " ");
					System.out.print(rs.getString("wdate") + " ");
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
