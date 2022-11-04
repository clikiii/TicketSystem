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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ticketSystem.User;
import userInterface.UIException.ExPasswordIsWrong;


public class MyAccount {
	
	private User user;

	public MyAccount(User aUser) {
		this.user = aUser;
		new MyAccount();
	}

	private MyAccount() {
		JFrame jf = new JFrame("MyAccount");

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
//				graphics.drawRoundRect(image.getIconWidth() - 450, 320, 300, 50, 50, 50); // border 1
//				graphics.drawRoundRect(image.getIconWidth() - 450, 390, 300, 50, 50, 50); // border 2
			}
		};
		jp.setBounds(0, -5, image.getIconWidth(), image.getIconHeight());

		jp.add(img);

		JLabel username = new JLabel("Hi! WWWWWWWWWW");
		username.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		username.setForeground(new Color(102, 147, 195));
		username.setBounds(image.getIconWidth() - 390, 230, 375, 50);

		layeredPane.add(jp, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(username, JLayeredPane.MODAL_LAYER);

		
		

		jf.setLayeredPane(layeredPane);
		jf.setSize(image.getIconWidth(), image.getIconHeight());
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
	}
}