package rocketleague2d.release;

import java.awt.FontFormatException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Font;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import rocketleague2d.release.GameObject.Ball;
import rocketleague2d.release.GameObject.Terrain;
import rocketleague2d.release.GameObject.Equipe;

public class Rocketleague2dRelease extends BasicGame{

    public static Terrain terrain;
    
    private TrueTypeFont trueTypeFont;
    
    public static Ball ball;
    
    static int FPS = 50;
    int FPStoSegond = 0;
    int Segond = 0;
    int Minute = 2;
    
    public boolean TimeOut = false;
    
    public static Equipe equipe_blue;
    public static Equipe equipe_orange;
    
    public static void main(String[] args) throws SlickException {        
        // Crée une instance du jeu
        Game game = new Rocketleague2dRelease("RocketLeague 2D");

        // Crée une fenêtre de jeu
        AppGameContainer appGameContainer = new AppGameContainer(game);
        appGameContainer.setDisplayMode(1920, 1080, true);
        appGameContainer.setTargetFrameRate(FPS);

        // Lance le jeu
        appGameContainer.start();
    }

    public Rocketleague2dRelease(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        terrain = new Terrain("background.png", 0, 0, gc.getWidth(), gc.getHeight());
    
        ball = new Ball("RLball.png", gc, 128);
    
        equipe_blue = new Equipe("blue", 50, 50, 104, 64, 0, gc.getHeight()/2);
        equipe_orange = new Equipe("orange", 50, 50, 104, 64, gc.getWidth(),gc.getHeight()/2);
        
        // CLASS FONT
        try {
            // Replace "path/to/your/font.ttf" with the actual path to your TTF file
            String fontPath = "font/Planer-Regular-webfont.ttf";

            // Load the TTF file using FileInputStream
            InputStream inputStream = new FileInputStream(new File(fontPath));

            // Create a font object
            java.awt.Font awtFont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, inputStream);

            // Set the font size and create the TrueTypeFont
            awtFont = awtFont.deriveFont(32f); // Adjust the font size as needed
            trueTypeFont = new TrueTypeFont(awtFont, false);
        } catch (FileNotFoundException e) {
        } catch (FontFormatException ex) {
        } catch (IOException ex) {
        }
        // CLASS FONT
        
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
        
        if(equipe_orange.cage.Collision(ball.position_x, ball.position_y)){
            equipe_orange.score++;
            ball.Reset();
            
        }
        if(equipe_blue.cage.Collision(ball.position_x, ball.position_y)){
            equipe_blue.score++;
            ball.Reset();
        }

    }

    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {
        terrain.Draw();
        ball.Draw();
        equipe_blue.kart.Draw();
        equipe_orange.kart.Draw();
        
         trueTypeFont.drawString((gc.getWidth()/2) - 180, 10, " Bleu :  " + equipe_blue.score + "            Orange : " + equipe_orange.score);
                  
         // CLASS TIME BY DRAW
         if(FPStoSegond == FPS){
             FPStoSegond = 0;
             if(Segond != 0){
               Segond -= 1;
             }
             else{
                if(Minute != 0){
                  Segond = 59;
                  Minute -= 1;
                }
                else{
                  TimeOut = true;                
                }
             }
         }
         else{
             FPStoSegond += 1;
         }
         // CLASS TIME BY DRAW

         if(Segond <= 9){
           trueTypeFont.drawString((gc.getWidth()/2) - 35, 10, "0" + Minute + ":" + "0" + Segond);             
         }
         else{
           trueTypeFont.drawString((gc.getWidth()/2) - 35, 10, "0" + Minute + ":" + Segond);                          
         }
         
         
    }
    
}
