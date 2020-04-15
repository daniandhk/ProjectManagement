/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author dania
 */
public abstract class Orang {
    private String nama;
    private double gaji;
    private String id;
    
    public Orang(String nama, double gaji, String id){
        this.nama = nama;
        this.gaji = gaji;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getGaji() {
        return gaji;
    }

    public void setGaji(double gaji) {
        this.gaji = gaji;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public String toString(){
        return "ID: "+id+"\nName: "+nama+"\nGaji: Rp. "+gaji;
    }
}
