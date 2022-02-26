package moe.yiheng.musicservice.utils;

import moe.yiheng.entity.music.*;
import moe.yiheng.musicservice.jsonEntities.RawMusicData;

import java.util.Map;

/**
 * @Author rinne
 * @Date 2022/2/26
 */
public class MusicConverter {
    public static Music convert(RawMusicData rawMusicData) {
        boolean hasRemas = rawMusicData.getCharts().size() == 5;
        Music.MusicBuilder music = Music.builder()
                .id(Integer.valueOf(rawMusicData.getId()))
                .title(rawMusicData.getTitle())
                .artist(rawMusicData.getBasicInfo().getArtist())
                .type(rawMusicData.getType())
                .version(rawMusicData.getBasicInfo().getFrom())
                .bpm(rawMusicData.getBasicInfo().getBpm())
                .isNew(rawMusicData.getBasicInfo().getIsNew())
                .genre(rawMusicData.getBasicInfo().getGenre());
        Level.LevelBuilder level = Level.builder()
                .basic(rawMusicData.getLevel().get(0))
                .advanced(rawMusicData.getLevel().get(1))
                .expert(rawMusicData.getLevel().get(2))
                .master(rawMusicData.getLevel().get(3));
        if (hasRemas) {
            level.remaster(rawMusicData.getLevel().get(4));
        }
        music.level(level.build());
        InnerLevel.InnerLevelBuilder innerLevel = InnerLevel.builder()
                .basic(rawMusicData.getDs().get(0))
                .advanced(rawMusicData.getDs().get(1))
                .expert(rawMusicData.getDs().get(2))
                .master(rawMusicData.getDs().get(3));
        if (hasRemas) {
            innerLevel.remaster(rawMusicData.getDs().get(4));
        }
        music.innerLevel(innerLevel.build());
        Charts.ChartsBuilder charts = Charts.builder()
                .basic(convertChart(rawMusicData, 0))
                .advanced(convertChart(rawMusicData, 1))
                .expert(convertChart(rawMusicData, 2))
                .master(convertChart(rawMusicData, 3));
        if (hasRemas) {
            charts.remaster(convertChart(rawMusicData, 4));
        }
        music.charts(charts.build());
        return music.build();
    }

    private static Chart convertChart(RawMusicData rawMusicData,int index) {
        var difficultyMap = Map.of(
                0, "Basic",
                1, "Advanced",
                2, "Expert",
                3, "Master",
                4, "Remaster"
        );
        RawMusicData.RawChart rawChart = rawMusicData.getCharts().get(index);
        Chart.ChartBuilder chart = Chart.builder()
                .type(rawMusicData.getType())
                .difficulty(difficultyMap.get(index))
                .innerLevel(rawMusicData.getDs().get(index))
                .designer(rawChart.getCharter())
                .tap(Integer.parseInt(rawChart.getNotes().get(0)))
                .hold(Integer.parseInt(rawChart.getNotes().get(1)))
                .slide(Integer.parseInt(rawChart.getNotes().get(2)));
        if (rawChart.getNotes().size() == 5) {
            // has touch
            chart.touch(Integer.parseInt(rawChart.getNotes().get(3)));
            chart.breakCount(Integer.parseInt(rawChart.getNotes().get(4)));
        } else {
            chart.breakCount(Integer.parseInt(rawChart.getNotes().get(3)));
        }
        chart.level(rawMusicData.getLevel().get(index));
        // cal total
        int total = rawChart.getNotes().stream().mapToInt(Integer::parseInt).sum();
        chart.total(total);
        return chart.build();
    }
}
