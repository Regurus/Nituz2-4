package Model;

public class ActionLogger implements Logger{
    private static ActionLogger ourInstance = new ActionLogger();

    public static ActionLogger getActionLoggerInstance() {
        return ourInstance;
    }

    private ActionLogger() {
    }
}
