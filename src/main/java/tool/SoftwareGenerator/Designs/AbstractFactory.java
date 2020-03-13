package tool.SoftwareGenerator.Designs;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.slf4j.Logger;
import tool.SoftwareGenerator.DesignPattern;
import tool.SoftwareGenerator.EnhanceDesign;
import tool.SoftwareGenerator.Log;
import tool.SoftwareGenerator.DesignGUIs.AbstractFactoryGUI;


public class AbstractFactory implements DesignPattern {
    public static int number = 0;
    private static Logger logger = Log.logger;
    private CompilationUnit abstractFactory;


    @Override
    public CompilationUnit designPattern() {
        abstractFactory = new CompilationUnit();
        createAbstractFactory();
        return abstractFactory;
    }


    private void createAbstractFactory(){
        String interfaceName = AbstractFactoryGUI.abstractFactory_name.getText() + "<T>";
        ClassOrInterfaceDeclaration factory = abstractFactory.addInterface(interfaceName, Modifier.Keyword.PUBLIC);

        //logger.trace("Message: Adding class fields to Abstract Factory interface --> " + interfaceName);
        EnhanceDesign.addCustomFields(factory, AbstractFactoryGUI.fields);

        //Adding method T create(String type) to abstract factory, since all factories will have to produce an interface instant
        //logger.trace("Message: Adding base functions part of the Abstract Factory design pattern");
        MethodDeclaration create = factory.addMethod("create");
        create.setType("T");
        create.addParameter("String", "type");
        String Notes =  "NOTE: Use T as generic type, since abstract factory interface declared --> " + factory.getNameAsString() +
                        "\nNOTE: T create(String type) already included";

        //logger.trace("Message: Adding class methods to Abstract Factory interface --> " + interfaceName);
        EnhanceDesign.addCustomMethods(factory, AbstractFactoryGUI.methods, true);

        //TODO: add classes of abstract factory and specify 'T' ass generic type to user
    }
}
