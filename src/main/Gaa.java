package main;

import java.io.IOException;
import java.sql.Date;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Gaa {
    public static final String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:";

    public static void main(String[] args) {
        // TODO 自動生成されたメソッド・スタブ
        Long isbn = 9784295007807L;

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
        System.out.println(json);

    }

}
