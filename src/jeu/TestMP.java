package jeu;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by crede on 28/01/2017.
 */
public class TestMP extends Application {

    public static void main() {
        launch();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {


        double xLibre = 0, yLibre = 0;

        LinkedList<Objet> objets = new LinkedList<>();

        boolean[][] tab =
                {{false, false, false, false, false, false, false, false},
                {false, true, false, true, true, true, true, false},
                {false, true, false, true, false, false, true, false},
                {false, true, false, true, false, true, true, false},
                {false, true, false, true, false, false, true, false},
                {false, true, false, true, true, true, true, false},
                {false, true, false, true, false, false, true, false},
                {false, true, false, true, false, false, true, false},
                {false, true, true, true, false, true, true, false},
                {false, false, false, false, false, false, false, false}};

        for (int i = 0; i < tab.length; i++) {

            for (int j = 0; j < tab[i].length; j++) {
                int finalI = i;
                int finalJ = j;
                boolean franchissable = tab[i][j];
                if (franchissable) {
                    xLibre = i;
                    yLibre = j;
                }
                objets.add(new Objet() {

                    BoundingBox limite = new BoundingBox(finalI * 16, finalJ * 16, 16, 16);


                    @Override
                    public Point2D getPosition() {

                        return new Point2D(limite.getMinX(), limite.getMinY());
                    }

                    @Override
                    public Bounds getLimite() {
                        return limite;
                    }

                    @Override
                    public boolean isFranchissable() {
                        return franchissable;
                    }
                });
            }
        }


        LinkedList<Objet_Mobile> mobile = new LinkedList<>();
        double finalXLibre = xLibre;
        double finalYLibre = yLibre;
        Objet_Mobile perso = new Objet_Mobile() {

            Point2D vitesse = new Point2D(0, 0);
            BoundingBox limite = new BoundingBox(finalXLibre * 16, finalYLibre * 16, 14, 14);


            @Override
            public Point2D getVitesse() {
                return vitesse;
            }

            @Override
            public void setVitesse(Point2D v) {


                vitesse = v;
                System.err.println(vitesse);
            }

            @Override
            public void setVitesse(double vx, double vy) {
                setVitesse(new Point2D(vx, vy));
            }

            @Override
            public void setPosition(double x, double y) {
                setPosition(new Point2D(x, y));
            }

            @Override
            public Point2D getPosition() {
                return new Point2D(limite.getMinX(), limite.getMinY());
            }

            @Override
            public void setPosition(Point2D p) {
                limite = new BoundingBox(p.getX(), p.getY(), limite.getWidth(), limite.getHeight());
            }

            @Override
            public Bounds getLimite() {
                return limite;
            }

            @Override
            public boolean isFranchissable() {
                return false;
            }
        };

        mobile.add(perso);

        Canvas canvs = new Canvas(500, 500);

        primaryStage.setScene(new Scene(new Group(canvs)));

        canvs.heightProperty().bind(primaryStage.heightProperty());
        canvs.widthProperty().bind(primaryStage.widthProperty());

        canvs.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case Z:
                        System.err.println(KeyCode.Z);
                        perso.setVitesse(perso.getVitesse().add(0, -1));
                        break;

                    case S:

                        System.err.println(KeyCode.S);
                        perso.setVitesse(perso.getVitesse().add(0, 1));
                        break;

                    case Q:

                        System.err.println(KeyCode.Q);
                        perso.setVitesse(perso.getVitesse().add(-1, 0));
                        break;

                    case D:

                        System.err.println(KeyCode.D);
                        perso.setVitesse(perso.getVitesse().add(1, 0));
                        break;

                    case SPACE:
                        perso.setVitesse(0, 0);
                }
            }
        });

        ScheduledService<Void> SS = new ScheduledService<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        Moteur_Physique.deplacement(objets, mobile);
                        return null;
                    }
                };
            }
        };
        SS.setPeriod(Duration.millis(240));
        SS.start();


        AnimationTimer at = new AnimationTimer() {
            @Override
            public void handle(long now) {
                draw(canvs, objets);
                draw(canvs, mobile);
                //System.out.println(perso.getVitesse());
            }
        };


        at.start();
        canvs.requestFocus();
        primaryStage.show();

    }

    private void draw(Canvas canvas, Iterable<? extends Objet> obj) {
        int ratio =2;
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.setStroke(Color.WHITE);
        for (Iterator<? extends Objet> iterator = obj.iterator(); iterator.hasNext(); ) {
            Objet next = iterator.next();
            if (next instanceof Objet_Mobile) {
                context.setFill(Color.GREEN);
            } else if (next.isFranchissable()) {
                context.setFill(Color.BLUE);
            } else {
                context.setFill(Color.RED);
            }
            Bounds limite = next.getLimite();
            context.fillRect(limite.getMinX()*ratio, limite.getMinY()*ratio, limite.getWidth()*ratio, limite.getHeight()*ratio);
            //context.strokeRect(limite.getMinX()*ratio, limite.getMinY()*ratio, limite.getWidth()*ratio, limite.getHeight()*ratio);
        }
    }

}



