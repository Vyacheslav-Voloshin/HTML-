package project;

import project.Controller;
import project.listeners.FrameListener;
import project.listeners.TabbedPaneChangeListener;

import javax.swing.*;
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

    private  JTabbedPane tabbedPane = new JTabbedPane(); //�� ���� ������ � ����� ���������
    private JTextPane htmlTextPane =new JTextPane(); //�� ���� ��������� ��� ���������� ������������ html ³� ���� ��������� �� ������ �������.
    private JEditorPane plainTextPane = new JEditorPane(); //�� ���� ��������� ��� ����������� HTML � ������ ������,�� ������������ ��� html (���� �� �� ����).

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
//�����  �������  �� ����������� ����
    public void initMenuBar(){

    }
//�����  �������  �� ����������� ����� ���������
    public void initEditor(){
        htmlTextPane.setContentType("text/html"); //���������� �������� "text/html" � ����� ���� �������� ��� ���������� htmlTextPane.
        JScrollPane htmlScrollPane = new JScrollPane(htmlTextPane); //�������� ����� ��������� ��������� JScrollPane �� ��� htmlTextPane
        tabbedPane.addTab("HTML",htmlScrollPane); //�������� ������� � ������ tabbedPane � ������ "HTML" � ����������� � ������������  ������.
        JScrollPane plainScrollPane = new JScrollPane(plainTextPane); // �������� ����� ��������� ��������� JScrollPane �� ���  plainTextPane
        tabbedPane.addTab("�����", plainScrollPane); //�������� ������� � ������ tabbedPane � ������ "�����" � ����������� � ������������  ������.
        tabbedPane.setPreferredSize(new Dimension(300, 300));//������������ ����������  ����� ����� tabbedPane.
        TabbedPaneChangeListener listener = new TabbedPaneChangeListener(this);
        tabbedPane.addChangeListener(listener); //��� ���������� tabbedPane ������� ���������� ������ TabbedPaneChangeListener ����� ����� addChangeListener
        getContentPane().add(tabbedPane,BorderLayout.CENTER);
    }
////�����  �������  �� ����������� ���������� ����������
    public void initGui(){
        initMenuBar();
        initEditor();
        pack();
    }

    public void selectedTabChanged() {

    }
}
