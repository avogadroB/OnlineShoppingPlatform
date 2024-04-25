package OnlineShopping;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class AdminPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable BestSellerTable;
	private JTextField prodNum;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField StartDatetext;
	private JTextField EndDateField;
	private JTable allProdTable;
	private JTextField startDateField;
	private JTextField endDateField;
	private JTable prodwiseRevTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPage frame = new AdminPage();
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
	public AdminPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1301, 704);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(113, 29, 176));
		contentPane.setForeground(new Color(113, 29, 176));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(354, 21, 893, 623);
		contentPane.add(layeredPane);
		layeredPane.setLayout(null);
		
		JPanel productwiseRevenuePanel = new JPanel();
		productwiseRevenuePanel.setForeground(new Color(225, 210, 63));
		productwiseRevenuePanel.setLayout(null);
		productwiseRevenuePanel.setBackground(new Color(59, 52, 132));
		productwiseRevenuePanel.setBounds(0, 0, 893, 623);
		layeredPane.add(productwiseRevenuePanel);
			
		
		JPanel TotalItemsPanel = new JPanel();
		TotalItemsPanel.setLayout(null);
		TotalItemsPanel.setBackground(new Color(59, 52, 132));
		TotalItemsPanel.setBounds(0, 0, 893, 623);
		layeredPane.add(TotalItemsPanel);
		
		
		JPanel highestSalePanel = new JPanel();
		highestSalePanel.setBounds(0, 0, 893, 623);
		layeredPane.add(highestSalePanel);
		highestSalePanel.setBackground(new Color(59, 52, 132));
		highestSalePanel.setLayout(null);
		
		JPanel Revenuepanel = new JPanel();
		Revenuepanel.setBounds(74, 0, 893, 623);
		layeredPane.add(Revenuepanel);
		Revenuepanel.setLayout(null);
		Revenuepanel.setBackground(new Color(59, 52, 132));
		
		layeredPane.removeAll();
		
		
		
		JLabel lblNumProd = new JLabel("Enter Number of top products: \r\n");
		lblNumProd.setForeground(new Color(255, 210, 63));
		lblNumProd.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNumProd.setBounds(54, 63, 299, 52);
		highestSalePanel.add(lblNumProd);
		
		prodNum = new JTextField();
		prodNum.setBounds(64, 126, 265, 40);
		highestSalePanel.add(prodNum);
		prodNum.setColumns(10);
		
		JButton btnRevenue = new JButton("Show Revenue");
		btnRevenue.setForeground(new Color(255, 210, 63));
		btnRevenue.setBackground(new Color(94, 22, 117));
		btnRevenue.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnRevenue.setBounds(63, 35, 191, 79);
		contentPane.add(btnRevenue);
		btnRevenue.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(Revenuepanel);
				layeredPane.repaint();
				layeredPane.revalidate();
				// TODO Auto-generated method stub
				
	
		}});
		
		JButton btnHighestSelling = new JButton("Best Sellers ");
		btnHighestSelling.setForeground(new Color(255, 210, 63));
		btnHighestSelling.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnHighestSelling.setBackground(new Color(94, 22, 117));
		btnHighestSelling.setBounds(63, 155, 191, 79);
		contentPane.add(btnHighestSelling);
		btnHighestSelling.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(highestSalePanel);
				layeredPane.repaint();
				layeredPane.revalidate();
				// TODO Auto-generated method stub
			}
		});
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 220, 496, 359);
		highestSalePanel.add(scrollPane);
		
		
		JButton btnProdNum = new JButton("Enter");
		btnProdNum.setForeground(new Color(64, 0, 128));
		btnProdNum.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnProdNum.setBackground(new Color(255, 210, 63));
		btnProdNum.setBounds(373, 114, 118, 52);
		btnProdNum.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				String prod=prodNum.getText();
				ResultSet rs1=null;
				String DB_URL = "jdbc:mysql://127.0.0.1:3306/miniproj?autoReconnect=true&useSSL=false";
				String USER = "root";
				String PASS = "root";
				Connection conn=null;
				try {
					PreparedStatement pstmt=null;
					String query="Call highest_selling_products("+prod+");";
					conn=DriverManager.getConnection(DB_URL, USER, PASS);
					pstmt=conn.prepareStatement(query);							
					rs1=pstmt.executeQuery(query);
					ResultSetMetaData rsmdt=rs1.getMetaData();
					DefaultTableModel m=(DefaultTableModel) BestSellerTable.getModel();
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
		highestSalePanel.add(btnProdNum);
		
		BestSellerTable = new JTable();
		BestSellerTable.setBackground(new Color(59, 52, 132));
		BestSellerTable.setForeground(new Color(225, 210, 63));
		scrollPane.setViewportView(BestSellerTable);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(197, 83, 185, 36);
		Revenuepanel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(197, 135, 185, 36);
		Revenuepanel.add(textField_1);
		
		JLabel lblAmt = new JLabel("");
		lblAmt.setForeground(new Color(255, 210, 63));
		lblAmt.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblAmt.setBackground(new Color(59, 52, 132));
		lblAmt.setBounds(449, 393, 289, 95);
		Revenuepanel.add(lblAmt);
		
		JLabel lblStartDate = new JLabel("Enter Start Date: ");
		lblStartDate.setForeground(new Color(255, 210, 63));
		lblStartDate.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblStartDate.setBackground(new Color(59, 52, 132));
		lblStartDate.setBounds(68, 105, 119, 14);
		Revenuepanel.add(lblStartDate);
		
		JLabel lblEndDate = new JLabel("Enter End Date: ");
		lblEndDate.setForeground(new Color(255, 210, 63));
		lblEndDate.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblEndDate.setBackground(new Color(59, 52, 132));
		lblEndDate.setBounds(68, 145, 119, 14);
		Revenuepanel.add(lblEndDate);
		
		JButton btnNodate = new JButton("Display All time Revenue");
		btnNodate.setForeground(new Color(64, 0, 128));
		btnNodate.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnNodate.setBackground(new Color(255, 210, 63));
		btnNodate.setBounds(498, 65, 240, 54);
		btnNodate.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ResultSet rs1=null;
				String DB_URL = "jdbc:mysql://127.0.0.1:3306/miniproj?autoReconnect=true&useSSL=false";
				String USER = "root";
				String PASS = "root";
				Connection conn=null;
				try {
					PreparedStatement pstmt=null;
					String query="call total_revenue();";
					conn=DriverManager.getConnection(DB_URL, USER, PASS);
					pstmt=conn.prepareStatement(query);							
					rs1=pstmt.executeQuery(query);
					while(rs1.next())
					{
						lblAmt.setText(rs1.getString(1));;
					}
					conn.close();
					pstmt.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	
		});
		Revenuepanel.add(btnNodate);
		
		JButton btnDate = new JButton("Display Revenue with dates");
		btnDate.setForeground(new Color(64, 0, 128));
		btnDate.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnDate.setBackground(new Color(255, 210, 63));
		btnDate.setBounds(498, 147, 240, 54);
		btnDate.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String start=textField.getText();
				String end=textField_1.getText();
				ResultSet rs1=null;
				String DB_URL = "jdbc:mysql://127.0.0.1:3306/miniproj?autoReconnect=true&useSSL=false";
				String USER = "root";
				String PASS = "root";
				Connection conn=null;
				try {
					PreparedStatement pstmt=null;
					String query="call total_revenue_bydate('"+start+"', '"+end+"');";
					conn=DriverManager.getConnection(DB_URL, USER, PASS);
					pstmt=conn.prepareStatement(query);							
					rs1=pstmt.executeQuery(query);
					while(rs1.next())
					{
						lblAmt.setText(rs1.getString(1));;
					}
					conn.close();
					pstmt.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	
		});
		Revenuepanel.add(btnDate);
		
		JLabel lblTheRevenueGenerated = new JLabel("The revenue generated is Rs. ");
		lblTheRevenueGenerated.setForeground(new Color(255, 210, 63));
		lblTheRevenueGenerated.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTheRevenueGenerated.setBackground(new Color(59, 52, 132));
		lblTheRevenueGenerated.setBounds(157, 393, 289, 95);
		Revenuepanel.add(lblTheRevenueGenerated);
		
		JButton btnTotalSales = new JButton("Total Items Sold");
		btnTotalSales.setForeground(new Color(255, 210, 63));
		btnTotalSales.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnTotalSales.setBackground(new Color(94, 22, 117));
		btnTotalSales.setBounds(63, 274, 191, 79);
		contentPane.add(btnTotalSales);
		btnTotalSales.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(TotalItemsPanel);
				layeredPane.repaint();
				layeredPane.revalidate();
				// TODO Auto-generated method stub
			}
		});
		
		StartDatetext = new JTextField();
		StartDatetext.setColumns(10);
		StartDatetext.setBounds(197, 83, 185, 36);
		TotalItemsPanel.add(StartDatetext);
		
		EndDateField = new JTextField();
		EndDateField.setColumns(10);
		EndDateField.setBounds(197, 135, 185, 36);
		TotalItemsPanel.add(EndDateField);
		
		JLabel lblStartDate_1 = new JLabel("Enter Start Date: ");
		lblStartDate_1.setForeground(new Color(255, 210, 63));
		lblStartDate_1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblStartDate_1.setBackground(new Color(59, 52, 132));
		lblStartDate_1.setBounds(68, 105, 119, 14);
		TotalItemsPanel.add(lblStartDate_1);
		
		JLabel lblEndDate_1 = new JLabel("Enter End Date: ");
		lblEndDate_1.setForeground(new Color(255, 210, 63));
		lblEndDate_1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblEndDate_1.setBackground(new Color(59, 52, 132));
		lblEndDate_1.setBounds(68, 145, 119, 14);
		TotalItemsPanel.add(lblEndDate_1);
		
		JButton btnDisplayAllProducts = new JButton("Display All Products Sold");
		btnDisplayAllProducts.setForeground(new Color(64, 0, 128));
		btnDisplayAllProducts.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnDisplayAllProducts.setBackground(new Color(255, 210, 63));
		btnDisplayAllProducts.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String start=StartDatetext.getText();
				String end=EndDateField.getText();
				ResultSet rs1=null;
				String DB_URL = "jdbc:mysql://127.0.0.1:3306/miniproj?autoReconnect=true&useSSL=false";
				String USER = "root";
				String PASS = "root";
				Connection conn=null;
				try {
					PreparedStatement pstmt=null;
					String query="call total_sales_bydate('"+start+"', '"+end+"');";
					conn=DriverManager.getConnection(DB_URL, USER, PASS);
					pstmt=conn.prepareStatement(query);							
					rs1=pstmt.executeQuery(query);
					ResultSetMetaData rsmdt=rs1.getMetaData();
					DefaultTableModel m=(DefaultTableModel) allProdTable.getModel();
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
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	
		});

		btnDisplayAllProducts.setBounds(498, 105, 240, 54);
		
		TotalItemsPanel.add(btnDisplayAllProducts);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(87, 209, 651, 355);
		TotalItemsPanel.add(scrollPane_1);
		
		allProdTable = new JTable();
		allProdTable.setBackground(new Color(59, 52, 132));
		allProdTable.setForeground(new Color(225, 210, 63));
		scrollPane_1.setViewportView(allProdTable);
		
		
		
		JButton btnProductwiseSales = new JButton("Productwise Sales");
		btnProductwiseSales.setForeground(new Color(255, 210, 63));
		btnProductwiseSales.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnProductwiseSales.setBackground(new Color(94, 22, 117));
		btnProductwiseSales.setBounds(63, 407, 191, 79);
		btnProductwiseSales.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(productwiseRevenuePanel);
				layeredPane.repaint();
				layeredPane.revalidate();
				// TODO Auto-generated method stub
			}
		});
		
		contentPane.add(btnProductwiseSales);
		
		startDateField = new JTextField();
		startDateField.setColumns(10);
		startDateField.setBounds(197, 83, 185, 36);
		productwiseRevenuePanel.add(startDateField);
		
		endDateField = new JTextField();
		endDateField.setColumns(10);
		endDateField.setBounds(197, 135, 185, 36);
		productwiseRevenuePanel.add(endDateField);
		
		JLabel lblStartDate_1_1 = new JLabel("Enter Start Date: ");
		lblStartDate_1_1.setForeground(new Color(255, 210, 63));
		lblStartDate_1_1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblStartDate_1_1.setBackground(new Color(59, 52, 132));
		lblStartDate_1_1.setBounds(68, 105, 119, 14);
		productwiseRevenuePanel.add(lblStartDate_1_1);
		
		JLabel lblEndDate_1_1 = new JLabel("Enter End Date: ");
		lblEndDate_1_1.setForeground(new Color(255, 210, 63));
		lblEndDate_1_1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblEndDate_1_1.setBackground(new Color(59, 52, 132));
		lblEndDate_1_1.setBounds(68, 145, 119, 14);
		productwiseRevenuePanel.add(lblEndDate_1_1);
		
		JButton btnproductsWithDate = new JButton("Display Productwise Revenue ");
		btnproductsWithDate.setForeground(new Color(64, 0, 128));
		btnproductsWithDate.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnproductsWithDate.setBackground(new Color(255, 210, 63));
		btnproductsWithDate.setBounds(478, 52, 295, 54);
		productwiseRevenuePanel.add(btnproductsWithDate);
		btnproductsWithDate.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ResultSet rs1=null;
				String DB_URL = "jdbc:mysql://127.0.0.1:3306/miniproj?autoReconnect=true&useSSL=false";
				String USER = "root";
				String PASS = "root";
				Connection conn=null;
				try {
					PreparedStatement pstmt=null;
					String query="call product_wise_revenue();";
					conn=DriverManager.getConnection(DB_URL, USER, PASS);
					pstmt=conn.prepareStatement(query);							
					rs1=pstmt.executeQuery(query);
					ResultSetMetaData rsmdt=rs1.getMetaData();
					DefaultTableModel m=(DefaultTableModel) prodwiseRevTable.getModel();
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
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	
		});
		
		JButton btnDisplayProductwiseRevenue = new JButton("Display Productwise Revenue with date");
		btnDisplayProductwiseRevenue.setForeground(new Color(64, 0, 128));
		btnDisplayProductwiseRevenue.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnDisplayProductwiseRevenue.setBackground(new Color(255, 210, 63));
		btnDisplayProductwiseRevenue.setBounds(478, 125, 295, 54);
		btnDisplayProductwiseRevenue.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String start=startDateField.getText();
				String end=endDateField.getText();
				ResultSet rs1=null;
				String DB_URL = "jdbc:mysql://127.0.0.1:3306/miniproj?autoReconnect=true&useSSL=false";
				String USER = "root";
				String PASS = "root";
				Connection conn=null;
				try {
					PreparedStatement pstmt=null;
					String query="call product_wise_revenue_bydate('"+start+"', '"+end+"');";
					conn=DriverManager.getConnection(DB_URL, USER, PASS);
					pstmt=conn.prepareStatement(query);							
					rs1=pstmt.executeQuery(query);
					ResultSetMetaData rsmdt=rs1.getMetaData();
					DefaultTableModel m=(DefaultTableModel) prodwiseRevTable.getModel();
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
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	
		});
		productwiseRevenuePanel.add(btnDisplayProductwiseRevenue);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(68, 237, 762, 335);
		productwiseRevenuePanel.add(scrollPane_2);
		
		prodwiseRevTable = new JTable();
		prodwiseRevTable.setForeground(new Color(225, 210, 63));
		prodwiseRevTable.setBackground(new Color(59, 52, 132));
		scrollPane_2.setViewportView(prodwiseRevTable);
		
		JButton BackButton = new JButton("<= BACK");
		BackButton.setForeground(new Color(255, 210, 63));
		BackButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		BackButton.setBackground(new Color(128, 0, 0));
		BackButton.setBounds(37, 591, 133, 53);
		BackButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				new AdminLoginPage().setVisible(true);
				
			}
			
		});
		contentPane.add(BackButton);
	}
}
