package com.seailz.atom.events.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * You must annotate any method that you want to be called when an event is fired with this annotation. This annotation
 * also allows you to provide a required channel id which will limit the method to only being called when a message is
 * received on that channel.
 *
 * @since 1.0.0
 * @see com.seailz.atom.Atom
 * @see com.seailz.atom.utils.Dispatcher
 * @author Seailz
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.METHOD})
public @interface AtomMethod {

    /**
     * You may specify a channel id which will limit the method to only running if the message is from that channel.
     *
     * <p>You can also include a wildcard character (*) anywhere in the channel id to allow for multiple channels to be
     * listened to. Or, you can even implement your own regex to match multiple channels. If you're doing that, please
     * mark ignoreWildcard() as true to avoid any issues.</p>
     */
    String channel() default "*";

    /**
     * If this is enabled, the wildcard character (*) will be ignored and the channel id will be treated as a regex.
     */
    boolean ignoreWildcard() default false;

}
