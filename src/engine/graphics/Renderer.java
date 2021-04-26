package engine.graphics;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import engine.graphics.meshes.ColorMesh;
import engine.graphics.meshes.TexturedMesh;
import engine.io.Window;
import engine.maths.Matrix4f;
import engine.maths.Vector3f;

public class Renderer {
	private Shader shader;
	
	private Window window;
	
	private Shader colorShader;
	private Shader textureShader;
	
	public Matrix4f getMatrix(Mesh m) {
		if (m.getCamera() == null) {
			return Matrix4f.identity();
		}
		return m.getCamera().getMatrix();
	}
	
	public Matrix4f getViewMatrix(Mesh m) {
		return m.getMatrix();
	}
	
	public Renderer(Shader shader, Window window) {
		this.shader = shader;
		this.colorShader = new Shader("ressources/shaders/color_mesh_vertex.glsl", "ressources/shaders/color_mesh_fragment.glsl", window);
		this.textureShader = new Shader("ressources/shaders/texture_mesh_vertex.glsl", "ressources/shaders/texture_mesh_fragment.glsl", window);
		this.window = window;
	}
	
	public void create() {
		window.setToCurrentGLContext();
		this.colorShader.create();
		this.textureShader.create();
	}
	
	public void renderMesh(ColorMesh mesh) {
		window.setToCurrentGLContext();
		
		// Bind Vao
		GL30.glBindVertexArray(mesh.getVAO());
		
		// Bind vertex position and color position (0 and 1)
		GL30.glEnableVertexAttribArray(0);
		GL30.glEnableVertexAttribArray(1);
		
		// Bind indices
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIBO());
		
		colorShader.bind();
		colorShader.setUniform("model", getMatrix(mesh));
		colorShader.setUniform("view", getViewMatrix(mesh));
		colorShader.setUniform("projection", window.getProjectionMatrix());
		
		// Draw
		GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndices().length, GL11.GL_UNSIGNED_INT, 0);
		
		colorShader.unbind();
		
		// Unbind Indices
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		
		// Disable vertex position and color position (0 and 1)
		GL30.glDisableVertexAttribArray(0);
		GL30.glDisableVertexAttribArray(1);
		
		// Unbind Vao by binding 0
		GL30.glBindVertexArray(0);
	}
	
	public void renderMesh(TexturedMesh mesh) {
		window.setToCurrentGLContext();
		
		// Bind Vao
		GL30.glBindVertexArray(mesh.getVAO());
		
		// Bind vertex position and texture coordinates (0 and 1)
		GL30.glEnableVertexAttribArray(0);
		GL30.glEnableVertexAttribArray(1);
		
		// Bind indices
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIBO());
		
		GL13.glEnable(GL13.GL_TEXTURE0);
		GL13.glBindTexture(GL11.GL_TEXTURE_2D, mesh.getMaterial().getTextureID());
		
		textureShader.bind();
		textureShader.setUniform("model", getMatrix(mesh));
		textureShader.setUniform("view", getViewMatrix(mesh));
		textureShader.setUniform("projection", window.getProjectionMatrix());
		
		// Draw
		GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndices().length, GL11.GL_UNSIGNED_INT, 0);
		
		textureShader.unbind();
		
		// Unbind Indices
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		
		// Disable vertex position and texture coordinates (0 and 1)
		GL30.glDisableVertexAttribArray(0);
		GL30.glDisableVertexAttribArray(1);
		
		// Unbind Vao by binding 0
		GL30.glBindVertexArray(0);
	}
	
	public void baseShaderRenderMesh(ColorMesh mesh) {
		window.setToCurrentGLContext();
		
		// Bind Vao
		GL30.glBindVertexArray(mesh.getVAO());
		
		// Bind vertex position and color position (0 and 1)
		GL30.glEnableVertexAttribArray(0);
		GL30.glEnableVertexAttribArray(1);
		
		// Bind indices
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIBO());
		
		shader.bind();
		shader.setUniform("model", getMatrix(mesh));
		shader.setUniform("view", getViewMatrix(mesh));
		shader.setUniform("projection", window.getProjectionMatrix());
		
		// Draw
		GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndices().length, GL11.GL_UNSIGNED_INT, 0);
		
		shader.unbind();
		
		// Unbind Indices
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		
		// Disable vertex position and color position (0 and 1)
		GL30.glDisableVertexAttribArray(0);
		GL30.glDisableVertexAttribArray(1);
		
		// Unbind Vao by binding 0
		GL30.glBindVertexArray(0);
	}
	
	public void baseShaderRenderMesh(TexturedMesh mesh) {
		window.setToCurrentGLContext();
		
		// Bind Vao
		GL30.glBindVertexArray(mesh.getVAO());
		
		// Bind vertex position and texture coordinates (0 and 1)
		GL30.glEnableVertexAttribArray(0);
		GL30.glEnableVertexAttribArray(1);
		
		// Bind indices
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIBO());
		
		GL13.glEnable(GL13.GL_TEXTURE0);
		GL13.glBindTexture(GL11.GL_TEXTURE_2D, mesh.getMaterial().getTextureID());
		
		shader.bind();
		shader.setUniform("model", getMatrix(mesh));
		shader.setUniform("view", getViewMatrix(mesh));
		shader.setUniform("projection", window.getProjectionMatrix());
		// Draw
		GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndices().length, GL11.GL_UNSIGNED_INT, 0);
		shader.unbind();
		
		// Unbind Indices
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		
		// Disable vertex position and texture coordinates (0 and 1)
		GL30.glDisableVertexAttribArray(0);
		GL30.glDisableVertexAttribArray(1);
		
		// Unbind Vao by binding 0
		GL30.glBindVertexArray(0);
	}
	
	public void renderMesh(Mesh mesh) {
		window.setToCurrentGLContext();
		
		// Bind Vao
		GL30.glBindVertexArray(mesh.getVAO());
		
		// Bind vertex position (0)
		GL30.glEnableVertexAttribArray(0);
		
		// Bind indices
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIBO());
		
		shader.bind();
		shader.setUniform("model", getMatrix(mesh));
		shader.setUniform("view", getViewMatrix(mesh));
		shader.setUniform("projection", window.getProjectionMatrix());
		
		// Draw
		GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndices().length, GL11.GL_UNSIGNED_INT, 0);
		shader.unbind();
		
		// Unbind Indices
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		
		// Disable vertex position (0)
		GL30.glDisableVertexAttribArray(0);
		
		// Unbind Vao by binding 0
		GL30.glBindVertexArray(0);
	}
	
	public void destroy() {
		shader.destroy();
		textureShader.destroy();
		colorShader.destroy();
	}

}
