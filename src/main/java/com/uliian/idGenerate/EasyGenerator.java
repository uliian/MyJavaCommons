package com.uliian.idGenerate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class EasyGenerator {
    private final CircleArray circleArray;
    private final int nodeId;
    private final long beginTime;

    private final Model model;
    private static Logger LOG = LoggerFactory.getLogger(EasyGenerator.class);

    public EasyGenerator(int nodeId, int timeWait) {
        this(nodeId,timeWait,Model.Server);
    }
    public EasyGenerator(int nodeId, int timeWait,Model model) {
        this.nodeId = nodeId;
        this.circleArray = new CircleArray(timeWait);
        LocalDateTime of = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
        beginTime = of.toEpochSecond(ZoneOffset.UTC) * 1000;
        this.model = model;
    }


    public IdResult generateIdResult() {
        do {
            long timestamp = System.currentTimeMillis() - beginTime;
            timestamp = timestamp / 1000;

            long sequence = this.circleArray.generateSequence(timestamp);
            if (this.model == Model.Server && sequence < 1048574) {
                return new IdResult(timestamp, sequence, this.nodeId, this.model);
            }else if (this.model == Model.Client && sequence < 1023){
                return new IdResult(timestamp, sequence, this.nodeId, this.model);
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
