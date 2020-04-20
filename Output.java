import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Output {


    //Creating Constructor
    public Output(){

    }
    //Creating Write to file Function
    public void writeToOutputFile(String filename, Datamodel datamodelHashtable){
        if (!new File(filename).exists()){
            try {
                new File(filename).createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename,true));
            String nowTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
            writer.write(nowTime+" | "+
                    datamodelHashtable.getOperation().name()+" | "+
                    datamodelHashtable.getID()+" | "+
                    datamodelHashtable.getTitle()+" | "+
                    datamodelHashtable.getName()+" | "+
                    datamodelHashtable.getPrice()+" | "+
                    datamodelHashtable.getUrl());
            writer.newLine();
            writer.close();
            new Logs().WriteLog(datamodelHashtable.operation.name()+" done.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
