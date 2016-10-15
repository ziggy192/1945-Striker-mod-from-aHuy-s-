package controllers;
import java.awt.*;
import models.GameObject;
import views.GameView;

/**
 * Created by apple on 10/11/16.
 */
public class GameSingleController extends BaseController {
    private GameView gameView;
    protected GameObject gameObject;

    public GameSingleController(GameObject gameObject, GameView gameView) {
        this.gameView = gameView;
        this.gameObject = gameObject;
    }

    public void draw(Graphics g) {
        gameView.drawImage(g, gameObject);

    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public void run(){

    }
    public boolean shouldBeRemoved(){
        return true;
    }

}
