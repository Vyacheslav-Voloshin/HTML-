import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/* ��������� ��������� ���� ����, � ����� ���� ���� � ������ � ����� ���������.
 �� ������ ������� ������������������� �������� ������, ��� ���������� html �������.
 �� �� ����� ���� ����������� �� ���������� ����� �������.
 �� ����� ������� ���� ��������, ���� ������������ ��� html �������, � ����� ������ ����� �� �������������� html ����.
 � ����� ����� ����� ���� �������� ����� �������, �������� �� �������� ��� ����.*/
public class View extends JFrame implements ActionListener {

    private Controller controller;

    private  JTabbedPane tabbedPane; //�� ���� ������ � ����� ���������
    private JTextPane htmlTextPane; //�� ���� ��������� ��� ���������� ������������ html ³� ���� ��������� �� ������ �������.
    private JEditorPane plainTextPane; //�� ���� ��������� ��� ����������� HTML � ������ ������,�� ������������ ��� html (���� �� �� ����).

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void init(){}

    public void exit(){
        controller.exit();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
