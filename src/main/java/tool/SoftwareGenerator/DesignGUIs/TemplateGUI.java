package tool.SoftwareGenerator.DesignGUIs;

import tool.MyToolWindow;
import tool.SoftwareGenerator.SafeCheck;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TemplateGUI {
    public static JPanel panel = new JPanel();
    public static JTextField template_name = new JTextField();

    private static JTextField new_methodAbstract = new JTextField();
    private static JTextField new_method = new JTextField();
    private static JTextField new_class = new JTextField();
    private static JButton add_methodAbstract = new JButton("Add field");
    private static JButton add_method = new JButton("Add method");
    private static JButton add_class = new JButton("Add class");
    private static JButton generate = new JButton("Generate");

    //For creation of compilation unit
    public static ArrayList<String> classes = new ArrayList<>();
    public static ArrayList<String> methods = new ArrayList<>();
    public static ArrayList<String> methodsAbstract = new ArrayList<>();


    //package
    public static JTextField packageName = new JTextField();

    static{
        // ************ Setting sizes and layouts ***************
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        template_name.setMaximumSize(new Dimension(GUIconstrants.width,  GUIconstrants.height));
        new_methodAbstract.setMaximumSize(new Dimension(GUIconstrants.width,  GUIconstrants.height));
        new_method.setMaximumSize(new Dimension(GUIconstrants.width,  GUIconstrants.height));
        new_class.setMaximumSize(new Dimension(GUIconstrants.width,  GUIconstrants.height));
        packageName.setMaximumSize(new Dimension(GUIconstrants.width,  GUIconstrants.height));

        panel.add(new JLabel("\nName of package:*\n")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(packageName);

        panel.add(new JLabel("Name of the abstract template class:")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(template_name);


        //Adding Methods
        panel.add(new JLabel("\nAdd methods that the template will use:")).setFont(new Font("Courier New", Font.ITALIC, 14));
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


        //Adding Methods
        panel.add(new JLabel("\nAdd abstract methods that will need to be implemented:")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(new JLabel("\nReturnType MethodName(Type1 name1, ...)\n")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(new_methodAbstract);
        panel.add(add_methodAbstract);
        add_methodAbstract.addActionListener(e -> {
            String method = new_methodAbstract.getText();
            if (SafeCheck.validMethod(method)){
                new_methodAbstract.setText("");
                methodsAbstract.add(method);
            }
        });


        //Adding Classes
        panel.add(new JLabel("\nAdd classes that extend the template class:")).setFont(new Font("Courier New", Font.ITALIC, 14));
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
            names.add(template_name.getText());

            if (SafeCheck.validPackageName(packageName.getText()) && SafeCheck.validInterfacesAndClasses(names)) {
                //disable fields ? ?
                MyToolWindow.designFactory.getDesign("Template", packageName.getText());
                //enable fields
                //clear fields
            }
        });
    }
}
