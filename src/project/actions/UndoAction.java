package project.actions;

import project.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
// Î‡Ò ‚≥‰Ï≥ÌË ‰≥ø
public class UndoAction extends AbstractAction {

    private View view;

    public UndoAction (View view){
        this.view = view;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
