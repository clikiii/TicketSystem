package userInterface;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import ticketSystem.Flight;
import ticketSystem.User;

public class SelectPassengers {

	private ArrayList<Flight> flight;
	private JLabel passengers;
	protected int num = 1;
	protected User user;
	private static JFrame jf;

	public SelectPassengers() { // ArrayList<Flight> aFlight  User aUser TODO
//		this.flight = aFlight; TODO
//		this.user = aUser; TODO
		if (false) // flight.size()==1 TODO
			oneFlightJFrame();
		else
			twoFlightJFrame();
	}

	private void twoFlightJFrame() {
		jf = new JFrame("Select Passengers");

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
				((Graphics2D) graphics).setStroke(new BasicStroke(2f));
				graphics.drawArc(1100, 130, 18, 18, 0, 360);
				graphics.drawArc(1100, 318 - 70, 18, 18, 0, 360);
				graphics.drawLine(1109, 218 - 70, 1109, 228 - 70);
				graphics.drawLine(1109, 233 - 70, 1109, 243 - 70);
				graphics.drawLine(1109, 248 - 70, 1109, 258 - 70);
				graphics.drawLine(1109, 263 - 70, 1109, 273 - 70);
				graphics.drawLine(1109, 278 - 70, 1109, 288 - 70);
				graphics.drawLine(1109, 293 - 70, 1109, 303 - 70);
				graphics.drawLine(1109, 308 - 70, 1109, 318 - 70);
				graphics.drawLine(image.getIconWidth() - 447, 110, image.getIconWidth() - 137, 110);
				graphics.drawLine(image.getIconWidth() - 447, 510, image.getIconWidth() - 137, 510);
//				graphics.drawRect(image.getIconWidth() - 447, 645, 140, 50);
				
				graphics.drawArc(1100, 330, 18, 18, 0, 360);
				graphics.drawArc(1100, 518 - 70, 18, 18, 0, 360);
				graphics.drawLine(1109, 418 - 70, 1109, 428 - 70);
				graphics.drawLine(1109, 433 - 70, 1109, 443 - 70);
				graphics.drawLine(1109, 448 - 70, 1109, 458 - 70);
				graphics.drawLine(1109, 463 - 70, 1109, 473 - 70);
				graphics.drawLine(1109, 478 - 70, 1109, 488 - 70);
				graphics.drawLine(1109, 493 - 70, 1109, 503 - 70);
				graphics.drawLine(1109, 508 - 70, 1109, 518 - 70);
			}
		};
		jp.setBounds(0, -5, image.getIconWidth(), image.getIconHeight());

		jp.add(img);

		JLabel dep = new JLabel("Hongkong"); // flight.get(0).getDeparture() TODO
		dep.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		dep.setForeground(new Color(102, 147, 195));
		dep.setBounds(1140, 110, 300, 50);

		JLabel des = new JLabel("Beijing"); // flight.get(0).getDestination() TODO
		des.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		des.setForeground(new Color(102, 147, 195));
		des.setBounds(1140, 228, 300, 50);
		
//		long diff1 = flight.get(1).getTakingOffTime().getTime() - flight.get(0).getLandingTime().getTime(); TODO
//		long hours1 = diff1 / (1000 * 60 * 60);
//		long minutes1 = diff1 - hours * (1000 * 60 * 60) / (1000 * 60);
//		String durationT1 = hours + "hours " + minutes + "minutes";
		String durationT1 = 2 + "h " + 22 + "m";
		
		JLabel stop = new JLabel("Stop Duration: " + durationT1); // flight.get(0).getDestination() TODO
		stop.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		stop.setForeground(Color.GRAY);
		stop.setBounds(1140, 268, 300, 50);

		JLabel depTime = new JLabel("10:25"); // flight.get(0).getTakingOffTime() TODO
		depTime.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		depTime.setForeground(new Color(102, 147, 195));
		depTime.setBounds(1020, 110, 300, 50);

		JLabel desTime = new JLabel("12:50"); // flight.get(0).getLandingTime() TODO
		desTime.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		desTime.setForeground(new Color(102, 147, 195));
		desTime.setBounds(1020, 228, 300, 50);

		Date date = new Date(); // flight.get(0).get
		String depDate = date.toString().substring(8, 11) + date.toString().substring(4, 7) + ", "
				+ date.toString().substring(24);

		JLabel depDateT = new JLabel(depDate);
		depDateT.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		depDateT.setForeground(Color.GRAY);
		depDateT.setBounds(992, 135, 300, 50);

		JLabel desDateT = new JLabel(depDate);
		desDateT.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		desDateT.setForeground(Color.GRAY);
		desDateT.setBounds(992, 253, 300, 50);

		JLabel flightNum = new JLabel("Flight No. " + "CN1234"); // flight.get(0).getFid() TODO
		flightNum.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		flightNum.setForeground(Color.GRAY);
		flightNum.setBounds(1140, 168, 300, 50);
		
		JLabel dep2 = new JLabel("Hongkong"); // flight.get(1).getDeparture() TODO
		dep2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		dep2.setForeground(new Color(102, 147, 195));
		dep2.setBounds(1140, 310, 300, 50);

		JLabel des2 = new JLabel("Beijing"); // flight.get(1).getDestination() TODO
		des2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		des2.setForeground(new Color(102, 147, 195));
		des2.setBounds(1140, 428, 300, 50);

		JLabel depTime2 = new JLabel("10:25"); // flight.get(1).getTakingOffTime() TODO
		depTime2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		depTime2.setForeground(new Color(102, 147, 195));
		depTime2.setBounds(1020, 310, 300, 50);

		JLabel desTime2 = new JLabel("12:50"); // flight.get(1).getLandingTime() TODO
		desTime2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		desTime2.setForeground(new Color(102, 147, 195));
		desTime2.setBounds(1020, 428, 300, 50);

		Date date2 = new Date(); // flight.get(1).get
		String depDate2 = date2.toString().substring(8, 11) + date2.toString().substring(4, 7) + ", "
				+ date2.toString().substring(24);

		JLabel depDateT2 = new JLabel(depDate2);
		depDateT2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		depDateT2.setForeground(Color.GRAY);
		depDateT2.setBounds(992, 335, 300, 50);

		JLabel desDateT2 = new JLabel(depDate2);
		desDateT2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		desDateT2.setForeground(Color.GRAY);
		desDateT2.setBounds(992, 453, 300, 50);

		JLabel flightNum2 = new JLabel("Flight No. " + "CN1234"); // flight.get(1).getFid() TODO
		flightNum2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		flightNum2.setForeground(Color.GRAY);
		flightNum2.setBounds(1140, 368, 300, 50);

//		long diff2 = flight.get(1).getLandingTime().getTime() - flight.get(0).getTakingOffTime().getTime(); TODO
//		long hours2 = diff2 / (1000 * 60 * 60);
//		long minutes2 = diff2 - hours * (1000 * 60 * 60) / (1000 * 60);
//		String durationT2 = hours + "hours " + minutes + "minutes";
		String durationT2 = 7 + "h " + 53 + "m";

		JLabel duration = new JLabel("Trip Duration: " + durationT2);
		duration.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		duration.setForeground(new Color(102, 147, 195));
		duration.setBounds(image.getIconWidth() - 447, 40, 300, 50);

		JLabel available = new JLabel("Available Seats:  " + 4); // min{flight.get(0).getAvailableSeats(),flight.get(1).getAvailableSeats()} TODO
		available.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		available.setForeground(new Color(102, 147, 195));
		available.setBounds(image.getIconWidth() - 447, 520, 300, 50);

		JLabel select = new JLabel("Select Passengers: ");
		select.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		select.setForeground(new Color(102, 147, 195));
		select.setBounds(image.getIconWidth() - 447, 570, 300, 50);

		passengers = new JLabel(num + "");
		passengers.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		passengers.setForeground(new Color(102, 147, 195));
		passengers.setBounds(image.getIconWidth() - 190, 570, 50, 50);

		JButton plus = new JButton("+");
		plus.setBounds(image.getIconWidth() - 172, 570, 60, 50);
		plus.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		plus.setForeground(new Color(102, 147, 195));
		plus.setBorderPainted(false);
		plus.setFocusPainted(false);
		plus.setOpaque(false);

		plus.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				plus.setForeground(new Color(135, 206, 235));
			}

			public void mouseExited(MouseEvent evt) {
				plus.setForeground(new Color(102, 147, 195));
			}
		});
		plus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (num == 4) { // min{flight.get(0).getAvaliableSeats(),flight.get(1).getAvailableSeats()} TODO
					return;
				}
				layeredPane.remove(passengers);
				passengers = new JLabel(++num + "");
				passengers.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
				passengers.setForeground(new Color(102, 147, 195));
				passengers.setBounds(image.getIconWidth() - 190, 570, 50, 50);
				layeredPane.add(passengers, JLayeredPane.MODAL_LAYER);
			}

		});

		JButton minus = new JButton("-");
		minus.setBounds(image.getIconWidth() - 252, 570, 60, 50);
		minus.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		minus.setForeground(new Color(102, 147, 195));
		minus.setBorderPainted(false);
		minus.setFocusPainted(false);
		minus.setOpaque(false);

		minus.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				minus.setForeground(new Color(135, 206, 235));
			}

			public void mouseExited(MouseEvent evt) {
				minus.setForeground(new Color(102, 147, 195));
			}
		});
		minus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (num == 1)
					return;
				layeredPane.remove(passengers);
				passengers = new JLabel(--num + "");
				passengers.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
				passengers.setForeground(new Color(102, 147, 195));
				passengers.setBounds(image.getIconWidth() - 190, 570, 50, 50);
				layeredPane.add(passengers, JLayeredPane.MODAL_LAYER);
			}

		});

		JButton buy = new JButton("Book Now");
		buy.setBounds(image.getIconWidth() - 475, 640, 160, 50);
		buy.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		buy.setForeground(new Color(255, 109, 107));
		buy.setBorderPainted(false);
		buy.setFocusPainted(false);
		buy.setOpaque(false);

		buy.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				buy.setForeground(Color.RED);
			}

			public void mouseExited(MouseEvent evt) {
				buy.setForeground(new Color(255, 109, 107));
			}
		});
		buy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// call the backend func TODO
				new BuySuccessfully(user);
			}
		});

		JButton cancel = new JButton("Cancel");
		cancel.setBounds(image.getIconWidth() - 235, 640, 120, 50);
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
		layeredPane.add(flightNum, JLayeredPane.MODAL_LAYER);
		layeredPane.add(flightNum2, JLayeredPane.MODAL_LAYER);
		layeredPane.add(duration, JLayeredPane.MODAL_LAYER);
		layeredPane.add(select, JLayeredPane.MODAL_LAYER);
		layeredPane.add(dep, JLayeredPane.MODAL_LAYER);
		layeredPane.add(des, JLayeredPane.MODAL_LAYER);
		layeredPane.add(depTime, JLayeredPane.MODAL_LAYER);
		layeredPane.add(desTime, JLayeredPane.MODAL_LAYER);
		layeredPane.add(depDateT, JLayeredPane.MODAL_LAYER);
		layeredPane.add(desDateT, JLayeredPane.MODAL_LAYER);
		layeredPane.add(stop, JLayeredPane.MODAL_LAYER);
		layeredPane.add(dep2, JLayeredPane.MODAL_LAYER);
		layeredPane.add(des2, JLayeredPane.MODAL_LAYER);
		layeredPane.add(depTime2, JLayeredPane.MODAL_LAYER);
		layeredPane.add(desTime2, JLayeredPane.MODAL_LAYER);
		layeredPane.add(depDateT2, JLayeredPane.MODAL_LAYER);
		layeredPane.add(desDateT2, JLayeredPane.MODAL_LAYER);
		layeredPane.add(available, JLayeredPane.MODAL_LAYER);
		layeredPane.add(plus, JLayeredPane.MODAL_LAYER);
		layeredPane.add(passengers, JLayeredPane.MODAL_LAYER);
		layeredPane.add(minus, JLayeredPane.MODAL_LAYER);
		layeredPane.add(buy, JLayeredPane.MODAL_LAYER);
		layeredPane.add(cancel, JLayeredPane.MODAL_LAYER);

		jf.setLayeredPane(layeredPane);
		jf.setSize(image.getIconWidth(), image.getIconHeight());
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
	}

	private void oneFlightJFrame() {
		// TODO Auto-generated method stub
		JFrame jf = new JFrame("Select Passengers");

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
				((Graphics2D) graphics).setStroke(new BasicStroke(2f));
				graphics.drawArc(1100, 230, 18, 18, 0, 360);
				graphics.drawArc(1100, 418 - 70, 18, 18, 0, 360);
				graphics.drawLine(1109, 318 - 70, 1109, 328 - 70);
				graphics.drawLine(1109, 333 - 70, 1109, 343 - 70);
				graphics.drawLine(1109, 348 - 70, 1109, 358 - 70);
				graphics.drawLine(1109, 363 - 70, 1109, 373 - 70);
				graphics.drawLine(1109, 378 - 70, 1109, 388 - 70);
				graphics.drawLine(1109, 393 - 70, 1109, 403 - 70);
				graphics.drawLine(1109, 408 - 70, 1109, 418 - 70);
				graphics.drawLine(image.getIconWidth() - 447, 210, image.getIconWidth() - 137, 210);
				graphics.drawLine(image.getIconWidth() - 447, 410, image.getIconWidth() - 137, 410);
//				graphics.drawRect(image.getIconWidth() - 447, 545, 140, 50);
			}
		};
		jp.setBounds(0, -5, image.getIconWidth(), image.getIconHeight());

		jp.add(img);

		JLabel dep = new JLabel("Hongkong"); // flight.get(0).getDeparture() TODO
		dep.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		dep.setForeground(new Color(102, 147, 195));
		dep.setBounds(1140, 280 - 70, 300, 50);

		JLabel des = new JLabel("Beijing"); // flight.get(0).getDestination() TODO
		des.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		des.setForeground(new Color(102, 147, 195));
		des.setBounds(1140, 398 - 70, 300, 50);

		JLabel depTime = new JLabel("10:25"); // flight.get(0).getTakingOffTime() TODO
		depTime.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		depTime.setForeground(new Color(102, 147, 195));
		depTime.setBounds(1020, 280 - 70, 300, 50);

		JLabel desTime = new JLabel("12:50"); // flight.get(0).getLandingTime() TODO
		desTime.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		desTime.setForeground(new Color(102, 147, 195));
		desTime.setBounds(1020, 398 - 70, 300, 50);

		Date date = new Date(); // flight.get(0).get TODO
		String depDate = date.toString().substring(8, 11) + date.toString().substring(4, 7) + ", "
				+ date.toString().substring(24);

		JLabel depDateT = new JLabel(depDate);
		depDateT.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		depDateT.setForeground(Color.GRAY);
		depDateT.setBounds(992, 305 - 70, 300, 50);

		JLabel desDateT = new JLabel(depDate);
		desDateT.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		desDateT.setForeground(Color.GRAY);
		desDateT.setBounds(992, 328 + 25, 300, 50);

		JLabel flightNum = new JLabel("Flight No. " + "CN1234"); // flight.get(0).getFid() TODO
		flightNum.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		flightNum.setForeground(Color.GRAY);
		flightNum.setBounds(1140, 338 - 70, 300, 50);

//		long diff = flight.get(0).getLandingTime().getTime() - flight.get(0).getTakingOffTime().getTime(); TODO
//		long hours = diff / (1000 * 60 * 60);
//		long minutes = diff - hours * (1000 * 60 * 60) / (1000 * 60);
//		String durationT = hours + "hours " + minutes + "minutes";
		String durationT = 7 + "h " + 53 + "m";

		JLabel duration = new JLabel("Trip Duration: " + durationT);
		duration.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		duration.setForeground(new Color(102, 147, 195));
		duration.setBounds(image.getIconWidth() - 447, 140, 300, 50);

		JLabel available = new JLabel("Available Seats:  " + 4); // flight.get(0).getAvailableSeats() TODO
		available.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		available.setForeground(new Color(102, 147, 195));
		available.setBounds(image.getIconWidth() - 447, 420, 300, 50);

		JLabel select = new JLabel("Select Passengers: ");
		select.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		select.setForeground(new Color(102, 147, 195));
		select.setBounds(image.getIconWidth() - 447, 550 - 80, 300, 50);

		passengers = new JLabel(num + "");
		passengers.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		passengers.setForeground(new Color(102, 147, 195));
		passengers.setBounds(image.getIconWidth() - 190, 550 - 80, 50, 50);

		JButton plus = new JButton("+");
		plus.setBounds(image.getIconWidth() - 172, 550 - 80, 60, 50);
		plus.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		plus.setForeground(new Color(102, 147, 195));
		plus.setBorderPainted(false);
		plus.setFocusPainted(false);
		plus.setOpaque(false);

		plus.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				plus.setForeground(new Color(135, 206, 235));
			}

			public void mouseExited(MouseEvent evt) {
				plus.setForeground(new Color(102, 147, 195));
			}
		});
		plus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (num == 4) { // flight.get(0).getAvaliableSeats() TODO
					return;
				}
				layeredPane.remove(passengers);
				passengers = new JLabel(++num + "");
				passengers.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
				passengers.setForeground(new Color(102, 147, 195));
				passengers.setBounds(image.getIconWidth() - 190, 550 - 80, 50, 50);
				layeredPane.add(passengers, JLayeredPane.MODAL_LAYER);
			}

		});

		JButton minus = new JButton("-");
		minus.setBounds(image.getIconWidth() - 252, 550 - 80, 60, 50);
		minus.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		minus.setForeground(new Color(102, 147, 195));
		minus.setBorderPainted(false);
		minus.setFocusPainted(false);
		minus.setOpaque(false);

		minus.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				minus.setForeground(new Color(135, 206, 235));
			}

			public void mouseExited(MouseEvent evt) {
				minus.setForeground(new Color(102, 147, 195));
			}
		});
		minus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (num == 1)
					return;
				layeredPane.remove(passengers);
				passengers = new JLabel(--num + "");
				passengers.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
				passengers.setForeground(new Color(102, 147, 195));
				passengers.setBounds(image.getIconWidth() - 190, 550 - 80, 50, 50);
				layeredPane.add(passengers, JLayeredPane.MODAL_LAYER);
			}

		});

		JButton buy = new JButton("Book Now");
		buy.setBounds(image.getIconWidth() - 475, 540, 160, 50);
		buy.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		buy.setForeground(new Color(255, 109, 107));
		buy.setBorderPainted(false);
		buy.setFocusPainted(false);
		buy.setOpaque(false);

		buy.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				buy.setForeground(Color.RED);
			}

			public void mouseExited(MouseEvent evt) {
				buy.setForeground(new Color(255, 109, 107));
			}
		});
		buy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		JButton cancel = new JButton("Cancel");
		cancel.setBounds(image.getIconWidth() - 235, 540, 120, 50);
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
		layeredPane.add(flightNum, JLayeredPane.MODAL_LAYER);
		layeredPane.add(duration, JLayeredPane.MODAL_LAYER);
		layeredPane.add(select, JLayeredPane.MODAL_LAYER);
		layeredPane.add(dep, JLayeredPane.MODAL_LAYER);
		layeredPane.add(des, JLayeredPane.MODAL_LAYER);
		layeredPane.add(depTime, JLayeredPane.MODAL_LAYER);
		layeredPane.add(desTime, JLayeredPane.MODAL_LAYER);
		layeredPane.add(depDateT, JLayeredPane.MODAL_LAYER);
		layeredPane.add(desDateT, JLayeredPane.MODAL_LAYER);
		layeredPane.add(available, JLayeredPane.MODAL_LAYER);
		layeredPane.add(plus, JLayeredPane.MODAL_LAYER);
		layeredPane.add(passengers, JLayeredPane.MODAL_LAYER);
		layeredPane.add(minus, JLayeredPane.MODAL_LAYER);
		layeredPane.add(buy, JLayeredPane.MODAL_LAYER);
		layeredPane.add(cancel, JLayeredPane.MODAL_LAYER);

		jf.setLayeredPane(layeredPane);
		jf.setSize(image.getIconWidth(), image.getIconHeight());
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
	}
	
	public static void CloseWindow() {
		jf.dispose();
	}

}
