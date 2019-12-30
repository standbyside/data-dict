package com.standbyside.datadict.service;

import com.standbyside.datadict.entity.ColumnInfo;
import com.standbyside.datadict.entity.TableInfo;
import com.standbyside.datadict.mapper.ColumnMapper;
import com.standbyside.datadict.mapper.TableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Value("${custom.schema-name}")
    private String schemaName;

    @Autowired
    private TableMapper tableMapper;
    @Autowired
    private ColumnMapper columnMapper;

    public List<TableInfo> findTables() {
        return tableMapper.findTables(schemaName);
    }

    public List<ColumnInfo> findColumns() {
        return columnMapper.findColumns(schemaName);
    }
}
