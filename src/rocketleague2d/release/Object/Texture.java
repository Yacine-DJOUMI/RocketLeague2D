package rocketleague2d.release.Object;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Texture {

    private Image image;

    public Texture(String path, int width, int height) throws SlickException {
        System.out.println("loading... texture/" + path);
        this.image = new Image("texture/" + path);
        this.image = this.image.getScaledCopy(width, height);
    }

    public Image getTexture() {
        return image;
    }   

}
