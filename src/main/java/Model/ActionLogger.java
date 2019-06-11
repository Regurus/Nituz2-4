package Model;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class ActionLogger implements Logger{
    private String path;
    private static ActionLogger actionLogger = null;

    private ActionLogger()
    {
    }

    public static ActionLogger actionLoggerInstance(){
        if(actionLogger == null){
            actionLogger = new ActionLogger();
        }
        return actionLogger;
    }

    public void setPath(String path){
        this.path = path;
    }

    public void writeToLog(String data){
        String textToAppend = data;
        try{
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(path, true)  //Set true for append mode
            );
            writer.newLine();   //Add new line
            writer.write(textToAppend);
            writer.close();
        }catch (Exception e){
            System.out.println(e.getCause());
        }

    }
}
