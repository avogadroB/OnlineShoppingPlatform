package OnlineShopping;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.EventQueue;
import java.io.Serializable;


import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.Color;


public class AdminLoginPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField enterEmail;
	private JTextField enterPwd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLoginPage frame = new AdminLoginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminLoginPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1301, 704);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(113, 29, 176));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel email = new JLabel("Enter Admin Name:");
		email.setFont(new Font("Segoe UI", Font.BOLD, 15));
		email.setForeground(new Color(225, 210, 63));
		email.setBounds(413, 212, 153, 32);
		contentPane.add(email);
		
		JLabel pwd = new JLabel("Enter Password:");
		pwd.setForeground(new Color(225, 210, 63));
		pwd.setFont(new Font("Segoe UI", Font.BOLD, 15));
		pwd.setBounds(413, 338, 133, 17);
		contentPane.add(pwd);
		
		JButton loginButton = new JButton("Login");
		loginButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		loginButton.setBackground(new Color(94, 22, 117));
		loginButton.setForeground(new Color(225, 210, 63));
		loginButton.setBounds(543, 433, 144, 53);
		contentPane.add(loginButton);
		
		JLabel unsuccessfulLabel = new JLabel("");
		unsuccessfulLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		unsuccessfulLabel.setForeground(new Color(255, 0, 0));
		unsuccessfulLabel.setBounds(322, 540, 1048, 68);
		contentPane.add(unsuccessfulLabel);
		
		enterEmail = new JTextField();
		enterEmail.setBounds(593, 221, 253, 20);
		contentPane.add(enterEmail);
		enterEmail.setColumns(10);
		
		enterPwd = new JTextField();
		enterPwd.setColumns(10);
		enterPwd.setBounds(593, 335, 253, 20);
		contentPane.add(enterPwd);
		
		JLabel Admin = new JLabel("Admin Login");
		Admin.setForeground(new Color(225, 210, 63));
		Admin.setFont(new Font("Segoe UI", Font.BOLD, 19));
		Admin.setBounds(580, 75, 239, 68);
		contentPane.add(Admin);
		
		JButton BackButton = new JButton("<= BACK");
		BackButton.setForeground(new Color(255, 210, 63));
		BackButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		BackButton.setBackground(new Color(128, 0, 0));
		BackButton.setBounds(10, 11, 133, 53);
		BackButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				new homePage().setVisible(true);
				
			}
			
		});
		contentPane.add(BackButton);
		loginButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						ResultSet rs=null;
						ResultSet rs1=null;
						String DB_URL = "jdbc:mysql://127.0.0.1:3306/miniproj?autoReconnect=true&useSSL=false";
						String USER = "root";
						String PASS = "root";
						Connection conn=null;
						try {
							PreparedStatement pstmt=null;
							conn=DriverManager.getConnection(DB_URL, USER, PASS);
							String em = enterEmail.getText();
							String pass = enterPwd.getText();
							String query = "Select * from productadmin where paName ='"+em+"' and paPwd = '"+pass+"';";
							
							pstmt=conn.prepareStatement(query);							
							rs1=pstmt.executeQuery(query);
							if(rs1.next()) 
							{
				            	JOptionPane(this,"Success");
				            	new AdminPage().setVisible(true);
				            }
				            else {
				            	unsuccessfulLabel.setText("Login Unsuccessful, Please try again!!");
				            	enterEmail.setText("");
				            	enterPwd.setText("");
				            }
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}

					private void JOptionPane(ActionListener actionListener, String string) {
						// TODO Auto-generated method stub
					}
			
		});
	}
}
