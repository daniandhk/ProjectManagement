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
public class Tugas {
    private Programmer pelaksana;
    private String id;
    private String status;
    private String idProject;
    
    public Tugas(Programmer pelaksana,String status,String id){
        this.pelaksana = pelaksana;
        this.id = id;
        this.status = status;
    }
    
    public Tugas(Programmer pelaksana,String status,String id,String idProject){
        this.pelaksana = pelaksana;
        this.id = id;
        this.status = status;
        this.idProject = idProject;
    }
    
    public String getIdProgrammer(){
        return pelaksana.getId();
    }

    public String getIdProject() {
        return idProject;
    }

    public void setIdProject(String idProject) {
        this.idProject = idProject;
    }

    public Programmer getPelaksana() {
        return pelaksana;
    }

    public void setPelaksana(Programmer pelaksana) {
        this.pelaksana = pelaksana;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String toString(){
        return "ID: "+id+"\nStatus: "+status;
    }
}
