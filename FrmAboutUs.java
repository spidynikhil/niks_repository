package hms;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class FrmAboutus extends JDialog
{
	JLabel lblAboutus;
	JPanel top,bottom;
	JButton btnOk;
	FrmAboutus()
	{
		top=new JPanel();
		bottom=new JPanel();
		//bottom.setPreferredSize(new Dimension(10,15));	
		btnOk=new JButton("Okay!");	
		top.setLayout(new FlowLayout());
		setIconImage(new ImageIcon("images/HMS_logo.jpg").getImage());
		setTitle("About HMS");
		
		lblAboutus=new JLabel(new ImageIcon("images/AboutUs.jpg"));
		lblAboutus.setSize(416,280);
	
		top.add(lblAboutus);
		bottom.add(btnOk);
		btnOk.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				dispose();
			}
		});
		//setSize(425,290);
		setSize(425,315);
		add(top);
		add(bottom,"South");
		setVisible(true);
		setLocation(CommonMethods.getCenterPoint(getSize()));
		setResizable(false);
	}
}