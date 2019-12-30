package com.standbyside.datadict.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.standbyside.datadict.entity.TableInfo;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PdfService {

    private FontSelector selector;
    {
        selector = new FontSelector();
        selector.addFont(FontFactory.getFont(FontFactory.TIMES_ROMAN, 12));
        selector.addFont(FontFactory.getFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED, 12));
    }

    public void generateFile(List<TableInfo> tables) throws IOException, DocumentException {
        Document doc = new Document(PageSize.A4);
        String fileName = System.nanoTime() + ".pdf";
        System.out.println(fileName);
        PdfWriter.getInstance(doc, new FileOutputStream(new File("D://test//" + fileName)));
        doc.open();

        for (TableInfo table : tables) {
            addColumnTable(doc, table);
        }
        doc.close();
    }

    private void addColumnTable(Document doc, TableInfo data) throws DocumentException {
        PdfPTable table = new PdfPTable(4);
        table.setHeaderRows(1);
        // 设置表格的宽度
        table.setTotalWidth(800);

        String[] headers = new String[]{"字段名称", "字段类型", "字段说明", "是否允许为空"};
        for (String header : headers) {
            table.addCell(new PdfPCell(selector.process(header)));
        }

        List<PdfPCell> cells = data.getColumns().stream().map(column -> {
            List<PdfPCell> columnCells = new ArrayList<>(10);
            columnCells.add(new PdfPCell(selector.process(column.getColumnName())));
            columnCells.add(new PdfPCell(selector.process(column.getColumnType())));
            columnCells.add(new PdfPCell(selector.process(column.getColumnComment())));
            columnCells.add(new PdfPCell(selector.process(column.getIsNullable())));
            return columnCells;
        }).flatMap(o -> o.stream()).collect(Collectors.toList());

        cells.forEach(cell -> table.addCell(cell));

        doc.add(selector.process(" "));
        doc.add(selector.process(data.getTableName() + " " + data.getTableComment()));
        doc.add(selector.process(" "));
        doc.add(table);
    }

}
