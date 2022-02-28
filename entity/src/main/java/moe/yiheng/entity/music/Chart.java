package moe.yiheng.entity.music;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author rinne
 */
@Entity
@Table(name = "chart")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Chart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private String type;
    private String difficulty;
    private String level;
    private Double innerLevel;
    private Integer tap;
    private Integer hold;
    private Integer slide;
    private Integer touch;
    @Column(name = "break")
    @JsonProperty(value = "break")
    private Integer breakCount;
    private Integer total;
    private String designer;

    // 统计数据
    private Integer playerCount;
    private Integer SSSCount;
    private Double average;
    private String tag;
    private Integer difficultyRankInSameLevel;
    private Integer songCountInSameLevel;
//    @Transient
//    private List<Comment> comments;
}