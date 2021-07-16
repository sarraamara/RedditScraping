import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private String subreddit ="";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 875, 671);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		final JScrollPane pane = new JScrollPane(editorPane);
		pane.setBounds(12, 13, 833, 540);
		contentPane.add(pane);
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textField.getText().isEmpty()) {
					subreddit= textField.getText();
				}
				else {
					subreddit= "all";
				}
				
				String html = RedditScraping.scrape(subreddit);
				editorPane.setContentType("text/html");
				editorPane.setText("<html>"+html+"</html>");
				
			}
		});
		btnNewButton.setBounds(703, 566, 142, 29);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(533, 566, 158, 29);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
