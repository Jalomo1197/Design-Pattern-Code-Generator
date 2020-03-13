package tool.SoftwareGenerator.DesignGUIs;

import tool.MyToolWindow;

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


    static{
        // ************ Setting sizes and layouts ***************
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        mediator_name.setMaximumSize(new Dimension(600, 200));
        new_component.setMaximumSize(new Dimension(600, 200));
        new_method.setMaximumSize(new Dimension(600, 200));



        panel.add(new JLabel("Name of the mediator class:")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(mediator_name);

        //****************** adding components *******************
        panel.add(new JLabel("Please enter the class names of the objects")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(new JLabel("that the Mediator will have references to:")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(new_component);
        panel.add(add_component);

        add_component.addActionListener(e -> {
            String component = new_component.getText();
            new_component.setText("");
            if (component.equals("")){
                System.out.println("invalid entry of field");
            }
            else{
                //validate with split??
                components.add(component);
            }
        });

        //*************************************************************************
        //**************************   Generate button   **************************
        panel.add(generate);
        generate.addActionListener(e -> {
            //TODO: validate input before calling factory
            MyToolWindow.designFactory.getDesign("Mediator");
        });
        //*************************************************************************
    }
}
