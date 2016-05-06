package com.rzp.exportPDF;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class App {
	public static void main(String[] args) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("week", new String[] { "星期一" + System.currentTimeMillis(), "星期二", "星期三", "星期四", "星期五", "星期六", "星期天" });
		ExportPDFUtil.exportTemplateHTML2PDF("test1.ftl", model, new File("e:\\out.pdf"));
	}
}
