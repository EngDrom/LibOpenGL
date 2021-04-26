package exports;

import java.util.ArrayList;

import engine.graphics.Camera;
import engine.maths.Vector3f;
import parser.nodes.ArrayNode;
import variables.ClassNode;
import variables.NativeMethodNode;
import variables.VariableContext;

public class CameraNode extends ClassNode {

	public CameraNode(int col, int line) {
		super(col, line);
		
		try {

		this.objects.put("getTranslation", new NativeMethodNode(-2, -2, typeName+".getTranslation", false, CameraNode.class.getMethod("getTranslation", new Class[] {})));
		this.objects.put("getRotation", new NativeMethodNode(-2, -2, typeName+".getRotation", false, CameraNode.class.getMethod("getRotation", new Class[] {})));
		this.objects.put("setTranslation", new NativeMethodNode(-2, -2, typeName+".setTranslation", false, CameraNode.class.getMethod("setTranslation", new Class[] {float.class, float.class, float.class})));
		this.objects.put("setRotation", new NativeMethodNode(-2, -2, typeName+".setRotation", false, CameraNode.class.getMethod("setRotation", new Class[] {float.class, float.class, float.class})));
		this.objects.put("addTranslation", new NativeMethodNode(-2, -2, typeName+".addTranslation", false, CameraNode.class.getMethod("addTranslation", new Class[] {float.class, float.class, float.class})));
		this.objects.put("addRotation", new NativeMethodNode(-2, -2, typeName+".addRotation", false, CameraNode.class.getMethod("addRotation", new Class[] {float.class, float.class, float.class})));
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error while loading OpenGL library, one of the native method could not be loaded");
		}
	}
	
	public CameraNode(CameraNode cameraNode, ArrayList<Object> args) {
		super(cameraNode, args);	
	}

	public Object createInstance(VariableContext context, ArrayList<Object> args) {
		if(!isRoot) {
			System.out.println("Can't create instance with sub child");
			return null;
		}
		return new CameraNode(this, args);
	}

	private Camera camera = new Camera();
	
	public ArrayNode getTranslation() {
		Vector3f dat = camera.getTranslation();
		
		ArrayNode n = new ArrayNode(-1, -1);
		n.add(dat.getX());
		n.add(dat.getY());
		n.add(dat.getZ());
		
		return n;
	}
	
	public ArrayNode getRotation() {
		Vector3f dat = camera.getRotation();
		
		ArrayNode n = new ArrayNode(-1, -1);
		n.add(dat.getX());
		n.add(dat.getY());
		n.add(dat.getZ());
		
		return n;
	}
	public void setTranslation(float x, float y, float z) {
		camera.setTranslation(new Vector3f(x,y,z));
	}
	public void setRotation(float x, float y, float z) {
		camera.setRotation(new Vector3f(x,y,z));
	}
	public void addTranslation(float x, float y, float z) {
		camera.addTranslation(new Vector3f(x,y,z));
	}
	public void addRotation(float x, float y, float z) {
		camera.addRotation(new Vector3f(x,y,z));
	}
	
	public Camera getCamera() {
		return camera;
	}

}
