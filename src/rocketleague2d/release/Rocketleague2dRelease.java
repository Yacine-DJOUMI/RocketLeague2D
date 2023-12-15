package rocketleague2d.release;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import rocketleague2d.release.GameObject.Ball;
import rocketleague2d.release.GameObject.Boost;
import rocketleague2d.release.GameObject.Terrain;
import rocketleague2d.release.GameObject.Equipe;

public class Rocketleague2dRelease extends BasicGame{

    public static Terrain terrain;
    public static Boost boost;
    
    public static Ball ball;
    
    public static Equipe equipe_blue;
    public static Equipe equipe_orange;
    
    public static void main(String[] args) throws SlickException {        
        // Crée une instance du jeu
        Game game = new Rocketleague2dRelease("RocketLeague 2D");

        // Crée une fenêtre de jeu
        AppGameContainer appGameContainer = new AppGameContainer(game);
        appGameContainer.setDisplayMode(1920, 1080, true);
        appGameContainer.setTargetFrameRate(50);

        // Lance le jeu
        appGameContainer.start();
    }

    public Rocketleague2dRelease(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        terrain = new Terrain("background.png", 0, 0, gc.getWidth(), gc.getHeight());
        boost = new Boost("background.png", 200, 200, 64, 64);
    
        ball = new Ball("RLball.png", gc, 128);
    
        equipe_blue = new Equipe("blue", 50, 50, 104, 64);
        equipe_orange = new Equipe("orange", 50, 50, 104, 64);
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        Input input = gc.getInput();

        // Quitte le jeu si la touche Échap est enfoncée
        if (input.isKeyDown(Input.KEY_ESCAPE)) {
            System.exit(0);
        }

        
        // Gestion des touches pour le kart de l'équipe 1
        if(input.isKeyDown(Input.KEY_LEFT)){
            equipe_orange.kart.turnLeft();
        }else if(input.isKeyDown(Input.KEY_RIGHT)){
            equipe_orange.kart.turnRight();
        }
        if(input.isKeyDown(Input.KEY_UP)){
            equipe_orange.kart.accelerate();
        }else{
            equipe_orange.kart.decelerate();
        }
        equipe_orange.kart.update();
        equipe_orange.kart.collision(gc.getWidth(), gc.getHeight());
        ball.update(equipe_orange.kart.position_x, equipe_orange.kart.position_y, equipe_orange.kart.velocity_x, equipe_orange.kart.velocity_y);

        
        // Gestion des touches pour le kart de l'équipe 2
        if(input.isKeyDown(Input.KEY_A)){
            equipe_blue.kart.turnLeft();
        }else if(input.isKeyDown(Input.KEY_D)){
            equipe_blue.kart.turnRight();
        }
        if(input.isKeyDown(Input.KEY_W)){
            equipe_blue.kart.accelerate();
        }else{
            equipe_blue.kart.decelerate();
        }        
        equipe_blue.kart.update();
        equipe_blue.kart.collision(gc.getWidth(), gc.getHeight());
        ball.update(equipe_blue.kart.position_x, equipe_blue.kart.position_y, equipe_blue.kart.velocity_x, equipe_blue.kart.velocity_y);

        
        if(input.isKeyDown(Input.KEY_R)){
            ball.Reset();
        }
        
        
        ball.collision(gc.getWidth(), gc.getHeight());

    }

    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {
        terrain.Draw();
        ball.Draw();
        equipe_blue.kart.Draw();
        equipe_orange.kart.Draw();
    }
    
}
