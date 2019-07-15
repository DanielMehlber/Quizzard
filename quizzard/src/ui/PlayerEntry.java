package ui;

import javax.swing.JPanel;

import javax.swing.JLabel;

public class PlayerEntry extends JPanel{

	private UI ui;
	
	public PlayerEntry(UI ui) {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 63, 63);
		add(panel);
		
		JLabel lblName = new JLabel("New label");
		lblName.setBounds(83, 10, 207, 30);
		add(lblName);
		
		JLabel lblTrophies = new JLabel("New label");
		lblTrophies.setBounds(83, 43, 207, 30);
		add(lblTrophies);
		//super(ui.getDesign());
	}

}
