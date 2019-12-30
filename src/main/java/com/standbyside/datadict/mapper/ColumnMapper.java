package com.standbyside.datadict.mapper;

import com.standbyside.datadict.entity.ColumnInfo;
import com.standbyside.datadict.entity.TableInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColumnMapper {

    List<ColumnInfo> findColumns(@Param("schemaName") String schemaName);
}
