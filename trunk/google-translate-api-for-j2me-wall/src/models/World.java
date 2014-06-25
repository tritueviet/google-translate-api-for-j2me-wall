/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author TRITUEVIET
 */
public class World{
    String ten;
    String loai;
    String phienAm;
    String nghia;

    public World(String ten, String loai, String phienAm, String nghia) {
        this.ten = ten;
        this.loai = loai;
        this.phienAm = phienAm;
        this.nghia = nghia;
    }

    public World() {
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getPhienAm() {
        return phienAm;
    }

    public void setPhienAm(String phienAm) {
        this.phienAm = phienAm;
    }

    public String getNghia() {
        return nghia;
    }

    public void setNghia(String nghia) {
        this.nghia = nghia;
    }

    public void xuat(){
        System.out.println(""+ten+" "+loai+" "+phienAm+" "+nghia);
    }
}
