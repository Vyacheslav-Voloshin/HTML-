package project;

import javax.swing.filechooser.FileFilter;
import java.io.File;
//Для відкриття або збереження файлу ми будемо використовувати JFileChooser з бібліотеки swing.
//Об'єкти цього підтримують фільтри, успадковані від FileFilter.
//Зараз ми напишемо свій фільтр:
public class HTMLFileFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        return ((f.isDirectory())
                || (f.getName().toLowerCase().endsWith(".htm"))
                || (f.getName().toLowerCase().endsWith(".html")));
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файли";
    }
}
