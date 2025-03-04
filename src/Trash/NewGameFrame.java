package Trash;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class NewGameFrame extends JFrame implements ActionListener,KeyListener {
    // Attributes
    public boolean isTimeLimited;
    public boolean isGoalLimited;
    public boolean twoMarginLimit;
    public int width = 600;
    public int height = 800;
    public int myPuckX = 250;
    public int myPuckY = 670;
    JButton backButton = new JButton();
    //JLabel myPuck = new JLabel();
    JPanel panel = new JPanel();

    NewGameFrame(boolean isTimeLimited, boolean isGoalLimited, boolean twoMarginLimit) {
        if(true) {
            System.out.println("time " + isTimeLimited);
            System.out.println("goal " + isGoalLimited);
            System.out.println("margin " + twoMarginLimit);

            this.setSize(width, height);
            this.setVisible(true);
            this.setResizable(false);

            backButton.setText("back");
            backButton.setSize(80, 40);
            backButton.setVisible(true);
            backButton.setOpaque(true);
            backButton.addActionListener(this);

            panel.setSize(600, 800);
            panel.setBackground(new Color(33, 4, 61));
            panel.setVisible(true);
            panel.setLayout(null);
            panel.setOpaque(true);
            panel.addKeyListener(this);
        }
        //myPuck.setBounds(250,670,70,70);
        //myPuck.setBackground(new Color(103, 220, 167));
        //myPuck.setOpaque(true);


        panel.add(backButton);
        //panel.add(myPuck);
        this.add(panel);
    }

    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        g2D.setColor(new Color(103, 220, 167));
        g2D.fillOval(myPuckX, myPuckY, 80, 80);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            this.dispose();
            //StartMenu newStartMenu = new StartMenu();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==37){
            myPuckX -= 4;
            repaint();
        }if(e.getKeyCode()==38){
            myPuckY -= 4;
            repaint();
        }if(e.getKeyCode()==39){
            myPuckX += 4;
            repaint();
        }if(e.getKeyCode()==40){
            myPuckY += 4;
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
