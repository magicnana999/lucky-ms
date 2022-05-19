package com.creolophus.lucky.common.web;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;

/**
 * 朝辞白帝彩云间 千行代码一日还 两岸领导啼不住 地铁已到回龙观
 *
 * @author magicnana
 * @date 2019/9/18 下午2:05
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Api {

  String SCOPE_PUBLIC = "PUBLIC";
  String SCOPE_INTERNAL = "INTERNAL";

  String INTERNAL_HEADER_KEY = "X-LiuYi-Inter";
  String INTERNAL_HEADER_VAL = "BMW525LIBENZGLE4504MATICPANAMERA";

  Api INTERNAL_API =
      new Api() {
        @Override
        public Class<? extends Annotation> annotationType() {
          return Api.class;
        }

        @Override
        public String auth() {
          return "";
        }

        @Override
        public String scope() {
          return SCOPE_INTERNAL;
        }

        @Override
        public String encrypt() {
          return "";
        }

        @Override
        public String decrypt() {
          return "";
        }

      };

  Api PUBLIC_API =
      new Api() {
        @Override
        public Class<? extends Annotation> annotationType() {
          return Api.class;
        }

        @Override
        public String auth() {
          return "";
        }

        @Override
        public String scope() {
          return SCOPE_PUBLIC;
        }

        @Override
        public String encrypt() {
          return "";
        }

        @Override
        public String decrypt() {
          return "";
        }

      };

  String auth() default "";

  String scope() default SCOPE_PUBLIC;

  String encrypt() default "";

  String decrypt() default "";


}
