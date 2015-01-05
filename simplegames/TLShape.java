/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplegames;

/**
 *
 * @author campben5
 */
public class TLShape extends TetrisPiece {
    
    //constructor
    public TLShape(Game1 currGame) {
        
        //Call parent constructor
        super(currGame); 
        
        //Do sepcific initialization
        Brick1.setBrickX(X_START);
        Brick1.setBrickY(Y_START);

        Brick2.setBrickX(X_START);
        Brick2.setBrickY(Y_START + Brick.B_WIDTH);
        
        Brick3.setBrickX(X_START);
        Brick3.setBrickY(Y_START + Brick.B_WIDTH + Brick.B_WIDTH);        

        Brick4.setBrickX(X_START + Brick.B_WIDTH);
        Brick4.setBrickY(Y_START + Brick.B_WIDTH + Brick.B_WIDTH);    
    }
    
    
    //rotation methods
    public int getRotationState() {
        return super.rotationState;
    }
    
    public void setRotationState(int nextState) {
        super.rotationState = nextState;
        
    }
    
    public boolean locationPreview(CollisionType type) {
 
        boolean crash = false; //used to know if the rojected move will have a crash!
        
        //store current Brick x,y locations
        int B1x = Brick1.getBrickX();
        int B1y = Brick1.getBrickY();
        
        int B2x = Brick2.getBrickX();
        int B2y = Brick2.getBrickY();
        
        int B3x = Brick3.getBrickX();
        int B3y = Brick3.getBrickY();
        
        int B4x = Brick4.getBrickX();
        int B4y = Brick4.getBrickY();
        
        int B5x = Brick5.getBrickX();
        int B5y = Brick5.getBrickY();
        
        //need to know the next rotation state
        System.out.println("Set Preview State to "+prevRotationState);
        if(prevRotationState == 0){
            System.out.println("trying 0");
            setStateZero();
        } else if (prevRotationState == 1) {
            System.out.println("trying 1");
            setState1();
        } else if (prevRotationState == 2) {
            System.out.println("trying 2");
            setState2();
        } else if (prevRotationState == -1) {
            System.out.println("trying -1");
           setStateNeg1(); 
        } else {
            //error
            System.out.println("prevRotationState is invalid!");
            
            //handle error by reorting it as a crash
            return true;
        }
        
        //based on next state set the new brick x,y for preview crash check
        
        //do the crash check! 
        //NEED to write in a more general way! not just RIGHT!
        crash = collisionCheck(Brick1, Brick2, Brick3,
                                    Brick4, Brick5, type);
        
        //need to restore x,y. This fucntion only knows if the grid space is 
        //ok or not
        Brick1.setBrickX(B1x);
        Brick1.setBrickY(B1y);
        
        Brick2.setBrickX(B2x);
        Brick2.setBrickY(B2y);
        
        Brick3.setBrickX(B3x);
        Brick3.setBrickY(B3y);
        
        Brick4.setBrickX(B4x);
        Brick4.setBrickY(B4y);
        
        Brick5.setBrickX(B5x);
        Brick5.setBrickY(B5y);
        
        System.out.println("preview is: " + crash);
        return crash;
    }
    
    private void setState1(){
                
        //need a rotatin point
        xRotationPoint = Brick3.getBrickX();
        yRotationPoint = Brick3.getBrickY();
        
        //move block 1
        Brick1.setBrickX(xRotationPoint + Brick.B_WIDTH + Brick.B_WIDTH);
        Brick1.setBrickY(yRotationPoint);
            
        //move block2
        Brick2.setBrickX(xRotationPoint+ Brick.B_WIDTH);
        Brick2.setBrickY(yRotationPoint);
        
        //move block 3
        Brick3.setBrickX(xRotationPoint);
        Brick3.setBrickY(yRotationPoint);
        
        //move block 4
        Brick4.setBrickX(xRotationPoint);
        Brick4.setBrickY(yRotationPoint + Brick.B_WIDTH);
            
        //Handle Brick 5
        TBrick.brickCloneLocation(Brick4, Brick5);       
    }
    
    private void setState2() {
                
        //need a rotatin point
        xRotationPoint = Brick3.getBrickX();
        yRotationPoint = Brick3.getBrickY();
        
        //move block 1
        Brick1.setBrickX(xRotationPoint);
        Brick1.setBrickY(yRotationPoint + Brick.B_WIDTH + Brick.B_WIDTH);
            
        //move block2
        Brick2.setBrickX(xRotationPoint);
        Brick2.setBrickY(yRotationPoint + Brick.B_WIDTH);
        
        //move block 3
        Brick3.setBrickX(xRotationPoint);
        Brick3.setBrickY(yRotationPoint);
        
        //move block 4
        Brick4.setBrickX(xRotationPoint - Brick.B_WIDTH);
        Brick4.setBrickY(yRotationPoint);
            
        //Handle Brick 5
        TBrick.brickCloneLocation(Brick4, Brick5);
    }
    
    private void setStateNeg1() {
                
        //need a rotatin point
        xRotationPoint = Brick3.getBrickX();
        yRotationPoint = Brick3.getBrickY();
        
        //move block 1
        Brick1.setBrickX(xRotationPoint- Brick.B_WIDTH - Brick.B_WIDTH);
        Brick1.setBrickY(yRotationPoint);
            
        //move block2
        Brick2.setBrickX(xRotationPoint- Brick.B_WIDTH);
        Brick2.setBrickY(yRotationPoint);

        //move block 3
        Brick3.setBrickX(xRotationPoint);
        Brick3.setBrickY(yRotationPoint);
        
        //move block 4
        Brick4.setBrickX(xRotationPoint);
        Brick4.setBrickY(yRotationPoint - Brick.B_WIDTH); 
            
        //Handle Brick 5
        TBrick.brickCloneLocation(Brick4, Brick5);
    }
    
    private void setStateZero() {
                
        //need a rotatin point
        xRotationPoint = Brick3.getBrickX();
        yRotationPoint = Brick3.getBrickY();
        
        //move block 1
        Brick1.setBrickX(xRotationPoint);
        Brick1.setBrickY(yRotationPoint - Brick.B_WIDTH - Brick.B_WIDTH);
            
        //move block2
        Brick2.setBrickX(xRotationPoint);
        Brick2.setBrickY(yRotationPoint - Brick.B_WIDTH);

        //move block 3
        Brick3.setBrickX(xRotationPoint);
        Brick3.setBrickY(yRotationPoint);
        
        //move block 4
        Brick4.setBrickX(xRotationPoint+ Brick.B_WIDTH);
        Brick4.setBrickY(yRotationPoint); 
            
        //Handle Brick 5
        TBrick.brickCloneLocation(Brick4, Brick5);
    }
    
    public void rRight() {

        //Check for crash with game wall
        if(locationPreview(CollisionType.RIGHT)){
            System.out.println("rRight() Preview showed CRASH, move not allowed!");
            valid = false;
            return;
        }
        
        //move from state 0 to state 1
        if(getRotationState() == 0 && valid == true) {
            System.out.println("rRight() state is 0");
            
            //check collisionCheck() for hitting other game shapes
            if(collisionCheck(Brick1, Brick2, Brick3,
                           Brick4, Brick5, CollisionType.COLLISON)){
                System.out.println("rRight() CRASH SHAPE, move not allowed!");
                valid = false;
                return;
            }
            
            setState1();
        
            //set next state
            setRotationState(1);
            
            valid = false;
        }
        
        //move from state 1 to state 2
        if(getRotationState() == 1 && valid == true) {
            System.out.println("rRight() state is 1");

            //check collisionCheck() for hitting other game shapes
            if(collisionCheck(Brick1, Brick2, Brick3,
                           Brick4, Brick5, CollisionType.COLLISON)){
                System.out.println("rRight() CRASH SHAPE, move not allowed!");
                valid = false;
                return;
            }
            
            setState2();
            
            //set next state
            setRotationState(2);
            valid = false;
        }
        
        //move from state 2 to state -1
        if(getRotationState() == 2 && valid == true) {
            System.out.println("rRight() state is 2");

            //check collisionCheck() for hitting other game shapes
            if(collisionCheck(Brick1, Brick2, Brick3,
                           Brick4, Brick5, CollisionType.COLLISON)){
                System.out.println("rRight() CRASH SHAPE, move not allowed!");
                valid = false;
                return;
            }
   
            setStateNeg1();
            
            //set next state
            setRotationState(-1);
            valid = false;
        }

        //move from state -1 to state 0
        if(getRotationState() == -1 && valid == true) {
            System.out.println("rRight() state is -1");
 
            //check collisionCheck() for hitting other game shapes
            if(collisionCheck(Brick1, Brick2, Brick3,
                           Brick4, Brick5, CollisionType.COLLISON)){
                System.out.println("rRight() CRASH SHAPE, move not allowed!");
                valid = false;
                return;
            }

            setStateZero();
            
            //set next state
            setRotationState(0);
            valid = false;
        }
        
    }
    
    public void rLeft() {
        System.out.println("rLeft()");
        
        //Check for crash with game wall
        if(locationPreview(CollisionType.LEFT)){
            System.out.println("rLeft() Preview showed CRASH, move not allowed!");
            valid = false;
            return;
        }
        
        //move from state 0 to state -1
        if(getRotationState() == 0 && valid == true) {
            System.out.println("rLeft() state is 0");
            
            //check collisionCheck() for hitting other game shapes
            if(collisionCheck(Brick1, Brick2, Brick3,
                           Brick4, Brick5, CollisionType.COLLISON)){
                System.out.println("rLeft() CRASH SHAPE, move not allowed!");
                valid = false;
                return;
            }

            setStateNeg1();
            
            //set next state
            setRotationState(-1);
            
            valid = false;
        }
        
        //move from state -1 to state 2
        if(getRotationState() == -1 && valid == true) {
            System.out.println("rLeft() state is 1");
            
            //check collisionCheck() for hitting other game shapes
            if(collisionCheck(Brick1, Brick2, Brick3,
                           Brick4, Brick5, CollisionType.COLLISON)){
                System.out.println("rLeft() CRASH SHAPE, move not allowed!");
                valid = false;
                return;
            }    

            setState2();
            
            //set next state
            setRotationState(2);
            valid = false;
        }
        
        //move from state 2 to state 1
        if(getRotationState() == 2 && valid == true) {
            System.out.println("rLeft() state is 2");
            
            //check collisionCheck() for hitting other game shapes
            if(collisionCheck(Brick1, Brick2, Brick3,
                           Brick4, Brick5, CollisionType.COLLISON)){
                System.out.println("rLeft() CRASH SHAPE, move not allowed!");
                valid = false;
                return;
            }    
        
            setState1();
            
            //set next state
            setRotationState(1);
            valid = false;
        }

        //move from state 1 to state 0
        if(getRotationState() == 1 && valid == true) {
            System.out.println("rLeft() state is -1");
            
            //check collisionCheck() for hitting other game shapes
            if(collisionCheck(Brick1, Brick2, Brick3,
                           Brick4, Brick5, CollisionType.COLLISON)){
                System.out.println("rLeft() CRASH SHAPE, move not allowed!");
                valid = false;
                return;
            }
 
            setStateZero();
            
            //set next state
            setRotationState(0);
            valid = false;
        }      
    } 
}
