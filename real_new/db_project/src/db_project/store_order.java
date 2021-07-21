package db_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;

public class store_order extends JFrame implements MouseListener{
   DB_Conn_Query Weekly = new DB_Conn_Query();
   private JPanel contentPane;
   private JTable menutable;
   public static DefaultTableModel model;
   public static DefaultTableModel model2;
   
   private String SelectDay;
   private int selectRow;
   private String member_id;
   private JTextField textField;
   private JTable table;

   /**
    * Launch the application.
    */
   public void mouseClicked(MouseEvent e) {
	      //선택한 셀의 행 번호계산 
	      if (e.getClickCount() ==2 )
	      {
	    	  selectRow = menutable.getSelectedRow();
	    	  model2.addRow(new Object[] {e.getSource()});
	      }
	   }
  
   @Override
   public void mousePressed(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mouseReleased(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mouseEntered(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mouseExited(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }
   /**
    * Create the frame.
    * @throws ParseException 
    */
   public store_order(int store_name2,String id) {
      setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 721, 482);
    
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);
      
      Date date = new Date();
      SimpleDateFormat format = new SimpleDateFormat("MMdd");
      
      String current = format.format(date);
      String month = current.substring(0,2);
      String day = current.substring(2,4);
      
      String header[]= {"코너명", "메뉴", "가격"};
//      String contents[][] = {{"코너1", "라면"},
//            {"코너2","치즈돈까스"},
//            {"코너3","추가버튼시 등록해둔 메뉴중 선택하게 콤보박스 생성하기"}};
//      model = new DefaultTableModel(contents, header);
      
      model = new DefaultTableModel(header, 0)
      { public boolean isCellEditable(int i, int c){ 
         return false; 
         } 
      };
        Weekly.WeeklyTable_member_order(store_name2 ,month+"/"+day);
        
      menutable = new JTable(model);
      JScrollPane sc = new JScrollPane(menutable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      sc.setLocation(12, 10);
      sc.setSize(682, 239);
      contentPane.add(sc);
      menutable.addMouseListener(new MouseAdapter() {
    	  public void mouseClicked(MouseEvent e) {
    	      //선택한 셀의 행 번호계산
    		  int a = Integer.parseInt(textField.getText());
    	      if (e.getClickCount() ==2 )
    	      {
    	    	  selectRow = menutable.getSelectedRow();
    	    	  model2.addRow(new Object[] {menutable.getValueAt(menutable.getSelectedRow(), 1), menutable.getValueAt(menutable.getSelectedRow(), 2)});
    	    	  a += Integer.parseInt(menutable.getValueAt(menutable.getSelectedRow(), 2).toString()); 
    	      }
    	      textField.setText(Integer.toString(a));
    	   }
      });
      
      JButton 뒤로가기버튼 = new JButton("BACK");
      뒤로가기버튼.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            member_menu main_menu = new member_menu(id); 
            main_menu.setVisible(true);
            dispose();
         }
      });
      JButton 주문버튼 = new JButton("주문확인");
      주문버튼.setBounds(483, 412, 97, 23);
      주문버튼.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent e)
    	  {
    		  SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
    		  Calendar time = Calendar.getInstance();
    		  String data = format.format(time.getTime());
    		  String name;
    		  if(Weekly.money_check(id) == true)
    		  {
    			  if (store_name2 == 1)
        		  {
        			  name = "정보공학관";
        		  }
        		  else if(store_name2 == 2)
        		  {
        			  name = "수덕전";
        		  }
        		  else
        		  {
        			  name = "기숙사";
        		  }
        		  for(int i = 0; i < table.getRowCount(); i++)
        		  {
        			  Weekly.order(id, table.getValueAt(i, 0).toString() , data, name);

        		  }
    			  JOptionPane.showMessageDialog(null,"주문에 성공했습니다.");
    			  member_menu MM = new member_menu(id);
    			  MM.setVisible(true);
    			  dispose();
        	  }
    		  else
    		  {
    			  JOptionPane.showMessageDialog(null,"잔액이 부족합니다.");
    		  }
    	  }
    		  
    	  
      });
      뒤로가기버튼.setBounds(597, 412, 97, 23);
      contentPane.add(뒤로가기버튼);
      contentPane.add(주문버튼);
      
      String header2[]= {"메뉴", "가격"};
      
      model2 = new DefaultTableModel(header2, 0)
      { public boolean isCellEditable(int i, int c){ 
         return false; 
         } 
      };
      table = new JTable(model2);
      JScrollPane sc2 = new JScrollPane(table);
      sc2.setBounds(12, 270, 333, 164);
      contentPane.add(sc2);
      
     
      sc2.setViewportView(table);
      
      textField = new JTextField();
      textField.setText("0");
      textField.setEditable(false);
      textField.setBounds(355, 413, 116, 21);
      contentPane.add(textField);
      textField.setColumns(10);
      
      JLabel lblNewLabel = new JLabel("\uCD1D\uAE08\uC561");
      lblNewLabel.setBounds(357, 393, 57, 15);
      contentPane.add(lblNewLabel);
//      
//        //테이블에 데이터 추가하기
//        //원본데이터를 건들지 않고 table의 매개변수인 model에 있는 데이터를 변경합니다
//        DefaultTableModel m =
//                (DefaultTableModel)table.getModel();
//        //모델에 데이터 추가 , 1번째 출에 새로운 데이터를 추가합니다
//        m.insertRow(1, new Object[]{"d1","d2","d3"});
//        //추가를 마치고 데이터 갱신을 알립니다.
//        table.updateUI();
    
        

   }
}