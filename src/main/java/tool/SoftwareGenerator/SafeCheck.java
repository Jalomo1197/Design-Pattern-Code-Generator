package tool.SoftwareGenerator;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import tool.MyToolWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class SafeCheck {
    private static Pattern validName = Pattern.compile("^[a-zA-Z$_]+$");
    private static Pattern validField = Pattern.compile("^[a-zA-Z$_]+ [a-zA-Z$_]+$");
    private static Pattern validMethod = Pattern.compile("^[a-zA-Z$_\\[\\]]+[ ]+[a-zA-Z$_]+[ ]*[(][[ ]*[a-zA-Z$_\\[\\]]+[ ]+[a-zA-Z$_]+[, ]*]*[)]$");
    private static String sourceDirectory;
    private static VirtualFile source;

    static{
        source = MyToolWindow.designFactory.source;
        sourceDirectory = source.toString();
    }


    public static boolean validName(String name){
        boolean valid = validName.matcher(name).find();
        if (valid)
            MyToolWindow.clearError();
        else
            MyToolWindow.ErrorMessage("Invalid class name '"+ name +"'\n");

        return valid;
    }


    public static boolean validPackageName(String newPackage){
        //TODO: call when generate button is clicked

        HashMap<String, Integer> check = new HashMap<>();
        String exceptedDirectory = sourceDirectory + "/" + newPackage;

        VirtualFile[] children = source.getChildren();
        for (VirtualFile child: children)
            check.put(child.toString(), 1);


        if (!check.containsKey(exceptedDirectory)){
            if (validName.matcher(newPackage).find()){
                MyToolWindow.clearError();
                return true;
            }
            else{
                MyToolWindow.ErrorMessage("Invalid package name '"+ newPackage +"'.\n");
            }
        }else{
            MyToolWindow.ErrorMessage("Package '"+ newPackage +"' already exists.\n");
        }

        return false;
    }

    public static boolean validInterfacesAndClasses(ArrayList<String> names ){
        //TODO: call when generate button is clicked

        HashMap<String, Integer> check = new HashMap<>();
        for(String name : names){
            if (!validName.matcher(name).find() || check.containsKey(name) ){
                MyToolWindow.ErrorMessage(name + " declared more than once (Classes/Interfaces)\n");
                return false;
            }
            check.put(name, 1);
        }

        MyToolWindow.clearError();
        return true;
    }


    public static boolean validField(String field){
        boolean valid = validField.matcher(field).find();

        if (valid)
            MyToolWindow.clearError();
        else
            MyToolWindow.ErrorMessage("Invalid field declaration '"+ field +"'\n");

        return valid;
    }

    public static boolean validMethod(String method){
        boolean valid = validMethod.matcher(method).find();

        if (valid)
            MyToolWindow.clearError();
        else
            MyToolWindow.ErrorMessage("Invalid method declaration \n'"+ method +"'\n");

        return valid;
    }



}
