package com.rzp.exportPDF;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.Map;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontProvider;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class ExportPDFUtil {
	public static final String TEMPLATE_PATH = ExportPDFUtil.class.getClassLoader().getResource("pdftemp").getFile();

	private static Configuration template_cfg = new Configuration(Configuration.VERSION_2_3_23);

	private static final FontProvider fontProvider = new ZHFontFactory();

	private static final String DEFAULT_CHARTSET = "UTF-8";

	static {
		try {
			template_cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
			template_cfg.setDefaultEncoding(DEFAULT_CHARTSET);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Template getTemplate(String name) {
		Template template = null;
		try {
			template = template_cfg.getTemplate(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return template;
	}

	public static String getSrcData(String name, Map<String, Object> model) {
		StringWriter stringWriter = new StringWriter();
		try {
			Template template = getTemplate(name);
			template.process(model, stringWriter);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringWriter.toString();
	}

	public static void exportPDF2Out(String src, File file) {
		try {
			exportPDF2Out(src, new BufferedOutputStream(new FileOutputStream(file)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void exportTemplateHTML2PDF(String templateName, Map<String, Object> model, File file) {
		String srcData = getSrcData(templateName, model);
		exportPDF2Out(srcData, file);
	}

	public static void exportPDF2Out(String src, OutputStream out) {
		Document document = new Document();
		PdfWriter writer = null;
		try {
			writer = PdfWriter.getInstance(document, out);
			document.open();
			XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(src.getBytes(DEFAULT_CHARTSET)), Charset.forName(DEFAULT_CHARTSET), fontProvider);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null)
				writer.flush();
			document.close();
			if (writer != null)
				writer.close();
		}
	}

	public static void exportTemplateHTML2PDF(String templateName, Map<String, Object> model, OutputStream out) {
		String srcData = getSrcData(templateName, model);
		exportPDF2Out(srcData, out);
	}

}
