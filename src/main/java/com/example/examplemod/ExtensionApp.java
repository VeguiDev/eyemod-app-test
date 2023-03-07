package com.example.examplemod;

import com.podloot.eyemod.gui.apps.App;
import com.podloot.eyemod.lib.gui.util.Line;
import com.podloot.eyemod.lib.gui.widgets.EyeLabel;

import net.minecraft.resources.ResourceLocation;

public class ExtensionApp extends App {
	public ExtensionApp() {
		super(new ResourceLocation(ExampleMod.MODID, "textures/gui/apps/appicon.png"), 0xffCCCCCC, "Test App");
	}

	@Override
	public boolean load() {
		EyeLabel label = new EyeLabel(100, 50, new Line("Example Text"));
		
		add(label, 0, 0);
		
		return true;
	}
}
