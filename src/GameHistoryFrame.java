import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameHistoryFrame extends JFrame implements ActionListener {
    public static ArrayList<String> data = new ArrayList<>();
    JButton backButton = new JButton();
    GameHistoryFrame(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        this.add(panel);

        JPanel GameHistoryPanel = new JPanel();
        GameHistoryPanel.setLayout(new BoxLayout(GameHistoryPanel,BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(GameHistoryPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        panel.add(scrollPane);

        backButton.setText("back");
        backButton.setSize(80,40);
        backButton.setVisible(true);
        backButton.setOpaque(true);
        backButton.addActionListener(this);
        GameHistoryPanel.add(backButton);
        int n = data.size();

        for (int i = 0; i < n; i++) {
            JButton temp = new JButton(data.get(i));
            temp.setFont(new Font("Ink Free",Font.ITALIC,34));
            temp.setBounds(0,220*i,400,200);
            GameHistoryPanel.add(temp);
        }
        //this.setLayout(new GridLayout(n+1,1));
        this.setSize(800,600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==backButton){
            this.dispose();
            StartMenu newStartMenu = new StartMenu();
        }
    }
    public static void addData(String input){
        data.add(input);
    }
}
