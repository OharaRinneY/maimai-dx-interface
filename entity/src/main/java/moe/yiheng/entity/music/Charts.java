package moe.yiheng.entity.music;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author rinne
 * @Date 2022/2/26
 */
@Entity
@Data
public class Charts {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    @JoinColumn(name = "basic_id")
    private Chart basic;
    @OneToOne
    @JoinColumn(name = "advanced_id")
    private Chart advanced;
    @OneToOne
    @JoinColumn(name = "expert_id")
    private Chart expert;
    @OneToOne
    @JoinColumn(name = "master_id")
    private Chart master;
    @OneToOne
    @JoinColumn(name = "remaster_id")
    private Chart remaster;
}
