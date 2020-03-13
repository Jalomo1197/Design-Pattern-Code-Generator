package tool.SoftwareGenerator.Designs;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import org.slf4j.Logger;
import tool.SoftwareGenerator.DesignGUIs.FacadeGUI;
import tool.SoftwareGenerator.DesignPattern;
import tool.SoftwareGenerator.EnhanceDesign;
import tool.SoftwareGenerator.Log;

import java.util.Scanner;

public class Facade implements DesignPattern {
    private CompilationUnit Facade;
    private String facadeName;
    private Logger logger = Log.logger;

    @Override
    public CompilationUnit designPattern() {
        Facade = new CompilationUnit();
        CreateFacadeClass();
        CreateComplexSubClasses();
        return Facade;
    }

    //Creating Facade class
    private void CreateFacadeClass(){
        facadeName = FacadeGUI.facade_name.getText();
        ClassOrInterfaceDeclaration facadeClass = Facade.addClass(facadeName, Modifier.Keyword.PUBLIC);
        EnhanceDesign.addCustomFields(facadeClass, FacadeGUI.facade_fields);
        EnhanceDesign.addCustomMethods(facadeClass, FacadeGUI.facade_methods, false);
        logger.trace("Message: Built Facade class --> Class: " + facadeName);
    }

    //Creating the classes the the facade will use
    private void CreateComplexSubClasses(){
        for (String subSystem : FacadeGUI.subSystem_classes){
            Facade.addClass(subSystem);
        }
        logger.trace("Message: Built class that will be used inside the facade");
    }
}
