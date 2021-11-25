package org.EngDrom.LibOpenGL.engine.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import org.EngDrom.LibOpenGL.engine.graphics.Material;
import org.EngDrom.LibOpenGL.engine.graphics.Mesh;
import org.EngDrom.LibOpenGL.engine.graphics.Vertex;
import org.EngDrom.LibOpenGL.engine.graphics.meshes.TexturedMesh;
import org.EngDrom.LibOpenGL.engine.graphics.vertex.TexturedVertex;
import org.EngDrom.LibOpenGL.engine.maths.Vector2f;
import org.EngDrom.LibOpenGL.engine.maths.Vector3f;

public class FileUtils {

	public static String loadAsString(String path) {
		try {
			return new String(Files.readAllBytes(Paths.get(path)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static TexturedMesh readObjFile(String path, Material mat) {
		try {
			Scanner sc = new Scanner(new FileInputStream(new File(path)));
			
			ArrayList<Vector3f> vertexs = new ArrayList<Vector3f>();
			ArrayList<Vector2f> v_textures = new ArrayList<Vector2f>();
			
			ArrayList<TexturedVertex> data = new ArrayList<TexturedVertex>();
			
			boolean lockData = false;
			while (sc.hasNextLine()) {
				String msg = sc.nextLine();
				
				String[] spl = msg.split(" ");
				
				if (spl[0].equals("v") && spl.length == 4) {
					if (spl.length == 4 && !lockData) {
						vertexs.add(new Vector3f(Float.parseFloat(spl[1]),
								Float.parseFloat(spl[2]),
								Float.parseFloat(spl[3])));
					} else {
						System.out.println("An error occured while parsing the OBJ file, one of the vertex is written after the first face.");
					}
				} else if (spl[0].equals("vt")) {
					if (spl.length == 3 && !lockData) {
						v_textures.add(new Vector2f(Float.parseFloat(spl[1]),
								Float.parseFloat(spl[2])));
					} else {
						System.out.println("An error occured while parsing the OBJ file, one of the vertex texture coordinate is written after the first face.");
					}
				} else if (spl[0].equals("f") && spl.length == 4) {
					lockData = true;
					
					String[] spl1 = spl[1].split("/");
					TexturedVertex tcx1 = new TexturedVertex(vertexs.get(Integer.parseInt(spl1[0]) - 1), v_textures.get(Integer.parseInt(spl1[1]) - 1));
					String[] spl2 = spl[2].split("/");
					TexturedVertex tcx2 = new TexturedVertex(vertexs.get(Integer.parseInt(spl2[0]) - 1), v_textures.get(Integer.parseInt(spl2[1]) - 1));
					String[] spl3 = spl[3].split("/");
					TexturedVertex tcx3 = new TexturedVertex(vertexs.get(Integer.parseInt(spl3[0]) - 1), v_textures.get(Integer.parseInt(spl3[1]) - 1));
					
					data.add(tcx1);
					data.add(tcx2);
					data.add(tcx3);
				}
			}

			int[] indices = new int[data.size()];
			Vertex[] dataArray = new Vertex[data.size()];
			for (int i = 0; i < indices.length; i++) {
				indices[i] = i;
				dataArray[i] = data.get(i);
			}
			
			TexturedMesh tm = new TexturedMesh(dataArray, indices, mat);
			
			return tm;
		} catch (Exception e) {
			System.out.println("An error occured, obj data isn't valid");
			e.printStackTrace();
		}
		
		return null;
	}
	
}
