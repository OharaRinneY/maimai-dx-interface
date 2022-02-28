package moe.yiheng.entity.music;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Author rinne
 * @Date 2022/2/26
 */
@Data
@Entity
@Table(name = "inner_level")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InnerLevel extends BaseEntityWithFiveDifficulties<Double>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    private Double basic;
    private Double advanced;
    private Double expert;
    private Double master;
    private Double remaster;
}
