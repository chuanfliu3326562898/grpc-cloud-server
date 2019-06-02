package com.consul;

import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobinLoadbalanceService implements LoadbalanceService {

    private AtomicInteger roundRobin = new AtomicInteger(0);
    private static final int MAX_VALUE=1000;
    private static final int MIN_VALUE=1;

    private AtomicInteger getRoundRobinValue(){
        if(this.roundRobin.getAndAdd(1)>MAX_VALUE){
            this.roundRobin.set(MIN_VALUE);
        }
        return this.roundRobin;
    }

    @Override
    public int index(int size) {
        return  (this.getRoundRobinValue().get() + size) % size;
    }
}
