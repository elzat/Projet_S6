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
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by crede on 28/01/2017.
 */
public class TestMP extends Application {


    public static Image set = new Image(TestMP.class.getResourceAsStream("/jeu/image/tileset.png"));
    public static Image vide = new WritableImage(set.getPixelReader(), 0, 0, 16, 16);
    public static Image mur = new WritableImage(set.getPixelReader(), 2 * 16, 0, 16, 16);
    public static Image perso = new WritableImage(set.getPixelReader(), 1 * 16, 0, 16, 16);

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

                    BoundingBox limite = new BoundingBox(finalI * 64, finalJ * 64, 64, 64);


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


        Perso perso = new Perso() {

            Point2D vitesse = new Point2D(0, 0);
            BoundingBox limite = new BoundingBox(finalXLibre * 64 + 1, finalYLibre * 64 + 1, 32, 32);

            boolean go = false;
            Point2D toGo;

            @Override
            public Point2D getVitesse() {
                if (go) {
                    if (getPosition().distance(toGo) > 5)
                        setVitesse(toGo.subtract(getPosition()));
                    else {
                        stopGo();
                    }
                }


                return vitesse;
            }

            @Override
            public void setVitesse(Point2D v) {
                vitesse = v.normalize().multiply(5);
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

            public void Go(Point2D pos) {
                go = true;
                toGo = pos;
            }

            @Override
            public void stopGo() {

                if (go)
                    setVitesse(Point2D.ZERO);
                go = false;
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
                        //  System.err.println(KeyCode.Z);
                        perso.stopGo();
                        perso.setVitesse(perso.getVitesse().getX(), -1);
                        break;

                    case S:

                        //  System.err.println(KeyCode.S);
                        perso.stopGo();
                        perso.setVitesse(perso.getVitesse().getX(), 1);

                        break;

                    case Q:

                        // System.err.println(KeyCode.Q);
                        perso.stopGo();
                        perso.setVitesse(-1, perso.getVitesse().getY());

                        break;

                    case D:

                        //System.err.println(KeyCode.D);
                        perso.stopGo();
                        perso.setVitesse(1, perso.getVitesse().getY());

                        break;

                    case SPACE:
                        perso.stopGo();
                        perso.setVitesse(0, 0);

                }
            }
        });

        canvs.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case Z:
                    case S:

                        //System.err.println(event.getCode());
                        perso.setVitesse(perso.getVitesse().getX(), 0);
                        break;

                    case Q:
                    case D:

                        //System.err.println(event.getCode());
                        perso.setVitesse(0, perso.getVitesse().getY());
                        break;

                    case SPACE:
                        perso.setVitesse(0, 0);
                }

            }
        });


        canvs.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    Point2D pos = new Point2D(event.getX(), event.getY());
                    perso.Go(pos);
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
        SS.setPeriod(Duration.millis(25));
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
        int ratio = 1;
        Image img = vide;
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.setStroke(Color.BLACK);
        for (Iterator<? extends Objet> iterator = obj.iterator(); iterator.hasNext(); ) {
            Objet next = iterator.next();
            if (next instanceof Objet_Mobile) {
                context.setFill(Color.GREEN);
                img = perso;
            } else if (next.isFranchissable()) {
                context.setFill(Color.BLUE);
                img = vide;
            } else {
                context.setFill(Color.RED);
                img = mur;
            }
            Bounds limite = next.getLimite();


//context.drawImage(img,limite.getMinX(),limite.getMinY());
            context.fillRect(limite.getMinX() * ratio, limite.getMinY() * ratio, limite.getWidth() * ratio, limite.getHeight() * ratio);
            // context.strokeRect(limite.getMinX()*ratio, limite.getMinY()*ratio, limite.getWidth()*ratio, limite.getHeight()*ratio);
            // rayure(context, limite.getMinX() * ratio, limite.getMinY() * ratio, limite.getWidth() * ratio, limite.getHeight() * ratio);
        }
    }

    private void rayure(GraphicsContext graphicsContext, double x, double y, double l, double h) {
        graphicsContext.setLineWidth(3);
        for (int i = 0; i < l; i += 3) {
            if (graphicsContext.getStroke().equals(Color.BLACK)) {
                graphicsContext.setStroke(Color.WHITE);
            } else
                graphicsContext.setStroke(Color.BLACK);
            graphicsContext.strokeLine(x + i, y, x + i, y);
        }
    }


    private interface Perso extends Objet_Mobile {
        void Go(Point2D pos);

        void stopGo();
    }

}



