package kibong.java_study;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Stream;

@SpringBootTest
class JavaStudyApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void annotationTest() {

        //어노테이션 가지오기
        System.out.println("어노테이션 가지오기 Start");
        Arrays.stream(Book.class.getAnnotations()).forEach(System.out::println);

        //자식,부모 클래스에 있는 어노테이션 가져오기
        System.out.println("자식,부모 클래스에 있는 어노테이션 가져오기 Start");
        Arrays.stream(MyBook.class.getAnnotations()).forEach(System.out::println);

        //자식 클래스에만 있는 어노테이션 가져오기
        System.out.println("자식 클래스에만 있는 어노테이션 가져오기 Start");
        Arrays.stream(MyBook.class.getDeclaredAnnotations()).forEach(System.out::println);

        //필드의 어노테이션 가져오기
        System.out.println("필드의 어노테이션 가져오기 Start");
        Arrays.stream(Book.class.getDeclaredFields()).forEach(b -> {
            System.out.println(b);
            Arrays.stream(b.getAnnotations()).forEach(
                    annotation -> {
                        System.out.println(annotation);
                        MyAnnotation myAnnotation = (MyAnnotation) annotation;
                        System.out.println(myAnnotation.name());
                        System.out.println(myAnnotation.age());
                    }
            );
        });
    }

    @Test
    void modifyClass() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        Class<Book> bookClass = Book.class; //북 클래스 가져오기
        Constructor<Book> constructor = bookClass.getConstructor(String.class); //북클래스 생성자 가져오기 String 값이 있는 생성자 가져오기
        Book book = constructor.newInstance("B Book"); // 해당 생성자에 B Book 넣어주기
        System.out.println(book); //Book 출력

        Field a = Book.class.getDeclaredField("A"); //Book클래스의 A라는 필드 가져오기
        System.out.println(a.get(null)); //A의 값 가져오기 -> A는 스태틱한 변수이기 때문에 인스턴스를 넣어줄 필요가 없음
        a.set(null, "Hello"); //A 값 변경
        System.out.println(a.get(null)); //A 출력

        Field b = Book.class.getDeclaredField("B"); //Book클래스의 B라는 필드 가져오기
        b.setAccessible(true); //B는 private 이므로 접근 가능하도록 변경
        System.out.println(b.get(book)); //B는 스태틱이 아니므로 인스턴스가 필요하다.
        b.set(book, "Hello Book"); //B 값 변경
        System.out.println(b.get(book)); //변경된 B 값 변경

        Method f = Book.class.getDeclaredMethod("f"); //B클래스의 F라는 메소드 정보 가져오기
        f.setAccessible(true); // private 이므로 접근 가능하도록 변경
        f.invoke(book); //메소드 F 실행

        Method sum = Book.class.getDeclaredMethod("sum", int.class, int.class); //sum 이라는 메소드 정보가져오기 파라미터가 필요함
        System.out.println(sum.invoke(book, 1, 2)); //인스턴스 넘기고 파라미터 넘김
    }
}
