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
	      //������ ���� �� ��ȣ��� 
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
      
      String header[]= {"�ڳʸ�", "�޴�", "����"};
//      String contents[][] = {{"�ڳ�1", "���"},
//            {"�ڳ�2","ġ��"},
//            {"�ڳ�3","�߰���ư�� ����ص� �޴��� �����ϰ� �޺��ڽ� �����ϱ�"}};
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
    	      //������ ���� �� ��ȣ���
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
      
      JButton �ڷΰ����ư = new JButton("BACK");
      �ڷΰ����ư.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            member_menu main_menu = new member_menu(id); 
            main_menu.setVisible(true);
            dispose();
         }
      });
      JButton �ֹ���ư = new JButton("�ֹ�Ȯ��");
      �ֹ���ư.setBounds(483, 412, 97, 23);
      �ֹ���ư.addActionListener(new ActionListener() {
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
        			  name = "�������а�";
        		  }
        		  else if(store_name2 == 2)
        		  {
        			  name = "������";
        		  }
        		  else
        		  {
        			  name = "�����";
        		  }
        		  for(int i = 0; i < table.getRowCount(); i++)
        		  {
        			  Weekly.order(id, table.getValueAt(i, 0).toString() , data, name);

        		  }
    			  JOptionPane.showMessageDialog(null,"�ֹ��� �����߽��ϴ�.");
    			  member_menu MM = new member_menu(id);
    			  MM.setVisible(true);
    			  dispose();
        	  }
    		  else
    		  {
    			  JOptionPane.showMessageDialog(null,"�ܾ��� �����մϴ�.");
    		  }
    	  }
    		  
    	  
      });
      �ڷΰ����ư.setBounds(597, 412, 97, 23);
      contentPane.add(�ڷΰ����ư);
      contentPane.add(�ֹ���ư);
      
      String header2[]= {"�޴�", "����"};
      
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
//        //���̺� ������ �߰��ϱ�
//        //���������͸� �ǵ��� �ʰ� table�� �Ű������� model�� �ִ� �����͸� �����մϴ�
//        DefaultTableModel m =
//                (DefaultTableModel)table.getModel();
//        //�𵨿� ������ �߰� , 1��° �⿡ ���ο� �����͸� �߰��մϴ�
//        m.insertRow(1, new Object[]{"d1","d2","d3"});
//        //�߰��� ��ġ�� ������ ������ �˸��ϴ�.
//        table.updateUI();
    
        

   }
}