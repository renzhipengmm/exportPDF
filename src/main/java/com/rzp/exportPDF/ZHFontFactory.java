package com.rzp.exportPDF;

import java.io.File;
import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactoryImp;
import com.itextpdf.text.pdf.BaseFont;

public class ZHFontFactory extends FontFactoryImp {

	private static BaseFont baseFont = null;

	static {

		try {
			baseFont = BaseFont.createFont(ZHFontFactory.class.getClassLoader().getResource("pdftemp").getFile() + File.separator + "simfang.ttf", BaseFont.IDENTITY_H,
					BaseFont.NOT_EMBEDDED);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Font getFont(String fontname, String encoding, boolean embedded, float size, int style, BaseColor color) {
		Font font = new Font(baseFont);
		font.setSize(size);
		font.setStyle(style);
		font.setColor(color);
		return font;
	}
}
