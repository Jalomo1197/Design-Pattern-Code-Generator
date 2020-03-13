package tool.SoftwareGenerator.Designs;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.slf4j.Logger;
import tool.SoftwareGenerator.DesignGUIs.MediatorGUI;
import tool.SoftwareGenerator.DesignPattern;
import tool.SoftwareGenerator.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mediator implements DesignPattern {
    private CompilationUnit Mediator;
    private ClassOrInterfaceDeclaration mediatorClass;
    private Logger logger = Log.logger;

    @Override
    public CompilationUnit designPattern() {
        Mediator = new CompilationUnit();
        CreateMediatorClassAndComponents();
        return Mediator;
    }

    //creating the Mediator class along with the necessary function that the design must have.
    private void CreateMediatorClassAndComponents(){
        ClassOrInterfaceDeclaration mediatorInterface = Mediator.addInterface("Mediator");
        mediatorInterface.addMethod("notify").removeBody().addParameter("Component", "sender");
        Mediator.addInterface("Component").addMethod("setMediator").removeBody().addParameter("Mediator", "mediator");
        System.out.println("Please enter the name of the Mediator class:");
        String name = MediatorGUI.mediator_name.getText();
        mediatorClass = Mediator.addClass(name, Modifier.Keyword.PUBLIC).addImplementedType("Mediator");
        int num = 1;
        //adding classes
        for (String component: MediatorGUI.components){
            ClassOrInterfaceDeclaration c = Mediator.addClass(component);
            c.addImplementedType("Component");
            c.addField("Mediator", "mediator", Modifier.Keyword.PRIVATE);
            MethodDeclaration m = c.addMethod("setMediator").addParameter("Mediator", "mediator");
            m.setBody(StaticJavaParser.parseBlock("{   this.mediator = mediator;   }"));
            c.addMethod("Operation").setBody(StaticJavaParser.parseBlock("{   mediator.notify(this);   }"));
        }
        //adding fields to mediator
        for (String component: MediatorGUI.components){
            mediatorClass.addField(component, "component_" + component, Modifier.Keyword.PRIVATE);
        }

        MethodDeclaration n = mediatorClass.addMethod("notify").addParameter("Component", "sender");
        String NotifyBlock = "";
        for (String type: MediatorGUI.components){
            mediatorClass.addMethod("reactOn" + type).setBody(StaticJavaParser.parseBlock("{ /* implement */ }"));
            NotifyBlock += " if (sender.equals(component" + type + ")){ reactOn" + type + "(); }";
        }
        n.setBody(StaticJavaParser.parseBlock("{"+ NotifyBlock+"}"));

        logger.trace("Message: Built Mediator class and the components/classes that the class will have control over --> Class: " + name);
    }

}
