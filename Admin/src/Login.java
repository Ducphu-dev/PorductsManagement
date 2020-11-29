import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Login extends JPanel {

	private JTextField txt_name;
	private JPasswordField txt_pass;
	
	
	public Login() {
	
		
		setLayout(null);
//		
		
		JLabel lblNewLabel = new JLabel("\u0110\u0103ng nh\u1EADp");
		lblNewLabel.setBounds(156, 52, 115, 20);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("T\u00E0i kho\u1EA3n");
		lblNewLabel_1.setBounds(59, 117, 64, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("M\u1EADt kh\u1EA9u");
		lblNewLabel_1_1.setBounds(59, 167, 64, 14);
		add(lblNewLabel_1_1);
		
		JTextField txt_name = new JTextField();
		txt_name.setBounds(146, 114, 218, 20);
		add(txt_name);
		txt_name.setColumns(10);
		
		JPasswordField txt_pass = new JPasswordField();
		txt_pass.setBounds(146, 164, 218, 20);
		add(txt_pass);
		
		JButton btn_login = new JButton("\u0110\u0103ng nh\u1EADp");
		btn_login.setBounds(257, 221, 107, 23);
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String name =  txt_name.getText();
				String pass =  txt_pass.getText();
				
				try {
					if(txt_name.getText().isEmpty() || txt_pass.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null,"Bạn chưa nhâp tài khoản hoặc mật khẩu!");
					}else {
						Connection connection = ConnectionUltils.getMyConnection();
					    String sql = "select * from AccountAdmin Where AdminName = '"+name+"' and AdminPassword = '" +pass+"'";
					    PreparedStatement statement = connection.prepareStatement(sql);
					    ResultSet rs = statement.executeQuery();
					    
					   if(rs.next()) {
					    	MainForEdit mfe = new MainForEdit();
					    	mfe.setVisible(true);
							
					    }else {
					    	JOptionPane.showMessageDialog(null,"Tên tài khoản hoặc mật khẩu sai!");
						}
					}
					
				} catch (Exception e) {
					  System.out.print("ketnoithatbai");
				}
				
//				if(name.equals("ducphu") && pass.equals("1234")) {
//					MainForEdit mfe = null;
//					try {
//						mfe = new MainForEdit();
//					} catch (ClassNotFoundException | SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//			    	mfe.setVisible(true);
//				}
			}
		});
		add(btn_login);
		
		
	}
}
