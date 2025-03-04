import Trash.NewGameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameModeFrame extends JFrame implements ActionListener {
    // Attributes
    public boolean timeLimitMode;
    public int LimitTime;
    public boolean goalLimitMode;
    public int LimitGoal;
    public boolean twoMarginMode;
    JCheckBox timeLimit = new JCheckBox();
    JCheckBox goalLimit = new JCheckBox();
    JCheckBox twoMargin = new JCheckBox();
    JButton done = new JButton();

    GameModeFrame() {

        timeLimit.setSize(200, 100);
        timeLimit.setBounds(100, 0, 200, 100);
        timeLimit.setText("Time Limit");

        goalLimit.setSize(200, 100);
        goalLimit.setBounds(100, 200, 200, 100);
        goalLimit.setText("Goal Limit");

        twoMargin.setSize(200, 100);
        twoMargin.setBounds(100, 400, 200, 100);
        twoMargin.setText("Two Margin Limit");

        done.setBounds(500, 450, 200, 100);
        done.setText("DONE!");
        done.addActionListener(this);

        this.setSize(800, 600);
        this.setLayout(new FlowLayout());
        this.setResizable(false);
        this.setVisible(true);
        this.add(timeLimit);
        this.add(goalLimit);
        this.add(twoMargin);
        this.add(done);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean selected = false;
        if (timeLimit.isSelected()) {
            selected  = true;
            this.timeLimitMode = true;
            this.LimitTime=Integer.parseInt(JOptionPane.showInputDialog("Please enter your limited time in seconds"));
        }
        if (goalLimit.isSelected()) {
            selected = true;
            this.goalLimitMode = true;
            this.LimitGoal=Integer.parseInt(JOptionPane.showInputDialog("Please enter your limited goal"));
        }
        if (twoMargin.isSelected()) {
            selected = true;
            this.twoMarginMode = true;
        }
        if(twoMargin.isSelected() && !goalLimit.isSelected()){
            JOptionPane.showMessageDialog(null,"you can't choose two margin limit w/o goal limit!");
            selected = false;
        }
        if(!selected){
            JOptionPane.showMessageDialog(null,"you have to select at least one!");
        }else {
            this.dispose();
            PlayerFrame gameFrame = new PlayerFrame(timeLimitMode, LimitTime, goalLimitMode, LimitGoal, twoMarginMode);
        }
    }

    public boolean isTimeLimitMode() {
        return timeLimitMode;
    }

    public void setTimeLimitMode(boolean timeLimitMode) {
        this.timeLimitMode = timeLimitMode;
    }

    public boolean isGoalLimitMode() {
        return goalLimitMode;
    }

    public void setGoalLimitMode(boolean goalLimitMode) {
        this.goalLimitMode = goalLimitMode;
    }

    public boolean isTwoMarginMode() {
        return twoMarginMode;
    }

    public void setTwoMarginMode(boolean twoMarginMode) {
        this.twoMarginMode = twoMarginMode;
    }
}