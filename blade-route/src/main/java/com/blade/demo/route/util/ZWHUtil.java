package com.blade.demo.route.util;

import com.blade.demo.route.model.user.User;
import com.blade.demo.route.util.oauth.ExpiredException;
import com.blade.demo.route.util.oauth.OAtuthUtil;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.RandomUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZWHUtil {

    public static Gson gson = new Gson();

    /**
     * 验证用户名，支持中英文（包括全角字符）、数字、下划线和减号 （全角及汉字算两位）,长度为4-20位,中文按二位计数
     *
     * @param userName
     * @return
     */
    public static boolean validateUserName(String userName) {
        String validateStr = "^[\\w\\-－＿[０-９]\u4e00-\u9fa5\uFF21-\uFF3A\uFF41-\uFF5A]+$";
        boolean rs = false;
        rs = matcher(validateStr, userName);
        if (rs) {
            int strLenth = getStrLength(userName);
            if (strLenth < 4 || strLenth > 20) {
                rs = false;
            }
        }
        return rs;
    }


    /**
     * 验证密码
     * 1，不能全部是数字
     * 2，不能全部是字母
     * 3，必须是数字或字母
     * @param password
     * @return
     */
    public static boolean validatePassword(String password) {
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
        return password.matches(regex);
    }

    /**
     * 获取字符串的长度，对双字符（包括汉字）按两位计数
     *
     * @param value
     * @return
     */
    public static int getStrLength(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        for (int i = 0; i < value.length(); i++) {
            String temp = value.substring(i, i + 1);
            if (temp.matches(chinese)) {
                valueLength += 2;
            } else {
                valueLength += 1;
            }
        }
        return valueLength;
    }

    private static boolean matcher(String reg, String string) {
        boolean tem = false;
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(string);
        tem = matcher.matches();
        return tem;
    }
    /*
    获取七位随机数
     */
    public static Long getRandomCode() {
        return RandomUtils.nextLong(1000000L,9999999L);
    }

    /**
     * 从token获取用户实体
     * @param token
     * @return
     * @throws ExpiredException
     */
    public static User getUserFromToken(String token) throws ExpiredException {
//        String token = request.header("Authorization");
        Claims claims = OAtuthUtil.parseJWT(token);
        User user = gson.fromJson(claims.getSubject(), User.class);
        return user;
    }
}
