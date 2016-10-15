import controllers.*;
import models.EnemyBullet;
import models.EnemyPlane;
import models.Plane;
import utils.Utils;
import views.GameView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by apple on 10/2/16.
 */
public class GameWindow extends Frame implements Runnable {

    private static final int BACKGROUND_WIDTH = 600;
    private static final int BACKGROUND_HEIGHT = 400;

    Image backgroundImage = null;
    Image backBufferImage;

    PlaneController planeController;
    PlaneController planeController2;
//    Vector<EnemyPlaneController> enemyPlaneVector;
//    Vector<EnemyBullet> enemyBulletVector;
    ControllerManager enemyPlaneControllerManager;
    Vector<BaseController> controllers ;



    Vector<GameSingleController> gameSingleControllers;

    public GameWindow() {
        controllers= new Vector<>();
        gameSingleControllers = new Vector<>();
        planeController = new PlaneController(
                new Plane(BACKGROUND_WIDTH / 2, BACKGROUND_HEIGHT - Plane.PLANE_HEIGHT),
                new GameView(Utils.loadImageFromRes("plane3.png"))
        );

        planeController2 = new PlaneController(
                new Plane(BACKGROUND_WIDTH / 2, BACKGROUND_HEIGHT - Plane.PLANE_HEIGHT),
                new GameView(Utils.loadImageFromRes("plane4.png"))
        );
//
//        gameSingleControllers.add(planeController);
//        gameSingleControllers.add(planeController2);
        controllers.add(planeController);
        controllers.add(planeController2);

        enemyPlaneControllerManager = new ControllerManager();
        backBufferImage = new BufferedImage(BACKGROUND_WIDTH,
                BACKGROUND_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        controllers.add(enemyPlaneControllerManager);




        for (int i = 0; i < 10; i++) {
            int y = 60;
            int x = i * (EnemyPlane.ENEMY_PLANE_WIDTH + 5);
            enemyPlaneControllerManager.add(new EnemyPlaneController(
                    new EnemyPlane(x, y),
                    new GameView(Utils.loadImageFromRes("plane1.png"))
            ));
        }

        this.setVisible(true);
        this.setSize(BACKGROUND_WIDTH, BACKGROUND_HEIGHT);

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("windowOpened");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("windowClosing");
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("windowClosed");
            }

            @Override
            public void windowIconified(WindowEvent e) {
                System.out.println("windowIconified");
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                System.out.println("windowDeiconified");
            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                planeController2.mouseMoved(e);
            }
        });
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                planeController2.mouseClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                planeController.keyPressed(e);


            }

            @Override
            public void keyReleased(KeyEvent e) {
                planeController.keyReleased(e);
            }
        });

        try {
            backgroundImage = ImageIO.read(
                    new File("resources/background.png"));
            System.out.println("Loaded backgroundImage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("draw background image");
    }

    @Override
    public void update(Graphics g) {

        Graphics backBufferGraphics = backBufferImage.getGraphics();

        backBufferGraphics.drawImage(backgroundImage,
                0, 0,
                BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null);

//        for (GameSingleController gameSingleController : gameSingleControllers) {
//            gameSingleController.draw(backBufferGraphics);
//        }
//        enemyPlaneControllerManager.draw(backBufferGraphics);

        for (BaseController baseControllers : controllers) {
            baseControllers.draw(backBufferGraphics);
        }
        g.drawImage(backBufferImage,
                0, 0,
                BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null);
    }

    int count = 0;
    public void colisionEnemyPlane_Bullet(ControllerManager enemyPlaneControllerManager,
                                        ControllerManager planeBulletControllerManager ){
        GameSingleController removingController = enemyPlaneControllerManager.getColision(planeBulletControllerManager);
        if (removingController != null)  {
            System.out.println("COLLIDED!!!");
            enemyPlaneControllerManager.remove(removingController);
        }


    }
    public void checkColision(){
            colisionEnemyPlane_Bullet(enemyPlaneControllerManager,planeController.getBulletControllers());
            colisionEnemyPlane_Bullet(enemyPlaneControllerManager,planeController2.getBulletControllers());

    }
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(17);
                repaint();
                for (BaseController baseControllers : controllers) {
                    baseControllers.run();
                }
                checkColision();
//                enemyPlaneControllerManager.run();


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
