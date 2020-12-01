package command;

public abstract class Command {
    public Editor editor;
    private String backup;
    public Command(Editor editor) {
        this.editor = editor;
    }
    void backup(){
        backup = editor.textFiled.getText();
    }
    public void undo(){
        editor.textFiled.setText(backup);
    }
    public abstract boolean execute();

}
