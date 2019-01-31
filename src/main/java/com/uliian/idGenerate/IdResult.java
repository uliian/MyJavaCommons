package com.uliian.idGenerate;

import java.util.Objects;

public class IdResult {
    private long timeStamp;

    public long getTimeStamp() {
        return timeStamp;
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
        this.sequence = id << 33 >> 42;
        this.nodeId = id << 42 >> 42;
    }

    private long sequence;
    private long nodeId;

    public long generateId(){
        //|--1位符号--|--32位时间戳--|--21位序列--|--10位机器码--|
        return 0 | this.timeStamp << 31 | this.sequence << 10 | this.nodeId;
//        //|--1位符号--|--32位时间戳--|--24位序列--|--7位机器码--|
//        return 0 | this.timeStamp << 31 | this.sequence << 7 | this.nodeId;
    }
}
