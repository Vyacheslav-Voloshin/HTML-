package project;

import project.actions.*;
import project.listeners.TextEditMenuListener;
import project.listeners.UndoMenuListener;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
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
    public static void initHelpMenu(View view, JMenuBar menuBar){
        JMenu helpMenu = new JMenu("Допомога");
        menuBar.add(helpMenu);
        addMenuItem(helpMenu, "Про програму", view);}

//іниціалізація меню вибору шрифту.
    public static void initFontMenu(View view, JMenuBar menuBar){
        JMenu fontMenu = new JMenu("Шрифт");
        menuBar.add(fontMenu);
        JMenu fontTypeMenu = new JMenu("Шрифт");
        fontMenu.add(fontTypeMenu);
        String[] fontTypes = {Font.SANS_SERIF, Font.SERIF, Font.MONOSPACED, Font.DIALOG, Font.DIALOG_INPUT};
        for (String fontType : fontTypes) {
            addMenuItem(fontTypeMenu, fontType, new StyledEditorKit.FontFamilyAction(fontType, fontType));
        }

        JMenu fontSizeMenu = new JMenu("Розмір шрифта");
        fontMenu.add(fontSizeMenu);

        String[] fontSizes = {"6", "8", "10", "12", "14", "16", "20", "24", "32", "36", "48", "72"};
        for (String fontSize : fontSizes) {
            addMenuItem(fontSizeMenu, fontSize, new StyledEditorKit.FontSizeAction(fontSize, Integer.parseInt(fontSize)));
        }

        fontMenu.addMenuListener(new TextEditMenuListener(view));
    }

//іниціалізація меню вибору кольору.
    public static void initColorMenu(View view, JMenuBar menuBar){
        JMenu colorMenu = new JMenu("Колір");
        menuBar.add(colorMenu);
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Червоний", Color.red));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Помаранчевий", Color.orange));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Жовтий", Color.yellow));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Зелений", Color.green));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Синій", Color.blue));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Голубий", Color.cyan));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Пурпуровий", Color.magenta));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Чорний", Color.black));

        colorMenu.addMenuListener(new TextEditMenuListener(view));
    }

//іниціалізація меню вирівнювання.
    public static void initAlignMenu(View view, JMenuBar menuBar){
        JMenu alignMenu = new JMenu("Вирівнювання");
        menuBar.add(alignMenu);
        addMenuItem(alignMenu, new StyledEditorKit.AlignmentAction("По лівому краю", StyleConstants.ALIGN_LEFT));
        addMenuItem(alignMenu, new StyledEditorKit.AlignmentAction("По центру", StyleConstants.ALIGN_CENTER));
        addMenuItem(alignMenu, new StyledEditorKit.AlignmentAction("По правому краю", StyleConstants.ALIGN_RIGHT));

        alignMenu.addMenuListener(new TextEditMenuListener(view));
    }

//іниціалізація меню вибору стилю тексту.
    public static void initStyleMenu(View view, JMenuBar menuBar){
        JMenu styleMenu = new JMenu("Стиль");
        menuBar.add(styleMenu);

        addMenuItem(styleMenu, "Напівжирний", new StyledEditorKit.BoldAction());
        addMenuItem(styleMenu, "Підкреслений", new StyledEditorKit.UnderlineAction());
        addMenuItem(styleMenu, "Курсив", new StyledEditorKit.ItalicAction());

        styleMenu.addSeparator();

        addMenuItem(styleMenu, "Підрядковий знак", new SubscriptAction());
        addMenuItem(styleMenu, "Надрядковий знак", new SuperscriptAction());
        addMenuItem(styleMenu, "Закреслений", new StrikeThroughAction());

        styleMenu.addMenuListener(new TextEditMenuListener(view));
    }

//іниціалізація меню редактування тексту.
    public static void initEditMenu(View view, JMenuBar menuBar){
        JMenu editMenu = new JMenu("Редагувати");
        menuBar.add(editMenu);

        JMenuItem undoItem = addMenuItem(editMenu, "Відмінити", new UndoAction(view));
        JMenuItem redoItem = addMenuItem(editMenu, "Повернути", new RedoAction(view));
        addMenuItem(editMenu, "Вирізати", new DefaultEditorKit.CutAction());
        addMenuItem(editMenu, "Копіювати", new DefaultEditorKit.CopyAction());
        addMenuItem(editMenu, "Вставити", new DefaultEditorKit.PasteAction());

        editMenu.addMenuListener(new UndoMenuListener(view, undoItem, redoItem));
    }

//іниціалізація меню Файл.
    public static void initFileMenu(View view, JMenuBar menuBar){
        JMenu fileMenu = new JMenu("Файл");
        menuBar.add(fileMenu);
        addMenuItem(fileMenu, "Новий", view);
        addMenuItem(fileMenu, "Відкрити", view);
        addMenuItem(fileMenu, "Зберегти", view);
        addMenuItem(fileMenu, "Зберегти як...", view);
        fileMenu.addSeparator();
        addMenuItem(fileMenu, "Вихід", view);
    }
}
