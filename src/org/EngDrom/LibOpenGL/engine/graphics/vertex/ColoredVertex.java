package org.EngDrom.LibOpenGL.engine.graphics.vertex;

import org.EngDrom.LibOpenGL.engine.graphics.Vertex;
import org.EngDrom.LibOpenGL.engine.maths.Vector3f;

public class ColoredVertex extends Vertex{
	private Vector3f color;
	
	public ColoredVertex(Vector3f pos, Vector3f color) {
		super(pos);
		this.color = color;
	}

	public Vector3f getColor() {
		return color;
	}
}
