package UI;

import javax.swing.JFrame;
import javax.swing.JTextField;

import Backend.Server;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

public class StartupInfo extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField textField_2;
	public StartupInfo() {
		setTitle("Secure Chat Server");
		setResizable(false);
		getContentPane().setLayout(null);
		this.getContentPane().setBackground(Color.WHITE);
		this.setSize(300, 160);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		textField = new JTextField();
		textField.setBounds(138, 11, 146, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Chatroom Name:");
		lblNewLabel.setBounds(10, 14, 118, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setBounds(10, 64, 118, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Port:");
		lblNewLabel_2.setBounds(10, 39, 118, 14);
		getContentPane().add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(138, 36, 146, 20);
		getContentPane().add(textField_1);
		
		textField_2 = new JPasswordField();
		textField_2.setColumns(10);
		textField_2.setBounds(138, 61, 146, 20);
		getContentPane().add(textField_2);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		btnNewButton.setBounds(163, 92, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window s = new Window(textField.getText(),textField_2.getPassword(),Integer.parseInt(textField_1.getText()));
				closeWindow();
			}
		});
		btnCreate.setBounds(31, 92, 89, 23);
		getContentPane().add(btnCreate);
		this.setVisible(true);
	}
	
	
	public void closeWindow() {
		this.dispose();
	}
	
}
