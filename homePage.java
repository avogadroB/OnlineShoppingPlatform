package OnlineShopping;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;

public class homePage extends JFrame {
	ImageIcon user=new ImageIcon("user image.png");
	ImageIcon admin=new ImageIcon("Admin pic.png");
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					homePage frame = new homePage();
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
	public homePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1301, 704);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(113, 29, 176));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel WelcomeLabel = new JLabel("Welcome To Electronics Shopping Center!",JLabel.CENTER);
		WelcomeLabel.setForeground(new Color(225, 210, 63));
		WelcomeLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 43));
		WelcomeLabel.setBounds(208, 11, 869, 58);
		contentPane.add(WelcomeLabel);
		
		
		int uw=user.getIconWidth();
		int uh=user.getIconHeight();
		setPreferredSize(new Dimension(uw,uh));
		JLabel userImage = new JLabel(new ImageIcon(homePage.class.getResource("/OnlineShopping/user image.png")));
		userImage.setBackground(Color.GRAY);
		userImage.setBounds(83, 79, 547, 516);
		contentPane.add(userImage);
		
		JLabel adminImage = new JLabel("");
		adminImage.setIcon(new ImageIcon(homePage.class.getResource("/OnlineShopping/Admin pic.png")));
		adminImage.setBounds(728, 103, 530, 499);
		contentPane.add(adminImage);
		
		JButton userButton = new JButton("User Login");
		userButton.setForeground(new Color(220, 107, 25));
		userButton.setBackground(new Color(94, 22, 117));
		userButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		userButton.setBounds(182, 611, 349, 32);
		contentPane.add(userButton);
		userButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						new UserLoginPage().setVisible(true);
					}
			
				});
		
		JButton btnAdminLogin = new JButton("Admin Login");
		btnAdminLogin.setForeground(new Color(220, 107, 25));
		btnAdminLogin.setBackground(new Color(94, 22, 117));
		btnAdminLogin.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnAdminLogin.setBounds(728, 611, 349, 32);
		contentPane.add(btnAdminLogin);
		btnAdminLogin.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AdminLoginPage().setVisible(true);
			}
	
		});
		
	}
}
