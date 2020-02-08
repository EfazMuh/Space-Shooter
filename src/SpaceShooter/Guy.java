package SpaceShooter;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author e31834
 */
public class Guy {

    protected int x;   // top left
    protected int y;
    protected int width = 700;
    protected int height = 500;
    protected int dx, dy;  // speed
    protected BufferedImage img;
    protected int img_width, img_height;
    protected Boolean bool;

    public Guy(Boolean bool) {
        this.bool = bool;
    }
    
    public void move() {
        x += dx;
        y += dy;
    }

    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }

    public void draw(Graphics g) {
        g.drawImage(img, x, y, null);
    }

    public Rectangle getShape() {
        return new Rectangle(x, y, img_width, img_height);
    }
}
