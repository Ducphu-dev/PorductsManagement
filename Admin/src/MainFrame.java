import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame {
	
	public static final int WIDTH = 450;
	public static final int HEIGHT = 350;
	private Login loginFrame;
	private static MainFrame mainFrame;
	
	public MainFrame() {
//		frame tạo
		setSize(WIDTH, HEIGHT);
		setLocation(400,200);
		setTitle("Đăng nhập");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		loginFrame = new Login();
		add(loginFrame);
	
	}
//	singleton partern
	public static MainFrame getInstance() {
		if(mainFrame==null) {
			mainFrame = new MainFrame();
		}
		return mainFrame;
	}
	
	public void startForm() {
		setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
            	MainFrame mf = MainFrame.getInstance();
        		mf.startForm();
            }
        });
	}
	
}