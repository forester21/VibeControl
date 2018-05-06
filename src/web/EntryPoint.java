package web;

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
    public List<Track> getTracks() throws Exception {
        return Player.getTracks();
    }

    @POST
    @Path("play")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String play(String message) throws Exception {
        System.out.println(message);
        Player.play(message);
        return "it's OK";
    }
}