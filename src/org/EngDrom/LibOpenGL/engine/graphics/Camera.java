package org.EngDrom.LibOpenGL.engine.graphics;

import org.EngDrom.LibOpenGL.engine.maths.Matrix4f;
import org.EngDrom.LibOpenGL.engine.maths.Vector3f;

public class Camera {
	
	private Vector3f translation = new Vector3f(0,0,0);
	private Vector3f rot_axis = new Vector3f(0, 0, 0);
	
	private Matrix4f cache;
	private boolean built = false;
	
	public void setRotation(Vector3f rot_axis) {
		built = false;
		this.rot_axis = rot_axis;
	}
	
	public void setTranslation(Vector3f translation) {
		built = false;
		this.translation = translation;
	}
	
	private void buildMatrix() {
		built = true;
		cache = Matrix4f.transform(translation, rot_axis, new Vector3f(1,1,1));
	}
	
	public Matrix4f getMatrix() {
		if (!built) {
			buildMatrix();
		}
		return cache;
	}

	public void addTranslation(Vector3f translation) {
		built = false;
		this.translation = this.translation.add(translation);
	}
	
	public void addRotation(Vector3f rotation) {
		built = false;
		this.rot_axis = this.rot_axis.add(rotation);
	}

	public Vector3f getTranslation() {
		return translation;
	}

	public Vector3f getRotation() {
		return rot_axis;
	}

}
