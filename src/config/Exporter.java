package config;

import java.util.HashMap;

import org.lwjgl.glfw.GLFW;

import engine.graphics.Camera;
import engine.graphics.Material;
import engine.graphics.Renderer;
import engine.graphics.Shader;
import engine.graphics.meshes.TexturedMesh;
import engine.io.Window;
import engine.maths.Vector3f;
import engine.utils.FileUtils;
import exports.CameraNode;
import exports.Constants;
import exports.MaterialNode;
import exports.RawOpenGL;
import exports.WindowNode;
import libs.LibExporter;
import parser.Node;
import variables.ClassNode;

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
		
		Camera camera = new Camera();
		
		TexturedMesh m = FileUtils.readObjFile("ressources/cube.obj", new Material("ressources/img.png"));
		
		Shader s = new Shader("ressources/shaders/vertex.glsl", "ressources/shaders/fragment.glsl", w);
		
		Renderer r = new Renderer(s, w);
		w.setBackgroundColor(1.0f, 0, 0);
		w.create();
		
		m.create();
		m.bindCamera(camera);
		m.addTranslation(new Vector3f(1, 0, 0));
		camera.addTranslation(new Vector3f(-1, 0, -1));
		r.create();
		s.create();
		while(!w.shouldClose() && !w.getInput().isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
			w.update();
			
			r.renderMesh(m);
			camera.addTranslation(new Vector3f(0, 0, -1f));
			
			w.swapBuffers();
		}
		w.destroy();
		m.destroy();
		s.destroy();
	}

}
