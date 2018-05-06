package app;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.comparator.NameFileComparator;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import sun.applet.*;
import web.Track;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by FORESTER on 06.05.18.
 */
public class Player {

    private final static List<File> allMusicFiles;
    private static int counter = 0;

    static{
        File rootDir = new File("/Users/FORESTER/Desktop/MUZIK");
        Collection<File> allMusicFilesCollection = FileUtils.listFiles(rootDir, FileFilterUtils.suffixFileFilter(".mp3"), TrueFileFilter.INSTANCE);
        allMusicFiles = new ArrayList<>(allMusicFilesCollection);
        allMusicFiles.sort(new NameFileComparator());
    }

    public static List<Track> getTracks() {
        List<Track> tracks =  new ArrayList<>(10);
        convertFilesToTracks(tracks, allMusicFiles.subList(counter, counter + 20));
        counter = counter + 20;
        return tracks;
    }

    private static void convertFilesToTracks(List<Track> tracks, List<File> trackFiles){
        for (File trackFile : trackFiles){
            tracks.add(new Track(trackFile.getName()));
        }
    }

    public static void play(String name) {
        for (File trackFile : allMusicFiles){
            if (name.equals(trackFile.getName())){
                Main.play(trackFile);
                break;
            }
        }
    }
}
