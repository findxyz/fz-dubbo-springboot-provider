package xyz.fz.dubbo.service;

public interface DemoService {
    String sayHello(String name);

    String sayTxHello(String name);

    void record(String no);
}
