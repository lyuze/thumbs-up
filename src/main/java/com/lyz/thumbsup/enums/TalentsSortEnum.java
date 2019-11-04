package com.lyz.thumbsup.enums;

import lombok.Getter;

/**
 * @Author: liuyuze
 * @Date: 2019.11.4 22:04
 */
@Getter
public enum TalentsSortEnum {
    DEFAULT(0, "默认排序"),
    SORT_BY_INFLUENCE(1, "根据影响力排序"),
    SORT_BY_EXPERIENCE(2, "根据经验排序"),
    ;

    private Integer code;

    private String msg;

    TalentsSortEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
