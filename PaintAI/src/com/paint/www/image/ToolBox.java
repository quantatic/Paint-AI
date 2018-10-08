package com.paint.www.image;
/**
 * Represents a ToolBox, which holds an equipped {@link Tool}.
 * @author Itai Rivkin-Fish
 * @version 10/8/18
 */
public class ToolBox {
	private static Tool equippedTool;

	public static void useEquippedTool(int mouseX,int mouseY, Layer layer) {
		if(equippedTool == null) {
			equippedTool = new EraseTool(20.0);
			//equippedTool = new PencilTool(new Pixel(255, 0, 0, 255), 20.0);
		}
		equippedTool.useTool(mouseX, mouseY, layer);
	}
	
	public static Tool getEquippedTool() {
		return equippedTool;
	}

	public static void setEquippedTool(Tool equippedTool) {
		ToolBox.equippedTool = equippedTool;
	}
}
