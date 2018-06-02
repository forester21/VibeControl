package web;

import app.Main;
import app.Player;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FORESTER on 05.05.18.
 */
@Path("/")
public class EntryPoint {

    @GET
    @Path("player")
    @Produces(MediaType.TEXT_HTML)
    public String test() throws Exception {
        return Util.getHtmlPageAsString("index.html");

    }

    @GET
    @Path("getTracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Track[] getTracks() throws Exception {
        return Player.getTracks();
    }

    @POST
    @Path("play")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String play(int id) throws Exception {
        System.out.println(id);
        int previousTrackId = Player.getCurrentPlayingId();
        Player.play(id);
        return String.valueOf(previousTrackId);
    }

    @POST
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public Track[] search(String searchString){
        System.out.println(searchString);
        if (searchString==null){
            return null;
        }
        return Player.getTrackStartingWith(searchString.toUpperCase());
    }

    @GET
    @Path("lower")
    @Produces(MediaType.TEXT_PLAIN)
    public String volumeLower() throws Exception {
        return Main.volumeLower();
    }

    @GET
    @Path("higher")
    @Produces(MediaType.TEXT_PLAIN)
    public String volumeHigher() throws Exception {
        return Main.volumeHgher();
    }
}