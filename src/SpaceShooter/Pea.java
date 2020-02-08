package SpaceShooter;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author e31834
 */
public class Pea extends Guy{
    public Pea(int x, int y, int dx, int dy) {
        super(null);
        try {
            img = ImageIO.read(new File("C:\\Users\\emuha\\Documents\\NetBeansProjects\\Space Shooter\\src\\SpaceShooter\\pea.png"));
            img_width = 18;
            img_height = 18;
        } catch (IOException e) {
            System.out.println(e);
        }
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }
}
