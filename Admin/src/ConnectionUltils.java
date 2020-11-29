import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUltils {

	public static Connection getMyConnection() throws SQLException, ClassNotFoundException {
		// Sử dụng Oracle.
		// Bạn có thể thay thế bởi Database nào đó.
		return SQLServerConnection.getSQLServerConnection();
	}
	
	
	//
	// Test Connection ...
	//
	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		System.out.println("Get connection ... ");

		// Lấy ra đối tượng Connection kết nối vào database.
		Connection conn = ConnectionUltils.getMyConnection();

		System.out.println("Get connection " + conn);

		System.out.println("Done!");
	}

}