package tool.SoftwareGenerator.DesignGUIs;

import tool.MyToolWindow;
import tool.SoftwareGenerator.SafeCheck;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AbstractFactoryGUI {
    // GUI
    public static JPanel panel = new JPanel();
    public static JTextField packageName = new JTextField();
    public static JTextField abstractFactory_name = new JTextField();
    private static JTextField new_field = new JTextField();
    private static JTextField new_method = new JTextField();
    private static JButton add_field = new JButton("Add field");
    private static JButton add_method = new JButton("Add method");
    private static JButton generate = new JButton("Generate");

    // For the creation of compilation unit
    public static ArrayList<String> fields = new ArrayList<>();
    public static ArrayList<String> methods = new ArrayList<>();



    static{
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        abstractFactory_name.setMaximumSize(new Dimension(GUIconstrants.width, GUIconstrants.height));
        new_field.setMaximumSize(new Dimension(GUIconstrants.width,  GUIconstrants.height));
        new_method.setMaximumSize(new Dimension(GUIconstrants.width,  GUIconstrants.height));
        packageName.setMaximumSize(new Dimension(GUIconstrants.width,  GUIconstrants.height));


        panel.add(new JLabel("\nName of package:*\n")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(packageName);


        panel.add(new JLabel("\nName of the abstract factory interface:*\n")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(abstractFactory_name);


        //Adding Fields
        panel.add(new JLabel("\nAdd fields to the abstract factory interface:")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(new JLabel("\nClassType FieldName")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(new_field);
        panel.add(add_field);
        add_field.addActionListener(e -> {
            String field = new_field.getText();
            if (SafeCheck.validField(field)){
                new_field.setText("");
                fields.add(field);
            }
        });


        //Adding Methods
        panel.add(new JLabel("\nAdd methods to the abstract factory interface:")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(new JLabel("\nReturnType MethodName(Type1 name1, ...)\n")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(new_method);
        panel.add(add_method);
        add_method.addActionListener(e -> {
            String method = new_method.getText();
            if (SafeCheck.validMethod(method)){
                new_method.setText("");
                methods.add(method);
            }
        });


        //Generate button
        panel.add(generate);
        generate.addActionListener(e -> {
            ArrayList<String> names = new ArrayList<>();
            names.add(abstractFactory_name.getText());
            if (SafeCheck.validPackageName(packageName.getText()) && SafeCheck.validInterfacesAndClasses(names)){
                MyToolWindow.designFactory.getDesign("Abstract Factory", packageName.getText());
            }
        });
    }
}
