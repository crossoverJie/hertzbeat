package org.dromara.hertzbeat.manager.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;

/**
 * 文件存储
 *
 *
 * Created by gcdd1993 on 2023/9/13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {
    private String name;
    private InputStream inputStream;
}
