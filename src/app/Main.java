package app;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import web.Server;

import java.io.File;

/**
 * Created by FORESTER on 05.05.18.
 */
public class Main extends Application {

    public static volatile String currentPlaying = "";

    static MediaPlayer currentMediaPlayer;
    static MediaPlayer followingMediaPlayer;


    public static void main(String[] args) {
        launch();
    }

    public static void play(File track) {
        Media hit = new Media(track.toURI().toString());
        followingMediaPlayer = new MediaPlayer(hit);
        if (currentMediaPlayer != null && currentMediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            currentMediaPlayer.stop();
        }
        followingMediaPlayer.play();
        currentMediaPlayer = followingMediaPlayer;
    }

    public void start(Stage primaryStage) throws Exception {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Server.startServer();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
