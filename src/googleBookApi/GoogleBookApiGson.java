package googleBookApi;

import java.io.IOException;
import java.util.List;

import com.example.gson.ImageLinks;
import com.example.gson.Item;
import com.example.gson.ListPrice;
import com.example.gson.Model;
import com.example.gson.SaleInfo;
import com.example.gson.VolumeInfo;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GoogleBookApiGson {

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

        Gson gson = new Gson();

        Model models = gson.fromJson(json, Model.class);
        List<Item> items2 = models.items;
        VolumeInfo  volumeInfo = items2.get(0).volumeInfo;
        ImageLinks imageLinks = volumeInfo.imageLinks;

        SaleInfo saleInfo = items2.get(0).saleInfo;
        ListPrice listPrice = saleInfo.listPrice;

        System.out.println("title: "    + volumeInfo.title);//title
        System.out.println("author: "   + volumeInfo.authors.get(0));//author
        System.out.println("出版日: "   + volumeInfo.publishedDate);//publishedDate
        System.out.println("出版社: "   + volumeInfo.publisher);//publisher
        System.out.println("定価:"      + listPrice.amount);
        System.out.println("説明："     + volumeInfo.description);//description
        System.out.println("ページ数：" + volumeInfo.pageCount);//pageCount
        System.out.println("スモールサムネイル:" + imageLinks.smallThumbnail);//smallThumbnail
        System.out.println("サムネイル:"         + imageLinks.thumbnail);//thumbnail

    }
}
