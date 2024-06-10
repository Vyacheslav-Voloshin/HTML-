package project.listeners;

import project.View;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
// Цей клас буде слухати та обробляти зміни стану панелі вкладок.
public class TabbedPaneChangeListener implements ChangeListener {

    private View view;

    public TabbedPaneChangeListener(View view) {
        this.view = view;
    }



    @Override
    public void stateChanged(ChangeEvent e) {
         view.selectedTabChanged();
    }
}
