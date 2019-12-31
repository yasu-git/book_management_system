package main;

import java.io.IOException;

import com.example.qson.Model;
import com.fasterxml.jackson.databind.ObjectMapper;

public class main {

    public static void main(String[] args) {
        // TODO 自動生成されたメソッド・スタブ

        String isbn ="";

        "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn + "&country=JP";

        String json = "";

        ObjectMapper mapper = new ObjectMapper();

        try{

            Model model = mapper.readValue(json,Model.class);
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
