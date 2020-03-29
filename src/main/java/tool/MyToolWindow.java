package tool;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.JBColor;
import javafx.scene.layout.Pane;
import tool.SoftwareGenerator.DesignGUIs.*;
import tool.SoftwareGenerator.SoftwareDesignFactory;
import tool.SoftwareGenerator.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MyToolWindow {
    //Software factory
    static public SoftwareDesignFactory designFactory;

    //contents
    private JPanel container = new JPanel();
    private JPanel currentPanel = new JPanel(); //just for remove not to throw nullExecption
    private JLabel Title = new JLabel("Design Pattern Program Generator\n");
    public static JLabel Error = new JLabel("\n");
    private String[] designs = new String[] {"Abstract Factory", "Builder", "Chain", "Facade", "Factory", "Mediator", "Template", "Visitor"};
    private ComboBox<String> designList = new ComboBox<>(designs);


    static public Project project;

    public MyToolWindow(ToolWindow toolWindow, final Project project){
        //Getting root directory of project;
        MyToolWindow.project = project;
        designFactory = new SoftwareDesignFactory();
        createGUI();
        createListeners();
    }


    private void createGUI(){
        Title.setFont(new Font("Courier New", Font.BOLD, 16));
        Error.setFont(new Font("Courier New", Font.BOLD, 14));
        designList.setMaximumSize(new Dimension(260, 40));
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        container.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel cur = AbstractFactoryGUI.panel;
        cur = BuilderGUI.panel;
        cur = FactoryGUI.panel;
        cur = FacadeGUI.panel;
        cur = ChainGUI.panel;
        cur = VisitorGUI.panel;
        cur = TemplateGUI.panel;
        cur =MediatorGUI.panel;

        container.add(Title);
        container.add(designList);
        container.add(Error);
        designList.setAlignmentX(Component.CENTER_ALIGNMENT);

        container.add(( currentPanel = AbstractFactoryGUI.panel));
    }

    private void createListeners(){
        // designList box Listener
        ActionListener designList_listener = event -> {
            container.remove(currentPanel);
            switch(designList.getSelectedItem().toString()){
                case "Abstract Factory":
                    container.add(( currentPanel = AbstractFactoryGUI.panel));
                    break;
                case "Builder":
                    container.add(( currentPanel = BuilderGUI.panel));
                    break;
                case "Factory":
                    container.add(( currentPanel = FactoryGUI.panel));
                    break;
                case "Facade":
                    container.add(( currentPanel = FacadeGUI.panel));
                    break;
                case "Chain":
                    container.add(( currentPanel = ChainGUI.panel));
                    break;
                case "Visitor":
                    container.add(( currentPanel = VisitorGUI.panel));
                    break;
                case "Template":
                    container.add(( currentPanel = TemplateGUI.panel));
                    break;
                case "Mediator":
                    container.add(( currentPanel = MediatorGUI.panel));
                    break;
                default:
                    container.add(( currentPanel = AbstractFactoryGUI.panel));
            }

            container.updateUI();
        };
        designList.addActionListener(designList_listener);
    }

    public static void ErrorMessage(String msg){
        Error.setText(msg);
        Error.updateUI();
    }

    public static void clearError(){
        Error.setText("");
        Error.updateUI();
    }

    public JPanel getContent() {
        return container;
    }
}
