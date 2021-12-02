package org.EngDrom.LibOpenGL.engine.graphics.meshes;

import org.EngDrom.LibOpenGL.engine.graphics.Material;
import org.EngDrom.LibOpenGL.engine.graphics.vertex.TexturedVertex;

public class TextMesh extends GUIMesh {

	public final float ratio_woh;
	
	public TextMesh(TexturedVertex[] vertices, int[] indices, Material mat, float ratio_woh) {
		super(vertices, indices, mat);
		this.ratio_woh = ratio_woh;
	}

}
