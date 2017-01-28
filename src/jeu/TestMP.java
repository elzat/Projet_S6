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
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

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
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {

            for (int j = 0; j < 10; j++) {
                int finalI = i;
                int finalJ = j;
                boolean franchissable = rand.nextInt(100) < 50;
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
            BoundingBox limite = new BoundingBox(finalXLibre * 16, finalYLibre * 16, 8, 8);


            @Override
            public Point2D getVitesse() {
                return vitesse;
            }

            @Override
            public void setVitesse(Point2D v) {
                vitesse = v;
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

        Canvas canvs = new Canvas(200, 200);

        primaryStage.setScene(new Scene(new Group(canvs)));

        canvs.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case Z:
                        perso.setVitesse(0, -4);
                        break;

                    case S:
                        perso.setVitesse(0, 4);
                        break;

                    case Q:
                        perso.setVitesse(-4, 0);
                        break;

                    case D:
                        perso.setVitesse(4, 0);
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
                draw(canvs.getGraphicsContext2D(), objets);
                draw(canvs.getGraphicsContext2D(), mobile);
                //System.out.println(perso.getVitesse());
            }
        };


        at.start();
        canvs.requestFocus();
        primaryStage.show();

    }

    private void draw(GraphicsContext context, Iterable<? extends Objet> obj) {
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
            context.fillRect(limite.getMinX(), limite.getMinY(), limite.getWidth(), limite.getHeight());
            context.strokeRect(limite.getMinX(), limite.getMinY(), limite.getWidth(), limite.getHeight());
        }
    }

}



