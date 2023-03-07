package com.example.examplemod;

import com.podloot.eyemod.gui.apps.App;
import com.podloot.eyemod.lib.gui.util.Line;
import com.podloot.eyemod.lib.gui.widgets.EyeButton;
import com.podloot.eyemod.lib.gui.widgets.EyeLabel;

import net.minecraft.resources.ResourceLocation;

public class ExtensionApp extends App {
	
	EyeLabel label;
	EyeButton button;
	Boolean textShowed = false;
	
	public ExtensionApp() {
		super(new ResourceLocation(ExampleMod.MODID, "textures/gui/apps/appicon.png"), 0xffCCCCCC, "Test App");
	}
	
	// This method toggles the visibility of the label and changes the text of the button.
	void toggleText() {
		
		label.hide(textShowed);
		
		button.setText(new Line(textShowed?"Show Text":"Hide Text"));
		
		textShowed = !textShowed;
		
	}
	
	@Override
	public boolean load() {
		
		// Initialize the text label
		Line labelText = new Line("Example Text");
		
		label = new EyeLabel(70, 20, labelText);
		
		label.hide(!textShowed);
		
		add(label, 0, 60); // Adds its to the main app panel.
		
		// Initialize the button widget.
		button = new EyeButton(60, 15, new Line("Show Text"));
		
		// Register action
		button.setAction(() -> {
			
			this.toggleText();
			
		});
		
		add(button, 0, 0); // Adds it to the main app panel.
		
		return true;
	}
}
