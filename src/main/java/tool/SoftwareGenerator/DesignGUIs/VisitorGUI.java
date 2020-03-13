package tool.SoftwareGenerator.DesignGUIs;

import tool.MyToolWindow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VisitorGUI {
    public static JPanel panel = new JPanel();
    public static JTextField visitor_name = new JTextField();

    private static JTextField new_class = new JTextField();
    private static JTextField new_method = new JTextField();
    private static JButton add_class = new JButton("Add field");
    private static JButton add_method = new JButton("Add method");
    private static JButton generate = new JButton("Generate");

    //For creation of compilation unit
    public static ArrayList<String> classes = new ArrayList<>();
    public static ArrayList<String> methods = new ArrayList<>();


    static{
        // ************ Setting sizes and layouts ***************
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        visitor_name.setMaximumSize(new Dimension(600, 200));
        new_method.setMaximumSize(new Dimension(600, 200));
        new_class.setMaximumSize(new Dimension(600, 200));


        panel.add(new JLabel("Name of the visitor interface:")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(visitor_name);


        //"What objects should the visitors be able to visit. Enter 'done' when finished."
        panel.add(new JLabel("Add class names of objects the visitor can visit:")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(new_class);
        panel.add(add_class);
        add_class.addActionListener(e -> {
            String Class = new_class.getText();
            new_class.setText("");
            if (Class.equals("")){
                System.out.println("invalid entry of field");
            }
            else{
                //validate with split??
                classes.add(Class);
            }
        });

        //Generate button
        panel.add(generate);
        generate.addActionListener(e -> {
            //validate first!!!
            MyToolWindow.designFactory.getDesign("Visitor");
        });
    }
}
