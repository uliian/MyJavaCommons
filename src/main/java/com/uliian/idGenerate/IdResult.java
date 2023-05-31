package com.uliian.idGenerate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Objects;

public class IdResult {

    private final long timeStamp;

    public long getTimeStamp() {
        return timeStamp;
    }

    private final Model model;
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
        this(timeStamp,sequence,nodeId,Model.Server);
    }

    public IdResult(long timeStamp, long sequence, long nodeId,Model model){
        this.timeStamp = timeStamp;
        this.sequence = sequence;
        this.nodeId = nodeId;
        this.model = model;
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
        this(id,Model.Server);
    }

    public IdResult(long id,Model model){
        this.model = model;
        this.timeStamp = id >> 31;
        switch (this.model){
            case Server:
                this.sequence = (id & 2147482624)>>10;
                this.nodeId = id & 1023;
                break;
            case Client:
                this.sequence = (id & 2145386496)>>22;
                this.nodeId = id & 2097151;
                break;
            default:
                throw new IllegalArgumentException("错误的ID模式");
        }
    }

    public Date getIdDate(){
        LocalDateTime of = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
        long beginTime = of.toEpochSecond(ZoneOffset.UTC)*1000;
        return new Date(this.timeStamp * 1000 + beginTime);
    }

    private final long sequence;
    private final long nodeId;

    public long generateId(){
        //server: |--1位符号--|--32位时间戳--|--21位序列--|--10位机器码--|
        //client: |--1位符号--|--32位时间戳--|--10位序列--|--21位机器码--|
        switch (this.model){
            case Server:
                return this.timeStamp << 31 | this.sequence << 10 | this.nodeId;
            case Client:
                return this.timeStamp << 31 | this.sequence << 22 | this.nodeId;
            default:
                throw new IllegalArgumentException("错误的ID模式");

        }
    }
}
