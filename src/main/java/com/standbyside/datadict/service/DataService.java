package com.standbyside.datadict.service;

import com.standbyside.datadict.entity.ColumnInfo;
import com.standbyside.datadict.entity.TableInfo;
import com.standbyside.datadict.mapper.ColumnMapper;
import com.standbyside.datadict.mapper.TableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DataService {

    @Value("${custom.schema-name}")
    private String schemaName;

    @Autowired
    private TableMapper tableMapper;
    @Autowired
    private ColumnMapper columnMapper;

    public List<TableInfo> findTables() {
        // 表
        List<TableInfo> tables = tableMapper.findTables(schemaName);
        // 字段
        List<ColumnInfo> columns = columnMapper.findColumns(schemaName);
        Map<String, List<ColumnInfo>> columnMap = columns.stream().collect(Collectors.groupingBy(ColumnInfo::getTableName));
        tables.stream().forEach(table -> table.setColumns(columnMap.get(table.getTableName())));
        // 索引 TODO
        return tables;
    }
}
