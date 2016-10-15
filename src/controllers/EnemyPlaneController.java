package controllers;

import models.EnemyBullet;
import models.GameObject;
import utils.Utils;
import views.GameView;

import java.awt.*;

/**
 * Created by Nghia on 10/11/2016.
 */
public class EnemyPlaneController extends GameSingleController {
    private static final int SPEED = 1;
    ControllerManager bulletControllers;
    private int count;
    private boolean beRemoved;
    public static final int NEW_BULLET_POINT=10;
    public EnemyPlaneController(GameObject gameObject, GameView gameView) {
        super(gameObject, gameView);
        count=0;
        bulletControllers=new ControllerManager();
        beRemoved=false;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        bulletControllers.draw(g);
    }

    @Override
    public void run() {
        fly();
        count++;
        bulletControllers.run();
        if (count== NEW_BULLET_POINT){
            count=0;
            newBullet();
        }

        if (gameObject.getY()>600)
            beRemoved=true;


    }

    public void fly() {
        gameObject.move(0,SPEED);
    }

    public void newBullet(){
        bulletControllers.add(new EnemyBulletController(
                new EnemyBullet(gameObject.getX(),gameObject.getY()),
                new GameView(Utils.loadImageFromRes("bullet.png"))
        ));
    }

    public boolean shouldBeRemoved(){
        return beRemoved;
    }



}
