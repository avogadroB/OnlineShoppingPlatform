package OnlineShopping;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Font;

public class placeOrderPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField AddressField;
	private JTextField customerIDField;
	private JTable orderTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					placeOrderPage frame = new placeOrderPage();
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
	public placeOrderPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1301, 704);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1283, 667);
		contentPane.add(layeredPane);
		
		JPanel Billpanel = new JPanel();
		Billpanel.setBackground(new Color(113, 29, 176));
		Billpanel.setBounds(0, 0, 1283, 667);
		layeredPane.add(Billpanel);
		Billpanel.setLayout(null);
		
		JLabel ThankYouLabel = new JLabel("Thank You For Shopping With Us!! ");
		ThankYouLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		ThankYouLabel.setForeground(new Color(225, 210, 63));
		ThankYouLabel.setBounds(422, 450, 525, 128);
		Billpanel.add(ThankYouLabel);
		
		orderTable = new JTable();
		orderTable.setForeground(new Color(255, 255, 255));
		orderTable.setBackground(new Color(0, 0, 64));
		orderTable.setBounds(78, 73, 1111, 248);
		Billpanel.add(orderTable);
		
		JLabel BillLabel = new JLabel("The following order is placed: ");
		BillLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
		BillLabel.setForeground(new Color(225, 210, 63));
		BillLabel.setBounds(78, 30, 298, 32);
		Billpanel.add(BillLabel);
		
		layeredPane.removeAll();
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(113, 29, 176));
		panel.setBounds(0, 0, 1283, 667);
		layeredPane.add(panel);
		panel.setLayout(null);
		
		JLabel displayTotal = new JLabel("");
		displayTotal.setForeground(new Color(225, 210, 63));
		displayTotal.setFont(new Font("Segoe UI", Font.BOLD, 15));
		displayTotal.setBounds(682, 153, 216, 14);
		panel.add(displayTotal);
		
		AddressField = new JTextField();
		AddressField.setBounds(686, 271, 230, 74);
		panel.add(AddressField);
		AddressField.setColumns(10);
		
		int bill=0;
		ResultSet rs1=null;
		ResultSet rs=null;
		String DB_URL = "jdbc:mysql://127.0.0.1:3306/miniproj?autoReconnect=true&useSSL=false";
		String USER = "root";
		String PASS = "root";
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement pstmt=null;
			String query="select sum(pCost) from cart natural join products";
			pstmt=conn.prepareStatement(query);
			rs1=pstmt.executeQuery(query);
			while(rs1.next())
			{
				bill=rs1.getInt(1);
			}
			query="select * from cart;";
			pstmt=conn.prepareStatement(query);
			rs=pstmt.executeQuery(query);
			while(rs.next())
			{
				query="Call AddOrder("+rs.getInt(2)+","+ rs.getInt(1)+")";
				pstmt=conn.prepareStatement(query);
				pstmt.executeUpdate(query);
			}
			
			query="Delete from cart;";
			pstmt=conn.prepareStatement(query);
			pstmt.executeUpdate(query);
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		displayTotal.setText("Rs. "+Integer.toString(bill));
		
		
		JLabel adressLabel = new JLabel("Enter Shipping Address ID: ");
		adressLabel.setForeground(new Color(225, 210, 63));
		adressLabel.setFont(new Font("Segoe UI", Font.BOLD, 19));
		adressLabel.setBounds(404, 282, 272, 44);
		panel.add(adressLabel);
		
		customerIDField = new JTextField();
		customerIDField.setColumns(10);
		customerIDField.setBounds(686, 369, 230, 74);
		panel.add(customerIDField);
		
		JLabel CidLabel = new JLabel("Enter Customer ID: ");
		CidLabel.setForeground(new Color(225, 210, 63));
		CidLabel.setFont(new Font("Segoe UI", Font.BOLD, 19));
		CidLabel.setBounds(472, 380, 204, 44);
		panel.add(CidLabel);
		
		
		
		JLabel billLabel = new JLabel("Total Bill: ");
		billLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		billLabel.setForeground(new Color(225, 210, 63));
		billLabel.setBounds(557, 153, 115, 14);
		panel.add(billLabel);
		
		JLabel orNumLabel = new JLabel("");
		orNumLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
		orNumLabel.setForeground(new Color(225, 210, 63));
		orNumLabel.setBounds(422, 350, 291, 42);
		Billpanel.add(orNumLabel);
		
		JButton confirmButton = new JButton("Confirm Order");
		confirmButton.setFont(new Font("Segoe UI", Font.BOLD, 19));
		confirmButton.setBackground(new Color(94, 22, 117));
		confirmButton.setForeground(new Color(225, 210, 63));
		confirmButton.setBounds(565, 515, 204, 44);
		panel.add(confirmButton);
		confirmButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						layeredPane.removeAll();
						layeredPane.add(Billpanel);
						layeredPane.repaint();
						layeredPane.revalidate();
						ResultSet rs1=null;
						ResultSet rs=null;
						String DB_URL = "jdbc:mysql://127.0.0.1:3306/miniproj?autoReconnect=true&useSSL=false";
						String USER = "root";
						String PASS = "root";
						Connection conn=null;
						
						try {
							int oid=0;
							PreparedStatement pstmt=null;
							String query="Select max(oid) from orders";
							conn=DriverManager.getConnection(DB_URL, USER, PASS);
							pstmt=conn.prepareStatement(query);							
							rs1=pstmt.executeQuery(query);
							while(rs1.next())
							{
								oid=rs1.getInt(1);
							}
							orNumLabel.setText("Your Order Number is: "+Integer.toString(oid));
							query="Update orders set aID="+AddressField.getText()+" where oID="+Integer.toString(oid)+";";
							pstmt=conn.prepareStatement(query);
							pstmt.executeUpdate(query);
							query="insert into updates values ("+customerIDField.getText()+","+Integer.toString(oid)+");";
							pstmt=conn.prepareStatement(query);
							pstmt.executeUpdate(query);
						 	query="Select pid, pName, pCost, pColour from contains natural join products where oId="+Integer.toString(oid);
							pstmt=conn.prepareStatement(query);							
							rs=pstmt.executeQuery(query);
							ResultSetMetaData rsmdt=rs.getMetaData();
							DefaultTableModel m=(DefaultTableModel) orderTable.getModel();
							m.setRowCount(0);
							int c=rsmdt.getColumnCount();
							String [] colnames=new String[c];
							for (int i=0; i<c; i++)
							{
								colnames[i]=rsmdt.getColumnName(i+1);
							}
							m.setColumnIdentifiers(colnames);
							while(rs.next())
							{
								String [] row= new String[c];
								for(int i=1; i<=c; i++)
								{
									row[i-1]=rs.getString(i);
								}
								m.addRow(row);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
			
				}
				);
		

		
		
		
		JLabel orTotalLabel = new JLabel("");
		orTotalLabel.setForeground(new Color(225, 210, 63));
		orTotalLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
		orTotalLabel.setBounds(422, 414, 291, 42);
		Billpanel.add(orTotalLabel);
		orTotalLabel.setText("Your Order total is: Rs. "+Integer.toString(bill));
		
		
		
		
	}
}
