package moe.yiheng.entity.music;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Author rinne
 * @Date 2022/2/26
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String basic;
    private String advanced;
    private String expert;
    private String master;
    private String remaster;
}
