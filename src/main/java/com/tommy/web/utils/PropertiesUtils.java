package com.tommy.web.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * Properties文件的读写
 * @author nxl
 * 
 */
public class PropertiesUtils {


	/**
	 * 读取Properties配置文件内容 路径在Resource根目录
	 * 
	 * @param filePath
	 * @return Properties
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Properties readProperties(String fileName)
			throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(PropertiesUtils.class.getClassLoader()
				.getResourceAsStream(fileName));
		return properties;
	}

	/**
	 * 默认读取PhpInterfaceUrl.properties
	 */
	public static Properties readProperties() throws FileNotFoundException,
			IOException {
		String fileName = "PhpInterfaceUrl.properties";
		Properties properties = new Properties();
		properties.load(PropertiesUtils.class.getClassLoader()
				.getResourceAsStream(fileName));
		return properties;
	}

	/**
	 * 获取所有source
	 * @return
	 * @throws Exception
	 */
	public static List<String> sourceVals() throws Exception {
		List<String> urllist = new ArrayList<String>();
		Properties p = readProperties("source.properties");
		Enumeration<Object> em = p.keys();
		while (em.hasMoreElements()) {
			urllist.add(p.getProperty(em.nextElement().toString()));
		}
		return urllist;
	}

}