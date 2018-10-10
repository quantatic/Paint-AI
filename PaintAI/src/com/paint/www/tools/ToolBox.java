package com.paint.www.tools;

import com.paint.www.image.Layer;
import com.paint.www.tools.shapes.CircularTool;
import com.paint.www.tools.types.EraseTool;

/**
 * Represents a ToolBox, which holds an equipped {@link Tool}.
 * @author Itai Rivkin-Fish
 * @version 10/8/18
 */
public class ToolBox {
	private static Tool equippedTool;

	public static void useEquippedTool(int mouseX,int mouseY, double scale, Layer layer) {
		if(equippedTool == null) {
			equippedTool = new EraseTool(new CircularTool(20.0));
		}
		equippedTool.useTool(mouseX, mouseY, scale, layer);
	}
	
	public static Tool getEquippedTool() {
		return equippedTool;
	}

	public static void setEquippedTool(Tool equippedTool) {
		ToolBox.equippedTool = equippedTool;
	}
}
