package org.EngDrom.LibOpenGL.engine.graphics.font;

import java.util.HashMap;

import org.EngDrom.LibOpenGL.engine.graphics.Material;
import org.EngDrom.LibOpenGL.engine.graphics.meshes.GUIMesh;
import org.EngDrom.LibOpenGL.engine.graphics.meshes.TextMesh;
import org.EngDrom.LibOpenGL.engine.graphics.vertex.TexturedVertex;
import org.EngDrom.LibOpenGL.engine.maths.Vector2f;
import org.EngDrom.LibOpenGL.engine.maths.Vector3f;

public class Font {

	public int base;
	public Material font_material;
	public HashMap<Character, FontCharacter> characters = new HashMap<Character, FontCharacter>();
	public float width;
	public float height;

	public TextMesh to_text( String text ) {
		float xmax  = 0;
		float ymax  = 0;
		
		float cur_x = 0;
		
		for (int idx = 0; idx < text.length(); idx ++) {
			char chr = text.charAt(idx);
			if (!characters.containsKey(chr) && chr != '\n') return null;
			
			if (chr == '\n') {
				if (xmax < cur_x) xmax = cur_x;
				
				ymax  += base;
				cur_x  = 0;
			} else {
				cur_x += characters.get(chr).xadvance;
			}
		}
		
		if (xmax < cur_x) xmax = cur_x;
		ymax += base;
		
		int cury = ((int)ymax) - base;
		int curx = 0;
		
		TexturedVertex[] vertices = new TexturedVertex[text.length() * 4];
		int[]            indices  = new int[text.length() * 6];
		for (int idx = 0; idx < text.length(); idx ++) {
			char chr = text.charAt(idx);
			
			if (chr == '\n') {
				cury -= base;
				curx  = 0;
				
				vertices[idx * 4]     = new TexturedVertex(new Vector3f(0, 0, 0), new Vector2f(0, 0));
				vertices[idx * 4 + 1] = new TexturedVertex(new Vector3f(0, 0, 0), new Vector2f(0, 0));
				vertices[idx * 4 + 2] = new TexturedVertex(new Vector3f(0, 0, 0), new Vector2f(0, 0));
				vertices[idx * 4 + 3] = new TexturedVertex(new Vector3f(0, 0, 0), new Vector2f(0, 0));
			} else {
				FontCharacter fnt_chr = characters.get(chr);
				float sx = ((float)curx + fnt_chr.xoff) / xmax * 2 - 1;
				float dx = ((float)fnt_chr.width)       / xmax * 2;
				float sy = ((float)cury + (base - fnt_chr.height) - fnt_chr.yoff) / ymax * 2 - 1;
				float dy = ((float)fnt_chr.height)      / ymax * 2;
				
				float stx = fnt_chr.tx0 / width;
				float etx = fnt_chr.tx1 / width;
				float sty = fnt_chr.ty0 / width;
				float ety = fnt_chr.ty1 / width;
				
				vertices[idx * 4]     = new TexturedVertex(new Vector3f(sx,      sy,      0), new Vector2f(stx, ety));
				vertices[idx * 4 + 1] = new TexturedVertex(new Vector3f(sx + dx, sy,      0), new Vector2f(etx, ety));
				vertices[idx * 4 + 2] = new TexturedVertex(new Vector3f(sx,      sy + dy, 0), new Vector2f(stx, sty));
				vertices[idx * 4 + 3] = new TexturedVertex(new Vector3f(sx + dx, sy + dy, 0), new Vector2f(etx, sty));
				
				curx += fnt_chr.xadvance;
			}
			
			indices[idx * 6]     = idx * 4;
			indices[idx * 6 + 1] = idx * 4 + 1;
			indices[idx * 6 + 2] = idx * 4 + 2;
			indices[idx * 6 + 3] = idx * 4 + 1;
			indices[idx * 6 + 4] = idx * 4 + 2;
			indices[idx * 6 + 5] = idx * 4 + 3;
		}
		
		return new TextMesh(vertices, indices, font_material, xmax / ymax);
	}
	
}
