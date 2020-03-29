package tool.SoftwareGenerator;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class EnhanceDesign {
    static Logger logger = Log.logger;


    //USED IN THIS PROJECT
    public static void addCustomFields(ClassOrInterfaceDeclaration Class, ArrayList<String> fields){
        for(String field: fields){
            String[] typeAndName = field.split(" ");

            ArrayList<String> types_names = new ArrayList<>();
            for (String s: typeAndName){
                if (!s.equals(""))
                    types_names.add(s);
            }

            Class.addField(types_names.get(0), types_names.get(1), Modifier.Keyword.PUBLIC);
        }
    }


    //USED IN THIS PROJECT
    public static List<MethodDeclaration> addCustomMethods(ClassOrInterfaceDeclaration Class, ArrayList<String> methods, boolean removeBody){
        List<MethodDeclaration> methodsList = new ArrayList<>();
        for(String method: methods){
            String[] methodAndParameters = method.split("[ ,()]", 0);

            ArrayList<String> method_parameters = new ArrayList<>();
            for (String s: methodAndParameters){
                if (!s.equals(""))
                    method_parameters.add(s);
            }

            int size = methodAndParameters.length;
            MethodDeclaration Method = new MethodDeclaration();
            for(int i = 0; i < size; i += 2){
                if(i == 0){
                    Method = Class.addMethod(method_parameters.get(1), Modifier.Keyword.PUBLIC).setType(method_parameters.get(0));
                    if (removeBody)
                        Method.removeBody();
                }
                else
                    Method.addParameter(method_parameters.get(i), method_parameters.get(i + 1));
            }
            methodsList.add(Method);
        }
        return methodsList;
    }
}
