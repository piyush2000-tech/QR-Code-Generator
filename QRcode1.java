import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

class QRcode1 implements ActionListener
{
	JFrame fr;
	JLabel l1,l6,lbrowse,l8,l9,l10;
	JButton b1,l2,l3,l4,l5,l7,lphoto,l11,l12;
	JTextField t2;
	JRadioButton r1,r2,r3;
	ButtonGroup bg;
	JTextArea t1;
	String path;
	Image original,scaled;
 	int p=0;

	public QRcode1()
	{
		Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
		fr = new JFrame("QRcode Generator");
		fr.setBounds((dim.width-800)/2,(dim.height-500)/2,800,500);
		fr.getContentPane().setBackground(Color.black);
		//fr.setForeground(Color.red);
		fr.setLayout(null);
		
		
		l2 = new JButton("QR Code Generator");
		l2.setBounds(40,18,350,30);
		l2.setFont(new Font("verdana",Font.BOLD,16));
		l2.setBackground(Color.green);
		l2.setForeground(Color.white);
		fr.add(l2);
		
		l3 = new JButton("<html><u>Generate</u></html>");
		l3.setBounds(270,150,110,30);
		l3.setForeground(Color.white);
		l3.setBackground(Color.green);
		l3.setFont(new Font("verdana",Font.BOLD,16));
		l3.addActionListener(this);
		fr.add(l3);
		
		l7 = new JButton("<html><u>Reset</u></html>");
		l7.setBounds(150,150,110,30);
		l7.setFont(new Font("verdana",Font.BOLD,16));
		l7.setForeground(Color.white);
		l7.setBackground(Color.green);
		l7.addActionListener(this);
		fr.add(l7);
		
		l8 = new JLabel("Enter the text here");
		l8.setBounds(150,52,180,45);
		l8.setForeground(Color.green);
		l8.setFont(new Font("verdana",Font.BOLD,16));
		fr.add(l8);
		
		t1 = new JTextArea();
		t1.setBounds(150,100,250,30);
		t1.setForeground(Color.blue);
		//t1.setBackground(Color.black);
		t1.setFont(new Font("times new roman",Font.BOLD,18));
		fr.add(t1);
		
		l4 = new JButton("<html><u>Save</u></html>");
		l4.setBounds(310,400,90,30);
		l4.setForeground(Color.white);
		l4.setBackground(Color.green);
		l4.setFont(new Font("verdana",Font.BOLD,18));
		fr.add(l4);
		l4.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				l4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			public void mouseClicked(MouseEvent me)
			{
				l6.setVisible(true);
				l9.setVisible(true);
				t2.setVisible(true);
				l10.setVisible(true);
				r1.setVisible(true);
				r2.setVisible(true);
				r3.setVisible(true);
				l11.setVisible(true);
				l12.setVisible(true);
			}
		});
		l5 = new JButton("<html><u>Back</u></html>");
		l5.setBounds(50,400,90,30);
		l5.setForeground(Color.white);
		l5.setBackground(Color.green);
		l5.setFont(new Font("verdana",Font.BOLD,18));
		fr.add(l5);
		l5.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				l5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			public void mouseClicked(MouseEvent me)
			{
				fr.dispose();
				new QRcode();
			}
		});
		
		
		
		lphoto = new JButton(new ImageIcon("photos/photo1.png"));
		lphoto.setBounds(40,52,100,100);
		fr.add(lphoto);
	
		
		
		l9 = new JLabel("Save QR Code");
		l9.setBounds(490,18,180,45);
		l9.setForeground(Color.white);
		l9.setBackground(Color.green);
		l9.setFont(new Font("verdana",Font.BOLD,18));
		l9.setVisible(false);
		fr.add(l9);
	
		l6 = new JLabel("File Name");
		l6.setBounds(480,52,150,45);
		l6.setForeground(Color.green);
		l6.setFont(new Font("verdana",Font.BOLD,16));
		l6.setVisible(false);
		fr.add(l6);
	
		t2 = new JTextField();
		t2.setBounds(480,100,195,30);
		t2.setForeground(Color.blue);
		t2.setFont(new Font("times new roman",Font.BOLD,18));
		t2.setVisible(false);
		fr.add(t2);
		
		l10 = new JLabel("Format:");
		l10.setBounds(480,130,100,45);
		l10.setForeground(Color.white);
		l10.setFont(new Font("verdana",Font.BOLD,16));
		l10.setVisible(false);
		fr.add(l10);
		
		bg = new ButtonGroup();
		r1 = new JRadioButton("PNG",true);
		r1.setBounds(500,190,70,20);
		r1.setForeground(Color.green);
		r1.setFont(new Font("verdana",Font.BOLD,16));
		r1.setOpaque(false);
		bg.add(r1);
		r1.setVisible(false);
		fr.add(r1);
		
		r2 = new JRadioButton("JPG");
		r2.setBounds(580,190,70,20);
		r2.setForeground(Color.green);
		r2.setFont(new Font("verdana",Font.BOLD,16));
		r2.setOpaque(false);
		bg.add(r2);
		r2.setVisible(false);
		fr.add(r2);
		
		r3 = new JRadioButton("GIF");
		r3.setBounds(660,190,70,20);
		r3.setForeground(Color.green);
		r3.setOpaque(false);
		r3.setFont(new Font("verdana",Font.BOLD,16));
		bg.add(r3);
		r3.setVisible(false);
		fr.add(r3);
		
		l11 = new JButton("<html><u>Save</u></html>");
		l11.setBounds(660,250,90,25);
		l11.setForeground(Color.white);
		l11.setBackground(Color.green);
		l11.setFont(new Font("verdana",Font.BOLD,18));
		l11.setVisible(false);
		fr.add(l11);
		l11.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				l11.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			public void mouseClicked(MouseEvent me)
			{
				
				if(t1.getText().length()==0){
					JOptionPane.showMessageDialog(fr,"Please generate QR Code before save image");
					t1.requestFocus();
				}
				else if(t2.getText().length()==0)
				{
					JOptionPane.showMessageDialog(fr,"File name can't leave blank");
					t2.requestFocus();
				}
				else if(p==0)
				{
					JOptionPane.showMessageDialog(fr,"Please generate QR Code before save image");
					t1.requestFocus();
				}
				else
				{
					if(r1.isSelected()==true)
					{
					int width = 100;
					int height = 100;
					BufferedImage image= null;
					
					//Icon icon = lphoto.getIcon();
					try{
						
						File inputFile = new File(t1.getText()+".png");
						image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB); 
						image = ImageIO.read(inputFile);
					}
					catch(Exception e){
					
					}	
					try{					
					File file = new File("photos/"+t2.getText()+".png");
					
					ImageIO.write(image,"png",file);
					}
					catch(Exception e){
						
					}
					}
					
				 	else if(r2.isSelected()==true)
					{
					int width = 100;
					int height = 100;
					BufferedImage image= null;
					
					//Icon icon = lphoto.getIcon();
					try{
						
						File inputFile = new File(t1.getText()+".png");
						image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB); 
						image = ImageIO.read(inputFile);
					}
					catch(Exception e){
					
					}	
					try{					
					File file = new File("photos/"+t2.getText()+".jpg");
					
					ImageIO.write(image,"jpg",file);
					}
					catch(Exception e){
						
					}
					}
					else if(r3.isSelected()==true)
					{
					int width = 100;
					int height = 100;
					BufferedImage image= null;
					
					//Icon icon = lphoto.getIcon();
					try{
						
						File inputFile = new File(t1.getText()+".png");
						image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB); 
						image = ImageIO.read(inputFile);
					}
					catch(Exception e){
					
					}	
					try{					
					File file = new File("photos/"+t2.getText()+".gif");
					
					ImageIO.write(image,"gif",file);
					}
					catch(Exception e){
						System.out.println(e);
					}
					}
					JOptionPane.showMessageDialog(fr,"File save successfully..........");	
				}	
			}
		});
		l12 = new JButton("<html><u>Cancel</u></html>");
		l12.setBounds(550,250,90,25);
		l12.setForeground(Color.white);
		l12.setBackground(Color.green);
		l12.setFont(new Font("verdana",Font.BOLD,18));
		l12.setVisible(false);
		fr.add(l12);
		l12.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				l12.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			public void mouseClicked(MouseEvent me)
			{
				t2.setText("");
				l6.setVisible(false);
				l9.setVisible(false);
				t2.setVisible(false);
				l10.setVisible(false);
				r1.setVisible(false);
				r2.setVisible(false);
				r3.setVisible(false);
				l11.setVisible(false);
				l12.setVisible(false);
			}
		});

		fr.setVisible(true);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==l3)
		{
			try
			{
				if(t1.getText().length()==0)
				{
					JOptionPane.showMessageDialog(fr,"Please enter text to generate QR Code");
					t1.requestFocus();
				}
				else
				{
					p=1;
					String qrCodeData = t1.getText();
					String filePath = t1.getText()+".png";
					String charset = "UTF-8"; // or "ISO-8859-1"
					Map hintMap = new HashMap();
					hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
	
					createQRCode(qrCodeData, filePath, charset, hintMap, 100, 100);
					System.out.println("QR Code image created successfully!");
		
					System.out.println("Data read from QR Code: "+ readQRCode(filePath, charset, hintMap));
					lphoto.setIcon(new ImageIcon(getClass().getResource(t1.getText()+".png")));
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		if(ae.getSource()==l7)
		{
			p=0;
			lphoto.setIcon(new ImageIcon("photos/photo1.png"));
			t1.setText("");
		}
	}
	
	public static void createQRCode(String qrCodeData, String filePath,
			String charset, Map hintMap, int qrCodeheight, int qrCodewidth)
			throws WriterException, IOException {
		BitMatrix matrix = new MultiFormatWriter().encode(
				new String(qrCodeData.getBytes(charset), charset),
				BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
		MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
				.lastIndexOf('.') + 1), new File(filePath));
	}

	public static String readQRCode(String filePath, String charset, Map hintMap)
			throws FileNotFoundException, IOException, NotFoundException {
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
				new BufferedImageLuminanceSource(
						ImageIO.read(new FileInputStream(filePath)))));
		Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap,
				hintMap);
		return qrCodeResult.getText();
	}
	public static void main(String args[])
	{
		new QRcode1();
	}
}