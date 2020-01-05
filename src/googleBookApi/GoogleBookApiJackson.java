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
    public static final String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:";

    public static Book getBook(Long isbn){

        Book book = new Book();

        //本の題名
        String title ="";
        //作者
        String[] author = new String[3];
        //出版日
        Date publishedDate = null;

        //出版社
        String publisher ="";
        //定価
        Integer listPrice =0;
        //説明文
        String description = "";
        //ページ数
        Integer pageCount =0;
        //smallサムネイル
        String smallThumbnail = "";
        //サムネイル
        String thumbnail = "";


        OkHttpClient okHttpClient;

        okHttpClient = new OkHttpClient();

        Request.Builder builder = new Request.Builder();

        //urlでリクエストを送信するアドレスを挿入
        builder.url(url + isbn);

        Request request = builder.build();

        Response response = null;


        //jsonの内容を表示する
//        System.out.println(json);


        try {
            response = okHttpClient.newCall(request).execute();
            String json = response.body().string();

            ObjectMapper mapper = new ObjectMapper();

            JsonNode node = mapper.readTree(json);

             title =          node.get("items").get(0).get("volumeInfo").get("title").asText();
             author[0] =         node.get("items").get(0).get("volumeInfo").get("authors").get(0).asText();
             author[1] =         node.get("items").get(0).get("volumeInfo").get("authors").get(1).asText();
             author[2] =         node.get("items").get(0).get("volumeInfo").get("authors").get(2).asText();
             publisher =      node.get("items").get(0).get("volumeInfo").get("publisher").asText();

             //出版日をDate型に変換するためにString dayで受け取り
             String day = node.get("items").get(0).get("volumeInfo").get("publishedDate").textValue();

             //文字列をsql型のDateに変換
             publishedDate =  Date.valueOf(day);

             listPrice =      node.get("items").get(0).get("saleInfo").get("listPrice").get("amount").asInt();
             description =    node.get("items").get(0).get("volumeInfo").get("description").asText();
             pageCount =      node.get("items").get(0).get("volumeInfo").get("pageCount").asInt();
             smallThumbnail = node.get("items").get(0).get("volumeInfo").get("imageLinks").get("smallThumbnail").asText();
             thumbnail =      node.get("items").get(0).get("volumeInfo").get("imageLinks").get("thumbnail").asText();

        } catch (IOException e) {
            e.printStackTrace();

        }

        book.setTitle(title);
        book.setAuthor1(author[0]);
        book.setAuthor2(author[1]);
        book.setAuthor3(author[2]);
        book.setPublishedDate(publishedDate);
        book.setPublisher(publisher);
        book.setListPrice(listPrice);
        book.setDescription(description);
        book.setPageCount(pageCount);
        book.setSmallThumbnail(smallThumbnail);
        book.setThumbnail(thumbnail);


        return book;

    }

}
