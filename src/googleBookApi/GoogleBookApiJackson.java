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


    public GoogleBookApiJackson(Long isbn){
        this.isbn = isbn;
    }

    public Long getIsbn(){
        return isbn;
    }

    public void setIsbn(Long isbn){
        this.isbn = isbn;
    }

    public String getUrl(){
        return  url;
    }



    public Book getBook(){

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


        try {
            response = okHttpClient.newCall(request).execute();
            String json = response.body().string();

            ObjectMapper mapper = new ObjectMapper();

            JsonNode node = mapper.readTree(json);

            book.setTitle(node.get("items").get(0).get("volumeInfo").get("title").asText());
            book.setAuthor1(node.get("items").get(0).get("volumeInfo").get("authors").get(0).asText());
            book.setAuthor2(node.get("items").get(0).get("volumeInfo").get("authors").get(1).asText());
            book.setAuthor3(node.get("items").get(0).get("volumeInfo").get("authors").get(2).asText());

          //出版日をDate型に変換するためにString dayで受け取り
            String day = node.get("items").get(0).get("volumeInfo").get("publishedDate").textValue();

          //文字列をsql型のDateに変換
            book.setPublishedDate(Date.valueOf(day));
            book.setPublisher(node.get("items").get(0).get("volumeInfo").get("publisher").asText());
            book.setListPrice(node.get("items").get(0).get("saleInfo").get("listPrice").get("amount").asInt());
            book.setDescription(node.get("items").get(0).get("volumeInfo").get("description").asText());
            book.setPageCount(node.get("items").get(0).get("volumeInfo").get("pageCount").asInt());
            book.setSmallThumbnail(node.get("items").get(0).get("volumeInfo").get("imageLinks").get("smallThumbnail").asText());
            book.setThumbnail(node.get("items").get(0).get("volumeInfo").get("imageLinks").get("thumbnail").asText());

        } catch (IOException e) {
            e.printStackTrace();

        }

        return book;

    }

}
