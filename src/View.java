import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/* Графічний інтерфейс буде вікно, в якому буде меню і панель з двома вкладками.
 На першій вкладці розташовуватиметься текстова панель, яка малюватиме html сторінку.
 На ній можна буде форматувати та редагувати текст сторінки.
 На другій вкладці буде редактор, який відображатиме код html сторінки, в ньому будуть видно всі використовувані html теги.
 У ньому також можна буде змінювати текст сторінки, додавати та видаляти різні теги.*/
public class View extends JFrame implements ActionListener {

    private Controller controller;

    private  JTabbedPane tabbedPane; //це буде панель з двома вкладками
    private JTextPane htmlTextPane; //це буде компонент для візуального редактування html Він буде розміщений на першій вкладці.
    private JEditorPane plainTextPane; //це буде компонент для редагування HTML у вигляді тексту,він відображатиме код html (теги та їх вміст).

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void init(){}

    public void exit(){
        controller.exit();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
