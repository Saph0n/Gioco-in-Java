package main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {

    public static JFrame window;

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        window=new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Don Keedic ADVENTURE");
        new Main().setIcon();
        GamePanel gamePanel=new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();

    }
    public void setIcon(){

        ImageIcon icon=new ImageIcon(getClass().getClassLoader().getResource("player/Capybara.png"));
        window.setIconImage(icon.getImage());

    }

}

