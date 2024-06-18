package project;

import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTMLWriter;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

public class Controller {

    private View view; //���� ���������� �� ������������� ����� HTMLDocument document
    private HTMLDocument document; //���� ���������� �� ������������� View view
    private File currentFile;  //����, ��� ����������� �� ����, ���� ����� �������� � ������ �������� (�������� ����)

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

    //����� ������� ���������� ����� �� ��������� � ���� html ������
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

    //����� ��������� ������ ���������
    public void createNewDocument(){
        view.selectHtmlTab();//�������� html ������� � �������.
        resetDocument(); // ����� resetDocument(), ���� ��������� �������� ��������.
        view.setTitle("HTML ��������");
        currentFile = null;
    }
    public void openDocument(){}
    public void saveDocument(){}
    public void saveDocumentAs(){}

    public static void main(String[] args) {
      View view = new View();
      Controller controller = new Controller(view);
      view.setController(controller);
      view.init();
      controller.init();
    }
}