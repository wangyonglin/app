package com.util;

public class APIURL {
    public static String UUID(String uuid){
        String url="http://api.eeob.com/cinemas/movie/id";
        return url+"?"+"id="+uuid;
    }
    public static String SEARCH(String search,Integer page,Integer size){
        String url="http://api.eeob.com/cinemas/movie/title";
        return url+"?"+"size="+size+"&"+"page="+page+"&"+"title="+search;
    }
    public static String ALL(Integer page,Integer size){
        String url="http://api.eeob.com/cinemas/movie/all";
        return url+"?"+"size="+size+"&"+"page="+page;
    }
    public static String CATEGORIES(String categories,Integer page,Integer size){
        String url="http://api.eeob.com/cinemas/movie/categories";
        return url+"?"+"size="+size+"&"+"page="+page+"&"+"categories="+categories;
    }
}