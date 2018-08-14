package com.jojo.util.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.jojo.model.AtouUserProgress;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
 * 增删改查生成
 * 
 */
public class JavaCodeCRUDGenerator {

	private static Charset charset = Charset.forName("UTF-8");

	/**
	 * 项目文件根目录
	 */
	private static final String PROJECT_DIRECTORY = System.getProperty("user.dir");

	/**
	 * 分割的正则
	 */
	private static final String PATH_SPLIT_REGEX = "/";

	/**
	 * 模板文件在项目内保存的位置
	 */
	private static final String TEMPLATE_FILE_PATH = "src/main/resources/generatorTemplate";

	private static final String SERVICE_PACKAGE_NAME = "com.jojo.service";
	
	private static final String CONTROLLER_PACKAGE_NAME = "com.jojo.controller.api";
	
	private static final String SERVICE_PACKAGE_FILE_PATH = "src/main/java/com/jojo/service";

	private static final String SERVICE_IMPL_PACKAGE_FILE_PATH = "src/main/java/com/jojo/service/impl";

	private static final String CONTROLLER_PACKAGE_FILE_PATH = "src/main/java/com/jojo/controller/api";

	private static final String JSP_FILE_PATH = "src/main/webapp/WEB-INF/view/jsp/model";

	/**
	 * 主入口，进行批量生成
	 * 
	 * @param classOfModels
	 */
	public static void generate(Class<?>... classOfModels) {
		for (Class<?> classOfModel : classOfModels) {
			generateFile(classOfModel, true, true, true, false);
		}
	}

	/**
	 * 这样可以选择生成哪几个文件
	 * 
	 * @param classOfModel
	 * @param serviceFlag
	 * @param serviceImplFlag
	 * @param controllerFlag
	 * @param jspFlag
	 */
	public static void generateFile(Class<?> classOfModel, boolean serviceFlag, boolean serviceImplFlag,
			boolean controllerFlag, boolean jspFlag) {
		// 准备参数
		String classSimpleName = classOfModel.getSimpleName();
		String className = classOfModel.getName();
		List<String> fieldNames = getFiledName(classOfModel);
		String serviceName = SERVICE_PACKAGE_NAME + "." + classSimpleName;
		
		// 最终的Map
		Map<String, Object> map = Maps.newHashMap();
		map.put("targetPackageService", SERVICE_PACKAGE_NAME);
		map.put("targetPackageController", CONTROLLER_PACKAGE_NAME);
		map.put("classSimpleName", classSimpleName);
		map.put("className", className);
		map.put("serviceName", serviceName);
		map.put("fieldNames", fieldNames);
		map.put("ctx", " ${ctx}");
		map.put("classSimpleNameUncapitalize", StringUtils.uncapitalize(classSimpleName));
		System.out.println(JSON.toJSONString(map));

		// 看不懂
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
		// 看不懂
		try {
			String templatePath = getEffectiveFileDir(TEMPLATE_FILE_PATH);
			cfg.setDirectoryForTemplateLoading(new File(templatePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 看不懂
		cfg.setDefaultEncoding("UTF-8");
		// 看不懂
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		// 看不懂
		cfg.setLogTemplateExceptions(false);
		
		try {
			// 生成service接口
			if (serviceFlag) {
				String servicePath = getEffectiveFileDir(SERVICE_PACKAGE_FILE_PATH) + classSimpleName + "Service.java";
				createFile(cfg, map, "service.ftlh", servicePath);
			}
			// 生成service接口实现
			if (serviceImplFlag) {
				String serviceImplPath = getEffectiveFileDir(SERVICE_IMPL_PACKAGE_FILE_PATH) + classSimpleName
						+ "ServiceImpl.java";
				createFile(cfg, map, "serviceImpl.ftlh", serviceImplPath);
			}
			// 生成controllerApi
			if (controllerFlag) {
				String controllerPath = getEffectiveFileDir(CONTROLLER_PACKAGE_FILE_PATH) + classSimpleName
						+ "ApiController.java";
				createFile(cfg, map, "controllerApi.ftlh", controllerPath);
			}
			// 生成JSP
			if (jspFlag) {
				String jspPath = getEffectiveFileDir(JSP_FILE_PATH) + StringUtils.uncapitalize(classSimpleName)
						+ ".jsp";
				createFile(cfg, map, "jsp.ftlh", jspPath);
			}
			System.out.println("all done.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取非静态字段名
	 * 
	 * @param clazz
	 * @return
	 */
	private static List<String> getFiledName(Class<?> clazz) {
		List<String> fieldNames = Lists.newArrayList();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (!Modifier.isStatic(field.getModifiers())) {
				fieldNames.add(field.getName());
			}
		}
		return fieldNames;
	}

	/**
	 * 返回目录<br>
	 * 会自动在前面带上项目路径
	 * 
	 * @param virtualPath
	 */
	private static String getEffectiveFileDir(String virtualPath) {
		String realPath = PROJECT_DIRECTORY + File.separator;

		// 不做毫无意义的判空处理
		String[] pathFragment = virtualPath.split(PATH_SPLIT_REGEX);

		for (String fragment : pathFragment) {
			realPath += fragment + File.separator;
		}

		return realPath;
	}
	
	/**
	 * 
	 * @param cfg
	 *            模板
	 * @param map
	 *            参数
	 * @param templateName
	 * @param filePath
	 */
	private static void createFile(Configuration cfg, Map<String, Object> map, String templateName, String filePath)
			throws Exception {
		Template temp = cfg.getTemplate(templateName);
		File file = new File(filePath);
		if (!file.exists()) {
			Files.createParentDirs(file);
			file.createNewFile();
		}
		BufferedWriter servicelWrite = Files.newWriter(file, charset);
		temp.process(map, servicelWrite);
		System.out.println(templateName + " done.");
	}

	public static void main(String[] args) {
		generate(AtouUserProgress.class);
	}

}
