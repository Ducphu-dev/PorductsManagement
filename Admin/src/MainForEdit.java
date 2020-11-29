import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainForEdit extends JFrame {

	private JPanel contentPane;
	
	public JTable table;
	private JTextField txt_name;
	private JTextField txt_brand;
	private JTextField num_amount;
	private JTextField num_id;
	private JTextField txt_country;
	private JTextField txt_kind;
	private JTextField num_price;
	private JTextField txt_search;
	private JComboBox com_dateday;
	private JComboBox com_datemonth;
	private JComboBox com_dateyear;
	private JComboBox com_currency;
	private JLayeredPane layeredPane;
	private JPanel ProductPanel;
	private JPanel UserPanel;
	private JPanel ProductLaptop;
	Vector data = null;
	String header[] = {"ID", "Tên sản phẩm", "Nơi sản xuất","Hãng","Loại","Só lượng","Ngày nhập","Giá"};
	String header_lap[] = {"ID", "Tên sản phẩm", "Kích thước màn hình","Độ phân giải","Tần số quét","Nặng","Rộng","Cao","Dày","Hệ điều hành","Vi xử lí","Ram","Card màn hình","Bộ nhớ","Wifi","Pin","Cổng kết nổi"};
	DefaultTableModel tblModel = new DefaultTableModel(header, 0);
	private JTextField txt_search1;
	private JTable table_user;
	private JTextField num_Lapid;
	private JTextField txt_Lapname;
	private JTextField num_Lapscreensize;
	private JTextField num_Lappixel;
	private JTextField txt_Lapcpu;
	private JTextField num_Lapram;
	private JTextField num_Lapstorage;
	private JTextField num_Laphertz;
	private JTextField num_Lapweight;
	private JTextField num_Lapwidth;
	private JTextField num_Lapheight;
	private JTextField num_Lapthick;
	private JTextField txt_Lapos;
	private JTextField txt_Lapcard;
	private JTextField txt_Lapwifi;
	private JTextField num_Lapbattery;
	private JTextField txt_Lapconnect;
	private JTable table_Lap;
	private JTextField txt_Lapsearch;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForEdit frame = new MainForEdit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void switchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.validate();
	}
	
	public void ExecuteQueryUser() throws ClassNotFoundException, SQLException {
		tblModel.setRowCount(0);
		Connection connection = ConnectionUltils.getMyConnection();
	    Statement statement = connection.createStatement();
	    String sql = "Select * From AccountUser";
	    ResultSet rs = statement.executeQuery(sql);
	    
		 
	    while (rs.next()) {
	        data = new Vector();
	        data.add(rs.getString(1));
	        data.add(rs.getString(2));
	        data.add(rs.getString(4));
	        data.add(rs.getDate(5));
	        data.add(rs.getString(6));
	        data.add(rs.getInt(7));
	        data.add(rs.getString(8));
	        data.add(rs.getString(9));
	        tblModel.addRow(data);
	      
	     }
	    table_user.setModel(tblModel);
	    
	    connection.close();
	}
	
	public void ExecuteQueryProducts() throws ClassNotFoundException,
    SQLException{
		
		tblModel.setRowCount(0);
		Connection connection = ConnectionUltils.getMyConnection();
	    Statement statement = connection.createStatement();
	    String sql = "Select * From Products";
	    ResultSet rs = statement.executeQuery(sql);
	    
		 
	    while (rs.next()) {
	        data = new Vector();
	        data.add(rs.getString(1));
	        data.add(rs.getString(2));
	        data.add(rs.getString(3));
	        data.add(rs.getString(4));
	        data.add(rs.getString(5));
	        data.add(rs.getInt(6));
	        data.add(rs.getDate(7));
	        data.add(rs.getInt(8));
	        tblModel.addRow(data);
	      
	     }
	    table.setModel(tblModel);
	    
	    connection.close();
	}
	
	public void Clear() {
		
		num_id.setText("");
		txt_name.setText("");
		txt_country.setText("");
		txt_brand.setText("");
		txt_kind.setText("");
		num_amount.setText("");
		com_dateday.setSelectedItem(1);
		com_datemonth.setSelectedItem(1);
		com_dateyear.setSelectedItem(1950);
		num_price.setText("");
	}
	
	public void Add() {
		String id = num_id.getText();
		String name = txt_name.getText();
		String country = txt_country.getText();
		String brand = txt_brand.getText();
		String kind = txt_kind.getText();
		String amount = num_amount.getText();
		String day = (String) com_dateday.getItemAt(com_dateday.getSelectedIndex()).toString();
		String month = (String) com_datemonth.getItemAt(com_datemonth.getSelectedIndex()).toString();
		String year = (String) com_dateyear.getItemAt(com_dateyear.getSelectedIndex()).toString();
		String price = num_price.getText();
		if(id.isEmpty() || name.isEmpty() || country.isEmpty() || brand.isEmpty() || kind.isEmpty() || amount == null || day == null || month == null || month == null || year == null || price == null) {
			JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập đầy đủ dữ liệu");
		}else {
			try {
				Connection connection = ConnectionUltils.getMyConnection();
				PreparedStatement ps = connection.prepareStatement("Insert Into Products values ( '"+ id + "', '" + name + "' , '" + country + "' , '" + brand + "' , '" + kind + "' ," + amount + ",('" + month + "/" + day + "/" + year + "')," + price + ")" );
				ps.executeUpdate();
				connection.close();
				ExecuteQueryProducts();
			} catch (Exception ex) {
				ex.printStackTrace();
			} 
		}
     }
	
	public void Search() throws ClassNotFoundException, SQLException {
		
		String search = txt_search.getText();
		tblModel.setRowCount(0);
		Connection connection = ConnectionUltils.getMyConnection();
		if(search.isEmpty()) {
			ExecuteQueryProducts();
			JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập dữ liệu cần tìm");
		}else {
			PreparedStatement ps = connection.prepareStatement("Select * from Products where ProductName like '%" + search + "%'");
		    ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				data = new Vector();
		        data.add(rs.getString(1));
		        data.add(rs.getString(2));
		        data.add(rs.getString(3));
		        data.add(rs.getString(4));
		        data.add(rs.getString(5));
		        data.add(rs.getInt(6));
		        data.add(rs.getDate(7));
		        data.add(rs.getInt(8));
		        tblModel.addRow(data);
		    }
		    table.setModel(tblModel);
		    
		    connection.close();
		}
		
	}
	private void Select() {
		
		int Row = table.getSelectedRow();
		
		if(Row == -1) {
			JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn sản phẩm để sửa");
		}else {
	        String id = (String) table.getValueAt(Row, 0);
	        String name = (String) table.getValueAt(Row, 1);
	        String country = (String) table.getValueAt(Row, 2);
	        String brand = (String) table.getValueAt(Row, 3);
	        String kind = (String) table.getValueAt(Row, 4);
	        int amount = (int) table.getValueAt(Row, 5);
	        Date date = (Date) table.getValueAt(Row, 6);
	        int price = (int) table.getValueAt(Row, 7);
	        
	        String ID = String.valueOf(id);
	        String AMOUNT = String.valueOf(amount);
	        String PRICE = String.valueOf(price);
	        SimpleDateFormat sdfd = new SimpleDateFormat("dd");
	        int day = Integer.parseInt(sdfd.format(date));
	        SimpleDateFormat sdfm = new SimpleDateFormat("MM");
	        int month = Integer.parseInt(sdfm.format(date));
	        SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
	        int year = Integer.parseInt(sdfy.format(date));
	        
	        num_id.setText(ID);
	        txt_name.setText(name);
	        txt_country.setText(country);
	        txt_brand.setText(brand);
	        txt_kind.setText(kind);
	        num_amount.setText(AMOUNT);
	        com_dateday.setSelectedItem(day);
	        com_datemonth.setSelectedItem(month);
	        com_dateyear.setSelectedItem(year);
	        num_price.setText(PRICE);
		}
	}
	
	public void UpdateLaptop() {
		
		String LapID = num_Lapid.getText();
		String LapName = txt_Lapname.getText();
		String LapScreenSize = num_Lapscreensize.getText();
		String LapPixel = num_Lappixel.getText();
		String LapHertz = num_Laphertz.getText();
		String LapWeight = num_Lapweight.getText();
		String LapWidth = num_Lapwidth.getText();
		String LapHeight = num_Lapheight.getText();
		String LapThick = num_Lapthick.getText();
		String LapOS = txt_Lapos.getText();
		String LapCPU = txt_Lapcpu.getText();
		String LapRam = num_Lapram.getText();
		String LapCard = txt_Lapcard.getText();
		String LapStorage = num_Lapstorage.getText();
		String LapWifi = txt_Lapwifi.getText();
		String LapBaterry = num_Lapbattery.getText();
		String LapConnect = txt_Lapconnect.getText();
        if(LapID.isEmpty() || LapName.isEmpty() || LapScreenSize.isEmpty() || LapPixel.isEmpty() || LapHertz.isEmpty() || LapWeight.isEmpty() || LapWidth.isEmpty() || LapHeight.isEmpty() || LapThick.isEmpty() || LapOS.isEmpty() || LapCPU.isEmpty() || LapRam.isEmpty() || LapCard.isEmpty() || LapStorage.isEmpty() || LapWifi.isEmpty() || LapBaterry.isEmpty() || LapConnect.isEmpty() ) {
        	JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập đầy đủ dữ liệu");
        }else{
        	try {
				Connection connection = ConnectionUltils.getMyConnection();
				PreparedStatement ps = connection.prepareStatement("UPDATE Laptop SET ProductName = '"+ LapName + "', ProductScreenSize = "+ LapScreenSize +", ProductScreenPixel ='"+ LapPixel +"', ProductScreenHertz = "+ LapHertz +", ProductWeight = '"+ LapWeight +"', ProductWidth ="+ LapWidth +", ProductHeight ="+ LapHeight +", ProductThickness = "+ LapThick +", ProductOS ='"+LapOS+"', ProductCPU ='"+ LapCPU +"', ProductRam = '"+ LapRam +"', ProductGPU = '"+ LapCard +"', ProductStorage = '"+ LapStorage +"', ProductWifi = '"+ LapWifi +"', ProductBaterry = '"+ LapBaterry +"', ProductConnect = '"+ LapConnect +"' where ProductID = '"+LapID+"'");                    
				ps.executeUpdate();
				ps.close();
				ShowLaptop();
			} catch (Exception ex) {
				ex.printStackTrace();
			} 
        }
		
		
	}
	
	public void Update() {
		String id = num_id.getText();
		String name = txt_name.getText();
		String country = txt_country.getText();
		String brand = txt_brand.getText();
		String kind = txt_kind.getText();
		String amount = num_amount.getText();
		String day = (String) com_dateday.getItemAt(com_dateday.getSelectedIndex()).toString();
		String month = (String) com_datemonth.getItemAt(com_datemonth.getSelectedIndex()).toString();
		String year = (String) com_dateyear.getItemAt(com_dateyear.getSelectedIndex()).toString();
		String price = num_price.getText();
		if(id.isEmpty() || name.isEmpty() || country.isEmpty() || brand.isEmpty() || kind.isEmpty() || amount == null || day == null || month == null || month == null || year == null || price == null) {
			JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập đầy đủ dữ liệu");
		}else {
				try {
				Connection connection = ConnectionUltils.getMyConnection();
				PreparedStatement ps = connection.prepareStatement("UPDATE Products SET ProductName = '"+ name + "', ProductCountry = '"+ country +"', ProductBrand = '"+ brand +"', ProductKind ='"+ kind +"', ProductAmount ="+ amount +", DateIn = ('"+month+"/"+day+"/"+year+"'), Price ="+price+" where ProductID = '"+id+"'");                    
				ps.executeUpdate();
				ExecuteQueryProducts();
			} catch (Exception ex) {
				ex.printStackTrace();
			} 
		}
		
	}
	
	
	public void Delete() {
		try {
			int Row = table.getSelectedRow();
			
			if(Row == -1) {
				JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn sản phẩm để xóa");
			}else {
				int ret = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa?", "?", JOptionPane.YES_NO_OPTION);
				if(ret != JOptionPane.YES_OPTION) {
					return;
				}else {
					String id = (String) table.getValueAt(Row, 0);
					Connection connection = ConnectionUltils.getMyConnection();
					PreparedStatement ps = connection.prepareStatement("Delete From Products where ProductID = '"+id+"'");
					ps.executeUpdate();
					connection.close();
					ExecuteQueryProducts();
				}
				
			}
			} catch (Exception ex) {
				ex.printStackTrace();
			} 
		}
	
	public void ShowLaptop() throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionUltils.getMyConnection();
	    Statement statement = connection.createStatement();
	    String sql = "Select * From Laptop";
	    ResultSet rs = statement.executeQuery(sql);
	    DefaultTableModel tblModel_lap = new DefaultTableModel(header_lap, 0);
		 
	    while (rs.next()) {
	        data = new Vector();
	        data.add(rs.getString(1));
	        data.add(rs.getString(2));
	        data.add(rs.getInt(3));
	        data.add(rs.getString(4));
	        data.add(rs.getInt(5));
	        data.add(rs.getString(6));
	        data.add(rs.getInt(7));
	        data.add(rs.getInt(8));
	        data.add(rs.getInt(9));
	        data.add(rs.getString(10));
	        data.add(rs.getString(11));
	        data.add(rs.getString(12));
	        data.add(rs.getString(13));
	        data.add(rs.getString(14));
	        data.add(rs.getString(15));
	        data.add(rs.getString(16));
	        data.add(rs.getString(17));
	        tblModel_lap.addRow(data);
	      
	     }
	    table_Lap.setModel(tblModel_lap);
		
		connection.close();
	}
	
	public void ShowDetails() throws ClassNotFoundException, SQLException {
		
		try {
			int Row = table.getSelectedRow();
			
			if(Row == -1) {
				JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn sản phẩm để xem chi tiết");
			}else {
				String id = (String) table.getValueAt(Row, 0);
				Connection connection = ConnectionUltils.getMyConnection();
				PreparedStatement ps = connection.prepareStatement("Select * from Laptop where ProductID ='" + id + "'");
			    ResultSet rs = ps.executeQuery();
			    
			    while (rs.next()) {
			    	
			    	String size = String.valueOf(rs.getInt(3));
			    	String hertz = String.valueOf(rs.getInt(5));
			    	String width = String.valueOf(rs.getInt(7));
			    	String height = String.valueOf(rs.getInt(8));
			    	String thick = String.valueOf(rs.getInt(9));
			       
			        num_Lapid.setText(rs.getString(1));
			        txt_Lapname.setText(rs.getString(2));
			        num_Lapscreensize.setText(size);
			        num_Lappixel.setText(rs.getString(4));
			        num_Laphertz.setText(hertz);
			        num_Lapweight.setText(rs.getString(6));
			        num_Lapwidth.setText(width);
			        num_Lapheight.setText(height);
			        num_Lapthick.setText(thick);
			        txt_Lapos.setText(rs.getString(10));
			        txt_Lapcpu.setText(rs.getString(11));
			        num_Lapram.setText(rs.getString(12));
			        txt_Lapcard.setText(rs.getString(13));
			        num_Lapstorage.setText(rs.getString(14));
			        txt_Lapwifi.setText(rs.getString(15));
			        num_Lapbattery.setText(rs.getString(16));
			        txt_Lapconnect.setText(rs.getString(17));
			     }
				switchPanels(ProductLaptop);
				ShowLaptop();
			}
		} catch (Exception ex) {
				ex.printStackTrace();
		} 
	}
		
	
	public MainForEdit() throws ClassNotFoundException, SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1141, 614);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Quản lý");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel.setBounds(59, 11, 71, 21);
		contentPane.add(lblNewLabel);
		
		JButton btnProduct = new JButton("Sản phẩm");
		btnProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(ProductPanel);
				try {
					ExecuteQueryProducts();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnProduct.setBounds(146, 13, 114, 23);
		contentPane.add(btnProduct);
		
		JButton btnUser = new JButton("Khách hàng");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(UserPanel);
				try {
					ExecuteQueryUser();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnUser.setBounds(288, 13, 114, 23);
		contentPane.add(btnUser);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 74, 1105, 493);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		ProductPanel = new JPanel();
		layeredPane.add(ProductPanel, "name_741486415484400");
		ProductPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Mã sản phẩm");
		lblNewLabel_1.setBounds(22, 3, 80, 14);
		ProductPanel.add(lblNewLabel_1);
		
		num_id = new JTextField();
		num_id.setColumns(10);
		num_id.setBounds(112, 0, 441, 20);
		ProductPanel.add(num_id);
		
		JLabel lblNewLabel_1_2 = new JLabel("Tên sản phẩm");
		lblNewLabel_1_2.setBounds(582, 3, 80, 14);
		ProductPanel.add(lblNewLabel_1_2);
		
		txt_name = new JTextField();
		txt_name.setColumns(10);
		txt_name.setBounds(672, 3, 423, 20);
		ProductPanel.add(txt_name);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nơi sản xuất");
		lblNewLabel_1_1.setBounds(22, 49, 80, 14);
		ProductPanel.add(lblNewLabel_1_1);
		
		txt_country = new JTextField();
		txt_country.setColumns(10);
		txt_country.setBounds(112, 46, 441, 20);
		ProductPanel.add(txt_country);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Tên hãng");
		lblNewLabel_1_1_2.setBounds(582, 49, 80, 14);
		ProductPanel.add(lblNewLabel_1_1_2);
		
		txt_brand = new JTextField();
		txt_brand.setColumns(10);
		txt_brand.setBounds(672, 49, 423, 20);
		ProductPanel.add(txt_brand);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Loại sản phẩm");
		lblNewLabel_1_1_1.setBounds(22, 96, 80, 14);
		ProductPanel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Số lượng");
		lblNewLabel_1_1_1_2.setBounds(582, 96, 80, 14);
		ProductPanel.add(lblNewLabel_1_1_1_2);
		
		num_amount = new JTextField();
		num_amount.setColumns(10);
		num_amount.setBounds(672, 96, 423, 20);
		ProductPanel.add(num_amount);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Ngày nhập");
		lblNewLabel_1_1_1_1.setBounds(22, 147, 80, 14);
		ProductPanel.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Ngày");
		lblNewLabel_1_1_1_1_1.setBounds(93, 147, 35, 14);
		ProductPanel.add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Tháng");
		lblNewLabel_1_1_1_1_1_1.setBounds(264, 147, 35, 14);
		ProductPanel.add(lblNewLabel_1_1_1_1_1_1);
		
		DefaultComboBoxModel day = new DefaultComboBoxModel();
		for(int i = 1 ; i <= 31 ; i++) {
			day.addElement(i);
		}
		com_dateday = new JComboBox(day);
		com_dateday.setBounds(138, 144, 89, 20);
		ProductPanel.add(com_dateday);
		
		DefaultComboBoxModel month = new DefaultComboBoxModel();
		for(int i = 1 ; i <= 12 ; i++) {
			month.addElement(i);
		}
		com_datemonth = new JComboBox(month);
		com_datemonth.setBounds(324, 144, 70, 20);
		ProductPanel.add(com_datemonth);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Năm");
		lblNewLabel_1_1_1_1_1_1_1.setBounds(416, 147, 35, 14);
		ProductPanel.add(lblNewLabel_1_1_1_1_1_1_1);
		
		DefaultComboBoxModel year = new DefaultComboBoxModel();
		for(int i = 1950 ; i <= 2020 ; i++) {
			year.addElement(i);
		}
		com_dateyear = new JComboBox(year);
		com_dateyear.setBounds(475, 144, 78, 20);
		ProductPanel.add(com_dateyear);
		
		JLabel lblNewLabel_1_1_1_2_1_1 = new JLabel("Giá tiền");
		lblNewLabel_1_1_1_2_1_1.setBounds(582, 147, 80, 14);
		ProductPanel.add(lblNewLabel_1_1_1_2_1_1);
		
		num_price = new JTextField();
		num_price.setColumns(10);
		num_price.setBounds(672, 147, 423, 20);
		ProductPanel.add(num_price);
		
		JButton btnNewButton_2 = new JButton("Xóa");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Clear();
			}
		});
		btnNewButton_2.setBounds(771, 190, 89, 23);
		ProductPanel.add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("Thêm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Add();
			}
		});
		btnNewButton.setBounds(1009, 190, 86, 23);
		ProductPanel.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Tìm kiếm");
		lblNewLabel_2.setBounds(138, 226, 70, 14);
		ProductPanel.add(lblNewLabel_2);
		
		txt_search = new JTextField();
		txt_search.setColumns(10);
		txt_search.setBounds(218, 223, 588, 20);
		ProductPanel.add(txt_search);
		
		JButton btnTm = new JButton("Tìm");
		btnTm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Search();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnTm.setBounds(804, 222, 86, 23);
		ProductPanel.add(btnTm);
	
		table = new JTable();
		table.setBounds(22, 248, 1073, 197);
		ProductPanel.add(table);
		
		JButton btnNewButton_1 = new JButton("Sửa");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Select();
			}
		});
		btnNewButton_1.setBounds(891, 456, 89, 23);
		ProductPanel.add(btnNewButton_1);
		
		JButton btnXa = new JButton("Xóa");
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Delete();
			}
		});
		btnXa.setBounds(782, 456, 86, 23);
		ProductPanel.add(btnXa);
		
		txt_kind = new JTextField();
		txt_kind.setColumns(10);
		txt_kind.setBounds(112, 93, 441, 20);
		ProductPanel.add(txt_kind);
		
		JButton btnNewButton_3 = new JButton("Sửa");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Update();
			}
		});
		btnNewButton_3.setBounds(891, 190, 89, 23);
		ProductPanel.add(btnNewButton_3);
		
		JButton btnNewButton_6 = new JButton("Chi tiết");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ShowDetails();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_6.setBounds(1006, 456, 89, 23);
		ProductPanel.add(btnNewButton_6);
		
		UserPanel = new JPanel();
		layeredPane.add(UserPanel, "name_741508007737500");
		UserPanel.setLayout(null);
		
		txt_search1 = new JTextField();
		txt_search1.setBounds(118, 12, 705, 20);
		UserPanel.add(txt_search1);
		txt_search1.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("Tìm kiếm");
		btnNewButton_4.setBounds(823, 12, 113, 21);
		UserPanel.add(btnNewButton_4);
		
		table_user = new JTable();
		table_user.setBounds(10, 55, 1085, 407);
		UserPanel.add(table_user);
		
		JButton btnNewButton_5 = new JButton("Vô hiệu hóa");
		btnNewButton_5.setBounds(973, 473, 122, 23);
		UserPanel.add(btnNewButton_5);
		
		ProductLaptop = new JPanel();
		ProductLaptop.setLayout(null);
		layeredPane.add(ProductLaptop, "name_84544996481500");
		
		JLabel lblNewLabel_1_3 = new JLabel("Mã sản phẩm");
		lblNewLabel_1_3.setBounds(36, 14, 80, 14);
		ProductLaptop.add(lblNewLabel_1_3);
		
		num_Lapid = new JTextField();
		num_Lapid.setColumns(10);
		num_Lapid.setBounds(126, 11, 419, 20);
		ProductLaptop.add(num_Lapid);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Tên sản phẩm");
		lblNewLabel_1_2_1.setBounds(574, 14, 80, 14);
		ProductLaptop.add(lblNewLabel_1_2_1);
		
		txt_Lapname = new JTextField();
		txt_Lapname.setColumns(10);
		txt_Lapname.setBounds(664, 11, 435, 20);
		ProductLaptop.add(txt_Lapname);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("Màn hình");
		lblNewLabel_1_1_3.setBounds(36, 45, 80, 14);
		ProductLaptop.add(lblNewLabel_1_1_3);
		
		num_Lapscreensize = new JTextField();
		num_Lapscreensize.setColumns(10);
		num_Lapscreensize.setBounds(126, 42, 49, 20);
		ProductLaptop.add(num_Lapscreensize);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Độ phân giải màn hình");
		lblNewLabel_1_1_2_1.setBounds(264, 45, 128, 14);
		ProductLaptop.add(lblNewLabel_1_1_2_1);
		
		num_Lappixel = new JTextField();
		num_Lappixel.setColumns(10);
		num_Lappixel.setBounds(402, 42, 212, 20);
		ProductLaptop.add(num_Lappixel);
		
		JLabel lblNewLabel_1_1_1_3 = new JLabel("Vi xử lí");
		lblNewLabel_1_1_1_3.setBounds(36, 105, 80, 14);
		ProductLaptop.add(lblNewLabel_1_1_1_3);
		
		txt_Lapcpu = new JTextField();
		txt_Lapcpu.setColumns(10);
		txt_Lapcpu.setBounds(126, 102, 528, 20);
		ProductLaptop.add(txt_Lapcpu);
		
		JLabel lblNewLabel_1_1_1_2_1 = new JLabel("Ram");
		lblNewLabel_1_1_1_2_1.setBounds(36, 136, 49, 14);
		ProductLaptop.add(lblNewLabel_1_1_1_2_1);
		
		num_Lapram = new JTextField();
		num_Lapram.setColumns(10);
		num_Lapram.setBounds(126, 133, 242, 20);
		ProductLaptop.add(num_Lapram);
		
		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("Card màn hình");
		lblNewLabel_1_1_1_1_2.setBounds(662, 105, 80, 14);
		ProductLaptop.add(lblNewLabel_1_1_1_1_2);
		
		JLabel lblNewLabel_1_1_1_2_1_1_1 = new JLabel("Bộ nhớ");
		lblNewLabel_1_1_1_2_1_1_1.setBounds(402, 136, 60, 14);
		ProductLaptop.add(lblNewLabel_1_1_1_2_1_1_1);
		
		num_Lapstorage = new JTextField();
		num_Lapstorage.setColumns(10);
		num_Lapstorage.setBounds(472, 133, 270, 20);
		ProductLaptop.add(num_Lapstorage);
		
		JLabel lblNewLabel_1_1_3_1 = new JLabel(".inch");
		lblNewLabel_1_1_3_1.setBounds(185, 45, 27, 14);
		ProductLaptop.add(lblNewLabel_1_1_3_1);
		
		JLabel lblNewLabel_3 = new JLabel("Tần sô quét");
		lblNewLabel_3.setBounds(635, 45, 80, 14);
		ProductLaptop.add(lblNewLabel_3);
		
		num_Laphertz = new JTextField();
		num_Laphertz.setColumns(10);
		num_Laphertz.setBounds(725, 42, 139, 20);
		ProductLaptop.add(num_Laphertz);
		
		JLabel lblNewLabel_4 = new JLabel("Nặng");
		lblNewLabel_4.setBounds(897, 45, 46, 14);
		ProductLaptop.add(lblNewLabel_4);
		
		num_Lapweight = new JTextField();
		num_Lapweight.setColumns(10);
		num_Lapweight.setBounds(953, 42, 143, 20);
		ProductLaptop.add(num_Lapweight);
		
		JLabel lblNewLabel_4_1 = new JLabel("Rộng");
		lblNewLabel_4_1.setBounds(485, 73, 46, 14);
		ProductLaptop.add(lblNewLabel_4_1);
		
		num_Lapwidth = new JTextField();
		num_Lapwidth.setColumns(10);
		num_Lapwidth.setBounds(541, 70, 113, 20);
		ProductLaptop.add(num_Lapwidth);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Dài");
		lblNewLabel_4_1_1.setBounds(682, 73, 33, 14);
		ProductLaptop.add(lblNewLabel_4_1_1);
		
		num_Lapheight = new JTextField();
		num_Lapheight.setColumns(10);
		num_Lapheight.setBounds(725, 70, 139, 20);
		ProductLaptop.add(num_Lapheight);
		
		JLabel lblNewLabel_4_1_1_1 = new JLabel("Dày");
		lblNewLabel_4_1_1_1.setBounds(897, 73, 33, 14);
		ProductLaptop.add(lblNewLabel_4_1_1_1);
		
		num_Lapthick = new JTextField();
		num_Lapthick.setColumns(10);
		num_Lapthick.setBounds(953, 67, 94, 20);
		ProductLaptop.add(num_Lapthick);
		
		JLabel lblNewLabel_4_1_1_1_1 = new JLabel(".mm");
		lblNewLabel_4_1_1_1_1.setBounds(1066, 70, 33, 14);
		ProductLaptop.add(lblNewLabel_4_1_1_1_1);
		
		JLabel lblNewLabel_1_1_3_2 = new JLabel("Hệ điều hành");
		lblNewLabel_1_1_3_2.setBounds(36, 76, 80, 14);
		ProductLaptop.add(lblNewLabel_1_1_3_2);
		
		txt_Lapos = new JTextField();
		txt_Lapos.setColumns(10);
		txt_Lapos.setBounds(126, 73, 317, 20);
		ProductLaptop.add(txt_Lapos);
		
		txt_Lapcard = new JTextField();
		txt_Lapcard.setBounds(760, 102, 339, 19);
		ProductLaptop.add(txt_Lapcard);
		txt_Lapcard.setColumns(10);
		
		JLabel lblNewLabel_1_1_1_2_1_1_1_1 = new JLabel("Wifi");
		lblNewLabel_1_1_1_2_1_1_1_1.setBounds(778, 136, 33, 14);
		ProductLaptop.add(lblNewLabel_1_1_1_2_1_1_1_1);
		
		txt_Lapwifi = new JTextField();
		txt_Lapwifi.setColumns(10);
		txt_Lapwifi.setBounds(834, 133, 265, 20);
		ProductLaptop.add(txt_Lapwifi);
		
		JLabel lblNewLabel_1_1_1_2_1_2 = new JLabel("Pin");
		lblNewLabel_1_1_1_2_1_2.setBounds(36, 167, 49, 14);
		ProductLaptop.add(lblNewLabel_1_1_1_2_1_2);
		
		num_Lapbattery = new JTextField();
		num_Lapbattery.setColumns(10);
		num_Lapbattery.setBounds(126, 164, 367, 20);
		ProductLaptop.add(num_Lapbattery);
		
		JLabel lblNewLabel_1_1_1_2_1_1_1_1_1 = new JLabel("Cổng kết nối");
		lblNewLabel_1_1_1_2_1_1_1_1_1.setBounds(551, 167, 80, 14);
		ProductLaptop.add(lblNewLabel_1_1_1_2_1_1_1_1_1);
		
		txt_Lapconnect = new JTextField();
		txt_Lapconnect.setColumns(10);
		txt_Lapconnect.setBounds(664, 164, 435, 20);
		ProductLaptop.add(txt_Lapconnect);
		
		table_Lap = new JTable();
		table_Lap.setBounds(10, 259, 1085, 223);
		ProductLaptop.add(table_Lap);
		
		JButton btnNewButton_7 = new JButton("Cập nhật");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateLaptop();
			}
		});
		btnNewButton_7.setBounds(1010, 195, 89, 23);
		ProductLaptop.add(btnNewButton_7);
		
		JLabel lblNewLabel_5 = new JLabel("Tìm kiếm");
		lblNewLabel_5.setBounds(236, 234, 46, 14);
		ProductLaptop.add(lblNewLabel_5);
		
		txt_Lapsearch = new JTextField();
		txt_Lapsearch.setBounds(282, 231, 511, 20);
		ProductLaptop.add(txt_Lapsearch);
		txt_Lapsearch.setColumns(10);
		
		JButton btnNewButton_8 = new JButton("Tìm");
		btnNewButton_8.setBounds(792, 230, 89, 23);
		ProductLaptop.add(btnNewButton_8);
		
		
		
		ExecuteQueryProducts();
		

	}
}
