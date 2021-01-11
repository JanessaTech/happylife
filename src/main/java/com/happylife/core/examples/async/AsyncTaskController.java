package com.happylife.core.examples.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

@RequestMapping("/tuoke-web/api/examples/asyn")
@RestController
public class AsyncTaskController {

    @Autowired
    private AsyncTask asyncTask;

    @GetMapping
    public String doTask() throws InterruptedException{
        long currentTimeMillis = System.currentTimeMillis();
        Future<String> future1 = asyncTask.task1();
        Future<String> future2 = asyncTask.task2();
        Future<String> future3 = asyncTask.task3();
        String result = null;
        /*for (;;) {
            if(task1.isDone() && task2.isDone() && task3.isDone()) {
                // 三个任务都调用完成，退出循环等待
                break;
            }
            Thread.sleep(1000);
        }*/
        long currentTimeMillis1 = System.currentTimeMillis();
        result = "Total cost :"+(currentTimeMillis1-currentTimeMillis)+"ms";
        return result;
    }
}
