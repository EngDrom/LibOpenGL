package org.EngDrom.LibOpenGL.engine.graphics.font;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

import org.EngDrom.LibOpenGL.engine.graphics.Material;
import org.EngDrom.StringParser.FntLineConfig;
import org.EngDrom.StringParser.FntLineParser;

public class FontManager {
	
	public static Font getFont(String png_path, String fnt_path) {
		Font fnt = new Font();
		
		try {
			File       fnt_file   = new File(fnt_path); 
			FileReader fnt_reader = new FileReader(fnt_file);
			
			BufferedReader bf_fnt_reader = new BufferedReader(fnt_reader);
		
			float width  = 0;
			float height = 0;
			
			String   line;
			while ( (line = bf_fnt_reader.readLine()) != null ) {
				FntLineConfig conf = FntLineParser.parse(line);
				
				if (conf.args.contains("char")) {
					FontCharacter chr = new FontCharacter(width, height, conf);
					fnt.characters.put(chr.chr, chr);
				} else if (conf.args.contains("common")) {
					width  = Float.parseFloat(conf.kwargs.getOrDefault("scaleW", "100"));
					height = Float.parseFloat(conf.kwargs.getOrDefault("scaleH", "100"));
					fnt.base = Integer.parseInt(conf.kwargs.getOrDefault("base", "100"));
				}
			}
			
			fnt.width  = width;
			fnt.height = height;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		fnt.font_material = new Material(png_path);
		return fnt;
	}
	
	public static void main(String[] args) {
		System.out.println("TEST");
		Font fnt = FontManager.getFont(null, "./ressources/font/calibri.fnt");
		fnt.to_text("this is a text");
	}

}
