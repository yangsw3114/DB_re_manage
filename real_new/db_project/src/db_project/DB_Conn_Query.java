package db_project;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

class DB_Conn_Query {
	   public boolean Employee_Guest = false;
	   public int buildingNum;
	   private String EmployeeNum;
	   private int menuNum;
	   Connection con = null;
	   public DB_Conn_Query( ) {
	     String url = "jdbc:oracle:thin:@localhost:1521:XE";
	     String id = "STORE";      String password = "1234";
	     try {   Class.forName("oracle.jdbc.driver.OracleDriver");
	        System.out.println("드라이버 적재 성공");
	        con = DriverManager.getConnection(url, id, password);
	        System.out.println("DB 연결 성공");
	     } catch (ClassNotFoundException e) {         System.out.println("No Driver.");    }
	       catch (SQLException e) {         System.out.println("Connection Fail");      }
	   }
	   public void end_db() {
		   try {
			   con.close(); 
		   }
		   catch (SQLException e) { e.printStackTrace(); }
	   }

	   public boolean id_check(String id) //id 체크
	   {
		   boolean check_id = true;
		   String dpay = "SELECT * FROM 디페이";

		   try {
			   PreparedStatement  stmt = con.prepareStatement(dpay);
			   ResultSet rs = stmt.executeQuery(dpay);

			   while (rs.next()) {
				   String IdCheck =rs.getString(1);
				   String number =rs.getString(2);
				   if( IdCheck.equals(id) ) {
					   check_id = false;
				   }
			   }

			   
         stmt.close();    rs.close();     
		   }catch (SQLException e) { e.printStackTrace(); }
		   return check_id;
		   
	   }
	   
	   public boolean login_check(String id, String password) //로그인 체크 
	   {
		   boolean check_login = false;
		   String dpay = "SELECT 모바일ID,비밀번호 FROM 디페이";
		   try {
			   Statement stmt = con.createStatement();
			   ResultSet rs = stmt.executeQuery(dpay);
			   while (rs.next()) {
				   System.out.println(rs.getString("모바일ID")+rs.getString("비밀번호"));
				   if( rs.getString("모바일ID").equals(id) && rs.getString("비밀번호").equals(password) ) {
					   check_login = true;
				   }
			   }
			   
			   //직원 아이디 비밀번호 일치 확인
			   if( check_login == false) {
				   String employee = "SELECT 직원번호, 주민등록번호, 건물번호 FROM 직원 WHERE 직책 = '영양사'";
				   Statement stmt2 = con.createStatement();
				   ResultSet rs2 = stmt.executeQuery(employee);
				   while (rs2.next()) {
					   String RRnumber = rs2.getString("주민등록번호");
					   String[] birthday = RRnumber.split("-");
					   if( rs2.getString("직원번호").equals(id) && birthday[0].equals(password) ) {
						   check_login = true;
						   Employee_Guest = true;
						   buildingNum = rs2.getInt("건물번호");
					   }
				   }
				   stmt.close();    rs.close();
				   stmt2.close();    rs2.close();
			   }
             }
		   catch (SQLException e) { e.printStackTrace(); }
		   return check_login;
		   
	   }
	   
	   public String BuildName(int buildnum) {
		   String Query = "SELECT 식당명 FROM 식당 WHERE 건물번호 = ?";
		   String Bname = null;
		   try {
			   PreparedStatement stmt = con.prepareStatement(Query);
			   stmt.setInt(1, buildnum);
			   stmt.executeUpdate();
			   ResultSet rs = stmt.executeQuery(Query);
			   while (rs.next()) {
				   
				   Bname = rs.getString("식당명");
				   }	
			   stmt.close();    rs.close();
		   }
		   catch  (SQLException e) { e.printStackTrace(); }
		   return Bname;
	   }
	   public List  Ranking() //로그인 체크 
	   {
		   String Query = "SELECT 메뉴번호 FROM (SELECT 메뉴번호, COUNT(수량) FROM 주문 GROUP BY 메뉴번호 ORDER BY COUNT(수량) DESC) WHERE ROWNUM <=5";
		   ArrayList<String> list = new ArrayList<String>();
		   try {
			   Statement stmt = con.createStatement();
			   ResultSet rs = stmt.executeQuery(Query);
			   while (rs.next()) {
				   
				   list.add(rs.getString("메뉴번호"));
				   System.out.println(rs.getString("메뉴번호"));
				   }			   			   
			   String Query2 = "SELECT 메뉴명 FROM 메뉴 WHERE 메뉴번호 = ?";
			   PreparedStatement stmt2 = con.prepareStatement(Query2);
			   ResultSet rs2 = null;
			   for(int i=0; i<list.size(); i++) {
				   String num = list.get(i);
				   stmt2.setString(1, num);
				   stmt2.executeUpdate();
				   
				   rs2 = stmt2.executeQuery(Query2);
				   rs2.next();
				   list.set(i, rs2.getString("메뉴명"));
				   
			   }
			   stmt.close();    rs.close();
			   stmt2.close();    rs2.close();
			   }
		   catch (SQLException e) { e.printStackTrace(); }	
		   return list;
	   }
	   
	   public void charge_or_pay (String year, String month, String mem_id) { //총사용금액 
	        
			   String list = "SELECT 내역코드,사용내역,금액,날짜,사용장소 FROM 내역 WHERE substr(날짜, 0,2) = '" +year.substring(2) +"' and substr(날짜, 4,2) = '" + month +"'  and 내역.모바일ID ='"+ mem_id+"'";
			   
			   try {
				   PreparedStatement stmt = con.prepareStatement(list);
				   ResultSet rs = stmt.executeQuery(list);
				   while (rs.next()) {
					   pay_list.pay_model.addRow(new Object[] {rs.getString("내역코드"), rs.getString("사용내역"), rs.getInt("금액"),  rs.getString("날짜"),  rs.getString("사용장소")});
				   }	   
				  stmt.close();    rs.close();     
			   }catch (SQLException e) { e.printStackTrace(); }
	   		}
	   
	     
	   public void menu_receipt (String year, String month, String mem_id) { //날짜별 dpay사용내역


	        try {


		        CallableStatement cstmt = con.prepareCall("{call pay_list(?, ?, ?, ?, ?)}");
	            System.out.println(year+ month+mem_id);
	            // INOUT 파라미터값 매핑
	            cstmt.setString(1, mem_id);
	            cstmt.setString(2, year.substring(2));
	            cstmt.setString(3, month);
	            //cstmt.setInt(4, charge);
	            //cstmt.setInt(5, spend);
	            
	            cstmt.registerOutParameter(4, java.sql.Types.VARCHAR);
	            cstmt.registerOutParameter(5, java.sql.Types.VARCHAR);

	            
	            // 프로시저 실행
	            cstmt.executeUpdate();
		   		//System.out.println(cstmt.getString(4)+cstmt.getString(5));
	            	            
			    //pay_list.pay_model.addRow(new Object[] {cstmt.getString(4),cstmt.getString(5)});
			    pay_list.total_charge = cstmt.getString(4);
			    pay_list.total_spend = cstmt.getString(5);
			    

	        }
	        	catch (SQLException e) { e.printStackTrace(); }
	   		}
	   	   	   
	   
	   public void dpay_list (String mem_id) { //dpay 사용내역
		   
		   String list = "SELECT 내역코드,사용내역,금액,날짜,사용장소 FROM 내역 WHERE 내역.모바일ID ='"+ mem_id+"'";
		   
		   try {
			   PreparedStatement stmt = con.prepareStatement(list);
			   ResultSet rs = stmt.executeQuery(list);
			   while (rs.next()) {
				   dpay.model.addRow(new Object[] {rs.getString("내역코드"), rs.getString("사용내역"), rs.getInt("금액"),  rs.getString("날짜"),  rs.getString("사용장소")});
			   }	   
			  stmt.close();    rs.close();     
		   }catch (SQLException e) { e.printStackTrace(); }
	   }
	   

	   public void menu_List() {
		   String list = "SELECT 메뉴명, 가격, 메뉴분류명  FROM 메뉴";
		   
		   try {
			   PreparedStatement stmt = con.prepareStatement(list);
			   ResultSet rs = stmt.executeQuery(list);
			   while (rs.next()) {
				   menu_control.model.addRow(new Object[] {rs.getString("메뉴명"), rs.getInt("가격"), rs.getString("메뉴분류명")});
			   }	   
			  stmt.close();    rs.close();     
		   }catch (SQLException e) { e.printStackTrace(); }
	   }

	   
	   public void menu_insert(String name, int price, String kind) {
		   String Query = "INSERT INTO 메뉴 values(EX_SEQ.NEXTVAL,?,?,?)";
		   try {
			   PreparedStatement stmt = con.prepareStatement(Query);
			   stmt.setString(1, name);
			   stmt.setInt(2, price);
			   stmt.setString(3, kind);
			   stmt.executeUpdate();
			   
			   stmt.close();
		   }
		   catch(Exception e) {
			   e.printStackTrace();
		   }
	   }
	   	   
	   public void menu_update(String newname, int price, String kind, String oldname) {
		   //where로 OLD:이름을 찾으면 SET에는 NEW:이름을 넣어야함
		   String Query = "Update 메뉴 set 메뉴명=?, 가격=?, 메뉴분류명=? WHERE 메뉴명 = ?";
		   try {
			   PreparedStatement stmt = con.prepareStatement(Query);
			   stmt.setString(1, newname);
			   stmt.setInt(2, price);
			   stmt.setString(3, kind);
			   stmt.setString(4, oldname);
			   stmt.executeUpdate();
			   
			   stmt.close();
		   }
		   catch(Exception e) {
			   e.printStackTrace();
		   }
	   }
   
	   public void menu_delete(String name) {
		   String Query = "DELETE FROM 메뉴 WHERE 메뉴명 = ?";
		   try {
			   PreparedStatement stmt = con.prepareStatement(Query);
			   stmt.setString(1, name);
			   stmt.executeUpdate();
			   
			   stmt.close();
		   }
		   catch(Exception e) {
			   e.printStackTrace();
		   }
	   }

	   public void employee_List(int buildNum) {
		   String Query = "SELECT 이름, 나이, 전화번호, 주민등록번호, 직책  FROM 직원 WHERE 건물번호 = ?";
		   
		   try {
			   PreparedStatement stmt = con.prepareStatement(Query);
			   stmt.setInt(1, buildNum);
			   stmt.executeUpdate();
			   
			   ResultSet rs = stmt.executeQuery(Query);
			   while (rs.next()) {
				   employee_manage.model.addRow(new Object[] {rs.getString("이름"), rs.getInt("나이"), rs.getString("전화번호"), rs.getString("주민등록번호"), rs.getString("직책")});
			   }	   
			  stmt.close();    rs.close();     
		   }catch (SQLException e) { e.printStackTrace(); }
	   }
	   
	   public void employee_insert(String name, int age, String Tel, String RRnum, String position, int buildNum) {
		   
		   String employee = "SELECT 직원번호 FROM 직원";
		   Statement stmt2;
		   try {
				stmt2 = con.createStatement();
				ResultSet rs2 = stmt2.executeQuery(employee);
				while (rs2.next()) {
					EmployeeNum = rs2.getString("직원번호");
					}
				stmt2.close();    rs2.close();  
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		   int EN = Integer.parseInt(EmployeeNum)+1;
		   EmployeeNum = Integer.toString(EN);
		   String Query = "INSERT INTO 직원 values(?,?,?,?,?,?,?)";
		   try {
			   PreparedStatement stmt = con.prepareStatement(Query);
			   stmt.setString(1, EmployeeNum);
			   stmt.setString(2, name);
			   stmt.setInt(3, age);
			   stmt.setString(4, Tel);
			   stmt.setString(5, RRnum);
			   stmt.setString(6, position);
			   stmt.setInt(7, buildNum);
			   
			   stmt.executeUpdate();
			   
			   stmt.close();
		   }
		   catch(Exception e) {
			   e.printStackTrace();
		   }
	   }
	  
	   public void employee_update(String newname, int age, String Tel, String RRnum, String position, String oldname) {
		   //where로 OLD:이름을 찾으면 SET에는 NEW:이름을 넣어야함
		   String Query = "Update 직원 set 이름=?, 나이=?, 전화번호=?, 주민등록번호=?, 직책=? WHERE 이름 = ?";
		   try {
			   PreparedStatement stmt = con.prepareStatement(Query);
			   stmt.setString(1, newname);
			   stmt.setInt(2, age);
			   stmt.setString(3, Tel);
			   stmt.setString(4, RRnum);
			   stmt.setString(5, position);
			   stmt.setString(6, oldname);
			   
			   stmt.executeUpdate();
			   
			   stmt.close();
		   }
		   catch(Exception e) {
			   e.printStackTrace();
		   }
	   }

	   public void employee_delete(String name) {
		   String Query = "DELETE FROM 직원 WHERE 이름 = ?";
		   try {
			   PreparedStatement stmt = con.prepareStatement(Query);
			   stmt.setString(1, name);
			   stmt.executeUpdate();
			   
			   stmt.close();
		   }
		   catch(Exception e) {
			   e.printStackTrace();
		   }
	   }
	   
	   public List menu_combobox() {
		   String Query = "SELECT 메뉴명  FROM 메뉴";
		   ArrayList<String> list = new ArrayList<String>();
		   try {
			   PreparedStatement stmt = con.prepareStatement(Query);
			   ResultSet rs = stmt.executeQuery(Query);
			   while (rs.next()) {
				   list.add(rs.getString("메뉴명"));
			   }
			   
			  stmt.close();    rs.close();   
			  
			  return list;
		   }catch (SQLException e) { e.printStackTrace(); }
		return list;
	   }

	   public List CornerName_combobox() {
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			String current = format.format(date);
			
		   String Query = "SELECT 코너명  FROM 존재 WHERE 날짜 = ?";
		   ArrayList<String> list = new ArrayList<String>();
		   try {
			   PreparedStatement stmt = con.prepareStatement(Query);
			   stmt.setString(1, current);
			   stmt.executeUpdate();
			   
			   ResultSet rs = stmt.executeQuery(Query);
			   while (rs.next()) {
				   list.add(rs.getString("코너명"));
			   }
			  stmt.close();    rs.close();   
			  
			  return list;
		   }catch (SQLException e) { e.printStackTrace(); }
		return list;
	   }
	   
	   public void WeeklyTable_member(int store_name,String day) { // 2020-12-01 추가( 고객이 보는 메뉴조회 식당별로 )
		   String Query = "SELECT 메뉴.메뉴명, 존재.코너명 FROM 메뉴,존재  WHERE 존재.건물번호 ='"+store_name+"' AND 메뉴.메뉴번호 = 존재.메뉴번호 AND 존재.날짜 = ?";
		   try {
			   PreparedStatement stmt = con.prepareStatement(Query);
			   String Day = "20/"+day;
			   stmt.setString(1, Day);
			   stmt.executeUpdate();
			   System.out.println(Day);
			   ResultSet rs = stmt.executeQuery(Query);
			   while (rs.next()) {
				   weekly_menutable_customer.model.addRow(new Object[] {rs.getString("코너명"),  rs.getString("메뉴명")});
			   }	   
			  stmt.close();    rs.close();  
		   }
		   catch(Exception e) {
			   e.printStackTrace();
		   }
	   }
	   
	   public void WeeklyTable_member_order(int store_name,String day) { // 2020-12-01 추가( 고객이 보는 메뉴주문)

		   String Query = "SELECT 메뉴.메뉴명, 존재.코너명 ,메뉴.가격 FROM 메뉴,존재  WHERE 존재.건물번호 ='"+store_name+"' AND 메뉴.메뉴번호 = 존재.메뉴번호 AND 존재.날짜 = ?";
		   try {
			   PreparedStatement stmt = con.prepareStatement(Query);
			   String Day = "20/"+day;
			   stmt.setString(1, Day);
			   stmt.executeUpdate();
			   System.out.println(Day);
			   ResultSet rs = stmt.executeQuery(Query);
			   while (rs.next()) {
				   System.out.println(rs.getString("코너명"));
				   System.out.println(rs.getString("메뉴명"));
				   store_order.model.addRow(new Object[] {rs.getString("코너명"),  rs.getString("메뉴명"),rs.getInt("가격")});
			   }	   
			  stmt.close();    rs.close();  
		   }
		   catch(Exception e) {
			   e.printStackTrace();
		   }
	   }
	   
	   public void WeeklyTable(String day, int buildNum) {
		   String Query = "SELECT 메뉴.메뉴명, 존재.코너명 FROM 메뉴,존재  WHERE 메뉴.메뉴번호 = 존재.메뉴번호 AND 존재.날짜 = ? AND 존재.건물번호 = ?";
		   try {
			   PreparedStatement stmt = con.prepareStatement(Query);
			   String Day = "20/"+day;
			   stmt.setString(1, Day);
			   stmt.setInt(2, buildNum);
			   stmt.executeUpdate();
			   
			   ResultSet rs = stmt.executeQuery(Query);
			   while (rs.next()) {
				   weekly_menutable.model.addRow(new Object[] {rs.getString("코너명"),  rs.getString("메뉴명")});
			   }	   
			  stmt.close();    rs.close();  
		   }
		   catch(Exception e) {
			   e.printStackTrace();
		   }
	   }

	   public void WeeklyTable_insert(String corner, String menuname, String selectday, int buildnum) {
		   String q = "SELECT 메뉴.메뉴번호 FROM 메뉴,존재 WHERE 메뉴.메뉴번호=존재.메뉴번호 AND 메뉴.메뉴명 = ?"; //?가 메뉴이름
		   try {
			   PreparedStatement stmt = con.prepareStatement(q);
			   stmt.setString(1, menuname);
			   stmt.executeUpdate();			   
			   ResultSet rs = stmt.executeQuery(q);
			   while (rs.next()) {
				   String menunumber = rs.getString("메뉴번호");
				   menuNum = Integer.parseInt(menunumber);
			   }
			   
			  stmt.close();    rs.close();  
		   }
		   catch(Exception e) {
			   e.printStackTrace();
		   }
	   }

	   public void WeeklyTable_delete(String name) {
		   //메뉴의 메뉴명을 통해 메뉴번호를 얻고 메뉴번호 값을 밑에  delete문에 넣어 삭제한다.
		   String Query = "DELETE FROM 존재 WHERE 메뉴번호 = (SELECT 메뉴번호 FROM 메뉴 WHERE 메뉴명 = ?)";
		   try {
			   PreparedStatement stmt = con.prepareStatement(Query);
			   stmt.setString(1, name);
			   stmt.executeUpdate();
			   
			   stmt.close();
		   }
		   catch(Exception e) {
			   e.printStackTrace();
		   }
	   }
	   public void money_charge(String id, int money, String date)
	   {
		   int a;
		   int or_num=0;
		   try
		   {
			   PreparedStatement pstmt = null;
		   		String sql = "select 잔액 from 디페이 where 모바일ID = '" + id + "'";
		   		pstmt=con.prepareStatement(sql);
		   		ResultSet rs = pstmt.executeQuery(sql);
		   		rs.next();
		   		a = rs.getInt("잔액");
		   		
		   		a += money;
		   		
		   		sql = "select * from 내역";
	            ResultSet rs4 = pstmt.executeQuery(sql);
           
	              while(rs4.next())
	              {
	            	  if(or_num < rs4.getInt("내역코드"))
	            	  {
	            		  or_num = rs4.getInt("내역코드");
	            	  }
	              }
	              
	              or_num += 1;

	              rs4.close();
		   		
		   		sql = "update 디페이 set 잔액 = '" + a + "' where 모바일ID = '" + id + "'";
		   		pstmt.executeUpdate(sql);
		   		
		   		String sql2 = "insert into 내역 values(?,?,?,?,?,?)";
	            pstmt=con.prepareStatement(sql2);
	            pstmt.setString(1, id);//학번	 
	            pstmt.setInt(2, or_num);//학번
	            pstmt.setString(3, "충전");
	            pstmt.setInt(4, money);//학번
	            pstmt.setString(5, date);//학번
	            pstmt.setString(6, "ATM");//학번
	            pstmt.executeUpdate();
		   		
		   		con.commit();	   		
		   		pstmt.close();
		   		
		   		
		   }
		   catch (SQLException e) { e.printStackTrace();}
		   	
	   		  
	   		  
	   }
	   	 public boolean money_check(String id)
	   	 {
	   		 int a;
	   		 boolean b= true;
	   		 try {
	   			PreparedStatement pstmt = null;
		   		  String sql = "select 잔액 from 디페이 where 모바일ID = '" + id + "'";
		   		  
		   		  pstmt=con.prepareStatement(sql);
		   		  ResultSet rs = pstmt.executeQuery(sql);
		   		  rs.next();
		   		  a = rs.getInt("잔액");
		   		if (a > 0)
		   		  {
		   			 b = true;
		   		  }
		   		  else
		   		  {
		   			 b = false;
		   		  }	  	
		   		return b;
	   		 }
	   		 catch (SQLException e) { e.printStackTrace(); 
	   		 return false;
	   		 }
	   		
	   	 }
	   
	      public void order(String id, String menu_name, String date, String place) // 주문 쿼리 
	      {

	         try {
	        	  int menu_num;
	              int menu_price;
	              int or_num=0;
	        	  PreparedStatement pstmt = null;
	              Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	              String sql = "select * from 주문";
	              ResultSet rs = stmt.executeQuery(sql);
             
	              while(rs.next())
	              {
	            	  if(or_num < rs.getInt("주문번호"))
	            	  {
	            		  or_num = rs.getInt("주문번호");
	            	  }
	              }
	              
	              or_num += 1;
	              

	              rs.close();
	              
	              sql = "select * from 메뉴 where 메뉴명 = '" + menu_name + "'";
	              ResultSet rs1 = stmt.executeQuery(sql);
	              rs1.next();
	              menu_num = rs1.getInt("메뉴번호");
	              menu_price = rs1.getInt("가격");
	             

	              rs1.close();
	              
	              sql = "select * from 고객 where 모바일ID = '" + id + "'";
	              ResultSet rs2 = stmt.executeQuery(sql);
	              rs2 = stmt.executeQuery(sql);
	              rs2.next();
	              String std_num = rs2.getString("학번");
	              rs2.close();
	              
	              String dpay_signup = "insert into 주문 values(?,?,?,?,?)";
	              pstmt=con.prepareStatement(dpay_signup);
	              pstmt.setInt(1,or_num);//메뉴번호
	              pstmt.setString(2, std_num);//학번
	              pstmt.setInt(3,menu_num);//메뉴번호
	              pstmt.setInt(4, 1);//수량
	              pstmt.setString(5, date);//날짜
	              pstmt.executeUpdate();
	         
	              sql = "select * from 내역";
	              ResultSet rs4 = stmt.executeQuery(sql);
             
	              while(rs4.next())
	              {
	            	  if(or_num < rs4.getInt("내역코드"))
	            	  {
	            		  or_num = rs4.getInt("내역코드");
	            	  }
	              }
	              
	              or_num += 1;

	              rs4.close();
	              
	              String sql2 = "insert into 내역 values(?,?,?,?,?,?)";
	              pstmt=con.prepareStatement(sql2);
	              pstmt.setString(1, id);//학번	 
	              pstmt.setInt(2, or_num);//학번
	              pstmt.setString(3, "구매");
	              pstmt.setInt(4, -menu_price);//학번
	              pstmt.setString(5, date);//학번
	              pstmt.setString(6, place);//학번
	              pstmt.executeUpdate();
	              
	              con.commit();
	              stmt.close();
	              pstmt.close(); 
	              
	         }catch (SQLException e) { e.printStackTrace(); }
	         
	      }
	      
	   
}