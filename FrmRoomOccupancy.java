package hms;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.print.*;
public class FrmRoomOccupancy extends JInternalFrame implements ActionListener
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
	String[] heads={"Room Id","Room Number","Room Category","Description","Room Charges","Bed Count","Bed Occupied","Bed Free"};
	Object data[][];
    DConnection dc;
    JTableHeader header;
   	FrmRoomOccupancy()
	{
		super("Room Occupancy Chart",true,true,true,true);

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
	    	rst=dc.executeQuery("select count(*) from room");//table name??
	    	rst.next();
			int n=rst.getInt(1);
	    	data=new Object[n][8];
	    	rst=dc.executeQuery("select * from room");
	     	for(int i=0;rst.next();i++)
	     	{
	     		data[i][0] = rst.getString(1);
	     		data[i][1] = rst.getString(2);
	     		data[i][2] = rst.getString(3);
	     		data[i][3] = rst.getString(5);
	     		data[i][4] = rst.getString(6);
	     		data[i][5] = rst.getString(4);
	     		data[i][6] = "";
	     		data[i][7] = "";
	
	     	}
	     	dc.close();
	     	for(int i=0;i<n;i++)
	     	{
	     		String rn=(String)data[i][1];
	     		rst=dc.executeQuery("select count(*) from ipdpatient where room_number="+rn +" and dod='Not Discharged Yet'");
	     		rst.next();
	     		int cnt=rst.getInt(1);
	     		dc.close();
	     		data[i][6]=cnt+"";
	     		data[i][7]=Integer.parseInt((String)data[i][5])-cnt+"";
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
		header.setBackground(new Color(64,64,128));
		header.setFont(new Font(Font.SERIF,Font.BOLD,17));
		jtb.setSelectionForeground(Color.black);
		jtb.setSelectionBackground(Color.orange);
	   	
		jtb.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
		{
   
			private static final long serialVersionUID = 1L;

	@Override
    public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus, int row, int col) {

        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

        String status = (String)table.getModel().getValueAt(row,7);
        if (status.equals("0")) 
        {
            setBackground(Color.magenta);
            setForeground(Color.white);
        } 
        else 
        {
            setBackground(table.getBackground());
            setForeground(table.getForeground());
        }       
        return this;
    }   
});
		jsp = new JScrollPane(jtb);
		jsp.getViewport().setBackground(new Color(255,255,255));
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(jsp);
		setLocation(120,80);
		setVisible(true);
		setSize(1000,500);
		
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