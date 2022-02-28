package moe.yiheng.enums.entity;

/**
 * @Author rinne
 * @Date 2022/3/1
 */
public enum Difficulty {
    BASIC(0, "Basic"),
    ADVANCED(1, "Advanced"),
    EXPERT(2, "Expert"),
    MASTER(3, "Master"),
    REMASTER(4, "Remaster");

    // 以下代码均为copilot编写
    private final int id;
    private final String name;

    Difficulty(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Difficulty getById(int id) {
        for (Difficulty difficulty : values()) {
            if (difficulty.getId() == id) {
                return difficulty;
            }
        }
        throw new IllegalArgumentException("No difficulty with id " + id);
    }

    public static Difficulty getByName(String name) {
        for (Difficulty difficulty : values()) {
            if (difficulty.getName().equals(name)) {
                return difficulty;
            }
        }
        throw new IllegalArgumentException("No difficulty with name " + name);
    }
}
