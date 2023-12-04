package rocketleague2d;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class main extends BasicGame {

    private texture backgroundTexture;
    private texture kartTexture;

    private float kartX;
    private float kartY;

    public main(String name) {
        super(name);
    }

    
    
    public void initTexture() throws SlickException {
        backgroundTexture = new texture("background.png");
        kartTexture = new texture("kart.png");
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        initTexture();

        kartX = gc.getWidth() / 2 - kartTexture.getImage().getWidth() / 2;
        kartY = gc.getHeight() - kartTexture.getImage().getHeight();
    }
    
    
    
    @Override
    public void update(GameContainer gc, int delta) throws SlickException {

        Input input = gc.getInput();
        
        if (input.isKeyDown(Input.KEY_ESCAPE)){
            System.exit(0);
        }
        if (input.isKeyDown(Input.KEY_LEFT)) {
            kartX -= 0.2 * delta;
        }
        if (input.isKeyDown(Input.KEY_RIGHT)) {
            kartX += 0.2 * delta;
        }
        if (input.isKeyDown(Input.KEY_UP)) {
            kartY -= 0.2 * delta;
        }
        if (input.isKeyDown(Input.KEY_DOWN)) {
            kartY += 0.2 * delta;
        }

        // Ensure the kart stays within the screen bounds
        kartX = Math.max(0, Math.min(gc.getWidth() - kartTexture.getImage().getWidth(), kartX));
    }

    
    
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        // Draw the background
        backgroundTexture.getImage().draw(0, 0, gc.getWidth(), gc.getHeight());

        // Draw the kart
        kartTexture.getImage().draw(kartX, kartY, 64, 64);

        // Draw other game elements on top of the background and kart
        g.drawString("RocketLeague 2D", 1920-150, 10);
    }

    
    
    public static void main(String[] args) throws SlickException {
        Game _Game = new main("RocketLeague 2D");
        AppGameContainer _AppGameContainer = new AppGameContainer(_Game);
        _AppGameContainer.setDisplayMode(1920, 1080, true);
        _AppGameContainer.start();
    }

    
    
}