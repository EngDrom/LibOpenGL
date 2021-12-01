package org.EngDrom.LibOpenGL.config;

import org.EngDrom.LibOpenGL.engine.graphics.Camera;
import org.EngDrom.LibOpenGL.engine.graphics.Renderer;
import org.EngDrom.LibOpenGL.engine.graphics.Shader;
import org.EngDrom.LibOpenGL.engine.graphics.meshes.GUIMesh;
import org.EngDrom.LibOpenGL.engine.graphics.vertex.TexturedVertex;
import org.EngDrom.LibOpenGL.engine.io.Window;
import org.EngDrom.LibOpenGL.engine.maths.Vector2f;
import org.EngDrom.LibOpenGL.engine.maths.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjglx.util.vector.Vector4f;

public class Exporter {

	public static boolean GLFW_INITIALIZED = false;
	public static void init() {
		GLFW.glfwInit();
		GLFW_INITIALIZED = true;
	}
	
	public static void main(String[] args) {
		init();

		double temp = 0;
		
		Window w = new Window(1000, 800, "TestWindow");
		
		TexturedVertex[] vertices = new TexturedVertex[] {
				new TexturedVertex(new Vector3f(0, 0, 0), new Vector2f(0, 0)),
				new TexturedVertex(new Vector3f(1, 0, 0), new Vector2f(1, 0)),
				new TexturedVertex(new Vector3f(0, 1, 0), new Vector2f(0, 1)),
				new TexturedVertex(new Vector3f(1, 1, 0), new Vector2f(1, 1)),
		};
		int[] indices = new int[] {
				0, 1, 2,
				1, 2, 3
		};
		
		Camera camera = new Camera();
		
		GUIMesh mesh = new GUIMesh(new Vector4f(0.5f, 0.5f, 0.5f, 1));
		
		Shader s = new Shader("ressources/shaders/vertex.glsl", "ressources/shaders/fragment.glsl", w);
		
		Renderer r = new Renderer(s, w);
		w.setBackgroundColor(0, 0, 0);
		w.create();
		
		mesh.create();
		mesh.setScalar(new Vector3f(0.25f, 1f, 1f));
		mesh.addTranslation(new Vector3f(0.5f, 0f, 0f));
	
		r.create();
		s.create();
		while(!w.shouldClose() && !w.getInput().isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
			w.update();
			
			r.renderMesh(mesh);
			
			w.swapBuffers();
		}
		w.destroy();
		mesh.destroy();
		s.destroy();
	}

}
