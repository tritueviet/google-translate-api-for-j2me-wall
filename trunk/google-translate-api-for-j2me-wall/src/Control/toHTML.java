package Control;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author boyxauzai
 */
public class toHTML {

    public toHTML() {
    }

    public static String translateVNSPEAK(String s) {
        s = s.toLowerCase();
        String t = "";
        String t1[] = {" ", "à", "á", "ả", "ã", "ạ", "ă", "ằ", "ắ", "ẳ", "ẵ", "ặ", "â", "ầ", "ấ", "ẩ", "ẫ", "ậ", "đ",
            "è", "é", "ẻ", "ẽ", "ẹ", "ê", "ề", "ế", "ể", "ễ", "ệ", "ì", "í", "ỉ", "ĩ", "ị", "ò", "ó",
            "ỏ", "õ", "ọ", "ô", "ồ", "ố", "ổ", "ỗ", "ộ", "ơ", "ờ", "ớ", "ở", "ỡ", "ợ", "ù", "ú", "ủ", "ũ",
            "ụ", "ư", "ừ", "ứ", "ử", "ữ", "ự", "ỳ", "ý", "ỷ", "ỹ", "ỵ"};
        String t2[] = {"%20", "%C3%A0", "%C3%A1", "%E1%BA%A3", "%C3%A3", "%E1%BA%A1", "%C4%83", "%E1%BA%B1",
            "%E1%BA%AF", "%E1%BA%B3", "%E1%BA%B5", "%E1%BA%B7", "%C3%A2", "%E1%BA%A7", "%E1%BA%A5", 
            "%E1%BA%A9", "%E1%BA%AB", "%E1%BA%AD", "%C4%91", "%C3%A8", "%C3%A9", "%E1%BA%BB", 
            "%E1%BA%BD", "%E1%BA%B9", "%C3%AA", "%E1%BB%81", "%E1%BA%BF", "%E1%BB%83", "%E1%BB%85", 
            "%E1%BB%87", "%C3%AC", "%C3%AD", "%E1%BB%89", "%C4%A9", "%E1%BB%8B", "%C3%B2", "%C3%B3", 
            "%E1%BB%8F", "%C3%B5", "%E1%BB%8D", "%C3%B4", "%E1%BB%93", "%E1%BB%91", "%E1%BB%95", 
            "%E1%BB%97", "%E1%BB%99", "%C6%A1", "%E1%BB%9D", "%E1%BB%9B", "%E1%BB%9F", "%E1%BB%A1",
            "%E1%BB%A3", "%C3%B9", "%C3%BA", "%E1%BB%A7", "%C5%A9", "%E1%BB%A5", "%C6%B0", "%E1%BB%AB", 
            "%E1%BB%A9", "%E1%BB%AD", "%E1%BB%AF", "%E1%BB%B1", "%E1%BB%B3", 
            "%C3%BD", "%E1%BB%B7", "%E1%BB%B9", "%E1%BB%B5"};
        for (int i = 0; i < s.length(); i++) {
            boolean kt = true;
            for (int j = 0; j < t1.length; j++) {
                if (s.substring(i, i+1).equals(t1[j])) {
                    t += t2[j];
                    kt = false;
                    break;
                }
            }
            if (kt) {
                t += s.substring(i, i+1);
            }
        }
        return t;
    }

    public static String translate(String s) {
        s = s.toLowerCase();
        String t = "";
        String t1[] = {" ", "à", "á", "ả", "ã", "ạ", "ă", "ằ", "ắ", "ẳ", "ẵ", "ặ", "â", "ầ", "ấ", "ẩ", "ẫ", "ậ", "đ",
            "è", "é", "ẻ", "ẽ", "ẹ", "ê", "ề", "ế", "ể", "ễ", "ệ", "ì", "í", "ỉ", "ĩ", "ị", "ò", "ó",
            "ỏ", "õ", "ọ", "ô", "ồ", "ố", "ổ", "ỗ", "ộ", "ơ", "ờ", "ớ", "ở", "ỡ", "ợ", "ù", "ú", "ủ", "ũ",
            "ụ", "ư", "ừ", "ứ", "ử", "ữ", "ự", "ỳ", "ý", "ỷ", "ỹ", "ỵ"};
        String t2[] = {"%20", "%C3%A0", "%C3%A1", "%E1%BA%A3", "%C3%A3", "%E1%BA%A1", "%C4%83", "%E1%BA%B1",
            "%E1%BA%AF", "%E1%BA%B3", "%E1%BA%B5", "%E1%BA%B7", "%C3%A2", "%E1%BA%A7",
            "%E1%BA%A5", "%E1%BA%A9", "%E1%BA%AB", "%E1%BA%AD", "%C4%91", "%C3%A8", "%C3%A9",
            "%E1%BA%BB", "%E1%BA%BD", "%E1%BA%B9", "%C3%AA", "%E1%BB%81", "%E1%BA%BF",
            "%E1%BB%83", "%E1%BB%85", "%E1%BB%87", "%C3%AC", "%C3%AD", "%E1%BB%89", "%C4%A9",
            "%E1%BB%8B", "%C3%B2", "%C3%B3", "%E1%BB%8F", "%C3%B5", "%E1%BB%8D", "%C3%B4",
            "%E1%BB%93", "%E1%BB%91", "%E1%BB%95", "%E1%BB%97", "%E1%BB%99", "%C6%A1",
            "%E1%BB%9D", "%E1%BB%9B", "%E1%BB%9F", "%E1%BB%A1", "%E1%BB%A3", "%C3%B9",
            "%C3%BA", "%E1%BB%A7", "%C5%A9", "%BB%A5", "%C6%B0", "%E1%BB%AB", "%E1%BB%A9",
            "%E1%BB%AD", "%E1%BB%AF", "%E1%BB%B1", "%E1%BB%B3", "%C3%BD", "%E1%BB%B7", "%E1%BB%B9", "%E1%BB%B5"};
        for (int i = 0; i < s.length(); i++) {
            boolean kt = true;
            for (int j = 0; j < t1.length; j++) {
                if (s.substring(i, i+1).equals(t1[j])) {
                    t += t2[j];
                    kt = false;
                    break;
                }
            }
            if (kt) {
                t += s.substring(i, i+1);
            }
        }
        return t;
    }
}
