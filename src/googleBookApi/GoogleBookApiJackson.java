package googleBookApi;

import java.io.IOException;
import java.sql.Date;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GoogleBookApiJackson {
    public static final String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:";

    public static void getBook(Long isbn){

        //jsonを保持
        String json ="";

        //本の題名
        String title = "";
        //作者
        String author = "";
        //出版日
        Date publishedDate = null;
        String day = "";
        //出版社
        String publisher = "";
        //定価
        Integer listPrice = null;
        //説明文
        String description = "";
        //ページ数
        Integer pageCount = null;
        //smallサムネイル
        String smallThumbnail ="";
        //サムネイル
        String thumbnail = "";


        OkHttpClient okHttpClient;

        okHttpClient = new OkHttpClient();

        Request.Builder builder = new Request.Builder();

        //urlでリクエストを送信するアドレスを挿入
        builder.url(url + isbn);

        Request request = builder.build();

        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            json = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //jsonの内容を表示する
//        System.out.println(json);

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode node = mapper.readTree(json);

             title =          node.get("items").get(0).get("volumeInfo").get("title").asText();
             author =         node.get("items").get(0).get("volumeInfo").get("authors").get(0).asText();
             publisher =      node.get("items").get(0).get("volumeInfo").get("publisher").asText();

             //出版日をDate型に変換するためにString dayで受け取り
             day = node.get("items").get(0).get("volumeInfo").get("publishedDate").textValue();

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

        System.out.println("title: " +title);
        System.out.println("author: " +author);
        System.out.println("出版社: "+publisher);
        System.out.println("出版日: " + publishedDate);
        System.out.println("定価: " + listPrice + "円");
        System.out.println("説明文: " + description);
        System.out.println("ページ数: " + pageCount);
        System.out.println("小さいサムネイル: " + smallThumbnail);
        System.out.println("サムネイル: " +thumbnail);



    }

}
