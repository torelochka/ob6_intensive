package ru.ob6.web.utils;

import java.util.regex.Pattern;

public class TrailerUrlParser {

    public static String parse(String url) {
        Pattern pattern = Pattern.compile("((http(s)?://)?)(www\\.)?((youtube\\.com/)|(youtu.be/))[\\S]+");
        return pattern.split(url, 1)[0];
    }
}
