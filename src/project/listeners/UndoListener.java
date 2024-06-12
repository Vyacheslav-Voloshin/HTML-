package project.listeners;

import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

// Цей клас стежитиме за правками, які можна скасувати чи повернути.
public class UndoListener implements UndoableEditListener {

    private UndoManager undoManager;

    public UndoListener(UndoManager undoManager) {
        this.undoManager = undoManager;
    }

    //• Метод undoableEditHappened(UndoableEditEvent e)  повинен з переданої події отримувати редагування та додавати її в undoManager.
    @Override
    public void undoableEditHappened(UndoableEditEvent e) {
          undoManager.addEdit(e.getEdit());
    }
}
