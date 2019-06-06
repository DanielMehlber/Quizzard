package ui;

import com.danielmehlber.myui.MyDesign;
import com.danielmehlber.myui.MyFrame;

import main.MainComponent;

public class UI extends MyFrame{

	MainComponent mainComponent;
	
	public UI(MainComponent mc) {
		super(MyDesign.FOX);
		mainComponent = mc;
		setTitle("Quizzard");
		
		//Final line
		go(600, 600);
	}

}
