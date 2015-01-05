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
public class Brick extends Rectangle {
    public static final int B_WIDTH = 30;
    private int x;
    private int y;
    private int xa;
    private int ya;
    private int colorNum;
    protected Game1 game;
    
    public Brick(Game1 currGame) {
        x = 50;
        y = 0;
        xa = 1;
        ya = 1;
        colorNum = 0;
        game = currGame;
        
    }
    
    public Brick( Game1 currGame, int xLoc, int yLoc) {
        System.out.println("Brick Overload "+ xLoc + "," +yLoc);
        game = currGame;
        x = xLoc;
        y = yLoc;
        xa = 1;
        ya = 1;
    }
    

    
    public int getBrickX() {
        return this.x;
    }
    
    public int getBrickXA() {
        return this.xa;
    }
    
    public int getBrickY() {
        return this.y;
    }
    
    public int getBrickYA() {
        return this.ya;
    }
    
    public void setBrickX(int newX) {
        this.x = newX;
        
    }
    
    public void setBrickXA(int newXA) {
        this.xa = newXA;
    }
    
    public void setBrickY(int newY) {
        this.y = newY;
    }
    
    public void setBrickYA(int newYA) {
        this.ya = newYA;
    }
    
    public void moveBrick() {
        //x = x + 1;
        //y = y + 1;
        if(x + xa < 0) {
            xa = 1;
        }
        
        if(x + xa > game.getWidth() - B_WIDTH) {
            xa = -1;
        }
        
        if(y + ya < 0) {
            ya =1;
        }
        
        if(y + ya > game.getHeight() - B_WIDTH) {
            //ya = -1;
            game.gameOver();
        }
        
        //check for collision
        if(this.collision()) {
            ya = -1;
            y = game.gameRacket.getTop() - B_WIDTH;
        }
        
        
        x = x + xa;
        y = y + ya;
    }
    
    public boolean collision() {
        return game.gameRacket.getBounds().intersects(this.getBounds());
    }
    
    public void moveBrick(int userInput) {
        this.moveBrick();
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
        return new Rectangle(x, y, B_WIDTH, B_WIDTH);
    }
        
    public void paint(Graphics2D g2d) {
        
        if(this.collision()) {
          this.changeColor(g2d);  
        }
        
        g2d.fillRect(x, y, 30, 30);
    }
}
