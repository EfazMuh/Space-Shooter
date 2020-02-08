package SpaceShooter;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author e31834
 */
public class BasicEnemy extends Guy{

    public BasicEnemy() {
        super(false);
        try {
            img = ImageIO.read(new File("C:\\Users\\emuha\\Documents\\NetBeansProjects\\Space Shooter\\src\\SpaceShooter\\bad.png"));
            img_width = 30;
            img_height = 30;
        } catch (IOException e) {
            System.out.println(e);
        }
        int d = ((int) (6 * Math.random() + 3));
        double num = Math.random();
        if (num < 0.25) {
            dx = -d;
            x = width+30;
            y = (int)(Math.random()*(height-29));
        } else if (num < 0.5 && num >= 0.25) {
            dx = d;
            x = -30;
            y = (int)(Math.random()*(height-29));
        } else if (num < 0.75 && num >= 0.5) {
            dy = -d;
            y = height + 30;
            x = (int)(Math.random()*(width-29));
        } else {
            dy = d;
            y = -30;
            x = (int)(Math.random()*(width-29));
        }
    }
}
