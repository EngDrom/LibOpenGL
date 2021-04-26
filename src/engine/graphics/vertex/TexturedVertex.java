package engine.graphics.vertex;

import engine.graphics.Vertex;
import engine.maths.Vector2f;
import engine.maths.Vector3f;

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
