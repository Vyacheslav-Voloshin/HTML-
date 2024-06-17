package project;

import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.File;
import java.io.StringReader;

public class Controller {

    private View view; //поле відповідаюче за представлення моделі HTMLDocument document
    private HTMLDocument document; //поле відповідаюче за представлення View view
    private File currentFile;  //поле, яке відповідатиме за файл, який зараз відкритий у нашому редакторі (поточний файл)

    public Controller(View view) {
        this.view = view;
    }

    public void init(){}

    public void exit(){
        System.exit(0);
    }

    public HTMLDocument getDocument() {
        return document;
    }

    // метод resetDocument(), який скидатиме поточний документ.
    public void  resetDocument(){
        if (document !=null)
        document.removeUndoableEditListener(view.getUndoListener()); // Видаляємо у поточного документа document слухача правок, які можна скасувати/повернути
        document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();//Створювати новий документ за замовчуванням та надавати його полю document.
        document.addUndoableEditListener(view.getUndoListener()); //Додавати новому документу слухача правок
        view.update();//Викликати у подання метод update().
    }

    //Метод записуватиме переданий текст з html тегами в документ document.
    public void setPlainText(String text){
        resetDocument();
        StringReader reader = new StringReader(text);
        try {
             new HTMLEditorKit().read(reader, document, 0);
        } catch (Exception e){
            ExceptionHandler.log(e);
        }
    }

    public static void main(String[] args) {
      View view = new View();
      Controller controller = new Controller(view);
      view.setController(controller);
      view.init();
      controller.init();
    }
}