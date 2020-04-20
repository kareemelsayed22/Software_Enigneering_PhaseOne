import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Interpertr {
    String filename = "custom.database";
    //Creating Constructor
    public Interpertr(){
        if (!new File(filename).exists()){
            try {
                new File(filename).createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // Inserting Data to database
    public void Insert(Datamodel datamodels,String OutputFilename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = null;
            int found = 0;
            if (new File(filename).length()> 0){
                while ((line = reader.readLine()) != null) {
                        //System.out.println(line);
                        String ID = line;
                        String Title = reader.readLine();
                        String Name = reader.readLine();
                        String price = reader.readLine();
                        String ImageURL = reader.readLine();
                        if (ID.trim().equals(datamodels.getID().trim())) {
                            new Logs().WriteLog("INSERT ID already exist");
                            found = 1;
                            break;
                        }
                    }
                if (found == 0){
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
                        writer.write(datamodels.getID());
                        writer.newLine();
                        writer.write(datamodels.getTitle());
                        writer.newLine();
                        writer.write(datamodels.getName());
                        writer.newLine();
                        writer.write(datamodels.getPrice());
                        writer.newLine();
                        writer.write(datamodels.getUrl());
                        writer.newLine();
                        writer.close();
                        new Output().writeToOutputFile(OutputFilename, datamodels);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                }
                else{
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
                        writer.write(datamodels.getID());
                        writer.newLine();
                        writer.write(datamodels.getTitle());
                        writer.newLine();
                        writer.write(datamodels.getName());
                        writer.newLine();
                        writer.write(datamodels.getPrice());
                        writer.newLine();
                        writer.write(datamodels.getUrl());
                        writer.newLine();
                        writer.close();
                        new Output().writeToOutputFile(OutputFilename,datamodels);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // Modify data into database
    public void Modify(Datamodel datamodels,String outputFilename){
        List<Datamodel> datamodels1 = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = null;
            while((line = reader.readLine())!= null){
                String ID = line;
                String Title = reader.readLine();
                String Name = reader.readLine();
                String price = reader.readLine();
                String ImageURL = reader.readLine();

                if (ID.trim().equals(datamodels.getID().trim())){
                    datamodels1.add(new Datamodel(Datamodel.OPERATION.MODIFY,datamodels.getTitle(),datamodels.getName(),datamodels.getPrice(),datamodels.getUrl(),datamodels.getID()));
                }else{
                    datamodels1.add(new Datamodel(Datamodel.OPERATION.MODIFY,Title,Name,price,ImageURL,ID));
                }
            }
            reader.close();
            DeleteAndRenewFile(datamodels1);
            new Output().writeToOutputFile(outputFilename,datamodels);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // Deleting Data into database
    public void Delete(Datamodel datamodels,String outputFilename){
        List<Datamodel> datamodels1 = new ArrayList<>();
        Datamodel datamodel = null;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = null;
            while((line = reader.readLine())!= null){
                String ID = line;
                String Title = reader.readLine();
                String Name = reader.readLine();
                String price = reader.readLine();
                String ImageURL = reader.readLine();

                if (ID.trim().equals(datamodels.getID().trim())){
                    datamodel = new Datamodel(Datamodel.OPERATION.DELETE,Title,Name,price,ImageURL,ID);
                    continue;
                }datamodels1.add(new Datamodel(Datamodel.OPERATION.DELETE,Title,Name,price,ImageURL,ID));
            }
            reader.close();
            DeleteAndRenewFile(datamodels1);
            if (datamodel != null){
                new Output().writeToOutputFile(outputFilename,datamodel);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    // Delete and renewing databse file
    private void DeleteAndRenewFile(List<Datamodel> datamodels1){
        try{
            File f = new File(filename);
            f.delete();
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
            for (int i  = 0; i < datamodels1.size(); i++){
                writer.write(datamodels1.get(i).getID());
                writer.newLine();
                writer.write(datamodels1.get(i).getTitle());
                writer.newLine();
                writer.write(datamodels1.get(i).getName());
                writer.newLine();
                writer.write(datamodels1.get(i).getPrice());
                writer.newLine();
                writer.write(datamodels1.get(i).getUrl());
                writer.newLine();
            }
            writer.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //Searching data into database
    public void Search(Datamodel datamodels,String outputFilename){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = null;
            while((line = reader.readLine())!= null){
                String ID = line;
                String Title = reader.readLine();
                String Name = reader.readLine();
                String price = reader.readLine();
                String ImageURL = reader.readLine();

                if (ID.trim().equals(datamodels.getID().trim())){
                    Datamodel datamodel = new Datamodel(Datamodel.OPERATION.SEARCH,Title,Name,price,ImageURL,ID);
                    new Output().writeToOutputFile(outputFilename,datamodel);
                }else{
                    //
                }
            }
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //Printing data from database
    public void Print(Datamodel datamodels,String outputFilename){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = null;
            while((line = reader.readLine())!= null){
                String ID = line;
                String Title = reader.readLine();
                String Name = reader.readLine();
                String price = reader.readLine();
                String ImageURL = reader.readLine();

                if (ID.trim().equals(datamodels.getID().trim())){
                    Datamodel datamodel = new Datamodel(Datamodel.OPERATION.PRINT,Title,Name,price,ImageURL,ID);
                    new Output().writeToOutputFile(outputFilename,datamodel);
                }else{
                    //
                }
            }
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
