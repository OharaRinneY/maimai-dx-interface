package moe.yiheng.entity.music;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author rinne
 * @Date 2022/2/26
 */
@Entity
@Data
public class Level {
    @Id
    @GeneratedValue
    private Integer id;

    private String basic;
    private String advanced;
    private String expert;
    private String master;
    private String remaster;
}
