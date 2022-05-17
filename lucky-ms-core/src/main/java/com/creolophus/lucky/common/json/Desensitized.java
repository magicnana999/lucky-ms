package com.creolophus.lucky.common.json;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;

/**
 * 日志脱敏
 *
 * @author magicnana
 * @date 2022/3/10 14:17
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Desensitized {

  Sensitive type() default Sensitive.STAR;

  enum Sensitive {
    STAR,   //替换为******
    HIDE,   //替换为""
    SHORTEN,//替换为xxx...
    ;
  }
}
