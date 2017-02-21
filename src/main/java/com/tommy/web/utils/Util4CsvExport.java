package com.tommy.web.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.google.common.collect.Lists;
import com.tommy.web.exception.BizException;

/**
 * Created by wen on 2017/2/8.
 */
public class Util4CsvExport {

    public static void main(String[] args) {
        LinkedHashMap<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("nihao",1 + "");
        headerMap.put("nihao2",2 + "");
        System.out.println(headerMap.values());

    }

    public static abstract class DefaultBatchExport<T> implements BatchExport<T> {
        @Override
        public int getBatchSize() {
            return BatchExport.DEFAULT_BATCH_SIZE;
        }
    }

    static enum PrintIO {
        web,local; // 网络，本地
    }

    public interface BatchExport<T> {
        int DEFAULT_BATCH_SIZE = 50000;

        /**
         *
         * @param batchCount 批次
         * @param batchSize 一次处理数
         * @return
         */
        List<T> getData(int batchCount, int batchSize);
        int getBatchSize();
    }


    static class Init {
        private List<Method> getMethodList;
        private CSVPrinter csvPrinter;

        public Init(List<Method> getMethodList, CSVPrinter csvPrinter) {
            this.getMethodList = getMethodList;
            this.csvPrinter = csvPrinter;
        }

        public CSVPrinter getCsvPrinter() {
            return csvPrinter;
        }
    }


    public static <T> void batchExport(String fileName, LinkedHashMap<String, String> headerMap, Class<T> t, BatchExport<T> batchExport) throws Exception {
        if (batchExport == null) throw new IllegalArgumentException("BatchExport is not implemented");
        Init init = init(PrintIO.local, fileName, headerMap, t);
        exportHeader(init, headerMap);
        int batchCount = 1;
        while (true) {
            List<T> datas = batchExport.getData(batchCount, batchExport.getBatchSize());
            if (datas == null || datas.isEmpty()) break;
            exportBody(init, datas);
            batchCount++;
            init.getCsvPrinter().flush();
        }
        close(init.getCsvPrinter());
    }

    public static <T> void export(String fileName, LinkedHashMap<String, String> headerMap, List<T> data, Class<T> t) throws Exception {
        // 设置 response 信息
        Init init = init(PrintIO.local, fileName, headerMap,  t);
        exportHeader(init, headerMap);
        exportBody(init, data);
        CSVPrinter print = init.getCsvPrinter();
        close(print);
    }

    /**
     * 初始化
     * @param fileName
     * @param headerMap
     * @param t
     * @throws Exception
     */
    private static <T>Init init(PrintIO printIO, String fileName, LinkedHashMap<String, String> headerMap, Class<T> t) throws Exception {
        CSVPrinter csvPrinter = null;
        if (printIO == PrintIO.web) {
            HttpServletResponse response = ContextUtils.getContext().getResponse();
            response.setCharacterEncoding("GBK");//这里不能用UTF-8，因为excel默认打开的编码UTF-8的文件会乱码
            response.setContentType("text/csv");
            response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".csv");
            csvPrinter = new CSVPrinter(response.getWriter(), CSVFormat.DEFAULT.withRecordSeparator("\n"));
        } else if (printIO == PrintIO.local) {
            FileOutputStream fos = new FileOutputStream(fileName);
            // 写BOM 防止乱码
            fos.write(new byte[] { (byte)0xEF, (byte)0xBB, (byte)0xBF });
            // 创建字节流输出对象
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            csvPrinter = new CSVPrinter(osw, CSVFormat.DEFAULT.withRecordSeparator("\n"));
        }

        //利用反射获取所有属性 get 方法
        List<Method> getMethodList = new ArrayList<Method>();
        if (t != null) {
            for (Iterator<String> it = headerMap.keySet().iterator(); it.hasNext();) {
                String key = it.next();
                key = firstUpper(headerMap.get(key));
                String getMethodName = "get" + key;
                Method getMethod = t.getMethod(getMethodName);
                getMethodList.add(getMethod);
            }
        }
        return new Init(getMethodList, csvPrinter);

    }

    /**
     * 导出表头
     * @param init
     * @param headerMap
     * @param <T>
     * @throws Exception
     */
    private static <T> void exportHeader(Init init, LinkedHashMap<String, String> headerMap) throws Exception {
        CSVPrinter csvPrinter = init.getCsvPrinter();
        if (csvPrinter == null) throw new BizException("export io is close");
        csvPrinter.printRecord(headerMap.keySet());
    }


    /**
     * 导出内容
     * @param init
     * @param data
     * @param <T>
     * @throws Exception
     */
    private static <T> void exportBody(Init init, List<T> data) throws Exception {
        CSVPrinter csvPrinter = init.getCsvPrinter();
        if (csvPrinter == null) throw new BizException("export io is close");
        //Content
        List<Method> getMethodList = init.getMethodList;
        for (T object : data) {
            List<String> newRecord = Lists.newArrayList();
            for (Method getMethod : getMethodList) {
                String value = String.valueOf(getMethod.invoke(object));

                if(value.length() > 8) {
                    newRecord.add("\t" + value);
                } else {
                    newRecord.add(value);
                }
            }
            csvPrinter.printRecord(newRecord);
        }
    }

    private static void close(CSVPrinter writer) throws IOException {
        if (writer != null) {
            writer.flush();
            writer.close();
        }
    }

    private static String firstUpper(String str) {
        if (str == null) return null;
        str = str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toUpperCase());
        return str;
    }
}
