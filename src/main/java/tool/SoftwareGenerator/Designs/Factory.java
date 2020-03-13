package tool.SoftwareGenerator.Designs;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.slf4j.Logger;
import tool.SoftwareGenerator.DesignGUIs.FactoryGUI;
import tool.SoftwareGenerator.DesignPattern;
import tool.SoftwareGenerator.EnhanceDesign;
import tool.SoftwareGenerator.Log;

import java.util.List;

public class Factory implements DesignPattern {
    public static int number = 0;
    private CompilationUnit Factory;
    private ClassOrInterfaceDeclaration commonInterface;
    private ClassOrInterfaceDeclaration factoryClass;
    private List<MethodDeclaration> factoryInterfaceMethods;
    private List<MethodDeclaration> commonInterfaceMethods;
    private String comInterfaceName;
    private String facInterfaceName;
    private Logger logger = Log.logger;


    @Override
    public CompilationUnit designPattern() {
        Factory = new CompilationUnit();
        CreateCommonInterface();
        CreateFactory();
        return Factory;
    }

    //common interface for the object being produce
    private void CreateCommonInterface(){
        comInterfaceName = FactoryGUI.common_name.getText();
        commonInterface = Factory.addInterface(comInterfaceName);
        EnhanceDesign.addCustomMethods(commonInterface, FactoryGUI.Common_methods, true);
        logger.trace("Message: Built Common Interface of the objects being produce by the factory --> Interface: " + comInterfaceName);
    }

    //Creating interface that factories will have to implement
    private void CreateFactory(){
        facInterfaceName = FactoryGUI.factory_name.getText();
        factoryClass = Factory.addClass(facInterfaceName);
        EnhanceDesign.addCustomFields(factoryClass, FactoryGUI.Factory_fields);
        factoryClass.addMethod("create" + comInterfaceName).setType(comInterfaceName);
        EnhanceDesign.addCustomMethods(factoryClass, FactoryGUI.Factory_methods, false);
        logger.trace("Message: Built Factory Class --> Class: " + facInterfaceName);
    }
}
