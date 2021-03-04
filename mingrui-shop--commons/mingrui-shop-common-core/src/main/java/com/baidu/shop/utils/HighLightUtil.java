package com.baidu.shop.utils;

import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.data.elasticsearch.core.SearchHit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName HighLightUtil
 * @Description: TODO
 * @Author wyj
 * @Date 2021/3/4
 * @Version V1.0
 **/
public class HighLightUtil {
    public static HighlightBuilder getHighlightBuilder(String ...field){

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        Arrays.asList(field).stream().forEach(fi ->{
            highlightBuilder.field(fi); // 设置需要高亮的字段
            highlightBuilder.preTags("<span style='color:red'>");//前置标签
            highlightBuilder.postTags("</span>");//后置标签
        });

        return highlightBuilder;

    }

    //构建高亮字段buiilder
    public static <T> List<T> getHighlightList(List<SearchHit<T>> searchHits){

     /*   List<T> list = searchHits.stream().map(searchHit -> {

            T content = searchHit.getContent();
            Map<String, List<String>> highlightFields = searchHit.getHighlightFields();
            highlightFields.forEach((key, value) -> {
                try {
                    Method method = content.getClass().getMethod("set" + firstCharUpper(key), String.class);

                    method.invoke(content, value.get(0));

                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
            return content;
        }).collect(Collectors.toList());

        return list;
        */

        return searchHits.stream().map(searchHit -> {

            T content = searchHit.getContent();
            Map<String, List<String>> highlightFields = searchHit.getHighlightFields();
            highlightFields.forEach((key, value) -> {
                try {
                    Method method = content.getClass().getMethod("set" + firstCharUpper(key), String.class);

                    method.invoke(content, value.get(0));

                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
            return content;
        }).collect(Collectors.toList());

    }


    /**
     * 将字符串首字母大写
     * ascll码表的值(小写英文字母的值) - 32 --> 大写字母的值
     * @param str
     * @return
     */
    public static String firstCharUpper(String str){

        char[] chars = str.toCharArray();
        chars[0] -= 32;
        return String.valueOf(chars);
    }
}
