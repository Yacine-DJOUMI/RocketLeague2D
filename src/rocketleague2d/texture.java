package rocketleague2d;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class texture {

    private Image image;

    public texture(String imagePath) throws SlickException {
        loadImage(imagePath);
    }

    private void loadImage(String imagePath) throws SlickException {
        try {
            image = new Image(imagePath);
        } catch (SlickException e) {
            throw new SlickException("Error loading image: " + imagePath);
        }
    }

    public Image getImage() {
        return image;
    }

}