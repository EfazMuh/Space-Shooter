package SpaceShooter;

import java.awt.*;
import java.util.*;

/**
 *
 * @author e31834
 */
public class Game{
    private boolean playing = false;
    private int width, height;
    protected Player player;
    private ArrayList<Guy> list = new ArrayList<Guy>();
    private ArrayList<Guy> weaps = new ArrayList<Guy>();
    private int score;
    private int disTime;
    private double enemyProb;
    private int wp;
    private int ammo;
    private boolean mu;
    private boolean sh;

    public Game( int w, int h ){
        width = w;
        height = h;
        player = new Player( width/2, height/2, width, height );
    }
    
    public void init(){
        wp = 0;
        score = 0;
        ammo = 5;
        mu = false;
        sh = false;
        enemyProb = 0.3;
        disTime = 200;
        player = new Player( width/2, height/2, width, height );
        list.clear();
        weaps.clear();
    }

    public void updatePlayerMotion( int dx, int dy  ){
        player.setSpeed( dx, dy );
    }
    
    public void throwPea(int x, int y, int dx, int dy) {
        Guy gu = new Guy(null);
        if (ammo >= 3 && mu) {
            gu = new Pea(x, y, dx, dy);
            weaps.add(gu);
            if (dx != 0) {
                gu = new Pea(x, y, dx, dx);
                weaps.add(gu);
                gu = new Pea(x, y, dx, -dx);
                weaps.add(gu);
            } else if (dy != 0) {
                gu = new Pea(x, y, dy, dy);
                weaps.add(gu);
                gu = new Pea(x, y, -dy, dy);
                weaps.add(gu);
            }
            ammo -= 3;
            wp += 3;
            mu = false;
        } else if (ammo > 0) {
            gu = new Pea(x, y, dx, dy);
            weaps.add(gu);
            ammo--;
            wp++;
        }
    }

    public void update(){
        player.move();
        Guy gu;
        if (Math.random() < 0.5) {
            if (Math.random() < 0.2) {
                gu = new Resource();
                list.add(gu);
            } else if (Math.random() < enemyProb){
                gu = new BasicEnemy();
                if (Math.random() < 0.3 + enemyProb/3) {
                    gu = new MovingEnemy();
                }
                list.add(gu);
            }
        }
        for (Guy g:list) {
            if (g instanceof Resource) {
                //((Resource) g).move(player.getX(), player.getY());
                ((Resource) g).inc();
            } else {
                g.move();
            }
        }
        if (wp > 0) {
            for (int i = 0; i < weaps.size(); i++) {
                Guy g = weaps.get(i);
                g.move();
                if (g.getX() < -18 || g.getY() < -18 || g.getX() > width || g.getY() > height) {
                    weaps.remove(g);
                    wp--;
                }
            }
        }
      
        Rectangle player_r = player.getShape();
        for ( int k = list.size() - 1; k >= 0; k-- ){
            Guy guy = list.get( k );
            Rectangle guy_r = guy.getShape();
            if ( player_r.intersects( guy_r ) ){
                if (guy.bool) {
                    if (((Resource)guy).isMu()) {
                        mu = true;
                        ammo += 2;
                    } else if (((Resource)guy).isSh()) {
                        sh = true;
                        player.shield(sh);
                    } 
                    ammo++;
                    score++;
                } else {
                    if (sh) {
                        sh = false;
                        player.shield(sh);
                    } else {
                        score--;
                        player.hurt();
                    }
                }
                list.remove(guy);
            }
        }
        for (Guy w:weaps) {
            Rectangle w_r = w.getShape();
            for ( int k = list.size() - 1; k >= 0; k-- ){
                Guy guy = list.get( k );
                Rectangle guy_r = guy.getShape();
                if ( w_r.intersects( guy_r ) ){
                    if (!guy.bool) {
                        list.remove(guy);
                    }
                }
            }
        }
        for (int i = 0; i < list.size(); i++) {
            Guy g = list.get(i);
            if (g instanceof Resource) {
                if (((Resource) g).getTime() > disTime) {
                    list.remove(i);
                }
            }else if (g.getX() < -30 || g.getY() < -30 || g.getX() > width + 30 || g.getY() > height + 30) {
                list.remove(i);
                i--;
            }
        }
    }
    
    public boolean keepPlaying(){
        return player.isAlive();
    }
    
    public int getScore(){
        return score;
    }
    
    public int getAmmo(){
        return ammo;
    }
    
    public boolean getMu(){
        return mu;
    }
    
    public boolean getSh(){
        return sh;
    }
    
    public void draw( Graphics g ){
        player.draw( g );
        for ( Guy guy : list )
            guy.draw( g );
        for ( Guy w : weaps)
            w.draw( g );
    }
    
    public void decTime() {
        if (disTime > 100) {
            disTime -= 10;
        }
    }
    
    public void incProb() {
        if (enemyProb < 1) {
            enemyProb += 0.05;
        }
    }
}