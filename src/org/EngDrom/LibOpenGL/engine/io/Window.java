package org.EngDrom.LibOpenGL.engine.io;

import org.EngDrom.LibOpenGL.engine.maths.Matrix4f;
import org.EngDrom.LibOpenGL.engine.maths.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class Window {
	// Width and Height of window
	private int width;
	private int height;

	// Store the position of the window
	private int[] windowPosX = new int[1], windowPosY = new int[1];
	
	// Title of window
	private String title;
	
	// Window Idea (GLFW window id)
	private long window;
	
	// Input container (keyboard and mouse)
	private Input input;
	
	// Background base erase color
	private Vector3f backgroundColor = new Vector3f(0, 0, 0);
	
	// Has been resized recently
	private boolean isResized = false;
	
	// Is full screen
	private boolean isFullscreen = false;
	
	private Matrix4f projectionMatrix;
	public Matrix4f getProjectionMatrix() {
		return projectionMatrix;
	}
	
	// Constructor (set width, height and title)
	public Window(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.title = title;
		
		makeProjectionMatrix(width, height);
	}
	
	// Create base window context
	public void create() {
		// Initialize GLFW, if it wasn't, raise Error
		if(!GLFW.glfwInit()) {
			System.out.println("Error glfw wasn't initalized");
			return ;
		}
		
		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW.GLFW_VERSION_MAJOR, 3);
		GLFW.glfwWindowHint(GLFW.GLFW_VERSION_MINOR, 0);
		
		// Create input context
		input = new Input();
		// create window in glfw
		window = GLFW.glfwCreateWindow(width, height, title, isFullscreen ? GLFW.glfwGetPrimaryMonitor() : 0, 0);
		
		// Check whether window was well implemented
		if (window == 0) {
			System.out.println("Error : Window couldn't be initialized");
			return ;
		}
		
		// Get Video mode of the base monitor
		GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());

		// Set windowBasePos
		windowPosX[0] = (videoMode.width() - width) / 2;
		windowPosY[0] = (videoMode.height() - height) / 2;
		
		// Set position to the center of the monitor
		GLFW.glfwSetWindowPos(window, windowPosX[0], windowPosY[0]);
		
		// Show window
		GLFW.glfwShowWindow(window);
		
		// Set the current context to the one of the window (tell opengl to work on window)
		GLFW.glfwMakeContextCurrent(window);
		// Create Capabilities for OpenGL
		GL.createCapabilities();
		// Enable Depth test
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		
		// Create Input callbacks
		createCallbacks();
		
		// Set Framerate
		GLFW.glfwSwapInterval(1);
	}
	
	public void setToCurrentGLContext() {
		GLFW.glfwMakeContextCurrent(window);
	}
	
	// Window Callbacks (not part of user interface)
	private GLFWWindowSizeCallback sizeCallback;
	
	// Create callbacks
	private void createCallbacks() {
		sizeCallback = new GLFWWindowSizeCallback() {
			@Override
			public void invoke(long window, int w, int h) {
				width = w;
				height = h;
				isResized = true;
				
				makeProjectionMatrix(width, height);
			}
		};
		
		GLFW.glfwSetKeyCallback(window, input.getKeyboardCallback());
		GLFW.glfwSetCursorPosCallback(window, input.getMouseMoveCallback());
		GLFW.glfwSetMouseButtonCallback(window, input.getMouseButtonsCallback());
		GLFW.glfwSetScrollCallback(window, input.getScrollCallback());
		GLFW.glfwSetWindowSizeCallback(window, sizeCallback);
	}
	
	protected void makeProjectionMatrix(int width, int height) {
		this.projectionMatrix = Matrix4f.projection(70f, (float)width / (float)height, 0.1f, 1000f);
	}

	// Destroy Window
	public void destroy() {
		input.destroy();
		sizeCallback.free();
		GLFW.glfwDestroyWindow(window);
	}
	
	// Render Object
	public void update() {
		// Set the current context to the one of the window (tell opengl to work on window)
		GLFW.glfwMakeContextCurrent(window);
		
		GLFW.glfwPollEvents();
		
		if (isResized) {
			// Set viewport size
			GL11.glViewport(0, 0, width, height);
			isResized = false;
		}
		// Erase and set with color
		GL11.glClearColor(backgroundColor.getX(), 
				backgroundColor.getY(), 
				backgroundColor.getZ(), 
				1.0f);
		
		// Launch erase
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}
	
	// Swap Buffers (end render)
	public void swapBuffers() {
		this.setToCurrentGLContext();
		GLFW.glfwSwapBuffers(window);
	}
	
	// Return whether the user requested to close the window
	public boolean shouldClose() {
		return GLFW.glfwWindowShouldClose(window);
	}
	
	// Set background color (by default black)
	public void setBackgroundColor(float r, float g, float b) {
		backgroundColor.set(r, g, b);
	}
	
	/**
	 * Getters and setters
	 */
	public boolean isFullscreen() {
		return isFullscreen;
	}

	public void setFullscreen(boolean isFullscreen) {
		this.isFullscreen = isFullscreen;
		isResized = true;
		if (isFullscreen) {
			GLFW.glfwGetWindowPos(window, windowPosX, windowPosY);
			GLFW.glfwSetWindowMonitor(window, GLFW.glfwGetPrimaryMonitor(), 0, 0, width, height, 0);
		} else {
			GLFW.glfwSetWindowMonitor(window, 0, windowPosX[0], windowPosY[0], width, height, 0);
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getTitle() {
		return title;
	}

	public long getWindow() {
		return window;
	}

	public Input getInput() {
		return input;
	}
}
