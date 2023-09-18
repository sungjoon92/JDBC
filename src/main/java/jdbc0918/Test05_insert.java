package jdbc0918;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Test05_insert {

	public static void main(String[] args) {
		// sungjuk 테이블 행 추가 연습

		try {

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "1234";
			String driver = "oracle.jdbc.driver.OracleDriver"; // ojdbc8.jar
			Class.forName(driver);

			// 3) 오라클 DB 서버 연결
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("오라클  JDBC 서버 연결 성공!!: ");

			String name = "박지성";
			int kor = 99, eng = 98, mat = 100;
			String addr = "Seoul";
 
			StringBuilder sql = new StringBuilder();
			sql.append(" INSERT INTO sungjuk(sno, uname ,kor, eng, mat, addr, wdate)");
			sql.append(" VALUES (sungjuk_seq.nextval, ?, ?, ?, ?, ?, sysdate)");
			// -> ? 특정값으로 대입할 수 있는 표식

			// sql 문으로 변환해서 toString(문자열로 변환)
			PreparedStatement pstmt = con.prepareStatement(sql.toString());

			// -> ?의 갯수, 순서, 자료형이 일치해야 한다
			pstmt.setString(1, name); // 1 ->첫번째 물음표, uname칼럼
			pstmt.setInt(2, kor); // 2 ->두번째 물음표, kor칼럼
			pstmt.setInt(3, eng); // 3 ->세번째 물음표, eng칼럼
			pstmt.setInt(4, mat); // 4 ->네번째 물음표, mat칼럼
			pstmt.setString(5, addr); // 5 ->다섯번째 물음표, addr칼럼

			int cnt = pstmt.executeUpdate(); // 실행 했을떄 행의 갯수가 변환
			if (cnt == 0) {
				System.out.println("행 추가 실패!!");
			} else {
				System.out.println("행 추가 성공!!");
			} // if end

			// 자료 반납 (순서주의)
			pstmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println("오라클 JDBC 실패 : " + e);
		} // end
	}// main end
}// class end
