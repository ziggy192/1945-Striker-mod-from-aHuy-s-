package controllers;

import models.GameObject;

import java.awt.*;
import java.util.Vector;

/**
 * Created by Nghia on 10/11/2016.
 */
public class ControllerManager extends BaseController {
//    public ControllerManager(GameObject gameObject, GameView gameView) {
//        super(gameObject, gameView);
//    }
    private Vector<GameSingleController> gameSingleControllers;


    public ControllerManager() {
        gameSingleControllers = new Vector<>();
    }


    public void add(GameSingleController gameSingleController){
        gameSingleControllers.add(gameSingleController);
    }
    public void remove(GameSingleController gameSingleController){
        gameSingleControllers.remove(gameSingleController);
    }


    public Vector<GameSingleController> getGameSingleControllers() {
        return gameSingleControllers;
    }

    @Override
    public void run() {

        Vector<GameSingleController> removingControllers = new Vector<>();
        for (GameSingleController controller : gameSingleControllers) {
            controller.run();

            if (controller.shouldBeRemoved()){
                removingControllers.add(controller);
            }

        }
        gameSingleControllers.removeAll(removingControllers);


    }

    @Override
    public void draw(Graphics graphics) {
        for (GameSingleController controller : gameSingleControllers) {
            controller.draw(graphics);

        }

    }

    public boolean checkColision(ControllerManager controllerManager){
        for (GameSingleController controller1: gameSingleControllers){
            for (GameSingleController controller2: controllerManager.getGameSingleControllers()){
                GameObject gameObject1 = controller1.getGameObject();
                GameObject gameObject2 = controller2.getGameObject();

                if (gameObject1.distanceTo(gameObject2)<= gameObject1.getRadius()+gameObject2.getRadius()){
                    return true;
                }
            }
        }



        return false;
    }

    public GameSingleController getColision(ControllerManager controllerManager){
        for (GameSingleController controller1: gameSingleControllers){
            for (GameSingleController controller2: controllerManager.getGameSingleControllers()){
                GameObject gameObject1 = controller1.getGameObject();
                GameObject gameObject2 = controller2.getGameObject();

                if (gameObject1.distanceTo(gameObject2)<= gameObject1.getRadius()+gameObject2.getRadius()){
                    return controller1;
                }
            }
        }
        return null;
    }




}
