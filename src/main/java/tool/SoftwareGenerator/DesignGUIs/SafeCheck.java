package tool.SoftwareGenerator.DesignGUIs;

public class SafeCheck {

    public static boolean FieldCheck(String field){
        String[] entries = field.split(" ", 0);
        return entries.length == 2;
    }

    public static boolean MethodCheck(String method){
        String[] entries = method.split("[ ,()]", 0);
        return entries.length % 2 == 0;
    }


}
