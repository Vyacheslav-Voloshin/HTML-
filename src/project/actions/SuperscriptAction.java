package project.actions;

import javax.swing.text.StyledEditorKit;
import java.awt.event.ActionEvent;

//Клас, який відповідає за стиль тексту "Надстрочный знак".
public class SuperscriptAction extends StyledEditorKit.StyledTextAction {
    public SuperscriptAction() {
        super("Надстрочный знак");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
