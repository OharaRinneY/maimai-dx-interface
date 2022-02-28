package moe.yiheng.musicservice.jsonEntities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author rinne
 * @Date 2022/2/28
 */
@Data
public class RawChartStat {
    private Integer count;
    private Double avg;
    @JsonProperty("sssp_count")
    private Integer ssspCount;
    private String tag;
    private Integer v;
    private Integer t;
}
