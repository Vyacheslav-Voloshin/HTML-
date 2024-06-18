package project;

import javax.swing.filechooser.FileFilter;
import java.io.File;
//��� �������� ��� ���������� ����� �� ������ ��������������� JFileChooser � �������� swing.
//��'���� ����� ���������� �������, ���������� �� FileFilter.
//����� �� �������� ��� ������:
public class HTMLFileFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        return ((f.isDirectory())
                || (f.getName().toLowerCase().endsWith(".htm"))
                || (f.getName().toLowerCase().endsWith(".html")));
    }

    @Override
    public String getDescription() {
        return "HTML � HTM �����";
    }
}
