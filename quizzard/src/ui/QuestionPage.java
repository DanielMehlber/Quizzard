package ui;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.danielmehlber.myui.MyButton;
import com.danielmehlber.myui.MyLabel;
import com.danielmehlber.myui.MyPage;

import main.QuestionData;

public class QuestionPage extends MyPage{

	private final UI ui;
	MyLabel lblGame;
	MyLabel lblRound;
	MyLabel lblTopic;
	MyLabel lblQuestion;
	
	public QuestionPage(UI ui) {
		super(ui.getDesign());
		this.ui = ui;
		
		MyButton ans_1 = new MyButton(getDesign(), "---");
		ans_1.setBounds(48, 238, 250, 35);
		JButton button = new JButton("New button");
		button.setBounds(324, 238, 250, 35);
		setLayout(null);
		add(ans_1);
		add(button);
		
		MyButton ans_3 = new MyButton(getDesign(), "---");
		ans_3.setBounds(48, 304, 250, 35);
		add(ans_3);
		
		MyButton ans_4 = new MyButton(getDesign(), "---");
		ans_4.setBounds(324, 304, 250, 35);
		add(ans_4);
		
		lblGame = new MyLabel(getDesign(), "GAME");
		lblGame.setBounds(48, 26, 294, 35);
		add(lblGame);
		
		lblRound = new MyLabel(getDesign(), "ROUND");
		lblRound.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRound.setBounds(352, 26, 222, 35);
		add(lblRound);
		
		lblTopic = new MyLabel(getDesign(), "TOPIC");
		lblTopic.setBounds(48, 61, 206, 35);
		add(lblTopic);
		
		lblQuestion = new MyLabel(getDesign(), "QUESTION");
		lblQuestion.setVerticalAlignment(SwingConstants.BOTTOM);
		lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestion.setBounds(48, 126, 526, 81);
		add(lblQuestion);
		
		MyButton btnHome = new MyButton(getDesign(), "Home");
		btnHome.setBounds(268, 432, 89, 23);
		add(btnHome);
		
	}
	
	public void setQuestion(QuestionData data) {
		
	}
}
