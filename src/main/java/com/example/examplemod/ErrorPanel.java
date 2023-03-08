package com.example.examplemod;

import com.podloot.eyemod.lib.gui.panels.EyeBoxPanel;
import com.podloot.eyemod.lib.gui.panels.EyePanel;
import com.podloot.eyemod.lib.gui.util.Line;
import com.podloot.eyemod.lib.gui.widgets.EyeButton;
import com.podloot.eyemod.lib.gui.widgets.EyeLabel;

public class ErrorPanel extends EyePanel {
    String error;

    EyeLabel errorLabel;
    EyeButton closeButton;

    public ErrorPanel(String text) {
        super(150, 50);

        setBack(0xFF2C3E50);

        error = text;

        hide(true);

        closeButton = new EyeButton(5, 5, new Line("X"));

        closeButton.setAction(() -> {
            if(!this.isHidden()) {
                hide(true);
            }
        });

        add(closeButton, (150-10), 5);

        errorLabel = new EyeLabel(130, 50, new Line(error));

        add(errorLabel, 10, 0);

    }

    public void setError(String text) {

        error = text;
        errorLabel.setText(new Line(text));

    }
}
