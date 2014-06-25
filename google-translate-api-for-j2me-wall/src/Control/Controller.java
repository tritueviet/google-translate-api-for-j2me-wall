/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import event.Event;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.Image;
import views.LoadScreen;
import views.ViewHome;

/**
 *
 * @author TRITUEVIET
 */
public class Controller {

    private static Controller instance = null;
    private Image icon;
    private Main main;
    public static  int size=401;
    public ViewHome viewHome;
    public static Vector items=null;
    private Controller() {

    }

     public void loadData() {
        items = new Vector();
        DataInputStream data = new DataInputStream(getClass().getResourceAsStream("/models/word.wall"));
        try {
            for (int i = 0; i < 3466; i++) {
                items.addElement(data.readUTF());
            }
            data.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
            for (int i = 0; i < 3466; i++) {
                System.out.println("\n"+items.elementAt(i).toString());
            }
    }
    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public void chay(Main main) {
        this.main = main;
        if (0 == 0) {
            LoadScreen load = new LoadScreen(main);
            javax.microedition.lcdui.Display.getDisplay(main).setCurrent(load);
            load.start();
            load = null;
        }
        loadData();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        //  show in here
        showHome();
    }


    public void handleEvent(int eventType, Event evt) {
        switch (eventType) {

        }
    }

    public void exit() {
        //   saveConfig();
        main.destroyApp(true);
    }

    public void showAbout() {
        // hiển thị giới thiệu
    }
    public void showHome() {
        viewHome = new ViewHome();
        viewHome.show();
    }
    
}