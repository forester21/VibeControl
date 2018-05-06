package app;

import javafx.beans.NamedArg;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

/**
 * Created by FORESTER on 06.05.18.
 */
public class NewTrackEvent extends Event {
    public NewTrackEvent(@NamedArg("eventType") EventType<? extends Event> eventType) {
        super(eventType);
    }

    public NewTrackEvent(@NamedArg("source") Object source, @NamedArg("target") EventTarget target, @NamedArg("eventType") EventType<? extends Event> eventType) {
        super(source, target, eventType);
    }


}
