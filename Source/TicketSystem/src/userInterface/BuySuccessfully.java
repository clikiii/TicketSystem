package userInterface;

import java.awt.Color;
import java.awt.Font;
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

import ticketSystem.User;

public class BuySuccessfully{

	protected User user;

	public BuySuccessfully(User aUser) {
		this.user = aUser;
		JFrame jf = new JFrame("BuySuccessfully");

		JLayeredPane layeredPane = new JLayeredPane();
		ImageIcon image = new ImageIcon("Source/ticketSystem/src/userInterface/ImgSource/warning.jpeg");
		JPanel jp = new JPanel();
		jp.setBounds(0, -5, image.getIconWidth(), image.getIconHeight() + 22);

		JLabel jl = new JLabel(image);
		jp.add(jl);

		JLabel text = new JLabel("Purchase Successfully!");
		text.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		text.setForeground(new Color(102, 146, 235));
		text.setBounds(322, 85, 278, 50);

		JButton MyOrders = new JButton("MyOrders");
		MyOrders.setBounds(image.getIconWidth() -265, image.getIconHeight() - 55, 160, 50);
		MyOrders.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 27));
		MyOrders.setForeground(Color.WHITE);
		MyOrders.setBorderPainted(false);
		MyOrders.setFocusPainted(false);
		MyOrders.setOpaque(false);
		MyOrders.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				MyOrders.setForeground(Color.LIGHT_GRAY);
			}

			public void mouseExited(MouseEvent evt) {
				MyOrders.setForeground(Color.WHITE);
			}
		});
		MyOrders.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MyOrders(user);
				jf.dispose();
			}
		});
		
		JButton exit = new JButton("OK");
		exit.setBounds(image.getIconWidth() - 90, image.getIconHeight() - 55, 90, 50);
		exit.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 27));
		exit.setForeground(Color.WHITE);
		exit.setBorderPainted(false);
		exit.setFocusPainted(false);
		exit.setOpaque(false);
		exit.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				exit.setForeground(Color.LIGHT_GRAY);
			}

			public void mouseExited(MouseEvent evt) {
				exit.setForeground(Color.WHITE);
			}
		});
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
			}
		});

		layeredPane.add(jp, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(text, JLayeredPane.MODAL_LAYER);
		layeredPane.add(exit, JLayeredPane.MODAL_LAYER);
		layeredPane.add(MyOrders, JLayeredPane.MODAL_LAYER);


		jf.setLayeredPane(layeredPane);
		jf.setSize(image.getIconWidth(), image.getIconHeight() + 22);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(CloseWindow());
		jf.setVisible(true);
	}

	private int CloseWindow() {
		SelectPassengers.CloseWindow();
		PurchaseTicket.CloseWindow();
		return JFrame.DISPOSE_ON_CLOSE;
	}
}
