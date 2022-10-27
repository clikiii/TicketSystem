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

public class SearchFlight {

	public static void main(String[] args) {
		new SearchFlight();
	}

	public SearchFlight() {
		JFrame jf = new JFrame("Air Ticket Booking System");

		JLayeredPane layeredPane = new JLayeredPane();
		ImageIcon image = new ImageIcon("Source/ticketSystem/src/userInterface/ImgSource/background.jpeg");
		JPanel jp = new JPanel();
		jp.setBounds(0, -5, image.getIconWidth(), image.getIconHeight());

		JLabel jl = new JLabel(image);
		jp.add(jl);

		JButton loginBtn = new JButton("Login");
		loginBtn.setBounds(image.getIconWidth() - 205, 0, 95, 50);
		loginBtn.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 20));
		loginBtn.setForeground(new Color(102, 147, 195));
		loginBtn.setBorderPainted(false);
		loginBtn.setFocusPainted(false);
		loginBtn.setOpaque(false);
		loginBtn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				loginBtn.setForeground(new Color(135, 206, 235));
			}

			public void mouseExited(MouseEvent e) {
				loginBtn.setForeground(new Color(102, 147, 195));
			}
		});
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Login.getInstance();
			}
		});

		JButton signupBtn = new JButton("Sign up");
		signupBtn.setBounds(image.getIconWidth() - 110, 0, 110, 50);
		signupBtn.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 20));
		signupBtn.setForeground(new Color(102, 147, 195));
		signupBtn.setBorderPainted(false);
		signupBtn.setFocusPainted(false);
		signupBtn.setOpaque(false);
		signupBtn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				signupBtn.setForeground(new Color(135, 206, 235));
			}

			public void mouseExited(MouseEvent evt) {
				signupBtn.setForeground(new Color(102, 147, 195));
			}
		});
		signupBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Signup.getInstance();
			}
		});

		layeredPane.add(jp, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(loginBtn, JLayeredPane.MODAL_LAYER);
		layeredPane.add(signupBtn, JLayeredPane.MODAL_LAYER);

		jf.setLayeredPane(layeredPane);
		jf.setSize(image.getIconWidth(), image.getIconHeight());
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}

}