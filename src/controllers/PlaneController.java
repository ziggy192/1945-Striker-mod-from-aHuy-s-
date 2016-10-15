package controllers;

import models.Bullet;
import models.GameObject;
import utils.Utils;
import views.GameView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by asus on 10/9/2016.
 */
public class PlaneController extends GameSingleController {
    private static final int SHOOT_DURATION = 20;

    private int dx;
    private int dy;
    public static final int SPEED = 10;

    //private Vector<BulletController> bulletControllers;
    ControllerManager bulletControllers;

    public PlaneController(GameObject gameObject, GameView gameView) {
        super(gameObject, gameView);
        bulletControllers = new ControllerManager();
    }


    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                dx = SPEED;
                break;
            case KeyEvent.VK_LEFT:
                dx = -SPEED;
                break;
            case KeyEvent.VK_UP:
                dy = -SPEED;
                break;
            case KeyEvent.VK_DOWN:
                dy = SPEED;
                break;
            case KeyEvent.VK_SPACE:
                createBullet();
                break;

        }
    }

    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_LEFT:
                dx = 0;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                dy = 0;
                break;

        }
    }

    @Override

    public void draw(Graphics g) {
        super.draw(g);
        bulletControllers.draw(g);


    }

    private int count;

    public ControllerManager getBulletControllers() {
        return bulletControllers;
    }

    @Override
    public void run() {

        // update model
        gameObject.move(dx, dy);
        bulletControllers.run();

    }

    private void createBullet() {
        BulletController bulletController = new BulletController(
                new Bullet(gameObject.getX(), gameObject.getY()),
                new GameView(Utils.loadImageFromRes("bullet.png"))
        );
        bulletControllers.add(bulletController);
    }



    public void mouseMoved(MouseEvent e) {
        gameObject.moveTo(e.getX() ,e.getY() );

    }

    public void mouseClicked(MouseEvent e) {
        createBullet();
    }
}
