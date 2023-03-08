package com.example.examplemod;

import com.podloot.eyemod.gui.apps.App;
import com.podloot.eyemod.lib.gui.util.Line;
import com.podloot.eyemod.lib.gui.widgets.EyeButton;
import com.podloot.eyemod.lib.gui.widgets.EyeLabel;
import com.podloot.eyemod.lib.gui.widgets.EyeTextField;

import net.minecraft.resources.ResourceLocation;

public class ExtensionApp extends App {
	
	EyeButton callButton;
	EyeLabel mainPanelTitle;
	EyeLabel reasonInputLabel;
	EyeTextField reasonInput;
	ErrorPanel errorPanel = new ErrorPanel("Default Error");

	public ExtensionApp() {
		super(new ResourceLocation(ExampleMod.MODID, "textures/gui/apps/appicon.png"), 0xffCCCCCC, "Emergency");
	}

	@Override
	public boolean load() {
		
		mainPanelTitle = new EyeLabel(100, 25, new Line("Call Police"));

		reasonInputLabel = new EyeLabel(100, 25, new Line("Reason"));

		reasonInput = new EyeTextField(100, 25);

		callButton = new EyeButton(25, 25, new Line("CALL"));

		callButton.setAction(() -> {
			errorPanel.hide(true);
			String reason = reasonInput.getInput();

			if(reason.length() <= 0) {
				errorPanel.setError("The reason input is required!");
				errorPanel.hide(false);
			} else {
				System.out.println(reason);
				reasonInput.setInput("");
			}

		});

		add(callButton, 65, 155);
		add(mainPanelTitle, 25, 5);
		add(reasonInput, 25, 125);
		add(reasonInputLabel, 0, 100);
		add(errorPanel, 0, 0);

		return true;
	}
}
