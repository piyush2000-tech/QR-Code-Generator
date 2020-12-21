import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class QRcode
{
	JFrame fr;
	JLabel l1,l4,lphoto;
	JButton b1,l2,l3;
	public QRcode()
	{
		Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
		fr = new JFrame("QRcode Generator");
		fr.setBounds((dim.width-1000)/2,(dim.height-700)/2,1000,700);
		fr.getContentPane().setBackground(Color.black);
		//fr.setForeground(Color.red);
		fr.setLayout(null);
		
		
		l3 = new JButton("<html><u>QR Code Generator</u></html>");
		l3.setBounds(150,120,600,70);
		l3.setFont(new Font("verdana",Font.BOLD,42));
		l3.setBackground(Color.green);
		l3.setForeground(Color.white);
		fr.add(l3);
		
		l4 = new JLabel("<html><u>Next-></u></html>");
		l4.setBounds(800,550,90,30);
		l4.setFont(new Font("verdana",Font.BOLD,18));
		l4.setBackground(Color.green);
		l4.setForeground(Color.white);
		fr.add(l4);
		l4.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				l4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			public void mouseClicked(MouseEvent me)
			{
				fr.dispose();
				new QRcode1();
			}
		});
		
		lphoto = new JLabel(new ImageIcon("photos/qrimg.png"));
		lphoto.setBounds(400,300,128,128);
		fr.add(lphoto);
		fr.setVisible(true);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	public static void main(String args[])
	{
		new QRcode();
	}
}