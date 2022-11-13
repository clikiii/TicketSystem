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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javax.swing.JTextField;

import ticketSystem.Flight;
import ticketSystem.TicketSystem;
import ticketSystem.User;
import userInterface.UIException.ExDepartureCityIsEmpty;
import userInterface.UIException.ExDestinationCityIsEmpty;
import userInterface.UIException.ExDepAndDesAreSame;
import userInterface.UIException.ExDateIsEmpty;
import userInterface.UIException.ExDepartureCityIsNotFound;
import userInterface.UIException.ExDestinationCityIsNotFound;

public class SearchFlight {
	private static SearchFlight instance = new SearchFlight();
	private static TicketSystem ticketSystem;

	public static void main(String[] args) {
		ticketSystem = TicketSystem.start();
		SearchFlight.getInstance();
	}

	public static SearchFlight getInstance() {
		return instance;
	}

	private JTextField dep;
	private JTextField des;
	private String date;
	private JLayeredPane layeredPane;
	private JLabel dft;
	private String selectDate;
	private String[] MM = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
	private User user = null;
	private JButton loginBtn;
	private JButton signupBtn;
	private JButton account;

	private SearchFlight() {
		JFrame jf = new JFrame("Air Ticket Booking System");

		ImageIcon image = new ImageIcon("Source/ticketSystem/src/userInterface/ImgSource/background.jpeg");
		JLabel jl = new JLabel(image);

		layeredPane = new JLayeredPane();

		JPanel jp = new JPanel() {

			@Override
			public void paint(Graphics graphics) {
				super.paint(graphics);
				((Graphics2D) graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON); // Anti-Aliasing

				graphics.setColor(new Color(102, 147, 195));
				graphics.drawRoundRect(image.getIconWidth() - 450, 270, 300, 50, 50, 50); // border 1
				graphics.drawRoundRect(image.getIconWidth() - 450, 370, 300, 50, 50, 50); // border 2
				graphics.drawRoundRect(image.getIconWidth() - 450, 440, 300, 50, 50, 50); // border 3
			}
		};
		jp.setBounds(0, -5, image.getIconWidth(), image.getIconHeight());

		jp.add(jl);

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

		JLabel jl0 = new JLabel("A world to share !");
		jl0.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		jl0.setForeground(new Color(102, 147, 195));
		jl0.setBounds(image.getIconWidth() - 410, 180, 300, 50);

		JLabel from = new JLabel("From:");
		from.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 12));
		from.setForeground(new Color(102, 147, 195));
		from.setBounds(image.getIconWidth() - 435, 250, 250, 50);

		dep = new JTextField(10);
		dep.addFocusListener(new JTextFieldHintListener(dep, "Departure City (e.g. Hongkong)"));
		dep.setOpaque(false);
		dep.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
		dep.setBounds(image.getIconWidth() - 435, 270, 280, 50);

		JLabel to = new JLabel("To:");
		to.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 12));
		to.setForeground(new Color(102, 147, 195));
		to.setBounds(image.getIconWidth() - 435, 350, 250, 50);

		des = new JTextField(10);
		des.addFocusListener(new JTextFieldHintListener(des, "Destination City (e.g. Beijing)"));
		des.setOpaque(false);
		des.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
		des.setBounds(image.getIconWidth() - 435, 370, 280, 50);

		JButton exchange = new JButton("↑↓");
		exchange.setBounds(image.getIconWidth() - 337, 325, 70, 30);
		exchange.setFont(new Font("Times New Roman", Font.BOLD, 20));
		exchange.setForeground(new Color(102, 147, 195));
		exchange.setBorderPainted(false);
		exchange.setFocusPainted(false);
		exchange.setOpaque(false);
		exchange.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				exchange.setForeground(new Color(135, 206, 235));
			}

			public void mouseExited(MouseEvent e) {
				exchange.setForeground(new Color(102, 147, 195));
			}
		});
		exchange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (dep.getText().equals("Departure City (e.g. Hongkong)")
						&& des.getText().equals("Destination City (e.g. Beijing)")) {
					// pass;
				} else if (dep.getText().equals("Departure City (e.g. Hongkong)")) {
					dep.setText(des.getText());
					dep.setForeground(new Color(102, 147, 195));
					dep.setFont(new Font("Times New Roman", Font.PLAIN, 17));
					des.addFocusListener(new JTextFieldHintListener(des, "Destination City (e.g. Beijing)"));
				} else if (des.getText().equals("Destination City (e.g. Beijing)")) {
					des.setText(dep.getText());
					des.setForeground(new Color(102, 147, 195));
					des.setFont(new Font("Times New Roman", Font.PLAIN, 17));
					dep.addFocusListener(new JTextFieldHintListener(dep, "Departure City (e.g. Hongkong)"));
				} else {
					String tmp = dep.getText();
					dep.setText(des.getText());
					des.setText(tmp);
				}
			}
		});

		JLabel date = new JLabel("Date:");
		date.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 12));
		date.setForeground(new Color(102, 147, 195));
		date.setBounds(image.getIconWidth() - 435, 420, 250, 50);

		setDefaultDate();
		dft = new JLabel(getDate());
		dft.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		dft.setForeground(Color.GRAY);
		dft.setBounds(image.getIconWidth() - 435, 440, 280, 50);

		JButton dateBtn = new JButton();
		dateBtn.setBounds(image.getIconWidth() - 435, 440, 280, 50);
		dateBtn.setBorderPainted(false);
		dateBtn.setFocusPainted(false);
		dateBtn.setOpaque(false);
		dateBtn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				dft.setForeground(new Color(135, 206, 235));
			}

			public void mouseExited(MouseEvent e) {
				if (dft.getText() == getDate())
					dft.setForeground(Color.GRAY);
				else
					dft.setForeground(new Color(102, 147, 195));
			}
		});
		dateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DateCalendar();
			}
		});

		JButton search = new JButton("Search");
		search.setBounds(image.getIconWidth() - 275, 520, 120, 50);
		search.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		search.setForeground(new Color(102, 147, 195));
		search.setBorderPainted(false);
		search.setFocusPainted(false);
		search.setOpaque(false);

		search.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				search.setForeground(new Color(135, 206, 235));
			}

			public void mouseExited(MouseEvent evt) {
				search.setForeground(new Color(102, 147, 195));
			}
		});

		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (dep.getText().equals("Departure City"))
						throw new ExDepartureCityIsEmpty();
					else if (des.getText().equals("Destination City"))
						throw new ExDestinationCityIsEmpty();
					else if (dep.getText().equals(des.getText()))
						throw new ExDepAndDesAreSame();
					else if (dft.getForeground() == Color.GRAY)
						throw new ExDateIsEmpty();
					else if (!ticketSystem.checkCity(dep.getText()))
						throw new ExDepartureCityIsNotFound(); // case5: cannot find dep, retry
					else if (!ticketSystem.checkCity(des.getText()))
						throw new ExDestinationCityIsNotFound(); // case6: cannot find des, retry
					else
						new PurchaseTicket(user, getAllFlights(), dep.getText(), des.getText(), dft.getText());
				} catch (ExDepartureCityIsEmpty e1) {
					e1.printStackTrace();
				} catch (ExDestinationCityIsEmpty e2) {
					e2.printStackTrace();
				} catch (ExDepAndDesAreSame e3) {
					e3.printStackTrace();
				} catch (ExDateIsEmpty e4) {
					e4.printStackTrace();
				} catch (ExDepartureCityIsNotFound e5) {
					e5.printStackTrace();
				} catch (ExDestinationCityIsNotFound e6) {
					e6.printStackTrace();
				}
			}
		});

		layeredPane.add(jp, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(jl0, JLayeredPane.MODAL_LAYER);
		layeredPane.add(loginBtn, JLayeredPane.MODAL_LAYER);
		layeredPane.add(signupBtn, JLayeredPane.MODAL_LAYER);
		layeredPane.add(from, JLayeredPane.MODAL_LAYER);
		layeredPane.add(dep, JLayeredPane.MODAL_LAYER);
		layeredPane.add(to, JLayeredPane.MODAL_LAYER);
		layeredPane.add(des, JLayeredPane.MODAL_LAYER);
		layeredPane.add(exchange, JLayeredPane.MODAL_LAYER);
		layeredPane.add(date, JLayeredPane.MODAL_LAYER);
		layeredPane.add(dateBtn, JLayeredPane.MODAL_LAYER);
		layeredPane.add(dft, JLayeredPane.MODAL_LAYER);
		layeredPane.add(search, JLayeredPane.MODAL_LAYER);

		jf.setLayeredPane(layeredPane);
		jf.setSize(image.getIconWidth(), image.getIconHeight());
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}

	private void setDefaultDate() {
		Calendar now = Calendar.getInstance();
		this.date = now.get(Calendar.DAY_OF_MONTH) + " " + MM[now.get(Calendar.MONTH)] + ", " + now.get(Calendar.YEAR);
	}

	public void setNewDftBtn(String aSelectDate) {
		this.selectDate = aSelectDate;
		String jlContent = aSelectDate.substring(6) + " " + MM[Integer.parseInt(aSelectDate.substring(4, 6)) - 1] + ", "
				+ aSelectDate.substring(0, 4);
		layeredPane.remove(dft);
		dft = new JLabel(jlContent);
		dft.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		dft.setForeground(new Color(102, 147, 195));
		dft.setBounds(1005, 440, 280, 50);
		layeredPane.add(dft, JLayeredPane.MODAL_LAYER);
	}

	private String getDate() {
		return this.date;
	}

	private ArrayList<ArrayList<Flight>> getAllFlights() {
		// call the backend func;
		// (getDatetime());
		return null;
	}

	private Date getDateFormat() {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Date sDate = new Date();
		if (this.selectDate.substring(6, 8).equals(this.date.substring(0, 2)))
			return new Date();
		else
			try {
				sDate = df.parse(this.selectDate + "000000");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		Calendar cal = Calendar.getInstance();
		cal.setTime(sDate);
		return new Date(cal.getTimeInMillis());
	}

	public void setAccountBtn(User aUser) {
		this.user = aUser;
		layeredPane.remove(loginBtn);
		layeredPane.remove(signupBtn);
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

	public void logout() {
		this.user = null;
		this.layeredPane.remove(account);
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

}