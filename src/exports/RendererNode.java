package exports;

import engine.graphics.Mesh;
import engine.graphics.Renderer;
import engine.graphics.Shader;
import engine.graphics.meshes.ColorMesh;
import engine.graphics.meshes.TexturedMesh;
import engine.io.Window;
import main.EntryPoint;
import variables.ClassNode;
import variables.NativeMethodNode;

public class RendererNode extends ClassNode {

	private Renderer renderer;
	private Shader sc;
	
	public RendererNode(int col, int line, String vertexPath, String fragmentPath, Window window) {
		super(col, line);
		this.typeName = "opengl.builtin.Renderer";
		isRoot = false;
		
		sc = new Shader(vertexPath, fragmentPath, window);
		renderer = new Renderer(sc, window);
		
		try {

		this.objects.put("create", new NativeMethodNode(-2, -2, typeName+".create", false, RendererNode.class.getMethod("create", new Class[] {})));
		this.objects.put("destroy", new NativeMethodNode(-2, -2, typeName+".destroy", false, RendererNode.class.getMethod("destroy", new Class[] {})));

		this.objects.put("render", new NativeMethodNode(-2, -2, typeName+".render", false, RendererNode.class.getMethod("renderMesh", new Class[] {MeshNode.class})));
		this.objects.put("renderTextured", new NativeMethodNode(-2, -2, typeName+".renderTextured", false, RendererNode.class.getMethod("renderTexturedMesh", new Class[] {MeshNode.class})));
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error while loading OpenGL library, one of the native method could not be loaded");
		}
	}
	
	public void create() {
		sc.create();
		renderer.create();
	}
	
	public void destroy() {
		renderer.destroy();
	}
	
	public void renderTexturedMesh(MeshNode mn) {
		if (mn.getMesh() instanceof TexturedMesh) {
			renderer.renderMesh((TexturedMesh)mn.getMesh());
		} else {
			EntryPoint.raiseErr("Expected textured mesh in input of renderTexturedMesh, got "+mn.getMesh().getClass());
		}
	}
	
	public void renderColoredMesh(MeshNode mn) {
		if (mn.getMesh() instanceof TexturedMesh) {
			renderer.renderMesh((TexturedMesh)mn.getMesh());
		} else {
			EntryPoint.raiseErr("Expected textured mesh in input of renderTexturedMesh, got "+mn.getMesh().getClass());
		}
	}
	
	public void renderMesh(MeshNode mn) {
		if (mn.getMesh() instanceof TexturedMesh) {
			renderer.baseShaderRenderMesh((TexturedMesh)mn.getMesh());
		} else if (mn.getMesh() instanceof ColorMesh) {
			renderer.baseShaderRenderMesh((ColorMesh)mn.getMesh());
		} else if (mn.getMesh() instanceof Mesh) {
			renderer.renderMesh(mn.getMesh());
		} else {
			EntryPoint.raiseErr("Expected textured mesh or colored mesh or mesh in input of renderTexturedMesh, got "+mn.getMesh().getClass());
		}
	}

}
