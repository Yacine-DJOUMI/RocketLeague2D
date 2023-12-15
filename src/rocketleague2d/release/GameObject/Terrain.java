package rocketleague2d.release.GameObject;

import org.newdawn.slick.SlickException;
import rocketleague2d.release.Object.Texture;

public class Terrain {
 
    private float position_x = 0;
    private float position_y = 0;
    
    private int size_x = 0;
    private int size_y = 0;

    private Texture texture;

    public Terrain(String textureName, float positionX, float positionY, int sizeX, int sizeY) throws SlickException{
      this.texture = new Texture(textureName, sizeX, sizeY);
      this.position_x = positionX;
      this.position_y = positionY;
      this.size_x = sizeX;
      this.size_y = sizeY;
    }
    
    public void Draw(){
        this.texture.getTexture().setCenterOfRotation(size_x, size_y);
        this.texture.getTexture().draw();
    }
    
}
