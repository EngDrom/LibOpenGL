package exports;

import engine.graphics.Mesh;
import engine.graphics.meshes.TexturedMesh;
import engine.io.Window;
import engine.maths.Vector3f;
import parser.nodes.ArrayNode;
import variables.ClassNode;
import variables.NativeMethodNode;

public class MeshNode extends ClassNode {

	private Mesh mesh;
	private Window window;

	public ArrayNode getTranslation() {
		Vector3f dat = mesh.getTranslation();
		
		ArrayNode n = new ArrayNode(-1, -1);
		n.add(dat.getX());
		n.add(dat.getY());
		n.add(dat.getZ());
		
		return n;
	}
	
	public ArrayNode getRotation() {
		Vector3f dat = mesh.getRotation();
		
		ArrayNode n = new ArrayNode(-1, -1);
		n.add(dat.getX());
		n.add(dat.getY());
		n.add(dat.getZ());
		
		return n;
	}
	public ArrayNode getScalar() {
		Vector3f dat = mesh.getScalar();
		
		ArrayNode n = new ArrayNode(-1, -1);
		n.add(dat.getX());
		n.add(dat.getY());
		n.add(dat.getZ());
		
		return n;
	}
	public void setTranslation(float x, float y, float z) {
		mesh.setTranslation(new Vector3f(x,y,z));
	}
	public void setRotation(float x, float y, float z) {
		mesh.setRotation(new Vector3f(x,y,z));
	}
	public void setScalar(float x, float y, float z) {
		mesh.setScalar(new Vector3f(x,y,z));
	}
	public void addTranslation(float x, float y, float z) {
		mesh.addTranslation(new Vector3f(x,y,z));
	}
	public void addRotation(float x, float y, float z) {
		mesh.addRotation(new Vector3f(x,y,z));
	}
	public void multScalar(float x, float y, float z) {
		mesh.multScalar(new Vector3f(x,y,z));
	}
	public void bind(CameraNode camera) {
		mesh.bindCamera(camera.getCamera());
	}
	
	public MeshNode(int col, int line, Mesh mesh, Window window) {
		super(col, line);
		
		this.mesh = mesh;
		this.window = window;
		isRoot = false;
		
		try {

		this.objects.put("create", new NativeMethodNode(-2, -2, typeName+".create", false, MeshNode.class.getMethod("create", new Class[] {})));
		this.objects.put("destroy", new NativeMethodNode(-2, -2, typeName+".destroy", false, MeshNode.class.getMethod("destroy", new Class[] {})));
		
		this.objects.put("bind", new NativeMethodNode(-2, -2, typeName+".bind", false, MeshNode.class.getMethod("bind", new Class[] {CameraNode.class})));
		
		this.objects.put("getTranslation", new NativeMethodNode(-2, -2, typeName+".getTranslation", false, MeshNode.class.getMethod("getTranslation", new Class[] {})));
		this.objects.put("getRotation", new NativeMethodNode(-2, -2, typeName+".getRotation", false, MeshNode.class.getMethod("getRotation", new Class[] {})));
		this.objects.put("getScalar", new NativeMethodNode(-2, -2, typeName+".getScalar", false, MeshNode.class.getMethod("getScalar", new Class[] {})));
		this.objects.put("setTranslation", new NativeMethodNode(-2, -2, typeName+".setTranslation", false, MeshNode.class.getMethod("setTranslation", new Class[] {float.class, float.class, float.class})));
		this.objects.put("setRotation", new NativeMethodNode(-2, -2, typeName+".setRotation", false, MeshNode.class.getMethod("setRotation", new Class[] {float.class, float.class, float.class})));
		this.objects.put("setScalar", new NativeMethodNode(-2, -2, typeName+".setScalar", false, MeshNode.class.getMethod("setScalar", new Class[] {float.class, float.class, float.class})));
		this.objects.put("addTranslation", new NativeMethodNode(-2, -2, typeName+".addTranslation", false, MeshNode.class.getMethod("addTranslation", new Class[] {float.class, float.class, float.class})));
		this.objects.put("addRotation", new NativeMethodNode(-2, -2, typeName+".addRotation", false, MeshNode.class.getMethod("addRotation", new Class[] {float.class, float.class, float.class})));
		this.objects.put("multScalar", new NativeMethodNode(-2, -2, typeName+".multScalar", false, MeshNode.class.getMethod("multScalar", new Class[] {float.class, float.class, float.class})));
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error while loading OpenGL library, one of the native method could not be loaded");
		}
	}
	
	public void create() {
		window.setToCurrentGLContext();
		mesh.create();
	}
	
	public void destroy() {
		window.setToCurrentGLContext();
		mesh.destroy();
	}

	public Mesh getMesh() {
		return mesh;
	}
	
}
