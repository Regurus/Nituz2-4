import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class mainTest {

public static void main(String[] args){
    String textToAppend = "Happy Learning !!";
    try{
        BufferedWriter writer = new BufferedWriter(
                new FileWriter("C:\\Users\\Inbar\\Downloads\\test.txt", true)  //Set true for append mode
        );
        writer.newLine();   //Add new line
        writer.write(textToAppend);
        writer.close();
    }catch (Exception e){
        System.out.println(e.getCause());
    }

}

}
