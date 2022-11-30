package userInterface;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import ticketSystem.Flight;
import ticketSystem.User;
import userInterface.UIException.ExUserIsNull;

public class PurchaseTicket {

	private ArrayList<ArrayList<Flight>> allFlights = new ArrayList<ArrayList<Flight>>();
	private int idx = 0;
	private JPanel tkt;
	private JScrollPane jsp;
	private static JFrame jf;
	private static JLayeredPane layeredPane;
	private static JButton loginBtn;
	private static JButton signupBtn;
	private static JButton account;
	private static User user = null;

	public PurchaseTicket(User aUser, ArrayList<ArrayList<Flight>> aAllFlights, String aDeparture, String aDestination,
			String aDate) {
		this.allFlights = aAllFlights;
		PurchaseTicket.user = aUser;

		jf = new JFrame("Purchase Ticket");

		layeredPane = new JLayeredPane();

		ImageIcon image = new ImageIcon(getClass().getResource("ImgSource/background.jpeg"));
		JLabel img = new JLabel(image);

		JPanel jp = new JPanel() {
			@Override
			public void paint(Graphics graphics) {
				super.paint(graphics);
				((Graphics2D) graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON); // Anti-Aliasing
				graphics.setColor(new Color(102, 147, 195));
				graphics.drawLine(1090 - 25, 80, 1100 - 25, 80);
				graphics.drawLine(1105 - 25, 80, 1115 - 25, 80);
				graphics.drawLine(1120 - 25, 80, 1130 - 25, 80);
				graphics.drawLine(1200 - 25, 80, 1210 - 25, 80);
				graphics.drawLine(1215 - 25, 80, 1225 - 25, 80);
				graphics.drawLine(1230 - 25, 80, 1240 - 25, 80);
			}
		};
		jp.setBounds(0, -5, image.getIconWidth(), image.getIconHeight());
		jp.add(img);

		loginBtn = new JButton("Login");
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
				new Login();
			}
		});

		signupBtn = new JButton("Sign up");
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
				new Signup();
			}
		});

		JLabel depCity = new JLabel(aDeparture, JLabel.RIGHT);
		depCity.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		depCity.setForeground(new Color(102, 147, 195));
		depCity.setBounds(image.getIconWidth() - 572 - 25, 50, 200, 50);

		JLabel desCity = new JLabel(aDestination);
		desCity.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		desCity.setForeground(new Color(102, 147, 195));
		desCity.setBounds(image.getIconWidth() - 180 - 25, 50, 300, 50);

		JLabel date = new JLabel(aDate);
		date.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		date.setForeground(new Color(102, 147, 195));
		date.setBounds(image.getIconWidth() - 330 - 25, 83, 300, 50);

		JLabel tick = new JLabel("√");
		tick.setFont(new Font("", Font.BOLD, 20));
		tick.setForeground(new Color(102, 147, 195));
		tick.setBounds(image.getIconWidth() - 530, 135, 100, 50);

		JButton sortByDepTime = new JButton("Sort By Departure Time");
		sortByDepTime.setBounds(image.getIconWidth() - 530, 135, 250, 50);
		sortByDepTime.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 20));
		sortByDepTime.setForeground(new Color(102, 147, 195));
		sortByDepTime.setBorderPainted(false);
		sortByDepTime.setFocusPainted(false);
		sortByDepTime.setOpaque(false);
		sortByDepTime.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				sortByDepTime.setForeground(new Color(135, 206, 235));
			}

			public void mouseExited(MouseEvent e) {
				sortByDepTime.setForeground(new Color(102, 147, 195));
			}
		});
		sortByDepTime.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tick.getBounds().x == 1220) {
					tick.setBounds(image.getIconWidth() - 530, 135, 100, 50);
					setAllFlights(SearchFlight.getInstance().getAllFlights());
					creatNewJScrollPane();
				}
			}
		});

		JButton sortByPrice = new JButton("Sort By Price");
		sortByPrice.setBounds(image.getIconWidth() - 220, 135, 160, 50);
		sortByPrice.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 20));
		sortByPrice.setForeground(new Color(102, 147, 195));
		sortByPrice.setBorderPainted(false);
		sortByPrice.setFocusPainted(false);
		sortByPrice.setOpaque(false);
		sortByPrice.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				sortByPrice.setForeground(new Color(135, 206, 235));
			}

			public void mouseExited(MouseEvent e) {
				sortByPrice.setForeground(new Color(102, 147, 195));
			}
		});
		sortByPrice.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tick.getBounds().x == 910) {
					tick.setBounds(image.getIconWidth() - 220, 135, 100, 50);
					setAllFlights(SearchFlight.getInstance().getAllFlightsByPrice());
					creatNewJScrollPane();
				}
			}
		});

		JButton returnBtn = new JButton("Return");
		returnBtn.setBounds(image.getIconWidth() - 140, 740, 120, 50);
		returnBtn.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 25));
		returnBtn.setForeground(new Color(102, 147, 195));
		returnBtn.setBorderPainted(false);
		returnBtn.setFocusPainted(false);
		returnBtn.setOpaque(false);
		returnBtn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				returnBtn.setForeground(new Color(135, 206, 235));
			}

			public void mouseExited(MouseEvent e) {
				returnBtn.setForeground(new Color(102, 147, 195));
			}
		});
		returnBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
			}
		});

		JPanel tickets = new JPanel();
		tickets.setPreferredSize(new Dimension(520, 120 * allFlights.size()));
		tickets.setOpaque(false);
		tickets.setLayout(null);
		for (idx = 0; idx < allFlights.size(); idx++) {

			tkt = new JPanel() {

				@Override
				public void paint(Graphics graphics) {
					super.paint(graphics);
					((Graphics2D) graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
							RenderingHints.VALUE_ANTIALIAS_ON); // Anti-Aliasing
					((Graphics2D) graphics).setStroke(new BasicStroke(2f));
					graphics.setColor(new Color(102, 147, 195));
					graphics.drawLine(0, 120, 490, 120);
					graphics.drawLine(0, 0, 490, 0);
					graphics.drawLine(132, 70, 232, 70);
					graphics.drawLine(222, 65, 232, 70);
					graphics.drawLine(0, 0, 0, 120);
					graphics.drawLine(490, 0, 490, 120);
				}
			};
			tkt.setBounds(0, idx * 120, 500, 130);
			tkt.setOpaque(false);
			tkt.setLayout(null);

			JLabel fid = new JLabel(allFlights.get(idx).get(0).getFid());
			fid.setFont(new Font("Times New Roman", Font.ITALIC, 15));
			fid.setForeground(Color.GRAY);
			fid.setBounds(20, 0, 300, 40);

			String depTime = allFlights.get(idx).get(0).getTakeOffTime().toString().substring(11, 16);
			JLabel takeOffTime = new JLabel(depTime);
			takeOffTime.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
			takeOffTime.setForeground(new Color(102, 147, 195));
			takeOffTime.setBounds(40, 30, 300, 50);

			JLabel dep = new JLabel(allFlights.get(idx).get(0).getDeparture(), JLabel.CENTER);
			dep.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
			dep.setForeground(new Color(102, 147, 195));
			dep.setBounds(17, 60, 100, 50);

			String desTime = allFlights.get(idx).get(allFlights.get(idx).size() - 1).getLandingTime().toString()
					.substring(11, 16);
			JLabel landingTime = new JLabel(desTime);
			landingTime.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
			landingTime.setForeground(new Color(102, 147, 195));
			landingTime.setBounds(265, 30, 300, 50);

			JLabel des = new JLabel(allFlights.get(idx).get(allFlights.get(idx).size() - 1).getDestination(),
					JLabel.CENTER);
			des.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
			des.setForeground(new Color(102, 147, 195));
			des.setBounds(244, 60, 100, 50);

			JLabel stop = new JLabel("1 Stop");
			stop.setFont(new Font("Times New Roman", Font.ITALIC, 15));
			stop.setForeground(Color.GRAY);
			stop.setBounds(160, 30, 100, 50);

			JLabel plusOne = new JLabel("+1");
			plusOne.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			plusOne.setForeground(new Color(255, 109, 107));
			plusOne.setBounds(330, 20, 100, 50);

			JLabel book = new JLabel("Book for");
			book.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
			book.setForeground(new Color(255, 109, 107));
			book.setBounds(385, 20, 280, 50);

			int totPrice = allFlights.get(idx).size() == 1 ? allFlights.get(idx).get(0).getPrice()
					: allFlights.get(idx).get(0).getPrice() + allFlights.get(idx).get(1).getPrice();
			JLabel price = new JLabel("$" + totPrice);
			price.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
			price.setForeground(new Color(255, 109, 107));
			price.setBounds(390, 50, 280, 50);

			JLabel index = new JLabel(idx + "");

			JButton bookBtn = new JButton();
			bookBtn.setBounds(358, 0, 132, 120);
			bookBtn.setBorderPainted(false);
			bookBtn.setFocusPainted(false);
			bookBtn.setOpaque(false);
			bookBtn.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					book.setForeground(Color.RED);
					price.setForeground(Color.RED);
				}

				public void mouseExited(MouseEvent e) {
					book.setForeground(new Color(255, 109, 107));
					price.setForeground(new Color(255, 109, 107));
				}
			});
			bookBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						if (user == null)
							throw new ExUserIsNull();
						else
							new SelectPassengers(allFlights.get(Integer.parseInt(index.getText())), user);
					} catch (ExUserIsNull e1) {
						e1.printStackTrace();
					}
				}
			});

			tkt.add(fid);
			tkt.add(takeOffTime);
			tkt.add(landingTime);
			tkt.add(dep);
			tkt.add(des);
			tkt.add(book);
			tkt.add(price);
			tkt.add(bookBtn);

			if (allFlights.get(idx).size() == 2) {
				tkt.add(stop);
				fid.setText(allFlights.get(idx).get(0).getFid() + " & " + allFlights.get(idx).get(1).getFid());
			}
			if (isNextDayArrival(allFlights.get(idx).get(0).getTakeOffTime(),
					allFlights.get(idx).get(allFlights.get(idx).size() - 1).getLandingTime()))
				tkt.add(plusOne);

			tickets.add(tkt);

		}
		jsp = new JScrollPane(tickets);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		jsp.setBorder(emptyBorder);
		jsp.setOpaque(false);
		jsp.getViewport().setOpaque(false);
		jsp.setBounds(900, 200, 540, 530);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		layeredPane.add(jp, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(jsp, JLayeredPane.MODAL_LAYER);
		if (PurchaseTicket.user == null) {
			layeredPane.add(loginBtn, JLayeredPane.MODAL_LAYER);
			layeredPane.add(signupBtn, JLayeredPane.MODAL_LAYER);
		} else
			PurchaseTicket.setAccountBtn(user);
		layeredPane.add(depCity, JLayeredPane.MODAL_LAYER);
		layeredPane.add(desCity, JLayeredPane.MODAL_LAYER);
		layeredPane.add(date, JLayeredPane.MODAL_LAYER);
		layeredPane.add(tick, JLayeredPane.MODAL_LAYER);
		layeredPane.add(sortByPrice, JLayeredPane.MODAL_LAYER);
		layeredPane.add(sortByDepTime, JLayeredPane.MODAL_LAYER);
		layeredPane.add(returnBtn, JLayeredPane.MODAL_LAYER);

		ImageIcon image2 = new ImageIcon(getClass().getResource("ImgSource/flight.jpeg"));
		JLabel img2 = new JLabel(image2);
		img2.setBounds(1115, 52, 50, 50);
		layeredPane.add(img2, JLayeredPane.MODAL_LAYER);

		jf.setLayeredPane(layeredPane);
		jf.setSize(image.getIconWidth(), image.getIconHeight());
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
	}

	private boolean isNextDayArrival(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return cal1.get(Calendar.DATE) != cal2.get(Calendar.DATE);
	}

	protected void creatNewJScrollPane() {
		layeredPane.remove(jsp);

		JPanel tickets = new JPanel();
		tickets.setPreferredSize(new Dimension(520, 120 * allFlights.size()));
		tickets.setOpaque(false);
		tickets.setLayout(null);
		for (idx = 0; idx < allFlights.size(); idx++) {

			tkt = new JPanel() {

				@Override
				public void paint(Graphics graphics) {
					super.paint(graphics);
					((Graphics2D) graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
							RenderingHints.VALUE_ANTIALIAS_ON); // Anti-Aliasing
					((Graphics2D) graphics).setStroke(new BasicStroke(2f));
					graphics.setColor(new Color(102, 147, 195));
					graphics.drawLine(0, 0, 490, 0);
					graphics.drawLine(0, 120, 490, 120);
					graphics.drawLine(132, 70, 232, 70);
					graphics.drawLine(222, 65, 232, 70);
					graphics.drawLine(0, 0, 0, 120);
					graphics.drawLine(490, 0, 490, 120);
				}
			};
			tkt.setBounds(0, idx * 120, 500, 130);
			tkt.setOpaque(false);
			tkt.setLayout(null);

			JLabel fid = new JLabel(allFlights.get(idx).get(0).getFid());
			fid.setFont(new Font("Times New Roman", Font.ITALIC, 15));
			fid.setForeground(Color.GRAY);
			fid.setBounds(20, 0, 300, 40);

			String depTime = allFlights.get(idx).get(0).getTakeOffTime().toString().substring(11, 16);
			JLabel takeOffTime = new JLabel(depTime);
			takeOffTime.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
			takeOffTime.setForeground(new Color(102, 147, 195));
			takeOffTime.setBounds(40, 30, 300, 50);

			JLabel dep = new JLabel(allFlights.get(idx).get(0).getDeparture(), JLabel.CENTER);
			dep.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
			dep.setForeground(new Color(102, 147, 195));
			dep.setBounds(17, 60, 100, 50);

			String desTime = allFlights.get(idx).get(allFlights.get(idx).size() - 1).getLandingTime().toString()
					.substring(11, 16);
			JLabel landingTime = new JLabel(desTime);
			landingTime.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
			landingTime.setForeground(new Color(102, 147, 195));
			landingTime.setBounds(265, 30, 300, 50);

			JLabel des = new JLabel(allFlights.get(idx).get(allFlights.get(idx).size() - 1).getDestination(),
					JLabel.CENTER);
			des.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
			des.setForeground(new Color(102, 147, 195));
			des.setBounds(244, 60, 100, 50);

			JLabel stop = new JLabel("1 Stop");
			stop.setFont(new Font("Times New Roman", Font.ITALIC, 15));
			stop.setForeground(Color.GRAY);
			stop.setBounds(160, 30, 100, 50);

			JLabel plusOne = new JLabel("+1");
			plusOne.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			plusOne.setForeground(new Color(255, 109, 107));
			plusOne.setBounds(330, 20, 100, 50);

			JLabel book = new JLabel("Book for");
			book.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
			book.setForeground(new Color(255, 109, 107));
			book.setBounds(385, 20, 280, 50);

			int totPrice = allFlights.get(idx).size() == 1 ? allFlights.get(idx).get(0).getPrice()
					: allFlights.get(idx).get(0).getPrice() + allFlights.get(idx).get(1).getPrice();
			JLabel price = new JLabel("$" + totPrice);
			price.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
			price.setForeground(new Color(255, 109, 107));
			price.setBounds(390, 50, 280, 50);

			JLabel index = new JLabel(idx + "");
			
			JButton bookBtn = new JButton();
			bookBtn.setBounds(358, 0, 132, 120);
			bookBtn.setBorderPainted(false);
			bookBtn.setFocusPainted(false);
			bookBtn.setOpaque(false);
			bookBtn.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					book.setForeground(Color.RED);
					price.setForeground(Color.RED);
				}

				public void mouseExited(MouseEvent e) {
					book.setForeground(new Color(255, 109, 107));
					price.setForeground(new Color(255, 109, 107));
				}
			});
			bookBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						if (user == null)
							throw new ExUserIsNull();
						else
							new SelectPassengers(allFlights.get(Integer.parseInt(index.getText())), user);
					} catch (ExUserIsNull e1) {
						e1.printStackTrace();
					}
				}
			});

			tkt.add(fid);
			tkt.add(takeOffTime);
			tkt.add(landingTime);
			tkt.add(dep);
			tkt.add(des);
			tkt.add(book);
			tkt.add(price);
			tkt.add(bookBtn);

			if (allFlights.get(idx).size() == 2) {
				tkt.add(stop);
				fid.setText(allFlights.get(idx).get(0).getFid() + " & " + allFlights.get(idx).get(1).getFid());
			}
			if (isNextDayArrival(allFlights.get(idx).get(0).getTakeOffTime(),
					allFlights.get(idx).get(allFlights.get(idx).size() - 1).getLandingTime()))
				tkt.add(plusOne);

			tickets.add(tkt);
		}
		jsp = new JScrollPane(tickets);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		jsp.setBorder(emptyBorder);
		jsp.setOpaque(false);
		jsp.getViewport().setOpaque(false);
		jsp.setBounds(900, 200, 540, 530);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		layeredPane.add(jsp, JLayeredPane.MODAL_LAYER);
	}

	private void setAllFlights(ArrayList<ArrayList<Flight>> aAllFlights) {
		this.allFlights = aAllFlights;
	}

	public static void setAccountBtn(User aUser) {
		user = aUser;
		if (layeredPane.getComponentCount() != 2) {
			layeredPane.remove(loginBtn);
			layeredPane.remove(signupBtn);
		}
		account = new JButton("Hi! " + user.getUsername());
		account.setBounds(1181, 0, 259, 50);
		account.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 20));
		account.setForeground(new Color(102, 147, 195));
		account.setBorderPainted(false);
		account.setFocusPainted(false);
		account.setOpaque(false);
		account.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				account.setForeground(new Color(135, 206, 235));
			}

			public void mouseExited(MouseEvent e) {
				account.setForeground(new Color(102, 147, 195));
			}
		});
		account.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MyAccount(user);
			}
		});
		layeredPane.add(account, JLayeredPane.MODAL_LAYER);
	}

	public static void logout() {
		user = null;
		layeredPane.remove(account);
		loginBtn = new JButton("Login");
		loginBtn.setBounds(1235, 0, 95, 50);
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
				new Login();
			}
		});

		signupBtn = new JButton("Sign up");
		signupBtn.setBounds(1330, 0, 110, 50);
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
				new Signup();
			}
		});
		layeredPane.add(loginBtn, JLayeredPane.MODAL_LAYER);
		layeredPane.add(signupBtn, JLayeredPane.MODAL_LAYER);
	}

	public static void CloseWindow() {
		jf.dispose();
	}

	public static boolean notCreate() {
		return layeredPane == null;
	}

}
