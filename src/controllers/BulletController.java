package controllers;

import models.GameObject;
import views.GameView;


/**
 * Created by apple on 10/9/16.
 */
public class BulletController extends GameSingleController {

    public static final int SPEED = 10;
    private boolean beRemoved ;
    public BulletController(GameObject gameObject, GameView gameView) {
        super(gameObject, gameView);
        beRemoved=false;
    }

    @Override
    public void run() {
        gameObject.move(0, -SPEED);
        if (gameObject.getY()<0)
            beRemoved=true;
    }

    @Override
    public boolean shouldBeRemoved (){
        return beRemoved;
    }
}
