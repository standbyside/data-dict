package com.standbyside.datadict.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
