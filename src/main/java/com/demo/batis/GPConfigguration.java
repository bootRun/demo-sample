package com.demo.batis;

import java.lang.reflect.Proxy;
import java.util.ResourceBundle;

public class GPConfigguration {

    public static final ResourceBundle sqlMappings;

    static {
        sqlMappings = ResourceBundle.getBundle("mesql");
    }

    public <T> T getMapper(Class clazz, GPSqlSession sqlSession) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[] {clazz}, new GPMapperProxy(sqlSession));
    }


}
