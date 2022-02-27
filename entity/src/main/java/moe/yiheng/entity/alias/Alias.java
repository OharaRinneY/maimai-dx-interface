package moe.yiheng.entity.alias;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

/**
 * @Author rinne
 * @Date 2022/2/27
 */
@Entity
@Data
@Table(name = "alias")
public class Alias {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer id;
    private String alias;
    @Column(name = "music_id")
    private Integer musicId;
    private Boolean passed;
}
