package tool.SoftwareGenerator;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import org.slf4j.Logger;
import tool.SoftwareGenerator.Designs.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class SoftwareDesignFactory extends AbstractSoftwareDesignFactory<CompilationUnit> {
    private Logger logger = Log.logger;
    private String projectBaseDir;
    private int designNumber = 1;

    public SoftwareDesignFactory(String baseDirectory){
        projectBaseDir = baseDirectory;
    }

    @Override
    public CompilationUnit getDesign(String design) {
        DesignPattern designPattern;
        String newPackage;
        switch(design){
            case "Abstract Factory":
                logger.trace("Message: Creating Abstract Factory Design.");
                newPackage = projectBaseDir + "/GeneratedCode/" + "AbstractFactory" + "Package_design" + designNumber++ + "/";
                designPattern = new AbstractFactory();
                break;
            case "Builder":
                logger.trace("Message: Creating Builder Design.");
                newPackage = projectBaseDir + "/GeneratedCode/" + "Builder" + "Package_design" + designNumber++ + "/";
                designPattern = new Builder();
                break;
            case "Factory":
                logger.trace("Message: Creating Factory Design.");
                newPackage = projectBaseDir + "/GeneratedCode/" + "Factory" + "Package_design" + designNumber++ + "/";
                designPattern = new Factory();
                break;
            case "Facade":
                logger.trace("Message: Creating Facade Design.");
                newPackage = projectBaseDir + "/GeneratedCode/" + "Facade" + "Package_design" + designNumber++ + "/";
                designPattern = new Facade();
                break;
            case "Chain":
                logger.trace("Message: Creating Chain Design.");
                newPackage = projectBaseDir + "/GeneratedCode/" + "Chain" + "Package_design" + designNumber++ + "/";
                designPattern = new Chain();
                break;
            case "Visitor":
                logger.trace("Message: Creating Visitor Design.");
                newPackage = projectBaseDir + "/GeneratedCode/" + "Visitor" + "Package_design" + designNumber++ + "/";
                designPattern = new Visitor();
                break;
            case "Template":
                logger.trace("Message: Creating Template Design.");
                newPackage = projectBaseDir + "/GeneratedCode/" + "Template" + "Package_design" + designNumber++ + "/";
                designPattern = new Template();
                break;
            case "Mediator":
                logger.trace("Message: Creating Mediator Design.");
                newPackage = projectBaseDir + "/GeneratedCode/" + "Mediator" + "Package_design" + designNumber++ + "/";
                designPattern = new Mediator();
                break;
            default:
                newPackage = projectBaseDir + "/GeneratedCode/" + "AbstractFactory" + "Package_design" + designNumber++ + "/";
                designPattern = new AbstractFactory();
        }

        CompilationUnit designRequested = designPattern.designPattern();
        ClassOrInterfaceDeclaration c = new ClassOrInterfaceDeclaration();
        new File(projectBaseDir + "/GeneratedCode/").mkdir();
        new File(newPackage).mkdir();
        for (ClassOrInterfaceDeclaration CorI : designRequested.findAll(c.getClass())){
            String newJavaFile = CorI.getNameAsString() + ".java";
            System.out.println("Project Base Directory: " + projectBaseDir);
            System.out.println("Creating File: " + newJavaFile);
            File a = new File(newPackage + CorI.getNameAsString() + ".java");
            System.out.println("Absolute Path: " + a.getAbsolutePath());
            try {
                a.setWritable(true);
                if (a.createNewFile()) {
                    System.out.println("Absolute Path: " + a.getAbsolutePath());
                    FileWriter fos = new FileWriter(a);
                    fos.write(CorI.toString());
                    fos.close();
                }
            }
            catch (IOException e){} 
        }


        return designPattern.designPattern();
    }
}

