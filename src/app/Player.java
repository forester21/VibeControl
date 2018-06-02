package app;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.comparator.NameFileComparator;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import web.Track;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by FORESTER on 06.05.18.
 * <p>
 * In this class "counter" is the same as "id"
 */
public class Player {

    private final static ArrayList<File> allMusicFiles;
    private static int counter = 0;
    //number of tracks added after pushing "more tracks"
    private final static int MORE_TRACKS_DELTA = 20;
    private static Track[] tracks = new Track[20];
    private static int currentPlayingId = 0;

    static {
        // Fill with tracks from file system
        File rootDir = new File("/Users/FORESTER/Desktop/MUZIK");
        Collection<File> allMusicFilesCollection = FileUtils.listFiles(rootDir, FileFilterUtils.suffixFileFilter(".mp3"), TrueFileFilter.INSTANCE);
        allMusicFiles = new ArrayList<>(allMusicFilesCollection);
        allMusicFiles.sort(new NameFileComparator());
    }

    public static synchronized Track[] getTracks() {
        for (int i = 0; i < MORE_TRACKS_DELTA; i++) {
            tracks[i] = new Track(counter, allMusicFiles.get(counter).getName());
            counter++;
            checkIfCounterIsOverfilled();
        }
        System.out.println(counter);
        return tracks;
    }

    public static void play(int id) {
        currentPlayingId = id;
        Main.play(allMusicFiles.get(id));
    }

    public static Track[] getTrackStartingWith(String searchString) {
        boolean tracksFound = false;
        for (int i = 0; i < allMusicFiles.size(); i++) {
            if (allMusicFiles.get(i).getName().startsWith(searchString)){
                counter = i;
                tracksFound = true;
                break;
            }
        }
        if (tracksFound){
            return getTracks();
        }
        return null;
    }

    private static void checkIfCounterIsOverfilled(){
        if (counter == allMusicFiles.size()){
            counter = 0;
        }
    }

    public static int getCurrentPlayingId() {
        return currentPlayingId;
    }
}
