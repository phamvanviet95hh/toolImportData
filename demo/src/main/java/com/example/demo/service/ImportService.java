package com.example.demo.service;

import com.example.demo.entitys.PartnerEntity;
import com.example.demo.repositorys.ImportRepository;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.*;

import com.example.demo.repositorys.PartnerRepository;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImportService {

    @Autowired
    private ImportRepository importRepository;

    @Autowired
    private PartnerRepository partnerRepository;

    @Transactional
    public <T> void importExcelFile(MultipartFile file, Class<T> clazz, JpaRepository<T, ?> repository) {
        try (InputStream inputStream = file.getInputStream();
                Workbook workbook = WorkbookFactory.create(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            List<T> entities = new ArrayList<>();

            // Lấy tiêu đề cột từ hàng đầu tiên
            List<String> headers = new ArrayList<>();
            if (rowIterator.hasNext()) {
                Row headerRow = rowIterator.next();
                for (Cell cell : headerRow) {
                    headers.add(cell.getStringCellValue().trim());
                }
            }

            // Duyệt từng dòng dữ liệu
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                T obj = clazz.getDeclaredConstructor().newInstance();

                for (int i = 0; i < headers.size(); i++) {
                    String fieldName = headers.get(i);
                    Cell cell = row.getCell(i);

                    if (cell == null)
                        continue;

                    Field field;
                    try {
                        field = clazz.getDeclaredField(fieldName);
                        field.setAccessible(true);

                        switch (cell.getCellType()) {
                            case STRING -> {
                                if (field.getType() == LocalDateTime.class) {
                                    // Định dạng ngày/tháng trong Excel
                                    DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                                            .appendPattern("d/M/yyyy HH:mm:ss")
                                            .optionalStart()
                                            .appendFraction(ChronoField.MICRO_OF_SECOND, 0, 6, true)
                                            .optionalEnd()
                                            .toFormatter();
                                    LocalDateTime dateTime = LocalDateTime.parse(cell.getStringCellValue(), formatter);
                                    field.set(obj, dateTime);
                                } else if (field.getType().equals(LocalDate.class)) {
                                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                                    if (cell.getCellType() == CellType.STRING) {
                                        field.set(obj, LocalDate.parse(cell.getStringCellValue().trim(), dateFormatter));
                                    } else if (cell.getCellType() == CellType.NUMERIC) {
                                        field.set(obj, cell.getLocalDateTimeCellValue().toLocalDate());
                                    }
                                } else if (field.getType() == Boolean.class || field.getType() == boolean.class) {
                                    String value = cell.getStringCellValue().trim().toLowerCase();
                                    field.set(obj, value.equals("true") || value.equals("1") || value.equals("yes"));
                                } else {
                                    field.set(obj, cell.getStringCellValue());
                                }
                            }
                            case NUMERIC -> {
                                if (DateUtil.isCellDateFormatted(cell) && field.getType().equals(Date.class)) {
                                    field.set(obj, cell.getDateCellValue());
                                } else if (field.getType() == String.class) {
                                    // Ép số thành chuỗi nếu field là String
                                    field.set(obj, String.valueOf(cell.getNumericCellValue()));
                                } else if (field.getType() == Integer.class || field.getType() == int.class) {
                                    field.set(obj, (int) cell.getNumericCellValue());
                                } else if (field.getType() == Double.class || field.getType() == double.class) {
                                    field.set(obj, cell.getNumericCellValue());
                                } else if (field.getType() == Long.class || field.getType() == long.class) {
                                    field.set(obj, (long) cell.getNumericCellValue());
                                } else {
                                    if (field.getType().equals(PartnerEntity.class)) {
                                        Long id = (long) cell.getNumericCellValue();
                                        PartnerEntity partner = partnerRepository.findById(id)
                                                .orElseThrow(() -> new RuntimeException("Không tìm thấy PartnerEntity với ID: " + id));
                                        field.set(obj, partner);
                                    }

                                }
                            }
                            case BOOLEAN -> field.set(obj, cell.getBooleanCellValue());
                        }
                    } catch (NoSuchFieldException e) {
                        // Nếu field không tồn tại thì bỏ qua
                        continue;
                    }
                }
                entities.add(obj);
//                repository.save(obj);
            }

             repository.saveAll(entities);

        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi đọc file Excel: " + e.getMessage(), e);
        }
    }
    private DateTimeFormatter getFlexibleDateTimeFormatter() {
        List<String> patterns = List.of(
            "dd/MM/yyyy HH:mm:ss.SSSSSS",
            "dd/MM/yyyy HH:mm:ss.SSS",
            "dd/MM/yyyy HH:mm:ss",
            "M/d/yyyy H:mm:ss.SSSSSS",
            "M/d/yyyy H:mm:ss.SSS",
            "M/d/yyyy H:mm:ss",
            "yyyy-MM-dd'T'HH:mm:ss.SSSSSS",
            "yyyy-MM-dd'T'HH:mm:ss.SSS",
            "yyyy-MM-dd HH:mm:ss"
        );
    
        return new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSSSSS"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
            .appendOptional(DateTimeFormatter.ofPattern("M/d/yyyy H:mm:ss.SSSSSS"))
            .appendOptional(DateTimeFormatter.ofPattern("M/d/yyyy H:mm:ss.SSS"))
            .appendOptional(DateTimeFormatter.ofPattern("M/d/yyyy H:mm:ss"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            .toFormatter(Locale.getDefault());
    }
    
}
