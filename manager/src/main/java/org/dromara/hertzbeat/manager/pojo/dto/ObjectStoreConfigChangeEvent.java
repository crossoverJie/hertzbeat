package org.dromara.hertzbeat.manager.pojo.dto;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;

/**
 *
 * Created by gcdd1993 on 2023/9/28
 */
@Getter
public class ObjectStoreConfigChangeEvent extends ApplicationEvent {
    private final ObjectStoreDTO<?> config;

    public ObjectStoreConfigChangeEvent(ObjectStoreDTO<?> config) {
        super(UUID.randomUUID());
        this.config = config;
    }
}
