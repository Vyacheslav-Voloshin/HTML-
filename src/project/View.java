package project;

import project.Controller;
import project.listeners.FrameListener;

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

    }
////�����  �������  �� ����������� ���������� ����������
    public void initGui(){
        initMenuBar();
        initEditor();
        pack();
    }
}
