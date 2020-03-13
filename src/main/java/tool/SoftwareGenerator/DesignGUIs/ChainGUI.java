package tool.SoftwareGenerator.DesignGUIs;

import tool.MyToolWindow;

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


    static{
        // ************ Setting sizes and layouts ***************
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        new_handler.setMaximumSize(new Dimension(600, 200));
        handler_name.setMaximumSize(new Dimension(600, 200));
        object_name.setMaximumSize(new Dimension(600, 200));

        //*************************************************************************
        // ************ Getting names of related Classes/Interfaces ***************
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
            new_handler.setText("");

            if (handler.equals("")){
                System.out.println("invalid entry of field");
            }
            else{
                //validate??
                handlers.add(handler);
            }
        });

        //*************************************************************************
        //**************************   Generate button   **************************
        panel.add(generate);
        generate.addActionListener(e -> {
            //TODO: validate input before calling factory
            MyToolWindow.designFactory.getDesign("Chain");
        });
        //*************************************************************************
    }
}
