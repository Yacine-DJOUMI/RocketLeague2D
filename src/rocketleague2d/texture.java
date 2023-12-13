package rocketleague2d;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class texture {

    private Image image;

    public texture(String path, int width, int height) throws SlickException {
        this.image = new Image(path);
        this.image = this.image.getScaledCopy(width, height);
    }

    public texture(String path) throws SlickException {
        this.image = new Image(path);
    }
    public Image getImage() {
        return image;
    }

}