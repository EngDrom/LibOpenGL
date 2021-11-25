package org.EngDrom.LibOpenGL.engine.graphics;

import org.EngDrom.LibOpenGL.engine.maths.Vector3f;

public class Vertex {
	private Vector3f position;
	
	public Vertex(Vector3f pos) {
		this.position = pos;
	}
	
	public Vector3f getPosition() {
		return position;
	}
}
