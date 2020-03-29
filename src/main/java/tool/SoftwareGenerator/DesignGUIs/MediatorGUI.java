package tool.SoftwareGenerator.DesignGUIs;

import tool.MyToolWindow;
import tool.SoftwareGenerator.SafeCheck;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MediatorGUI {
    public static JPanel panel = new JPanel();
    public static JTextField mediator_name = new JTextField();

    private static JTextField new_component = new JTextField();
    private static JTextField new_method = new JTextField();
    private static JButton add_component = new JButton("Add component");
    private static JButton add_method = new JButton("Add method");
    private static JButton generate = new JButton("Generate");

    //For creation of compilation unit
    public static ArrayList<String> components = new ArrayList<>();
    public static ArrayList<String> methods = new ArrayList<>();

    //package
    public static JTextField packageName = new JTextField();


    static{
        // ************ Setting sizes and layouts ***************
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        mediator_name.setMaximumSize(new Dimension(GUIconstrants.width,  GUIconstrants.height));
        new_component.setMaximumSize(new Dimension(GUIconstrants.width,  GUIconstrants.height));
        new_method.setMaximumSize(new Dimension(GUIconstrants.width,  GUIconstrants.height));
        packageName.setMaximumSize(new Dimension(GUIconstrants.width,  GUIconstrants.height));

        panel.add(new JLabel("\nName of package:*\n")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(packageName);

        panel.add(new JLabel("Name of the mediator class:")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(mediator_name);

        //****************** adding components *******************
        panel.add(new JLabel("Please enter the class names of the objects")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(new JLabel("that the Mediator will have references to:")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(new_component);
        panel.add(add_component);

        // Class
        add_component.addActionListener(e -> {
            String component = new_component.getText();
            if (SafeCheck.validName(component)){
                new_component.setText("");
                components.add(component);
            }
        });

        //*************************************************************************
        //**************************   Generate button   **************************
        panel.add(generate);
        generate.addActionListener(e -> {
            //validate first
            ArrayList<String> names = new ArrayList<>();
            names.add(mediator_name.getText());

            if (SafeCheck.validPackageName(packageName.getText()) && SafeCheck.validInterfacesAndClasses(names)) {
                //disable fields ? ?
                MyToolWindow.designFactory.getDesign("Mediator", packageName.getText());
                //enable fields
                //clear fields
            }
        });
        //*************************************************************************
    }
}
