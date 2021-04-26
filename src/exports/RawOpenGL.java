package exports;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL21;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL31;
import org.lwjgl.opengl.GL32;
import org.lwjgl.opengl.GL33;
import org.lwjgl.opengl.GL40;
import org.lwjgl.opengl.GL41;
import org.lwjgl.opengl.GL42;
import org.lwjgl.opengl.GL43;
import org.lwjgl.opengl.GL44;
import org.lwjgl.opengl.GL45;

import variables.ClassNode;
import variables.NativeClassNode;

public class RawOpenGL {

	public static ClassNode computerRaw() {
		ClassNode cl = new ClassNode(-1, -1);
		cl.typeName = "RawOpenGL";
		cl.name = "RawOpenGL";
		cl.isRoot = false;
		
		cl.objects.put("GLFW", new NativeClassNode(-1, -1, GLFW.class).build("opengl.raw.glfw"));
		cl.objects.put("GL", new NativeClassNode(-1, -1, GL.class).build("opengl.raw.gl"));
		cl.objects.put("GL11", new NativeClassNode(-1, -1, GL11.class).build("opengl.raw.gl11"));
		cl.objects.put("GL12", new NativeClassNode(-1, -1, GL12.class).build("opengl.raw.gl12"));
		cl.objects.put("GL13", new NativeClassNode(-1, -1, GL13.class).build("opengl.raw.gl13"));
		cl.objects.put("GL14", new NativeClassNode(-1, -1, GL14.class).build("opengl.raw.gl14"));
		cl.objects.put("GL15", new NativeClassNode(-1, -1, GL15.class).build("opengl.raw.gl15"));
		cl.objects.put("GL20", new NativeClassNode(-1, -1, GL20.class).build("opengl.raw.gl20"));
		cl.objects.put("GL21", new NativeClassNode(-1, -1, GL21.class).build("opengl.raw.gl21"));
		cl.objects.put("GL30", new NativeClassNode(-1, -1, GL30.class).build("opengl.raw.gl30"));
		cl.objects.put("GL31", new NativeClassNode(-1, -1, GL31.class).build("opengl.raw.gl31"));
		cl.objects.put("GL32", new NativeClassNode(-1, -1, GL32.class).build("opengl.raw.gl32"));
		cl.objects.put("GL33", new NativeClassNode(-1, -1, GL33.class).build("opengl.raw.gl33"));
		cl.objects.put("GL40", new NativeClassNode(-1, -1, GL40.class).build("opengl.raw.gl40"));
		cl.objects.put("GL41", new NativeClassNode(-1, -1, GL41.class).build("opengl.raw.gl41"));
		cl.objects.put("GL42", new NativeClassNode(-1, -1, GL42.class).build("opengl.raw.gl42"));
		cl.objects.put("GL43", new NativeClassNode(-1, -1, GL43.class).build("opengl.raw.gl43"));
		cl.objects.put("GL44", new NativeClassNode(-1, -1, GL44.class).build("opengl.raw.gl44"));
		cl.objects.put("GL45", new NativeClassNode(-1, -1, GL45.class).build("opengl.raw.gl45"));
		
		return cl;
	}

}
