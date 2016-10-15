package controllers;

import models.GameObject;
import views.GameView;

import java.awt.*;

/**
 * Created by Nghia on 10/14/2016.
 */
public class EnemyBulletController extends GameSingleController {
    public EnemyBulletController(GameObject gameObject, GameView gameView) {
        super(gameObject, gameView);
        beRemoved=false;
    }

    public static final int SPEED = 10;
    private boolean beRemoved ;

    @Override
    public void run() {
        gameObject.move(0, SPEED);
        if (gameObject.getY()>800)
            beRemoved=true;

    }

    @Override
    public boolean shouldBeRemoved (){
        return beRemoved;
    }
}
