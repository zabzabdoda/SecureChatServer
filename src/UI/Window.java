package UI;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.border.TitledBorder;

import Backend.Server;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class Window extends JFrame implements WindowListener{
	
	private Server server;
	
	public Window(String name, char[] password, int port) {
		this.addWindowListener(this);
		getContentPane().setLayout(null);
		this.setSize(440, 500);
		this.setTitle("Secure Chat Server");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Close Server");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("Edit");
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("Change");
		mnNewMenu_1.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Server Name");
		mnNewMenu_2.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Password");
		mnNewMenu_2.add(mntmNewMenuItem_3);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Chat", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, null, null));
		panel.setBounds(10, 45, 195, 333);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 175, 299);

		
		JTextArea textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBounds(10, 26, 175, 296);
		scrollPane.setViewportView(textArea);
		panel.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "User List", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, null, null));
		panel_1.setBounds(229, 45, 195, 333);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JList<String> list = new JList<String>();
		list.setBounds(10, 26, 175, 296);
		DefaultListModel<String> lm = new DefaultListModel<String>();
		list.setModel(lm);
		panel_1.add(list);
		
		JLabel lblNewLabel = new JLabel(name);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 0, 414, 34);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.append("Test\n");
			}
		});
		btnNewButton.setBounds(10, 389, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lm.addElement("Test");
			}
		});
		btnNewButton_1.setBounds(335, 389, 89, 23);
		getContentPane().add(btnNewButton_1);
		this.setVisible(true);
		new Thread() {
			@Override
			public void run() {
				server = new Server(name, password, port, textArea);
				server.startServer();
			}
		}.start();
		
	}
	
	
	public void closeWindow() {
		server.closeServer();
		this.dispose();
	}


	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
	}


	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		closeWindow();
	}


	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
