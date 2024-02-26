package com.bloomscorp.aster;

import com.bloomscorp.bsb.BmxApplication;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@BmxApplication
@EnableAster
public @interface AsterApplication {
}
