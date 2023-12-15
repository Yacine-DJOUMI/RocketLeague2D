package rocketleague2d.release.GameObject;

import org.newdawn.slick.SlickException;

public class Equipe {
    
    private String nom;
    public Kart kart;
    public Cage cage;
    public int score = 0;
    
    public Equipe(String nomEquipe, float init_position_x, float init_position_y, int init_size_x, int init_size_y,float cageX,float cageY,float rotation) throws SlickException{
        this.nom = nomEquipe;      
        this.kart = new Kart("Car_" + nomEquipe + ".png", init_position_x, init_position_y, init_size_x, init_size_y, rotation);
        this.cage = new Cage(cageX,cageY);
    }
    
    public int getScore(){
        return score;
    }
    
   
}
