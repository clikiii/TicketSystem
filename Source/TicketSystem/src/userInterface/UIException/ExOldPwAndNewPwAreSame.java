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

public class ExOldPwAndNewPwAreSame extends Exception {

	public ExOldPwAndNewPwAreSame() {
		super("The departure city and destination city are the same.");
		JFrame jf = new JFrame("ExOldPwAndNewPwAreSame");

		JLayeredPane layeredPane = new JLayeredPane();
		ImageIcon image = new ImageIcon("Source/ticketSystem/src/userInterface/ImgSource/warning.jpeg");
		JPanel jp = new JPanel();
		jp.setBounds(0, -5, image.getIconWidth(), image.getIconHeight() + 22);

		JLabel jl = new JLabel(image);
		jp.add(jl);

		JLabel text = new JLabel("Old and new passwords cannot be the same.");
		text.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		text.setForeground(new Color(102, 146, 235));
		text.setBounds(311, 87, 289, 50);

		JButton ok = new JButton("OK");
		ok.setBounds(image.getIconWidth() - 100, image.getIconHeight() - 55, 90, 50);
		ok.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 27));
		ok.setForeground(Color.WHITE);
		ok.setBorderPainted(false);
		ok.setFocusPainted(false);
		ok.setOpaque(false);
		ok.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				ok.setForeground(Color.LIGHT_GRAY);
			}

			public void mouseExited(MouseEvent evt) {
				ok.setForeground(Color.WHITE);
			}
		});
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
			}
		});

		layeredPane.add(jp, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(text, JLayeredPane.MODAL_LAYER);
		layeredPane.add(ok, JLayeredPane.MODAL_LAYER);

		jf.setLayeredPane(layeredPane);
		jf.setSize(image.getIconWidth(), image.getIconHeight() + 22);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
	}

	public ExOldPwAndNewPwAreSame(String msg) {
		super(msg);
	}
}
