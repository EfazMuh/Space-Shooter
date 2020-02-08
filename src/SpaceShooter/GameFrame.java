package SpaceShooter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author e31834
 */
public class GameFrame extends JFrame {
    private final int WIDTH = 700;
    private final int BTN_LABEL_HEIGHT = 30;
    private final int GAME_HEIGHT = 500;
    private final int PANEL_HEIGHT = GAME_HEIGHT + 2*BTN_LABEL_HEIGHT;

    private Game game = new Game( WIDTH, GAME_HEIGHT );
    private JButton btn = new JButton( "Start" );
    private JLabel lbl = new JLabel("  Score: 0  Ammo: 0                                                       Arrows: Move  WASD: Shoot                                               Multi: Off  Shield: Off");
    private JLabel cont = new JLabel("Arrows: Move WASD: Shoot");
    private GamePanel gp = new GamePanel( game );
    private javax.swing.Timer timer;
    private int change = 0;

    public GameFrame(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle( "Space Shooter" );
        setResizable( false );
        JPanel jp = new JPanel();
        jp.setBackground( new Color(145, 70, 190) );
        jp.setPreferredSize( new Dimension( WIDTH, PANEL_HEIGHT ) );
        jp.setLayout( null );
        timer = new Timer(100, new Clock());
        btn.setBounds(0, 0, WIDTH, BTN_LABEL_HEIGHT );
        btn.addActionListener(new ButtonListener());
        lbl.setBounds(0, GAME_HEIGHT + BTN_LABEL_HEIGHT, WIDTH, BTN_LABEL_HEIGHT);
        gp.setBounds( 0, BTN_LABEL_HEIGHT, WIDTH, GAME_HEIGHT );
        jp.add( gp );
        jp.add(btn);
        jp.add(lbl);
        getContentPane().add( jp );
        pack(); 

    }

    public void display() {
        EventQueue.invokeLater(new Runnable() {
                public void run() {
                    setVisible(true);
                }
            });
    }

    private class Clock implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            game.update();
            gp.repaint();
            int score = game.getScore();
            int ammo = game.getAmmo();
            String multi;
            if (game.getMu()) {
                multi = "On";
            } else {
                multi = "Off";
            }
            String shield;
            if (game.getSh()) {
                shield= "On";
            } else {
                shield = "Off";
            }
            change++;
            if (change == 100) {
                game.decTime();
                game.incProb();
                change = 0;
            }
            if (!game.keepPlaying()) {
                timer.stop();
                lbl.setText("GAME OVER. YOUR FINAL SCORE IS " + score);
            } else {
                lbl.setText("  Score: " + score + "  Ammo: " + ammo + "                                                       Arrows: Move  WASD: Shoot                                               Multi: " + multi + "  Shield: " + shield);
            }
        }
    }

    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if (btn.getText().equals("Start")) {
                timer.start();
                btn.setText("Reset");
                game.init();
            } else {
                timer.stop();
                btn.setText("Start");
                game.init();
                lbl.setText("  Score: 0  Ammo: 0                                                       Arrows: Move  WASD: Shoot                                               Multi: Off  Shield: Off");
                gp.repaint();
            }
            gp.requestFocusInWindow();
        }
    }
}