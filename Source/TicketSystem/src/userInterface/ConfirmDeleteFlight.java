package userInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import ticketSystem.Flight;
import ticketSystem.Order;
import ticketSystem.User;
import ticketSystem.database.dbException.ExDbOrderNotFound;

public class ConfirmDeleteFlight {

	public ConfirmDeleteFlight(int orderIdx, User user, Order order) {
		JFrame jf = new JFrame("ConfirmDeleteFlight");

		JLayeredPane layeredPane = new JLayeredPane();
		ImageIcon image = new ImageIcon("Source/ticketSystem/src/userInterface/ImgSource/warning.jpeg");
		JPanel jp = new JPanel();
		jp.setBounds(0, -5, image.getIconWidth(), image.getIconHeight() + 22);

		JLabel jl = new JLabel(image);
		jp.add(jl);

		JLabel text = new JLabel("Are you sure to delete this flight?");
		text.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		text.setForeground(new Color(102, 146, 235));
		text.setBounds(318, 85, 278, 50);
		
		JButton Yes = new JButton("Yes");
		Yes.setBounds(image.getIconWidth() - 200, image.getIconHeight() - 55, 85, 50);
		Yes.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 27));
		Yes.setForeground(Color.WHITE);
		Yes.setBorderPainted(false);
		Yes.setFocusPainted(false);
		Yes.setOpaque(false);
		Yes.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				Yes.setForeground(Color.LIGHT_GRAY);
			}

			public void mouseExited(MouseEvent evt) {
				Yes.setForeground(Color.WHITE);
			}
		});
		Yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {			    
			    String flightSet = order.getFlightSetObjList().size() == 1
                        ? order.getFlightSetObjList().get(0).getFlightIndex() + ""
                        : order.getFlightSetObjList().get(0).getFlightIndex() + " "
                                + order.getFlightSetObjList().get(1).getFlightIndex();
                int changeNum = order.getNumber();
                try {
                    user.cancelOrder(orderIdx, flightSet, changeNum);
                } catch (ExDbOrderNotFound e1) {
                    e1.printStackTrace();
                }
			    
				MyOrders.CloseWindow();
				new MyOrders(user);
				jf.dispose();
			}
		});

		JButton No = new JButton("No");
		No.setBounds(image.getIconWidth() - 100, image.getIconHeight() - 55, 80, 50);
		No.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 27));
		No.setForeground(Color.WHITE);
		No.setBorderPainted(false);
		No.setFocusPainted(false);
		No.setOpaque(false);
		No.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				No.setForeground(Color.LIGHT_GRAY);
			}

			public void mouseExited(MouseEvent evt) {
				No.setForeground(Color.WHITE);
			}
		});
		No.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
			}
		});

		layeredPane.add(jp, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(text, JLayeredPane.MODAL_LAYER);
		layeredPane.add(Yes, JLayeredPane.MODAL_LAYER);
		layeredPane.add(No, JLayeredPane.MODAL_LAYER);

		jf.setLayeredPane(layeredPane);
		jf.setSize(image.getIconWidth(), image.getIconHeight() + 22);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
	}

}
