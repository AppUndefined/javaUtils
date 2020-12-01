package command;

public class PasteCommand extends Command {
    public PasteCommand(Editor editor) {
        super(editor);
    }

    @Override
    public boolean execute() {
        if(editor.clipboard == null || editor.clipboard.isEmpty()){
            return false;
        }
        backup();
        editor.textFiled.insert(editor.clipboard,editor.textFiled.getCaretPosition());
        return true;
    }
}
