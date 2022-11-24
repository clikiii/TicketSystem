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
import ticketSystem.database.dbException.ExDbFlightNotFound;
import ticketSystem.database.dbException.ExDbSeatInsufficient;

public class SelectPassengers {

    private ArrayList<Flight> flight;
    private JLabel passengers;
    protected int num = 1;
    protected User user;
    private static JFrame jf;

    public SelectPassengers(ArrayList<Flight> aFlight, User aUser) {
        this.flight = aFlight;
        this.user = aUser;
        if (flight.size() == 1)
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
                // graphics.drawRect(image.getIconWidth() - 447, 645, 140, 50);

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

        JLabel dep = new JLabel(flight.get(0).getDeparture());
        dep.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
        dep.setForeground(new Color(102, 147, 195));
        dep.setBounds(1140, 110, 300, 50);

        JLabel des = new JLabel(flight.get(0).getDestination());
        des.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
        des.setForeground(new Color(102, 147, 195));
        des.setBounds(1140, 228, 300, 50);

        long diff1 = flight.get(1).getTakeOffTime().getTime() - flight.get(0).getLandingTime().getTime();
        long hours1 = diff1 / (1000 * 60 * 60);
        long minutes1 = (diff1 - hours1 * (1000 * 60 * 60)) / (1000 * 60);
        String durationT1 = hours1 + "hours " + minutes1 + "minutes";

        System.out.println("start" + flight.get(1).getTakeOffTime());
        System.out.println("end" + flight.get(0).getLandingTime());

        JLabel stop = new JLabel("Stop Duration: " + durationT1);
        stop.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        stop.setForeground(Color.GRAY);
        stop.setBounds(1140, 268, 300, 50);

        JLabel depTime = new JLabel(flight.get(0).getTakeOffTime().toString().substring(11, 16));
        depTime.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
        depTime.setForeground(new Color(102, 147, 195));
        depTime.setBounds(1020, 110, 300, 50);

        JLabel desTime = new JLabel(flight.get(0).getLandingTime().toString()
                .substring(11, 16));
        desTime.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
        desTime.setForeground(new Color(102, 147, 195));
        desTime.setBounds(1020, 228, 300, 50);

        Date date1 = flight.get(0).getTakeOffTime();
        String depDate = date1.toString().substring(8, 11) + date1.toString().substring(4, 7) + ", "
                + date1.toString().substring(24);

        JLabel depDateT = new JLabel(depDate);
        depDateT.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
        depDateT.setForeground(Color.GRAY);
        depDateT.setBounds(992, 135, 300, 50);

        Date date2 = flight.get(0).getLandingTime();
        String desDate = date2.toString().substring(8, 11) + date2.toString().substring(4, 7) + ", "
                + date2.toString().substring(24);

        JLabel desDateT = new JLabel(desDate);
        desDateT.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
        desDateT.setForeground(Color.GRAY);
        desDateT.setBounds(992, 253, 300, 50);

        JLabel flightNum = new JLabel("Flight No. " + flight.get(0).getFid());
        flightNum.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
        flightNum.setForeground(Color.GRAY);
        flightNum.setBounds(1140, 168, 300, 50);

        JLabel dep2 = new JLabel(flight.get(1).getDeparture());
        dep2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
        dep2.setForeground(new Color(102, 147, 195));
        dep2.setBounds(1140, 310, 300, 50);

        JLabel des2 = new JLabel(flight.get(1).getDestination());
        des2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
        des2.setForeground(new Color(102, 147, 195));
        des2.setBounds(1140, 428, 300, 50);

        JLabel depTime2 = new JLabel(flight.get(1).getTakeOffTime().toString().substring(11, 16));
        depTime2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
        depTime2.setForeground(new Color(102, 147, 195));
        depTime2.setBounds(1020, 310, 300, 50);

        JLabel desTime2 = new JLabel(flight.get(flight.size() - 1).getLandingTime().toString()
                .substring(11, 16));
        desTime2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
        desTime2.setForeground(new Color(102, 147, 195));
        desTime2.setBounds(1020, 428, 300, 50);

        Date date3 = flight.get(1).getTakeOffTime();
        String depDate3 = date3.toString().substring(8, 11) + date3.toString().substring(4, 7) + ", "
                + date3.toString().substring(24);

        JLabel depDateT2 = new JLabel(depDate3);
        depDateT2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
        depDateT2.setForeground(Color.GRAY);
        depDateT2.setBounds(992, 335, 300, 50);

        Date date4 = flight.get(1).getLandingTime();
        String desDate4 = date4.toString().substring(8, 11) + date4.toString().substring(4, 7) + ", "
                + date4.toString().substring(24);

        JLabel desDateT2 = new JLabel(desDate4);
        desDateT2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
        desDateT2.setForeground(Color.GRAY);
        desDateT2.setBounds(992, 453, 300, 50);

        JLabel flightNum2 = new JLabel("Flight No. " + flight.get(1).getFid());
        flightNum2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
        flightNum2.setForeground(Color.GRAY);
        flightNum2.setBounds(1140, 368, 300, 50);

        long diff2 = flight.get(1).getLandingTime().getTime() - flight.get(0).getTakeOffTime().getTime();
        System.out.println(diff2);
        long hours2 = diff2 / (1000 * 60 * 60);
        System.out.println(hours2);
        long minutes2 = (diff2 - hours2 * 1000 * 60 * 60) / (1000 * 60);
        System.out.println(minutes2);
        String durationT2 = hours2 + "hours " + minutes2 + "minutes";

        JLabel duration = new JLabel("Trip Duration: " + durationT2);
        duration.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
        duration.setForeground(new Color(102, 147, 195));
        duration.setBounds(image.getIconWidth() - 447, 40, 447, 50);

        JLabel available = new JLabel(
                "Available Seats:  " + Math.min(flight.get(0).getAvailableSeats(), flight.get(1).getAvailableSeats()));
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
                if (num == Math.min(flight.get(0).getAvailableSeats(), flight.get(1).getAvailableSeats())) {
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
                System.out.println("111");
                if (flight.size() == 1) {
                    try {
                        user.addOrder(flight.get(0).getFlightIndex() + "", num, user.getUsername());
                    } catch (ExDbSeatInsufficient e1) {
                        e1.printStackTrace();
                    } catch (ExDbFlightNotFound e1) {
                        e1.printStackTrace();
                    }
                } else if (flight.size() == 2) {
                    try {
                        user.addOrder(flight.get(0).getFlightIndex() + " " + flight.get(1).getFlightIndex(), num,
                                user.getUsername());
                    } catch (ExDbSeatInsufficient e1) {
                        e1.printStackTrace();
                    } catch (ExDbFlightNotFound e1) {
                        e1.printStackTrace();
                    }
                }
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
                // graphics.drawRect(image.getIconWidth() - 447, 545, 140, 50);
            }
        };
        jp.setBounds(0, -5, image.getIconWidth(), image.getIconHeight());

        jp.add(img);

        JLabel dep = new JLabel(flight.get(0).getDeparture());
        dep.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
        dep.setForeground(new Color(102, 147, 195));
        dep.setBounds(1140, 280 - 70, 300, 50);

        JLabel des = new JLabel(flight.get(0).getDestination());
        des.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
        des.setForeground(new Color(102, 147, 195));
        des.setBounds(1140, 398 - 70, 300, 50);

        JLabel depTime = new JLabel(flight.get(0).getTakeOffTime().toString().substring(11, 16));
        depTime.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
        depTime.setForeground(new Color(102, 147, 195));
        depTime.setBounds(1020, 280 - 70, 300, 50);

        JLabel desTime = new JLabel(flight.get(flight.size() - 1).getLandingTime().toString()
                .substring(11, 16));
        desTime.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
        desTime.setForeground(new Color(102, 147, 195));
        desTime.setBounds(1020, 398 - 70, 300, 50);

        Date date1 = flight.get(0).getTakeOffTime();
        String depDate = date1.toString().substring(8, 11) + date1.toString().substring(4, 7) + ", "
                + date1.toString().substring(24);

        JLabel depDateT = new JLabel(depDate);
        depDateT.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
        depDateT.setForeground(Color.GRAY);
        depDateT.setBounds(992, 305 - 70, 300, 50);

        Date date2 = flight.get(0).getLandingTime();
        String desDate = date2.toString().substring(8, 11) + date2.toString().substring(4, 7) + ", "
                + date2.toString().substring(24);

        JLabel desDateT = new JLabel(desDate);
        desDateT.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
        desDateT.setForeground(Color.GRAY);
        desDateT.setBounds(992, 328 + 25, 300, 50);

        JLabel flightNum = new JLabel("Flight No. " + flight.get(0).getFid());
        flightNum.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
        flightNum.setForeground(Color.GRAY);
        flightNum.setBounds(1140, 338 - 70, 300, 50);

        long diff = flight.get(0).getLandingTime().getTime() - flight.get(0).getTakeOffTime().getTime();
        long hours = diff / (1000 * 60 * 60);
        long minutes = (diff - hours * 1000 * 60 * 60) / (1000 * 60);
        String durationT = hours + "hours " + minutes + "minutes";

        JLabel duration = new JLabel("Trip Duration: " + durationT);
        duration.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
        duration.setForeground(new Color(102, 147, 195));
        duration.setBounds(image.getIconWidth() - 447, 140, 447, 50);

        JLabel available = new JLabel("Available Seats:  " + flight.get(0).getAvailableSeats());
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
                if (num == flight.get(0).getAvailableSeats()) {
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
                try {
                    user.addOrder(flight.get(0).getFlightIndex() + "", num, user.getUsername());
                } catch (ExDbSeatInsufficient e1) {
                    e1.printStackTrace();
                } catch (ExDbFlightNotFound e1) {
                    e1.printStackTrace();
                }
                new BuySuccessfully(user);
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
