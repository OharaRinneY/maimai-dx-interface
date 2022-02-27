package moe.yiheng.musicservice.dto;

import lombok.Data;

/**
 * @Author rinne
 * @Date 2022/2/26
 */
@Data
public class Range<T> {
    private T from;
    private T to;

    public Range(T from, T to) {
        this.from = from;
        this.to = to;
    }
}
