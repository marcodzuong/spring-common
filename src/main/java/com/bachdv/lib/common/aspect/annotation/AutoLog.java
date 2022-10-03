package com.bachdv.lib.common.aspect.annotation;

import java.lang.annotation.*;
/**
 * @author BachDV
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoLog {

    /**
     * log content
     */
    String value() default "";

}
