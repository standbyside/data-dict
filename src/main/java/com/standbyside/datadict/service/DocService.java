package com.standbyside.datadict.service;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;
import com.standbyside.datadict.entity.TableInfo;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocService {

    private Font BASE_FONT = new Font(Font.NORMAL, 10, Font.NORMAL, new Color(0, 0, 0));

    public void generateFile(List<TableInfo> tables) throws IOException, DocumentException {
        Document doc = new Document(PageSize.A4);
        String fileName = System.nanoTime() + ".doc";
        System.out.println(fileName);
        RtfWriter2.getInstance(doc, new FileOutputStream(new File("D://test//" + fileName)));
        doc.open();

        for (TableInfo table : tables) {
            addColumnTable(doc, table);
        }
        doc.close();
    }

    private void addColumnTable(Document doc, TableInfo data) throws DocumentException {
        Table table = new Table(4);


        String[] headers = new String[] {
                "字段名称", "字段类型", "字段说明", "是否允许为空"
        };
        for (String header : headers) {
            table.addCell(new Cell(word(header)));
        }

        List<Cell> cells = data.getColumns().stream().map(column -> {
            List<Cell> columnCells = new ArrayList<>(10);
            try {
                columnCells.add(new Cell(word(column.getColumnName())));
                columnCells.add(new Cell(word(column.getColumnType())));
                columnCells.add(new Cell(word(column.getColumnComment())));
                columnCells.add(new Cell(word(column.getIsNullable())));
            } catch (BadElementException e) {
                e.printStackTrace();
            }
            return columnCells;
        }).flatMap(o -> o.stream()).collect(Collectors.toList());

        cells.forEach(cell -> table.addCell(cell));

        doc.add(word(" "));
        doc.add(word(data.getTableName() + " " + data.getTableComment()));
        doc.add(table);
    }

    private Paragraph word(String content) {
        return new Paragraph(" " + content, BASE_FONT);
    }
}
