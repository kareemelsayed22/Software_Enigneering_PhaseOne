public class ErrorModel {
    int line;
    //Creating Error Enum Type
    public enum TYPE{
        SYNTAX,
        INVALID_COMMAND
    }
    TYPE type;
    //Creating Constructor
    public ErrorModel(int line,TYPE t){
        this.line = line;
        this.type = t;
    }
    //Getting line
    public int getLine() {
        return line;
    }
    //Setting Line
    public void setLine(int line) {
        this.line = line;
    }
    //Getting Type
    public TYPE getType() {
        return type;
    }
    //Setting Type
    public void setType(TYPE type) {
        this.type = type;
    }
}
