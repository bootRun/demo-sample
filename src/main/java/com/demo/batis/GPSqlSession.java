package com.demo.batis;

import java.util.Objects;

public class GPSqlSession {

    private GPConfigguration configguration;

    private GPExecutor executor;


    public GPSqlSession(GPConfigguration configguration, GPExecutor executor) {
        this.configguration = configguration;
        this.executor = executor;
    }

    public  <T> T selectOne(String statementId, Object parameter){

        String sql = GPConfigguration.sqlMappings.getString(statementId);
        if (sql == null || "".equals(sql)) {
            return null;
        }
        return executor.query(sql, parameter);
    }

    public <T> T getMapper(Class clazz) {
        return configguration.getMapper(clazz, this);
    }

}
