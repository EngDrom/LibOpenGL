package org.EngDrom.LibOpenGL.engine.graphics.meshes;

import java.nio.Buffer;
import java.nio.FloatBuffer;

import org.EngDrom.LibOpenGL.engine.graphics.Mesh;
import org.EngDrom.LibOpenGL.engine.graphics.Vertex;
import org.EngDrom.LibOpenGL.engine.graphics.vertex.ColoredVertex;
import org.lwjgl.opengl.GL15;
import org.lwjgl.system.MemoryUtil;

public class ColorMesh extends Mesh{

	private int cbo;
	
	public ColorMesh(Vertex[] vertices, int[] indices) {
		super(vertices, indices);
	}
	
	public void create() {
		super.create();
		// Generate color buffer
		FloatBuffer colorBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
		float[] colorData = new float[vertices.length * 3];
		for (int i = 0; i < vertices.length; i++) {
			if (vertices[i] instanceof ColoredVertex) {
				colorData[i * 3] = ((ColoredVertex)vertices[i]).getColor().getX();
				colorData[i * 3 + 1] = ((ColoredVertex)vertices[i]).getColor().getY();
				colorData[i * 3 + 2] = ((ColoredVertex)vertices[i]).getColor().getZ();
			} else {
				System.out.println("Expected colored vertex, got other type");
			}
		}
		
		((Buffer)colorBuffer.put(colorData)).flip();

		// Store color buffer
		cbo = storeData(colorBuffer, 1, 3);
	}

	public int getCBO() {
		return cbo;
	}
	
	public void destroy() {
		GL15.glDeleteBuffers(cbo);
		
		super.destroy();
	}
	
}
