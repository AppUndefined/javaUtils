package command;

public class CutCommand extends  Command {
    public CutCommand(Editor editor) {
        super(editor);
    }

    @Override
    public boolean execute() {
        if(editor.textFiled.getSelectedText().isEmpty()){
            return false;
        }
        backup();
        String source = editor.textFiled.getText();
        editor.clipboard = editor.textFiled.getSelectedText();
        editor.textFiled.setText(cutString(source));
        return false;
    }

    private String cutString(String source) {
        String start = source.substring(0, editor.textFiled.getSelectionStart());
        String end = source.substring(editor.textFiled.getSelectionEnd());
        return start + end;
    }
}
