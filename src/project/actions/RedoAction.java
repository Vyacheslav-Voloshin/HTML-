package project.actions;

import project.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
//Клас повернення дії
public class RedoAction extends AbstractAction {

    private View view;

    public RedoAction (View view){
        this.view = view;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        view.redo();
    }
}
