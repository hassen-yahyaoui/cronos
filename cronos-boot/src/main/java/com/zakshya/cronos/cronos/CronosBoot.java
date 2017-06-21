package com.zakshya.cronos.cronos;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CronosBoot {

    @EventListener
    public void onContextRefresh(ContextRefreshedEvent event) {
        SchedulableBuilder.buildFromApplicationContext(event.getApplicationContext());
    }

}