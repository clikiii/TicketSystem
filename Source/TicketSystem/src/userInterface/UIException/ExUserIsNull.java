package userInterface.UIException;

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

import userInterface.Login;
import userInterface.Signup;


public class ExUserIsNull extends Exception {
	public ExUserIsNull() {
		super("Please login or sign up first.");
		JFrame jf = new JFrame("ExUserIsNull");

		JLayeredPane layeredPane = new JLayeredPane();
		ImageIcon image = new ImageIcon(getClass().getResource("warning.jpeg"));
		JPanel jp = new JPanel();
		jp.setBounds(0, -5, image.getIconWidth(), image.getIconHeight() + 22);

		JLabel jl = new JLabel(image);
		jp.add(jl);

		JLabel text = new JLabel("Please login or sign up first.");
		text.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		text.setForeground(new Color(102, 146, 235));
		text.setBounds(317, 85, 278, 50);

		JButton Signup = new JButton("Signup");
		Signup.setBounds(image.getIconWidth() - 125, image.getIconHeight() - 55, 125, 50);
		Signup.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 27));
		Signup.setForeground(Color.WHITE);
		Signup.setBorderPainted(false);
		Signup.setFocusPainted(false);
		Signup.setOpaque(false);
		Signup.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				Signup.setForeground(Color.LIGHT_GRAY);
			}

			public void mouseExited(MouseEvent evt) {
				Signup.setForeground(Color.WHITE);
			}
		});
		Signup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Signup();
				jf.dispose();
			}
		});
		
		JButton login = new JButton("Login");
		login.setBounds(image.getIconWidth() - 260, image.getIconHeight() - 55, 120, 50);
		login.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 27));
		login.setForeground(Color.WHITE);
		login.setBorderPainted(false);
		login.setFocusPainted(false);
		login.setOpaque(false);
		login.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				login.setForeground(Color.LIGHT_GRAY);
			}

			public void mouseExited(MouseEvent evt) {
				login.setForeground(Color.WHITE);
			}
		});
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Login();
				jf.dispose();
			}
		});

		layeredPane.add(jp, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(text, JLayeredPane.MODAL_LAYER);
		layeredPane.add(Signup, JLayeredPane.MODAL_LAYER);
		layeredPane.add(login, JLayeredPane.MODAL_LAYER);

		jf.setLayeredPane(layeredPane);
		jf.setSize(image.getIconWidth(), image.getIconHeight() + 22);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
	}

	public ExUserIsNull(String msg) {
		super(msg);
	}
}
