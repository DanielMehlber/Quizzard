package ui;

import javax.swing.JPanel;

import com.danielmehlber.myui.MyButton;
import com.danielmehlber.myui.MyDirection;
import com.danielmehlber.myui.MyPage;
import com.danielmehlber.myui.MyTextEntry;
import com.danielmehlber.myui.MyTextEntry.MY_TEXT_ENTRY_MODE;

import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;

public class StartPage extends MyPage{

	private UI ui;
	private MyTextEntry entryUsername;
	private MyTextEntry entryPassword;
	private MyButton btnCreateProfile;
	private MyButton btnLogin;
	
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
		
		JPanel panel = new JPanel();
		sl_contentHolder.putConstraint(SpringLayout.NORTH, panel, yDistance, SpringLayout.NORTH, contentHolder);
		sl_contentHolder.putConstraint(SpringLayout.WEST, panel, 260, SpringLayout.WEST, contentHolder);
		sl_contentHolder.putConstraint(SpringLayout.SOUTH, panel, -287, SpringLayout.SOUTH, contentHolder);
		sl_contentHolder.putConstraint(SpringLayout.EAST, panel, -260, SpringLayout.EAST, contentHolder);
		contentHolder.add(panel);
		
		entryUsername = new MyTextEntry(ui.getDesign());
		sl_contentHolder.putConstraint(SpringLayout.NORTH, entryUsername, yDistance, SpringLayout.SOUTH, panel);
		sl_contentHolder.putConstraint(SpringLayout.WEST, entryUsername, txtDistanceHorizontal, SpringLayout.WEST, contentHolder);
		sl_contentHolder.putConstraint(SpringLayout.EAST, entryUsername, -txtDistanceHorizontal, SpringLayout.EAST, contentHolder);
		contentHolder.add(entryUsername);
		
		entryPassword = new MyTextEntry(ui.getDesign(), MY_TEXT_ENTRY_MODE.PASSWORD);
		sl_contentHolder.putConstraint(SpringLayout.NORTH, entryPassword, yDistance, SpringLayout.SOUTH, entryUsername);
		sl_contentHolder.putConstraint(SpringLayout.WEST, entryPassword, txtDistanceHorizontal, SpringLayout.WEST, contentHolder);
		sl_contentHolder.putConstraint(SpringLayout.EAST, entryPassword, -txtDistanceHorizontal, SpringLayout.EAST, contentHolder);
		contentHolder.add(entryPassword);
		
		btnCreateProfile = new MyButton(ui.getDesign(), "Create Profile");
		sl_contentHolder.putConstraint(SpringLayout.NORTH, btnCreateProfile, yDistance, SpringLayout.SOUTH, entryPassword);
		sl_contentHolder.putConstraint(SpringLayout.WEST, btnCreateProfile, 130, SpringLayout.WEST, contentHolder);
		sl_contentHolder.putConstraint(SpringLayout.SOUTH, btnCreateProfile, 58, SpringLayout.SOUTH, entryPassword);
		sl_contentHolder.putConstraint(SpringLayout.EAST, btnCreateProfile, 259, SpringLayout.WEST, contentHolder);
		contentHolder.add(btnCreateProfile);
		btnCreateProfile.addRunnable(()->{ui.changePage(ui.pageRegister, MyDirection.WEST);});
		
		btnLogin = new MyButton(ui.getDesign(), "Login");
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
		
		entryUsername.setSubtext("Your username belongs in here");
		//entryUsername.setFont(entryUsername.getFont().deriveFont(20f)); //TODO: MyTextEntry.setTextSize
		entryPassword.setSubtext("Your password belongs in here");
		
	}
}
