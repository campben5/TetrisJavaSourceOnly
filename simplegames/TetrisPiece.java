/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplegames;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author campben5
 */
public abstract class TetrisPiece {
    
    //Class level vriables, every instanciated object of the 
    //TetrisPiece class will share this
    protected static int fallRate; //initially set in constructor
    protected static double speedIncrease; //initially set in constructor
    protected static Game1 game;   //initially set in constructor
    private static int fallAttemptCount; //initially set in constructor
    private static boolean gameSet = false;
    protected static enum CollisionType { LEFT, RIGHT, COLLISON };
    
    //Instance variables common to all Tetris pieces
    protected int rotationState;
    protected int prevRotationState;
    protected int xRotationPoint;
    protected int yRotationPoint;
    protected boolean valid;

    //Has A section
    protected TBrick Brick1;
    protected TBrick Brick2;
    protected TBrick Brick3; //used on all derived shapes for setting foration point
    protected TBrick Brick4;
    protected TBrick Brick5; //for Tetris peices that only print 4 bricks this
                             //Bridk5 will be the same as Brick 4 when game play Starts!
    
    //Constants for class
    protected static final int X_START = 100;
    protected static final int Y_START = 0;

    
    //Consttructor
    public TetrisPiece(Game1 currGame) {
        
        //only need to set these static fields once
        if(gameSet == false) {
            game = currGame;
            fallRate = game.getGameLevel();
            speedIncrease = 0.1;
            fallAttemptCount = 0;
            gameSet = true;
        }
        
        Brick1 = new TBrick(game, X_START, Y_START);
        Brick2 = new TBrick(game, X_START, Y_START);
        Brick3 = new TBrick(game, X_START, Y_START);
        Brick4 = new TBrick(game, X_START, Y_START);
        Brick5 = new TBrick(game, X_START, Y_START);
        
        rotationState = 0;
        prevRotationState = 0;
        xRotationPoint = Brick3.getBrickX();
        yRotationPoint = Brick3.getBrickY();
        valid = true;
    }
    
    
    
    //rotation methods
    abstract public int getRotationState();
    
    abstract public void setRotationState(int nextState);
    
    abstract public void rRight();
    
    abstract public void rLeft();
    
    abstract public boolean locationPreview(CollisionType type);
    
    //Check for out of bounds on rotation
    private boolean collisionRightHelper(int toCheck) {
        boolean crashFound = true;
        //System.out.println("toCheck: " + toCheck);
        //System.out.println("toCheck - Brick.B_WIDTH: " + (toCheck - Brick.B_WIDTH));
        //System.out.println("toCheck + Brick.B_WIDTH: " + (toCheck + Brick.B_WIDTH));
        //System.out.println("game.getWidth(): " + (game.getWidth()));
        
        if((toCheck + Brick.B_WIDTH <= game.getWidth()) && (toCheck >= 0)){
            crashFound = false;
        }
        return crashFound;
    }
    
    //Check for out of bounds on rotation
    private boolean collisionLeftHelper(int toCheck) {
        return collisionRightHelper(toCheck);
    }
    
    public boolean collisionCheck(TBrick B1, TBrick B2, TBrick B3,
                                    TBrick B4, TBrick B5, CollisionType type) {
        boolean crash = true;
        System.out.println("COLLION CHECK");
        if(type == CollisionType.RIGHT) {
            System.out.println("CollisionType.RIGHT");
            //Check all bricks to make shure they do not go outside the game space
            System.out.println("check Brick1");
            crash = collisionRightHelper(Brick1.getBrickX());
            if(crash == false) {
                System.out.println("check Brick2");
                crash = collisionRightHelper(Brick2.getBrickX());
                if(crash == false) {
                    System.out.println("check Brick3");
                    crash = collisionRightHelper(Brick3.getBrickX());
                    if(crash == false) {
                        System.out.println("check Brick4");
                        crash = collisionRightHelper(Brick4.getBrickX());
                        if(crash == false) {
                            System.out.println("check Brick5");
                            crash = collisionRightHelper(Brick5.getBrickX());
                        }
                    }
                } 
            }
            
        } else if(type == CollisionType.LEFT) {
            //if((Brick1.getBrickX() - 2 + Brick.B_WIDTH > Brick.B_WIDTH))
            System.out.println("CollisionType.LEFT");
            //Check all bricks to make shure they do not go outside the game space
            System.out.println("check Brick1");
            crash = collisionLeftHelper(Brick1.getBrickX());
            if(crash == false) {
                System.out.println("check Brick2");
                crash = collisionLeftHelper(Brick2.getBrickX());
                if(crash == false) {
                    System.out.println("check Brick3");
                    crash = collisionLeftHelper(Brick3.getBrickX());
                    if(crash == false) {
                        System.out.println("check Brick4");
                        crash = collisionLeftHelper(Brick4.getBrickX());
                        if(crash == false) {
                            System.out.println("check Brick5");
                            crash = collisionLeftHelper(Brick5.getBrickX());
                        }
                    }
                } 
            }
            
        } else if(type == CollisionType.COLLISON) {
            System.out.println("CollisionType.COLLISION");
            crash = false;
            
        } else { //error
            System.out.println("Error, invalid ColisionType");
            crash = false;
        }
        
        System.out.println("return: "+ crash);
        return crash;  
    }
    
    //move methods
    public void mRight() {
        boolean allowMove = false;
        if(this.valid == true) {
        //System.out.println("falling "+ l[0][0].getBrickY() + level);
            switch (this.rotationState) {
                case -1:
                    if((Brick3.getBrickX() + Brick.B_WIDTH < game.getWidth())) {
                        allowMove = true;
                    }
                    break;
                case 0:
                    if((Brick4.getBrickX() + Brick.B_WIDTH < game.getWidth())) {
                        allowMove = true;
                    }
                    break;
                case 1:
                    if((Brick1.getBrickX() + Brick.B_WIDTH < game.getWidth())) {
                        allowMove = true;
                    }
                    break;
                case 2:
                    if((Brick3.getBrickX() + Brick.B_WIDTH < game.getWidth())) {
                        allowMove = true;
                    }
                    break;
                }
                    
            if(allowMove == true) {    
                Brick1.setBrickX(Brick1.getBrickX() + 2);  
                Brick2.setBrickX(Brick2.getBrickX() + 2); 
                Brick3.setBrickX(Brick3.getBrickX() + 2); 
                Brick4.setBrickX(Brick4.getBrickX() + 2);
                
                //Handle Brick 5
                TBrick.brickCloneLocation(Brick4, Brick5);
            }
            else {
                 //System.out.println("Move not allowed");
            }
            valid = false;
        }        
    }
    
    public void mLeft() {
        boolean allowMove = false;
        if(this.valid == true) {
            //System.out.println("falling "+ l[0][0].getBrickY() + level);
            switch (this.rotationState) {
                case -1:
                    if((Brick1.getBrickX() + Brick.B_WIDTH > Brick.B_WIDTH)) {
                        allowMove = true;
                    }
                    break;
                case 0:
                    if((Brick2.getBrickX() + Brick.B_WIDTH > Brick.B_WIDTH)) {
                        allowMove = true;
                    }
                    break;
                case 1:
                    if((Brick3.getBrickX() + Brick.B_WIDTH > Brick.B_WIDTH)) {
                        allowMove = true;
                    }
                    break;
                case 2:
                    if((Brick4.getBrickX() + Brick.B_WIDTH > Brick.B_WIDTH)) {
                        allowMove = true;
                    }
                    break;
            }
                    
            if(allowMove == true) {    
                Brick1.setBrickX(Brick1.getBrickX() -2);  
                Brick2.setBrickX(Brick2.getBrickX() -2); 
                Brick3.setBrickX(Brick3.getBrickX() -2); 
                Brick4.setBrickX(Brick4.getBrickX() -2);
                
                //Handle Brick 5
                TBrick.brickCloneLocation(Brick4, Brick5);
            }
            else {
                //System.out.println("Move not allowed");
            }
            valid = false;
        }
        
    }
    
   //key press methods
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            //xa = -1;
            //System.out.println("call rLeft()");
            
            //set next rotatation state for preview
            if(rotationState == 0) {
                prevRotationState = -1;
            } else if (rotationState == 1) {
                prevRotationState = 0;
            } else if (rotationState == 2) {
                prevRotationState = 1;
            } else if(rotationState == -1) {
                prevRotationState = 2;
            } else {
                System.out.println("Error setting preview rotation state");
            }
            
            //Attempt rotation
            rLeft();
            valid = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            //xa = 1;
            //System.out.println("call rRight()");
            
            //set next rotatation state for preview
            if(rotationState == 0) {
                prevRotationState = 1;
            } else if (rotationState == 1) {
                prevRotationState = 2;
            } else if (rotationState == 2) {
                prevRotationState = -1;
            } else if(rotationState == -1) {
                prevRotationState = 0;
            } else {
                System.out.println("Error setting preview rotation state");
            }
            
            //Attempt rotation
            rRight();
            valid = true;

        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            //xa = 1;
            //System.out.println("call mLeft()");
            mLeft();
            valid = true;

        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            //xa = 1;
            //System.out.println("call mRight()");
            mRight();
            valid = true;

        }
        else {
            //xa = 0;
        }
    }
    
    public void keyReleased(KeyEvent e) {
        //System.out.println("release");
        
    }
    
    //draw on scree method
    public void paint(Graphics2D g2d) {
         
        //System.out.println("Paint L");
        g2d.fillRect(Brick1.getBrickX(), Brick1.getBrickY(), Brick.B_WIDTH, Brick.B_WIDTH);
        g2d.fillRect(Brick2.getBrickX(), Brick2.getBrickY(), Brick.B_WIDTH, Brick.B_WIDTH);
        g2d.fillRect(Brick3.getBrickX(), Brick3.getBrickY(), Brick.B_WIDTH, Brick.B_WIDTH);
        g2d.fillRect(Brick4.getBrickX(), Brick4.getBrickY(), Brick.B_WIDTH, Brick.B_WIDTH);
    }
    
    //fall methods
    public static int getFallRate() { 
        //System.out.println("Fall Rate" + fallRate);
        return fallRate; 
    }
    
    public void setFallRate(double speed) {
        double currSpeed = (double)game.getGameLevel();
        speedIncrease += speed;
        
        if(speedIncrease > currSpeed) {
            fallRate += 1;
        }
        
    }
    
    private void doFall() {
        //System.out.println("DoFall" + fallAttemptCount);
        if(fallAttemptCount == 25) {
            Brick1.setBrickY(Brick1.getBrickY() + getFallRate());  
            Brick2.setBrickY(Brick2.getBrickY() + getFallRate()); 
            Brick3.setBrickY(Brick3.getBrickY() + getFallRate()); 
            Brick4.setBrickY(Brick4.getBrickY() + getFallRate()); 
        
            //Handle Brick 5
            TBrick.brickCloneLocation(Brick4, Brick5);
            
            //reset fallAttemptCount
            fallAttemptCount = 0;
        }
        else {
            fallAttemptCount += 1;
        }
    }
    
    public void fall() {
        doFall();
    }  
}
