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

import userInterface.UIException.ExUsernameIsEmpty;
import userInterface.UIException.ExUsernameOutOfRange;
import userInterface.UIException.ExPasswordIsEmpty;
import userInterface.UIException.ExPasswordOutOfRange;
import userInterface.UIException.ExTwoPasswordDifferent;

public class Signup {
	
	private JTextField usr;
	private JPasswordField pw;

	public Signup() {
		JFrame jf = new JFrame("Sign up");

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
				graphics.drawRoundRect(image.getIconWidth() - 450, 320, 300, 50, 50, 50); // border 1
				graphics.drawRoundRect(image.getIconWidth() - 450, 390, 300, 50, 50, 50); // border 2
				graphics.drawRoundRect(image.getIconWidth() - 450, 460, 300, 50, 50, 50); // border 3

			}
		};
		jp.setBounds(0, -5, image.getIconWidth(), image.getIconHeight());

		jp.add(img);

		JLabel jl0 = new JLabel("SIGN UP");
		jl0.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		jl0.setForeground(new Color(102, 147, 195));
		jl0.setBounds(image.getIconWidth() - 360, 230, 300, 50);

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

		JLabel jl3 = new JLabel("Confirm Password:");
		jl3.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 12));
		jl3.setForeground(new Color(102, 147, 195));
		jl3.setBounds(image.getIconWidth() - 435, 440, 250, 50);

		JPasswordField cpw = new JPasswordField(10);
		cpw.addFocusListener(new JPasswordFieldHintListenser(cpw, "Within 10 characters"));
		cpw.setOpaque(false);
		cpw.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
		cpw.setBounds(image.getIconWidth() - 435, 460, 280, 50);

		JButton signup = new JButton("Sign up");
		signup.setBounds(image.getIconWidth() - 450, 540, 130, 50);
		signup.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		signup.setForeground(new Color(102, 147, 195));
		signup.setBorderPainted(false);
		signup.setFocusPainted(false);
		signup.setOpaque(false);

		signup.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				signup.setForeground(new Color(135, 206, 235));
			}

			public void mouseExited(MouseEvent evt) {
				signup.setForeground(new Color(102, 147, 195));
			}
		});
		signup.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (usr.getText().equals("Within 10 characters"))
						throw new ExUsernameIsEmpty();
					else if (usr.getText().length() > 10)
						throw new ExUsernameOutOfRange();
					else if (new String(pw.getPassword()).equals("Within 10 characters"))
						throw new ExPasswordIsEmpty();
					else if (new String(pw.getPassword()).length() > 10)
						throw new ExPasswordOutOfRange();
					else if (!new String(pw.getPassword()).equals(new String(cpw.getPassword())))
						throw new ExTwoPasswordDifferent();
					// case6: else if (username is already existed)
					// case7: else {sign-up successfully --- get start ---}
				} catch (ExUsernameIsEmpty e1) {
					e1.printStackTrace();
				} catch (ExUsernameOutOfRange e2) {
					e2.printStackTrace();
				} catch (ExPasswordIsEmpty e3) {
					e3.printStackTrace();
				} catch (ExPasswordOutOfRange e4) {
					e4.printStackTrace();
				} catch (ExTwoPasswordDifferent e5) {
					e5.printStackTrace();
				}

			}

		});

		JButton cancel = new JButton("Cancel");
		cancel.setBounds(image.getIconWidth() - 275, 540, 120, 50);
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
		layeredPane.add(jl3, JLayeredPane.MODAL_LAYER);
		layeredPane.add(cpw, JLayeredPane.MODAL_LAYER);
		layeredPane.add(signup, JLayeredPane.MODAL_LAYER);
		layeredPane.add(cancel, JLayeredPane.MODAL_LAYER);

		jf.setLayeredPane(layeredPane);
		jf.setSize(image.getIconWidth(), image.getIconHeight());
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
	}
}