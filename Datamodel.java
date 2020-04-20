public class Datamodel {
    //Creating enum for operations
    public enum OPERATION{
        INSERT,
        PRINT,
        MODIFY,
        DELETE,
        SEARCH
    }
    OPERATION operation;
    String title,name,price,url,id;
    //Creating constructor for Printing & deleting & searching because these all have only one param
    public Datamodel(OPERATION operation, String id){        //FOR printing , Delete , Search
        this.id = id;
        this.operation = operation;
    }
    //Getting Operation value
    public OPERATION getOperation() {
        return operation;
    }
    //Setting Operation value
    public void setOperation(OPERATION operation) {
        this.operation = operation;
    }
    //Creating Global constructor
    public Datamodel(OPERATION operation,String title, String name, String price, String url, String id) {
        this.title = title;
        this.name = name;
        this.price = price;
        this.url = url;
        this.id = id;
        this.operation = operation;
    }
    //Getting ID
    public String getID(){
        return  id;
    }
    //Setting ID
    public void setID(String id){
        this.id = id;
    }
    //Getting Title
    public String getTitle() {
        return title;
    }
    //Setting Title
    public void setTitle(String title) {
        this.title = title;
    }
    //Getting Name
    public String getName() {
        return name;
    }
    //Setting Name
    public void setName(String name) {
        this.name = name;
    }
    // Getting Price
    public String getPrice() {
        return price;
    }
    //Setting Price
    public void setPrice(String price) {
        this.price = price;
    }
    //Getting Url
    public String getUrl() {
        return url;
    }
    //Setting Url
    public void setUrl(String url) {
        this.url = url;
    }
}
