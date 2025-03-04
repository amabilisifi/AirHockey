import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerFrame extends JFrame implements ActionListener {
    String myPlayerName;
    String otherPlayerName;
    boolean timeLimitedMode;
    int limitTime;
    boolean goalLimitedMode;
    int limitGoal;
    boolean twoMarginMode;

    TextField textField1 = new TextField();
    JButton submit1 = new JButton("submit");

    TextField textField2 = new TextField();
    JButton submit2 = new JButton("submit");
    JButton startGame = new JButton("START!");
    PlayerFrame(boolean timeLimitedMode,int limitTime,boolean goalLimitedMode,int limitGoal,boolean twoMarginMode){
        this.timeLimitedMode = timeLimitedMode;
        this.limitTime = limitTime;
        this.goalLimitedMode = goalLimitedMode;
        this.limitGoal = limitGoal;
        this.twoMarginMode = twoMarginMode;

        this.setSize(700,500);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());


        submit1.addActionListener(this);

        textField1.setPreferredSize(new Dimension(250,40));
        textField1.setText("player 1");
        textField1.setVisible(true);submit1.addActionListener(this);
        textField1.setBackground(new Color(103, 220, 167));
        textField1.setForeground(Color.BLACK);

        submit2.addActionListener(this);

        textField2.setPreferredSize(new Dimension(250,40));
        textField2.setText("player 2");
        textField2.setVisible(true);
        textField2.setBackground(new Color(126, 181, 236));
        textField2.setForeground(Color.BLACK);

        startGame.addActionListener(this);

        this.add(textField1);
        this.add(submit1);
        this.add(textField2);
        this.add(submit2);
        this.add(startGame);
        this.pack();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submit1){
            myPlayerName = textField1.getText();
            textField1.setEnabled(false);
        }
        if(e.getSource()==submit2){
            otherPlayerName = textField2.getText();
            textField2.setEnabled(false);
        }
        if(e.getSource()==startGame && !textField1.isEnabled() && !textField2.isEnabled()) {
            this.dispose();
            GameFrame gameFrame = new GameFrame(myPlayerName,otherPlayerName,timeLimitedMode,limitTime,goalLimitedMode,limitGoal,twoMarginMode);
        }
    }
}
