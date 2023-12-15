package rocketleague2d.release.GameObject;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import rocketleague2d.release.Object.ObjectMouvable;

public class Ball extends ObjectMouvable {
    
    private float init_position_x = 0;
    private float init_position_y = 0;
    
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
      if(TerrainCollision(terrain_size_x, terrain_size_y, 0.9f)){
        velocity_x = 0f;
        velocity_y = 0f;
      }
    }

    public void update(float kartX, float kartY, float kartSpeedX, float kartSpeedY){
      float kartRadius = 50.0f; // Adjust the radius of the kart
      float ballRadius = 50.0f; // Adjust the radius of the ball

      // Check for collision
      if(isCollision(kartX, kartY, position_x, position_y, kartRadius, ballRadius)){
        // Collision detected
        System.out.println("Collision detected!");

        // Expel the ball (adjust the expulsion force as needed)
        float expulsionForce = 5.0f;
        // Calculate the direction of expulsion based on the relative position of the kart and the ball
        float directionX = position_x - kartX;
        float directionY = position_y - kartY;
        float magnitude = (float) Math.sqrt(directionX * directionX + directionY * directionY);
        
        // Normalize the direction vector
        directionX /= magnitude;
        directionY /= magnitude;

        // Apply the expulsion force in the calculated direction
        velocity_x = directionX * expulsionForce;
        velocity_y = directionY * expulsionForce;
    }else{
        System.out.println("No collision.");
    }

    // Update positions based on speeds (you need to do this in your game loop)
    position_x += velocity_x;
    position_y += velocity_y;
  }

    // Function to check collision between a kart and a ball
    private boolean isCollision(float kartX, float kartY, float ballX, float ballY, float kartRadius, float ballRadius) {
        // Calculate the distance between the centers of the kart and the ball
        float distance = (float) Math.sqrt(Math.pow(kartX - ballX, 2) + Math.pow(kartY - ballY, 2));
        // Check if the distance is less than the sum of the radii
        return distance < (kartRadius + ballRadius);
    }
    
}