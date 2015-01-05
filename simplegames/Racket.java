/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplegames;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 *
 * @author campben5
 */
public class Racket {
    
    private static final int Y = 330;
    private static final int WIDTH = 60;
    private static final int HEIGHT = 10;
    private int x;
    private int xa;
    private Game1 game;
    
    public Racket(Game1 currGame) {
        this.game = currGame;
        this.x = 0;
        this.xa = 0; 
    }
    
    private void changeColor(Graphics2D g2d) {
            g2d.setColor(Color.black);
    }
   
    public void move() {
        if(x + xa > game.getWidth() - 30) {
            x = 0;
        }
        else if(x + xa < 0) {
            x = game.getWidth() - 30;
        }
        else {
            x = x + xa;
        }
        
    }
    
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            xa = -1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            xa = 1;
        }
        else {
            xa = 0;
        }
        
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, Y, WIDTH, HEIGHT);
    } 
    
    public int getTop() {
        return Y;
    }
    
    public void keyReleased(KeyEvent e) {
        xa = 0;
    }
    
    public void paint(Graphics2D g2d) {
        this.changeColor(g2d);
        g2d.fillRect(x, Y, WIDTH, HEIGHT);
    }
    
}
