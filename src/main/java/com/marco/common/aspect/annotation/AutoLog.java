package com.marco.common.aspect.annotation;

import java.lang.annotation.*;
/**
 * @author MarcoDuong
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
