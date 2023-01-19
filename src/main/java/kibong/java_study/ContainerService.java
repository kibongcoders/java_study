package kibong.java_study;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ContainerService {

    public static <T> T getObject(Class<T> classType) {
        T instance = createInstance(classType); //인스턴스 만들기

        Arrays.stream(classType.getDeclaredFields()).forEach(field -> {
            if (field.getAnnotation(Inject.class) != null) { //Inject 클래스가 있을 경우에만 가져옴
                Object o = createInstance(field.getType());
                field.setAccessible(true);
                try {
                    field.set(instance, o); //있으면 해당 필드에 값 넣어줌
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return instance;
    }

    private static <T> T createInstance(Class<T> classType) {
        try {
            return classType.getConstructor((Class<?>) null).newInstance(); //기본 생성자
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException |
                 InvocationTargetException | NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }
}
