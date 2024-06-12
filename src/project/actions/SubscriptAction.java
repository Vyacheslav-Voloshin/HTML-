package project.actions;

import javax.swing.text.StyledEditorKit;
import java.awt.event.ActionEvent;
//Клас, який відповідає за стиль тексту "Подстрочный знак".
public class SubscriptAction extends StyledEditorKit.StyledTextAction {

    public SubscriptAction() {
        super("Подстрочный знак");

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
