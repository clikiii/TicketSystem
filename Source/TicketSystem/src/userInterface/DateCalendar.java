package userInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class DateCalendar {

	private int year;
	private int month;
	private int day;
	private int weekDay;
	private int selectDay;
	private JFrame jf;

	public DateCalendar() {
		jf = new JFrame("DateCalendar");

		JLayeredPane layeredPane = new JLayeredPane();
		ImageIcon image = new ImageIcon("Source/ticketSystem/src/userInterface/ImgSource/calendar.jpeg");
		JPanel jp = new JPanel();
		jp.setBounds(0, -5, image.getIconWidth(), image.getIconHeight() + 22);

		JLabel jl = new JLabel(image);
		jp.add(jl);

		JLabel title = new JLabel("Select the date");
		title.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 25));
		title.setForeground(new Color(102, 147, 195));
		title.setBounds(200, 13, 400, 50);

		JLabel Sun = new JLabel("Sun.");
		Sun.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 20));
		Sun.setForeground(new Color(102, 147, 195));
		Sun.setBounds(50, 60, 100, 50);

		JLabel Mon = new JLabel("Mon.");
		Mon.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 20));
		Mon.setForeground(new Color(102, 147, 195));
		Mon.setBounds(115, 60, 100, 50);

		JLabel Tues = new JLabel("Tues.");
		Tues.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 20));
		Tues.setForeground(new Color(102, 147, 195));
		Tues.setBounds(185, 60, 100, 50);

		JLabel Wed = new JLabel("Wed.");
		Wed.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 20));
		Wed.setForeground(new Color(102, 147, 195));
		Wed.setBounds(255, 60, 100, 50);

		JLabel Thur = new JLabel("Thur.");
		Thur.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 20));
		Thur.setForeground(new Color(102, 147, 195));
		Thur.setBounds(325, 60, 100, 50);

		JLabel Fri = new JLabel("Fri.");
		Fri.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 20));
		Fri.setForeground(new Color(102, 147, 195));
		Fri.setBounds(400, 60, 100, 50);

		JLabel Sat = new JLabel("Sat.");
		Sat.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 20));
		Sat.setForeground(new Color(102, 147, 195));
		Sat.setBounds(465, 60, 100, 50);

		ArrayList<Integer> Days = Today();
		boolean isBefore = true;
		int cnt = 0;
		for (int i = 0; i < Days.size(); i++) {
			if (Days.get(i) == day)
				isBefore = false;
			if (isBefore)
				CreatABeforeBtn(i, Days.get(i), layeredPane);
			else if (cnt < 14) {
				CreatANowBtn(i, Days.get(i), layeredPane);
				cnt++;
			} else
				CreatAAfterBtn(i, Days.get(i), layeredPane);
		}

		layeredPane.add(jp, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(title, JLayeredPane.MODAL_LAYER);
		layeredPane.add(Sun, JLayeredPane.MODAL_LAYER);
		layeredPane.add(Mon, JLayeredPane.MODAL_LAYER);
		layeredPane.add(Tues, JLayeredPane.MODAL_LAYER);
		layeredPane.add(Wed, JLayeredPane.MODAL_LAYER);
		layeredPane.add(Thur, JLayeredPane.MODAL_LAYER);
		layeredPane.add(Fri, JLayeredPane.MODAL_LAYER);
		layeredPane.add(Sat, JLayeredPane.MODAL_LAYER);

		jf.setLayeredPane(layeredPane);
		jf.setSize(image.getIconWidth(), image.getIconHeight() + 22);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
	}

	private void CreatABeforeBtn(int idx, int aDay, JLayeredPane layeredPane) {
		JLabel jl = new JLabel(aDay + "");
		jl.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 20));
		jl.setForeground(Color.GRAY);
		jl.setBounds(55 + 70 * idx, 100, 100, 50);
		layeredPane.add(jl, JLayeredPane.MODAL_LAYER);
	}

	private void CreatANowBtn(int idx, int aDay, JLayeredPane layeredPane) {
		JLabel jl = new JLabel(aDay + "");

		JButton jb = new JButton();

		jl.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 20));
		jl.setForeground(new Color(102, 147, 195));

		jb.setBorderPainted(false);
		jb.setFocusPainted(false);
		jb.setOpaque(false);
		jb.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				jl.setForeground(new Color(135, 206, 235));
			}

			public void mouseExited(MouseEvent evt) {
				jl.setForeground(new Color(102, 147, 195));
			}
		});
		jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setSelectDay(aDay);
				SearchFlight.getInstance().setNewDftBtn(getSelectDate());
				getJFrame().dispose();
			}

		});
		if (idx < 7) {
			jl.setBounds(53 + 70 * idx, 100, 100, 50);
			jb.setBounds(43 + 70 * idx, 105, 40, 40);
		} else if (idx >= 7 && idx < 14) {
			jl.setBounds(53 + 70 * (idx % 7), 140, 100, 50);
			jb.setBounds(43 + 70 * (idx % 7), 145, 40, 40);
		} else {
			jl.setBounds(53 + 70 * (idx % 7), 180, 100, 50);
			jb.setBounds(43 + 70 * (idx % 7), 185, 40, 40);
		}
		layeredPane.add(jl, JLayeredPane.MODAL_LAYER);
		layeredPane.add(jb, JLayeredPane.MODAL_LAYER);
	}

	private void CreatAAfterBtn(int idx, int aDay, JLayeredPane layeredPane) {
		JLabel jl = new JLabel(aDay + "");
		jl.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 20));
		jl.setForeground(Color.GRAY);
		jl.setBounds(50 + 70 * (idx % 7), 180, 100, 50);
		layeredPane.add(jl, JLayeredPane.MODAL_LAYER);
	}

	private ArrayList<Integer> Today() {
		Calendar now = Calendar.getInstance();
		this.year = now.get(Calendar.YEAR);
		this.month = now.get(Calendar.MONTH) + 1;
		this.day = now.get(Calendar.DAY_OF_MONTH);
		this.weekDay = now.get(Calendar.DAY_OF_WEEK);
		int daysOfMonth = numDaysOfMonth(year, month);
		int daysOfLastM = month == 1 ? 31 : numDaysOfMonth(year, month - 1);

		ArrayList<Integer> Days = new ArrayList<Integer>();
		int tmpWeekDay = weekDay;
		while (--weekDay > 0) {
			if (day - weekDay > 0)
				Days.add(day - weekDay);
			else
				Days.add(daysOfLastM - weekDay + 1);
		}
		int tmpDay = day;
		for (int i = 0; i < 22 - tmpWeekDay; i++) {
			Days.add(tmpDay);
			tmpDay = tmpDay == daysOfMonth ? 1 : tmpDay + 1;
		}
		return Days;
	}

	private int numDaysOfMonth(int aYear, int aMonth) {
		if (aMonth != 2) {
			switch (aMonth) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				return 31;
			case 4:
			case 6:
			case 9:
			case 11:
				return 30;
			default:
				break;
			}
		} else {
			if (isLeapYear(aYear)) {
				return 29;
			} else {
				return 28;
			}
		}
		return 0;
	}

	private boolean isLeapYear(int aYear) {
		if (aYear % 4 == 0 && aYear % 100 != 0 || aYear % 400 == 0)
			return true;
		return false;
	}

	private void setSelectDay(int aSelectDay) {
		this.selectDay = aSelectDay;
	}

	private JFrame getJFrame() {
		return this.jf;
	}

	public int getSelectDay() {
		return this.selectDay;
	}

	public int getSelectYear() {
		if (getSelectMonth() == 1)
			return this.year + 1;
		else
			return this.year;
	}

	public int getSelectMonth() {
		if (this.selectDay < this.day) {
			if (this.month == 12)
				return 1;
			return this.month + 1;
		}
		return this.month;
	}
	
	public String getSelectDate() {
		if (getSelectDay()<10 && getSelectMonth()<10)
			return getSelectYear() + "0" + getSelectMonth() + "0" + getSelectDay();
		else if (getSelectDay()<10)
			return getSelectYear() + "" + getSelectMonth() + "0" + getSelectDay();
		else if (getSelectMonth()<10)
			return getSelectYear() + "0" + getSelectMonth() + "" + getSelectDay();
		return getSelectYear() + "" + getSelectMonth() + "" + getSelectDay();
	}
}
