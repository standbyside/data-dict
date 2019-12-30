package com.standbyside.datadict.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TableInfo {

  /**
   * 表名.
   */
  private String tableName;
  /**
   * 表注释.
   */
  private String tableComment;
  /**
   * 字段列表.
   */
  private List<ColumnInfo> columns;
}
