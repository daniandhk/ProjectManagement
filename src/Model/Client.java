/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author dania
 */
public class Client {
    private ArrayList<Project> daftarProject;
    private String nama;
    private String id;
    
    public Client(String nama, String id){
        this.nama = nama;
        this.id = id;
        daftarProject = new ArrayList();
    }

    public void addDaftarProject(Project p) {
        daftarProject.add(p);
    }

    public ArrayList<Project> getDaftarProject() {
        return daftarProject;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public Project getProjectIdx(int i){
        return daftarProject.get(i);
    }
    
    public String toString(){
        return "ID: "+id+"\nName: "+nama;
    }
}
