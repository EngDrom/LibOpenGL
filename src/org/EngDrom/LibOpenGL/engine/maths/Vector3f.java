package org.EngDrom.LibOpenGL.engine.maths;

public class Vector3f {

	private float x, y, z;
	
	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public Vector3f add(Vector3f v) {
		return new Vector3f(x + v.getX(), y + v.getY(), z + v.getZ());
	}
	
	public Vector3f multiply(Vector3f v) {
		return new Vector3f(x * v.getX(), y * v.getY(), z * v.getZ());
	}

	public Vector3f divide(Vector3f scalar) {
		float x = scalar.x == 0 ? 0 : this.x / scalar.x;
		float y = scalar.y == 0 ? 0 : this.y / scalar.y;
		float z = scalar.z == 0 ? 0 : this.z / scalar.z;
		
		return new Vector3f(x, y, z);
	}
	
}
