package Control;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.UnsupportedEncodingException;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.*;

/**
 * @author TRITUEVIET
 */
public class Main extends MIDlet {

    public Main() {
    }

    public void startApp() {
        //Controller.getInstance().chay(this);
        String s = "của";
        System.out.println("  " + s);
        s = Translate.Dich("your mouse is", models.Language.ENGLISH, models.Language.VIETNAMESE);
        System.out.println("kq    :   " + s);

        Alert a = new Alert("");
        a.setString(s);
        Form aa = new Form(s);
        TextField texF = new TextField(s, "", 1000, TextField.ANY);
        try {
            texF.setString(s);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        aa.append(texF);



        try {
            PlayMp3.play2(s, models.Language.VIETNAMESE);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("....");

        }
        Display.getDisplay(this).setCurrent(aa);

    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
        notifyDestroyed();
    }
}
