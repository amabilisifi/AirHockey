import Trash.NewGameFrame;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class StartMenu extends JFrame implements ActionListener {
    JButton newGameButton = new JButton();
    JButton gameHistoryButton = new JButton();
    JButton exitButton = new JButton();
    JPanel panel = new JPanel();
    StartMenu(){

        panel.setSize(600,800);
        panel.setBackground(new Color(0, 0, 0));
        panel.setOpaque(true);
        panel.setVisible(true);

        newGameButton.setBounds(150,150,150,80);
        newGameButton.setText("new game!");
        newGameButton.setForeground(Color.black);
        newGameButton.setVisible(true);
        newGameButton.addActionListener(this);
        newGameButton.setOpaque(true);

        gameHistoryButton.setBounds(150,250,150,80);
        gameHistoryButton.setText("game history");
        gameHistoryButton.setForeground(Color.black);
        gameHistoryButton.setVisible(true);
        gameHistoryButton.addActionListener(this);
        gameHistoryButton.setOpaque(true);

        exitButton.setBounds(150,350,150,80);
        exitButton.setSize(150,80);
        exitButton.setText("EXIT");
        exitButton.setForeground(Color.black);
        exitButton.setVisible(true);
        exitButton.addActionListener(this);
        exitButton.setOpaque(true);

        this.setVisible(true);
        this.setSize(new Dimension(450,650));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(newGameButton);
        this.add(gameHistoryButton);
        this.add(exitButton);
        this.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==newGameButton){
            this.dispose();
            GameModeFrame gameModeFrame = new GameModeFrame();
        }if (e.getSource()==gameHistoryButton){
            this.dispose();
            GameHistoryFrame gameHistoryFrame = new GameHistoryFrame();
        }if (e.getSource()==exitButton){
            System.exit(0);
        }
    }
}