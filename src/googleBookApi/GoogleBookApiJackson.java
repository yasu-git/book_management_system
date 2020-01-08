package googleBookApi;

import java.io.IOException;
import java.sql.Date;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.Book;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GoogleBookApiJackson {

    private Long isbn = 0L;

    public static final String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:";

    public GoogleBookApiJackson(Long isbn) throws Exception {
        this.isbn = isbn;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getUrl() {
        return url;
    }

    public Book getBook() {

        Book book = new Book();

        OkHttpClient okHttpClient;

        okHttpClient = new OkHttpClient();

        Request.Builder builder = new Request.Builder();

        //urlでリクエストを送信するアドレスを挿入
        builder.url(getUrl() + getIsbn());

        Request request = builder.build();

        Response response = null;

        //jsonの内容を表示する
        //        System.out.println(json);

        JsonNode node = null;

        try {
            response = okHttpClient.newCall(request).execute();
            String json = response.body().string();
            ObjectMapper mapper = new ObjectMapper();
            node = mapper.readTree(json);

        } catch (IOException e) {
            e.printStackTrace();

        }

        book.setTitle(node.get("items").get(0).get("volumeInfo").get("title").asText());
        book.setAuthor1(node.get("items").get(0).get("volumeInfo").get("authors").get(0).asText());

        try {
            book.setAuthor2(node.get("items").get(0).get("volumeInfo").get("authors").get(1).asText());
        } catch (Exception e) {
        }

        try {
            book.setAuthor3(node.get("items").get(0).get("volumeInfo").get("authors").get(2).asText());
        } catch (Exception e) {
        }

        //出版日をDate型に変換するためにString dayで受け取り
        String day = node.get("items").get(0).get("volumeInfo").get("publishedDate").textValue();

        //文字列をsql型のDateに変換
        book.setPublishedDate(Date.valueOf(day));

        try {
            book.setPublisher(node.get("items").get(0).get("volumeInfo").get("publisher").asText());
        } catch (Exception e) {
            book.setPublisher("情報なし");
        }

        try {
            book.setListPrice(node.get("items").get(0).get("saleInfo").get("listPrice").get("amount").asInt());
        } catch (Exception e) {
            book.setListPrice(0);
        }



        try {
            book.setDescription(node.get("items").get(0).get("volumeInfo").get("description").asText());
        } catch (Exception e) {
        }

        try {
            book.setPageCount(node.get("items").get(0).get("volumeInfo").get("pageCount").asInt());
        } catch (Exception e) {
        }

        try {
            book.setSmallThumbnail(
                    node.get("items").get(0).get("volumeInfo").get("imageLinks").get("smallThumbnail").asText());
        } catch (Exception e) {
        }

        try {
            book.setThumbnail(node.get("items").get(0).get("volumeInfo").get("imageLinks").get("thumbnail").asText());
        } catch (Exception e) {
        }

        return book;

    }

}
