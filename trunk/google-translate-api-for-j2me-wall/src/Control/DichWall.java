package Control;



import models.Language;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;


/**
 * Makes the Google Translate API available to Java ME applications.
 * @author Jinath Sanjitha
 * @author Richard Midwinter
 */
public class DichWall {

    private static final String URL_STRING2 = "http://ajax.googleapis.com/ajax/services/language/translate?v=1.0&langpair=";
//    private static final String URL_STRING = "http://translate.google.com/translate_tts?q=" + "en" + "&tl=" + str2";
    
    private static final String TEXT_VAR2 = "&q=";
    private static final String URL_STRING_DETECT_LANGUAGE2 = "http://ajax.googleapis.com/ajax/services/language/detect?v=1.0"; // This the detect language connection string to WebService.
    public static String AUTO_DETECTED_LANGUAGE2 = "";

    /**
     * Translates text from a given language to another given language using Google Translate API
     *
     * @param text The String to translate.
     * @param from The language code to translate from.
     * @param to The language code to translate to.
     * @return The translated String.
     * @throws IOException
     */
    
    public DichWall() {
    }

    
    public static String dichWall(String text, String from, String to) throws Exception {
        text = text.toLowerCase();
        from = from.toLowerCase();
        to = to.toLowerCase();
        HttpConnection connection = null;
        InputStream inputstream = null;
        String str = "";
        try {
            String url = URL_STRING2 + from + "%7C" + to + TEXT_VAR2 + enCodeWall(text);

            connection = (HttpConnection) Connector.open(url);
            connection.setRequestMethod(HttpConnection.GET);
            //System.out.println("Status Line Code: " + connection.getResponseCode());
            //System.out.println("Status Line Message: " + connection.getResponseMessage());

            if (connection.getResponseCode() == HttpConnection.HTTP_OK) {
                //System.out.println("Connection OK");
                inputstream = connection.openInputStream();
                int length = (int) connection.getLength();
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
                //System.out.println("response =" + str);

                if (from.equals(Language.AUTO_DETECT)) { //Find the autodetected language
                    AUTO_DETECTED_LANGUAGE2 = getAutoDetectedLanguage(text);
                     //System.out.println("AUTO_DETECTED_LANGUAGE2 = " + AUTO_DETECTED_LANGUAGE2);
                }
                
                
                return str;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new Exception("[google-api-translate-javame] Error retrieving translation.");
        } finally {
            if (inputstream != null) {
                try {
                    inputstream.close();
                } catch (IOException ex) {
                    /*log error*/
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (IOException ex) {
                    /*log error*/
                }
            }
        }
        return str;
    }

    /**
     * Process and Return the Unicode text from the received data
     *
     * @param buf byte array
     * @return Processed String.
     */
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

    /**
     * This static method detected language and return language string code.
     * Contributet by Kamil MyÅ›liwiec
     *
     * @param text provided to translate
     * @return The Auto detected language Code
     * @throws Exception "Error getting the Auto detected language"
     */
    public static String getAutoDetectedLanguage(String text) throws Exception {

        String url = URL_STRING_DETECT_LANGUAGE2 + TEXT_VAR2 + enCodeWall(text);
        StringBuffer sb = new StringBuffer();

        HttpConnection connection = (HttpConnection) Connector.open(url);
        connection.setRequestMethod(HttpConnection.GET);

        // If connection is OK
        if (connection.getResponseCode() == HttpConnection.HTTP_OK) {

            InputStream is = connection.openInputStream();
            int character = 0;
            
            while (character != -1) { // Buffer the data
                character = is.read();
                if (character != -1) {
                    sb.append((char) character);
                }
            }
        } else {
            throw new Exception("[google-api-translate-javame] Error getting the Auto detected language");
        }

        // Return the language String code
        return sb.toString();
    }

    /**
     * Encode the text to be translated.
     * This is a light-weigt implementation of URLEncoder.encode(String s, "UTF-8") in JavaSE.
     *
     * @param s The String to be encoded.
     * @return The encoded String.
     */
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