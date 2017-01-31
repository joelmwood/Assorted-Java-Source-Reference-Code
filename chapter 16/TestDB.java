import java.sql.*;

public class TestDB
{
	public static void main(String[] args) throws Exception
	{
		UnpaidOrder order = new UnpaidOrder();
		String[] info = order.getOrders();
		for (int i = 0; i < info.length; i++)
			System.out.print(info[i]);
	}
}