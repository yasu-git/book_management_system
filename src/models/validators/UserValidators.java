package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.User;
import utils.DBUtil;

public class UserValidators {
    public static List<String> validate(User u, Boolean userId_duplicate_check_flag, Boolean password_check_flag ){
        List<String> errors = new ArrayList<String>();

        //userIdが入力されているか userIdが存在しているか検証
        String userId_error = _validateUserId(u.getUserId(), userId_duplicate_check_flag);

        if(!userId_error.equals("")){
            errors.add(userId_error);
        }

        //namaが入力されているか
        String name_error = _validateName(u.getName());

        if(!name_error.equals("")){
            errors.add(name_error);
        }

        //パスワードが入力されているか
        String password_error = _validatePassword(u.getPassword(), password_check_flag);

        if(!password_error.equals("")){
            errors.add(password_error);
        }

        return errors;
    }

    private static String _validateUserId(String userId, Boolean userId_duplicate_check_flag){
        //必須入力チェック
        if(userId == null || userId.equals("")){
            return "useridを入力してください。";
        }

        //既に登録されている社員番号との重複チェック
        if(userId_duplicate_check_flag){
            EntityManager em = DBUtil.createEntityManager();
            long users_count = (long)em.createNamedQuery("checkRegisteredUserId" ,Long.class)
                    .setParameter("userId", userId)
                    .getSingleResult();

            em.close();

            if(users_count > 0){
                return "入力されたuserIdの情報はすでにそんざいしています。";
            }
        }

        return"";

    }

    //userNameの必須入力チェック
    private static String _validateName(String name){
        if(name ==null || name.equals("")){
            return "氏名を入力してください。";
        }
        return "";
    }

    //パスワードの必須入力チェック
    private static String _validatePassword(String password, Boolean password_check_flag){
        //パスワードを変更する場合のみ実行
        if(password_check_flag && (password == null || password.equals(""))){
            return "パスワードを入力してください。";
        }
        return "";
    }
}
