package tool.SoftwareGenerator;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class EnhanceDesign {
    static Logger logger = Log.logger;

    public static boolean CustomFields(Scanner scan, String to){
        System.out.println("Do you want to add members to "+ to +" (y/n)?    ");
        scan = new Scanner(System.in);
        String fields = scan.nextLine();
        if (fields.equals("y")){
            return true;
        }
        else if (fields.equals("n")){
            return false;
        }
        else{
            System.out.println("Please enter valid answer y/n");
            return CustomFields(scan, to);
        }
    }




    //USED IN THIS PROJECT
    public static void addCustomFields(ClassOrInterfaceDeclaration Class, ArrayList<String> fields){
        for(String field: fields){
            String[] typeAndName = field.split(" ");
            Class.addField(typeAndName[0], typeAndName[1], Modifier.Keyword.PUBLIC);
        }
    }


    //USED IN THIS PROJECT
    public static List<MethodDeclaration> addCustomMethods(ClassOrInterfaceDeclaration Class, ArrayList<String> methods, boolean removeBody){
        List<MethodDeclaration> methodsList = new ArrayList<>();
        for(String method: methods){
            String[] methodAndParameters = method.split("[ ,()]", 0);
            int size = methodAndParameters.length;
            MethodDeclaration Method = new MethodDeclaration();
            for(int i = 0; i < size; i += 2){
                if(i == 0){
                    Method = Class.addMethod(methodAndParameters[1], Modifier.Keyword.PUBLIC).setType(methodAndParameters[0]);
                    if (removeBody)
                        Method.removeBody();
                }
                else
                    Method.addParameter(methodAndParameters[i], methodAndParameters[i + 1]);
            }
            methodsList.add(Method);
        }
        return methodsList;
    }




    public static void addCustomFields(ClassOrInterfaceDeclaration newClass, Scanner scan, boolean prompt){
        if (prompt){
            System.out.println( "Enter members"+
                                "\nExample:" +
                                "\n      Member:" +
                                "\n      Type NameOfVar     " +
                                "\nEnter 'done' when finished:");
        }
        Scanner var;
        int count = 1;
        while (true){
            try {
                System.out.println("Member "+ count++ + "(Type NameOfVariable):");
                var = new Scanner(scan.nextLine());
                String type = var.next();
                if (type.equals("done"))
                    break;
                newClass.addPublicField(type, var.next());

            }
            catch(Exception e){
                System.out.println("Please enter valid type and name");
                count--;
            }
        }
    }

    public static boolean CustomMethods(Scanner scan){
        System.out.println("Do you want to add your own methods (y/n)?    ");
        String fields = scan.nextLine();
        if (fields.equals("y")){
            return true;
        }
        else if (fields.equals("n")){
            return false;
        }
        else{
            System.out.println("Please enter valid answer y/n");
            return CustomMethods(scan);
        }
    }

    public static List<MethodDeclaration> addCustomMethods(ClassOrInterfaceDeclaration newClass, Scanner scan, String NOTES, boolean prompt){
        List<MethodDeclaration> addedMethods = new ArrayList<MethodDeclaration>();
        System.out.println(NOTES);
        if (prompt){
            System.out.println( "Please enter methods like so");
            System.out.println( "Example:\n" +
                                "       ReturnType Name:\n" +
                                "       int getValue \n" +
                                "       Parameters (Type Name):             \n" +
                                "       int parameter         <-- Enter more parameters. 'next' for another \n" +
                                "                                 method or 'done' when finished");
        }

        Scanner var;
        int count = 0;
        int count2 = 0;
        while (true){
            System.out.println("\nMethod " + ++count + "\nReturnType Name:");
            MethodDeclaration newMethod;
            String type;
            String name;

            try {
                var = new Scanner(scan.nextLine());
                type = var.next();
                if (type.equals("done")) break;
                name = var.next();
            }
            catch(Exception e){
                System.out.println("Please enter valid input: ReturnType Name");
                count--;
                continue;
            }
            newMethod = newClass.addMethod(name, Modifier.Keyword.PUBLIC);
            newMethod.setType(type);
            addedMethods.add(newMethod);


            while(true){
                try{
                    System.out.println("Parameter " + ++count2 +" (Type Name):");
                    var = new Scanner(scan.nextLine());
                    type = var.next();
                    if (type.equals("next") || type.equals("done")) break;
                    name = var.next();
                }
                catch(Exception e){
                    System.out.println("Please enter valid parameter: Type Name");
                    count2--;
                    continue;
                }
                newMethod.addParameter(type, name);
            }
            if(type.equals("done")) break;
            count2 = 0;
        }
        return addedMethods;
    }


    public static boolean ConcreteInterfaces(String Interface, Scanner scan){
        System.out.println("Do you want to create classes that implement the interface " + Interface + " (y/n)?");
        String classes = scan.nextLine();
        if (classes.equals("y")){
            return true;
        }
        else if (classes.equals("n")){
            return false;
        }
        else{
            System.out.println("Please enter valid answer y/n");
            return ConcreteInterfaces(Interface, scan);
        }
    }


    public static List<ClassOrInterfaceDeclaration> addConcreteInterfaces(CompilationUnit unit, ClassOrInterfaceDeclaration Interface, List<MethodDeclaration> InterfaceMethods, Scanner scan){
        List<ClassOrInterfaceDeclaration> Classes = new ArrayList<ClassOrInterfaceDeclaration>();
        String interfaceName = Interface.getNameAsString();
        System.out.println("Enter the names of the class that will implement "+ interfaceName +
                            "\nEnter 'done' when finished");
        String Class = "";
        int count = 1;
        while(true){
            System.out.println(count++ + " class that will implement "+ interfaceName +": ");
            Class = scan.nextLine();
            if (Class.equals("done")) break;

            ClassOrInterfaceDeclaration newClass = unit.addClass(Class);
            newClass.addImplementedType(interfaceName);
            if(CustomFields(scan, newClass.getNameAsString()))
                addCustomFields(newClass, scan, false);
            for (MethodDeclaration m: InterfaceMethods)
                newClass.addMethod(m.getNameAsString()).setParameters(m.getParameters());
        }
        return Classes;
    }


    public static boolean extendAbstract(String Abstract, Scanner scan){
        System.out.println("Do you want to create classes that extend the abstract class " + Abstract + " (y/n)?");
        String classes = scan.nextLine();
        if (classes.equals("y")){
            return true;
        }
        else if (classes.equals("n")){
            return false;
        }
        else{
            System.out.println("Please enter valid answer y/n");
            return extendAbstract(Abstract, scan);
        }
    }


    public static List<ClassOrInterfaceDeclaration> addExtends(CompilationUnit unit, ClassOrInterfaceDeclaration AbstractClass, List<MethodDeclaration> AbstractMethods, Scanner scan){
        List<ClassOrInterfaceDeclaration> Classes = new ArrayList<ClassOrInterfaceDeclaration>();
        String abstractName = AbstractClass.getNameAsString();
        System.out.println("Enter the names of the classes that will extend "+ abstractName +
                "\nEnter 'done' when finished");
        String Class = "";
        int count = 1;
        while(true){
            System.out.println(count++ + " class that will extend "+ abstractName +": ");
            Class = scan.nextLine();
            if (Class.equals("done")) break;

            ClassOrInterfaceDeclaration newClass = unit.addClass(Class);
            newClass.addExtendedType(abstractName);
            if(CustomFields(scan, newClass.getNameAsString()))
                addCustomFields(newClass, scan, false);
            for (MethodDeclaration m: AbstractMethods)
                newClass.addMethod(m.getNameAsString()).setParameters(m.getParameters());
        }
        return Classes;
    }

}
