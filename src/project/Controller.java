package project;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTMLWriter;
import java.io.*;

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

    //����� ������� ��������� �� �������� ������ �����
    public void openDocument() {
        view.selectHtmlTab(); //�������� html ������� � �������.
        JFileChooser jFileChooser = new JFileChooser(); //��������� ����� ��'��� ��� ������ ����� JFileChooser
        jFileChooser.setFileFilter(new HTMLFileFilter()); //������������� ��'���� ����� JFileChooser �� ������ ��'��� HTMLFileFilter
        if (jFileChooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) { ////���� ���������� ���������� ���� �����:
            currentFile = jFileChooser.getSelectedFile(); //�������� �������� ���� � ��� currentFile.
            resetDocument(); //����� resetDocument(), ���� ��������� �������� ��������.
            view.setTitle(currentFile.getName()); //������������� ��'� ����� �� ��������� ���� �������������.
            try (FileReader fileReader = new FileReader(currentFile)){
                new HTMLEditorKit().read(fileReader,document,0); ///���������� ��� � ��'���� FileReade-� � document
            } catch (IOException|BadLocationException e) {
                ExceptionHandler.log(e);
            }
            view.resetUndo(); //������� ������ (��������� ����� resetUndo �������).
        }

    }

    //����� ������ �������� �����, ��� saveDocumentAs(), �� �� ����������� ���� � ������������, � ������������ currentFile.
    public void saveDocument(){
       view.selectHtmlTab(); //�������� html ������� � �������.
       if (currentFile!=null){
           try (FileWriter fileWriter = new FileWriter(currentFile)){ //��������� FileWriter � ����������� currentFile.
               new HTMLEditorKit().write(fileWriter,document,0,document.getLength()); //���������� ��� � ��������� � ��'��� FileWriter-� ��������� ����, �� �� �� ������ � ����� getPlainText()
           } catch (IOException | BadLocationException e) {
               ExceptionHandler.log(e);
           }
       } else {
           saveDocumentAs();
       }
    }

    //����� ��� ���������� ����� �� ����� ��'��
    public void saveDocumentAs(){
        view.selectHtmlTab();//�������� html ������� � �������.
        JFileChooser jFileChooser = new JFileChooser(); //��������� ����� ��'��� ��� ������ ����� JFileChooser
        jFileChooser.setFileFilter(new HTMLFileFilter()); //������������� ��'���� ����� JFileChooser �� ������ ��'��� HTMLFileFilter
        if (jFileChooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION){ //���� ���������� ���������� ���� �����:
            currentFile = jFileChooser.getSelectedFile(); //�������� �������� ���� � ��� currentFile.
            view.setTitle(currentFile.getName()); //������������� ��'� ����� �� ��������� ���� �������������.

            try (FileWriter fileWriter = new FileWriter(currentFile)){ //��������� FileWriter � ����������� currentFile.
                HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
                htmlEditorKit.write(fileWriter,document,0,document.getLength()); //���������� ��� � ��������� � ��'��� FileWriter-� ��������� ����, �� �� �� ������ � ����� getPlainText().
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