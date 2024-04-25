package OnlineShopping;

import java.awt.EventQueue;
import java.time.LocalDateTime;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;  
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat; 

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;

public class UserPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTable cartTable;
	private JTextField pIDfield;
	private JTextField cIDfield;
	private JTable filterTable;
	private JTextField pidField;
	private JTextField colour;
	private JTextField minPrice;
	private JTextField maxPrice;
	private JTextField category;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserPage frame = new UserPage();
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
	public UserPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1301, 704);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(113, 29, 176));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		int oid=0;
		ResultSet rs1=null;
		String DB_URL = "jdbc:mysql://127.0.0.1:3306/miniproj?autoReconnect=true&useSSL=false";
		String USER = "root";
		String PASS = "root";
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement pstmt=null;
			String query="select oid from orders order by oid desc limit 1";
			pstmt=conn.prepareStatement(query);
			rs1=pstmt.executeQuery(query);
			while(rs1.next())
			{
				oid=rs1.getInt(1)+1;
			}
			query="insert into orders(oID, oDate, oBill, oStatus) values ("+Integer.toString(oid)+",CURDATE(), 0, 'Ordered');";
			pstmt=conn.prepareStatement(query);
			pstmt.executeUpdate(query);

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		final int o=oid;
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 28, 1263, 495);
		contentPane.add(layeredPane);
		
		JPanel displaypanel = new JPanel();
		displaypanel.setBounds(0, 0, 1263, 495);
		layeredPane.add(displaypanel);
		displaypanel.setLayout(null);
		
		JPanel cartpanel = new JPanel();
		cartpanel.setBounds(0, 0, 1263, 495);
		layeredPane.add(cartpanel);
		cartpanel.setLayout(null);
		
		JPanel filterpanel = new JPanel();
		filterpanel.setBounds(0, 0, 1263, 495);
		layeredPane.add(filterpanel);
		
		JButton DisplayButton = new JButton("Display Products");
		DisplayButton.setForeground(new Color(225, 210, 63));
		DisplayButton.setBackground(new Color(94, 22, 117));
		DisplayButton.setBounds(10, 552, 154, 23);
		DisplayButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						layeredPane.removeAll();
						layeredPane.add(displaypanel);
						layeredPane.repaint();
						layeredPane.revalidate();
						// TODO Auto-generated method stub
						ResultSet rs1=null;
						String DB_URL = "jdbc:mysql://127.0.0.1:3306/miniproj?autoReconnect=true&useSSL=false";
						String USER = "root";
						String PASS = "root";
						Connection conn=null;
						try {
							PreparedStatement pstmt=null;
							String query="select pID, pName, pCost, pDescription, pColour, pCategory, compName from products natural join company;";
							conn=DriverManager.getConnection(DB_URL, USER, PASS);
							pstmt=conn.prepareStatement(query);							
							rs1=pstmt.executeQuery(query);
							ResultSetMetaData rsmdt=rs1.getMetaData();
							DefaultTableModel m=(DefaultTableModel) table.getModel();
							m.setRowCount(0);
							int c=rsmdt.getColumnCount();
							String [] colnames=new String[c];
							for (int i=0; i<c; i++)
							{
								colnames[i]=rsmdt.getColumnName(i+1);
							}
							m.setColumnIdentifiers(colnames);
							while(rs1.next())
							{
								String [] row= new String[c];
								for(int i=1; i<=c; i++)
								{
									row[i-1]=rs1.getString(i);
								}
								m.addRow(row);
							}
							conn.close();
							pstmt.close();
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
			
				});
		
		contentPane.add(DisplayButton);
		
		JLabel productID = new JLabel("Enter Product ID: ");
		productID.setForeground(new Color(220, 107, 25));
		productID.setBounds(1022, 534, 116, 14);
		contentPane.add(productID);
		
		JLabel OrderId = new JLabel("Order ID: "+oid);
		OrderId.setForeground(new Color(220, 107, 25));
		OrderId.setBounds(1022, 556, 116, 14);
		contentPane.add(OrderId);
		
		pidField = new JTextField();
		pidField.setBounds(1129, 534, 96, 20);
		pidField.setColumns(10);
		contentPane.add(pidField);
		
		JButton addcartButton = new JButton("Add to Cart");
		addcartButton.setBackground(new Color(94, 22, 117));
		addcartButton.setForeground(new Color(225, 210, 63));
		addcartButton.setBounds(970, 581, 136, 23);
		addcartButton.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) {
						
						// TODO Auto-generated method stub
						String pid=pidField.getText();
						
						String DB_URL = "jdbc:mysql://127.0.0.1:3306/miniproj?autoReconnect=true&useSSL=false";
						String USER = "root";
						String PASS = "root";
						Connection conn=null;
						try {					
							
							PreparedStatement pstmt=null;
							String query="insert into cart values ("+Integer.toString(o)+", " +pid+"); ";
							conn=DriverManager.getConnection(DB_URL, USER, PASS);
							pstmt=conn.prepareStatement(query);
							pstmt.executeUpdate(query);
							pidField.setText("");
						} 
						catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
			
				});
		contentPane.add(addcartButton);
		
		JButton cartButton = new JButton("Show Cart");
		cartButton.setBackground(new Color(94, 22, 117));
		cartButton.setForeground(new Color(225, 210, 63));
		cartButton.setBounds(10, 586, 154, 23);
		cartButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(cartpanel);
				layeredPane.repaint();
				layeredPane.revalidate();
				// TODO Auto-generated method stub
				ResultSet rs1=null;
				String DB_URL = "jdbc:mysql://127.0.0.1:3306/miniproj?autoReconnect=true&useSSL=false";
				String USER = "root";
				String PASS = "root";
				Connection conn=null;
				try {
					PreparedStatement pstmt=null;
					String query="select oID, pID, pName, pCost, pDescription, pColour, pCategory, compName from cart natural join products natural join company;";
					conn=DriverManager.getConnection(DB_URL, USER, PASS);
					pstmt=conn.prepareStatement(query);							
					rs1=pstmt.executeQuery(query);
					ResultSetMetaData rsmdt=rs1.getMetaData();
					DefaultTableModel m=(DefaultTableModel) cartTable.getModel();
					m.setRowCount(0);
					int c=rsmdt.getColumnCount();
					String [] colnames=new String[c];
					for (int i=0; i<c; i++)
					{
						colnames[i]=rsmdt.getColumnName(i+1);
					}
					m.setColumnIdentifiers(colnames);
					while(rs1.next())
					{
						String [] row= new String[c];
						for(int i=1; i<=c; i++)
						{
							row[i-1]=rs1.getString(i);
						}
						m.addRow(row);
					}
					conn.close();
					pstmt.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	
		});

		contentPane.add(cartButton);
		
		colour = new JTextField();
		colour.setBounds(224, 553, 147, 20);
		contentPane.add(colour);
		colour.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Sort By Colour");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel.setForeground(new Color(220, 107, 25));
		lblNewLabel.setBounds(252, 534, 109, 14);
		contentPane.add(lblNewLabel);
		
		minPrice = new JTextField();
		minPrice.setColumns(10);
		minPrice.setBounds(649, 553, 133, 20);
		contentPane.add(minPrice);
		
		maxPrice = new JTextField();
		maxPrice.setColumns(10);
		maxPrice.setBounds(649, 592, 133, 20);
		contentPane.add(maxPrice);
		
		JLabel lblSortByPrice = new JLabel("Sort By Price");
		lblSortByPrice.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblSortByPrice.setForeground(new Color(220, 107, 25));
		lblSortByPrice.setBounds(649, 534, 102, 14);
		contentPane.add(lblSortByPrice);
		
		category = new JTextField();
		category.setColumns(10);
		category.setBounds(386, 553, 173, 20);
		contentPane.add(category);
		
		JLabel sortCategory = new JLabel("Sort By Category");
		sortCategory.setForeground(new Color(220, 107, 25));
		sortCategory.setFont(new Font("Segoe UI", Font.BOLD, 13));
		sortCategory.setBounds(423, 534, 136, 14);
		contentPane.add(sortCategory);
		
		JLabel lblNewLabel_1 = new JLabel("Start Price:");
		lblNewLabel_1.setForeground(new Color(220, 107, 25));
		lblNewLabel_1.setBounds(586, 556, 70, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Max Price:");
		lblNewLabel_1_1.setForeground(new Color(220, 107, 25));
		lblNewLabel_1_1.setBounds(586, 586, 70, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JButton applycolFilterButton = new JButton("Apply Colour Filter");
		applycolFilterButton.setBackground(new Color(94, 22, 117));
		applycolFilterButton.setForeground(new Color(225, 210, 63));
		applycolFilterButton.setBounds(224, 582, 154, 23);
		applycolFilterButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				String col=colour.getText();
				layeredPane.removeAll();
				layeredPane.add(filterpanel);
				layeredPane.repaint();
				layeredPane.revalidate();
				// TODO Auto-generated method stub
				ResultSet rs1=null;
				String DB_URL = "jdbc:mysql://127.0.0.1:3306/miniproj?autoReconnect=true&useSSL=false";
				String USER = "root";
				String PASS = "root";
				Connection conn=null;
				try {
					PreparedStatement pstmt=null;
					String query="Call spColour('"+col+"');";
					conn=DriverManager.getConnection(DB_URL, USER, PASS);
					pstmt=conn.prepareStatement(query);							
					rs1=pstmt.executeQuery(query);
					ResultSetMetaData rsmdt=rs1.getMetaData();
					DefaultTableModel m=(DefaultTableModel) filterTable.getModel();
					m.setRowCount(0);
					int c=rsmdt.getColumnCount();
					String [] colnames=new String[c];
					for (int i=0; i<c; i++)
					{
						colnames[i]=rsmdt.getColumnName(i+1);
					}
					m.setColumnIdentifiers(colnames);
					while(rs1.next())
					{
						String [] row= new String[c];
						for(int i=1; i<=c; i++)
						{
							row[i-1]=rs1.getString(i);
						}
						m.addRow(row);
					}
					conn.close();
					pstmt.close();
					colour.setText("");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	
		});
		contentPane.add(applycolFilterButton);
		
		
		JButton btnApplyCategoryFilter = new JButton("Apply Category Filter");
		btnApplyCategoryFilter.setForeground(new Color(225, 210, 63));
		btnApplyCategoryFilter.setBackground(new Color(94, 22, 117));
		btnApplyCategoryFilter.setBounds(386, 582, 173, 23);
		btnApplyCategoryFilter.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				String cat=category.getText();
				layeredPane.removeAll();
				layeredPane.add(filterpanel);
				layeredPane.repaint();
				layeredPane.revalidate();
				// TODO Auto-generated method stub
				ResultSet rs1=null;
				String DB_URL = "jdbc:mysql://127.0.0.1:3306/miniproj?autoReconnect=true&useSSL=false";
				String USER = "root";
				String PASS = "root";
				Connection conn=null;
				try {
					PreparedStatement pstmt=null;
					String query="Call spCategory('"+cat+"');";
					conn=DriverManager.getConnection(DB_URL, USER, PASS);
					pstmt=conn.prepareStatement(query);							
					rs1=pstmt.executeQuery(query);
					ResultSetMetaData rsmdt=rs1.getMetaData();
					DefaultTableModel m=(DefaultTableModel) filterTable.getModel();
					m.setRowCount(0);
					int c=rsmdt.getColumnCount();
					String [] colnames=new String[c];
					for (int i=0; i<c; i++)
					{
						colnames[i]=rsmdt.getColumnName(i+1);
					}
					m.setColumnIdentifiers(colnames);
					while(rs1.next())
					{
						String [] row= new String[c];
						for(int i=1; i<=c; i++)
						{
							row[i-1]=rs1.getString(i);
						}
						m.addRow(row);
					}
					conn.close();
					pstmt.close();
					category.setText("");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	
		});
		contentPane.add(btnApplyCategoryFilter);
		
		JButton applyPriceFilterButton = new JButton("Apply Price Filter");
		applyPriceFilterButton.setForeground(new Color(225, 210, 63));
		applyPriceFilterButton.setBackground(new Color(94, 22, 117));
		applyPriceFilterButton.setBounds(617, 620, 165, 23);
		applyPriceFilterButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				String min=minPrice.getText();
				int mi=Integer.parseInt(min);
				String max=maxPrice.getText();
				int ma=Integer.parseInt(max);
				layeredPane.removeAll();
				layeredPane.add(filterpanel);
				layeredPane.repaint();
				layeredPane.revalidate();
				// TODO Auto-generated method stub
				ResultSet rs1=null;
				String DB_URL = "jdbc:mysql://127.0.0.1:3306/miniproj?autoReconnect=true&useSSL=false";
				String USER = "root";
				String PASS = "root";
				Connection conn=null;
				try {
					PreparedStatement pstmt=null;
					String query="Call spCost("+mi+","+ma+");";
					conn=DriverManager.getConnection(DB_URL, USER, PASS);
					pstmt=conn.prepareStatement(query);							
					rs1=pstmt.executeQuery(query);
					ResultSetMetaData rsmdt=rs1.getMetaData();
					DefaultTableModel m=(DefaultTableModel) filterTable.getModel();
					m.setRowCount(0);
					int c=rsmdt.getColumnCount();
					String [] colnames=new String[c];
					for (int i=0; i<c; i++)
					{
						colnames[i]=rsmdt.getColumnName(i+1);
					}
					m.setColumnIdentifiers(colnames);
					while(rs1.next())
					{
						String [] row= new String[c];
						for(int i=1; i<=c; i++)
						{
							row[i-1]=rs1.getString(i);
						}
						m.addRow(row);
					}
					conn.close();
					pstmt.close();
					minPrice.setText("");
					maxPrice.setText("");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	
		});
		contentPane.add(applyPriceFilterButton);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1263, 495);
		displaypanel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		filterpanel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 5, 1263, 490);
		filterpanel.add(scrollPane_1);
		
		filterTable = new JTable();
		scrollPane_1.setViewportView(filterTable);
		filterTable.setBounds(112, 34, 1040, 279);
		
		
		cartTable = new JTable();
		cartTable.setBounds(0, 0, 1263, 495);
		cartpanel.add(cartTable);
		
		
		
		
		
		JButton Clear = new JButton("Clear Filters");
		Clear.setForeground(new Color(225, 210, 63));
		Clear.setBackground(new Color(94, 22, 117));
		Clear.setBounds(312, 620, 154, 23);
		Clear.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.repaint();
				layeredPane.revalidate();
				// TODO Auto-generated method stub
				minPrice.setText("");
				maxPrice.setText("");
				colour.setText("");
				category.setText("");
			}
	
		});
		contentPane.add(Clear);
		
		JButton removecartButton = new JButton("Remove from Cart");		
		removecartButton.setForeground(new Color(225, 210, 63));
		removecartButton.setBackground(new Color(94, 22, 117));
		removecartButton.setBounds(1119, 581, 136, 23);
		removecartButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// TODO Auto-generated method stub
				String pid=pidField.getText();
				String DB_URL = "jdbc:mysql://127.0.0.1:3306/miniproj?autoReconnect=true&useSSL=false";
				String USER = "root";
				String PASS = "root";
				Connection conn=null;
				try {					
					
					PreparedStatement pstmt=null;
					String query="delete from cart where pID =" +pid+" and oID="+Integer.toString(o)+" limit 1; ";
					conn=DriverManager.getConnection(DB_URL, USER, PASS);
					pstmt=conn.prepareStatement(query);
					pstmt.executeUpdate(query);
					pidField.setText("");
				} 
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	
		});
		contentPane.add(removecartButton);
		
		
		JButton placeorderButton = new JButton("Place Order");
		placeorderButton.setBackground(new Color(94, 22, 117));
		placeorderButton.setForeground(new Color(225, 210, 63));
		placeorderButton.setBounds(1057, 620, 125, 23);
		placeorderButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						
						// TODO Auto-generated method stub
						new placeOrderPage().setVisible(true);
					}
			
				});
		contentPane.add(placeorderButton);
		
		JButton BackButton = new JButton("<= BACK");
		BackButton.setForeground(new Color(255, 210, 63));
		BackButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		BackButton.setBackground(new Color(128, 0, 0));
		BackButton.setBounds(10, 620, 133, 36);
		BackButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				new UserLoginPage().setVisible(true);
				
			}
			
		});
		contentPane.add(BackButton);
		
		
		
	}
}
