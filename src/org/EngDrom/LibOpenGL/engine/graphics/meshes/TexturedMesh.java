package org.EngDrom.LibOpenGL.engine.graphics.meshes;

import java.nio.Buffer;
import java.nio.FloatBuffer;

import org.EngDrom.LibOpenGL.engine.graphics.Material;
import org.EngDrom.LibOpenGL.engine.graphics.Mesh;
import org.EngDrom.LibOpenGL.engine.graphics.Vertex;
import org.EngDrom.LibOpenGL.engine.graphics.vertex.TexturedVertex;
import org.EngDrom.LibOpenGL.engine.maths.Vector3f;
import org.lwjgl.opengl.GL15;
import org.lwjgl.system.MemoryUtil;

public class TexturedMesh extends Mesh {

	private Material mat;
	private int tcbo;
	
	public TexturedMesh(Vertex[] vertices, int[] indices, Material mat) {
		super(vertices, indices);
		this.mat = mat;
	}
	
	public void create() {
		super.create();
		
		if (mat != null)
			mat.create();
		
		// Generate texture coordinates buffer
		FloatBuffer textureCoordinatesBuffer = MemoryUtil.memAllocFloat(vertices.length * 2);
		float[] tCoData = new float[vertices.length * 2];
		for (int i = 0; i < vertices.length; i++) {
			if (vertices[i] instanceof TexturedVertex) {
				tCoData[i * 2] = ((TexturedVertex)vertices[i]).getTextureCoordinates().getX();
				tCoData[i * 2 + 1] = ((TexturedVertex)vertices[i]).getTextureCoordinates().getY();
			} else {
				System.out.println("Expected textured vertex, got other type");
			}
		}

		((Buffer)textureCoordinatesBuffer.put(tCoData)).flip();

		// Store texture coordinates buffer
		tcbo = storeData(textureCoordinatesBuffer, 1, 2);
	}
	
	public int getTCBO() {
		return tcbo;
	}
	
	public Material getMaterial() {
		return mat;
	}
	
	public void destroy() {
		GL15.glDeleteBuffers(tcbo);
		
		if (mat != null)
			mat.destroy();
		
		super.destroy();
	}

}
