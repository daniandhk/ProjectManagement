/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Database.Database;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dania
 */
public class Application {
    private ArrayList<Manager> listManager;
    private ArrayList<Programmer> listProgrammer;
    private ArrayList<Client> listClient;
    private ArrayList<Project> listProject;
    private ArrayList<Tugas> listTugas;
    Database db;
    
    public Application(){
        listManager = new ArrayList();
        listProgrammer = new ArrayList();
        listClient = new ArrayList();
        db=new Database();
        db.connect();
    }
    
    public void setClientAddProject(Project p){
        getClient(p.getIdClient()).addDaftarProject(p);
    }
    
    public void setProjectAddTim(Programmer p){
        try{
            if(p.getIdProject()!="null"){
                getProject(p.getIdProject()).setTim(p);
            }
        }catch(Exception e){
            System.out.println("Kosong");
        }
    }
    
    public void setProjectAddTugas(Tugas t){
        getProject(t.getIdProject()).setTugas(t);
    }
    //ini nge assign relasi
    public void loadClientListProject(){
        for(int i=0;i<listProject.size();i++){
            setClientAddProject(listProject.get(i));
        }
    }
    
    public void loadProjectListTim(){
        for(int i=0;i<listProgrammer.size();i++){
            if(listProgrammer.get(i).getIdProject()!="null"){
                setProjectAddTim(listProgrammer.get(i));
            }
        }
    }
    
    public void loadProjectListTugas(){
        for(int i=0;i<listTugas.size();i++){
            setProjectAddTugas(listTugas.get(i));
        }
    }
    
    public Programmer getProgrammer(String id){
        int i = 0;
        boolean found = false;
        while((i<listProgrammer.size())&&(!found)){
            if(((listProgrammer.get(i)).getId()).equals(id)){
                found=true;
            }
            else{
                i++;
            }
        }
        if(found){
            return(listProgrammer.get(i));
        }
        else{
            return null;
        }
    }
    
    public Manager getManager(String id){
        int i = 0;
        boolean found = false;
        while ((i < listManager.size()) && (!found)){
            if (((listManager.get(i)).getId()).equals(id)){
                found=true;
            }else{
                i++;
            }
        }
        if (found){
            return (listManager.get(i));
        }
        else{
            return null;
        }
    }
    
    public Client getClient(String id){
        int i = 0;
        boolean found = false;
        while ((i < listClient.size()) && (!found)){
            if (((listClient.get(i)).getId()).equals(id)){
                found=true;
            }else{
                i++;
            }
        }
        if (found){
            return (listClient.get(i));
        }
        else{
            return null;
        }
    }
    
    public Project getProject(String id){
        int i = 0;
        boolean found = false;
        while ((i < listProject.size()) && (!found)){
            if (((listProject.get(i)).getId()).equals(id)){
                found=true;
            }else{
                i++;
            }
        }
        if (found){
            return (listProject.get(i));
        }
        else{
            return null;
        }
    }
    
    public Tugas getTugas(String id){
        int i = 0;
        boolean found = false;
        while ((i < listTugas.size()) && (!found)){
            if (((listTugas.get(i)).getId()).equals(id)){
                found=true;
            }else{
                i++;
            }
        }
        if (found){
            return (listTugas.get(i));
        }
        else{
            return null;
        }
    }
    
    public ArrayList<Manager> getListManager(){
        return this.listManager;
    }
    
    public ArrayList<Programmer> getListProgrammer(){
        return this.listProgrammer;
    }
    
    public ArrayList<Client> getListClient(){
        return this.listClient;
    }

    public ArrayList<Project> getListProject() {
        return listProject;
    }

    public ArrayList<Tugas> getListTugas() {
        return listTugas;
    }
    
    public void setListManager(Manager m) {
        listManager.add(m);
        db.saveManager(m);
    }

    public void setListProgrammer(Programmer p) {
        listProgrammer.add(p);
        db.saveProgrammer(p);
    }

    public void setListClient(Client c) {
        listClient.add(c);
        db.saveClient(c);
    }
    
    public void setListProject(Project c) {
        listProject.add(c);
        db.saveProject(c);
    }
    
    public void setListTugas(Tugas c) {
        listTugas.add(c);
        db.saveTugas(c);
    }
    
    public String[] getManagerListId(){
        String[] listId=new String[listManager.size()];
        for (int i=0;i<listManager.size();i++){
            listId[i]=listManager.get(i).getId();
        }
        return listId;
    }
    
    public String[] getProgrammerListId(){
        String[] listId=new String[listProgrammer.size()];
        for (int i=0;i<listProgrammer.size();i++){
            listId[i]=listProgrammer.get(i).getId();
        }
        return listId;
    }
    
    public String[] getProgrammerListId(String idProject){
        String[] listId=new String[listProgrammer.size()];
        for (int i=0;i<listProgrammer.size();i++){
            if(!listProgrammer.get(i).getIdProject().equals(null)){
                if(listProgrammer.get(i).getIdProject().equals(idProject)){
                    listId[i]=listProgrammer.get(i).getId();
                }
            }
        }
        return listId;
    }
    
    public String[] getProgrammerListIdNot(String idProject){
        String[] listId=new String[listProgrammer.size()];
        for (int i=0;i<listProgrammer.size();i++){
            if(!listProgrammer.get(i).getIdProject().equals(null)){
                if(!listProgrammer.get(i).getIdProject().equals(idProject)){
                    listId[i]=listProgrammer.get(i).getId();
                } 
            } else{
                listId[i]=listProgrammer.get(i).getId();
            }
        }
        return listId;
    }
    
    public String[] getClientListId(){
        String[] listId=new String[listClient.size()];
        for (int i=0;i<listClient.size();i++){
            listId[i]=listClient.get(i).getId();
        }
        return listId;
    }
    
    public String[] getProjectListId(String idManager){
        String[] listId=new String[listProject.size()];
        for (int i=0;i<listProject.size();i++){
            if(listProject.get(i).getIdManager().equals(idManager)){
                listId[i]=listProject.get(i).getId();
            }
        }
        return listId;
    }
    
    public String[] getProjectListId1(String idClient){
        String[] listId=new String[listProject.size()];
        for (int i=0;i<listProject.size();i++){
            if(listProject.get(i).getIdClient().equals(idClient)){
                listId[i]=listProject.get(i).getId();
            }
        }
        return listId;
    }
    
    public String[] getProjectListId(){
        String[] listId=new String[listProject.size()];
        for (int i=0;i<listProject.size();i++){
            listId[i]=listProject.get(i).getId();
        }
        return listId;
    }
    
    public String[] getTugasListId(String idProgrammer){
        String[] listId=new String[listTugas.size()];
        for (int i=0;i<listTugas.size();i++){
            if(!listTugas.get(i).getIdProgrammer().equals(null)){
                if(listTugas.get(i).getIdProgrammer().equals(idProgrammer)){
                    listId[i]=listTugas.get(i).getId();
                }
            }
            
        }
        return listId;
    }
    
    public String[] getTugasListId1(String idProject){
        String[] listId=new String[listTugas.size()];
        for (int i=0;i<listTugas.size();i++){
            int j = 0;
            while(j<listTugas.size()&&!listTugas.get(i).getIdProject().equals(idProject)){
                j++;
            }
            if(listTugas.get(i).getIdProject().equals(idProject)){
                listId[i]=listTugas.get(i).getId();
            } 
            
        }
        return listId;
    }
    
    public String[] getTugasListIdNot(String idProject){
        String[] listId=new String[listTugas.size()];
        for (int i=0;i<listTugas.size();i++){
            int j = 0;
            while(j<listTugas.size()&&listTugas.get(i).getIdProject().equals(idProject)){
                j++;
            }
            if(!listTugas.get(i).getIdProject().equals(idProject)){
                listId[i]=listTugas.get(i).getId();
            } 
            
        }
        return listId;
    }
    
    public String[] getTugasListId(){
        String[] listId=new String[listTugas.size()];
        for (int i=0;i<listTugas.size();i++){
            listId[i]=listTugas.get(i).getId();
        }
        return listId;
    }
    //load array
    public void loadManagers(){
        listManager = db.loadAllManagers();
    }
    
    public void loadProgrammers(){
        listProgrammer = db.loadAllProgrammers();
    }
    
    public void loadClients(){
        listClient = db.loadAllClients();
    }
    
    public void loadProjects(){
        listProject = db.loadAllProjects();
    }
    
    public void loadTugas(){
        listTugas = db.loadAllTugas();
    }
    
    public int newManagerId(){
        if(listManager.size()==0){
            return 1;
        }
        else{
            String lastId=listManager.get(listManager.size()-1).getId();
            String lastNumId=lastId.substring(2);
            int lastNoId=Integer.parseInt(lastNumId);
            return lastNoId+1;
        }
    }
    
    public int newProgrammerId(){
        if(listProgrammer.size()==0){
            return 1;
        }
        else{
            String lastId=listProgrammer.get(listProgrammer.size()-1).getId();
            String lastNumId=lastId.substring(2);
            int lastNoId=Integer.parseInt(lastNumId);
            return lastNoId+1;
        }
    }
    
    public int newClientId(){
        if(listClient.size()==0){
            return 1;
        }
        else{
            String lastId=listClient.get(listClient.size()-1).getId();
            String lastNumId=lastId.substring(2);
            int lastNoId=Integer.parseInt(lastNumId);
            return lastNoId+1;
        }
    }
    
    public int newProjectId(){
        if(listProject.size()==0){
            return 1;
        }
        else{
            String lastId=listProject.get(listProject.size()-1).getId();
            String lastNumId=lastId.substring(5);
            int lastNoId=Integer.parseInt(lastNumId);
            return lastNoId+1;
        }
    }
    
    public int newTugasId(){
        if(listTugas.size()==0){
            return 1;
        }
        else{
            String lastId=listTugas.get(listTugas.size()-1).getId();
            String lastNumId=lastId.substring(2);
            int lastNoId=Integer.parseInt(lastNumId);
            return lastNoId+1;
        }
    }
    
    public void updateManager(String id, String nama, double gaji){
        int i=0;
        while(i<listManager.size()&&!listManager.get(i).getId().equals(id)){
            i++;
        }
        if(listManager.get(i).getId().equals(id)){
            listManager.get(i).setNama(nama);
            listManager.get(i).setGaji(gaji);
            db.updateManager(id, nama, gaji);
        }
    }
    
    public void updateProgrammer(String id,String nama, double gaji){
        int i=0;
        while(i<listProgrammer.size()&&!listProgrammer.get(i).getId().equals(id)){
            i++;
        }
        if(listProgrammer.get(i).getId().equals(id)){
            listProgrammer.get(i).setNama(nama);
            listProgrammer.get(i).setGaji(gaji);
            db.updateProgrammer(id, nama, gaji);
        }
    }
    
    public void updateProgrammer(String id,String nama, double gaji,String idProject){
        int i=0;
        while(i<listProgrammer.size()&&!listProgrammer.get(i).getId().equals(id)){
            i++;
        }
        if(listProgrammer.get(i).getId().equals(id)){
            listProgrammer.get(i).setNama(nama);
            listProgrammer.get(i).setGaji(gaji);
            listProgrammer.get(i).setIdProject(idProject);
            db.updateProgrammer(id, nama, gaji,idProject);
        }
    }
    
    public void updateClient(String id, String nama){
        int i=0;
        while(i<listClient.size()&&!listClient.get(i).getId().equals(id)){
            i++;
        }
        if(listClient.get(i).getId().equals(id)){
            listClient.get(i).setNama(nama);
            db.updateClient(id, nama);
        }
    }
    
    public void updateProject(String id, String deadline, String idManager){
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date date = format.parse(deadline);
            int i=0;
            while(i<listProject.size()&&!listProject.get(i).getId().equals(id)){
                i++;
            }
            if(listProject.get(i).getId().equals(id)){
                listProject.get(i).setDeadline(date);
                listProject.get(i).setManager(getManager(idManager));
                db.updateProject(id, date, idManager);
            }
        } catch (ParseException ex) {
            System.out.println("input error");;
        }
    }
    
    public void updateTugas(String id, String status, String idProgrammer){
        int i=0;
        while(i<listTugas.size()&&!listTugas.get(i).getId().equals(id)){
            i++;
        }
        if(listTugas.get(i).getId().equals(id)){
            listTugas.get(i).setStatus(status);
            listTugas.get(i).setPelaksana(getProgrammer(idProgrammer));
            db.updateTugas(id, status, idProgrammer);
        }
    }
    
    public void deleteManager(Manager m){
        listManager.remove(m);
        db.deleteManager(m.getId());
    }
    
    public void deleteProgrammer(Programmer p){
        listProgrammer.remove(p);
        db.deleteProgrammer(p.getId());
    }
    
    public void deleteClient(Client c){
        listClient.remove(c);
        db.deleteClient(c.getId());
    }
    
    public void deleteProject(Project p){
        listProject.remove(p);
        db.deleteProject(p.getId());
    }
    
    public void deleteTugas(Tugas t){
        listTugas.remove(t);
        db.deleteTugas(t.getId());
    }
    
    public void removeProgrammer(String id){
        getProgrammer(id).setIdProject("null");
        db.removeProgrammerProject(id);
    }
    
    public void changeStatus(String id){
        getTugas(id).setStatus("finished");
        db.changeStatus(id);
    }
    
    public void addAvailabe(Project p, Programmer pro){
        pro.setIdProject(p.getId());
        db.addAvailable(p.getId(), pro.getId());
    }
}
