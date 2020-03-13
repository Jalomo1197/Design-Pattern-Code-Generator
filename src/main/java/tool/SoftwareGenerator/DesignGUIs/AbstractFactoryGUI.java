package tool.SoftwareGenerator.DesignGUIs;

import tool.MyToolWindow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AbstractFactoryGUI {
    // GUI
    public static JPanel panel = new JPanel();
    public static JTextField abstractFactory_name = new JTextField();
    private static JTextField new_field = new JTextField();
    private static JTextField new_method = new JTextField();
    private static JButton add_field = new JButton("Add field");
    private static JButton add_method = new JButton("Add method");
    private static JButton generate = new JButton("Generate");

    //For creation of compilation unit
    public static ArrayList<String> fields = new ArrayList<>();
    public static ArrayList<String> methods = new ArrayList<>();


    static{
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        abstractFactory_name.setMaximumSize(new Dimension(600, 200));
        new_field.setMaximumSize(new Dimension(600, 200));
        new_method.setMaximumSize(new Dimension(600, 200));



        panel.add(new JLabel("\nName of the abstract factory interface:*\n")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(abstractFactory_name);



        //Adding Feilds
        panel.add(new JLabel("\nAdd fields to the abstract factory interface:")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(new JLabel("\nClassType FieldName")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(new_field);
        panel.add(add_field);
        add_field.addActionListener(e -> {
            String field = new_field.getText();
            new_field.setText("");

            if (field.equals("")){
                System.out.println("invalid entry of field");
            }
            else{
                //validate??
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
            new_method.setText("");
            if (method.equals("")){
                System.out.println("invalid entry of field");
            }
            else{
                //validate with split??
                methods.add(method);
            }
        });


        //Generate button
        panel.add(generate);
        generate.addActionListener(e -> {
            //validate first!!!
            MyToolWindow.designFactory.getDesign("Abstract Factory");
        });
    }
}
