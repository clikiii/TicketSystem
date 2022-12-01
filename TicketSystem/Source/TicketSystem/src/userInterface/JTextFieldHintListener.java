package userInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class JTextFieldHintListener implements FocusListener {
	private String hintText;
	private JTextField textField;

	public JTextFieldHintListener(JTextField jTextField, String hintText) {
		this.textField = jTextField;
		this.hintText = hintText;
		jTextField.setText(hintText);
		jTextField.setForeground(Color.GRAY);
		jTextField.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	}

	@Override
	public void focusGained(FocusEvent e) {
		String temp = textField.getText();
		if (temp.equals(hintText)) {
			textField.setText("");
			textField.setForeground(new Color(102, 147, 195));
			textField.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		}

	}

	@Override
	public void focusLost(FocusEvent e) {
		String temp = textField.getText();
		if (temp.equals("")) {
			textField.setText(hintText);
			textField.setForeground(Color.GRAY);
			textField.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		}

	}

}