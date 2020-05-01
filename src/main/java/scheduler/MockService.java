package scheduler;

import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author chenjiena
 * @version 1.0
 * @created 2020/5/1.
 */

@Service
public class MockService {

    public void mockMethod(){
        System.out.println("模拟数据库访问"+ new Date());
    }

}
