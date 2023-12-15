package rocketleague2d.release.GameObject;

import org.newdawn.slick.SlickException;

public class Equipe {
    
    private String nom;
    public Kart kart;
    public Cage cage;
    
    public Equipe(String nomEquipe, float init_position_x, float init_position_y, int init_size_x, int init_size_y) throws SlickException{
        this.nom = nomEquipe;
        this.kart = new Kart("Car_" + nomEquipe + ".png", init_position_x, init_position_y, init_size_x, init_size_y);
        this.cage = new Cage();
    }
    
}
