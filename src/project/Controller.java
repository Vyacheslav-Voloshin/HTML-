package project;

import javax.swing.text.html.HTMLDocument;
import java.io.File;

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

    public static void main(String[] args) {
      View view = new View();
      Controller controller = new Controller(view);
      view.setController(controller);
      view.init();
      controller.init();
    }
}