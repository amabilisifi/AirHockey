package Trash;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class NewGameFrameI extends JFrame {
    public int xOtherPuck = 250;
    public int yOtherPuck = 690;
    Action myPuckUp = new upActionMyPuk();
    Action myPuckRight = new rightActionMyPuk();
    Action myPuckDown = new leftActionMyPuk();
    Action myPuckLeft = new downActionMyPuk();

    JLabel label = new JLabel();
    JLabel otherPuck = new JLabel();

    NewGameFrameI() {
        this.setSize(600, 800);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("AirHockey+");

        label.getInputMap().put(KeyStroke.getKeyStroke('w'), "MyUP");
        label.getActionMap().put("MyUP", myPuckUp);
        label.getInputMap().put(KeyStroke.getKeyStroke('d'), "MyRight");
        label.getActionMap().put("MyRight", myPuckRight);
        label.getInputMap().put(KeyStroke.getKeyStroke('s'), "MyDown");
        label.getActionMap().put("MyDown", myPuckLeft);
        label.getInputMap().put(KeyStroke.getKeyStroke('a'), "MyLeft");
        label.getActionMap().put("MyLeft", myPuckDown);

        otherPuck.setBounds(xOtherPuck,yOtherPuck,80,80);
        otherPuck.setBackground(Color.CYAN);

        //this.add(otherPuck);
        this.add(label);
    }

    public void paint(Graphics g){
        Graphics2D g2D = (Graphics2D) g;

        g2D.fillOval(xOtherPuck,yOtherPuck,80,80);
    }

    public class upActionMyPuk extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            yOtherPuck -= 4;
            repaint();
        }
    }

    public class rightActionMyPuk extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            xOtherPuck += 4;
            repaint();
        }
    }

    public class downActionMyPuk extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            yOtherPuck += 4;
            repaint();
        }
    }

    public class leftActionMyPuk extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            xOtherPuck -= 4;
            repaint();
        }
    }
}
