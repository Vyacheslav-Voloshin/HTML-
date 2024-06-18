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


/* ��������� ��������� ���� ����, � ����� ���� ���� � ������ � ����� ���������.
 �� ������ ������� ������������������� �������� ������, ��� ���������� html �������.
 �� �� ����� ���� ����������� �� ���������� ����� �������.
 �� ����� ������� ���� ��������, ���� ������������ ��� html �������, � ����� ������ ����� �� �������������� html ����.
 � ����� ����� ����� ���� �������� ����� �������, �������� �� �������� ��� ����.*/
public class View extends JFrame implements ActionListener {

    private Controller controller;

    private JTabbedPane tabbedPane = new JTabbedPane(); //�� ���� ������ � ����� ���������
    private JTextPane htmlTextPane = new JTextPane(); //�� ���� ��������� ��� ���������� ������������ html ³� ���� ��������� �� ������ �������.
    private JEditorPane plainTextPane = new JEditorPane(); //�� ���� ��������� ��� ����������� HTML � ������ ������,�� ������������ ��� html (���� �� �� ����).

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

    //�����  �������  �� ����������� ����
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

    //�����  �������  �� ����������� ����� ���������
    public void initEditor() {
        htmlTextPane.setContentType("text/html"); //���������� �������� "text/html" � ����� ���� �������� ��� ���������� htmlTextPane.
        JScrollPane htmlScrollPane = new JScrollPane(htmlTextPane); //�������� ����� ��������� ��������� JScrollPane �� ��� htmlTextPane
        tabbedPane.addTab("HTML", htmlScrollPane); //�������� ������� � ������ tabbedPane � ������ "HTML" � ����������� � ������������  ������.
        JScrollPane plainScrollPane = new JScrollPane(plainTextPane); // �������� ����� ��������� ��������� JScrollPane �� ���  plainTextPane
        tabbedPane.addTab("�����", plainScrollPane); //�������� ������� � ������ tabbedPane � ������ "�����" � ����������� � ������������  ������.
        tabbedPane.setPreferredSize(new Dimension(300, 300));//������������ ����������  ����� ����� tabbedPane.
        TabbedPaneChangeListener listener = new TabbedPaneChangeListener(this);
        tabbedPane.addChangeListener(listener); //��� ���������� tabbedPane ������� ���������� ������ TabbedPaneChangeListener ����� ����� addChangeListener
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    ////�����  �������  �� ����������� ���������� ����������
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

    //����� ������� ������� ��. ������� ���� �� ��������� undoManager. ����� �� ������� ������ �������, ������ ��.
    public void undo() {
        try {
            undoManager.undo();
        } catch (CannotUndoException e) {
            ExceptionHandler.log(e);
        }
    }

    //����� ������� ����� ��������� ��.
    public void redo() {
        try {
            undoManager.redo();
        } catch (CannotRedoException e) {
            ExceptionHandler.log(e);
        }
    }

    //�����, ���� ������� ������� �� ������ � �������� undoManager.
    public void resetUndo() {
        undoManager.discardAllEdits();
    }

    //������ �� ���� undoListener
    public UndoListener getUndoListener() {
        return undoListener;
    }

    //�����  ������� ��������� true, ���� ������� �������, �� �������� HTML � ����� �������
    public boolean isHtmlTabSelected() {
        if (tabbedPane.getSelectedIndex() == 0) {
            return true;
        } else {
            return false;
        }
    }

    //����� selectHtmlTab(), ���� ������� �������� ������� html �� ������� �� �����������.
    public void  selectHtmlTab(){
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    // ����� update(), ���� ������� ������������� �������� �� ������ �����������.
    public void  update(){
        htmlTextPane.setDocument(controller.getDocument());
    }

    // ����� showAbout(), ���� ������� ���������� �������� ���� � ����������� ��� ��������.
    public void showAbout(){
        JOptionPane.showMessageDialog(this, "The best HTML editor ", "Version 1.0", JOptionPane.INFORMATION_MESSAGE);
    }

    //����� �����������, ���� �������� ���� ������� �������
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
