package command;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Editor {
    public JTextArea  textFiled;
    public String clipboard;
    private CommandHistory  history = new CommandHistory();
    public void init() {
        JFrame jFrame = new JFrame("文本编辑器");
        JPanel jPanel = new JPanel();
        jFrame.setContentPane(jPanel);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));
        textFiled = new JTextArea();
        textFiled.setLineWrap(true);
        jPanel.add(textFiled);
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton ctrlC = new JButton("Ctrl+C");
        JButton ctrlX = new JButton("Ctrl+X");
        JButton ctrlV = new JButton("Ctrl+V");
        JButton ctrlZ = new JButton("Ctrl+Z");
        Editor editor = this;
        ctrlC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeCommand(new CopyCommand(editor));
            }
        });
        ctrlX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeCommand(new CutCommand(editor));
            }
        });
        ctrlV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeCommand(new PasteCommand(editor));
            }
        });
        ctrlZ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                undo();
            }
        });
        buttons.add(ctrlC);
        buttons.add(ctrlX);
        buttons.add(ctrlV);
        buttons.add(ctrlZ);
        jPanel.add(buttons);
        jFrame.setSize(450,200);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

    }

    private void undo() {
        if(history.isEmpty())return;
        Command command = history.pop();
        if (command != null) {
            command.undo();
        }
    }
    private void executeCommand(Command command){
        if(command.execute()){
            history.push(command);
        }
    }
    
}
