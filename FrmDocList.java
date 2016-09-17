package hms;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import java.awt.print.*;
public class FrmDocList extends JInternalFrame implements ActionListener
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton jbt,btnExit;
	JPanel p;
	JTable jtb;
	ResultSet rst;
	JScrollPane jsp;
	String[] heads={"Doctor Id","Name","Contact","Degree","Specialization","Fees","Mail id","Timings"};
	Object data[][];
    DConnection dc;
    JTableHeader header;
   	FrmDocList()
	{
		super("Doctor List",true,true,true,true);

		jbt=new JButton("Print");
		jbt.setForeground(Color.white);
		jbt.setBackground(new Color(128,0,255));
		jbt.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,20));
		jbt.addActionListener(this);
		
		btnExit=new JButton("Cancel");
		btnExit.setForeground(Color.white);
		btnExit.setBackground(Color.red);
		btnExit.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,20));
		btnExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				dispose();
			}
		});
		
		p=new JPanel();
		p.setLayout(new GridLayout(1,2));
		p.add(btnExit);p.add(jbt);
		add(p,"South");

		try
		{
	    	dc=new DConnection();
	    	rst=dc.executeQuery("select count(*) from doctor");//tabale name??
	    	rst.next();
			int n=rst.getInt(1);
	    	data=new Object[n][8];
	    	rst=dc.executeQuery("select * from doctor");
	     	for(int i=0;rst.next();i++)
	     	{
	     		for(int j=0;j<8;j++)
	     			data[i][j] = rst.getString(j+1);
	     	}
	     	dc.close();
	    }
	    catch(Exception e)
		{		
			e.printStackTrace();
		}

		jtb=new JTable(data,heads);	
		jtb.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);	
		jtb.setFillsViewportHeight(true);
		jtb.setRowHeight(25);
	    jtb.setRowMargin(5);
	    Dimension d1=new Dimension(5,5);
	    jtb.setIntercellSpacing(d1);
	    jtb.setGridColor(Color.black);
	    jtb.setShowGrid(true);
	    jtb.setForeground(Color.blue);
		jtb.setBackground(new Color(255,255,255));
		jtb.setFont(new Font(Font.SERIF,Font.BOLD+Font.ITALIC,15));
		header=jtb.getTableHeader();
		header.setForeground(Color.white);
		header.setBackground(new Color(0,64,128));
		header.setFont(new Font(Font.SERIF,Font.BOLD,17));
		jtb.setSelectionForeground(Color.black);
		jtb.setSelectionBackground(Color.orange);
	   
	   	
	   	
		jsp = new JScrollPane(jtb);
		jsp.getViewport().setBackground(new Color(255,255,255));
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(jsp);
		
		setLocation(120,80);
		setSize(900,500);
		setVisible(true);
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		try
		{
			jtb.print();
		}
		catch(PrinterException e)
		{
			e.printStackTrace();
		}
	}
}