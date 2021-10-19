package com.uliian.idGenerate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class EasyGenerator {
    private final CircleArray circleArray;
    private final int nodeId;
    private final long beginTime;
    private static Logger LOG = LoggerFactory.getLogger(EasyGenerator.class);

    public EasyGenerator(int nodeId, int timeWait) {
        this.nodeId = nodeId;
        this.circleArray = new CircleArray(timeWait);
        LocalDateTime of = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
        beginTime = of.toEpochSecond(ZoneOffset.UTC) * 1000;
    }

    public IdResult generateIdResult() {
        do {
            long timestamp = System.currentTimeMillis() - beginTime;
            timestamp = timestamp / 1000;

            long sequence = this.circleArray.generateSequence(timestamp);
            if (sequence < 1048574) {
                IdResult idresult = new IdResult(timestamp, sequence, this.nodeId);
                return idresult;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        } while (true);
    }

    @Override
    public String toString() {
        return "EasyGenerator{" +
                "circleArray=" + circleArray +
                ", nodeId=" + nodeId +
                ", beginTime=" + beginTime +
                ", hash=" + super.hashCode() +
                '}';
    }

    public long newId() {
        LOG.debug("new id generate:{}",this.toString());
        return this.generateIdResult().generateId();
    }
}
