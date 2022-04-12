package com.demo.metrics.collector;

public abstract class AbstractCollector<T extends CollectorConfig> {

    public AbstractCollector() {
    }

    public abstract void connect();

    public abstract void destroy();

    public abstract void collect() throws Exception;

    public T getCollector() {
        return null;
    }


}
