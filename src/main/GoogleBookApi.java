package main;

import java.io.IOException;
import java.util.List;

import com.example.qson.Item;
import com.example.qson.ListPrice;
import com.example.qson.Model;
import com.example.qson.SaleInfo;
import com.example.qson.VolumeInfo;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GoogleBookApi {

    public static final String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:";

    public static void GoogleBook(Long isbn) {

        //jsonを保持
        String json = "";

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

        System.out.println(json);

        Gson gson = new Gson();

        Model models = gson.fromJson(json, Model.class);
        List<Item> items2 = models.items;
        VolumeInfo  volume = items2.get(0).volumeInfo;
        SaleInfo saleInfo = items2.get(0).saleInfo;
        ListPrice listPrice = saleInfo.listPrice;

        System.out.println("title: " + volume.title);//title
        System.out.println("author: " + volume.authors);//author
        System.out.println("出版日: " +volume.publishedDate);//publishedDate
        System.out.println("出版社: " + volume.publisher);//publisher
        System.out.println("定価:" + listPrice.amount);
        System.out.println("説明：" + volume.description);//description
        System.out.println("ページ数："+ volume.pageCount);//pageCount
        System.out.println("スモールサムネイル:"+ volume.imageLinks.smallThumbnail);//smallThumbnail
        System.out.println("サムネイル:" + volume.imageLinks.thumbnail);//thumbnail

        //List型だとget(0)で配列のどちらかを選び中にある" "の中のタイプを指定
//        System.out.println("isbn13コード: " + volume.industryIdentifiers.get(0).identifier);//
        System.out.println(models.items.get(0).volumeInfo.title);

    }
}
