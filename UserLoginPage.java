package OnlineShopping;

import java.awt.EventQueue;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
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

public class UserLoginPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField enterEmail;
	private JTextField enterPwd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Connection conn=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		
		JTable table;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserLoginPage frame = new UserLoginPage();
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
	public UserLoginPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1301, 704);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(113, 29, 176));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		enterEmail = new JTextField();
		enterEmail.setFont(new Font("Tahoma", Font.PLAIN, 11));
		enterEmail.setBounds(645, 208, 382, 46);
		contentPane.add(enterEmail);
		enterEmail.setColumns(10);
		
		enterPwd = new JTextField();
		enterPwd.setFont(new Font("Tahoma", Font.PLAIN, 11));
		enterPwd.setColumns(10);
		enterPwd.setBounds(645, 322, 382, 46);
		contentPane.add(enterPwd);
		
		JLabel email = new JLabel("Enter Email:");
		email.setForeground(new Color(225, 210, 63));
		email.setFont(new Font("Segoe UI", Font.BOLD, 15));
		email.setBounds(490, 224, 105, 14);
		contentPane.add(email);
		
		JLabel pwd = new JLabel("Enter Password:");
		pwd.setForeground(new Color(225, 210, 63));
		pwd.setFont(new Font("Segoe UI", Font.BOLD, 15));
		pwd.setBounds(490, 338, 127, 14);
		contentPane.add(pwd);
		
		JButton loginButton = new JButton("Login");
		loginButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		loginButton.setForeground(new Color(225, 210, 63));
		loginButton.setBackground(new Color(94, 22, 117));
		loginButton.setBounds(612, 417, 133, 46);
		contentPane.add(loginButton);
		
		JLabel unsuccessfulLabel = new JLabel("");
		unsuccessfulLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		unsuccessfulLabel.setForeground(new Color(255, 0, 0));
		unsuccessfulLabel.setBounds(322, 540, 1048, 68);
		contentPane.add(unsuccessfulLabel);
		
		JLabel User = new JLabel("User Login");
		User.setForeground(new Color(225, 210, 63));
		User.setFont(new Font("Segoe UI", Font.BOLD, 19));
		User.setBounds(597, 70, 239, 68);
		contentPane.add(User);
		
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
							String query = "Select * from Customers where cEmail ='"+em+"' and cPwd = '"+pass+"';";
							
							pstmt=conn.prepareStatement(query);							
							rs1=pstmt.executeQuery(query);
							if(rs1.next()) 
							{
				            	JOptionPane(this,"Success");
				            	new UserPage().setVisible(true);
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
