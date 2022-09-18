package com.weng.demo.springbootredis;

import com.weng.demo.springbootredis.util.RedisUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
class SpringbootRedisApplicationTests {

    @Autowired
    private RedisUtil redisUtil;
    private static final int SIZE = 100; // 10w
    private static final boolean VERBOSE = true;
    private static final int THREADS = Runtime.getRuntime().availableProcessors() << 1;

    @Test
    void testRedis() throws InterruptedException, IOException {
        redisUtil.set("test_incr_id", 1l);
        AtomicInteger control = new AtomicInteger(-1);
        Set<Long> uidSet = new ConcurrentSkipListSet<>();
        long old = System.currentTimeMillis();
        // Initialize threads
        List<Thread> threadList = new ArrayList<>(THREADS);
        for (int i = 0; i < THREADS; i++) {
            Thread thread = new Thread(() -> workerRun(uidSet, control));
            thread.setName("UID-generator-" + i);

            threadList.add(thread);
            thread.start();
        }

        // Wait for worker done
        for (Thread thread : threadList) {
            thread.join();
        }

        // Check generate 700w times
        Assert.assertEquals(SIZE, control.get());

        // Check UIDs are all unique
        checkUniqueID(uidSet);
        long now = System.currentTimeMillis();
        long time = now - old;
        System.out.println(".....................user time  =" + time);
    }

    private void workerRun(Set<Long> uidSet, AtomicInteger control) {
        for (; ; ) {
            int myPosition = control.updateAndGet(old -> (old == SIZE ? SIZE : old + 1));
            if (myPosition == SIZE) {
                return;
            }

            doGenerate(uidSet, myPosition);
        }
    }

    private void doGenerate(Set<Long> uidSet, int index) {

        Long incr_id = redisUtil.increment("test_incr_id", 1l);
        boolean existed = !uidSet.add(incr_id);
        if (existed) {
            System.out.println("Found duplicate UID " + incr_id);
        }

        // Check UID is positive, and can be parsed
        Assert.assertTrue(incr_id > 0L);

        if (VERBOSE) {
            System.out.println(Thread.currentThread().getName() + " No." + index + " >>> " + incr_id);
        }
    }

    private void checkUniqueID(Set<Long> uidSet) throws IOException {
        System.out.println(uidSet.size());
        Assert.assertEquals(SIZE, uidSet.size());
    }
}
