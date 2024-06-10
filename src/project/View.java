package project;

import project.Controller;
import project.listeners.FrameListener;

import javax.swing.*;
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

    private  JTabbedPane tabbedPane = new JTabbedPane(); //це буде панель з двома вкладками
    private JTextPane htmlTextPane =new JTextPane(); //це буде компонент для візуального редактування html Він буде розміщений на першій вкладці.
    private JEditorPane plainTextPane = new JEditorPane(); //це буде компонент для редагування HTML у вигляді тексту,він відображатиме код html (теги та їх вміст).

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void init(){
        initGui();
        FrameListener frameListener = new FrameListener(this);
        addWindowListener(frameListener);
        this.setVisible(true);
    }

    public void exit(){
        controller.exit();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
//Метод  відповідає  за ініціалізацію меню
    public void initMenuBar(){

    }
//Метод  відповідає  за ініціалізацію панелі редактора
    public void initEditor(){

    }
////Метод  відповідає  за ініціалізацію графічного интерфейсу
    public void initGui(){
        initMenuBar();
        initEditor();
        pack();
    }
}
