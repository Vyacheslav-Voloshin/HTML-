package project.actions;

import project.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
//���� ����� 䳿
public class UndoAction extends AbstractAction {

    private View view;

    public UndoAction (View view){
        this.view = view;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
