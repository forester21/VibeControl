package app;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import web.Server;

import java.io.File;
import java.math.BigDecimal;

/**
 * Created by FORESTER on 05.05.18.
 */
public class Main extends Application {

    static MediaPlayer currentMediaPlayer;
    static MediaPlayer followingMediaPlayer;
    private static final double VOLUME_DELTA = 0.1;

    public static void main(String[] args) {
        launch();
    }

    public static synchronized void play(File track) {
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

    public static synchronized String volumeHgher(){
        currentMediaPlayer.setVolume(currentMediaPlayer.getVolume()+VOLUME_DELTA);
        return String.valueOf(currentMediaPlayer.getVolume());
    }

    public static synchronized String volumeLower(){
        currentMediaPlayer.setVolume(currentMediaPlayer.getVolume()-VOLUME_DELTA);
        return String.valueOf(currentMediaPlayer.getVolume());
    }
}
