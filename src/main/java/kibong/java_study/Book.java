package kibong.java_study;

import lombok.Data;

@MyAnnotation(name = "bong", age = 29)
@Data
public class Book {

    public static String A = "A";

    private String B = "BOOK";

    private static final String C = "BOOK";

    @MyAnnotation
    public String d = "d";

    @MyAnnotation(name = "e", age = 100)
    protected String e = "e";

    public Book() {
    }

    public Book(String b){
        this.B = b;
    }

    public Book(String a, String d, String e) {
        A = a;
        this.d = d;
        this.e = e;
    }


    private void f() {
        System.out.println("F");
    }
    @MyAnnotation
    public void g() {
        System.out.println("G");
    }

    public int h() {
        return 100;
    }

    public int sum(int one, int two){
        return one + two;
    }
}
