package exports;

import java.math.BigDecimal;

import parser.nodes.NumberNode;
import variables.VariableContext;

public class Constants {

	public static final int OBJ_FILE = 0;
	
	public static final int COLORED_MESH = 1000;
	public static final int TEXTURED_MESH = 1001;
	
	public static VariableContext computeConstants() {
		VariableContext ctx = new VariableContext();
		
		ctx.values.put("OBJ_FILE", new NumberNode(BigDecimal.valueOf(OBJ_FILE), -1, -1));
		ctx.values.put("COLORED_MESH", new NumberNode(BigDecimal.valueOf(COLORED_MESH), -1, -1));
		ctx.values.put("TEXTURED_MESH", new NumberNode(BigDecimal.valueOf(TEXTURED_MESH), -1, -1));
	
		return ctx;
	}
	
}
