package com.slomaxonical.architectspalette.registry.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ChangeGroup {
    int value() default 1;
    //0 building
    //1 deco
    //2 redstone
    //3 transport
    //6 misc
    //7 food
    //8 tools
}
