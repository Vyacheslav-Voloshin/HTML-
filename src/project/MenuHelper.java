package project;

import javax.swing.*;
import java.awt.event.ActionListener;

//�� ���� ��������� ���� ��� ����������� �� ������������ ����.
public class MenuHelper {

    public static JMenuItem addMenuItem(JMenu parent, String text, ActionListener actionListener){
//�� parent - ���� � ��� �� ������ �����,
// text - ����� ������, �� ��������,
// actionListener - ������ �� ������ ����, �� ��������.
       JMenuItem jMenuItem = new JMenuItem(text); //��������� ����� ����� ���� JMenuItem, �������������� text.
       jMenuItem.addActionListener(actionListener); //������������� ��� ����� ������� �� �� ��������� ������ addActionListener()
       parent.add(jMenuItem); //�������� �� parent ��������� ����� ����.
       return jMenuItem;
    }

    public static JMenuItem addMenuItem(JMenu parent, Action action){
       JMenuItem jMenuItem = new JMenuItem(action); ////��������� ����� ����� ���� JMenuItem, �������������� action
       parent.add(jMenuItem);//�������� �� parent ��������� ����� ����.
       return jMenuItem;
    }

    public static JMenuItem addMenuItem(JMenu parent, String text, Action action){
        JMenuItem jMenuItem = addMenuItem(parent,action); //��������� ����, ���������� ��������� �����
        jMenuItem.setText(text); // ������������ ���
        parent.add(jMenuItem);
        return jMenuItem;
    }
//������������ ���� ��������.
    public static void initHelpMenu(View view, JMenuBar menuBar){}

//������������ ���� ������ ������.
    public static void initFontMenu(View view, JMenuBar menuBar){}

//������������ ���� ������ �������.
    public static void initColorMenu(View view, JMenuBar menuBar){}

//������������ ���� �����������.
    public static void initAlignMenu(View view, JMenuBar menuBar){}

//������������ ���� ������ ����� ������.
    public static void initStyleMenu(View view, JMenuBar menuBar){}

//������������ ���� ������������ ������.
    public static void initEditMenu(View view, JMenuBar menuBar){}

//������������ ���� ����.
    public static void initFileMenu(View view, JMenuBar menuBar){}
}
