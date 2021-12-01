package org.EngDrom.LibOpenGL.engine.graphics;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.EngDrom.LibOpenGL.engine.maths.Matrix4f;
import org.EngDrom.LibOpenGL.engine.maths.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

public class Mesh {

	// Storing the Mesh data
	protected Vertex[] vertices;
	private int[] indices;
	private Camera camera;
	
	// OpenGL vertex array id, position buffer id and indices buffer id
	private int vao, pbo, ibo;
	
	public Mesh(Vertex[] vertices, int[] indices) {
		this.vertices = vertices;
		this.indices = indices;
	}
	
	public void bindCamera(Camera c) {
		camera = c;
	}
	
	public Camera getCamera() {
		return camera;
	}
	
	public void create() {
		// Generate vertex array
		vao = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vao);
		
		// Generate position buffer
		FloatBuffer positionsBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
		float[] positionData = new float[vertices.length * 3];
		for (int i = 0; i < vertices.length; i++) {
			positionData[i * 3] = vertices[i].getPosition().getX();
			positionData[i * 3 + 1] = vertices[i].getPosition().getY();
			positionData[i * 3 + 2] = vertices[i].getPosition().getZ();
		}
		((Buffer)positionsBuffer.put(positionData)).flip();
		
		// Store color buffer
		pbo = storeData(positionsBuffer, 0, 3);

		// Generate indices buffer
		IntBuffer indicesBuffer = MemoryUtil.memAllocInt(indices.length);
		((Buffer)indicesBuffer.put(indices)).flip();
		
		// Store indices buffer
		ibo = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	protected int storeData(FloatBuffer buffer, int index, int size) {
		int bufferID = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, bufferID);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(index, size, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		return bufferID;
	}

	public void destroy() {
		GL15.glDeleteBuffers(pbo);
		GL15.glDeleteBuffers(ibo);
		
		GL30.glDeleteVertexArrays(vao);
	}
	
	public Vertex[] getVertices() {
		return vertices;
	}

	public int[] getIndices() {
		return indices;
	}

	public int getVAO() {
		return vao;
	}

	public int getPBO() {
		return pbo;
	}

	public int getIBO() {
		return ibo;
	}


	private Vector3f scalar = new Vector3f(1, 1, 1);
	
	public void setScalar(Vector3f vector3f) {
		this.scalar = vector3f;
		this.buildMatrix();
	}
	
	public void multScalar(Vector3f other) {
		this.scalar = scalar.multiply(other);
		this.buildMatrix();
	}
	
	public Vector3f getScalar() {
		return scalar;
	}
	
	private Vector3f cached_translation = new Vector3f(0, 0, 0);
	private Vector3f translation = new Vector3f(0, 0, 0);
	private Vector3f rot_axis = new Vector3f(0, 0, 0);
	
	private Matrix4f cache;
	
	public void setRotation(Vector3f rot_axis) {
		this.rot_axis = rot_axis;
		this.buildMatrix();
	}
	
	public void setTranslation(Vector3f translation) {
		this.translation = translation;
		this.buildMatrix();
	}
	
	private void buildMatrix() {
		this.build_cached_translation();
		cache = Matrix4f.transform(cached_translation, rot_axis, scalar);
	}
	
	private void build_cached_translation() {
		cached_translation = translation.divide(scalar);
	}
	
	public Matrix4f getMatrix() {
		if (cache == null) this.buildMatrix();
		
		return cache;
	}

	public void addTranslation(Vector3f translation) {
		this.translation = this.translation.add(translation);
		this.buildMatrix();
	}
	
	public void addRotation(Vector3f rotation) {
		this.rot_axis = this.rot_axis.add(rotation);
		this.buildMatrix();
	}

	public Vector3f getTranslation() {
		return translation;
	}

	public Vector3f getRotation() {
		return rot_axis;
	}

	
}
