package Control;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.io.file.FileConnection;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.media.control.VolumeControl;

public class PlayMp3 {

    private static HttpConnection http;
    private static InputStream inputStream;
    private static Player play;
    private static StringBuffer buf;
    String str2 = "en";

    public PlayMp3() {
    }

    //  trả về 0 là lỗi trả về 1 là ok
    public int playaudio(String s, String lang) {



        try {
            String url = "";
            if (lang.equals("vi")) {
                s = toHTML.translateVNSPEAK(s);
                url = "http://www.vnspeak.com/speakit.php?txt=" + s;
            } else {
                s = toHTML.translate(s);
                url = "http://translate.google.com/translate_tts?q=" + s + "&tl=" + lang;
            }


            System.out.println("urrl:  " + url);


            (http = (HttpConnection) Connector.open(url)).setRequestMethod("GET");
            if (!lang.equals("vi")) {
                http.setRequestProperty("Host", "translate.google.com");
            } else {
                http.setRequestProperty("Host", "www.vnspeak.com");
            }

            http.setRequestProperty("Accept-Language", str2);
            http.setRequestProperty("User-Agent", "Opera/9.64");

            buf = new StringBuffer();
            inputStream = http.openInputStream();
            int i;
            while ((i = inputStream.read()) != -1) {
                buf.append((char) i);
            }

            String localObject = buf.toString();
            saveImage2File(localObject.getBytes(), "chay");
            http.close();

            str2 = "audio/mp3";

            try {
                if (play != null);
                play = Manager.createPlayer(System.getProperty("fileconn.dir.photos") + "chay.mp3");
                play.realize();
                play.prefetch();
                System.out.println("play");
                play.start();
            } catch (Exception localException) {
                System.out.println("loi ...");
                return 0;
            }
        } catch (IOException localIOException) {
            return 0;
        }
        return 1;

    }

    public static int play2(String s, String lang) {

        try {
            String url = "";
            if (lang.equals("vi")) {
                s = toHTML.translateVNSPEAK(s);
                url = "http://www.vnspeak.com/speakit.php?txt=" + s;
            } else {
                s = toHTML.translate(s);
                url = "http://translate.google.com/translate_tts?q=" + s + "&tl=" + lang;
            }
            System.out.println("urrl:  " + url);

            try {
                
                HttpConnection conn = (HttpConnection) Connector.open(url,
                        Connector.READ_WRITE);
                InputStream is = conn.openInputStream();
                play = Manager.createPlayer(is, "audio/mpeg");

                play.realize();
                // get volume control for player and set volume to max
               VolumeControl vc = (VolumeControl) play.getControl("VolumeControl");
                if (vc != null) {
                    vc.setLevel(100);
                }
                play.prefetch();
                play.start();
            } catch (Exception e) {
            }
            

        } catch (Exception localIOException) {
            return 0;
        }
        return 1;
    }

    public void saveImage2File(final byte[] photo, final String text) {
        try {
            // Receive a photo as byte array 
            // Save Image to file
            FileConnection fileConn = null;
            DataOutputStream dos = null;
            //fileConn = (FileConnection) Connector.open("file:///E:/"+text+".gif");

            fileConn = (FileConnection) Connector.open(System.getProperty("fileconn.dir.photos") + text + ".mp3");

            if (!fileConn.exists()) {
                fileConn.create();
            }

            dos = new DataOutputStream(fileConn.openOutputStream());
            dos.write(photo);

            dos.flush();
            dos.close();
            fileConn.close();

            Runtime.getRuntime().gc();
        } catch (Exception e) {
            System.out.println("Error!" + e.getMessage());
        }
    }
}
