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

import ticketSystem.Order;
import ticketSystem.User;

public class MyOrders {

    private static User user;
    private static JScrollPane jsp;
    private static JLayeredPane layeredPane;
    private static int idx;
    private static ArrayList<Order> allOrders;
    private static JPanel tkt;
    private static JFrame jf;

    public MyOrders(User aUser) {
        user = aUser;
        allOrders = user.getMyOrder();
        
        jf = new JFrame("Login");

        layeredPane = new JLayeredPane();

        ImageIcon image = new ImageIcon("Source/ticketSystem/src/userInterface/ImgSource/background.jpeg");
        JLabel img = new JLabel(image);

        JPanel jp = new JPanel() {
            @Override
            public void paint(Graphics graphics) {
                super.paint(graphics);
                ((Graphics2D) graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON); // Anti-Aliasing
                graphics.setColor(new Color(102, 147, 195));

            }
        };
        jp.setBounds(0, -5, image.getIconWidth(), image.getIconHeight());

        jp.add(img);

        JLabel jl0 = new JLabel("My Orders");
        jl0.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
        jl0.setForeground(new Color(102, 147, 195));
        jl0.setBounds(image.getIconWidth() - 355, 125, 300, 50);

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
        tickets.setPreferredSize(new Dimension(520, 120 * allOrders.size()));
        tickets.setOpaque(false);
        tickets.setLayout(null);
        for (idx = 0; idx < allOrders.size(); idx++) {

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

            JLabel fid = new JLabel(allOrders.get(idx).getFlightSetObjList().get(0).getFid());
            fid.setFont(new Font("Times New Roman", Font.ITALIC, 15));
            fid.setForeground(Color.GRAY);
            fid.setBounds(20, 0, 300, 40);

            JLabel takeOffTime = new JLabel(
                    allOrders.get(idx).getFlightSetObjList().get(0).getTakeOffTime().toString().substring(11, 16));
            takeOffTime.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
            takeOffTime.setForeground(new Color(102, 147, 195));
            takeOffTime.setBounds(40, 30, 300, 50);

            JLabel dep = new JLabel(allOrders.get(idx).getFlightSetObjList().get(0).getDeparture(), JLabel.CENTER);
            dep.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
            dep.setForeground(new Color(102, 147, 195));
            dep.setBounds(17, 60, 100, 50);

            JLabel landingTime = new JLabel(allOrders.get(idx).getFlightSetObjList()
                    .get(allOrders.get(idx).getFlightSetObjList().size() - 1).getLandingTime().toString()
                    .substring(11, 16));
            landingTime.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
            landingTime.setForeground(new Color(102, 147, 195));
            landingTime.setBounds(265, 30, 300, 50);

            JLabel des = new JLabel(allOrders.get(idx).getFlightSetObjList()
                    .get(allOrders.get(idx).getFlightSetObjList().size() - 1).getDestination(), JLabel.CENTER);
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

            JLabel num = new JLabel("×" + allOrders.get(idx).getNumber());
            num.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 25));
            num.setForeground(new Color(255, 109, 107));
            num.setBounds(355, 35, 100, 50);

            JLabel cancel = new JLabel("Cancel");
            cancel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
            cancel.setForeground(new Color(255, 109, 107));
            cancel.setBounds(405, 20, 280, 50);

            JLabel order = new JLabel("Order");
            order.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
            order.setForeground(new Color(255, 109, 107));
            order.setBounds(410, 50, 280, 50);

            JLabel index = new JLabel(idx + "");

            JButton cancelBtn = new JButton();
            cancelBtn.setBounds(390, 0, 90, 120);
            cancelBtn.setBorderPainted(false);
            cancelBtn.setFocusPainted(false);
            cancelBtn.setOpaque(false);
            cancelBtn.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    cancel.setForeground(Color.RED);
                    order.setForeground(Color.RED);
                }

                public void mouseExited(MouseEvent e) {
                    cancel.setForeground(new Color(255, 109, 107));
                    order.setForeground(new Color(255, 109, 107));
                }
            });
            cancelBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int orderIdx = Integer.parseInt(index.getText());
                    new ConfirmDeleteFlight(allOrders.get(orderIdx).getOrderIndex(), user, allOrders.get(orderIdx));
                }
            });

            tkt.add(fid);
            tkt.add(takeOffTime);
            tkt.add(landingTime);
            tkt.add(dep);
            tkt.add(des);
            tkt.add(num);
            tkt.add(cancel);
            tkt.add(order);
            tkt.add(cancelBtn);

            if (allOrders.get(idx).getFlightSetObjList().size() == 2) {
                tkt.add(stop);
                fid.setText(allOrders.get(idx).getFlightSetObjList().get(0).getFid() + " & "
                        + allOrders.get(idx).getFlightSetObjList().get(1).getFid());
            }
            if (isNextDayArrival(allOrders.get(idx).getFlightSetObjList().get(0).getTakeOffTime(),
                    allOrders.get(idx).getFlightSetObjList().get(allOrders.get(idx).getFlightSetObjList().size() - 1)
                            .getLandingTime()))
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
        layeredPane.add(jl0, JLayeredPane.MODAL_LAYER);
        layeredPane.add(jsp, JLayeredPane.MODAL_LAYER);
        layeredPane.add(returnBtn, JLayeredPane.MODAL_LAYER);

        jf.setLayeredPane(layeredPane);
        jf.setSize(image.getIconWidth(), image.getIconHeight());
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setVisible(true);
    }

    public static void CreateNewJsp() {
        layeredPane.remove(jsp);

        JPanel tickets = new JPanel();
        tickets.setPreferredSize(new Dimension(520, 120 * allOrders.size()));
        tickets.setOpaque(false);
        tickets.setLayout(null);
        for (idx = 0; idx < allOrders.size(); idx++) {

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

            JLabel fid = new JLabel(allOrders.get(idx).getFlightSetObjList().get(0).getFid());
            fid.setFont(new Font("Times New Roman", Font.ITALIC, 15));
            fid.setForeground(Color.GRAY);
            fid.setBounds(20, 0, 300, 40);

            JLabel takeOffTime = new JLabel(
                    allOrders.get(idx).getFlightSetObjList().get(0).getTakeOffTime().toString().substring(11, 16));
            takeOffTime.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
            takeOffTime.setForeground(new Color(102, 147, 195));
            takeOffTime.setBounds(40, 30, 300, 50);

            JLabel dep = new JLabel(allOrders.get(idx).getFlightSetObjList().get(0).getDeparture(), JLabel.CENTER);
            dep.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
            dep.setForeground(new Color(102, 147, 195));
            dep.setBounds(17, 60, 100, 50);

            JLabel landingTime = new JLabel(allOrders.get(idx).getFlightSetObjList()
                    .get(allOrders.get(idx).getFlightSetObjList().size() - 1).getLandingTime().toString()
                    .substring(11, 16));
            landingTime.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
            landingTime.setForeground(new Color(102, 147, 195));
            landingTime.setBounds(265, 30, 300, 50);

            JLabel des = new JLabel(allOrders.get(idx).getFlightSetObjList()
                    .get(allOrders.get(idx).getFlightSetObjList().size() - 1).getDestination(), JLabel.CENTER);
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
            
            JLabel index = new JLabel(idx + "");

            JLabel num = new JLabel("×" + allOrders.get(idx).getNumber());
            num.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 25));
            num.setForeground(new Color(255, 109, 107));
            num.setBounds(355, 35, 100, 50);

            JLabel cancel = new JLabel("Cancel");
            cancel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
            cancel.setForeground(new Color(255, 109, 107));
            cancel.setBounds(405, 20, 280, 50);

            JLabel order = new JLabel("Order");
            order.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
            order.setForeground(new Color(255, 109, 107));
            order.setBounds(410, 50, 280, 50);

            JButton cancelBtn = new JButton();
            cancelBtn.setBounds(390, 0, 90, 120);
            cancelBtn.setBorderPainted(false);
            cancelBtn.setFocusPainted(false);
            cancelBtn.setOpaque(false);
            cancelBtn.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    cancel.setForeground(Color.RED);
                    order.setForeground(Color.RED);
                }

                public void mouseExited(MouseEvent e) {
                    cancel.setForeground(new Color(255, 109, 107));
                    order.setForeground(new Color(255, 109, 107));
                }
            });
            cancelBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int orderIdx = Integer.parseInt(index.getText());
                    new ConfirmDeleteFlight(allOrders.get(orderIdx).getOrderIndex(), user, allOrders.get(orderIdx));
                }
            });

            tkt.add(fid);
            tkt.add(takeOffTime);
            tkt.add(landingTime);
            tkt.add(dep);
            tkt.add(des);
            tkt.add(num);
            tkt.add(cancel);
            tkt.add(order);
            tkt.add(cancelBtn);

            if (allOrders.get(idx).getFlightSetObjList().size() == 2) {
                tkt.add(stop);
                fid.setText(allOrders.get(idx).getFlightSetObjList().get(0).getFid() + " & "
                        + allOrders.get(idx).getFlightSetObjList().get(1).getFid());
            }
            if (isNextDayArrival(allOrders.get(idx).getFlightSetObjList().get(0).getTakeOffTime(),
                    allOrders.get(idx).getFlightSetObjList().get(allOrders.get(idx).getFlightSetObjList().size() - 1)
                            .getLandingTime()))
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

    private static boolean isNextDayArrival(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return cal1.get(Calendar.DATE) != cal2.get(Calendar.DATE);
    }

    public static void CloseWindow() {
        jf.dispose();
    }

}
