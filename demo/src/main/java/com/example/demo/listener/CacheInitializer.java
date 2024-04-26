package com.example.demo.listener;

import com.example.demo.cache.ScriptCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

@Component
public class CacheInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheInitializer.class);
    private static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    @Autowired
    private ScriptCache scriptCache;

    @EventListener(ApplicationReadyEvent.class)
    public void initializeCacheAfterStartup() {
        Runnable cacheRefresher = () -> {
            try{
                scriptCache.clearCache();
                LOGGER.info("Clearing script cache");
            }catch (Exception e){
                LOGGER.error("Error occurred while refreshing script cache: " + e.getMessage());
            }
        };

        try {
            LOGGER.info("Initializing new script cache");
            scheduler.scheduleWithFixedDelay(cacheRefresher, 5, 3600, SECONDS); //hourly refresh
        } catch(Exception e) {
            LOGGER.error("Error occurred while scheduling cache refresh internal: " + e.getMessage());
        }
    }
}
