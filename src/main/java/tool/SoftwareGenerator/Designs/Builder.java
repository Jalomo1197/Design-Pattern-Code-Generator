package tool.SoftwareGenerator.Designs;
//JavaParser imports

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.slf4j.Logger;
import tool.SoftwareGenerator.DesignGUIs.BuilderGUI;
import tool.SoftwareGenerator.DesignPattern;
import tool.SoftwareGenerator.EnhanceDesign;
import tool.SoftwareGenerator.Log;

import java.util.List;
import java.util.Scanner;

//Logger imports


public class Builder implements DesignPattern {
    public static int number = 0;
    static final Logger logger = Log.logger;
    private List<MethodDeclaration> planMethods;
    private ClassOrInterfaceDeclaration Builder_interface;
    private ClassOrInterfaceDeclaration Plan_interface;
    private String objectName;
    private CompilationUnit Builder;

    @Override
    public CompilationUnit designPattern() {
        Builder = new CompilationUnit();
        CreateBuildInterface();
        CreatePlanInterface();
        CreatePlanObject();
        CreateDirectorClass();
        return Builder;
    }


    //Builder interface that all concrete builder implement
    private void CreateBuildInterface(){
        String interfaceBuildName = BuilderGUI.builder_name.getText();
        Builder_interface = Builder.addInterface(interfaceBuildName);
        logger.trace("Message: Built the builder interface with methods --> Interface: " + interfaceBuildName);
        EnhanceDesign.addCustomMethods(Builder_interface, BuilderGUI.Builder_methods, true);
    }


    //Creating Plan Interface. Objects being built by concrete builders will implement this.
    private void CreatePlanInterface(){
        String interfacePlanName = BuilderGUI.planner_name.getText();
        Plan_interface = Builder.addInterface(interfacePlanName);
        logger.trace("Message: Built the Plan Interface with methods --> Interface: " + interfacePlanName);
        planMethods = EnhanceDesign.addCustomMethods(Plan_interface, BuilderGUI.Planner_methods, true);
    }


    //Creating the base Object that builders will produce, note this class can be extend if user wants modification.
    private void CreatePlanObject(){
        objectName = BuilderGUI.object_name.getText();
        ClassOrInterfaceDeclaration object = Builder.addClass(objectName);
        object.addImplementedType(Plan_interface.getNameAsString());

        //Implementing methods
        logger.trace("Message: Built the class that implements " + Plan_interface.getNameAsString() + ", with methods --> Class:" + objectName);
        for(MethodDeclaration m: planMethods)
            object.addMethod(m.getNameAsString(), Modifier.Keyword.PUBLIC).setParameters(m.getParameters()).setType(m.getType());

        EnhanceDesign.addCustomFields(object, BuilderGUI.Object_fields);
    }

    //Creating Director class the do Builder Design Pattern, sequence of steps is left up to the user.
    private void CreateDirectorClass(){
        String classDirectorName = BuilderGUI.director_name.getText();
        ClassOrInterfaceDeclaration Director_class = Builder.addClass(classDirectorName);
        Director_class.addField(Builder_interface.getNameAsString(), "builder", Modifier.Keyword.PRIVATE);
        ConstructorDeclaration constructor = Director_class.addConstructor(Modifier.Keyword.PUBLIC);
        constructor.addParameter(Builder_interface.getNameAsString(), "builder");
        constructor.setBody(StaticJavaParser.parseBlock("{ this.builder = builder; }"));
        MethodDeclaration getInstance = Director_class.addMethod("get" + objectName, Modifier.Keyword.PUBLIC);
        getInstance.setType(objectName);
        String code = "{ return this.builder." + getInstance.getNameAsString() + "(); }";
        getInstance.setBody(StaticJavaParser.parseBlock(code));
        Director_class.addMethod("construct" + objectName, Modifier.Keyword.PUBLIC).setBody(StaticJavaParser.parseBlock("{ /* enter builder methods in deserved order */ }"));
        logger.trace("Message: Built the Director class part of the builder design pattern --> Class: " + classDirectorName);
    }
}
