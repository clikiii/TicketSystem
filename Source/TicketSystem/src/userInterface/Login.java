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

import ticketSystem.TicketSystem;
import ticketSystem.User;
import userInterface.UIException.ExUsernameIsNotFound;
import userInterface.UIException.ExPasswordIsWrong;
import userInterface.UIException.ExUsernameIsEmpty;

public class Login {

	private JTextField usr;
	private JPasswordField pw;

	public Login() {
		TicketSystem ticketSystem = TicketSystem.start();
		JFrame jf = new JFrame("Login");

		JLayeredPane layeredPane = new JLayeredPane();

		ImageIcon image = new ImageIcon("Source/ticketSystem/src/userInterface/ImgSource/background.jpeg");
		JLabel img = new JLabel(image);

		JPanel jp = new JPanel() {
			@Override
			public void paint(Graphics graphics) {
				super.paint(graphics);
				((Graphics2D) graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON); // Anti-Aliasing
//				BasicStroke stokeLine = new BasicStroke(1);
//				((Graphics2D) graphics).setStroke(stokeLine);
				graphics.setColor(new Color(102, 147, 195));
				graphics.drawRoundRect(image.getIconWidth() - 450, 320, 300, 50, 50, 50); // border 1
				graphics.drawRoundRect(image.getIconWidth() - 450, 390, 300, 50, 50, 50); // border 2
			}
		};
		jp.setBounds(0, -5, image.getIconWidth(), image.getIconHeight());

		jp.add(img);

		JLabel jl0 = new JLabel("LOG IN");
		jl0.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		jl0.setForeground(new Color(102, 147, 195));
		jl0.setBounds(image.getIconWidth() - 355, 230, 300, 50);

		JLabel jl1 = new JLabel("Username:");
		jl1.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 12));
		jl1.setForeground(new Color(102, 147, 195));
		jl1.setBounds(image.getIconWidth() - 435, 300, 250, 50);

		usr = new JTextField(10);
		usr.addFocusListener(new JTextFieldHintListener(usr, "Within 10 characters"));
		usr.setOpaque(false);
		usr.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
		usr.setBounds(image.getIconWidth() - 435, 320, 280, 50);

		JLabel jl2 = new JLabel("Password:");
		jl2.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 12));
		jl2.setForeground(new Color(102, 147, 195));
		jl2.setBounds(image.getIconWidth() - 435, 370, 250, 50);

		pw = new JPasswordField(10);
		pw.addFocusListener(new JPasswordFieldHintListenser(pw, "Within 10 characters"));
		pw.setOpaque(false);
		pw.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
		pw.setBounds(image.getIconWidth() - 435, 390, 280, 50);

		JButton login = new JButton("Login");
		login.setBounds(image.getIconWidth() - 450, 465, 110, 50);
		login.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		login.setForeground(new Color(102, 147, 195));
		login.setBorderPainted(false);
		login.setFocusPainted(false);
		login.setOpaque(false);

		login.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				login.setForeground(new Color(135, 206, 235));
			}

			public void mouseExited(MouseEvent evt) {
				login.setForeground(new Color(102, 147, 195));
			}
		});
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (usr.getText().equals("admin") && new String(pw.getPassword()).equals("admin")) { // admin
						new AdminPage();
						jf.dispose();
					} else if (usr.getText().equals("Within 10 characters"))
						throw new ExUsernameIsEmpty();
					else if (ticketSystem.login(usr.getText(), new String(pw.getPassword())) == null)
						throw new ExUsernameIsNotFound(); // case1: username is not found
					else if (((User) ticketSystem.login(usr.getText(), new String(pw.getPassword()))).getUsername()
							.equals("password wrong"))
						throw new ExPasswordIsWrong(); // case2: password is wrong
					else {
						// case3: login successfully
						SearchFlight.getInstance()
								.setAccountBtn((User) ticketSystem.login(usr.getText(), new String(pw.getPassword())));
						if (!PurchaseTicket.notCreate())
							PurchaseTicket.setAccountBtn(
									(User) ticketSystem.login(usr.getText(), new String(pw.getPassword())));
						jf.dispose();
					}
				} catch (ExUsernameIsEmpty e1) {
					e1.printStackTrace();
				} catch (ExPasswordIsWrong e2) {
					e2.printStackTrace();
				} catch (ExUsernameIsNotFound e3) {
					e3.printStackTrace();
				}
			}

		});

		JButton cancel = new JButton("Cancel");
		cancel.setBounds(image.getIconWidth() - 275, 465, 120, 50);
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
		layeredPane.add(jl1, JLayeredPane.MODAL_LAYER);
		layeredPane.add(usr, JLayeredPane.MODAL_LAYER);
		layeredPane.add(jl2, JLayeredPane.MODAL_LAYER);
		layeredPane.add(pw, JLayeredPane.MODAL_LAYER);
		layeredPane.add(login, JLayeredPane.MODAL_LAYER);
		layeredPane.add(cancel, JLayeredPane.MODAL_LAYER);

		jf.setLayeredPane(layeredPane);
		jf.setSize(image.getIconWidth(), image.getIconHeight());
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
	}
}