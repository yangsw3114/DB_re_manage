package db_project;

import java.awt.*;
import java.util.*;
import java.text.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class calendar {
	private static String year ;;
	private static String month;
	private static String days ;
	private static String str ="";
	public calendar(String mem_id) {
		Date now = new Date();
		final SpinnerDateModel model = new SpinnerDateModel(now, null, now,Calendar.DAY_OF_WEEK);
		JSpinner spinner = new JSpinner(model);
		spinner.setBounds(6, 5, 100, 22);
		final DateFormat df = new SimpleDateFormat("yyyy-MM");
		
		JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner,"yyyy-MM");
		JFormattedTextField ftf = editor.getTextField();
		ftf.setEditable(false);
		ftf.setHorizontalAlignment(JTextField.CENTER);
		
		ftf.setBackground(new Color(255, 255, 255));
		spinner.setEditor(editor);
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Date value = (Date) model.getValue();
				Date next = (Date) model.getNextValue();
				String[] a;
				if (value != null && next != null) {
						str = df.format(value);					
						a = str.split("-");
						year = a[0];//Integer.parseInt(a[0]);
						month = a[1];//Integer.parseInt(a[1]);
						//days = a[2];//Integer.parseInt(a[1]);
						
			}
			}
		});
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.add(spinner);
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(panel);
		
		JButton btnNewButton = new JButton("        O K        ");
		btnNewButton.setBounds(111, 5, 117, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pay_list a = new pay_list(year,month,mem_id); 
				a.setVisible(true);
				f.dispose();
			}
		});
		panel.add(btnNewButton);
		f.setSize(250, 100);
		f.setLocation(200, 200);
		f.setVisible(true);
	}
}