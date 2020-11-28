package Sign;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import RhythmGame.Multiplay;

public class SignUpForm extends JFrame {

	DataTransferObject dto = new DataTransferObject(); // object dto

	private JPanel contentPane;

	private JTextField tfMemberName; // name
	private JTextField tfEmail; // email

	private JPasswordField jpMemberPassword; // password
	private JPasswordField jpMemberPasswordCheck; // password check

	private JLabel laTitle;
	private JLabel laName;
	private JLabel laPassword;
	private JLabel laPasswordCheck;
	private JLabel laReInput;
	private JLabel laEmail;

	private JButton btLogin;
	private JButton btCancel;
	private JButton btCheck;

	private boolean checkPass = false;
	private boolean checkName = false;

	public SignUpForm() {

		setTitle("SignUp");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(450, 500);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	
		// 종료버튼 누를 시
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				new Multiplay();
			}
		});

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		laTitle = new JLabel("Sign-Up", JLabel.CENTER);
		laName = new JLabel("ID", JLabel.RIGHT);
		laPassword = new JLabel("Password", JLabel.RIGHT);
		laPasswordCheck = new JLabel("Password Check", JLabel.RIGHT);
		laReInput = new JLabel("ReInput", JLabel.RIGHT);
		laEmail = new JLabel("Email", JLabel.RIGHT);

		laTitle.setFont(new Font("�븿珥덈＼�룍��", Font.BOLD, 20));

		btLogin = new JButton("Login");
		btCancel = new JButton("Cancel");
		btCheck = new JButton("Check");

		tfMemberName = new JTextField();
		tfEmail = new JTextField();

		jpMemberPassword = new JPasswordField();
		jpMemberPasswordCheck = new JPasswordField();

		// title
		laTitle.setBounds(159, 41, 101, 30);
		contentPane.add(laTitle);

		// id
		laName.setBounds(69, 113, 69, 20);
		tfMemberName.setColumns(10);
		tfMemberName.setBounds(159, 106, 170, 35);
		btCheck.setBounds(330, 105, 70, 35);
		contentPane.add(laName);
		contentPane.add(tfMemberName);
		contentPane.add(btCheck); // name check

		// password
		laPassword.setBounds(69, 163, 69, 20);
		jpMemberPassword.setColumns(10);
		jpMemberPassword.setBounds(159, 156, 170, 35);
		contentPane.add(laPassword);
		contentPane.add(jpMemberPassword);

		// password check
		laPasswordCheck.setBounds(49, 213, 99, 20);
		jpMemberPassword.setColumns(10);
		jpMemberPasswordCheck.setBounds(159, 206, 170, 35);
		contentPane.add(laPasswordCheck);
		contentPane.add(jpMemberPasswordCheck);

		// email
		laEmail.setBounds(69, 263, 69, 20);
		tfEmail.setBounds(159, 253, 170, 35);
		contentPane.add(laEmail);
		contentPane.add(tfEmail);

		// login
		btLogin.setBounds(140, 363, 80, 35);
		contentPane.add(btLogin);

		// cancel
		btCancel.setBounds(230, 363, 80, 35);
		contentPane.add(btCancel);

		// name check button event
		btCheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String temp = null;
				Object obj = e.getSource();

				if ((JButton) obj == btCheck) { // set name
					temp = tfMemberName.getText();
				}
				if (!DataAccessObject.nameCheck(temp)) {
					JOptionPane.showMessageDialog(null, "Already exist name...");
				} else {
					JOptionPane.showMessageDialog(null, "Enable!!!");
					checkName = true;
				}
			}
		});

		// login button event
		btLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Object obj = e.getSource();

				// check name, password
				if ((JButton) obj == btLogin) {
					String tempPass = jpMemberPasswordCheck.getText();

					if (tempPass.equals(jpMemberPassword.getText())) {
						checkPass = true;
					} else {
						JOptionPane.showMessageDialog(null, "Password error...");
						jpMemberPassword.setText("");
						jpMemberPasswordCheck.setText("");
						return;
					}
				}

				if (!checkName) {
					JOptionPane.showMessageDialog(null, "Please Check ID...");
					return;
				}

				dto.setName(tfMemberName.getText());
				dto.setPassword(jpMemberPassword.getText());
				dto.setAddress(tfEmail.getText().toString());

				try {
					DataAccessObject.create(dto);

					System.out.println("Data Insert Success...");
					JOptionPane.showMessageDialog(null, "Create Success!!!");
					dispose();
					new SignInForm();
				} catch (Exception ex) {
					System.out.println("Data Insert Fail... " + ex.getMessage());
				}
			}
		});

		// cancel button event
		btCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Multiplay();
				dispose();
			}
		});
	}
}