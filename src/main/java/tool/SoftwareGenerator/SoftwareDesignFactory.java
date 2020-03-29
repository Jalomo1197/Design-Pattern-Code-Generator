package tool.SoftwareGenerator;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import org.slf4j.Logger;
import tool.SoftwareGenerator.Designs.*;
import tool.MyToolWindow;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SoftwareDesignFactory extends AbstractSoftwareDesignFactory<CompilationUnit> {
    private Logger logger = Log.logger;
    private String sourceDirectory;
    VirtualFile source;

    public SoftwareDesignFactory(){
        VirtualFile[] vFiles = ProjectRootManager.getInstance(MyToolWindow.project).getContentSourceRoots();
        for (VirtualFile v : vFiles){
            if (v.toString().contains("main/java")){
                source = v;
                break;
            }
        }
        if(source == null)
            sourceDirectory = MyToolWindow.project.getBasePath();
        else
            sourceDirectory = source.toString().substring(7) + "/";

        System.out.println("Targeted directory: "+ sourceDirectory);
    }

    @Override
    public CompilationUnit getDesign(String design, String packageName) {
        DesignPattern designPattern;
        switch(design){
            case "Abstract Factory":
                logger.trace("Message: Creating Abstract Factory Design.");
                designPattern = new AbstractFactory();
                break;
            case "Builder":
                logger.trace("Message: Creating Builder Design.");
                designPattern = new Builder();
                break;
            case "Factory":
                logger.trace("Message: Creating Factory Design.");
                designPattern = new Factory();
                break;
            case "Facade":
                logger.trace("Message: Creating Facade Design.");
                designPattern = new Facade();
                break;
            case "Chain":
                logger.trace("Message: Creating Chain Design.");
                designPattern = new Chain();
                break;
            case "Visitor":
                logger.trace("Message: Creating Visitor Design.");
                designPattern = new Visitor();
                break;
            case "Template":
                logger.trace("Message: Creating Template Design.");
                designPattern = new Template();
                break;
            case "Mediator":
                logger.trace("Message: Creating Mediator Design.");
                designPattern = new Mediator();
                break;
            default:
                return null;
        }


        String newPackage = sourceDirectory + packageName + "/";
        CompilationUnit designRequested = designPattern.designPattern();
        ClassOrInterfaceDeclaration c = new ClassOrInterfaceDeclaration();
        new File(newPackage).mkdir(); //making package
        for (ClassOrInterfaceDeclaration CorI : designRequested.findAll(c.getClass())){
            String newJavaFile = CorI.getNameAsString() + ".java";
            if (newJavaFile.contains("<T>"))
                newJavaFile = newJavaFile.substring(0, newJavaFile.length() - 3);
            System.out.println("New Directory: " + newPackage + newJavaFile);
            File a = new File(newPackage + newJavaFile);
            System.out.println("Absolute Path: " + a.getAbsolutePath());
            try {
                a.setWritable(true);
                if (a.createNewFile()) {
                    System.out.println("Absolute Path: " + a.getAbsolutePath());
                    FileWriter fos = new FileWriter(a);
                    fos.write("package " + packageName + ";\n\n");
                    fos.write(CorI.toString());
                    fos.close();
                }
            }
            catch (IOException e){} 
        }


        return designPattern.designPattern();
    }
}

