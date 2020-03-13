package tool.SoftwareGenerator.Designs;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.slf4j.Logger;
import tool.SoftwareGenerator.DesignGUIs.TemplateGUI;
import tool.SoftwareGenerator.DesignPattern;
import tool.SoftwareGenerator.EnhanceDesign;
import tool.SoftwareGenerator.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Template implements DesignPattern {
    private CompilationUnit Template;
    private ClassOrInterfaceDeclaration template;
    private Logger logger = Log.logger;
    private String templateName;


    @Override
    public CompilationUnit designPattern() {
        Template = new CompilationUnit();
        CreateAbstractClass();
        CreateClasses();
        return Template;
    }

    //Creating abstract template class with some optional and abstract methods for the steps in the algorithm.
    private void CreateAbstractClass(){
        templateName = TemplateGUI.template_name.getText();
        template = Template.addClass(templateName, Modifier.Keyword.ABSTRACT, Modifier.Keyword.PUBLIC);
        MethodDeclaration m = template.addMethod("templateMethod", Modifier.Keyword.PUBLIC, Modifier.Keyword.FINAL);
        m.setBody(StaticJavaParser.parseBlock("{" +
                "/*" +
                "Enter the sequence of algorithm steps (functions) and control flow" +
                "*/" +
                "}"));

        EnhanceDesign.addCustomMethods(template, TemplateGUI.methods, false);
        List<MethodDeclaration> abstractMethodsList = EnhanceDesign.addCustomMethods(template, TemplateGUI.methodsAbstract, true);
        for (MethodDeclaration meth : abstractMethodsList){
            meth.addModifier(Modifier.Keyword.ABSTRACT);
        }
        logger.trace("Message: Created abstract class for Template design pattern --> Class: " + templateName);
    }


    private void CreateClasses(){
        for (String newClass : TemplateGUI.classes){
            EnhanceDesign.addCustomMethods(
                    Template.addClass(newClass, Modifier.Keyword.PUBLIC).addExtendedType(templateName),
                    TemplateGUI.methodsAbstract, false);
        }
    }
}
