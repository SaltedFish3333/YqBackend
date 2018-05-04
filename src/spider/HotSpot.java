package spider;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.apache.http.impl.client.DefaultHttpClient;



public class HotSpot {
   // public static String webheader="http://s.weibo.com/weibo/";
    public static String webheader="http://s.weibo.com/weibo/";
    public static String doubanheader="https://www.douban.com/";
    public static String toutiaoheader="https://www.toutiao.com/";
 
    public static String download_page(String url){
        String content=null;
        //创建客户端
        DefaultHttpClient httpClient=new DefaultHttpClient();
        HttpGet httpGet=new HttpGet(url);
        HttpResponse response;
        try {
            response = httpClient.execute(httpGet);
            HttpEntity entity=response.getEntity();
            if (entity!=null) {
                content=EntityUtils.toString(entity,"utf-8");
                //EntityUtils.consume(entity);
            }
        } catch (ClientProtocolException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }finally {
            httpClient.getConnectionManager().shutdown();
        }

        return content;
    }

    public static Document get_page(String url){
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
            System.out.println("url或网络连接错误");
            return null;
        }
    }

    public static String Analysis_page(Document page_html,String tagStr){
        Elements links=page_html.getElementsByTag(tagStr);
        String allstring=null;
        int tag=0;
        for(Element link:links){
            String script=link.toString();
            if (script.length()>tag) {
                tag=script.length();
                allstring=script;
            }
        }
        return allstring;
    }

    public static String[] patter_wb(String tag){
        String [][] result=new String[50][2];
        int i=0,j=0;
       // Pattern pattern = Pattern.compile("<p class=\\\\\"star_name\\\\\">\\\\n  <a target=\\\\\"_blank\\\\\" href=\\\\\"\\\\/weibo\\\\/(.*?)&Refer=top\\\\\"  suda-data=\\\\\"key=tblog_search_list&value=list_realtimehot\\\\\">(.*?)<\\\\/a>\\n  <\\\\/p>");
        Pattern pattern = Pattern.compile("class=\\\\\"star_name\\\\\">\\\\n\\s+<a target=\\\\\"_blank\\\\\"\\s+href=\\\\\"\\\\/weibo\\\\/(.*?)&Refer=top\\\\\"\\s+suda-data=\\\\\"key=tblog_search_list&value=list_realtimehot\\\\\">(.*?)<\\\\/a>");
        Matcher matcher = pattern.matcher(tag);
        Boolean b=matcher.find();
        while (matcher.find()) {
            result[i][j]=StringEscapeUtils.unescapeJava(matcher.group(2));
            j++;
            result[i][j]=webheader+matcher.group(1);
            j=0;i++;
        }
        String resultArray[] = new String[50];
        for(int index = 0; index < 50; index ++){
        	resultArray[index] = result[index][0];
        }
        return resultArray;
    }
    public static String[] patter_db(String tag){
        String [][] result=new String[6][2];
        int i=0,j=0;
       // Pattern pattern = Pattern.compile("<p class=\\\\\"star_name\\\\\">\\\\n  <a target=\\\\\"_blank\\\\\" href=\\\\\"\\\\/weibo\\\\/(.*?)&Refer=top\\\\\"  suda-data=\\\\\"key=tblog_search_list&value=list_realtimehot\\\\\">(.*?)<\\\\/a>\\n  <\\\\/p>");
        Pattern pattern = Pattern.compile("<a\\s+href=\"https://www.douban.com/gallery/topic/\\d{3,5}/\\?from_reason=(.*?)&amp;from=gallery_rec_topic\"\\s+target=\"_blank\">[\\s|\\n|\\t|\\r]+<span class=\"topic_name\">[\\s|\\n|\\t|\\r]+<span>(.*?)</span>");
        Matcher matcher = pattern.matcher(tag);
        Boolean b=matcher.find();
        while (matcher.find()) {
            result[i][j]=StringEscapeUtils.unescapeJava(matcher.group(2));
            j++;
            result[i][j]=doubanheader+matcher.group(1);
            j=0;i++;
        }
        String resultArray[] = new String[6];
        for(int index = 0; index < 6; index ++){
        	resultArray[index] = result[index][0];
        }
        return resultArray;
    }
    public static String[] patter_tt(String tag){
    	String [][] result=new String[12][4];
        int i=0,j=0;
       // Pattern pattern = Pattern.compile("<p class=\\\\\"star_name\\\\\">\\\\n  <a target=\\\\\"_blank\\\\\" href=\\\\\"\\\\/weibo\\\\/(.*?)&Refer=top\\\\\"  suda-data=\\\\\"key=tblog_search_list&value=list_realtimehot\\\\\">(.*?)<\\\\/a>\\n  <\\\\/p>");
        //Pattern pattern = Pattern.compile("<a target=\\\\\"_blank\\\\\"\\s+href=\\\\\"\\\\/weibo\\\\/(.*?)&Refer=top\\\\\"\\s+suda-data=\\\\\"key=tblog_search_list&value=list_realtimehot\\\\\">(.*?)<\\\\/a>");
       //Pattern pattern = Pattern.compile("<a\\s+href=\"https://www.douban.com/gallery/topic/\\d{3,5}/\\?from_reason=(.*?)&amp;from=gallery_rec_topic\"\\s+target=\"_blank\">[\\s|\\n|\\t|\\r]+<span class=\"topic_name\">[\\s|\\n|\\t|\\r]+<span>(.*?)</span>");
        Pattern pattern = Pattern.compile("\"open_url\":\"\\\\/group\\\\/(.*?)\\\\/\",\"group_id\":\"(.*?)\",\"image_url\":\"\\\\/\\\\/p\\d{1,1}.pstatp.com\\\\/list\\\\/240x240\\\\/(.*?)\",\"title\":\"(.*?)\"}");
        Matcher matcher = pattern.matcher(tag);
        Boolean b=matcher.find();
        while (matcher.find()) {
            result[i][j]=StringEscapeUtils.unescapeJava(matcher.group(4));
            j++;
            result[i][j]=toutiaoheader+matcher.group(1);
            j=0;i++;
        }
        String resultArray[] = new String[12];
        for(int index = 0; index < 12; index ++){
        	resultArray[index] = result[index][0];
        }
        
        return resultArray;
    }
}
