package tool.SoftwareGenerator.DesignGUIs;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.VerticalFlowLayout;
import com.intellij.ui.components.panels.VerticalLayout;
import javafx.scene.layout.HBox;
import tool.MyToolWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BuilderGUI {
    public static JPanel panel = new JPanel();
    public static JTextField builder_name = new JTextField();
    public static JTextField planner_name = new JTextField();
    public static JTextField object_name = new JTextField();
    public static JTextField director_name = new JTextField();

    //For modification for design
    private static JTextField new_field = new JTextField();
    private static JTextField new_method = new JTextField();
    private static JButton add_field = new JButton("Add field");
    private static JButton add_method = new JButton("Add method");
    private static JButton generate = new JButton("Generate");

    //For creation of compilation unit
    public static ArrayList<String> Object_fields = new ArrayList<>();
    public static ArrayList<String> Builder_methods = new ArrayList<>();
    public static ArrayList<String> Planner_methods = new ArrayList<>();


    //list of classes and interfaces
    private static String[] classesAndInterfaces = new String[] {"builder interface", "plan interface"};
    private static ComboBox<String> EnhanceList = new ComboBox<>(classesAndInterfaces);
    private static ActionListener[] action = new ActionListener[2];
    private static  ActionListener currentActionListener;


    static{
        // ************ Setting sizes and layouts ***************
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        builder_name.setMaximumSize(new Dimension(600, 200));
        planner_name.setMaximumSize(new Dimension(600, 200));
        object_name.setMaximumSize(new Dimension(600, 200));
        director_name.setMaximumSize(new Dimension(600, 200));
        new_field.setMaximumSize(new Dimension(600, 200));
        new_method.setMaximumSize(new Dimension(600, 200));



        // ************ Getting names of related Classes/Interfaces ***************
        panel.add(new JLabel("Name of the builder interface:")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(builder_name);


        panel.add(new JLabel("Name of the plan interface:")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(planner_name);

        panel.add(new JLabel("Name of the class that builders")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(new JLabel("instantiate/build:")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(object_name);

        panel.add(new JLabel("Name of the director class:")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(director_name);

        //*************************************************************************
        //************* Adding Methods To Different Classes/Interfaces ************
        panel.add(new JLabel("\nAdd methods to")).setFont(new Font("Courier New", Font.ITALIC, 14));
        panel.add(EnhanceList);


        action[0] = e -> {
                            String method = new_method.getText();
                            new_method.setText("");
                            if (method.equals("")){
                                System.out.println("invalid entry of field");
                            }
                            else{
                                //validate with split??
                                Builder_methods.add(method);
                            }
                        };

        action[1] = e -> {
                            String method = new_method.getText();
                            new_method.setText("");
                            if (method.equals("")){
                                System.out.println("invalid entry of field");
                            }
                            else{
                                //validate with split??
                                Planner_methods.add(method);
                            }
                        };

        currentActionListener = action[0];

        EnhanceList.addActionListener(e -> {
                                                switch (EnhanceList.getSelectedItem().toString()){
                                                    case "builder interface":
                                                        add_method.removeActionListener(currentActionListener);
                                                        add_method.addActionListener((currentActionListener = action[0]));
                                                        break;
                                                    case "plan interface":
                                                        add_method.removeActionListener(currentActionListener);
                                                        add_method.addActionListener((currentActionListener = action[1]));
                                                        break;
                                                    default:
                                                }
                                            });

        panel.add(new JLabel("\nReturnType MethodName(Type1 name1, ...)\n")).setFont(new Font("Courier New", Font.ITALIC, 14));;
        panel.add(new_method);
        panel.add(add_method);
        add_method.addActionListener(currentActionListener);

        //*************************************************************************
        //******************   Adding Fields to Object   **************************
        //Adding Feilds
        panel.add(new JLabel("\nAdd fields to the object that builders instantiate/build:")).setFont(new Font("Courier New", Font.ITALIC, 14));
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
                Object_fields.add(field);
            }
        });

        //*************************************************************************
        //**************************   Generate button   **************************
        panel.add(generate);
        generate.addActionListener(e -> {
            //TODO: validate input before calling factory
            MyToolWindow.designFactory.getDesign("Builder");
        });
        //*************************************************************************
    }
}
