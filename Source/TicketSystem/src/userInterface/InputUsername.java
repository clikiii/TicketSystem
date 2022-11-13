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
import userInterface.UIException.ExUsernameIsEmpty;
import userInterface.UIException.ExUsernameIsNotFound;

public class InputUsername {
	protected User user = null;

	public InputUsername() {
		JFrame jf = new JFrame("InputUsername");

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
				graphics.drawRoundRect(image.getIconWidth() - 450, 320, 300, 50, 50, 50);
			}
		};
		jp.setBounds(0, -5, image.getIconWidth(), image.getIconHeight());

		jp.add(img);

		JLabel jl0 = new JLabel("Input Username");
		jl0.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		jl0.setForeground(new Color(102, 147, 195));
		jl0.setBounds(image.getIconWidth() - 405, 230, 300, 50);

		JLabel jl1 = new JLabel("Username:");
		jl1.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 12));
		jl1.setForeground(new Color(102, 147, 195));
		jl1.setBounds(image.getIconWidth() - 435, 300, 250, 50);

		JTextField usr = new JTextField(10);
		usr.addFocusListener(new JTextFieldHintListener(usr, "Within 10 characters"));
		usr.setOpaque(false);
		usr.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
		usr.setBounds(image.getIconWidth() - 435, 320, 280, 50);

		JButton query = new JButton("Query");
		query.setBounds(image.getIconWidth() - 450, 395, 110, 50);
		query.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		query.setForeground(new Color(102, 147, 195));
		query.setBorderPainted(false);
		query.setFocusPainted(false);
		query.setOpaque(false);

		query.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				query.setForeground(new Color(135, 206, 235));
			}

			public void mouseExited(MouseEvent evt) {
				query.setForeground(new Color(102, 147, 195));
			}
		});
		query.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (false)
						throw new ExUsernameIsEmpty();
					else if (false)
						throw new ExUsernameIsNotFound();
					else {
						// user = (call backend func.) usr.getText()
						new QueryUserOrders(user);
						jf.dispose();
					}
				} catch (ExUsernameIsNotFound e1) {
					e1.printStackTrace();
				} catch (ExUsernameIsEmpty e2) {
					e2.printStackTrace();
				}
			}

		});

		JButton cancel = new JButton("Cancel");
		cancel.setBounds(image.getIconWidth() - 275, 395, 120, 50);
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
		layeredPane.add(query, JLayeredPane.MODAL_LAYER);
		layeredPane.add(cancel, JLayeredPane.MODAL_LAYER);

		jf.setLayeredPane(layeredPane);
		jf.setSize(image.getIconWidth(), image.getIconHeight());
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
	}
}
