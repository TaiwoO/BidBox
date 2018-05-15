package finalproject.mobilecomputing.bidbox.utils.apis.BidBox;

import org.json.JSONException;
import org.json.JSONObject;

import finalproject.mobilecomputing.bidbox.models.Book;

/**
 * Created by Taiwo on 5/15/2018.
 */

public final class BookJsonWrapper {

    private BookJsonWrapper() {
        throw new UnsupportedOperationException();
    }

    public static Book createFromJson(JSONObject bookJson) {
        Book book = new Book();
        if (bookJson == null) {
            return null;
        }

        try {
            String id = bookJson.getString("_id");
            String name = bookJson.getString("name");
            String version = bookJson.optString("version");
            String condition = bookJson.getString("condition");
            String isbn = bookJson.optString("isbn");

            book.setId(id);
            book.setName(name);
            book.setVersion(version);
            book.setCondition(condition);
            book.setIsbn(isbn);

            return book;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
