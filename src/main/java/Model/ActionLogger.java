package Model;

public class ActionLogger implements Logger{
    private String path;
    private static ActionLogger actionLogger = null;
    private ActionLogger(String path)
    {
        if(actionLogger == null){
            this.path = path;
        }
    }

    public static ActionLogger actionLoggerInstance(){
        if(actionLogger == null){
            actionLogger = new ActionLogger("logger");
        }
        return actionLogger;
    }
}
