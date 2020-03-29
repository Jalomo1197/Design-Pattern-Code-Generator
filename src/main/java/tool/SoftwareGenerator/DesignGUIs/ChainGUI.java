package tool.SoftwareGenerator.DesignGUIs;

import tool.MyToolWindow;
import tool.SoftwareGenerator.SafeCheck;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ChainGUI {
    public static JPanel panel = new JPanel();
    public static JTextField handler_name = new JTextField();
    public static JTextField object_name = new JTextField();

    //For modification for design
    private static JTextField new_handler = new JTextField();
    //private static JTextField new_method = new JTextField();
    private static JButton add_handler = new JButton("Add Handler");
    //private static JButton add_field = new JButton("Add field");
    //private static JButton add_method = new JButton("Add method");
    private static JButton generate = new JButton("Generate");

    //For creation of compilation unit
    public static ArrayList<String> handlers = new ArrayList<>();
   // public static ArrayList<String> fields = new ArrayList<>();
    public static ArrayList<String> methods = new ArrayList<>();


    //package
    public static JTextField packageName = new JTextField();

    static{
        // ************ Setting sizes and layouts ***************
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        new_handler.setMaximumSize(new Dimension(GUIconstrants.width,  GUIconstrants.height));
        handler_name.setMaximumSize(new Dimension(GUIconstrants.width,  GUIconstrants.height));
        object_name.setMaximumSize(new Dimension(GUIconstrants.width,  GUIconstrants.height));
        packageName.setMaximumSize(new Dimension(GUIconstrants.width,  GUIconstrants.height));

        //*************************************************************************
        // ************ Getting names of related Classes/Interfaces ***************
        panel.add(new JLabel("\nName of package:*\n")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(packageName);

        panel.add(new JLabel("Name of the handler interface:")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(handler_name);

        panel.add(new JLabel("Class name of parameter that handlers take/handle:")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(object_name);

//Please enter the Class-type of the object that all concrete handlers should handle

        //*************************************************************************
        //******************   Adding Concrete Handlers  **************************
        panel.add(new JLabel("Add concrete handlers' class names:")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(new_handler);
        panel.add(add_handler);
        add_handler.addActionListener(e ->{
            String handler = new_handler.getText();
            if(SafeCheck.validName(handler)){
                new_handler.setText("");
                handlers.add(handler);
            }
        });

        //*************************************************************************
        //**************************   Generate button   **************************
        panel.add(generate);
        generate.addActionListener(e -> {
            //validate first
            ArrayList<String> names = new ArrayList<>();
            names.add(handler_name.getText());
            names.add(object_name.getText());

            if (SafeCheck.validPackageName(packageName.getText()) && SafeCheck.validInterfacesAndClasses(names)){
                //disable fields ? ?
                MyToolWindow.designFactory.getDesign("Chain", packageName.getText());
                //enable fields
                //clear fields
            }
        });
        //*************************************************************************
    }
}
