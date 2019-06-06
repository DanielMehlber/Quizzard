package main;

import ui.UI;

public class MainComponent {

	UI ui;
	
	public MainComponent() {
		ui = new UI(this);
	}
	
	public static void main(String[]args) {
		Console.info("main", "game started", false);
		new MainComponent();
	}
	
	

}
