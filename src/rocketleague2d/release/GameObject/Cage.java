package rocketleague2d.release.GameObject;

import org.newdawn.slick.SlickException;
import rocketleague2d.release.Object.Texture;

public class Cage {
    
 
    private float position_x = 0;
    private float position_y = 0;
    
 

    public Cage(float positionX, float positionY) throws SlickException{      
      this.position_x = positionX;
      this.position_y = positionY;
    }
    
  public boolean Collision(float ballX, float ballY){
      
      if(isCollision(ballX,ballY)){
          return true;
      }
      return false;
      
  }
  
   private boolean isCollision(float ballX, float ballY) {
       
       float cageRadius = 100.0f;
       float ballRadius = 50.0f;
       
        // Calculate the distance between the centers of the kart and the ball
        float distance = (float) Math.sqrt(Math.pow(position_x - ballX, 2) + Math.pow(position_y - ballY, 2));
        // Check if the distance is less than the sum of the radii
        return distance < (cageRadius + ballRadius);
    }
    
}

    

