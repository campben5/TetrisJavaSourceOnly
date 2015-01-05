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
public class TZShape extends TetrisPiece {
    
    //constructor
    public TZShape(Game1 currGame) {
        super(currGame);
    }
    
    
    //rotation methods
    public int getRotationState() {
        return 0;
    }
    
    public void setRotationState(int nextState) {
        
    }
    
    public boolean locationPreview(CollisionType type) {
        return true;
    }
    
    public void rRight() {
        
    }
    
    public void rLeft() {
        
    } 
}
