package finalproject.mobilecomputing.bidbox.models;

/**
 * Created by z on 5/3/18.
 */

//book class that makes book objects base on data from the server
public class Book {

    private String id, name, version, ownerID, condition, isbn;

    //default Constructor
    public Book(){
        this.id="000";
        this.name="TestBook";
        this.version="v9";
        this.ownerID="sss1";
        this.condition="New";
        this.isbn="000000";
    }

    //initialize contructor
    public Book(String...strArr){
        this.id=strArr[0];
        this.name=strArr[1];
        this.version=strArr[2];
        this.ownerID=strArr[3];
        this.condition=strArr[4];
        this.isbn=strArr[5];
    }

    //initialize contructor
    public Book(String id, String name, String version, String ownderID, String condition, String isbn){
        this.id=id;
        this.name=name;
        this.version=version;
        this.ownerID=ownderID;
        this.condition=condition;
        this.isbn=isbn;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", ownerID='" + ownerID + '\'' +
                ", condition='" + condition + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
