package userInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPasswordField;

public class JPasswordFieldHintListenser implements FocusListener {
	private String hintText;
	private JPasswordField passwordField;

	public JPasswordFieldHintListenser(JPasswordField jPasswordField, String hintText) {
		this.passwordField = jPasswordField;
		this.hintText = hintText;
		jPasswordField.setEchoChar((char) 0);
		jPasswordField.setText(hintText);
		jPasswordField.setForeground(Color.GRAY);
		jPasswordField.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	}

	@Override
	public void focusGained(FocusEvent e) {
		String temp = new String(passwordField.getPassword());
		if (temp.equals(hintText)) {
			passwordField.setEchoChar('●');
			passwordField.setText("");
			passwordField.setForeground(new Color(102, 147, 195));
			passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		}

	}

	@Override
	public void focusLost(FocusEvent e) {
		String temp = new String(passwordField.getPassword());
		if (temp.equals("")) {
			passwordField.setEchoChar((char) 0);
			passwordField.setText(hintText);
			passwordField.setForeground(Color.GRAY);
			passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		}

	}

}