package tool.SoftwareGenerator.Designs;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.slf4j.Logger;
import tool.SoftwareGenerator.DesignGUIs.VisitorGUI;
import tool.SoftwareGenerator.DesignPattern;
import tool.SoftwareGenerator.EnhanceDesign;
import tool.SoftwareGenerator.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Visitor implements DesignPattern {
    private CompilationUnit Visitor;
    private ClassOrInterfaceDeclaration visitorInterface;
    private List<MethodDeclaration> visitorMethods = new ArrayList<>();
    private List<String> classes = new ArrayList<>();
    private Logger logger = Log.logger;
    private String visitorName;

    @Override
    public CompilationUnit designPattern() {
        Visitor = new CompilationUnit();
        CreateVisitorInterface();
        logger.trace("Message: Built the Visitor interface");
        CreateElementInterface();
        logger.trace("Message: Built the Element interface");
        CreateObjectsToBeVisited();
        logger.trace("Message: Built class/classes that implement the element interface");
        return Visitor;
    }

    //Building the visitor interface with necessary function for the design
    private void CreateVisitorInterface(){
        visitorName = VisitorGUI.visitor_name.getText();
        visitorInterface = Visitor.addInterface(visitorName);

        for (String visitClass : VisitorGUI.classes){
            MethodDeclaration curMethod = visitorInterface.addMethod("visit");
            curMethod.addParameter(visitClass, "element");
            curMethod.removeBody();
            visitorMethods.add(curMethod);
        }
    }

    //building the element interface with necessary function for the design
    private void CreateElementInterface(){
        ClassOrInterfaceDeclaration element = Visitor.addInterface("element");
        MethodDeclaration m = element.addMethod("accept");
        m.removeBody();
        m.addParameter(visitorName, "visitor");
    }


    //building class the will implement the interface element
    private void CreateObjectsToBeVisited(){
        for(String Class : VisitorGUI.classes){
            ClassOrInterfaceDeclaration current = Visitor.addClass(Class);
            current.addImplementedType("element");
            MethodDeclaration m = current.addMethod("accept");
            m.addParameter(visitorName, "visitor");
        }
    }
}
