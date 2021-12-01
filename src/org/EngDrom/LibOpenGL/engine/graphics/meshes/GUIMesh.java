package org.EngDrom.LibOpenGL.engine.graphics.meshes;

import org.EngDrom.LibOpenGL.engine.graphics.Material;
import org.EngDrom.LibOpenGL.engine.graphics.vertex.TexturedVertex;
import org.EngDrom.LibOpenGL.engine.maths.Vector2f;
import org.EngDrom.LibOpenGL.engine.maths.Vector3f;
import org.lwjglx.util.vector.Vector4f;

public class GUIMesh extends TexturedMesh {

	public static final TexturedVertex[] vertices = new TexturedVertex[] {
			new TexturedVertex(new Vector3f(-1, -1, 0), new Vector2f(0, 0)),
			new TexturedVertex(new Vector3f( 1, -1, 0), new Vector2f(1, 0)),
			new TexturedVertex(new Vector3f(-1,  1, 0), new Vector2f(0, 1)),
			new TexturedVertex(new Vector3f( 1,  1, 0), new Vector2f(1, 1))
	};
	public static final int[] indices = new int [] {
			0, 1, 2,
			1, 2, 3
	};
	
	public GUIMesh(String str) {	
		super(vertices, indices, new Material("./ressources/" + str));
	}
	
	public Vector4f color;
	public boolean isTexture = true;
	public GUIMesh(Vector4f color) {
		super(vertices, indices, null);
		isTexture = false;
		this.color = color;
	}
	
}
