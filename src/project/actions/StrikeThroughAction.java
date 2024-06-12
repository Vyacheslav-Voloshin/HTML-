package project.actions;

import javax.swing.text.StyledEditorKit;
import java.awt.event.ActionEvent;
//Клас, який відповідає за стиль тексту "Зачеркнутый".
public class StrikeThroughAction extends StyledEditorKit.StyledTextAction{


    public StrikeThroughAction() {
        super("Зачеркнутый");

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
