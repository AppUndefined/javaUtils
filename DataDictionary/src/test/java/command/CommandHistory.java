package command;

import java.util.Stack;

public class CommandHistory {

    private Stack<Command>  history = new Stack<Command>();

    public void push(Command command) {
        history.push(command);
    }
    public Command  pop() {
        return history.pop();
    }
    public boolean isEmpty(){
        return history.isEmpty();
    }
}
