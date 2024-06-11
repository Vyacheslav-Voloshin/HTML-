package project;

import javax.swing.*;
import java.awt.event.ActionListener;

//Це буде допоміжний клас для ініціалізації та налаштування меню.
public class MenuHelper {

    public static JMenuItem addMenuItem(JMenu parent, String text, ActionListener actionListener){
//де parent - меню в яке ми додаємо пункт,
// text - текст пункту, що додається,
// actionListener - слухач дій пункту меню, що додається.
       JMenuItem jMenuItem = new JMenuItem(text); //Створюємо новий пункт меню JMenuItem, використовуючи text.
       jMenuItem.addActionListener(actionListener); //Встановлювати цей пункт слухача дій за допомогою методу addActionListener()
       parent.add(jMenuItem); //Додавати до parent створений пункт меню.
       return jMenuItem;
    }

    public static JMenuItem addMenuItem(JMenu parent, Action action){
       JMenuItem jMenuItem = new JMenuItem(action); ////Створюємо новий пункт меню JMenuItem, використовуючи action
       parent.add(jMenuItem);//Додавати до parent створений пункт меню.
       return jMenuItem;
    }

    public static JMenuItem addMenuItem(JMenu parent, String text, Action action){
        JMenuItem jMenuItem = addMenuItem(parent,action); //створюємо обєкт, викликавши попередній метод
        jMenuItem.setText(text); // встановлюємо імя
        parent.add(jMenuItem);
        return jMenuItem;
    }
//іниціалізація меню допомоги.
    public static void initHelpMenu(View view, JMenuBar menuBar){}

//іниціалізація меню вибору шрифту.
    public static void initFontMenu(View view, JMenuBar menuBar){}

//іниціалізація меню вибору кольору.
    public static void initColorMenu(View view, JMenuBar menuBar){}

//іниціалізація меню вирівнювання.
    public static void initAlignMenu(View view, JMenuBar menuBar){}

//іниціалізація меню вибоуа стилю тексту.
    public static void initStyleMenu(View view, JMenuBar menuBar){}

//іниціалізація меню редактування тексту.
    public static void initEditMenu(View view, JMenuBar menuBar){}

//іниціалізація меню Файл.
    public static void initFileMenu(View view, JMenuBar menuBar){}
}
