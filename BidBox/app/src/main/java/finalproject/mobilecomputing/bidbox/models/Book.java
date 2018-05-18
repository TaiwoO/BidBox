package finalproject.mobilecomputing.bidbox.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by z on 5/3/18.
 */

//book class that makes book objects base on data from the server
public class Book implements Serializable {

    @SerializedName("_id")
    private String id;
    private String name, version, ownerID, condition, isbn;
    private Double price;

    public Book() {

    }
    public Book(String id, String name, String version, String ownerID, String condition, String isbn, Double price) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.ownerID = ownerID;
        this.condition = condition;
        this.isbn = isbn;
        this.price = price;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
                ", price=" + price +
                '}';
    }
}
