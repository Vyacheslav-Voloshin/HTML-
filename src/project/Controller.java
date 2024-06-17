package project;

import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.File;
import java.io.StringReader;

public class Controller {

    private View view; //���� ���������� �� ������������� ����� HTMLDocument document
    private HTMLDocument document; //���� ���������� �� ������������� View view
    private File currentFile;  //����, ��� ����������� �� ����, ���� ����� �������� � ������ �������� (�������� ����)

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

    // ����� resetDocument(), ���� ��������� �������� ��������.
    public void  resetDocument(){
        if (document !=null)
        document.removeUndoableEditListener(view.getUndoListener()); // ��������� � ��������� ��������� document ������� ������, �� ����� ���������/���������
        document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();//���������� ����� �������� �� ������������� �� �������� ���� ���� document.
        document.addUndoableEditListener(view.getUndoListener()); //�������� ������ ��������� ������� ������
        view.update();//��������� � ������� ����� update().
    }

    //����� ������������ ��������� ����� � html ������ � �������� document.
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