package engine.graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Material {

	private Texture texture;
	private float width, height;
	private int textureID;
	
	private String path;
	
	public Material(String path) {
		this.path = path;
	}
	
	public void create() {
		createImg();
		
		width = texture.getWidth();
		height = texture.getHeight();
		textureID = texture.getTextureID();
	}
	
	public void createImg() {
		try {
			texture = TextureLoader.getTexture("PNG", new FileInputStream(path), GL11.GL_NEAREST);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public int getTextureID() {
		return textureID;
	}
	
	public void destroy() {
		GL13.glDeleteTextures(textureID);
	}
	
}
