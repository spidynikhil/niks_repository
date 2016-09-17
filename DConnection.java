package hms;
import java.sql.*;
import javax.swing.*;
class DConnection
{
		Connection conn;
		Statement stmt;
		ResultSet rst;
		private void open()
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
			}
			catch(ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			try
			{
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/hms","root","");
				stmt=conn.createStatement();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		ResultSet executeQuery(String query)//for select
		{
			try
			{
				open();
				rst=stmt.executeQuery(query);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			return rst;
		}
		int executeOther(String query)//for other query
		{
			int cnt=0;
			try
			{
				open();
				cnt=stmt.executeUpdate(query);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,"Selected item currently in use. Can't complete action now!","Oops! Something went wrong!",JOptionPane.ERROR_MESSAGE);
			}
			close();
			return cnt;
		}
		void close()
		{
			try
			{
				stmt.close();
				conn.close();	
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
}


