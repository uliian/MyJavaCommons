package com.uliian.idGenerate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.Stopwatch;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

import static org.junit.Assert.*;

public class EasyGeneratorTest {
    @Test
    public void idGenerator_grow_success() {
        EasyGenerator easyGenerator = new EasyGenerator(10, 60);
        long oldId = -1L;


        for (int i = 0; i < 5000000; i++) {
            IdResult newIdresult = easyGenerator.generateIdResult();
            long newId = newIdresult.generateId();
            if (!(newId > oldId)) {
                System.out.println("fuck");
            }
            assertTrue(newId > oldId);
            oldId = newId;
        }
    }

    @Test
    public void idGenerator_Notsame_success() throws InterruptedException {
        final int TOTAL_THREADS = 16;
        ExecutorService executorService = Executors.newFixedThreadPool(TOTAL_THREADS);

        for (int cc = 0; cc < 1; cc++) {
            long beginTime = System.currentTimeMillis();
            ConcurrentLinkedQueue<Long> queue = new ConcurrentLinkedQueue();

            EasyGenerator easyGenerator = new EasyGenerator(10, 60);

            ArrayList<Callable<Integer>> callables = new ArrayList<>();
            for (int i = 0; i < 1; i++) {
                Callable<Integer> c = () -> {
                    for (int j = 0; j < 50000; j++) {
                        queue.add(easyGenerator.newId());
                    }
                    return 0;
                };
                callables.add(c);
            }

            executorService.invokeAll(callables);
            System.out.println("OK,500 0000 id spend"+(System.currentTimeMillis()-beginTime)+"ms");
            HashSet<Long> ids = new HashSet<>(queue);
            Assert.assertEquals(ids.size(), queue.size());
        }
    }

    @Test
    public void IdResolve_success(){
        EasyGenerator easyGenerator = new EasyGenerator(10, 60);
        IdResult idResult = easyGenerator.generateIdResult();
        long id = idResult.generateId();
        IdResult idResult1 = new IdResult(id);
        Assert.assertTrue(idResult.equals(idResult1));

        LocalDateTime of = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
        long beginTime = of.toEpochSecond(ZoneOffset.UTC)*1000;
        System.out.println(new Date(idResult.getTimeStamp() * 1000 + beginTime));
    }

    @Test
    public void IdResolve_id_success(){
        LocalDateTime of = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
        long beginTime = of.toEpochSecond(ZoneOffset.UTC)*1000;
        IdResult idResult = new IdResult(1326637231477099521L);
        System.out.println(new Date(idResult.getTimeStamp() * 1000 + beginTime));
//        idResult = new IdResult(1308840604989398017L);
//        System.out.println(new Date(idResult.getTimeStamp() * 1000 + beginTime));
//        Assert.assertTrue(idResult.getSequence() == 0);
    }

    @Test
    public void generate_timestamp_id_success(){
        EasyGenerator easyGenerator = new EasyGenerator(1, 1000);
        System.out.println(easyGenerator.generateIdResult().getIdDate());
        System.out.println(LocalDateTime.now());
        System.out.println(easyGenerator.newId());
        long id = Helper.generateTimeId(LocalDateTime.now());
        IdResult idResult = new IdResult(id);
        System.out.println(idResult.getIdDate());
    }

    @Test
    public void idResolve(){
        IdResult idResult = new IdResult(1460512762252754945L);
        System.out.println(idResult);
    }

    @Test
    public void Idt(){
        int nodeId1 = 1;
        EasyGenerator easyGenerator = new EasyGenerator(nodeId1, 600000);
        EasyGenerator easyGenerator2 = new EasyGenerator(2,600000);
        HashSet<Long> sets = new HashSet<>();

        for (int i = 0;i<99999999;i++){
            IdResult idResult = easyGenerator.generateIdResult();
            Assert.assertEquals(idResult.getNodeId(),nodeId1);
            long newId = idResult.generateId();
            IdResult idResult1 = new IdResult(newId);
            sets.add(newId);
            long newId2 = easyGenerator2.newId();

            if(sets.contains(newId2)){
                System.out.println(idResult);
                System.out.println(idResult1);
                System.out.println(newId2);
                System.out.println(i);
            }

        }
        
    } 
}
