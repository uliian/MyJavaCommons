package com.uliian.idGenerate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public final class Helper {
    public static long generateTimeId(LocalDateTime time){
        LocalDateTime of = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
        long beginTime = of.toEpochSecond(ZoneOffset.UTC)*1000;
        long timeStamp = time.toEpochSecond(ZoneOffset.ofHours(8)) * 1000;
        timeStamp = timeStamp - beginTime;
        IdResult idResult = new IdResult(timeStamp/1000, 0, 0);
        return idResult.generateId();
    }
}
