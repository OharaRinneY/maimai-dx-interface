package moe.yiheng.musicservice.jsonEntities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author rinne
 * @Date 2022/2/26
 * music data from maimai-dx-prober
 */
@Data
public class RawMusicData {
    private String id;
    private String title;
    private String type;
    private List<Double> ds;
    private List<String> level;
    private List<Integer> cids;
    private List<RawChart> charts;
    @JsonProperty("basic_info")
    private BasicInfo basicInfo;

    @Data
    public static class RawChart {
        private List<String> notes;
        private String charter;
    }

    @Data
    public static class BasicInfo {
        private String title;
        private String artist;
        private String genre;
        private Integer bpm;
        @JsonProperty("release_date")
        private String releaseDate;
        private String from;
        @JsonProperty("is_new")
        private Boolean isNew;
    }
}
