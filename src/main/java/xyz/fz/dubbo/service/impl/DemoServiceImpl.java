package xyz.fz.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import xyz.fz.dubbo.service.DemoService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service(interfaceName = "xyz.fz.dubbo.service.DemoService", version = "1.0.0")
public class DemoServiceImpl implements DemoService {

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

    @Override
    @Transactional
    public String sayTxHello(String name) {
        String sql = "insert into t_test values(:name) ";
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        namedParameterJdbcTemplate.update(sql, params);
        int a = 1/0;
        Map<String, Object> params2 = new HashMap<>();
        params2.put("name", name + "a");
        namedParameterJdbcTemplate.update(sql, params2);
        return name;
    }

    @Override
    public void record(String no) {
        String sql = "insert into t_test(no) values(:no) ";
        Map<String, Object> params = new HashMap<>();
        params.put("no", no);
        namedParameterJdbcTemplate.update(sql, params);
    }
}
