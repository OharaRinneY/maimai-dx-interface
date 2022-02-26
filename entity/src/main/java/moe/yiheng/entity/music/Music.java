package moe.yiheng.entity.music;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @Author rinne
 * @Date 2022/2/26
 */
@Entity
@Data
@Table(name = "music")
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String artist;
    private String type;
    private String version;
    private Integer bpm;
    private Boolean isNew;
    private String genre;
    private String coverUrl;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "level_id", referencedColumnName = "id")
    private Level level;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inner_level_id", referencedColumnName = "id")
    private InnerLevel innerLevel;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "charts_id", referencedColumnName = "id")
    private Charts charts;

    @Transient // 通过 alias service 查询
    private List<String> alias;
}
