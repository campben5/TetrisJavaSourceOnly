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
public class TBrick extends Rectangle {
    public static final int B_WIDTH = 30;
    private int x;
    private int y;
    protected Game1 game;
    
    public TBrick( Game1 currGame, int xLoc, int yLoc) {
        System.out.println("Make a Brick "+ xLoc + "," +yLoc);
        game = currGame;
        x = xLoc;
        y = yLoc;;
    }
    
    public static void brickCloneLocation(TBrick toBeCloned, TBrick cloned) {
        cloned.x = toBeCloned.x;
        cloned.y = toBeCloned.y;
    }
    

    public int getBrickX() {
        return this.x;
    }
    
    public int getBrickY() {
        return this.y;
    }
    
    public void setBrickX(int newX) {
        this.x = newX;
        
    }
    
    public void setBrickY(int newY) {
        this.y = newY;
    }
       
    public boolean collision() {
        //return game.gameRacket.getBounds().intersects(this.getBounds());
        return false;
    }
    
   public Rectangle getBounds() {
        return new Rectangle(x, y, B_WIDTH, B_WIDTH);
    }
        
    public void paint(Graphics2D g2d) {
        g2d.fillRect(x, y, 30, 30);
    }
    
}
