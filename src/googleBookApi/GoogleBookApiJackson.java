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


    //本の情報取得数
    private Integer count = 0;

    //googleBooksApiのアドレス
    public final String url = "https://www.googleapis.com/books/v1/volumes?q=";

    JsonNode node ;

    Book book = new Book();

    //getter
    //setter

    public String getUrl() {
        return url;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer jsonNode) {
        this.count = jsonNode;
    }

    public JsonNode getNode() {
        return node;
    }

    public void setNode(JsonNode node){
        this.node = node;
    }

    public Book getBook(){
        return book;
    }



    //isbn検索時
    public void setIsbn(Long isbn) {

        OkHttpClient okHttpClient;

        okHttpClient = new OkHttpClient();

        Request.Builder builder = new Request.Builder();

        //urlでリクエストを送信するアドレスを挿入
        builder.url(getUrl() + "isbn:" + isbn);

        Request request = builder.build();

        Response response = null;

        //jsonの内容を表示する
        //        System.out.println(json);

        try {
            response = okHttpClient.newCall(request).execute();
            String json = response.body().string();
            ObjectMapper mapper = new ObjectMapper();
            node = mapper.readTree(json);
            setNode(node);
            setCount(node.get("totalItems").asInt());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //本の情報を取得
    public Book getBooks() {

        JsonNode node = getNode();
        Book book = getBook();

        try{
            book.setTitle(node.get("items").get(0).get("volumeInfo").get("title").asText());
        }catch(Exception e){

        }

        try{
            book.setAuthor1(node.get("items").get(0).get("volumeInfo").get("authors").get(0).asText());
        }catch(Exception e){

        }

        try {
            book.setAuthor2(node.get("items").get(0).get("volumeInfo").get("authors").get(1).asText());
        } catch (Exception e) {
        }

        try {
            book.setAuthor3(node.get("items").get(0).get("volumeInfo").get("authors").get(2).asText());
        } catch (Exception e) {
        }

        //出版日をDate型に変換するためにString dayで受け取り
        try {
            String day = node.get("items").get(0).get("volumeInfo").get("publishedDate").textValue();

            //文字列をsql型のDateに変換
            book.setPublishedDate(Date.valueOf(day));

        } catch (Exception e) {

        }

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
            book.setDescription("なし");
        }

        try {
            book.setPageCount(node.get("items").get(0).get("volumeInfo").get("pageCount").asInt());
        } catch (Exception e) {
            book.setPageCount(0);
        }

        try {
            book.setSmallThumbnail(
                    node.get("items").get(0).get("volumeInfo").get("imageLinks").get("smallThumbnail").asText());
        } catch (Exception e) {
            book.setSmallThumbnail("No Image");
        }

        try {
            book.setThumbnail(node.get("items").get(0).get("volumeInfo").get("imageLinks").get("thumbnail").asText());
        } catch (Exception e) {
            book.setThumbnail("No Image");
        }
        try{
            book.setIsbn(node.get("items").get(0).get("volumeInfo").get("industryIdentifiers").get(0).get("identifier").asLong());
        }catch(Exception e){
            book.setIsbn(0L);
        }

        return book;
    }

}
