package moe.yiheng.entity.music;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import moe.yiheng.enums.entity.Difficulty;

import javax.persistence.*;
import java.util.Optional;

/**
 * @Author rinne
 * @Date 2022/2/26
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Charts extends BaseEntityWithFiveDifficulties<Chart> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "basic_id")
    private Chart basic;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "advanced_id")
    private Chart advanced;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "expert_id")
    private Chart expert;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "master_id")
    private Chart master;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "remaster_id")
    private Chart remaster;

}
