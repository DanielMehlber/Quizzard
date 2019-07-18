package ui;

import com.danielmehlber.myui.MyButton;
import com.danielmehlber.myui.MyDirection;
import com.danielmehlber.myui.MyPage;
import com.danielmehlber.myui.MyTextEntry;
import com.danielmehlber.myui.MyTextEntry.MY_TEXT_ENTRY_MODE;

import main.Console;
import main.ErrCode;

import javax.swing.SpringLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Page used for login
 * @author mehlber
 *
 */
public class StartPage extends MyPage{

	private UI ui;
	private MyTextEntry entryUsername;
	private MyTextEntry entryPassword;
	private MyButton btnCreateProfile;
	private MyButton btnLogin;
	private Runnable login;
	
	public StartPage(UI ui) {
		super(ui.getDesign());
		this.ui = ui;
		setLayout(null);
		
		MyPage contentHolder = new MyPage(ui.getDesign());
		contentHolder.setBounds(262, 219, 666, 468);
		add(contentHolder);
		SpringLayout sl_contentHolder = new SpringLayout();
		contentHolder.setLayout(sl_contentHolder);
		
		int txtDistanceHorizontal = 130;
		int yDistance = 30;
		
		
		Logo picture = new Logo();
		sl_contentHolder.putConstraint(SpringLayout.NORTH, picture, yDistance, SpringLayout.NORTH, contentHolder);
		sl_contentHolder.putConstraint(SpringLayout.WEST, picture, 260, SpringLayout.WEST, contentHolder);
		sl_contentHolder.putConstraint(SpringLayout.SOUTH, picture, -287, SpringLayout.SOUTH, contentHolder);
		sl_contentHolder.putConstraint(SpringLayout.EAST, picture, -260, SpringLayout.EAST, contentHolder);
		contentHolder.add(picture);
		
		entryUsername = new MyTextEntry(ui.getDesign());
		sl_contentHolder.putConstraint(SpringLayout.NORTH, entryUsername, yDistance, SpringLayout.SOUTH, picture);
		sl_contentHolder.putConstraint(SpringLayout.WEST, entryUsername, txtDistanceHorizontal, SpringLayout.WEST, contentHolder);
		sl_contentHolder.putConstraint(SpringLayout.EAST, entryUsername, -txtDistanceHorizontal, SpringLayout.EAST, contentHolder);
		contentHolder.add(entryUsername);
		
		entryPassword = new MyTextEntry(ui.getDesign(), MY_TEXT_ENTRY_MODE.PASSWORD);
		sl_contentHolder.putConstraint(SpringLayout.NORTH, entryPassword, yDistance, SpringLayout.SOUTH, entryUsername);
		sl_contentHolder.putConstraint(SpringLayout.WEST, entryPassword, txtDistanceHorizontal, SpringLayout.WEST, contentHolder);
		sl_contentHolder.putConstraint(SpringLayout.EAST, entryPassword, -txtDistanceHorizontal, SpringLayout.EAST, contentHolder);
		contentHolder.add(entryPassword);
		
		btnCreateProfile = new MyButton(ui.getDesign(), "Ich bin neu");
		sl_contentHolder.putConstraint(SpringLayout.NORTH, btnCreateProfile, yDistance, SpringLayout.SOUTH, entryPassword);
		sl_contentHolder.putConstraint(SpringLayout.WEST, btnCreateProfile, 130, SpringLayout.WEST, contentHolder);
		sl_contentHolder.putConstraint(SpringLayout.SOUTH, btnCreateProfile, 58, SpringLayout.SOUTH, entryPassword);
		sl_contentHolder.putConstraint(SpringLayout.EAST, btnCreateProfile, 259, SpringLayout.WEST, contentHolder);
		contentHolder.add(btnCreateProfile);
		btnCreateProfile.addRunnable(()->{ui.changePage(ui.pageRegister, MyDirection.WEST, UI.pageChangeSpeedFac);});
		
		btnLogin = new MyButton(ui.getDesign(), "Eintreten");
		sl_contentHolder.putConstraint(SpringLayout.NORTH, btnLogin, yDistance, SpringLayout.SOUTH, entryPassword);
		sl_contentHolder.putConstraint(SpringLayout.WEST, btnLogin, -253, SpringLayout.EAST, contentHolder);
		sl_contentHolder.putConstraint(SpringLayout.SOUTH, btnLogin, 58, SpringLayout.SOUTH, entryPassword);
		sl_contentHolder.putConstraint(SpringLayout.EAST, btnLogin, -130, SpringLayout.EAST, contentHolder);
		contentHolder.add(btnLogin);
		StartPage _this = this;
		addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				//UI.center(_this, contentHolder, true, true);
				
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
		
		entryUsername.setSubtext("Verrate mir deinen Namen...");
		entryPassword.setSubtext("... und das Passwort, dann darfst du rein.");
		
		login = () -> {
			ErrCode code = ui.mainComponent.login(entryUsername.getText(), ui.mainComponent.getHash(entryPassword.getText()));
			switch(code) {
			case ERR_LOGIN_UNKNOWN_USER: 
				entryUsername.error("Ich kenne dich nicht. Willst du beitreten?", 3f);
				break;
			case ERR_LOGIN_INCORRECT_PASSWORD:
				entryPassword.error("Nur mut dem richtigen Passwort kommst du rein!", 3f);
				break;
			case NULL:
				break;
			default:
				Console.error("login", "UNKNOWN ERRCODE", false);
				break;
			}
			
		};
		
		btnLogin.addRunnable(login);
		entryUsername.addRunnable(login);
		entryPassword.addRunnable(login);
		
		System.out.println();
		
	}
}
