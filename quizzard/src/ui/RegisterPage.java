package ui;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.danielmehlber.myui.MyButton;
import com.danielmehlber.myui.MyDirection;
import com.danielmehlber.myui.MyLabel;
import com.danielmehlber.myui.MyPage;
import com.danielmehlber.myui.MyTextEntry;
import com.danielmehlber.myui.MyTextEntry.MY_TEXT_ENTRY_MODE;

import main.ErrCode;

/**
 * Page used for registration
 * @author mehlber
 *
 */

public class RegisterPage extends MyPage { 

	
	private UI ui;
	private MyPage contentHolder;
	
	
	public RegisterPage(UI ui) {
		super(ui.getDesign());
		this.ui = ui;
		setLayout(null);
		contentHolder = new MyPage(ui.getDesign());
		contentHolder.setBounds(320, 99, 568, 500);
		add(contentHolder);
		contentHolder.setLayout(null);
		
		JLabel label = new MyLabel(ui.getDesign());
		label.setText("Zuerst die Formalit‰ten...");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(150, 13, 260, 41);
		contentHolder.add(label);
		
		MyTextEntry entryUsername = new MyTextEntry(ui.getDesign());
		entryUsername.setBounds(58, 100, 453, 55);
		contentHolder.add(entryUsername);
		entryUsername.setSubtext("Verrate mir deinen Namen");
		
		MyTextEntry entryPassword = new MyTextEntry(ui.getDesign(), MY_TEXT_ENTRY_MODE.PASSWORD);
		entryPassword.setBounds(58, 177, 453, 55);
		contentHolder.add(entryPassword);
		entryPassword.setSubtext("W‰hle ein Passwort");
		
		MyTextEntry entryPasswordRepeat = new MyTextEntry(ui.getDesign(), MY_TEXT_ENTRY_MODE.PASSWORD);
		entryPasswordRepeat.setBounds(58, 255, 453, 55);
		contentHolder.add(entryPasswordRepeat);
		entryPasswordRepeat.setSubtext("Wiederhole es, nur zur Sicherheit. Doppelt h‰lt besser");
		
		MyButton btnBack = new MyButton(ui.getDesign(), "Zur¸ck");
		btnBack.setBounds(58, 330, 156, 41);
		contentHolder.add(btnBack);
		btnBack.addRunnable(()->{ui.changePage(ui.pageStart, MyDirection.EAST, UI.pageChangeSpeedFac);});
		
		MyButton btnRegister = new MyButton(ui.getDesign(), "Los");
		btnRegister.setBounds(355, 330, 156, 41);
		contentHolder.add(btnRegister);
		btnRegister.addRunnable(() -> {
			String _t = btnRegister.getText();
			new Thread(() -> btnRegister.setText("Eine Sekunde...")).start();;
			btnRegister.setEnabled(false);
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ErrCode code = ui.mainComponent.register(entryUsername.getText(), entryPassword.getText(), entryPasswordRepeat.getText());
			switch(code) {
			case ERR_REGISTRATION_USERNAME_TOO_LONG: 
				entryUsername.error("Zu lang! Das kann sich doch kein Mensch merken!", 3);
				break;
			case ERR_REGISTRATION_PASSWORDS_NOT_MATCHING:
				entryPasswordRepeat.error("Vertippt?", 3);
				break;
			case ERR_REGISTRATION_REDUNDANT_USERNAME:
				entryUsername.error("Ich kenne jemanden, der heiﬂt zum Verwechseln ‰hnlich", 3);
				break;
			case ERR_REGISTRATION_USERNAME_EMPTY:
				entryUsername.error("Hast du nicht was vergessen?", 3);
				break;
			case ERR_REGISTRATION_PASSWORD_EMPTY:
				entryPassword.error("Hast du nicht was vergessen?", 3);
			default:
				break;
			}
			btnRegister.setEnabled(true);
			btnRegister.setText(_t);
			
			
		});
		
		RegisterPage _this = this;
		
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

	}

}
