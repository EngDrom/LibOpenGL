package org.EngDrom.LibOpenGL.engine.graphics.vertex;

import org.EngDrom.LibOpenGL.engine.graphics.Vertex;
import org.EngDrom.LibOpenGL.engine.maths.Vector2f;
import org.EngDrom.LibOpenGL.engine.maths.Vector3f;

public class TexturedVertex extends Vertex {
	
	private Vector2f tcoord;

	public TexturedVertex(Vector3f pos, Vector2f text_coord) {
		super(pos);
		tcoord = text_coord;
	}

	public Vector2f getTextureCoordinates() {
		return tcoord;
	}

}
