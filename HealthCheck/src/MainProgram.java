import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;

public class MainProgram extends JFrame {

	private JPanel contentPane;
	
	public MainProgram(String name) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 970, 511);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel westPanel = new JPanel();
		westPanel.setBackground(Color.WHITE);
		westPanel.setBounds(10, 10, 336, 454);
		contentPane.add(westPanel);
		westPanel.setLayout(null);
		
		JLabel userName = new JLabel(name);
		userName.setFont(new Font("굴림", Font.BOLD, 29));
		userName.setHorizontalAlignment(SwingConstants.CENTER);
		userName.setBounds(12, 28, 127, 65);
		westPanel.add(userName);
		
		JPanel eastPanel = new JPanel();
		eastPanel.setBackground(Color.WHITE);
		eastPanel.setBounds(358, 10, 586, 454);
		contentPane.add(eastPanel);
		eastPanel.setLayout(null);
		
		JButton nearbySearchButton = new JButton("주변 검사소 찾기");
		nearbySearchButton.setFont(new Font("굴림", Font.BOLD, 21));
		nearbySearchButton.setBounds(299, 10, 275, 362);
		eastPanel.add(nearbySearchButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(12, 382, 562, 62);
		eastPanel.add(lblNewLabel);
		
		JButton userHealth = new JButton("내 건강정보 조회");
		userHealth.setFont(new Font("굴림", Font.BOLD, 21));
		userHealth.setBounds(12, 10, 275, 362);
		eastPanel.add(userHealth);
	}
}
