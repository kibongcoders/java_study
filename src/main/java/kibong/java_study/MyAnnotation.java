package kibong.java_study;

import java.lang.annotation.*;

@Retention(value = RetentionPolicy.RUNTIME) //언제까지 유지 할 것인가
@Target({
        ElementType.METHOD,
        ElementType.TYPE,
        ElementType.FIELD,
        ElementType.CONSTRUCTOR,
        ElementType.ANNOTATION_TYPE,
}) //Annotation 범위 지정
@Inherited //하위클래스 전달 지정
public @interface MyAnnotation {

    String name() default "kibong";

    int age() default 28;
}
