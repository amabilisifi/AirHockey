import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame implements KeyListener {
    GameFrame(String myPlayer,String otherPlayer,boolean timeLimitedMode,int limitTime,boolean goalLimitedMode,int limitGoal,boolean twoMarginMode){
        GamePanel gamePanel = new GamePanel(myPlayer,otherPlayer,timeLimitedMode,limitTime,goalLimitedMode,limitGoal,twoMarginMode);
        this.add(gamePanel);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
        this.setTitle("AirHockey+");
        this.addKeyListener(gamePanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar()=='b'){
            this.dispose();
            StartMenu startMenu = new StartMenu();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
