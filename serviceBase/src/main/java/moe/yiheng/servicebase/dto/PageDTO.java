package moe.yiheng.servicebase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author rinne
 * @Date 2022/2/28
 */
@Data
@AllArgsConstructor
public class PageDTO<T> {
    private Long total;
    private List<T> data;
}
