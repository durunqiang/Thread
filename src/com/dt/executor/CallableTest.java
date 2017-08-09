package com.dt.executor;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * V1.1
 * ʵ��Callable�ӿڣ���дcall����
 * Callable��������Runnable�Ľӿڣ�ʵ��Callable�ӿڵ����ʵ��Runnable���඼�ǿɱ������߳�ִ�е�����
 * Callable��Runnable�м��㲻ͬ:
 * 1��Callable�涨�ķ�����call()����Runnable�涨�ķ�����run().
 * 2��Callable������ִ�к�ɷ���ֵ����Runnable�������ǲ��ܷ���ֵ��
 * 3��call()�������׳��쳣����run()�����ǲ����׳��쳣�ġ�
 * 4������Callable������õ�һ��Future����Future��ʾ�첽����Ľ�������ṩ�˼������Ƿ���ɵķ���,�Ե�
 * ����������,����������Ľ��.ͨ��Future������˽�����ִ�����,��ȡ�������ִ��,���ɻ�ȡ����ִ�еĽ��
 */
class TaskWithResult implements Callable<String> {  
    private int id;  
  
    public TaskWithResult(int id) {  
        this.id = id;  
    }  
  
    @Override  
    public String call() throws Exception {  
        return "result of TaskWithResult " + id;  
    }  
}  
  
public class CallableTest {  
	
    public static void main(String[] args) throws InterruptedException, ExecutionException {  
        ExecutorService exec = Executors.newCachedThreadPool();  
        ArrayList<Future<String>> results = new ArrayList<Future<String>>();    //Future �൱�����������Executorִ�еĽ����һ������ 
        
        for (int i = 0; i < 10; i++) {  
            results.add(exec.submit(new TaskWithResult(i)));  
        }  
        for (Future<String> fs : results) {  
            if (fs.isDone()) {  
                System.out.println(fs.get());  
            } else {  
                System.out.println("Future result is not yet complete");  
            }  
        }  
        exec.shutdown();  
    }  
}  
