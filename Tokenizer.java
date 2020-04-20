import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    //Pattern
    // INSERT | ID | TITLE | NAME | PRICE | IMAGE_URL
    // MODIFY | ID | TITLE | NAME | PRICE | IMAGE_URL
    // PRINT | ID
    // DELETE | ID
    // SEARCH | ID

    //Init Tokens callback abstract class
    TokensCallback callback;
    //Creating tokenize function
    public void Tokenize(String filename,TokensCallback tokensCallback){
        callback = tokensCallback;
        boolean found = false;
        if (new File(filename).exists()){found = true;}else{
            System.out.println("File n't found");
        }
        List<ErrorModel> errors = new ArrayList<>();
        List<Datamodel> tokens = new ArrayList<>();
        if (found){
            try{
                BufferedReader reader = new BufferedReader(new FileReader(filename));
                String line = null;
                int line_i = 0;
                while ((line = reader.readLine())!= null){
                    String [] split = line.trim().split("\\|");
                    try{
                        String Operation = split[0].trim();
                        switch (Operation.trim()){
                            case "INSERT":
                               try{
                                   String ID = split[1];
                                   String Title = split[2];
                                   String Name = split[3];
                                   String Price = split[4];
                                   String ImageUrl = split[5];
                                   if (ID.isEmpty() || ID.equals(" ")){ID = "-1";}
                                   if (Title.isEmpty() || Title.equals(" ")){Title = "-1";}
                                   if (Name.isEmpty() || Name.equals(" ")){Name = "-1";}
                                   if (Price.isEmpty() || Price.equals(" ")){Price = "-1";}
                                   if (ImageUrl.isEmpty() || ImageUrl.equals(" ")){ImageUrl = "-1";}
                                   tokens.add(new Datamodel(Datamodel.OPERATION.INSERT,Title,Name,Price,ImageUrl,ID));
                               }catch (Exception e){
                                   errors.add(new ErrorModel(line_i+1, ErrorModel.TYPE.INVALID_COMMAND));
                               }

                                break;
                            case "PRINT":
                                tokens.add(new Datamodel(Datamodel.OPERATION.PRINT,split[1]));
                                break;
                            case "MODIFY":
                                tokens.add(new Datamodel(Datamodel.OPERATION.MODIFY,split[2],split[3],split[4],split[5],split[1]));
                                break;
                            case "SEARCH":
                                tokens.add(new Datamodel(Datamodel.OPERATION.SEARCH,split[1]));
                                break;
                            case "DELETE":
                                tokens.add(new Datamodel(Datamodel.OPERATION.DELETE,split[1]));
                                break;
                            default:
                                errors.add(new ErrorModel(line_i+1, ErrorModel.TYPE.SYNTAX));
                                break;

                        }
                    }catch (Exception e){
                        e.printStackTrace();

                    }
                    line_i ++;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        callback.onResult(found,errors,tokens);
    }
    //Creating abstract class TokenCallback
    abstract public static class TokensCallback{
        abstract public void onResult(boolean isFoundFile,List<ErrorModel> errors,List<Datamodel> datamodels);
    }
}
