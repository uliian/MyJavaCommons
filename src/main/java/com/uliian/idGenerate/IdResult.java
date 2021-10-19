package com.uliian.idGenerate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Objects;

public class IdResult {

    private long timeStamp;

    public long getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        LocalDateTime of = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
        long beginTime = of.toEpochSecond(ZoneOffset.UTC)*1000;
        long createTimeTimestamp = beginTime + timeStamp*1000;
        return "IdResult{" +
                "timeStamp=" + timeStamp +
                ", sequence=" + sequence +
                ", nodeId=" + nodeId +
                ", createTime="+ Instant.ofEpochMilli(createTimeTimestamp).atZone(ZoneId.systemDefault()).toLocalDateTime().toString()+
                '}';
    }

    public long getSequence() {
        return sequence;
    }

    public long getNodeId() {
        return nodeId;
    }

    public IdResult(long timeStamp, long sequence, long nodeId) {
        this.timeStamp = timeStamp;
        this.sequence = sequence;
        this.nodeId = nodeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IdResult)) return false;
        IdResult idResult = (IdResult) o;
        return getTimeStamp() == idResult.getTimeStamp() &&
                getSequence() == idResult.getSequence() &&
                getNodeId() == idResult.getNodeId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTimeStamp(), getSequence(), getNodeId());
    }

    public IdResult(long id){
        this.timeStamp = id >> 31;
        this.sequence = (id & 2147482624)>>10;
        this.nodeId = id & 1023;
    }

    public Date getIdDate(){
        LocalDateTime of = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
        long beginTime = of.toEpochSecond(ZoneOffset.UTC)*1000;
        return new Date(this.timeStamp * 1000 + beginTime);
    }

    private final long sequence;
    private final long nodeId;

    public long generateId(){
        //|--1位符号--|--32位时间戳--|--21位序列--|--10位机器码--|
        return this.timeStamp << 31 | this.sequence << 10 | this.nodeId;
//        //|--1位符号--|--32位时间戳--|--24位序列--|--7位机器码--|
//        return 0 | this.timeStamp << 31 | this.sequence << 7 | this.nodeId;
    }
}
