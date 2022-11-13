package userInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import javax.swing.SwingConstants;

import ticketSystem.User;


public class MyAccount {
	
	private User user;
	private static JFrame jf;

	public MyAccount(User aUser) {
		this.user = aUser;
		new MyAccount();
	}

	private MyAccount() {
		jf = new JFrame("MyAccount");

		JLayeredPane layeredPane = new JLayeredPane();

		ImageIcon image = new ImageIcon("Source/ticketSystem/src/userInterface/ImgSource/background.jpeg");
		JLabel img = new JLabel(image);

		JPanel jp = new JPanel() {
			@Override
			public void paint(Graphics graphics) {
				super.paint(graphics);
				((Graphics2D) graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON); // Anti-Aliasing
				graphics.setColor(new Color(102, 147, 195));
				graphics.drawRoundRect(image.getIconWidth() - 490, 305, 425, 50, 50, 50); // border 1
				graphics.drawRoundRect(image.getIconWidth() - 490, 405, 425, 50, 50, 50); // border 2
				graphics.drawLine(image.getIconWidth() - 343, 305, image.getIconWidth() - 297, 355);
				graphics.drawLine(image.getIconWidth() - 263, 405, image.getIconWidth() - 217, 455);
			}
		};
		jp.setBounds(0, -5, image.getIconWidth(), image.getIconHeight());

		jp.add(img);

		JLabel username = new JLabel("Hi! " + user.getUsername(), SwingConstants.CENTER);
		username.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		username.setForeground(new Color(102, 147, 195));
		username.setBounds(image.getIconWidth() - 490, 200, 400, 50);
		
		JButton myOrder = new JButton("My Orders");
		myOrder.setBounds(image.getIconWidth() - 490, 300, 155, 50);
		myOrder.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		myOrder.setForeground(new Color(102, 147, 195));
		myOrder.setBorderPainted(false);
		myOrder.setFocusPainted(false);
		myOrder.setOpaque(false);

		myOrder.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				myOrder.setForeground(new Color(135, 206, 235));
			}

			public void mouseExited(MouseEvent evt) {
				myOrder.setForeground(new Color(102, 147, 195));
			}
		});
		myOrder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new MyOrders(user);
			}

		});
		
		JButton changePw = new JButton("Change Password");
		changePw.setBounds(image.getIconWidth() - 300, 300, 235, 50);
		changePw.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		changePw.setForeground(new Color(102, 147, 195));
		changePw.setBorderPainted(false);
		changePw.setFocusPainted(false);
		changePw.setOpaque(false);

		changePw.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				changePw.setForeground(new Color(135, 206, 235));
			}

			public void mouseExited(MouseEvent evt) {
				changePw.setForeground(new Color(102, 147, 195));
			}
		});
		changePw.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ChangePassword(user);
			}

		});
		
		JButton deleteMe = new JButton("Close Account");
		deleteMe.setBounds(image.getIconWidth() - 490, 400, 195, 50);
		deleteMe.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		deleteMe.setForeground(new Color(102, 147, 195));
		deleteMe.setBorderPainted(false);
		deleteMe.setFocusPainted(false);
		deleteMe.setOpaque(false);

		deleteMe.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				deleteMe.setForeground(new Color(135, 206, 235));
			}

			public void mouseExited(MouseEvent evt) {
				deleteMe.setForeground(new Color(102, 147, 195));
			}
		});
		deleteMe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ConfirmCloseAccount(user);
			}

		});
		
		JButton Logout = new JButton("Logout");
		Logout.setBounds(image.getIconWidth() - 190, 400, 120, 50);
		Logout.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		Logout.setForeground(new Color(102, 147, 195));
		Logout.setBorderPainted(false);
		Logout.setFocusPainted(false);
		Logout.setOpaque(false);

		Logout.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				Logout.setForeground(new Color(135, 206, 235));
			}

			public void mouseExited(MouseEvent evt) {
				Logout.setForeground(new Color(102, 147, 195));
			}
		});
		Logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SearchFlight.getInstance().logout();
				PurchaseTicket.logout();
				jf.dispose();
			}

		});
		
		JButton returnBtn = new JButton("Return");
		returnBtn.setBounds(image.getIconWidth() - 190, 500, 120, 50);
		returnBtn.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		returnBtn.setForeground(new Color(102, 147, 195));
		returnBtn.setBorderPainted(false);
		returnBtn.setFocusPainted(false);
		returnBtn.setOpaque(false);

		returnBtn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				returnBtn.setForeground(new Color(135, 206, 235));
			}

			public void mouseExited(MouseEvent evt) {
				returnBtn.setForeground(new Color(102, 147, 195));
			}
		});
		returnBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
			}

		});
		
		

		layeredPane.add(jp, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(username, JLayeredPane.MODAL_LAYER);
		layeredPane.add(myOrder, JLayeredPane.MODAL_LAYER);
		layeredPane.add(changePw, JLayeredPane.MODAL_LAYER);
		layeredPane.add(deleteMe, JLayeredPane.MODAL_LAYER);
		layeredPane.add(Logout, JLayeredPane.MODAL_LAYER);
		layeredPane.add(returnBtn, JLayeredPane.MODAL_LAYER);
		
		

		jf.setLayeredPane(layeredPane);
		jf.setSize(image.getIconWidth(), image.getIconHeight());
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
	}
	
	public static void CloseWindow() {
		jf.dispose();
	}
}