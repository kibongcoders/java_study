package kibong.java_study;

import kibong.java_study.Proxy.DefaultProxyBookService;
import kibong.java_study.Proxy.ProxyBook;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

public class DynamicClassProxyTest {

    @Test
    public void di() {
        MethodInterceptor handler = new MethodInterceptor() {
            final DefaultProxyBookService defaultProxyBookService = new DefaultProxyBookService();

            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println(" ");

                System.out.println("메소드 이름 : " + method.getName());

                //argument 출력
                for (Object arg : args) {
                    System.out.println(arg);
                }

                //rent의 경우에만 Start End 붙여줌
                if (method.getName().equals("rent")) {
                    System.out.println("Dynamic Proxy Class Start");
                    Object invoke = method.invoke(defaultProxyBookService, args);
                    System.out.println("Dynamic Proxy Class End");
                    return invoke;
                }

                return method.invoke(defaultProxyBookService, args);
            }
        };

        //Cglib Enhancer 생성
        DefaultProxyBookService defaultProxyBookService = (DefaultProxyBookService) Enhancer.create(DefaultProxyBookService.class, handler);

        ProxyBook proxyBook = new ProxyBook();
        proxyBook.setTitle("kibong");
        defaultProxyBookService.rent(proxyBook);
        defaultProxyBookService.returnBook(proxyBook);
        System.out.println(" ");
    }
}
