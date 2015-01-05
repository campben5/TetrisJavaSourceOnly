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
public class LShape extends Brick {
    
    protected static final int X_START = 75;
    protected static final int Y_START = 75;
    protected Brick[][] l;
    protected int rotationState;
    protected static int fallDelay;
    private int block1X;
    private int block1Y;
    private int block2X;
    private int block2Y;
    private int block3X;
    private int block3Y;
    private int block4X;
    private int block4Y;
    private boolean valid;

    public LShape(Game1 currGame) {
        super(currGame);
        rotationState = 0;
        l = new Brick[3][3];
        l[0][0] = new Brick(currGame, X_START, Y_START );  
        l[1][0] = new Brick(currGame, X_START, Y_START + Brick.B_WIDTH);
        l[2][0] = new Brick(currGame, X_START, Y_START + Brick.B_WIDTH + Brick.B_WIDTH);
        l[2][1] = new Brick(currGame, X_START + Brick.B_WIDTH , Y_START + Brick.B_WIDTH + Brick.B_WIDTH);
        
        System.out.println("Init Check at "+ l[0][0].getBrickX() + "," + l[0][0].getBrickY() );
        System.out.println("Init Check at "+ l[1][0].getBrickX()+ "," + l[1][0].getBrickY() );
        System.out.println("Init Check at "+ l[2][0].getBrickX() + "," + l[2][0].getBrickY() );
        System.out.println("Init Check at "+ l[2][1].getBrickX() + "," + l[2][1].getBrickY() );
        
        setCurrL();
        
        valid = true;
        fallDelay = 0;
 
    }
    
    private void setCurrL() { 
        block1X = l[0][0].getBrickX();
        block1Y = l[0][0].getBrickY(); 
        block2X = l[1][0].getBrickX(); 
        block2Y = l[1][0].getBrickY(); 
        block3X = l[2][0].getBrickX(); 
        block3Y = l[2][0].getBrickY(); 
        block4X = l[2][1].getBrickX(); 
        block4Y = l[2][1].getBrickY(); 
    }
    
    public int getRotationState() {
        //System.out.println("rRight() state is "+ this.rotationState);
        //setRotationState(this.rotationState + 1);
        return this.rotationState;
    }
    
    public void setRotationState(int nextState) {
        this.rotationState = nextState;
    }
    
    public void rRight() {
        int rotX = l[2][0].getBrickX();
        int rotY = l[2][0].getBrickY();
        
        //move from state 0 to state 1
        if(getRotationState() == 0 && valid == true) {
            System.out.println("rRight() state is 0");
            
            //move block 1
            l[0][0].setBrickX(rotX + Brick.B_WIDTH + Brick.B_WIDTH);
            l[0][0].setBrickY(rotY);
            
            //move block2
            l[1][0].setBrickX(rotX + Brick.B_WIDTH);
            l[1][0].setBrickY(rotY);
        
            //move block 3
            l[2][0].setBrickX(rotX);
            l[2][0].setBrickY(rotY);
        
            //move block 4
            l[2][1].setBrickX(rotX);
            l[2][1].setBrickY(rotY + Brick.B_WIDTH);
            
            setCurrL();
            
            //set next state
            setRotationState(1);
            
            valid = false;
        }
        
        //move from state 1 to state 2
        if(getRotationState() == 1 && valid == true) {
            System.out.println("rRight() state is 1");
        
            //move block 1
            l[0][0].setBrickX(rotX);
            l[0][0].setBrickY(rotY + Brick.B_WIDTH + Brick.B_WIDTH);
            
            //move block2
            l[1][0].setBrickX(rotX);
            l[1][0].setBrickY(rotY + Brick.B_WIDTH);
        
            //move block 3
            l[2][0].setBrickX(rotX);
            l[2][0].setBrickY(rotY);
        
            //move block 4
            l[2][1].setBrickX(rotX - Brick.B_WIDTH);
            l[2][1].setBrickY(rotY);
            
            setCurrL();
            
            //set next state
            setRotationState(2);
            valid = false;
        }
        
        //move from state 2 to state -1
        if(getRotationState() == 2 && valid == true) {
            System.out.println("rRight() state is 2");
        
            //move block 1
            l[0][0].setBrickX(rotX - Brick.B_WIDTH - Brick.B_WIDTH);
            l[0][0].setBrickY(rotY);
            
            //move block2
            l[1][0].setBrickX(rotX - Brick.B_WIDTH);
            l[1][0].setBrickY(rotY);

            //move block 3
            l[2][0].setBrickX(rotX);
            l[2][0].setBrickY(rotY);
        
            //move block 4
            l[2][1].setBrickX(rotX);
            l[2][1].setBrickY(rotY - Brick.B_WIDTH); 
            
            setCurrL();
            
            //set next state
            setRotationState(-1);
            valid = false;
        }

        //move from state -1 to state 0
        if(getRotationState() == -1 && valid == true) {
            System.out.println("rRight() state is -1");
        
            //move block 1
            l[0][0].setBrickX(rotX);
            l[0][0].setBrickY(rotY - Brick.B_WIDTH - Brick.B_WIDTH);
            
            //move block2
            l[1][0].setBrickX(rotX);
            l[1][0].setBrickY(rotY - Brick.B_WIDTH);

            //move block 3
            l[2][0].setBrickX(rotX);
            l[2][0].setBrickY(rotY);
        
            //move block 4
            l[2][1].setBrickX(rotX + Brick.B_WIDTH);
            l[2][1].setBrickY(rotY); 
            
            setCurrL();
            
            //set next state
            setRotationState(0);
            valid = false;
        }
    }
    
    public void rLeft() {
        int rotX = l[2][0].getBrickX();
        int rotY = l[2][0].getBrickY();
        
        //move from state 0 to state -1
        if(getRotationState() == 0 && valid == true) {
            System.out.println("rLeft() state is 0");
            
            //move block 1
            l[0][0].setBrickX(rotX - Brick.B_WIDTH - Brick.B_WIDTH);
            l[0][0].setBrickY(rotY);
            
            //move block2
            l[1][0].setBrickX(rotX - Brick.B_WIDTH);
            l[1][0].setBrickY(rotY);

            //move block 3
            l[2][0].setBrickX(rotX);
            l[2][0].setBrickY(rotY);
        
            //move block 4
            l[2][1].setBrickX(rotX);
            l[2][1].setBrickY(rotY - Brick.B_WIDTH);
            
            setCurrL();
            
            //set next state
            setRotationState(-1);
            
            valid = false;
        }
        
        //move from state -1 to state 2
        if(getRotationState() == -1 && valid == true) {
            System.out.println("rLeft() state is 1");
        
            //move block 1
            l[0][0].setBrickX(rotX);
            l[0][0].setBrickY(rotY + Brick.B_WIDTH + Brick.B_WIDTH);
            
            //move block2
            l[1][0].setBrickX(rotX);
            l[1][0].setBrickY(rotY + Brick.B_WIDTH);
        
            //move block 3
            l[2][0].setBrickX(rotX);
            l[2][0].setBrickY(rotY);
        
            //move block 4
            l[2][1].setBrickX(rotX - Brick.B_WIDTH);
            l[2][1].setBrickY(rotY);
            
            setCurrL();
            
            //set next state
            setRotationState(2);
            valid = false;
        }
        
        //move from state 2 to state 1
        if(getRotationState() == 2 && valid == true) {
            System.out.println("rLeft() state is 2");
        
            //move block 1
            l[0][0].setBrickX(rotX + Brick.B_WIDTH + Brick.B_WIDTH);
            l[0][0].setBrickY(rotY);
            
            //move block2
            l[1][0].setBrickX(rotX + Brick.B_WIDTH);
            l[1][0].setBrickY(rotY);
        
            //move block 3
            l[2][0].setBrickX(rotX);
            l[2][0].setBrickY(rotY);
        
            //move block 4
            l[2][1].setBrickX(rotX);
            l[2][1].setBrickY(rotY + Brick.B_WIDTH);
            
            setCurrL();
            
            //set next state
            setRotationState(1);
            valid = false;
        }

        //move from state 1 to state 0
        if(getRotationState() == 1 && valid == true) {
            System.out.println("rLeft() state is -1");
        
            //move block 1
            l[0][0].setBrickX(rotX);
            l[0][0].setBrickY(rotY - Brick.B_WIDTH - Brick.B_WIDTH);
            
            //move block2
            l[1][0].setBrickX(rotX);
            l[1][0].setBrickY(rotY - Brick.B_WIDTH);

            //move block 3
            l[2][0].setBrickX(rotX);
            l[2][0].setBrickY(rotY);
        
            //move block 4
            l[2][1].setBrickX(rotX + Brick.B_WIDTH);
            l[2][1].setBrickY(rotY); 
            
            setCurrL();
            
            //set next state
            setRotationState(0);
            valid = false;
        }
    }
    
    public void fall(int level) {
        
        this.fallDelay += level;
        if(this.fallDelay > 10) {
        //System.out.println("falling "+ l[0][0].getBrickY() + level);
        l[0][0].setBrickY(l[0][0].getBrickY() + level);  
        l[1][0].setBrickY(l[1][0].getBrickY() + level); 
        l[2][0].setBrickY(l[2][0].getBrickY() + level); 
        l[2][1].setBrickY(l[2][1].getBrickY() + level); 
        
        setCurrL();
        this.fallDelay = 0;
        }
        
    }
    
    public void mRight() {
        
        boolean allowMove = false;
        if(this.valid == true) {
        //System.out.println("falling "+ l[0][0].getBrickY() + level);
            switch (this.rotationState) {
                case -1:
                    if((l[2][0].getBrickX() + 2 + Brick.B_WIDTH < l[2][0].game.getWidth())) {
                        allowMove = true;
                    }
                    break;
                case 0:
                    //System.out.println("Width "+l[2][1].game.getWidth());
                    //System.out.println("new move "+ (l[2][1].getBrickX() + 2 + Brick.B_WIDTH));
                    if((l[2][1].getBrickX() + 2 + Brick.B_WIDTH < l[2][1].game.getWidth())) {
                        allowMove = true;
                    }
                    break;
                case 1:
                    if((l[0][0].getBrickX() + 2 + Brick.B_WIDTH < l[0][0].game.getWidth())) {
                        allowMove = true;
                    }
                    break;
                case 2:
                    if((l[2][0].getBrickX() + 2  + Brick.B_WIDTH < l[2][0].game.getWidth())) {
                        allowMove = true;
                    }
                    break;
                }
                    
            if(allowMove == true) {    
                l[0][0].setBrickX(l[0][0].getBrickX() + 2);  
                l[1][0].setBrickX(l[1][0].getBrickX() + 2); 
                l[2][0].setBrickX(l[2][0].getBrickX() + 2); 
                l[2][1].setBrickX(l[2][1].getBrickX() + 2);
            }
            else {
                 System.out.println("Move not allowed");
            }
            
            setCurrL();
            this.valid = false;
        }    
    }
    
    public void mLeft() {
            boolean allowMove = false;
            if(this.valid == true) {
                //System.out.println("falling "+ l[0][0].getBrickY() + level);
                switch (this.rotationState) {
                    case -1:
                        if((l[0][0].getBrickX() - 2 + Brick.B_WIDTH > Brick.B_WIDTH)) {
                            allowMove = true;
                        }
                        break;
                    case 0:
                        //System.out.println("Width "+l[2][0].game.getWidth());
                        //System.out.println("new move "+ (l[2][0].getBrickX() - 2 + Brick.B_WIDTH));
                        if((l[2][0].getBrickX() - 2 + Brick.B_WIDTH > Brick.B_WIDTH)) {
                            allowMove = true;
                        }
                        break;
                    case 1:
                        if((l[2][0].getBrickX() - 2 + Brick.B_WIDTH > Brick.B_WIDTH)) {
                            allowMove = true;
                        }
                        break;
                    case 2:
                        if((l[2][1].getBrickX() - 2 + Brick.B_WIDTH > Brick.B_WIDTH)) {
                            allowMove = true;
                        }
                        break;
                }
                    
                if(allowMove == true) {    
                    l[0][0].setBrickX(l[0][0].getBrickX() - 2);  
                    l[1][0].setBrickX(l[1][0].getBrickX() - 2); 
                    l[2][0].setBrickX(l[2][0].getBrickX() - 2); 
                    l[2][1].setBrickX(l[2][1].getBrickX() - 2);
                }
                else {
                    System.out.println("Move not allowed");
                }

                setCurrL();
                this.valid = false;
        }
        
    }
    
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            //xa = -1;
            System.out.println("call rLeft()");
            rLeft();
            valid = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            //xa = 1;
            System.out.println("call rRight()");
            rRight();
            valid = true;

        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            //xa = 1;
            System.out.println("call mLeft()");
            mLeft();
            valid = true;

        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            //xa = 1;
            System.out.println("call mRight()");
            mRight();
            valid = true;

        }
        else {
            //xa = 0;
        }
        
    }
    
    public void keyReleased(KeyEvent e) {
        //xa = 0;
            System.out.println("release");
            //stableize block 1
            l[0][0].setBrickX(block1X);
            l[0][0].setBrickY(block1Y);
            
            //stableize  block2
            l[1][0].setBrickX(block2X);
            l[1][0].setBrickY(block2Y);

            //stableize  block 3
            l[2][0].setBrickX(block3X);
            l[2][0].setBrickY(block3Y);
        
            //stableize  block 4
            l[2][1].setBrickX(block4X);
            l[2][1].setBrickY(block4Y);
    }
    
    public void paint(Graphics2D g2d) {
        
        //if(this.rotationState == 0) {  
            g2d.fillRect(l[0][0].getBrickX(), l[0][0].getBrickY(), Brick.B_WIDTH, Brick.B_WIDTH);
            g2d.fillRect(l[1][0].getBrickX(), l[1][0].getBrickY(), Brick.B_WIDTH, Brick.B_WIDTH);
            g2d.fillRect(l[2][0].getBrickX(), l[2][0].getBrickY(), Brick.B_WIDTH, Brick.B_WIDTH);
            g2d.fillRect(l[2][1].getBrickX(), l[2][1].getBrickY(), Brick.B_WIDTH, Brick.B_WIDTH);
        //}
        //else if(this.rotationState == 0) {
            
        //}
        
       
    }
    
    
}
