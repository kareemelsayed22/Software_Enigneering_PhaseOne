import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logs {
    //Log filename
    String logFilename = "Transaction.log";
    //Creating Log constructor
    public Logs(){
        if (!new File(logFilename).exists()){
            try {
                new File(logFilename).createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //Writing writeLog function
    public void WriteLog(String print){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(logFilename,true));
            writer.write(print);
            writer.newLine();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
