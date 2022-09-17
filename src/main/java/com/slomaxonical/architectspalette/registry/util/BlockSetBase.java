package com.slomaxonical.architectspalette.registry.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.slomaxonical.architectspalette.registry.util.StoneBlockSet.SetComponent.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BlockSetBase {
    StoneBlockSet.SetComponent[] parts() default {SLAB,VERTICAL_SLAB,STAIRS,WALL};
}