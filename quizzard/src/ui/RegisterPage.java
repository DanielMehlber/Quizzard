package ui;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.danielmehlber.myui.MyButton;
import com.danielmehlber.myui.MyDialog;
import com.danielmehlber.myui.MyDirection;
import com.danielmehlber.myui.MyLabel;
import com.danielmehlber.myui.MyPage;
import com.danielmehlber.myui.MyTextEntry;

import main.ErrCode;

public class RegisterPage extends MyPage {

	
	private UI ui;
	private MyPage contentHolder;
	/**
	 * Create the panel.
	 */
	public RegisterPage(UI ui) {
		super(ui.getDesign());
		this.ui = ui;
		setLayout(null);
		contentHolder = new MyPage(ui.getDesign());
		contentHolder.setBounds(320, 99, 568, 669);
		add(contentHolder);
		contentHolder.setLayout(null);
		
		JLabel label = new MyLabel(ui.getDesign());
		label.setText("Register");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(150, 13, 260, 41);
		contentHolder.add(label);
		
		MyTextEntry entryUsername = new MyTextEntry(ui.getDesign());
		entryUsername.setBounds(58, 100, 453, 55);
		contentHolder.add(entryUsername);
		entryUsername.setSubtext("Select username");
		
		MyTextEntry entryPassword = new MyTextEntry(ui.getDesign());
		entryPassword.setBounds(58, 177, 453, 55);
		contentHolder.add(entryPassword);
		entryPassword.setSubtext("Select your password");
		
		MyTextEntry entryPasswordRepeat = new MyTextEntry(ui.getDesign());
		entryPasswordRepeat.setBounds(58, 255, 453, 55);
		contentHolder.add(entryPasswordRepeat);
		entryPasswordRepeat.setSubtext("Repeat password");
		
		MyButton btnBack = new MyButton(ui.getDesign(), "Back");
		btnBack.setBounds(58, 330, 156, 41);
		contentHolder.add(btnBack);
		btnBack.addRunnable(()->{ui.changePage(ui.pageStart, MyDirection.EAST);});
		
		MyButton btnRegister = new MyButton(ui.getDesign(), "Register");
		btnRegister.setBounds(355, 330, 156, 41);
		contentHolder.add(btnRegister);
		btnRegister.addRunnable(() -> {
			ErrCode code = ui.mainComponent.register(entryUsername.getText(), entryPassword.getText(), entryPasswordRepeat.getText());
			switch(code) {
			case ERR_REGISTRATION_USERNAME_TOO_LONG: 
				entryUsername.error("Too long! Try something else...", 2);
				break;
			case ERR_REGISTRATION_PASSWORDS_NOT_MATCHING:
				entryPasswordRepeat.error("Not matching! Please try again...", 2);
				break;
			case ERR_REGISTRATION_REDUNDANT_USERNAME:
				entryUsername.error("Already taken! Try something else...", 2);
				break;
			default:
				break;
			}
		});
		
		RegisterPage _this = this;
		
		addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				UI.center(_this, contentHolder, true, true);
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

	}

}
