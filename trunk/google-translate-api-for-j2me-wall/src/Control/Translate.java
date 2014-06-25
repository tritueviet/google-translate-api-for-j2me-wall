package Control;

import models.Language;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import org.json.me.JSONObject;

/**
 * Makes the Google Translate API available to Java ME applications.
 * @author Jinath Sanjitha
 * @author Richard Midwinter
 */
public class Translate {

//trả về leng =0 là lỗi, trả về leng >=1 là ok
    public static String Dich(String text, String str1, String str2) {
        String resuilt = "";
        try {
            if (!"".equals(text.trim())) {

                Object localObject1;
                if (str1.equals("vi")) {
                    localObject1 = enCodeWall(text);
                    System.out.println(" viet name: " + localObject1.toString());
                } else {
                    localObject1 = text;
                }

                try {
                    resuilt = "";

                    HttpConnection http;
                    (http = (HttpConnection) Connector.open("http://translate.google.ru/translate_a/t", 3, true)).setRequestMethod("POST");

                    http.setRequestProperty("Host", "translate.google.ru");

                    http.setRequestProperty("User-Agent", "Opera/9.64");

                    http.setRequestProperty("Referer", "translate.google.ru");
                    http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    http.setRequestProperty("Accept-Charset", "UTF-8");
                    http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=\"utf-8\"");
                    http.setRequestProperty("Accept", "*/*");
                    http.setRequestProperty("Proxy-Connection", "close");
                    http.setRequestProperty("Connection", "Keep-Alive");
                    

                    getConnectionInformation(http);
                    OutputStreamWriter out= new OutputStreamWriter(http.openOutputStream(),"utf-8");
//                    http.openOutputStream().write(("sl=" + str1 + "&tl=" + str2 + "&client=t&text=" + text).getBytes());
//                    http.openOutputStream().close();
                    out.write(("sl=" + str1 + "&tl=" + str2 + "&client=t&text=" + text));


                    StringBuffer sb = new StringBuffer();
                    
                    InputStream is = http.openDataInputStream();
                    Reader in = new InputStreamReader(is, "utf-8");
                    StringBuffer temp = new StringBuffer(1024);
                    char[] buffer = new char[1024];
                    int read;
                    while ((read = in.read(buffer, 0, buffer.length)) != -1) {
                    temp.append(buffer, 0, read);
                    }
                    sb.append(temp.toString());
                     
                 /*   String str = "";
                    InputStream inputstream = http.openInputStream();
                    int length = (int) http.getLength();
                    //System.out.println(length);

                    if (length != -1) {
                        byte incomingData[] = new byte[length];
                        inputstream.read(incomingData);
                        str = getUnicodeWall(incomingData);
                    } else {
                        ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
                        int ch;
                        while ((ch = inputstream.read()) != -1) {
                            bytestream.write(ch);
                        }
                        str = new String(getUnicodeWall(bytestream.toByteArray()));
                        bytestream.close();
                    }
                  * 
                  */
                    System.out.println(""+sb);
                    resuilt = sb.toString().substring(4);
                    resuilt = resuilt.substring(0, resuilt.indexOf("\""));
                    
                    //final JSONObject json = new JSONObject(str);
                    //final String translatedText = ((JSONObject) json.get("responseData")).getString("translatedText");

                    
                } catch (Exception localException) {
                    localException.printStackTrace();
                }

            }
        } catch (Exception ex) {
            System.out.println(" loi ...");
            ex.printStackTrace();
        }
        System.out.println(" ket thuc");

        return resuilt;
    }

    public static void getConnectionInformation(HttpConnection hc) {

        System.out.println("Request Method for this connection is " + hc.getRequestMethod());
        System.out.println("URL in this connection is " + hc.getURL());
        System.out.println("Protocol for this connection is " + hc.getProtocol()); // It better be HTTP:)
        System.out.println("This object is connected to " + hc.getHost() + " host");
        System.out.println("HTTP Port in use is " + hc.getPort());
        System.out.println("Query parameter in this request are  " + hc.getQuery());

    }

    public static String getUnicodeWall(byte buf[]) {
        StringBuffer buffer = null;
        InputStream is = null;
        InputStreamReader isr = null;
        //System.out.println("Before Unicode = " + new String(buf));
        try {
            is = new ByteArrayInputStream(buf);
            if (is == null) {
                throw new Exception("ByteArrayInputStream is Empty");
            }
            isr = new InputStreamReader(is, "UTF-8");
            buffer = new StringBuffer();
            int ch;
            while ((ch = isr.read()) > -1) {
                if (ch == (int) '\\') {
                    String strInt = "";
                    for (int i = 0; i < 6; i++) { //skip a unicode eg. \u0026#39;
                        isr.read();
                    }
                    for (int i = 0; i < 2; i++) { //skip a unicode eg. \u0026#39;
                        strInt += (char) isr.read();
                    }
                    isr.read();
                    //System.out.println("." + myHexString + ".");
                    int i = Integer.parseInt(strInt);
                    buffer.append((char) i);
                } else {
                    buffer.append((char) ch);
                }
            }
            if (isr != null) {
                isr.close();
            }
        } catch (Exception ex) {
            //System.out.println(ex);
        }
        //System.out.println("After Unicode = " + buffer.toString());

        return buffer.toString();
    }

    private static String enCodeWall(String s) {

        StringBuffer sbuf = new StringBuffer();
        int ch;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            switch (ch) {
                case ' ': {
                    sbuf.append("+");
                    break;
                }
                case '!': {
                    sbuf.append("%21");
                    break;
                }
                case '*': {
                    sbuf.append("%2A");
                    break;
                }
                case '\'': {
                    sbuf.append("%27");
                    break;
                }
                case '(': {
                    sbuf.append("%28");
                    break;
                }
                case ')': {
                    sbuf.append("%29");
                    break;
                }
                case ';': {
                    sbuf.append("%3B");
                    break;
                }
                case ':': {
                    sbuf.append("%3A");
                    break;
                }
                case '@': {
                    sbuf.append("%40");
                    break;
                }
                case '&': {
                    sbuf.append("%26");
                    break;
                }
                case '=': {
                    sbuf.append("%3D");
                    break;
                }
                case '+': {
                    sbuf.append("%2B");
                    break;
                }
                case '$': {
                    sbuf.append("%24");
                    break;
                }
                case ',': {
                    sbuf.append("%2C");
                    break;
                }
                case '/': {
                    sbuf.append("%2F");
                    break;
                }
                case '?': {
                    sbuf.append("%3F");
                    break;
                }
                case '%': {
                    sbuf.append("%25");
                    break;
                }
                case '#': {
                    sbuf.append("%23");
                    break;
                }
                case '[': {
                    sbuf.append("%5B");
                    break;
                }
                case ']': {
                    sbuf.append("%5D");
                    break;
                }
                default:
                    sbuf.append((char) ch);
            }
        }
        return sbuf.toString();
    }
}