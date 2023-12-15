package rocketleague2d.release.GameObject;

import org.newdawn.slick.SlickException;
import rocketleague2d.release.Object.ObjectMouvable;
import static rocketleague2d.release.Rocketleague2dRelease.equipe_blue;

public class Kart extends ObjectMouvable {
        
    private float rotation = 0;
    private float rotation_max = 5.0f;

    private float velocity_max = 250.0f;
    
    private float incress_speed = 0;
    private float incress_speed_max = 10.0f;
    private float incress_speed_acceleration = 0.1f;
    private float incress_speed_deceleration = 0.1f;
    
    private boolean isStop = true;
    
    public Kart(String textureName, float positionX, float positionY, int sizeX, int sizeY) throws SlickException {
        super(textureName, positionX, positionY, sizeX, sizeY);
    }
    
    public void Draw() {
        texture.getTexture().setCenterOfRotation(size_x/2, size_y/2);
        texture.getTexture().setRotation(this.rotation);
        texture.getTexture().drawCentered(position_x, position_y);
        texture.getTexture().setRotation(0.0f);
    }

    public void collision(float terrain_size_x, float terrain_size_y){
        TerrainCollision(terrain_size_x, terrain_size_y, 1.5f);
    }
    
    public void update() {

        if(incress_speed > incress_speed_max){
            incress_speed = incress_speed_max;
        }

        double deltaX = (velocity_x + incress_speed) * Math.cos(Math.toRadians(rotation));
        double deltaY = (velocity_y + incress_speed) * Math.sin(Math.toRadians(rotation));

        position_x += deltaX;
        position_y += deltaY;

        float friction = 0.18f;
        velocity_x *= (1.0 - friction);
        velocity_y *= (1.0 - friction);

        if(velocity_x < 0.0){
            velocity_x = 0.0f;
            isStop = true;
        }
        if(velocity_y < 0.0){
            velocity_y = 0.0f;
            isStop = true;
        }

        if(isStop == true){
            incress_speed -= incress_speed_deceleration;
            if(incress_speed < 0){
                incress_speed = 0;
            }
        }
        
    }
    
    public void accelerate(){
        isStop = false;
        incress_speed += incress_speed_acceleration;

        if (velocity_x < velocity_max) {
            velocity_x += incress_speed_acceleration;
        }
        if (velocity_y < velocity_max) {
            velocity_y += incress_speed_acceleration;
        }
    }

    public void decelerate() {
        if (velocity_x > 0.0) {
            velocity_x -= incress_speed_deceleration;
        }
        if (velocity_y > 0.0) {
            velocity_y -= incress_speed_deceleration;
        }
    }

    public void turnLeft() {
        rotation -= rotation_max;
    }

    public void turnRight() {
        rotation += rotation_max;
    }
        
}
