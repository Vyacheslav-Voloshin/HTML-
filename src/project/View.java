package project;

import project.Controller;
import project.listeners.FrameListener;
import project.listeners.TabbedPaneChangeListener;
import project.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/* Графічний інтерфейс буде вікно, в якому буде меню і панель з двома вкладками.
 На першій вкладці розташовуватиметься текстова панель, яка малюватиме html сторінку.
 На ній можна буде форматувати та редагувати текст сторінки.
 На другій вкладці буде редактор, який відображатиме код html сторінки, в ньому будуть видно всі використовувані html теги.
 У ньому також можна буде змінювати текст сторінки, додавати та видаляти різні теги.*/
public class View extends JFrame implements ActionListener {

    private Controller controller;

    private JTabbedPane tabbedPane = new JTabbedPane(); //це буде панель з двома вкладками
    private JTextPane htmlTextPane = new JTextPane(); //це буде компонент для візуального редактування html Він буде розміщений на першій вкладці.
    private JEditorPane plainTextPane = new JEditorPane(); //це буде компонент для редагування HTML у вигляді тексту,він відображатиме код html (теги та їх вміст).

    private UndoManager undoManager = new UndoManager();

    private UndoListener undoListener = new UndoListener(undoManager);

    public View() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void init() {
        initGui();
        FrameListener frameListener = new FrameListener(this);
        addWindowListener(frameListener);
        this.setVisible(true);
    }

    public void exit() {
        controller.exit();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    //Метод  відповідає  за ініціалізацію меню
    public void initMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        MenuHelper.initFileMenu(this, jMenuBar);
        MenuHelper.initEditMenu(this, jMenuBar);
        MenuHelper.initStyleMenu(this, jMenuBar);
        MenuHelper.initAlignMenu(this, jMenuBar);
        MenuHelper.initColorMenu(this, jMenuBar);
        MenuHelper.initFontMenu(this, jMenuBar);
        MenuHelper.initHelpMenu(this, jMenuBar);
        getContentPane().add(jMenuBar, BorderLayout.NORTH);

    }

    //Метод  відповідає  за ініціалізацію панелі редактора
    public void initEditor() {
        htmlTextPane.setContentType("text/html"); //встановити значення "text/html" в якості типа контента для компонента htmlTextPane.
        JScrollPane htmlScrollPane = new JScrollPane(htmlTextPane); //Створити новий локальный компонент JScrollPane на базі htmlTextPane
        tabbedPane.addTab("HTML", htmlScrollPane); //Додавати вкладку в панель tabbedPane з іменем "HTML" і компонентом з попереднього  пункту.
        JScrollPane plainScrollPane = new JScrollPane(plainTextPane); // Створити новий локальный компонент JScrollPane на базі  plainTextPane
        tabbedPane.addTab("Текст", plainScrollPane); //Додавати вкладку в панель tabbedPane з іменем "Текст" і компонентом з попереднього  пункту.
        tabbedPane.setPreferredSize(new Dimension(300, 300));//Встановлюємо необхідний  розмір панелі tabbedPane.
        TabbedPaneChangeListener listener = new TabbedPaneChangeListener(this);
        tabbedPane.addChangeListener(listener); //для компоненту tabbedPane повинен додаватися слухач TabbedPaneChangeListener через метод addChangeListener
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    ////Метод  відповідає  за ініціалізацію графічного интерфейсу
    public void initGui() {
        initMenuBar();
        initEditor();
        pack();
    }

    public boolean canUndo() {
        return undoManager.canUndo();
    }

    public boolean canRedo() {
        return undoManager.canRedo();
    }

    //Метод скасовує останню дію. Реалізуй його за допомогою undoManager. Метод не повинен кидати виняток, логіруй їх.
    public void undo() {
        try {
            undoManager.undo();
        } catch (CannotUndoException e) {
            ExceptionHandler.log(e);
        }
    }

    //Метод повертає раніше скасовану дію.
    public void redo() {
        try {
            undoManager.redo();
        } catch (CannotRedoException e) {
            ExceptionHandler.log(e);
        }
    }

    //Метод, який повинен скидати всі правки в менеджері undoManager.
    public void resetUndo() {
        undoManager.discardAllEdits();
    }

    //геттер до поля undoListener
    public UndoListener getUndoListener() {
        return undoListener;
    }

    //Метод  повинен повертати true, якщо вибрано вкладку, що відображає HTML у панелі вкладок
    public boolean isHtmlTabSelected() {
        if (tabbedPane.getSelectedIndex() == 0) {
            return true;
        } else {
            return false;
        }
    }

    //метод selectHtmlTab(), який повинен вибирати вкладку html та скидати всі редагування.
    public void  selectHtmlTab(){
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    // метод update(), який повинен встановлювати документ на панель редагування.
    public void  update(){
        htmlTextPane.setDocument(controller.getDocument());
    }

    // метод showAbout(), який повинен показувати діалогове вікно з інформацією про програму.
    public void showAbout(){
        JOptionPane.showMessageDialog(this, "The best HTML editor ", "Version 1.0", JOptionPane.INFORMATION_MESSAGE);
    }

    //метод викликається, коли відбулася зміна вибраної вкладки
    public void selectedTabChanged(){
        switch (tabbedPane.getSelectedIndex()) {
            case 0:
                controller.setPlainText(plainTextPane.getText());
                break;
            case 1:
                plainTextPane.setText(controller.getPlainText());
                break;
        }
        resetUndo();
    }
}
