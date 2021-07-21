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
	        System.out.println("����̹� ���� ����");
	        con = DriverManager.getConnection(url, id, password);
	        System.out.println("DB ���� ����");
	     } catch (ClassNotFoundException e) {         System.out.println("No Driver.");    }
	       catch (SQLException e) {         System.out.println("Connection Fail");      }
	   }
	   public void end_db() {
		   try {
			   con.close(); 
		   }
		   catch (SQLException e) { e.printStackTrace(); }
	   }

	   public boolean id_check(String id) //id üũ
	   {
		   boolean check_id = true;
		   String dpay = "SELECT * FROM ������";

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
	   
	   public boolean login_check(String id, String password) //�α��� üũ 
	   {
		   boolean check_login = false;
		   String dpay = "SELECT �����ID,��й�ȣ FROM ������";
		   try {
			   Statement stmt = con.createStatement();
			   ResultSet rs = stmt.executeQuery(dpay);
			   while (rs.next()) {
				   System.out.println(rs.getString("�����ID")+rs.getString("��й�ȣ"));
				   if( rs.getString("�����ID").equals(id) && rs.getString("��й�ȣ").equals(password) ) {
					   check_login = true;
				   }
			   }
			   
			   //���� ���̵� ��й�ȣ ��ġ Ȯ��
			   if( check_login == false) {
				   String employee = "SELECT ������ȣ, �ֹε�Ϲ�ȣ, �ǹ���ȣ FROM ���� WHERE ��å = '�����'";
				   Statement stmt2 = con.createStatement();
				   ResultSet rs2 = stmt.executeQuery(employee);
				   while (rs2.next()) {
					   String RRnumber = rs2.getString("�ֹε�Ϲ�ȣ");
					   String[] birthday = RRnumber.split("-");
					   if( rs2.getString("������ȣ").equals(id) && birthday[0].equals(password) ) {
						   check_login = true;
						   Employee_Guest = true;
						   buildingNum = rs2.getInt("�ǹ���ȣ");
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
		   String Query = "SELECT �Ĵ�� FROM �Ĵ� WHERE �ǹ���ȣ = ?";
		   String Bname = null;
		   try {
			   PreparedStatement stmt = con.prepareStatement(Query);
			   stmt.setInt(1, buildnum);
			   stmt.executeUpdate();
			   ResultSet rs = stmt.executeQuery(Query);
			   while (rs.next()) {
				   
				   Bname = rs.getString("�Ĵ��");
				   }	
			   stmt.close();    rs.close();
		   }
		   catch  (SQLException e) { e.printStackTrace(); }
		   return Bname;
	   }
	   public List  Ranking() //�α��� üũ 
	   {
		   String Query = "SELECT �޴���ȣ FROM (SELECT �޴���ȣ, COUNT(����) FROM �ֹ� GROUP BY �޴���ȣ ORDER BY COUNT(����) DESC) WHERE ROWNUM <=5";
		   ArrayList<String> list = new ArrayList<String>();
		   try {
			   Statement stmt = con.createStatement();
			   ResultSet rs = stmt.executeQuery(Query);
			   while (rs.next()) {
				   
				   list.add(rs.getString("�޴���ȣ"));
				   System.out.println(rs.getString("�޴���ȣ"));
				   }			   			   
			   String Query2 = "SELECT �޴��� FROM �޴� WHERE �޴���ȣ = ?";
			   PreparedStatement stmt2 = con.prepareStatement(Query2);
			   ResultSet rs2 = null;
			   for(int i=0; i<list.size(); i++) {
				   String num = list.get(i);
				   stmt2.setString(1, num);
				   stmt2.executeUpdate();
				   
				   rs2 = stmt2.executeQuery(Query2);
				   rs2.next();
				   list.set(i, rs2.getString("�޴���"));
				   
			   }
			   stmt.close();    rs.close();
			   stmt2.close();    rs2.close();
			   }
		   catch (SQLException e) { e.printStackTrace(); }	
		   return list;
	   }
	   
	   public void charge_or_pay (String year, String month, String mem_id) { //�ѻ��ݾ� 
	        
			   String list = "SELECT �����ڵ�,��볻��,�ݾ�,��¥,������ FROM ���� WHERE substr(��¥, 0,2) = '" +year.substring(2) +"' and substr(��¥, 4,2) = '" + month +"'  and ����.�����ID ='"+ mem_id+"'";
			   
			   try {
				   PreparedStatement stmt = con.prepareStatement(list);
				   ResultSet rs = stmt.executeQuery(list);
				   while (rs.next()) {
					   pay_list.pay_model.addRow(new Object[] {rs.getString("�����ڵ�"), rs.getString("��볻��"), rs.getInt("�ݾ�"),  rs.getString("��¥"),  rs.getString("������")});
				   }	   
				  stmt.close();    rs.close();     
			   }catch (SQLException e) { e.printStackTrace(); }
	   		}
	   
	     
	   public void menu_receipt (String year, String month, String mem_id) { //��¥�� dpay��볻��


	        try {


		        CallableStatement cstmt = con.prepareCall("{call pay_list(?, ?, ?, ?, ?)}");
	            System.out.println(year+ month+mem_id);
	            // INOUT �Ķ���Ͱ� ����
	            cstmt.setString(1, mem_id);
	            cstmt.setString(2, year.substring(2));
	            cstmt.setString(3, month);
	            //cstmt.setInt(4, charge);
	            //cstmt.setInt(5, spend);
	            
	            cstmt.registerOutParameter(4, java.sql.Types.VARCHAR);
	            cstmt.registerOutParameter(5, java.sql.Types.VARCHAR);

	            
	            // ���ν��� ����
	            cstmt.executeUpdate();
		   		//System.out.println(cstmt.getString(4)+cstmt.getString(5));
	            	            
			    //pay_list.pay_model.addRow(new Object[] {cstmt.getString(4),cstmt.getString(5)});
			    pay_list.total_charge = cstmt.getString(4);
			    pay_list.total_spend = cstmt.getString(5);
			    

	        }
	        	catch (SQLException e) { e.printStackTrace(); }
	   		}
	   	   	   
	   
	   public void dpay_list (String mem_id) { //dpay ��볻��
		   
		   String list = "SELECT �����ڵ�,��볻��,�ݾ�,��¥,������ FROM ���� WHERE ����.�����ID ='"+ mem_id+"'";
		   
		   try {
			   PreparedStatement stmt = con.prepareStatement(list);
			   ResultSet rs = stmt.executeQuery(list);
			   while (rs.next()) {
				   dpay.model.addRow(new Object[] {rs.getString("�����ڵ�"), rs.getString("��볻��"), rs.getInt("�ݾ�"),  rs.getString("��¥"),  rs.getString("������")});
			   }	   
			  stmt.close();    rs.close();     
		   }catch (SQLException e) { e.printStackTrace(); }
	   }
	   

	   public void menu_List() {
		   String list = "SELECT �޴���, ����, �޴��з���  FROM �޴�";
		   
		   try {
			   PreparedStatement stmt = con.prepareStatement(list);
			   ResultSet rs = stmt.executeQuery(list);
			   while (rs.next()) {
				   menu_control.model.addRow(new Object[] {rs.getString("�޴���"), rs.getInt("����"), rs.getString("�޴��з���")});
			   }	   
			  stmt.close();    rs.close();     
		   }catch (SQLException e) { e.printStackTrace(); }
	   }

	   
	   public void menu_insert(String name, int price, String kind) {
		   String Query = "INSERT INTO �޴� values(EX_SEQ.NEXTVAL,?,?,?)";
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
		   //where�� OLD:�̸��� ã���� SET���� NEW:�̸��� �־����
		   String Query = "Update �޴� set �޴���=?, ����=?, �޴��з���=? WHERE �޴��� = ?";
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
		   String Query = "DELETE FROM �޴� WHERE �޴��� = ?";
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
		   String Query = "SELECT �̸�, ����, ��ȭ��ȣ, �ֹε�Ϲ�ȣ, ��å  FROM ���� WHERE �ǹ���ȣ = ?";
		   
		   try {
			   PreparedStatement stmt = con.prepareStatement(Query);
			   stmt.setInt(1, buildNum);
			   stmt.executeUpdate();
			   
			   ResultSet rs = stmt.executeQuery(Query);
			   while (rs.next()) {
				   employee_manage.model.addRow(new Object[] {rs.getString("�̸�"), rs.getInt("����"), rs.getString("��ȭ��ȣ"), rs.getString("�ֹε�Ϲ�ȣ"), rs.getString("��å")});
			   }	   
			  stmt.close();    rs.close();     
		   }catch (SQLException e) { e.printStackTrace(); }
	   }
	   
	   public void employee_insert(String name, int age, String Tel, String RRnum, String position, int buildNum) {
		   
		   String employee = "SELECT ������ȣ FROM ����";
		   Statement stmt2;
		   try {
				stmt2 = con.createStatement();
				ResultSet rs2 = stmt2.executeQuery(employee);
				while (rs2.next()) {
					EmployeeNum = rs2.getString("������ȣ");
					}
				stmt2.close();    rs2.close();  
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		   int EN = Integer.parseInt(EmployeeNum)+1;
		   EmployeeNum = Integer.toString(EN);
		   String Query = "INSERT INTO ���� values(?,?,?,?,?,?,?)";
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
		   //where�� OLD:�̸��� ã���� SET���� NEW:�̸��� �־����
		   String Query = "Update ���� set �̸�=?, ����=?, ��ȭ��ȣ=?, �ֹε�Ϲ�ȣ=?, ��å=? WHERE �̸� = ?";
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
		   String Query = "DELETE FROM ���� WHERE �̸� = ?";
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
		   String Query = "SELECT �޴���  FROM �޴�";
		   ArrayList<String> list = new ArrayList<String>();
		   try {
			   PreparedStatement stmt = con.prepareStatement(Query);
			   ResultSet rs = stmt.executeQuery(Query);
			   while (rs.next()) {
				   list.add(rs.getString("�޴���"));
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
			
		   String Query = "SELECT �ڳʸ�  FROM ���� WHERE ��¥ = ?";
		   ArrayList<String> list = new ArrayList<String>();
		   try {
			   PreparedStatement stmt = con.prepareStatement(Query);
			   stmt.setString(1, current);
			   stmt.executeUpdate();
			   
			   ResultSet rs = stmt.executeQuery(Query);
			   while (rs.next()) {
				   list.add(rs.getString("�ڳʸ�"));
			   }
			  stmt.close();    rs.close();   
			  
			  return list;
		   }catch (SQLException e) { e.printStackTrace(); }
		return list;
	   }
	   
	   public void WeeklyTable_member(int store_name,String day) { // 2020-12-01 �߰�( ���� ���� �޴���ȸ �Ĵ纰�� )
		   String Query = "SELECT �޴�.�޴���, ����.�ڳʸ� FROM �޴�,����  WHERE ����.�ǹ���ȣ ='"+store_name+"' AND �޴�.�޴���ȣ = ����.�޴���ȣ AND ����.��¥ = ?";
		   try {
			   PreparedStatement stmt = con.prepareStatement(Query);
			   String Day = "20/"+day;
			   stmt.setString(1, Day);
			   stmt.executeUpdate();
			   System.out.println(Day);
			   ResultSet rs = stmt.executeQuery(Query);
			   while (rs.next()) {
				   weekly_menutable_customer.model.addRow(new Object[] {rs.getString("�ڳʸ�"),  rs.getString("�޴���")});
			   }	   
			  stmt.close();    rs.close();  
		   }
		   catch(Exception e) {
			   e.printStackTrace();
		   }
	   }
	   
	   public void WeeklyTable_member_order(int store_name,String day) { // 2020-12-01 �߰�( ���� ���� �޴��ֹ�)

		   String Query = "SELECT �޴�.�޴���, ����.�ڳʸ� ,�޴�.���� FROM �޴�,����  WHERE ����.�ǹ���ȣ ='"+store_name+"' AND �޴�.�޴���ȣ = ����.�޴���ȣ AND ����.��¥ = ?";
		   try {
			   PreparedStatement stmt = con.prepareStatement(Query);
			   String Day = "20/"+day;
			   stmt.setString(1, Day);
			   stmt.executeUpdate();
			   System.out.println(Day);
			   ResultSet rs = stmt.executeQuery(Query);
			   while (rs.next()) {
				   System.out.println(rs.getString("�ڳʸ�"));
				   System.out.println(rs.getString("�޴���"));
				   store_order.model.addRow(new Object[] {rs.getString("�ڳʸ�"),  rs.getString("�޴���"),rs.getInt("����")});
			   }	   
			  stmt.close();    rs.close();  
		   }
		   catch(Exception e) {
			   e.printStackTrace();
		   }
	   }
	   
	   public void WeeklyTable(String day, int buildNum) {
		   String Query = "SELECT �޴�.�޴���, ����.�ڳʸ� FROM �޴�,����  WHERE �޴�.�޴���ȣ = ����.�޴���ȣ AND ����.��¥ = ? AND ����.�ǹ���ȣ = ?";
		   try {
			   PreparedStatement stmt = con.prepareStatement(Query);
			   String Day = "20/"+day;
			   stmt.setString(1, Day);
			   stmt.setInt(2, buildNum);
			   stmt.executeUpdate();
			   
			   ResultSet rs = stmt.executeQuery(Query);
			   while (rs.next()) {
				   weekly_menutable.model.addRow(new Object[] {rs.getString("�ڳʸ�"),  rs.getString("�޴���")});
			   }	   
			  stmt.close();    rs.close();  
		   }
		   catch(Exception e) {
			   e.printStackTrace();
		   }
	   }

	   public void WeeklyTable_insert(String corner, String menuname, String selectday, int buildnum) {
		   String q = "SELECT �޴�.�޴���ȣ FROM �޴�,���� WHERE �޴�.�޴���ȣ=����.�޴���ȣ AND �޴�.�޴��� = ?"; //?�� �޴��̸�
		   try {
			   PreparedStatement stmt = con.prepareStatement(q);
			   stmt.setString(1, menuname);
			   stmt.executeUpdate();			   
			   ResultSet rs = stmt.executeQuery(q);
			   while (rs.next()) {
				   String menunumber = rs.getString("�޴���ȣ");
				   menuNum = Integer.parseInt(menunumber);
			   }
			   
			  stmt.close();    rs.close();  
		   }
		   catch(Exception e) {
			   e.printStackTrace();
		   }
	   }

	   public void WeeklyTable_delete(String name) {
		   //�޴��� �޴����� ���� �޴���ȣ�� ��� �޴���ȣ ���� �ؿ�  delete���� �־� �����Ѵ�.
		   String Query = "DELETE FROM ���� WHERE �޴���ȣ = (SELECT �޴���ȣ FROM �޴� WHERE �޴��� = ?)";
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
		   		String sql = "select �ܾ� from ������ where �����ID = '" + id + "'";
		   		pstmt=con.prepareStatement(sql);
		   		ResultSet rs = pstmt.executeQuery(sql);
		   		rs.next();
		   		a = rs.getInt("�ܾ�");
		   		
		   		a += money;
		   		
		   		sql = "select * from ����";
	            ResultSet rs4 = pstmt.executeQuery(sql);
           
	              while(rs4.next())
	              {
	            	  if(or_num < rs4.getInt("�����ڵ�"))
	            	  {
	            		  or_num = rs4.getInt("�����ڵ�");
	            	  }
	              }
	              
	              or_num += 1;

	              rs4.close();
		   		
		   		sql = "update ������ set �ܾ� = '" + a + "' where �����ID = '" + id + "'";
		   		pstmt.executeUpdate(sql);
		   		
		   		String sql2 = "insert into ���� values(?,?,?,?,?,?)";
	            pstmt=con.prepareStatement(sql2);
	            pstmt.setString(1, id);//�й�	 
	            pstmt.setInt(2, or_num);//�й�
	            pstmt.setString(3, "����");
	            pstmt.setInt(4, money);//�й�
	            pstmt.setString(5, date);//�й�
	            pstmt.setString(6, "ATM");//�й�
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
		   		  String sql = "select �ܾ� from ������ where �����ID = '" + id + "'";
		   		  
		   		  pstmt=con.prepareStatement(sql);
		   		  ResultSet rs = pstmt.executeQuery(sql);
		   		  rs.next();
		   		  a = rs.getInt("�ܾ�");
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
	   
	      public void order(String id, String menu_name, String date, String place) // �ֹ� ���� 
	      {

	         try {
	        	  int menu_num;
	              int menu_price;
	              int or_num=0;
	        	  PreparedStatement pstmt = null;
	              Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	              String sql = "select * from �ֹ�";
	              ResultSet rs = stmt.executeQuery(sql);
             
	              while(rs.next())
	              {
	            	  if(or_num < rs.getInt("�ֹ���ȣ"))
	            	  {
	            		  or_num = rs.getInt("�ֹ���ȣ");
	            	  }
	              }
	              
	              or_num += 1;
	              

	              rs.close();
	              
	              sql = "select * from �޴� where �޴��� = '" + menu_name + "'";
	              ResultSet rs1 = stmt.executeQuery(sql);
	              rs1.next();
	              menu_num = rs1.getInt("�޴���ȣ");
	              menu_price = rs1.getInt("����");
	             

	              rs1.close();
	              
	              sql = "select * from �� where �����ID = '" + id + "'";
	              ResultSet rs2 = stmt.executeQuery(sql);
	              rs2 = stmt.executeQuery(sql);
	              rs2.next();
	              String std_num = rs2.getString("�й�");
	              rs2.close();
	              
	              String dpay_signup = "insert into �ֹ� values(?,?,?,?,?)";
	              pstmt=con.prepareStatement(dpay_signup);
	              pstmt.setInt(1,or_num);//�޴���ȣ
	              pstmt.setString(2, std_num);//�й�
	              pstmt.setInt(3,menu_num);//�޴���ȣ
	              pstmt.setInt(4, 1);//����
	              pstmt.setString(5, date);//��¥
	              pstmt.executeUpdate();
	         
	              sql = "select * from ����";
	              ResultSet rs4 = stmt.executeQuery(sql);
             
	              while(rs4.next())
	              {
	            	  if(or_num < rs4.getInt("�����ڵ�"))
	            	  {
	            		  or_num = rs4.getInt("�����ڵ�");
	            	  }
	              }
	              
	              or_num += 1;

	              rs4.close();
	              
	              String sql2 = "insert into ���� values(?,?,?,?,?,?)";
	              pstmt=con.prepareStatement(sql2);
	              pstmt.setString(1, id);//�й�	 
	              pstmt.setInt(2, or_num);//�й�
	              pstmt.setString(3, "����");
	              pstmt.setInt(4, -menu_price);//�й�
	              pstmt.setString(5, date);//�й�
	              pstmt.setString(6, place);//�й�
	              pstmt.executeUpdate();
	              
	              con.commit();
	              stmt.close();
	              pstmt.close(); 
	              
	         }catch (SQLException e) { e.printStackTrace(); }
	         
	      }
	      
	   
}