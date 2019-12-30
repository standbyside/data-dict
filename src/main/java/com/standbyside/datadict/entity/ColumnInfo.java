package com.standbyside.datadict.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ColumnInfo {

    /**
     * 表名.
     */
    private String tableName;
    /**
     * 字段名.
     */
    private String columnName;
    /**
     * 字段编号.
     */
    private String ordinalPosition;
    /**
     * 字段注释.
     */
    private String columnComment;
    /**
     * 数据类型.
     */
    private String columnType;
    /**
     * 允许为空.
     */
    private String isNullable;
    /**
     * 键类型.
     */
    private String columnKey;

}
