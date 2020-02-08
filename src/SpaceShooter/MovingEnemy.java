package SpaceShooter;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author e31834
 */
public class MovingEnemy extends Guy{

    private int count = 0;
    Boolean some;
    
    public MovingEnemy() {
        super(false);
        try {
            img = ImageIO.read(new File("C:\\Users\\emuha\\Documents\\NetBeansProjects\\Space Shooter\\src\\SpaceShooter\\mbad.png"));
            img_width = 30;
            img_height = 30;
        } catch (IOException e) {
            System.out.println(e);
        }
        int d = ((int) (6 * Math.random() + 3));
        double num = Math.random();
        if (num < 0.25) {
            dx = -d;
            dy = 10;
            x = width+30;
            y = (int)(Math.random()*(height-91) + 50);
            some = true;
        } else if (num < 0.5 && num >= 0.25) {
            dx = d;
            dy = -10;
            x = -30;
            y = (int)(Math.random()*(height-91) + 50);
            some = true;
        } else if (num < 0.75 && num >= 0.5) {
            dy = -d;
            dx = 10;
            y = height + 30;
            x = (int)(Math.random()*(width-91) + 50);
            some = false;
        } else {
            dy = d;
            dx = -10;
            y = -30;
            x = (int)(Math.random()*(width-91) + 50);
            some = false;
        }
    }
    
    public void move() {
        x += dx;
        y += dy;
        count++;
        if (count == 5) {
            if (some) {
                dy = -dy;
                count = 0;
            } else {
                dx = - dx;
                count = 0;
            }
        }
    }
}
