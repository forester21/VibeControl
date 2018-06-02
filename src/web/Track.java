package web;

/**
 * Created by FORESTER on 06.05.18.
 */
public class Track {

    private String trackName;

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public Track(long id, String trackName) {
        this.trackName = trackName;
        this.id = id;
    }

    public Track() {
    }
}
