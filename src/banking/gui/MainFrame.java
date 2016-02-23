package banking.gui;

import banking.primitive.core.Account;
import banking.primitive.core.AccountServer;
import banking.primitive.core.AccountServerFactory;

import java.io.*;
import java.util.*;
import java.awt.FlowLayout;
import java.awt.Container;
import java.awt.event.*;
import javax.swing.*;

/**
 * MainFrame.java
 * Creates the GUI for the program
 * @author kevinagary
 * @since: 2/20/2016
 *
 */
@SuppressWarnings("serial")
class MainFrame extends JFrame {

	AccountServer _myServer;
	Properties _props;
	JLabel _typeLabel;
	JLabel _nameLabel;
	JLabel _balanceLabel;
	JComboBox _typeOptions;
	JTextField _nameField;
	JTextField _balanceField;
	JButton _depositButton;
	JButton _withdrawButton;
	JButton	_newAccountButton;
	JButton	_displayAccountsButton;
	JButton	_displayODAccountsButton;


	public MainFrame(String propertyFile) throws IOException {

		//** initialize myServer
		_myServer = AccountServerFactory.getMe().lookup();

		_props = new Properties();

		FileInputStream fis = null; 
		try {
			fis =  new FileInputStream(propertyFile);
			_props.load(fis);
			fis.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw ioe;
		}
		_constructForm();
	}

	
	private void _constructForm() {
		//*** Make these read from properties
		_typeLabel		= new JLabel(_props.getProperty("TypeLabel"));
		_nameLabel		= new JLabel(_props.getProperty("NameLabel"));
		_balanceLabel	= new JLabel(_props.getProperty("BalanceLabel"));

		Object[] accountTypes = {"Savings", "Checking"};
		_typeOptions = new JComboBox(accountTypes);
		_nameField = new JTextField(20);
		_balanceField = new JTextField(20);

		_newAccountButton = new JButton("New Account");
		JButton depositButton = new JButton("Deposit");
		JButton withdrawButton = new JButton("Withdraw");
		JButton saveButton = new JButton("Save Accounts");
		_displayAccountsButton = new JButton("List Accounts");
		JButton displayAllAccountsButton = new JButton("All Accounts");

		this.addWindowListener(new FrameHandler());
		_newAccountButton.addActionListener(new NewAccountHandler());
		_displayAccountsButton.addActionListener(new DisplayHandler());
		displayAllAccountsButton.addActionListener(new DisplayHandler());
		depositButton.addActionListener(new DepositHandler());
		withdrawButton.addActionListener(new WithdrawHandler());
		saveButton.addActionListener(new SaveAccountsHandler());		
		
		Container pane = getContentPane();
		pane.setLayout(new FlowLayout());
		
		JPanel panel1 = new JPanel();
		panel1.add(_typeLabel);
		panel1.add(_typeOptions);
		
		JPanel panel2 = new JPanel();
		panel2.add(_displayAccountsButton);
		panel2.add(displayAllAccountsButton);
		panel2.add(saveButton);
		
		JPanel panel3 = new JPanel();
		panel3.add(_nameLabel);
		panel3.add(_nameField);
		
		JPanel panel4 = new JPanel();
		panel4.add(_balanceLabel);
		panel4.add(_balanceField);
		
		JPanel panel5 = new JPanel();
		panel5.add(_newAccountButton);
		panel5.add(depositButton);
		panel5.add(withdrawButton);

		pane.add(panel1);
		pane.add(panel2);
		pane.add(panel3);
		pane.add(panel4);
		pane.add(panel5);
		
		setSize(400, 250);
	}

	class DisplayHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			List<Account> accounts = null;
			if (e.getSource() == _displayAccountsButton) {
				accounts = _myServer.getActiveAccounts();
			} else {
				accounts = _myServer.getAllAccounts();
			}
			StringBuffer sb = new StringBuffer();
			Account thisAcct = null;
			for (Iterator<Account> li = accounts.iterator(); li.hasNext();) {
				thisAcct = (Account)li.next();
				sb.append(thisAcct.toString()+"\n");
			}

			JOptionPane.showMessageDialog(null, sb.toString());
		}
	}

	// Complete a handler for new account button
	class NewAccountHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String type = _typeOptions.getSelectedItem().toString();
			String name = _nameField.getText();
			String balance = _balanceField.getText();

			if (_myServer.newAccount(type, name, Float.parseFloat(balance))) {
				JOptionPane.showMessageDialog(null, "Account created successfully");
			} else {
				JOptionPane.showMessageDialog(null, "Account not created!");
			}
		}
	}
	
	// Complete a handler for new account button
	class SaveAccountsHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				_myServer.saveAccounts();
				JOptionPane.showMessageDialog(null, "Accounts saved");
			} catch (IOException exc) {
				JOptionPane.showMessageDialog(null, "Error saving accounts");
			}
		}
	}

	// Complete a handler for deposit button
	class DepositHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String name = _nameField.getText();
			String balance = _balanceField.getText();
			Account acc = _myServer.getAccount(name);
			if (acc != null && acc.deposit(Float.parseFloat(balance))) {
				JOptionPane.showMessageDialog(null, "Deposit successful");
			} else {
				JOptionPane.showMessageDialog(null, "Deposit unsuccessful");
			}		
		}
	}
	// Complete a handler for deposit button
	class WithdrawHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String name = _nameField.getText();
			String balance = _balanceField.getText();
			Account acc = _myServer.getAccount(name);
			if (acc != null && acc.withdraw(Float.parseFloat(balance))) {
				JOptionPane.showMessageDialog(null, "Withdrawal successful");
			} else {
				JOptionPane.showMessageDialog(null, "Withdrawal unsuccessful");
			}		
		}
	}
	
	//** Complete a handler for the Frame that terminates 
	//** (System.exit(1)) on windowClosing event

	static class FrameHandler extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}
}
