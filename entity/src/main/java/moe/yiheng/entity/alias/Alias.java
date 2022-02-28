package moe.yiheng.entity.alias;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Author rinne
 * @Date 2022/2/27
 */
@Entity
@Data
@Table(name = "alias")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Alias {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer id;
    private String alias;
    @Column(name = "music_id")
    private Integer musicId;
    private Boolean passed;
}
