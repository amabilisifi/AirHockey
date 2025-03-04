import javax.swing.*;

public class WinnerFrame extends JFrame {
    WinnerFrame(String winner) {
        int answer = JOptionPane.showConfirmDialog(null,"Winner is "+winner+" .\nDo you want to go to start Menu ?","AirHockey+",JOptionPane.YES_NO_OPTION);
        // 0 yes  1 no
        if(answer == 0){
            this.dispose();
            StartMenu startMenu = new StartMenu();
        }if(answer == 1){
            this.dispose();
        }
    }
}
