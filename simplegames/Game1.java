/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplegames;


import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



/**
 *
 * @author campben5
 */
//@SuppressWaringgs("serial")
public class Game1 extends JPanel {
    
    private JFrame frame;
    protected TLShape gameL;
    private int usrInput;
    private static int level;
    
    public Game1() {
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
                gameL.keyPressed(e);            
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
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
    
    public void gameLoop() {
        while(true) {
            try {
                gameL.fall();
                this.repaint();
                Thread.sleep(10);
                
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
        
        gameL.paint(g2d);
    }
    
}
