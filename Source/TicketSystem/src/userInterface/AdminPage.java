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
import ticketSystem.Admin;

public class AdminPage {
	private Admin admin;

    public AdminPage(Admin aAdmin) {
		this.admin = aAdmin;
		JFrame jf = new JFrame("AdminPage");

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
				graphics.drawRoundRect(image.getIconWidth() - 420, 305, 270, 50, 50, 50); // border 1
				graphics.drawRoundRect(image.getIconWidth() - 420, 405, 270, 50, 50, 50); // border 2
			}
		};
		jp.setBounds(0, -5, image.getIconWidth(), image.getIconHeight());

		jp.add(img);

		JLabel jl0 = new JLabel("Admin Page");
		jl0.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		jl0.setForeground(new Color(102, 147, 195));
		jl0.setBounds(image.getIconWidth() - 355, 220, 300, 50);

		JButton queryAll = new JButton("Query All Orders");
		queryAll.setBounds(image.getIconWidth() - 410, 300, 250, 50);
		queryAll.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		queryAll.setForeground(new Color(102, 147, 195));
		queryAll.setBorderPainted(false);
		queryAll.setFocusPainted(false);
		queryAll.setOpaque(false);

		queryAll.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				queryAll.setForeground(new Color(135, 206, 235));
			}

			public void mouseExited(MouseEvent evt) {
				queryAll.setForeground(new Color(102, 147, 195));
			}
		});
		queryAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new QueryAllOrders(admin.getAllOrder());
			}

		});
		
		JButton queryUser = new JButton("Query User Orders");
		queryUser.setBounds(image.getIconWidth() - 410, 400, 250, 50);
		queryUser.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		queryUser.setForeground(new Color(102, 147, 195));
		queryUser.setBorderPainted(false);
		queryUser.setFocusPainted(false);
		queryUser.setOpaque(false);

		queryUser.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				queryUser.setForeground(new Color(135, 206, 235));
			}

			public void mouseExited(MouseEvent evt) {
				queryUser.setForeground(new Color(102, 147, 195));
			}
		});
		queryUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new InputUsername(admin);
			}

		});

		JButton cancel = new JButton("Return");
		cancel.setBounds(image.getIconWidth() - 275, 475, 120, 50);
		cancel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		cancel.setForeground(new Color(102, 147, 195));
		cancel.setBorderPainted(false);
		cancel.setFocusPainted(false);
		cancel.setOpaque(false);

		cancel.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				cancel.setForeground(new Color(135, 206, 235));
			}

			public void mouseExited(MouseEvent evt) {
				cancel.setForeground(new Color(102, 147, 195));
			}
		});
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
			}

		});

		layeredPane.add(jp, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(jl0, JLayeredPane.MODAL_LAYER);
		layeredPane.add(cancel, JLayeredPane.MODAL_LAYER);
		layeredPane.add(queryAll, JLayeredPane.MODAL_LAYER);
		layeredPane.add(queryUser, JLayeredPane.MODAL_LAYER);

		jf.setLayeredPane(layeredPane);
		jf.setSize(image.getIconWidth(), image.getIconHeight());
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
	}
}
