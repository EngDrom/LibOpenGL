package exports;

import java.util.ArrayList;

import config.Exporter;
import engine.graphics.Mesh;
import engine.graphics.meshes.TexturedMesh;
import engine.io.Window;
import engine.utils.FileUtils;
import main.EntryPoint;
import parser.nodes.BooleanNode;
import parser.nodes.NumberNode;
import variables.ClassNode;
import variables.NativeMethodNode;
import variables.VariableContext;

public class WindowNode extends ClassNode {
	
	protected WindowNode(ClassNode other, ArrayList<Object> args) {
		super(other, args);
		this.typeName = "opengl.builtin.Window";
		
		checkSize(args, 3);
		
		int width = getIntegerValue(args, 0);
		int height = getIntegerValue(args, 1);
		String title = getStringValue(args, 2);
		
		if (!Exporter.GLFW_INITIALIZED) {
			Exporter.init();
		}
		
		window = new Window(width, height, title);
	}
	
	public void create() {
		window.create();
	}
	
	public void destroy() {
		window.destroy();
	}
	
	public void update() {
		window.update();
	}
	
	public void flush() {
		window.swapBuffers();
	}
	
	public void setBackgroundColor(float r, float g, float b) {
		window.setBackgroundColor(r, g, b);
	}
	
	public BooleanNode shouldClose() {
		return new BooleanNode(-1, -1, window.shouldClose());
	}
	
	public RendererNode createRenderer(String vertexPath, String fragmentPath) {
		window.setToCurrentGLContext();
		
		// Create Renderer node and return it
		// A renderer node contains a renderer and a shader
		RendererNode rn = new RendererNode(-1, -1, vertexPath, fragmentPath, window);
		return rn;
	}
	
	public MeshNode readMesh(MaterialNode matNode, String path, int type_extension, int type) {
		window.setToCurrentGLContext();
		
		// An Obj file can only build a textured mesh
		if (type_extension == Constants.OBJ_FILE
				&& type == Constants.TEXTURED_MESH) {
			Mesh mesh = FileUtils.readObjFile(path, matNode.getMaterial());
			
			MeshNode node = new MeshNode(-1, -1, mesh, window);
			
			return node;
		}
		
		// Create a Mesh node and return it
		// A Mesh node contains a mesh (normal, colored or textured)
		return null;
	}
	
	public WindowNode(int col, int line) {
		super(col, line);
		this.typeName = "opengl.builtin.Window";
		
		try {
		
		this.objects.put("create", new NativeMethodNode(-2, -2, typeName+".create", false, WindowNode.class.getMethod("create", new Class[] {})));
		this.objects.put("destroy", new NativeMethodNode(-2, -2, typeName+".destroy", false, WindowNode.class.getMethod("destroy", new Class[] {})));
		this.objects.put("update", new NativeMethodNode(-2, -2, typeName+".update", false, WindowNode.class.getMethod("update", new Class[] {})));
		this.objects.put("flush", new NativeMethodNode(-2, -2, typeName+".flush", false, WindowNode.class.getMethod("flush", new Class[] {})));
		this.objects.put("shouldClose", new NativeMethodNode(-2, -2, typeName+".shouldClose", false, WindowNode.class.getMethod("shouldClose", new Class[] {})));
		
		this.objects.put("createRenderer", new NativeMethodNode(-2, -2, typeName+".createRenderer", false, WindowNode.class.getMethod("createRenderer", new Class[] {String.class, String.class})));
		this.objects.put("readMesh", new NativeMethodNode(-2, -2, typeName+".readMesh", false, WindowNode.class.getMethod("readMesh", new Class[] {MaterialNode.class, String.class, int.class, int.class})));
		this.objects.put("setBackgroundColor", new NativeMethodNode(-2, -2, typeName+".setBackgroundColor", false, WindowNode.class.getMethod("setBackgroundColor", new Class[] {float.class, float.class, float.class})));
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error while loading OpenGL library, one of the native method could not be loaded");
		}
	}

	public Object createInstance(VariableContext context, ArrayList<Object> args) {
		if(!isRoot) {
			System.out.println("Can't create instance with sub child");
			return null;
		}
		return new WindowNode(this, args);
	}
	
	public Window window;

	public Window getWindow() {
		return window;
	}
	
}
