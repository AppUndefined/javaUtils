package command;

public class CopyCommand extends Command {
    public CopyCommand(Editor editor) {
        super(editor);
    }
    @Override
    public boolean execute() {
        editor.clipboard = editor.textFiled.getSelectedText();
        return false;
    }
}
