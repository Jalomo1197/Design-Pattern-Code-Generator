package tool;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.wm.ToolWindow;
import javafx.scene.layout.Pane;
import tool.SoftwareGenerator.DesignGUIs.*;
import tool.SoftwareGenerator.SoftwareDesignFactory;

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
    private JPanel myToolWindowContent = new JPanel();
    private JPanel currentPanel = new JPanel(); //just for remove not to throw nullExecption
    private JLabel Title = new JLabel("Design Pattern Program Generator\n");
    private JButton generate = new JButton("Generate");
    private String[] designs = new String[] {"Abstract Factory", "Builder",
            "Chain", "Facade", "Factory", "Mediator", "Template", "Visitor"};
    private ComboBox<String> designList = new ComboBox<>(designs);
   // private Project project;
    private String projectBaseDiretory;
    private File dir;

    public MyToolWindow(ToolWindow toolWindow, final Project project){
        //Getting root directory of project;
        projectBaseDiretory = project.getBasePath();
        designFactory = new SoftwareDesignFactory(projectBaseDiretory);

        createGUI();
        createListeners();
    }


    void createGUI(){

        Title.setFont(new Font("Courier New", Font.BOLD, 18));
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        container.add(myToolWindowContent);

        JPanel cur = AbstractFactoryGUI.panel;
        cur = BuilderGUI.panel;
        cur = FactoryGUI.panel;
        cur = FacadeGUI.panel;
        cur = ChainGUI.panel;
        cur = VisitorGUI.panel;
        cur = TemplateGUI.panel;
        cur =MediatorGUI.panel;

        myToolWindowContent.add(Title);
        myToolWindowContent.add(designList);
        myToolWindowContent.setMaximumSize(new Dimension(750, 500));
        container.add(( currentPanel = AbstractFactoryGUI.panel));
    }

    void createListeners(){
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


    public JPanel getContent() {
        return container;
    }
}
