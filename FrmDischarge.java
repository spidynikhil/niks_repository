package hms;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
class FrmDischarge extends JInternalFrame 
	
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lblIPDno,lblPatient,lblDate,lblRoomCharges,lblProCharges,lblDrCharges,lblMedCharges,lblOtherCharges,lblTotal,lblAdvance,lblNet;
	TextField txtIPDno,txtPatient,txtDate,txtRoomCharges,txtProCharges,txtDrCharges,txtMedCharges,txtOtherCharges,txtTotal,txtAdvance,txtNet;
	static FrmDischarge p1; 
	JButton btnDischarge,btnDiscard;
	DConnection dc;
	ResultSet rst,rst2;
	int ipd_no=0;
	FrmDischarge(boolean flag,String query,FrmIPDAdmission fpp)
	{
		super("Discharge",true,true,true,true);
		setResizable(false);
		dc=new DConnection();
		setLayout(new GridLayout(12,2));
		
		lblIPDno = new JLabel("IPD No. ");
		lblIPDno.setForeground(new Color(128,0,64));
     	lblIPDno.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		lblPatient = new JLabel("Patient Name");
		lblPatient.setForeground(new Color(128,0,64));
     	lblPatient.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		lblDate = new JLabel("Date ");
		lblDate.setForeground(new Color(128,0,64));
     	lblDate.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		lblRoomCharges = new JLabel("Room Charges ");
		lblRoomCharges.setForeground(new Color(128,0,64));
     	lblRoomCharges.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		lblProCharges= new JLabel("Procedure Charges ");
		lblProCharges.setForeground(new Color(128,0,64));
     	lblProCharges.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		lblDrCharges= new JLabel("Dcctor Charges ");
		lblDrCharges.setForeground(new Color(128,0,64));
     	lblDrCharges.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		lblMedCharges= new JLabel("Medicine Charges ");
		lblMedCharges.setForeground(new Color(128,0,64));
     	lblMedCharges.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
     	
		lblOtherCharges = new JLabel("Other Charges ");
		lblOtherCharges.setForeground(new Color(128,0,64));
     	lblOtherCharges.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		lblTotal = new JLabel("Total ");
		lblTotal.setForeground(Color.magenta);
     	lblTotal.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		lblAdvance = new JLabel("Advance ");
		lblAdvance.setForeground(new Color(0,128,64));
     	lblAdvance.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		lblNet = new JLabel("Net Amount ");
		lblNet.setForeground(Color.red);
     	lblNet.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		txtIPDno=new TextField();
		txtIPDno.setEditable(false);
		txtIPDno.setForeground(new Color(106,106,106));
     	txtIPDno.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		txtPatient = new TextField();
		txtPatient.setEditable(false);
		txtPatient.setForeground(new Color(106,106,106));
     	txtPatient.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		txtDate = new TextField();
		txtDate.setEditable(false);
		txtDate.setForeground(new Color(106,106,106));
     	txtDate.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		txtRoomCharges = new TextField();
		txtRoomCharges.setEditable(false);
		txtRoomCharges.setForeground(new Color(106,106,106));
     	txtRoomCharges.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		txtProCharges= new TextField();
		txtProCharges.setEditable(false);
		txtProCharges.setForeground(new Color(106,106,106));
     	txtProCharges.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		txtDrCharges= new TextField();
		txtDrCharges.setForeground(new Color(106,106,106));
     	txtDrCharges.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		txtMedCharges= new TextField();
		txtMedCharges.setEditable(false);
		txtMedCharges.setForeground(new Color(106,106,106));
     	txtMedCharges.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		txtOtherCharges = new TextField();
		txtOtherCharges.setForeground(new Color(106,106,106));
     	txtOtherCharges.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,18));
		
		txtTotal = new TextField();
		txtTotal.setEditable(false);
		txtTotal.setForeground(Color.magenta);
     	txtTotal.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,20));
		
		txtAdvance = new TextField();
		txtAdvance.setForeground(new Color(0,128,64));
     	txtAdvance.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,20));
		
		txtNet = new TextField();
		txtNet.setEditable(false);
		txtNet.setForeground(Color.red);
     	txtNet.setFont(new Font(Font.SERIF,Font.ITALIC+Font.BOLD,20));
		
		
		Date dNow = new Date();
      	SimpleDateFormat ft =new SimpleDateFormat ("dd.MM.yyyy");

      	txtDate.setText((ft.format(dNow)));
		try
		{
				
				rst=dc.executeQuery(query);
				rst.next();
				String dt=rst.getString("doa");
				String s11[]=dt.split("\\.");
				Calendar cd=Calendar.getInstance();
				Calendar doa=Calendar.getInstance();
				doa.set(Integer.parseInt(s11[2]),
		    	Integer.parseInt(s11[1])-1,Integer.parseInt(s11[0]));
		    	long l1=cd.getTime().getTime();
		    	long l2=doa.getTime().getTime();
		    	long diff=l1-l2;
		    	long days=diff/1000/60/60/24;
		    	long d=days+1;

				txtIPDno.setText(rst.getString(1));	
				ResultSet rst3;
				rst3=dc.executeQuery("select * from patient where pat_id='"+rst.getString("pat_id")+"'");
				rst3.next();
				ResultSet rst9,rst7;
			    rst9=dc.executeQuery("select sum(amount) from ipdmedicine where ipd_no="+rst.getString("ipd_no"));
				
				rst7=dc.executeQuery("select sum(amount) from ipdprocedure where ipd_no="+rst.getString("ipd_no"));
				
				txtPatient.setText(rst3.getString("pat_name"));
				ResultSet rst4;
				rst4=dc.executeQuery("select * from room where room_number='"+rst.getString("room_number")+"'");
				rst4.next();
				lblRoomCharges.setText("Room Charges  ("+d+"x"+rst4.getInt("room_charges")+")");
				txtRoomCharges.setText(d*Integer.parseInt(rst4.getString("room_charges"))+"");
				if(rst7.next())
					txtProCharges.setText(rst7.getString(1));
				else
					txtProCharges.setText("0");
				if(rst9.next())	
					txtMedCharges.setText(rst9.getString(1));
				else
					txtMedCharges.setText("0");
				txtDrCharges.setText(rst.getString("drcharges"));
				txtOtherCharges.setText(rst.getString("othercharges"));
				txtAdvance.setText(rst.getString("adpay"));
			//	txtTotal.setText(rst4.getString("room_charges"));
			//	txtNet.setText(rst.getString((rst.getInt("total")-rst.getInt("adpay"))+""));
				
				btnDischarge=new JButton("Discharge");
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		btnDiscard=new JButton("Discard");
		
		txtDrCharges.addTextListener(new TextListener()
		{	
			public void textValueChanged(TextEvent fe)
			{	
				int l1,l2,l3,l4,l5,l6,l7;
				if(txtRoomCharges.getText().equals(""))
				l1=0;
				else
				l1=Integer.parseInt(txtRoomCharges.getText());
				if(txtProCharges.getText().equals(""))
				l2=0;
				else
			    l2=Integer.parseInt(txtProCharges.getText());
				if(txtMedCharges.getText().equals(""))
				l3=0;
				else
				l3=Integer.parseInt(txtMedCharges.getText());
				if(txtOtherCharges.getText().equals(""))
				l4=0;
				else
				l4=Integer.parseInt(txtOtherCharges.getText());
				if(txtDrCharges.getText().equals(""))
				l5=0;
				else
				l5=Integer.parseInt(txtDrCharges.getText());
				l6=l1+l2+l3+l4+l5;
		 		txtTotal.setText(l6+"");
		 		l7=l6-Integer.parseInt(txtAdvance.getText());
		 		txtNet.setText(l7+"");
			
			}
			
		});
		txtOtherCharges.addTextListener(new TextListener()
		{	
			public void textValueChanged(TextEvent fe)
			{	
				int l1,l2,l3,l4,l5,l6,l7;
				if(txtRoomCharges.getText().equals(""))
				l1=0;
				else
				l1=Integer.parseInt(txtRoomCharges.getText());
				if(txtProCharges.getText().equals(""))
				l2=0;
				else
			    l2=Integer.parseInt(txtProCharges.getText());
				if(txtMedCharges.getText().equals(""))
				l3=0;
				else
				l3=Integer.parseInt(txtMedCharges.getText());
				if(txtOtherCharges.getText().equals(""))
				l4=0;
				else
				l4=Integer.parseInt(txtOtherCharges.getText());
				if(txtDrCharges.getText().equals(""))
				l5=0;
				else
				l5=Integer.parseInt(txtDrCharges.getText());
				l6=l1+l2+l3+l4+l5;
		 		txtTotal.setText(l6+"");
		 		l7=l6-Integer.parseInt(txtAdvance.getText());
		 		txtNet.setText(l7+"");
			
			}
			
		});
		
		
		
		txtProCharges.addFocusListener(new FocusListener()
		{	
			public void focusGained(FocusEvent fe){
				
				}
			public void focusLost(FocusEvent fe)
			{	
				int l1,l2,l3,l4,l5,l6,l7;
				if(txtRoomCharges.getText().equals(""))
				l1=0;
				else
				l1=Integer.parseInt(txtRoomCharges.getText());
				if(txtProCharges.getText().equals(""))
				l2=0;
				else
			    l2=Integer.parseInt(txtProCharges.getText());
				if(txtMedCharges.getText().equals(""))
				l3=0;
				else
				l3=Integer.parseInt(txtMedCharges.getText());
				if(txtOtherCharges.getText().equals(""))
				l4=0;
				else
				l4=Integer.parseInt(txtOtherCharges.getText());
				if(txtDrCharges.getText().equals(""))
				l5=0;
				else
				l5=Integer.parseInt(txtDrCharges.getText());
				l6=l1+l2+l3+l4+l5;
		 		txtTotal.setText(l6+"");
		 		l7=l6-Integer.parseInt(txtAdvance.getText());
		 		txtNet.setText(l7+"");
			
			}
			
		});
		txtMedCharges.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent fe){
				
				}
			public void focusLost(FocusEvent fe)
			{
			
				int l1,l2,l3,l4,l5,l6,l7;
				if(txtRoomCharges.getText().equals(""))
				l1=0;
				else
				l1=Integer.parseInt(txtRoomCharges.getText());
				if(txtProCharges.getText().equals(""))
				l2=0;
				else
			    l2=Integer.parseInt(txtProCharges.getText());
				if(txtMedCharges.getText().equals(""))
				l3=0;
				else
				l3=Integer.parseInt(txtMedCharges.getText());
				if(txtOtherCharges.getText().equals(""))
				l4=0;
				else
				l4=Integer.parseInt(txtOtherCharges.getText());
				if(txtDrCharges.getText().equals(""))
				l5=0;
				else
				l5=Integer.parseInt(txtDrCharges.getText());
				l6=l1+l2+l3+l4+l5;
		 		txtTotal.setText(l6+"");
		 		l7=l6-Integer.parseInt(txtAdvance.getText());
		 		txtNet.setText(l7+"");
			}
			
		});
		txtOtherCharges.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent fe){
				}
			public void focusLost(FocusEvent fe)
			{
			
				int l1,l2,l3,l4,l5,l6,l7;
				if(txtRoomCharges.getText().equals(""))
				l1=0;
				else
				l1=Integer.parseInt(txtRoomCharges.getText());
				if(txtProCharges.getText().equals(""))
				l2=0;
				else
			    l2=Integer.parseInt(txtProCharges.getText());
				if(txtMedCharges.getText().equals(""))
				l3=0;
				else
				l3=Integer.parseInt(txtMedCharges.getText());
				if(txtOtherCharges.getText().equals(""))
				l4=0;
				else
				l4=Integer.parseInt(txtOtherCharges.getText());
				if(txtDrCharges.getText().equals(""))
				l5=0;
				else
				l5=Integer.parseInt(txtDrCharges.getText());
				l6=l1+l2+l3+l4+l5;
		 		txtTotal.setText(l6+"");
		 		l7=l6-Integer.parseInt(txtAdvance.getText());
		 		txtNet.setText(l7+"");
			}
			
		});
		txtDrCharges.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent fe){
			
				}
			public void focusLost(FocusEvent fe)
			{
				
				int l1,l2,l3,l4,l5,l6,l7;
				if(txtRoomCharges.getText().equals(""))
				l1=0;
				else
				l1=Integer.parseInt(txtRoomCharges.getText());
				if(txtProCharges.getText().equals(""))
				l2=0;
				else
			    l2=Integer.parseInt(txtProCharges.getText());
				if(txtMedCharges.getText().equals(""))
				l3=0;
				else
				l3=Integer.parseInt(txtMedCharges.getText());
				if(txtOtherCharges.getText().equals(""))
				l4=0;
				else
				l4=Integer.parseInt(txtOtherCharges.getText());
				if(txtDrCharges.getText().equals(""))
				l5=0;
				else
				l5=Integer.parseInt(txtDrCharges.getText());
				l6=l1+l2+l3+l4+l5;
		 		txtTotal.setText(l6+"");
		 		l7=l6-Integer.parseInt(txtAdvance.getText());
		 		txtNet.setText(l7+"");
			
			}
			
		});
		
		btnDiscard.setForeground(Color.white);
		btnDiscard.setBackground(Color.red);
		btnDiscard.setFont(new Font(Font.SERIF,Font.ITALIC,22));
		btnDiscard.addActionListener(new ActionListener()
		{
		 	public void actionPerformed(ActionEvent ae)
		 	{
		 		dispose();
		 	}
		});
		 
		btnDischarge.setForeground(Color.white);
		btnDischarge.setBackground(Color.blue);
		btnDischarge.setFont(new Font(Font.SERIF,Font.ITALIC,22));
		btnDischarge.addActionListener(new ActionListener()
		{
		 	public void actionPerformed(ActionEvent ae)
		 	{
		 		int l1,l2,l3,l4,l5,l6,l7;
				if(txtRoomCharges.getText().equals(""))
				l1=0;
				else
				l1=Integer.parseInt(txtRoomCharges.getText());
				if(txtProCharges.getText().equals(""))
				l2=0;
				else
			    l2=Integer.parseInt(txtProCharges.getText());
				if(txtMedCharges.getText().equals(""))
				l3=0;
				else
				l3=Integer.parseInt(txtMedCharges.getText());
				if(txtOtherCharges.getText().equals(""))
				l4=0;
				else
				l4=Integer.parseInt(txtOtherCharges.getText());
				if(txtDrCharges.getText().equals(""))
				l5=0;
				else
				l5=Integer.parseInt(txtDrCharges.getText());
				l6=l1+l2+l3+l4+l5;
		 		txtTotal.setText(l6+"");
		 		l7=l6-Integer.parseInt(txtAdvance.getText());
		 		txtNet.setText(l7+"");
		 		String s1=txtIPDno.getText();
		 		dc.executeOther("update ipdpatient set dod='"+txtDate.getText()+"',total="+txtTotal.getText()+",net="+txtNet.getText()+",procharges="+txtProCharges.getText()+",medcharges="+txtMedCharges.getText()+",othercharges="+txtOtherCharges.getText()+",drcharges="+txtDrCharges.getText()+" where ipd_no="+s1);
		 		dispose();
		 		fpp.reload();
		 		//fpp.jsp.repaint();
		 		//fpp.repaint();
		 	}	
		 });
		 add(lblIPDno);add(txtIPDno);
		 add(lblPatient);add(txtPatient);
		 add(lblDate);add(txtDate);
		 add(lblRoomCharges);add(txtRoomCharges);
		 add(lblProCharges);add(txtProCharges);
		 add(lblDrCharges);add(txtDrCharges);
		 add(lblMedCharges);add(txtMedCharges);
		 add(lblOtherCharges);add(txtOtherCharges);
		 add(lblTotal);add(txtTotal);
		 add(lblAdvance);add(txtAdvance);
		 add(lblNet);add(txtNet);
		 add(btnDischarge);add(btnDiscard);
		 
		 
		 setSize(500,500);
		 setVisible(true);
		 Point p=CommonMethods.getCenterPoint(getSize());
		 setLocation(p.x,p.y-30);
	}
}
