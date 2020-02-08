package SpaceShooter;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author e31834
 */
public class Resource extends Guy{
    
    private int time = 0;
    private boolean mu = false;
    private boolean sh = false;

    public Resource() {
        super(true);
        x = (int)(Math.random()*width-19);
        y = (int)(Math.random()*width-19);
        if (Math.random() < 0.2) {
            try {
                img = ImageIO.read(new File("C:\\Users\\emuha\\Documents\\NetBeansProjects\\Space Shooter\\src\\SpaceShooter\\multi.png"));
                img_width = 18;
                img_height = 18;
            } catch (IOException e) {
                System.out.println(e);
            }
            mu = true;
        } else if (Math.random() < 0.25){
            try {
                img = ImageIO.read(new File("C:\\Users\\emuha\\Documents\\NetBeansProjects\\Space Shooter\\src\\SpaceShooter\\shield.png"));
                img_width = 18;
                img_height = 18;
            } catch (IOException e) {
                System.out.println(e);
            }
            sh = true;
        } else {
            try {
                img = ImageIO.read(new File("C:\\Users\\emuha\\Documents\\NetBeansProjects\\Space Shooter\\src\\SpaceShooter\\good.png"));
                img_width = 18;
                img_height = 18;
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
    
    public void inc() {
        time++;
    }
    
    public int getTime() {
        return time;
    }
    
    public boolean isMu() {
        return mu;
    }
    
    public boolean isSh() {
        return sh;
    }
    
    /*public void move(int x, int y) {
        if (x < this.x && this.x-x> dx) {
            this.x += dx;
        } else if (x < this.x) {
            this.x += this.x-x;
        } else if (x > this.x && x-this.x> dx) {
            this.x -= dx;
        } else {
            this.x -= x-this.x;
        }
        if (y < this.y && this.y-y> dy) {
            this.y += dy;
        } else if (y < this.y) {
            this.y += this.y-y;
        } else if (y > this.y && y-this.y> dy) {
            this.y -= dy;
        } else {
            this.y -= y-this.y;
        }
    }*/
}
