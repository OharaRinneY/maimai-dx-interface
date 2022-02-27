package moe.yiheng.musicservice.dto;

import lombok.Data;

/**
 * @Author rinne
 * @Date 2022/2/26
 */
@Data
public class QueryConditions {
    private String title;
    private String artist;
    private String type;
    private String version;
    private Integer bpm;
    private Boolean isNew;
    private String genre;

    private Range<Double> innerLevel;
    private Range<String> level;

    private String difficulty;
}
