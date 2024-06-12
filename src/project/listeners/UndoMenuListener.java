package project.listeners;

import project.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
/*Наш редактор підтримуватиме механізм скасування/повернення (undo/redo) дій у редакторі.
 Клас UndoMenuListener. Цей слухач стежитиме за меню, а якщо конкретніше, то за моментом,
 коли меню редагування буде обрано користувачем. У цей момент він буде запитувати у подання чи можемо ми зараз скасувати або повернути якусь дію,
 і в залежності від цього робити доступними або недоступними пункти меню "Скасувати" та "Повернути".*/
public class UndoMenuListener implements MenuListener {
    private View view;
    private JMenuItem undoMenuItem; //Пункт меню "Відмінити"
    private JMenuItem redoMenuItem; //Пункт меню "Повернути"

    public UndoMenuListener(View view, JMenuItem undoMenuItem, JMenuItem redoMenuItem) {
        this.view = view;
        this.undoMenuItem = undoMenuItem;
        this.redoMenuItem = redoMenuItem;
    }

    @Override
    public void menuSelected(MenuEvent menuEvent) {
        if (view.canUndo()){
            undoMenuItem.setEnabled(true);
        } else {
            undoMenuItem.setEnabled(false);
        }
        if (view.canRedo()){
            redoMenuItem.setEnabled(true);
        } else {
            redoMenuItem.setEnabled(false);
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}
