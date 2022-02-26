package moe.yiheng.musicservice.utils;

import com.alibaba.druid.util.StringUtils;
import com.querydsl.core.types.dsl.BooleanExpression;
import moe.yiheng.entity.music.QMusic;
import moe.yiheng.musicservice.vo.QueryConditions;
import moe.yiheng.musicservice.vo.Range;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author rinne
 * @Date 2022/2/26
 */
public class ExpressionUtil {

    public static BooleanExpression generateExpression(QueryConditions conditions) {
        QMusic music = QMusic.music;
        BooleanExpression expression = music.isNotNull().or(music.isNull());
        if (!StringUtils.isEmpty(conditions.getTitle())) {
            expression = expression.and(music.title.likeIgnoreCase("%" + conditions.getTitle() + "%"));
        }
        if (!StringUtils.isEmpty(conditions.getArtist())) {
            expression = expression.and(music.artist.likeIgnoreCase("%" + conditions.getArtist() + "%"));
        }
        if (!StringUtils.isEmpty(conditions.getType())) {
            expression = expression.and(music.type.equalsIgnoreCase(conditions.getType()));
        }
        if (!StringUtils.isEmpty(conditions.getVersion())) {
            expression = expression.and(music.version.equalsIgnoreCase(conditions.getVersion()));
        }
        if (conditions.getBpm() != null) {
            expression = expression.and(music.bpm.eq(conditions.getBpm()));
        }
        if (conditions.getIsNew() != null) {
            expression = expression.and(music.isNew.eq(conditions.getIsNew()));
        }
        if (conditions.getGenre() != null) {
            expression = expression.and(music.genre.equalsIgnoreCase(conditions.getGenre()));
        }
        if (conditions.getLevel() != null) {
            Range<Double> range = levelToDs(conditions.getLevel());
            BooleanExpression dsExpression = getDsExpression(range, music);
            expression = expression.and(dsExpression);
        }
        if (conditions.getInnerLevel() != null) {
            BooleanExpression dsExpression = getDsExpression(conditions.getInnerLevel(), music);
            expression = expression.and(dsExpression);
        }
        // TODO alias
        return expression;
    }

    private static BooleanExpression getDsExpression(Range<Double> dsRange, QMusic music) {
        List<BooleanExpression> expressions = new ArrayList<>();
        expressions.add(music.innerLevel.basic.between(dsRange.getFrom(), dsRange.getTo()));
        expressions.add(music.innerLevel.advanced.between(dsRange.getFrom(), dsRange.getTo()));
        expressions.add(music.innerLevel.expert.between(dsRange.getFrom(), dsRange.getTo()));
        expressions.add(music.innerLevel.master.between(dsRange.getFrom(), dsRange.getTo()));
        expressions.add(music.innerLevel.remaster.between(dsRange.getFrom(), dsRange.getTo()));
        BooleanExpression dsExpression = music.isNotNull().and(music.isNull());
        for (BooleanExpression expression1 : expressions) {
            dsExpression = dsExpression.or(expression1);
        }
        return dsExpression;
    }

    public static Range<Double> levelToDs(Range<String> range) {
        // 从我的小程序抄来的，我也看不懂了
        String min = range.getFrom();
        String max = range.getTo();
        if (!min.equals(max)) {
            if (min.contains("+")) {
                min = min.replace("+", ".7");
            }
            if (max.contains("+")) {
                max = max.replace("+", ".9");
            } else {
                max = max + (Double.parseDouble(max) >= 7.0 ? ".6" : ".9");
            }
        } else {
            String level = min;
            if (level.contains("+")) {
                min = level.replace("+", ".7");
                max = level.replace("+", ".9");
            } else {
                min = level;
                max = level + (Double.parseDouble(level) >= 7.0 ? ".6" : ".9");
            }
        }
        return new Range<>(Double.parseDouble(min), Double.parseDouble(max));
    }
}
