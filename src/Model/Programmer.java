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
public class Programmer extends Orang {
    private String idProject = "null";
    
    public Programmer(String nama, double gaji, String id){
        super(nama,gaji,id);
        setIdProject("null");
    }
    
    public Programmer(String nama, double gaji, String id, String idProject){
        super(nama,gaji,id);
        setIdProject(idProject);
    }

    public String getIdProject() {
        return idProject;
    }

    public void setIdProject(String idProject) {
        this.idProject = idProject;
    }
    
}
