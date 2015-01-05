/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplegames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.lang.Integer;


/**
 *
 * @author campben5
 */
//@SuppressWaringgs("serial")
public class Game1 extends JPanel {
    
    private JFrame frame;
    private Ball gameBall;
    protected Racket gameRacket;
    protected Brick gameBrick;
    //protected LShape gameL;
    protected TLShape gameL;
    private int usrInput;
    private static int level;
    
    public Game1() {
        //make the game ball
        //this.gameBall = new Ball(this);
        //this.gameRacket = new Racket(this);
        //this.gameBrick = new Brick(this);
        //this.gameL = new LShape(this);
        this.usrInput = 0;
        this.level = 1;
        
        
        gameL = new TLShape(this);

        
        //create the game board
        this.initUI();
        
        //start the game!
        this.gameLoop();
    }
    
    
    public int getGameLevel() {
        //System.out.println("Get Game Level");
        return level;
    }
    
    private void initUI() {
        frame = new JFrame("Tetris");
        
        //set up the key listener for user input!
        //noteL this is using an anonymous class! 
        //Anonymous classes can be use to implement an interface!
        KeyListener listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
                //System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
                //x = Integer. KeyEvent.getKeyText(e.getKeyCode())
                //gameRacket.keyPressed(e);
                gameL.keyPressed(e);
                
                //try {
                //    usrInput = Integer.parseInt(KeyEvent.getKeyText(e.getKeyCode()));
                //} catch (NumberFormatException exc) {
                //    System.out.println("input was not a number!");
                //}
                
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                //System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
                //gameRacket.keyReleased(e);
                gameL.keyReleased(e);
            }
        };
        
        //add the listener to the frame
        frame.addKeyListener(listener);
        frame.setFocusable(true);
        
        //set the size of the game board
        frame.add(this);
        frame.setSize(500, 700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void gameLoop() {
        while(true) {
            try {
                //gameBall.moveBall();
                //gameRacket.move();
                //gameBrick.moveBrick();
                gameL.fall();
                this.repaint();
                Thread.sleep(10);
                
                //gameBall.moveBall(usrInput);
            } catch (InterruptedException e) {
                System.out.println("error closing program!");
                return;
            
            }
        }
    }
    
    public void gameOver() {
        JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }
    
    @Override
    //note this is run by a thread that is started
    //when the JFrame object is created!
    public void paint(Graphics g) {
        //System.out.println("in paint");
        super.paint(g);//if you do not call this, you just get a line!
        Graphics2D g2d = (Graphics2D) g;
        
        //makes edges of moving ball smoother
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                              RenderingHints.VALUE_ANTIALIAS_ON);
        
        //gameBall.paint(g2d);
        //gameRacket.paint(g2d);
        //gameBrick.paint(g2d);
        gameL.paint(g2d);
        //g2d.setColor(Color.red);

        //g2d.drawOval(0, 50, 30, 30);
        //g2d.fillRect(50, 0, 30, 30);
        //g2d.fillRect(50, 50, 30, 30);
        
        //g2d.draw(new Ellipse2D.Double(0, 100, 30, 30));
    }
    
}
