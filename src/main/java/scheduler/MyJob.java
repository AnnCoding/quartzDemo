package scheduler;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author chenjiena
 * @version 1.0
 * @created 2020/5/1.
 */
public class MyJob {

    @Resource
    private MockService mockService;

    public void mockCallService(){
        mockService.mockMethod();
    }
}
