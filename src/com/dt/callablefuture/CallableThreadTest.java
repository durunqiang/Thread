package com.dt.callablefuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
*  ͨ�� Callable �� Future �����߳�
1. ���� Callable �ӿڵ�ʵ���࣬��ʵ�� call() �������� call() ��������Ϊ�߳�ִ���壬�����з���ֵ��
2. ���� Callable ʵ�����ʵ����ʹ�� FutureTask ������װ Callable ���󣬸� FutureTask �����װ�˸� Callable ����� call() �����ķ���ֵ��
3. ʹ�� FutureTask ������Ϊ Thread ����� target �������������̡߳�
4. ���� FutureTask ����� get() ������������߳�ִ�н�����ķ���ֵ��
*/
public class CallableThreadTest implements Callable<Integer> {
	
    public static void main(String[] args)  
    {  
        CallableThreadTest ctt = new CallableThreadTest();  
        FutureTask<Integer> ft = new FutureTask<>(ctt);  
        for(int i = 0;i < 100;i++)  
        {  
            System.out.println(Thread.currentThread().getName()+" ��ѭ������i��ֵ"+i);  
            if(i==20)  
            {  
                new Thread(ft,"�з���ֵ���߳�").start();  
            }  
        }  
        try
        {  
            System.out.println("���̵߳ķ���ֵ��"+ft.get());
        } catch (InterruptedException e)  
        {  
            e.printStackTrace();  
        } catch (ExecutionException e)  
        {  
            e.printStackTrace();
        }  
    }
    
    @Override
    public Integer call() throws Exception  
    {  
        int i = 0;  
        for(;i<100;i++)  
        {  
            System.out.println(Thread.currentThread().getName()+" "+i);  
        }  
        return i;  
    }
    
}

