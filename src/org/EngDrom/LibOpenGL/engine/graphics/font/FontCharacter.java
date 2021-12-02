package org.EngDrom.LibOpenGL.engine.graphics.font;

import org.EngDrom.StringParser.FntLineConfig;

public class FontCharacter {

	public final char chr;
	public final int  xadvance;
	public final int  xoff;
	public final int  yoff;
	
	// Texture coordinates
	public final float tx0;
	public final float ty0;
	public final float tx1;
	public final float ty1;
	
	public final float width;
	public final float height;
	
	public FontCharacter (char chr, int xadvance, float tx0, float ty0, float tx1, float ty1, int xoff, int yoff) {
		this.chr      = chr;
		this.xadvance = xadvance;
		this.tx0      = tx0;
		this.ty0      = ty0;
		this.tx1      = tx1;
		this.ty1      = ty1;
		this.width    = tx1 - tx0;
		this.height   = ty1 - ty0;
		this.xoff     = xoff;
		this.yoff     = yoff;
	}
	
	public FontCharacter ( float max_width, float max_height, FntLineConfig conf ) {
		char chrcp  = (char) Integer.parseInt(conf.kwargs.get("id"));
		int  xadvcp = Integer.parseInt(conf.kwargs.get("xadvance"));
		int  tx0cp  = Integer.parseInt(conf.kwargs.get("x"));
		int  ty0cp  = Integer.parseInt(conf.kwargs.get("y"));
		int  tx1cp  = Integer.parseInt(conf.kwargs.get("width"));
		int  ty1cp  = Integer.parseInt(conf.kwargs.get("height")); 
		int  xoffcp = Integer.parseInt(conf.kwargs.get("xoffset"));
		int  yoffcp = Integer.parseInt(conf.kwargs.get("yoffset"));
	
		this.chr      = chrcp;
		this.xadvance = xadvcp;
		this.tx0      = tx0cp;
		this.tx1      = tx1cp + tx0cp;
		this.ty0      = ty0cp;
		this.ty1      = ty1cp + ty0cp;
		this.width    = tx1cp;
		this.height   = ty1cp;
		this.xoff     = xoffcp;
		this.yoff     = yoffcp;
	}
	
}
