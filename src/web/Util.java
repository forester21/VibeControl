package web;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import java.io.File;
import java.net.URI;

/**
 * Created by FORESTER on 05.05.18.
 */
public class Util {

    private final static String charsetName = "UTF-8";


    public static String getHtmlPageAsString(String pagePath) throws Exception {
        Document doc = Jsoup.parse(new File(getResourceURI(pagePath)), charsetName);
//        System.out.println("BEFORE:\n" + doc.outerHtml());

        // Replace each link nodes with its respective CSS file content
        for (Element link : doc.select("link[rel=stylesheet]")) {
            String cssFilename = link.attr("href");
            Element style = new Element(Tag.valueOf("style"), "");
            style.appendText("/* " + cssFilename + " */");
            style.appendText(getResAsString(cssFilename));
            link.replaceWith(style);
        }
//        System.out.println(doc.outerHtml());
        return doc.outerHtml();
    }

    private static URI getResourceURI(String resourceName) throws Exception{
        return Util.class.getResource(resourceName).toURI();
    }

    private static String getResAsString(String resourceName) throws Exception{
        return IOUtils.toString(getResourceURI(resourceName),charsetName);
    }
}
