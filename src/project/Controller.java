package project;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTMLWriter;
import java.io.*;

public class Controller {

    private View view; //поле відповідаюче за представлення моделі HTMLDocument document
    private HTMLDocument document; //поле відповідаюче за представлення View view
    private File currentFile;  //поле, яке відповідатиме за файл, який зараз відкритий у нашому редакторі (поточний файл)

    public Controller(View view) {
        this.view = view;
    }

    public void init(){
        createNewDocument();
    }

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

    //Метод повинен отримувати текст із документа з усіма html тегами
    public String getPlainText(){
        StringWriter stringWriter = new StringWriter();
        try {
            HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
            htmlEditorKit.write(stringWriter,document,0,document.getLength());
        } catch (Exception e){
            ExceptionHandler.log(e);
        }
        return stringWriter.toString();
    }

    //метод створення нового документа
    public void createNewDocument(){
        view.selectHtmlTab();//Вибирати html вкладку у вистави.
        resetDocument(); // метод resetDocument(), який скидатиме поточний документ.
        view.setTitle("HTML редактор");
        currentFile = null;
    }

    //метод повинен відповідати за відкриття нового файлу
    public void openDocument() {
        view.selectHtmlTab(); //Вибираємо html вкладку у вистави.
        JFileChooser jFileChooser = new JFileChooser(); //створюємо новий об'єкт для вибору файлу JFileChooser
        jFileChooser.setFileFilter(new HTMLFileFilter()); //встановлювати об'єкту класу JFileChooser як фільтр об'єкт HTMLFileFilter
        if (jFileChooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) { ////Якщо користувач підтвердить вибір файлу:
            currentFile = jFileChooser.getSelectedFile(); //Зберігаємо вибраний файл у полі currentFile.
            resetDocument(); //метод resetDocument(), який скидатиме поточний документ.
            view.setTitle(currentFile.getName()); //Встановлюваємо ім'я файлу як заголовок вікна представлення.
            try (FileReader fileReader = new FileReader(currentFile)){
                new HTMLEditorKit().read(fileReader,document,0); ///Переписуємо дані з об'єкта FileReade-а в document
            } catch (IOException|BadLocationException e) {
                ExceptionHandler.log(e);
            }
            view.resetUndo(); //Скинути правки (викликати метод resetUndo подання).
        }

    }

    //Метод должен работать также, как saveDocumentAs(), но не запрашивать файл у пользователя, а использовать currentFile.
    public void saveDocument(){
       view.selectHtmlTab(); //Вибираємо html вкладку у вистави.
       if (currentFile!=null){
           try (FileWriter fileWriter = new FileWriter(currentFile)){ //Створюємо FileWriter з урахуванням currentFile.
               new HTMLEditorKit().write(fileWriter,document,0,document.getLength()); //Переписуємо дані з документа в об'єкт FileWriter-а аналогічно тому, як ми це робили в методі getPlainText()
           } catch (IOException | BadLocationException e) {
               ExceptionHandler.log(e);
           }
       } else {
           saveDocumentAs();
       }
    }

    //метод для збереження файлу під новим ім'ям
    public void saveDocumentAs(){
        view.selectHtmlTab();//Вибирати html вкладку у вистави.
        JFileChooser jFileChooser = new JFileChooser(); //створюємо новий об'єкт для вибору файлу JFileChooser
        jFileChooser.setFileFilter(new HTMLFileFilter()); //встановлювати об'єкту класу JFileChooser як фільтр об'єкт HTMLFileFilter
        if (jFileChooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION){ //Якщо користувач підтвердить вибір файлу:
            currentFile = jFileChooser.getSelectedFile(); //Зберігаємо вибраний файл у полі currentFile.
            view.setTitle(currentFile.getName()); //Встановлюваємо ім'я файлу як заголовок вікна представлення.

            try (FileWriter fileWriter = new FileWriter(currentFile)){ //Створюємо FileWriter з урахуванням currentFile.
                HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
                htmlEditorKit.write(fileWriter,document,0,document.getLength()); //Переписуємо дані з документа в об'єкт FileWriter-а аналогічно тому, як ми це робили в методі getPlainText().
            } catch (IOException | BadLocationException e) {
                ExceptionHandler.log(e);
            }
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