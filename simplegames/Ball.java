/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplegames;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author campben5
 */
public class Ball {
    
    private static final int DIAMETER = 30;
    private int x;
    private int y;
    private int xa;
    private int ya;
    private int colorNum;
    private Game1 game;
    
    public Ball(Game1 currGame) {
        x = 0;
        y = 0;
        xa = 1;
        ya = 1;
        colorNum = 0;
        game = currGame;
        
    }
    
    public void moveBall() {
        //x = x + 1;
        //y = y + 1;
        if(x + xa < 0) {
            xa = 1;
        }
        
        if(x + xa > game.getWidth() - DIAMETER) {
            xa = -1;
        }
        
        if(y + ya < 0) {
            ya =1;
        }
        
        if(y + ya > game.getHeight() - DIAMETER) {
            //ya = -1;
            game.gameOver();
        }
        
        //check for collision
        if(this.collision()) {
            ya = -1;
            y = game.gameRacket.getTop() - DIAMETER;
        }
        
        
        x = x + xa;
        y = y + ya;
    }
    
    public boolean collision() {
        return game.gameRacket.getBounds().intersects(this.getBounds());
    }
    
    public void moveBall(int userInput) {
        this.moveBall();
        x = x + userInput;
        
        if(x > game.getWidth() - 30) {
            x = 0;
        }
        
    }
    
    private void changeColor(Graphics2D g2d) {
        System.out.println("colorNum "+colorNum);
        if(colorNum == 0){
            g2d.setColor(Color.BLUE);
            colorNum = 1;
        }
        else {
            g2d.setColor(Color.RED);
            colorNum = 0;
        }
    }
    
   public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }
        
    public void paint(Graphics2D g2d) {
        
        if(this.collision()) {
          this.changeColor(g2d);  
        }
        
        g2d.fillOval(x, y, 30, 30);
    }
}
