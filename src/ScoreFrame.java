import javax.swing.*;
import java.awt.*;

public class ScoreFrame extends JFrame {
    public int myScore;
    public int otherScore;
    ScoreFrame(int myScore,int otherScore){
        this.myScore = myScore;
        this.otherScore = otherScore;
        JPanel scorePanel = new JPanel();
        scorePanel.setSize(400,200);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("SCORE");
        this.add(scorePanel);
        this.setSize(400,200);
        this.setResizable(false);
        this.setVisible(true);
    }
    public void paint(Graphics g){
        Graphics2D g2D = (Graphics2D) g;

        g2D.setPaint(new Color(10, 3, 40));
        g2D.fillRect(0,0,400,200);
        g2D.setPaint(new Color(126, 181, 236));
        g2D.setFont(new Font("Ink Free",Font.BOLD,70));
        g2D.drawString("      "+otherScore,110,135);
        g2D.setPaint(new Color(239, 198, 109));
        g2D.setFont(new Font("Ink Free",Font.BOLD,70));
        g2D.drawString("   -   ",110,135);
        g2D.setPaint(new Color(103, 220, 167));
        g2D.setFont(new Font("Ink Free",Font.BOLD,70));
        g2D.drawString(myScore+"   ",110,135);
    }

    public void updateBoard(int myScore,int otherScore){
        repaint();
    }
}
