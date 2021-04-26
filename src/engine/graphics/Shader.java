package engine.graphics;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryUtil;

import engine.io.Window;
import engine.maths.Matrix4f;
import engine.maths.Vector2f;
import engine.maths.Vector3f;
import engine.utils.FileUtils;

public class Shader {
	// Shaders Code
	private String vertexFile, fragmentFile;
	// Shaders ID and Program ID
	private int vertexID, fragmentID, programID;
	
	private Window window;

	// Load shaders code
	public Shader(String vertexPath, String fragmentPath, Window window) {
		this.vertexFile = FileUtils.loadAsString(vertexPath);
		this.fragmentFile = FileUtils.loadAsString(fragmentPath);
		this.window = window;
	}
	
	// Create program
	public void create() {
		window.setToCurrentGLContext();
		
		// Create program
		programID = GL20.glCreateProgram();
		
		// Create Vertex Shader
		vertexID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
		
		// Bind source code and compile
		GL20.glShaderSource(vertexID, vertexFile);
		GL20.glCompileShader(vertexID);
		
		if (GL20.glGetShaderi(vertexID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.out.println("An error occured, vertex shader couldn't compile :");
			System.out.println(GL20.glGetShaderInfoLog(vertexID));
			return ;
		}
		
		// Create Fragment Shader
		fragmentID = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
		
		// Bind source code and compile
		GL20.glShaderSource(fragmentID, fragmentFile);
		GL20.glCompileShader(fragmentID);
		
		if (GL20.glGetShaderi(fragmentID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.out.println("An error occured, fragment shader couldn't compile :");
			System.out.println(GL20.glGetShaderInfoLog(fragmentID));
			return ;
		}
		
		// Attach Shaders
		GL20.glAttachShader(programID, vertexID);
		GL20.glAttachShader(programID, fragmentID);
		
		// Link program
		GL20.glLinkProgram(programID);
		if (GL20.glGetProgrami(programID, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {
			System.out.println("Program Linking: " + GL20.glGetProgramInfoLog(programID));
			return;
		}
		
		// validate program
		GL20.glValidateProgram(programID);
		if (GL20.glGetProgrami(programID, GL20.GL_VALIDATE_STATUS) == GL11.GL_FALSE) {
			System.out.println("Program Validation: " + GL20.glGetProgramInfoLog(programID));
			return;
		}
	}
	
	public int getUniformLocation(String name) {
		return GL20.glGetUniformLocation(programID, name);
	}
	
	public void setUniform(String name, float value) {
		GL20.glUniform1f(getUniformLocation(name), value);
	}
	
	public void setUniform(String name, int value) {
		GL20.glUniform1i(getUniformLocation(name), value);
	}
	
	public void setUniform(String name, boolean value) {
		GL20.glUniform1i(getUniformLocation(name), value ? 1 : 0);
	}
	
	public void setUniform(String name, Vector2f value) {
		GL20.glUniform2f(getUniformLocation(name), value.getX(), value.getY());
	}
	
	public void setUniform(String name, Vector3f value) {
		GL20.glUniform3f(getUniformLocation(name), value.getX(), value.getY(), value.getZ());
	}
	
	public void setUniform(String name, Matrix4f value) {
		FloatBuffer buf = MemoryUtil.memAllocFloat(value.SIZE * value.SIZE);
		buf.put(value.getAll()).flip();
		GL20.glUniformMatrix4fv(getUniformLocation(name), true, buf);
	}
	
	// Bind program
	public void bind() {
		window.setToCurrentGLContext();
		GL20.glUseProgram(programID);
	}
	
	// Unbind program
	public void unbind() {
		window.setToCurrentGLContext();
		GL20.glUseProgram(0);
	}
	
	// Clear program
	public void destroy() {
		window.setToCurrentGLContext();
		GL20.glDetachShader(programID, vertexID);
		GL20.glDetachShader(programID, fragmentID);
		GL20.glDeleteShader(vertexID);
		GL20.glDeleteShader(fragmentID);
		GL20.glDeleteProgram(programID);
	}
}
