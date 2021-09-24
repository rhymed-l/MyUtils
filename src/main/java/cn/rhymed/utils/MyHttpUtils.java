package cn.rhymed.utils;



import java.io.*;
import java.net.*;
import java.util.*;

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


//    /**
//     * 向指定 URL 发送请求(模拟from表单提交)
//     * @param requestUrl    发送请求的 URL
//     * @param requestHeader 请求头参数map。
//     * @param params        请求参数map。
//     * @param files         文件参数map。
//     * @param files         文件参数map。
//     * @return 所代表远程资源的响应结果
//     */
//    public static String sendRequest(String requestUrl, Map<String, String> requestHeader, Map<String, String> params, Map<String, File> files, HttpRequestMethodEnum requestMode, HttpRequestTypeEnum requestType) {
//        if(requestMode == null)
//        {
//            throw new IllegalArgumentException("请求参数不能为空");
//        }
//        if(requestType == null)
//        {
//            throw new IllegalArgumentException("请求类型不能为空");
//        }
//        BufferedReader reader = null;
//        String result = "";
//
//        if(HttpRequestMethodEnum.GET == requestMode){
//
//        }else {
//            OutputStream out = null;
//        }
//
//        try {
//            if (requestUrl == null || requestUrl.isEmpty()) {
//                return result;
//            }
//            if(requestType.equals(HttpRequestTypeEnum.PARAMS))
//            {
//                requestUrl = requestUrl + paramMapToString(params);
//            }
//            URL realUrl = new URL(requestUrl);
//            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
//            connection.setRequestProperty("Accept", "*/*");
//            connection.setRequestProperty("Connection", "keep-alive");
//            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0");
//            connection.setDoOutput(true);
//            connection.setDoInput(true);
//            //请求方式设置
//            connection.setRequestMethod(requestMode.toString());
//
//            //请求头设置
//            if (requestHeader != null && requestHeader.size() > 0) {
//                for (Map.Entry<String, String> entry : requestHeader.entrySet()) {
//                    connection.setRequestProperty(entry.getKey(), entry.getValue());
//                }
//            }
//
//            if(MyStringUtils.isNotEmpty(requestType.getType()))
//            {
//                connection.setRequestProperty("content-type", requestType.getType());
//            }
//
//            out  = new DataOutputStream(connection.getOutputStream());
//
//
//            if(requestType.equals(HttpRequestTypeEnum.X_WWW_FORM_URLENCODED)){
//                if (params != null && params.size() > 0) {
//                    String formData = "";
//                    for (Map.Entry<String, String> entry : params.entrySet()) {
//                        formData += entry.getKey() + "=" + entry.getValue() + "&";
//                    }
//                    formData = formData.substring(0, formData.length() - 1);
//                    out.write(formData.getBytes());
//                }
//            }else if (requestType.equals(HttpRequestTypeEnum.FORM_DATA)){
//                String boundary = "-----------------------------" + System.currentTimeMillis();
//                connection.setRequestProperty("content-type", requestType.getType() + " boundary=" + boundary);
//                if (params != null && params.size() > 0) {
//                    StringBuilder sbFormData = new StringBuilder();
//                    for (Map.Entry<String, String> entry : params.entrySet()) {
//                        sbFormData.append("--" + boundary + "\r\n");
//                        sbFormData.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"\r\n\r\n");
//                        sbFormData.append(entry.getValue() + "\r\n");
//                    }
//                    out.write(sbFormData.toString().getBytes());
//                }
//                for (Map.Entry<String, File> entry : files.entrySet()) {
//                    String fileName = entry.getKey();
//                    File file = entry.getValue();
//                    if (file == null || file.getPath() == null || file.getName().isEmpty()) {
//                        continue;
//                    }
//                    if (!file.exists()) {
//                        continue;
//                    }
//                    out.write(("--" + boundary + "\r\n").getBytes());
//                    out.write(("Content-Disposition: form-data; name=\"" + fileName + "\"; filename=\"" + file.getName() + "\"\r\n").getBytes());
//                    out.write(("Content-Type: application/x-msdownload\r\n\r\n").getBytes());
//                    DataInputStream in = new DataInputStream(new FileInputStream(file));
//                    int bytes = 0;
//                    byte[] bufferOut = new byte[1024];
//                    while ((bytes = in.read(bufferOut)) != -1) {
//                        out.write(bufferOut, 0, bytes);
//                    }
//                    in.close();
//                    out.write(("\r\n").getBytes());
//                }
//                out.write(("--" + boundary + "--").getBytes());
//            }else if(requestType.equals(HttpRequestTypeEnum.RAW_JSON)){
//                out.write(paramMapToJSON(params).getBytes());
//            }
//            out.flush();
//            out.close();
//            out = null;
//            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                result += line;
//            }
//        } catch (Exception e) {
//            System.out.println("发送" + requestMode + "请求出现异常！");
//            e.printStackTrace();
//        } finally {
//            try {
//                if (out != null) {
//                    out.close();
//                }
//                if (reader != null) {
//                    reader.close();
//                }
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//        return result;
//    }


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
     * Map参数转为String参数
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






}
