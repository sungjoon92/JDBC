package jdbc0919;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test01_selectAll {

	public static void main(String[] args) {
		// sunkjuk 테이블 전체 행 조회하기	
		

		Connection con = null; // DB를 연결하는 함수
		PreparedStatement pstmt = null;  //sql문으로 변환시켜주는 함수
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
			sql.append(" SELECT sno, uname, kor, eng, mat, tot, aver, addr, wdate");
			sql.append(" FROM sungjuk");
			sql.append(" ORDER BY SNO DESC ");

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
				} while (rs.next());{} // while end
				
			} else {
				System.out.println("자료없음~~");
			} // if end
		

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
			
		}//try ~ catch end
		
		
		
		
		
	}//main end
}//class end
