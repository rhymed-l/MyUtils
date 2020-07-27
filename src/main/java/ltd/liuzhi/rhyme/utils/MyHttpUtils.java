package ltd.liuzhi.rhyme.utils;


import java.io.*;
import java.net.*;
import java.util.*;
import java.util.function.BiConsumer;

/**
 * 网络请求工具类
 * @author LiuZhi
 */
public class MyHttpUtils {

    public static final String POST_REQUEST_METHOD = "POST";
    public static final String GET_REQUEST_METHOD = "GET";
    public static final String DELETE_REQUEST_METHOD = "DELETE";
    public static final String PUT_REQUEST_METHOD = "PUT";
    public static final String HEAD_REQUEST_METHOD = "HEAD";
    public static final String OPTIONS_REQUEST_METHOD = "OPTIONS";
    public static final String CONNECT_REQUEST_METHOD = "CONNECT";
    public static final String TRACE_REQUEST_METHOD = "TRACE";

    private MyHttpUtils() {
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url) {
        return sendGet(url, null,null, "UTF-8");
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        return sendGet(url, null,param, "UTF-8");
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, Map<String,String> param) {
        return sendGet(url,null, paramMapToString(param), "UTF-8");
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param requestHeader 请求头参数
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url,Map<String,String> requestHeader, String param, String charset) {
        String result = "";
        BufferedReader in = null;
        if (charset == null || charset.isEmpty()) {
            charset = "UTF-8";
        }
        try {

            String urlNameString = MyStringUtils.isEmpty(param) ? url : url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("ContentType", "application/json;charset=" + charset);
            if (requestHeader != null && requestHeader.size() > 0) {
                for (Map.Entry<String, String> entry : requestHeader.entrySet()) {
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//			for (String key : map.keySet()) {
//				System.out.println(key + "--->" + map.get(key));
//			}
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
//			System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url) {
        return sendPost(url,"");
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url,String param) {

        return sendPost(url,null,param);
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param requestHeader 请求头参数
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url,Map<String, String> requestHeader, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("ContentType", "application/json;charset=UTF-8");
            if (requestHeader != null && requestHeader.size() > 0) {
                for (Map.Entry<String, String> entry : requestHeader.entrySet()) {
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
            return null;
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url 发送请求的 URL
     * @param param 请求参数
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, Map<String, String> param) {
        return sendPost(url, paramMapToString(param));
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * Map 成功响应 code 1 data 为响应数据 否则 code 0 msg 具体错误信息
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static Map sendPostByMap(String url, String param) {
        Map map = new HashMap();
        map.put("code", 1);
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            if (conn.getResponseCode() != 200) {
                map.put("code", 0);
                map.put("msg", "responseCode=" + conn.getResponseCode());
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
            map.put("code", 0);
            map.put("msg", e.toString());
            return map;
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        map.put("data", result);
        return map;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数
     * @return 所代表远程资源的响应结果
     */
    public static String sendPostL(String url, Map<String,String> param)
    {
        return sendPostL(url,paramMapToString(param));
    }
    /**
     * 向指定 URL 发送POST方法的请求
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 json 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPostL(String url, String param) {
        return sendPostL(url,null,param);
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * @param url   发送请求的 URL
     * @param requestHeader 请求头参数
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPostL(String url, Map<String,String> requestHeader ,String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            conn.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            if (requestHeader != null && requestHeader.size() > 0) {
                for (Map.Entry<String, String> entry : requestHeader.entrySet()) {
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);

            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求(模拟from表单提交)
     *
     * @param requestUrl    发送请求的 URL
     * @param params        请求参数map。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPostByForm(String requestUrl, Map<String, String> params)
    {
        return sendPostByForm(requestUrl,null,params,null);
    }
    /**
     * 向指定 URL 发送POST方法的请求(模拟from表单提交)
     *
     * @param requestUrl    发送请求的 URL
     * @param params        请求参数map。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPostByForm(String requestUrl, Map<String, String> requestHeader,  Map<String, String> params)
    {
        return sendPostByForm(requestUrl,requestHeader,params,null);
    }

    public static String sendRequest(String requestUrl, Map<String, String> requestHeader, Map<String, String> params, File file,String requestMode) {
        if (MyStringUtils.isEmpty(requestMode)) {
            throw new IllegalArgumentException("请求参数不能为空");
        }
            OutputStream out = null;
            BufferedReader reader = null;
            String result = "";
            try {
                if (requestUrl == null || requestUrl.isEmpty()) {
                    return result;
                }
                URL realUrl = new URL(requestUrl);
                HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
                connection.setRequestProperty("accept", "*/*");
                connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setRequestMethod(requestMode);
                if (requestHeader != null && requestHeader.size() > 0) {
                    for (Map.Entry<String, String> entry : requestHeader.entrySet()) {
                        connection.setRequestProperty(entry.getKey(), entry.getValue());
                    }
                }
                connection.setRequestProperty("content-type", "application/octet-stream");
                out = new DataOutputStream(connection.getOutputStream());
                out.write((" \r\n").getBytes());
                DataInputStream in = new DataInputStream(new FileInputStream(file));
                int bytes = 0;
                byte[] bufferOut = new byte[1024];
                while ((bytes = in.read(bufferOut)) != -1) {
                    out.write(bufferOut, 0, bytes);
                }
                in.close();
                out.flush();
                out.close();
                out = null;
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                String line;
                while ((line = reader.readLine()) != null) {
                    result += line;
                }
            } catch (Exception e) {
                System.out.println("发送" + requestMode + "请求出现异常！");
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            return result;
    }
    /**
     * 向指定 URL 发送请求(模拟from表单提交)
     *
     * @param requestUrl    发送请求的 URL
     * @param requestHeader 请求头参数map。
     * @param params        请求参数map。
     * @param files         文件参数map。
     * @return 所代表远程资源的响应结果
     */
    public static String sendRequestByForm(String requestUrl, Map<String, String> requestHeader, Map<String, String> params, Map<String, File> files,String requestMode) {
        if(MyStringUtils.isEmpty(requestMode))
        {
            throw new IllegalArgumentException("请求参数不能为空");
        }
        OutputStream out = null;
        BufferedReader reader = null;
        String result = "";
        try {
            if (requestUrl == null || requestUrl.isEmpty()) {
                return result;
            }
            URL realUrl = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod(requestMode);
            if (requestHeader != null && requestHeader.size() > 0) {
                for (Map.Entry<String, String> entry : requestHeader.entrySet()) {
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            if (files == null || files.size() == 0) {
                connection.setRequestProperty("content-type", "application/x-www-form-urlencoded");
                out = new DataOutputStream(connection.getOutputStream());
                if (params != null && params.size() > 0) {
                    String formData = "";
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        formData += entry.getKey() + "=" + entry.getValue() + "&";
                    }
                    formData = formData.substring(0, formData.length() - 1);
                    out.write(formData.getBytes());
                }
            } else {
                String boundary = "-----------------------------" + System.currentTimeMillis();
                connection.setRequestProperty("content-type", "multipart/form-data; boundary=" + boundary);
                out = new DataOutputStream(connection.getOutputStream());
                if (params != null && params.size() > 0) {
                    StringBuilder sbFormData = new StringBuilder();
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        sbFormData.append("--" + boundary + "\r\n");
                        sbFormData.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"\r\n\r\n");
                        sbFormData.append(entry.getValue() + "\r\n");
                    }
                    out.write(sbFormData.toString().getBytes());
                }
                for (Map.Entry<String, File> entry : files.entrySet()) {
                    String fileName = entry.getKey();
                    File file = entry.getValue();
                    if (file == null || file.getPath() == null || file.getName().isEmpty()) {
                        continue;
                    }
                    if (!file.exists()) {
                        continue;
                    }
                    out.write(("--" + boundary + "\r\n").getBytes());
                    out.write(("Content-Disposition: form-data; name=\"" + fileName + "\"; filename=\"" + file.getName() + "\"\r\n").getBytes());
                    out.write(("Content-Type: application/x-msdownload\r\n\r\n").getBytes());
                    DataInputStream in = new DataInputStream(new FileInputStream(file));
                    int bytes = 0;
                    byte[] bufferOut = new byte[1024];
                    while ((bytes = in.read(bufferOut)) != -1) {
                        out.write(bufferOut, 0, bytes);
                    }
                    in.close();
                    out.write(("\r\n").getBytes());
                }
                out.write(("--" + boundary + "--").getBytes());
            }
            out.flush();
            out.close();
            out = null;
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送" + requestMode + "请求出现异常！");
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 向指定 URL 发送POST方法的请求(模拟from表单提交)
     *
     * @param requestUrl    发送请求的 URL
     * @param requestHeader 请求头参数map。
     * @param params        请求参数map。
     * @param files         文件参数map。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPostByForm(String requestUrl, Map<String, String> requestHeader, Map<String, String> params, Map<String, File> files) {
        return sendRequestByForm(requestUrl,requestHeader,params,files,POST_REQUEST_METHOD);
    }

    /**
     * 下载图片到本地
     *
     * @param urlList URL地址
     * @param path    保存的路径
     */
    public static void downloadPicture(String urlList, String path) {
        URL url = null;
        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读图片数据
     *
     * @param urlList URL地址
     */
    public static byte[] readPicture(String urlList) {
        URL url = null;
        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            dataInputStream.close();
            byte[] bytes = output.toByteArray();
            output.close();
            return bytes;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取url 的 HOST地址
     *
     * @param url 需要获取的连接
     * @return 返回url的host
     */
    public static String getUrlHost(String url) {
        if (url == null) {
            throw new RuntimeException("需要获取的URL地址不能为NULL");
        }
        url = url.toLowerCase();
        String host;
        if (url.contains("https://")) {
            host = "https://" +  MyStringUtils.getTextMiddle(url, "https://", "/");
        } else if (url.contains("http://")) {
            host = "http://" + MyStringUtils.getTextMiddle(url, "http://", "/");
        } else {
            host = MyStringUtils.getTextLeft(url, "/");
        }
        return host;
    }


    /**
     * Map参数转为String参数
     *
     * @param param 需要转换的参数
     * @return 转换之后的参数
     */
    public static String paramMapToString(Map<String,String> param)
    {
        String paramStr = "";
        if(param == null)
        {
            return paramStr;
        }
        StringBuffer sb = new StringBuffer();
        param.forEach((key,val) -> sb.append("&").append(key).append("=").append(val));
        if(sb.toString().length()>0)
        {
            paramStr = sb.toString().substring(1);
        }
        return paramStr;
    }

    /**
     * 获取短连接
     * @param longUrl 需要缩短的长链接
     * @return 返回缩短后的链接
     */
    public static String longUrlToShorUrl(String longUrl)
    {
        // 可以自定义生成 MD5 加密字符传前的混合 KEY
        String key = "LiuZhi" ;
        // 要使用生成 URL 的字符
        String[] chars = new String[] { "a" , "b" , "c" , "d" , "e" , "f" , "g" , "h" ,
                "i" , "j" , "k" , "l" , "m" , "n" , "o" , "p" , "q" , "r" , "s" , "t" ,
                "u" , "v" , "w" , "x" , "y" , "z" , "0" , "1" , "2" , "3" , "4" , "5" ,
                "6" , "7" , "8" , "9" , "A" , "B" , "C" , "D" , "E" , "F" , "G" , "H" ,
                "I" , "J" , "K" , "L" , "M" , "N" , "O" , "P" , "Q" , "R" , "S" , "T" ,
                "U" , "V" , "W" , "X" , "Y" , "Z"
        };
        // 对传入网址进行 MD5 加密
        String sMD5EncryptResult = (MyEncryptionUtils.MD5Encode32(key + longUrl));
        String hex = sMD5EncryptResult;
        String[] resUrl = new String[4];
        //得到 4组短链接字符串
        for ( int i = 0; i < 4; i++) {
            // 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
            String sTempSubString = hex.substring(i * 8, i * 8 + 8);
            // 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用 long ，则会越界
            long lHexLong = 0x3FFFFFFF & Long.parseLong (sTempSubString, 16);
            String outChars = "" ;
            //循环获得每组6位的字符串
            for ( int j = 0; j < 6; j++) {
                // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
                //(具体需要看chars数组的长度   以防下标溢出，注意起点为0)
                long index = 0x0000003D & lHexLong;
                // 把取得的字符相加
                outChars += chars[( int ) index];
                // 每次循环按位右移 5 位
                lHexLong = lHexLong >> 5;
            }
            // 把字符串存入对应索引的输出数组
            resUrl[i] = outChars;
        }
        return resUrl[1];
    }
}
