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

import ticketSystem.User;
import userInterface.UIException.ExPasswordIsEmpty;
import userInterface.UIException.ExPasswordIsWrong;
import userInterface.UIException.ExPasswordOutOfRange;
import userInterface.UIException.ExTwoPasswordDifferent;
import userInterface.UIException.ExOldPwAndNewPwAreSame;

public class ChangePassword {

	private User user;

	public ChangePassword(User aUser) {
		this.user = aUser;
		new ChangePassword();
	}

	private ChangePassword() {
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

		JLabel jl0 = new JLabel("Change Password");
		jl0.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		jl0.setForeground(new Color(102, 147, 195));
		jl0.setBounds(image.getIconWidth() - 410, 230, 300, 50);

		JLabel jl1 = new JLabel("Old Password:");
		jl1.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 12));
		jl1.setForeground(new Color(102, 147, 195));
		jl1.setBounds(image.getIconWidth() - 435, 300, 250, 50);

		JPasswordField oldPw = new JPasswordField(10);
		oldPw.setOpaque(false);
		oldPw.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
		oldPw.setBounds(image.getIconWidth() - 435, 320, 280, 50);

		JLabel jl2 = new JLabel("New Password:");
		jl2.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 12));
		jl2.setForeground(new Color(102, 147, 195));
		jl2.setBounds(image.getIconWidth() - 435, 370, 250, 50);

		JPasswordField newPw = new JPasswordField(10);
		newPw.addFocusListener(new JPasswordFieldHintListenser(newPw, "Within 10 characters"));
		newPw.setOpaque(false);
		newPw.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
		newPw.setBounds(image.getIconWidth() - 435, 390, 280, 50);

		JLabel jl3 = new JLabel("Confirm New Password:");
		jl3.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 12));
		jl3.setForeground(new Color(102, 147, 195));
		jl3.setBounds(image.getIconWidth() - 435, 440, 250, 50);

		JPasswordField cNewPw = new JPasswordField(10);
		cNewPw.addFocusListener(new JPasswordFieldHintListenser(cNewPw, "Within 10 characters"));
		cNewPw.setOpaque(false);
		cNewPw.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
		cNewPw.setBounds(image.getIconWidth() - 435, 460, 280, 50);

		JButton OK = new JButton("Confirm");
		OK.setBounds(image.getIconWidth() - 450, 540, 140, 50);
		OK.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		OK.setForeground(new Color(102, 147, 195));
		OK.setBorderPainted(false);
		OK.setFocusPainted(false);
		OK.setOpaque(false);

		OK.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				OK.setForeground(new Color(135, 206, 235));
			}

			public void mouseExited(MouseEvent evt) {
				OK.setForeground(new Color(102, 147, 195));
			}
		});
		OK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (new String(oldPw.getPassword()).equals("")
							|| new String(newPw.getPassword()).equals("Within 10 characters"))
						throw new ExPasswordIsEmpty();
					else if (new String(newPw.getPassword()).length() > 10)
						throw new ExPasswordOutOfRange();
					else if (!new String(newPw.getPassword()).equals(new String(cNewPw.getPassword())))
						throw new ExTwoPasswordDifferent();
					else if (false)
						throw new ExPasswordIsWrong(); // password is wrong
					else if (new String(oldPw.getPassword()).equals(new String(newPw.getPassword())))
						throw new ExOldPwAndNewPwAreSame();
					else {
						// change password successfully
//						user.changePwd(user.getUserName(), null, new String(newPw.getPassword()));
						jf.dispose();
					}
				} catch (ExPasswordIsEmpty e1) {
					e1.printStackTrace();
				} catch (ExPasswordOutOfRange e2) {
					e2.printStackTrace();
				} catch (ExTwoPasswordDifferent e3) {
					e3.printStackTrace();
				} catch (ExPasswordIsWrong e4) {
					e4.printStackTrace();
				} catch (ExOldPwAndNewPwAreSame e5) {
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
		layeredPane.add(oldPw, JLayeredPane.MODAL_LAYER);
		layeredPane.add(jl2, JLayeredPane.MODAL_LAYER);
		layeredPane.add(newPw, JLayeredPane.MODAL_LAYER);
		layeredPane.add(jl3, JLayeredPane.MODAL_LAYER);
		layeredPane.add(cNewPw, JLayeredPane.MODAL_LAYER);
		layeredPane.add(OK, JLayeredPane.MODAL_LAYER);
		layeredPane.add(cancel, JLayeredPane.MODAL_LAYER);

		jf.setLayeredPane(layeredPane);
		jf.setSize(image.getIconWidth(), image.getIconHeight());
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
	}

}
