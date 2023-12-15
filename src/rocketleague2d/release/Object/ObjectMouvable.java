package rocketleague2d.release.Object;

import org.newdawn.slick.SlickException;

public class ObjectMouvable {
    
    public float velocity_x = 0;
    public float velocity_y = 0;

    public float position_x = 0;
    public float position_y = 0;

    public int size_x = 0;
    public int size_y = 0;
    
    public Texture texture;

    public ObjectMouvable(String textureName, float positionX, float positionY, int sizeX, int sizeY) throws SlickException{
        this.texture = new Texture(textureName, sizeX, sizeY);
        this.position_x = positionX;
        this.position_y = positionY;
        this.size_x = sizeX;
        this.size_y = sizeY;
    }
    
    public boolean TerrainCollision(float terrain_size_x, float terrain_size_y, float troubleshot){
        boolean collision = false;
        if(position_x + size_x > terrain_size_x){
            position_x = terrain_size_x - size_x;
            collision = true;
        }
        if(position_y + (size_y*troubleshot) > terrain_size_y){
            position_y = terrain_size_y - (size_y*troubleshot);
            collision = true;
        }
        if(position_x - size_x < 0){
            position_x = size_x;
            collision = true;
        }
        if(position_y - (size_y*troubleshot) < 0){
            position_y = (size_y*troubleshot);
            collision = true;
        }
        return collision;
    }
    
}
