package tool.SoftwareGenerator.Designs;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.slf4j.Logger;
import tool.SoftwareGenerator.DesignGUIs.ChainGUI;
import tool.SoftwareGenerator.DesignPattern;
import tool.SoftwareGenerator.Log;


public class Chain implements DesignPattern {
    private ClassOrInterfaceDeclaration baseHandler;
    private MethodDeclaration setNexHandler;
    private MethodDeclaration handle;
    private CompilationUnit Chain;
    private Logger logger = Log.logger;
    private String handlerName;

    @Override
    public CompilationUnit designPattern() {
        Chain = new CompilationUnit();
        CreateHandlerInterface();
        CreateBaseHandler();
        CreateHandlers();
        return Chain;
    }


    //Building the handler interface that all concrete handlers will implement
    private void CreateHandlerInterface(){
        handlerName = ChainGUI.handler_name.getText();
        ClassOrInterfaceDeclaration handlerInterface = Chain.addInterface(handlerName);
        setNexHandler = handlerInterface.addMethod("setNext");
        setNexHandler.addParameter(handlerName, "next");
        setNexHandler.removeBody();
        handle = handlerInterface.addMethod("handle");
        handle.removeBody();
        System.out.println("Please enter the Class-type of the object that all concrete handlers should handle");
        String classType = ChainGUI.object_name.getText();
        handle.addParameter(classType, "request");

        logger.trace("Message: Built the Handler Interface --> Interface: " + handlerName);
    }


    //Building the bad handler class that implements the handler interface, defined appropriate and needed functions
    //to connect the handler once they are created, the order of the the chain is left up to the user.
    private void CreateBaseHandler(){
        baseHandler = Chain.addClass("Base"+ handlerName);
        baseHandler.addField(handlerName, "next", Modifier.Keyword.PRIVATE);
        baseHandler.addImplementedType(handlerName);
        MethodDeclaration setNext = baseHandler.addMethod(setNexHandler.getNameAsString(), Modifier.Keyword.PUBLIC);
        setNext.setParameters(setNexHandler.getParameters());
        setNext.setBody(StaticJavaParser.parseBlock("{" +
                "   this.next = next;" +
                "}"));

        MethodDeclaration handleBase = baseHandler.addMethod("handle", Modifier.Keyword.PUBLIC);
        handleBase.setParameters(handle.getParameters());
        handleBase.setBody(StaticJavaParser.parseBlock("{" +
                "   if(next != null){" +
                "       next.handle(request);" +
                "   }" +
                "}"));

        logger.trace("Message: Built the Base Handler Class --> Class: " + "Base"+ handlerName);
    }


    //Building concrete handlers with code to allow handlers to either handle a request or
    //to modify/check request then pass it to the next handler in the chain.
    private void CreateHandlers(){
        ClassOrInterfaceDeclaration current;
        MethodDeclaration curMethod;

        for(String newHandler : ChainGUI.handlers){
            current = Chain.addClass(newHandler);
            current.addExtendedType(baseHandler.getNameAsString());
            curMethod = current.addMethod(handle.getNameAsString(), Modifier.Keyword.PUBLIC);
            curMethod.setParameters(handle.getParameters());
            curMethod.setBody(StaticJavaParser.parseBlock("{" +
                    "if(canHandle(request)){" +
                    "   /*  handle, then call next handler if needed    */" +
                    "}" +
                    "else{" +
                    "   super.handle(request);" +
                    "}" +
                    "}"));

            curMethod =  current.addMethod("canHandle", Modifier.Keyword.PRIVATE);
            curMethod.setParameters(handle.getParameters());
            curMethod.setType("boolean");
        }
        logger.trace("Message: Built the concrete Handlers");
    }

}
