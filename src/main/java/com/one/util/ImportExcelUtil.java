package com.one.util;


import com.one.model.Compact;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 上传Excel表
 */
public class ImportExcelUtil {

    //总行数
    private int totalRows = 0;
    //总条数
    private int totalCells = 0;
    //错误信息接收器
    private String errorMsg;

    //构造方法
    public ImportExcelUtil() {
    }

    //获取总行数
    public int getTotalRows() {
        return totalRows;
    }

    //获取总列数
    public int getTotalCells() {
        return totalCells;
    }

    //获取错误信息-暂时未用到暂时留着
    public String getErrorInfo() {
        return errorMsg;
    }

    /**
     * @param filePath
     * @return
     * @描述：是否是2003的excel，返回true是2003
     */
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    /**
     * @param filePath
     * @return
     * @描述：是否是2007的excel，返回true是2007
     */
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    /**
     * 验证是否是EXCEL文件
     *
     * @param filePath
     * @return
     */
    public static boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            return false;
        }
        return true;
    }

    /**
     * 读EXCEL文件，获取客户信息集合
     *
     * @param fileName
     * @return
     */
    public List<Compact> getExcelInfo(String fileName, MultipartFile Mfile) {

        //把spring文件上传的MultipartFile转换成CommonsMultipartFile类型
        CommonsMultipartFile cf = (CommonsMultipartFile) Mfile; //获取本地存储路径
        File file = new File("D:\\fileupload");
        //创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）
        if (!file.exists()) file.mkdirs();
        //新建一个文件
        File file1 = new File("D:\\fileupload\\" + new Date().getTime() + ".xls");
        //将上传的文件写入新建的文件中
        try {
            cf.getFileItem().write(file1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //初始化客户信息的集合
        List<Compact> compactList = new ArrayList<Compact>();
        //初始化输入流
        FileInputStream is = null;
        Workbook wb = null;
        try {
            //根据新建的文件实例化输入流
            is = new FileInputStream(file1);
            //根据excel里面的内容读取客户信息
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if (fileName.endsWith("xls")) {
                //当excel是2003时
                wb = new HSSFWorkbook(is);
            } else if (fileName.endsWith("xlsx")) {
                //当excel是2007时
                wb = new XSSFWorkbook(is);
            }

            //读取Excel里面客户的信息
            compactList = readExcelValue(wb);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    is = null;
                    e.printStackTrace();
                }
            }
        }
        return compactList;
    }

    /**
     * 读取Excel里面客户的信息
     *
     * @param wb
     * @return
     */
    private List<Compact> readExcelValue(Workbook wb) {
        //得到第一个shell
        Sheet sheet = wb.getSheetAt(0);

        //得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();

        //得到Excel的列数(前提是有行数)
        if (totalRows >= 1 && sheet.getRow(0) != null) {
            //判断行数大于一，并且第一行必须有标题（这里有bug若文件第一行没值就完了）
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        } else {
            return null;
        }

        List<Compact> compactList = new ArrayList<Compact>();//声明一个对象集合
        Compact compact;//声明一个对象

        //循环Excel行数,从第二行开始。标题不入库
        for (int r = 1; r < totalRows; r++) {
            compact = new Compact();
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            } else {
                //循环Excel的列
                for (int c = 1; c < this.totalCells; c++) {
                    Cell cell = row.getCell(c);
                    if (cell != null) {
                        if (c == 1) {
                            compact.setContractSignYear(getValue(cell).toString());//得到行中第一个值
                        } else if (c == 2) {
                            compact.setItemCoding(getValue(cell).toString());//得到行中第二个值
                        } else if (c == 3) {
                            compact.setSalePerson(getValue(cell).toString());//得到行中第三个值
                        } else if (c == 4) {
                            compact.setProjectName(getValue(cell).toString());//得到行中第三个值
                        } else if (c == 5) {
                            compact.setContractAmount(getValue(cell).toString());//得到行中第三个值
                        } else if (c == 6) {
                            compact.setInvoiceAmount2015(getValue(cell).toString());//得到行中第三个值
                        } else if (c == 7) {
                            compact.setRecoveredAmount2015(getValue(cell).toString());//得到行中第三个值
                        } else if (c == 8) {
                            compact.setInvoiceAmount2016(getValue(cell).toString());//得到行中第三个值
                        } else if (c == 9) {
                            compact.setRecoveredAmountDate2016(getValue(cell).toString());//得到行中第三个值
                        } else if (c == 10) {
                            compact.setRecoveredAmount2016(getValue(cell).toString());//得到行中第三个值
                        } else if (c == 11) {
                            compact.setInvoiceAmount2017(getValue(cell).toString());//得到行中第三个值
                        } else if (c == 12) {
                            compact.setRecoveredAmountDate2017(getValue(cell).toString());//得到行中第三个值
                        } else if (c == 13) {
                            compact.setRecoveredAmount2017(getValue(cell).toString());//得到行中第三个值
                        } else if (c == 14) {
                            compact.setInvoiceAmount2018(getValue(cell).toString());//得到行中第三个值
                        } else if (c == 15) {
                            compact.setInvoiceAmountDate2018(getValue(cell).toString());//得到行中第三个值
                        } else if (c == 16) {
                            compact.setRecoveredAmount2018(getValue(cell).toString());//得到行中第三个值
                        } else if (c == 17) {
                            compact.setRecoveredAmountDate2018(getValue(cell).toString());//得到行中第三个值
                        } else if (c == 18) {
                            compact.setDue(getValue(cell).toString());//得到行中第三个值
                        } else if (c == 19) {
                            compact.setInvoiceAmount(getValue(cell).toString());//得到行中第三个值
                        } else if (c == 20) {
                            compact.setUnbilledAmount(getValue(cell).toString());//得到行中第三个值
                        } else if (c == 21) {
                            compact.setReceivableTotal(getValue(cell).toString());//得到行中第三个值
                        }
                    }
                }
            }
            //添加对象到集合中
            compactList.add(compact);
        }
        return compactList;
    }

    /**
     * 得到Excel表中的值
     *
     * @param cell Excel中的每一个格子
     * @return Excel中每一个格子中的值
     */
    private Object getValue(Cell cell) {
        Object cellValue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                // 如果当前Cell的Type为STRING 字符串类型
                case HSSFCell.CELL_TYPE_STRING:
                    cellValue = cell.getStringCellValue();
//                        if (cellValue.trim().equals("") || cellValue.trim().length() <= 0)
//                            cellValue = " ";
                    break;
                // 如果当前Cell的Type为NUMERIC 数值类型
                case HSSFCell.CELL_TYPE_NUMERIC:
                    short format = cell.getCellStyle().getDataFormat();
                    System.out.println("format:" + format + "       value:" + cell.getNumericCellValue());
                    if (format == 14 || format == 31 || format == 57 || format == 58 || (176 <= format && format <= 178) || (182 <= format && format <= 196)
                            || (210 <= format && format <= 213) || (208 == format)) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        double value = cell.getNumericCellValue();
                        Date date = DateUtil.getJavaDate(value);
                        cellValue = sdf.format(date);
                    }
                    // 判断当前的cell是否为Date
                    else if (HSSFDateUtil.isCellDateFormatted(cell)) {  //先注释日期类型的转换，在实际测试中发现HSSFDateUtil.isCellDateFormatted(cell)只识别2014/02/02这种格式。
                        // 如果是Date类型则，取得该Cell的Date值           // 对2014-02-02格式识别不出是日期格式
                        Date date = cell.getDateCellValue();
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        cellValue = df.format(date);
                    } else { // 如果是纯数字
                        // 取得当前Cell的数值
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }

                    break;
                // 如果当前Cell的Type为FORMULA 公式
                case HSSFCell.CELL_TYPE_FORMULA:
                    cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;

                // 如果当前Cell的Type为BLANK 空白或空
                case HSSFCell.CELL_TYPE_BLANK:
                    cellValue = " ";
                    break;

                // 如果当前Cell的Type为BOOLEAN: 布尔型
                case HSSFCell.CELL_TYPE_BOOLEAN:
                    break;

                // 如果当前Cell的Type为ERROR: 错误
                case HSSFCell.CELL_TYPE_ERROR:
                    break;
                default: //未知类型
                    break;
            }
        }
        return cellValue;
    }
}


