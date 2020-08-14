package com.github.appundefined.annotation;

import java.lang.annotation.*;

@Target(value= {ElementType.FIELD})
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
public @interface TreeElement {
    String name();
}
