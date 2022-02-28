package moe.yiheng.entity.music;

import moe.yiheng.enums.entity.Difficulty;

import java.util.Optional;

/**
 * @Author rinne
 * @Date 2022/3/1
 */
public abstract class BaseEntityWithFiveDifficulties<T> {

    public Optional<T> getByDifficulty(Difficulty difficulty) {
        switch (difficulty) {
            case BASIC:
                return Optional.of(getBasic());
            case ADVANCED:
                return Optional.of(getAdvanced());
            case EXPERT:
                return Optional.of(getExpert());
            case MASTER:
                return Optional.of(getMaster());
            case REMASTER:
                return Optional.ofNullable(getRemaster());
            default:
                return Optional.empty();
        }
    }

    public abstract T getBasic();

    public abstract void setBasic(T basic);

    public abstract T getAdvanced();

    public abstract void setAdvanced(T advanced);

    public abstract T getExpert();

    public abstract void setExpert(T expert);

    public abstract T getMaster();

    public abstract void setMaster(T master);

    public abstract T getRemaster();

    public abstract void setRemaster(T remaster);
}
