package tool.SoftwareGenerator.DesignGUIs;

import tool.MyToolWindow;
import tool.SoftwareGenerator.SafeCheck;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FacadeGUI {
    public static JPanel panel = new JPanel();
    public static JTextField facade_name = new JTextField();

    private static JTextField new_field = new JTextField();
    private static JTextField new_method = new JTextField();
    private static JTextField new_subSystem = new JTextField();
    private static JButton add_field = new JButton("Add field");
    private static JButton add_method = new JButton("Add method");
    private static JButton add_subSystem = new JButton("Add subsystem class");
    private static JButton generate = new JButton("Generate");

    //For creation of compilation unit
    public static ArrayList<String> facade_fields = new ArrayList<>();
    public static ArrayList<String> facade_methods = new ArrayList<>();
    public static ArrayList<String> subSystem_classes = new ArrayList<>();


    //package
    public static JTextField packageName = new JTextField();

    static{
        // ************ Setting sizes and layouts ***************
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        facade_name.setMaximumSize(new Dimension(GUIconstrants.width,  GUIconstrants.height));
        new_field.setMaximumSize(new Dimension(GUIconstrants.width,  GUIconstrants.height));
        new_method.setMaximumSize(new Dimension(GUIconstrants.width,  GUIconstrants.height));
        new_subSystem.setMaximumSize(new Dimension(GUIconstrants.width,  GUIconstrants.height));
        packageName.setMaximumSize(new Dimension(GUIconstrants.width,  GUIconstrants.height));


        // ************ Getting names of related Classes/Interfaces ***************
        panel.add(new JLabel("\nName of package:*\n")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(packageName);

        panel.add(new JLabel("Name of the facade class:")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(facade_name);


        //*************************************************************************
        //************* Adding Fields/Methods To Facade  **************************
        panel.add(new JLabel("\nAdd fields to facade class:")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(new JLabel("\nClassType VarName")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(new_field);
        panel.add(add_field);
        panel.add(new JLabel("\nAdd methods to facade class:")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(new JLabel("\nreturnType MethodName(type p1, type p2, ...)")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(new_method);
        panel.add(add_method);

        add_field.addActionListener(e -> {
            String field = new_field.getText();
            if (SafeCheck.validField(field)){
                new_field.setText("");
                facade_fields.add(field);
            }
        });

        add_method.addActionListener(e -> {
            String method = new_method.getText();
            if (SafeCheck.validMethod(method)){
                new_method.setText("");
                facade_methods.add(method);
            }
        });

        //*************************************************************************
        //************* Adding SubSystem Classes  *********************************
        panel.add(new JLabel("\nAdd subsystem classes that facade uses:")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(new_subSystem);
        panel.add(add_subSystem);

        add_subSystem.addActionListener(e -> {
            String subSystem = new_subSystem.getText();
            if(SafeCheck.validName(subSystem)){
                new_subSystem.setText("");
                subSystem_classes.add(subSystem);
            }
        });
        //*************************************************************************
        //**************************   Generate button   **************************
        panel.add(generate);
        generate.addActionListener(e -> {
            //validate first
            ArrayList<String> names = new ArrayList<>();
            names.add(facade_name.getText());

            if (SafeCheck.validPackageName(packageName.getText()) && SafeCheck.validInterfacesAndClasses(names)) {
                //disable fields ? ?
                MyToolWindow.designFactory.getDesign("Facade", packageName.getText());
                //enable fields
                //clear fields
            }
        });
        //*************************************************************************
    }
}
