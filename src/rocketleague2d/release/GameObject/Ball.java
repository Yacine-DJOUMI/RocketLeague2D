package rocketleague2d.release.GameObject;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import rocketleague2d.release.Object.ObjectMouvable;

public class Ball extends ObjectMouvable {
    
    private float init_position_x = 0;
    private float init_position_y = 0;
    
    private float deceleration = 0.009f;
    
    public Ball(String textureName, GameContainer gc, int size) throws SlickException {
        super(textureName, gc.getWidth()/2, gc.getHeight()/2, size, size);
        init_position_x = position_x;
        init_position_y = position_y;
    }
    
    public void Draw(){
        texture.getTexture().drawCentered(position_x, position_y);
    }
    
    public void Reset(){
        position_x = init_position_x;
        position_y = init_position_y;
        velocity_x = 0;
        velocity_y = 0;
    }
 
    public void collision(float terrain_size_x, float terrain_size_y){
      int collision_direction = TerrainCollision(terrain_size_x, terrain_size_y, 0.9f);
      if(collision_direction == 1){
        velocity_x = -velocity_x;
      } else if(collision_direction == 2){
        velocity_y = -velocity_y;
      }
    }

    public void update(float kartX, float kartY, float kartSpeedX, float kartSpeedY){
      float kartRadius = 50.0f; 
      float ballRadius = 50.0f; 

      
      if(isCollision(kartX, kartY, position_x, position_y, kartRadius, ballRadius)){
        
        System.out.println("Collision detected!");

        
        float expulsionForce = 5.0f;
      
        float directionX = position_x - kartX;
        float directionY = position_y - kartY;
        float magnitude = (float) Math.sqrt(directionX * directionX + directionY * directionY);
        
       
        directionX /= magnitude;
        directionY /= magnitude;

       
        velocity_x = directionX * expulsionForce;
        velocity_y = directionY * expulsionForce;
    }else{
        System.out.println("No collision.");
    }
      
      
      if(velocity_x < 0){
        velocity_x += deceleration;
      }
      if(velocity_y < 0){
        velocity_y += deceleration;
      }

      if(velocity_x > 0){
        velocity_x -= deceleration;
      }
      if(velocity_y > 0){
        velocity_y -= deceleration;
      }
      

    
    position_x += velocity_x;
    position_y += velocity_y;
  }

  
    private boolean isCollision(float kartX, float kartY, float ballX, float ballY, float kartRadius, float ballRadius) {
       
        float distance = (float) Math.sqrt(Math.pow(kartX - ballX, 2) + Math.pow(kartY - ballY, 2));
       
        return distance < (kartRadius + ballRadius);
    }
    
}