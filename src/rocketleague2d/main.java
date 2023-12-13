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
    private Equipe orange;
    private Equipe bleu;

    public main(String name) {
        super(name);
    }

    // Initialise les textures
    public void initTexture() throws SlickException {
        backgroundTexture = new texture("background.png");
        kartTexture = new texture("kart.png", 104, 64);
    }

    // Initialise le jeu
    @Override
    public void init(GameContainer gc) throws SlickException {
        initTexture();

        // Initialise les karts pour les deux équipes
        orange = new Equipe("Orange");
        bleu = new Equipe("Bleu");

        // Initialise les positions initiales des karts (vous pouvez ajuster cela selon vos besoins)
        orange.setPositionX(500.0);
        orange.setPositionY(500.0);

        bleu.setPositionX(600.0);
        bleu.setPositionY(500.0);
    }

    // Met à jour l'état du jeu
    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        Input input = gc.getInput();

        // Quitte le jeu si la touche Échap est enfoncée
        if (input.isKeyDown(Input.KEY_ESCAPE)) {
            System.exit(0);
        }

        // Gestion des touches pour le kart de l'équipe 1
        if (input.isKeyDown(Input.KEY_LEFT)) {
            orange.turnLeft();
        } else if (input.isKeyDown(Input.KEY_RIGHT)) {
            orange.turnRight();
        }

        if (input.isKeyDown(Input.KEY_UP)) {
            orange.accelerate();
        } else {
            orange.decelerate();
        }

        // Gestion des touches pour le kart de l'équipe 2
        if (input.isKeyDown(Input.KEY_A)) {
            bleu.turnLeft();
        } else if (input.isKeyDown(Input.KEY_D)) {
            bleu.turnRight();
        }

        if (input.isKeyDown(Input.KEY_W)) {
            bleu.accelerate();
        } else {
            bleu.decelerate();
        }

        // Mise à jour des karts
        orange.update();
        bleu.update();
    }

    // Dessine les éléments graphiques du jeu
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        // Dessine le fond
        backgroundTexture.getImage().draw(0, 0, gc.getWidth(), gc.getHeight());

        // Dessine les karts des deux équipes
        dessinerKart(orange);
        dessinerKart(bleu);

        // Réinitialise la rotation pour les éléments suivants
        kartTexture.getImage().setRotation(0.0f);

        // Affiche le titre du jeu en haut de l'écran
        g.drawString("RocketLeague 2D", 1920 - 150, 10);
    }

    // Méthode pour dessiner un kart
    private void dessinerKart(Equipe kart) {
        kartTexture.getImage().setCenterOfRotation(kartTexture.getImage().getWidth() / 2, kartTexture.getImage().getHeight() / 2);
        kartTexture.getImage().setRotation((float)kart.getRotation());
        kartTexture.getImage().drawCentered((float)kart.getPositionX(), (float)kart.getPositionY());
    }

    // Point d'entrée principal du programme
    public static void main(String[] args) throws SlickException {
        // Crée une instance du jeu
        Game game = new main("RocketLeague 2D");

        // Crée une fenêtre de jeu
        AppGameContainer appGameContainer = new AppGameContainer(game);
        appGameContainer.setDisplayMode(1920, 1080, true);

        // Lance le jeu
        appGameContainer.start();
    }
}