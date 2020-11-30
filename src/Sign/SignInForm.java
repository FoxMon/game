package Sign;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import RhythmGame.DynamicBeat;
import RhythmGame.Main;
import RhythmGame.Multiplay;

public class SignInForm extends JFrame {

	public static DataTransferObject dto = new DataTransferObject(); // object dto

	private JPanel contentPane;

	private JTextField tfMemberName; // name

	private JPasswordField jpMemberPassword; // password

	private JLabel laTitle;
	private JLabel laName;
	private JLabel laPassword;

	private JButton btLogin;
	private JButton btCancel;

	public SignInForm() {

		setTitle("SignIn");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 400);
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

		laTitle = new JLabel("Sign-In", JLabel.CENTER);
		laName = new JLabel("ID", JLabel.RIGHT);
		laPassword = new JLabel("Password", JLabel.RIGHT);

		laTitle.setFont(new Font("함초롱바탕", Font.BOLD, 20));

		btLogin = new JButton("Login");
		btCancel = new JButton("Cancel");

		tfMemberName = new JTextField();

		jpMemberPassword = new JPasswordField();

		// title
		laTitle.setBounds(140, 41, 101, 30);
		contentPane.add(laTitle);

		// id
		laName.setBounds(50, 113, 69, 20);
		tfMemberName.setColumns(10);
		tfMemberName.setBounds(140, 106, 170, 35);
		contentPane.add(laName);
		contentPane.add(tfMemberName);

		// password
		laPassword.setBounds(50, 163, 69, 20);
		jpMemberPassword.setColumns(10);
		jpMemberPassword.setBounds(140, 156, 170, 35);
		contentPane.add(laPassword);
		contentPane.add(jpMemberPassword);

		// login
		btLogin.setBounds(125, 240, 80, 35);
		contentPane.add(btLogin);

		// cancel
		btCancel.setBounds(215, 240, 80, 35);
		contentPane.add(btCancel);

		// login button event.
		btLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Object obj = e.getSource();

				if ((JButton) obj == btLogin) { // set name, password.
					dto.setName(tfMemberName.getText());
					dto.setPassword(jpMemberPassword.getText());
				}

				try {
					switch (DataAccessObject.loginCheck(dto)) {
					case 1:
						JOptionPane.showMessageDialog(null, "Success!!!");
						Main.isLogin = true;
						new chat.MainChat();
						dispose();
						Main.dynamicBeat.setVisible(false); // 시작화면 제거
						Main.isLogin = true;
						break;

					case 0:
						JOptionPane.showMessageDialog(null, "Password Error...");
						break;

					case -1:
						JOptionPane.showMessageDialog(null, "None ID...");
						break;
					}
				} catch (Exception ex) {
					System.out.println("Login Failed... " + ex.getMessage());
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