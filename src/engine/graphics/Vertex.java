package engine.graphics;

import engine.maths.Vector3f;

public class Vertex {
	private Vector3f position;
	
	public Vertex(Vector3f pos) {
		this.position = pos;
	}
	
	public Vector3f getPosition() {
		return position;
	}
}
