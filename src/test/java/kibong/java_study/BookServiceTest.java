package kibong.java_study;

import kibong.java_study.Proxy.DefaultProxyBookService;
import kibong.java_study.Proxy.ProxyBook;
import kibong.java_study.Proxy.ProxyBookService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class BookServiceTest {

    //ProxyBookService proxyBookService = new BookServiceProxy(new DefaultProxyBookService());

    @Test
    public void di() {
        ProxyBook proxyBook = new ProxyBook();
        proxyBook.setTitle("kibong");
        //proxyBookService.rent(proxyBook);
    }
}
