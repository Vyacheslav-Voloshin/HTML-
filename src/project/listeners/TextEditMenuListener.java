package project.listeners;

import project.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

public class TextEditMenuListener implements MenuListener {

    private View view;

    public TextEditMenuListener(View view) {
        this.view = view;
    }

// • Метод menuSelected(MenuEvent menuEvent)  повинен встановлювати доступність пунктів меню Стиль, Вирівнювання, Колір та Шрифт залежно від вибраної вкладки.
    @Override
    public void menuSelected(MenuEvent e) {
       JMenu jMenu = (JMenu) e.getSource();
       Component[] components = jMenu.getMenuComponents();
        for (Component component:components) {
            component.setEnabled(view.isHtmlTabSelected());
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }


}
