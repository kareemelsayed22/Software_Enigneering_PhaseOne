import java.util.List;

public class main {

    //Java main Function
    public static void main(String[] args){
        if (args.length == 3){
            String inPutFilename  = args[0];    //Argument 1
            String outPutFilenme = args[1];     //Argument 2
            String directoryPath = args[2];     //Argument 3
            //Creating Tokens
            new Tokenizer().Tokenize(directoryPath + inPutFilename, new Tokenizer.TokensCallback() {
                @Override
                public void onResult(boolean isFoundFile, List<ErrorModel> errors, List<Datamodel> datamodels) {
                    //Checking is file found
                    if (isFoundFile){
                        //Checking if errors
                        if (errors.size() > 0){
                            System.out.println("There are errors in Instructions:");
                            for (ErrorModel m : errors){
                                System.out.println("Error at line "+m.line+" "+m.type);
                            }
                        }else{
                            System.out.println("Compile Successful");
                            for (Datamodel datamodel : datamodels){
                                switch (datamodel.operation){
                                    case INSERT:
                                        new Interpertr().Insert(datamodel,outPutFilenme);
                                        break;
                                    case SEARCH:
                                        new Interpertr().Search(datamodel,outPutFilenme);
                                        break;
                                    case DELETE:
                                        new Interpertr().Delete(datamodel,outPutFilenme);
                                        break;
                                    case MODIFY:
                                        new Interpertr().Modify(datamodel,outPutFilenme);
                                        break;
                                    case PRINT:
                                        new Interpertr().Print(datamodel,outPutFilenme);
                                        break;
                                }
                            }
                        }
                    }

                }
            });
        }else{
            System.out.println("Invalid arguments");
        }
    }
}
