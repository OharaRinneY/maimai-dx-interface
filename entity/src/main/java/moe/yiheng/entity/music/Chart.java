package moe.yiheng.entity.music;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "chart")
public class Chart {
    @Id
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    @Transient // 通过comment-service查询该属性
//    private List<Comment> comments;
}