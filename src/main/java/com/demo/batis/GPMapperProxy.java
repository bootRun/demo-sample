package com.demo.batis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class GPMapperProxy implements InvocationHandler {

    private GPSqlSession sqlSession;

    public GPMapperProxy(GPSqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String mapperInterface = method.getDeclaringClass().getName();
        String methodName = method.getName();

        String statementId = mapperInterface + "." + methodName;

        return sqlSession.selectOne(statementId, args[0]);
    }
}
