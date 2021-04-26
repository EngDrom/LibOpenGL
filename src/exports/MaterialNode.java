package exports;

import java.util.ArrayList;

import engine.graphics.Material;
import engine.io.Window;
import main.EntryPoint;
import variables.ClassNode;
import variables.NativeMethodNode;
import variables.VariableContext;

public class MaterialNode extends ClassNode {

	private Material mat;
	private Window win;
	
	public Object createInstance(VariableContext context, ArrayList<Object> args) {
		if(!isRoot) {
			System.out.println("Can't create instance with sub child");
			return null;
		}
		return new MaterialNode(this, args);
	}
	
	protected MaterialNode(ClassNode other, ArrayList<Object> args) {
		super(other, args);

		this.typeName = "opengl.builtin.Material";
		
		checkSize(args, 2);
		
		if (args.get(0) instanceof WindowNode) {
			win = ((WindowNode) args.get(0)).getWindow();
		} else {
			EntryPoint.raiseErr("Missing parent window in material constructor");
		}
		String path = getStringValue(args, 1);
		
		mat = new Material(path);
	}
	
	public void create() {
		win.setToCurrentGLContext();
		mat.create();
	}
	
	public void destroy() {
		win.setToCurrentGLContext();
		mat.destroy();
	}
	
	public MaterialNode(int col, int line) {
		super(col, line);
		this.typeName = "opengl.builtin.Material";
		
		try {
		
		// this.objects.put("create", new NativeMethodNode(-2, -2, typeName+".create", false, MaterialNode.class.getMethod("create", new Class[] {})));
		// this.objects.put("destroy", new NativeMethodNode(-2, -2, typeName+".destroy", false, MaterialNode.class.getMethod("destroy", new Class[] {})));
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error while loading OpenGL library, one of the native method could not be loaded");
		}
	}

	public Material getMaterial() {
		return mat;
	}

}
