/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author dania
 */
public class Project {
    private Manager manager;
    private Date deadline;
    private String id;
    private ArrayList<Programmer> tim;
    private ArrayList<Tugas> tugas;
    private String idClient;
    
    public Project(Date deadline,Manager manager, String id){
        this.deadline = deadline;
        this.manager = manager;
        tim = new ArrayList();
        tugas = new ArrayList();
        this.id = id;
    }
    
    public Project(Date deadline,Manager manager, String id,String idClient){
        this.deadline = deadline;
        this.manager = manager;
        tim = new ArrayList();
        tugas = new ArrayList();
        this.id = id;
        setIdClient(idClient);
    }

    public ArrayList<Programmer> getTim() {
        return tim;
    }

    public ArrayList<Tugas> getTugas() {
        return tugas;
    }
    
    public void setTim(Programmer p){
        tim.add(p);
    }
    
    public void setTugas(Tugas t){
        tugas.add(t);
    }
    
    public String getIdManager(){
        return manager.getId();
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }
    
    public String toString(){
        String s = "ID: "+id+"\nManager: "+manager+"\nDeadline: "+deadline+"\nProgrammer: ";
        for(int i=0;i<tim.size();i++){
            s = s+"\n"+tim.get(i).getId();
        }
        s = s+"\nTugas: ";
        for(int i=0;i<tugas.size();i++){
            s = s+"\n"+tugas.get(i).getId();
        }
        return s;
    }
}
