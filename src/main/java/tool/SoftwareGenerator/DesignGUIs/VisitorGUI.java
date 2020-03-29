package tool.SoftwareGenerator.DesignGUIs;

import tool.MyToolWindow;
import tool.SoftwareGenerator.SafeCheck;

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

    //package
    public static JTextField packageName = new JTextField();

    static{
        // ************ Setting sizes and layouts ***************
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        visitor_name.setMaximumSize(new Dimension(GUIconstrants.width,  GUIconstrants.height));
        new_method.setMaximumSize(new Dimension(GUIconstrants.width,  GUIconstrants.height));
        new_class.setMaximumSize(new Dimension(GUIconstrants.width,  GUIconstrants.height));
        packageName.setMaximumSize(new Dimension(GUIconstrants.width,  GUIconstrants.height));

        panel.add(new JLabel("\nName of package:*\n")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(packageName);


        panel.add(new JLabel("Name of the visitor interface:")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(visitor_name);


        //"What objects should the visitors be able to visit. Enter 'done' when finished."
        panel.add(new JLabel("Add class names of objects the visitor can visit:")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(new_class);
        panel.add(add_class);
        add_class.addActionListener(e -> {
            String Class = new_class.getText();
            if (SafeCheck.validName(Class)){
                new_class.setText("");
                classes.add(Class);
            }
        });

        //Generate button
        panel.add(generate);
        generate.addActionListener(e -> {
            //validate first
            ArrayList<String> names = new ArrayList<>();
            names.add(visitor_name.getText());

            if (SafeCheck.validPackageName(packageName.getText()) && SafeCheck.validInterfacesAndClasses(names)) {
                //disable fields ? ?
                MyToolWindow.designFactory.getDesign("Visitor", packageName.getText());
                //enable fields
                //clear fields
            }
        });
    }
}
