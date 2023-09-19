package jdbc0919;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test04_select_paging {

	public static void main(String[] args) {
		// 페이징
		// sungjuk 테이블에서 이름순으로 정렬후 행번호 4~6만 조회하시오

		int start = 4;
		int end = 6;

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
			sql.append(" SELECT sno, uname,  aver, addr, rnum ");
			sql.append(" FROM ( ");
			sql.append(" 		SELECT sno, uname, aver, addr, rownum as rnum ");
			sql.append(" 		FROM ( ");
			sql.append(" 				SELECT sno, uname, aver, addr ");
			sql.append("					 FROM sungjuk ");
			sql.append(" 				 ORDER BY uname ");
			sql.append(" 				) ");
			sql.append("			) ");
			sql.append(" WHERE rnum >= ? and rnum <= ? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("자료있음~~");
				do{
				System.out.print(rs.getInt("sno") + " ");
				System.out.print(rs.getString("uname") + " ");
				System.out.print(rs.getInt("aver") + " ");
				System.out.print(rs.getString("addr") + " ");
				System.out.print(rs.getString("rnum") + " ");
				System.out.println();
				}while(rs.next());
				
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
