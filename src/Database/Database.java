/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Model.Client;
import Model.Manager;
import Model.Programmer;
import Model.Project;
import Model.Tugas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author dania
 */
public class Database {
    private Connection con;
    
    public void connect(){
        try{
            String url="jdbc:mysql://localhost:3306/proyek";
            String username="root";
            String password="";
            con=DriverManager.getConnection(url,username,password);
            System.out.println("Connected to database.");
        }
        catch(SQLException se){
            System.out.println("Connection error.");
        }
    }
    
    public void saveManager(Manager m){
        try{
            String query="insert into manager values('"+m.getId()+"','"+m.getNama()+"','"+m.getGaji()+"');";
            Statement s=con.createStatement();
            s.execute(query);
            System.out.println("Saving success");
        }
        catch(SQLException se){
            System.out.println("Saving error");
        }
    }
    
    public void saveProgrammer(Programmer mp){
        try{
            String query="insert into programmer values('"+mp.getId()+"','"+mp.getNama()+
                    "','"+mp.getGaji()+"','"+mp.getIdProject()+"');";
            Statement s=con.createStatement();
            s.execute(query);
            System.out.println("Saving success");
        }
        catch(SQLException se){
            System.out.println("Saving error");
        }
    }
    
    public void saveClient(Client mp){
        try{
            String query="insert into client values('"+mp.getId()+"','"+mp.getNama()+"');";
            Statement s=con.createStatement();
            s.execute(query);
            System.out.println("Saving success");
        }
        catch(SQLException se){
            System.out.println("Saving error");
        }
    }
    
    public void saveProject(Project p){
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String date = format.format(p.getDeadline());
            String query="insert into project values('"+p.getId()+"','"+date+"',(SELECT idManager FROM manager WHERE idManager='"+p.getIdManager()+"'),(SELECT idClient FROM client WHERE idClient='"+p.getIdClient()+"'));";
            Statement s=con.createStatement();
            s.execute(query);
            System.out.println("Saving success");
        }
        catch(SQLException se){
            System.out.println("Saving error");
        }
    }
    
    public void saveTugas(Tugas t){
        try{
            String query="insert into tugas values('"+t.getId()+"','Processing','"+t.getIdProgrammer()+"',(select idProject from project where idProject='"+t.getIdProject()+"'));";
            Statement s=con.createStatement();
            s.execute(query);
            System.out.println("Saving success");
        }
        catch(SQLException se){
            System.out.println("Saving error");
        }
    }
    
    public void updateManager(String id, String nama, double gaji){
        try{
            String query="update manager set namaManager ='"+nama+"' where idManager ='"+id+"';";
            Statement s=con.createStatement();
            s.execute(query);
            query="update manager set gajiManager ='"+gaji+"' where idManager ='"+id+"';";
            s=con.createStatement();
            s.execute(query);
            System.out.println("Updated");
        } catch(SQLException se){
            System.out.println("Updating error");
        }
    }
    
    public void updateProgrammer(String id, String nama, double gaji){
        try{
            String query="update programmer set namaProgrammer ='"+nama+"' where idProgrammer ='"+id+"';";
            Statement s=con.createStatement();
            s.execute(query);
            query="update programmer set gajiProgrammer ='"+gaji+"' where idProgrammer ='"+id+"';";
            s=con.createStatement();
            s.execute(query);
            System.out.println("Updated");
        } catch(SQLException se){
            System.out.println("Updating error");
        }
    }
    
    public void updateProgrammer(String id, String nama, double gaji,String idProject){
        try{
            String query="update programmer set namaProgrammer ='"+nama+"' where idProgrammer ='"+id+"';";
            Statement s=con.createStatement();
            s.execute(query);
            query="update programmer set gajiProgrammer ='"+gaji+"' where idProgrammer ='"+id+"';";
            s=con.createStatement();
            s.execute(query);
            query="update programmer set FK_idProject ='"+idProject+"' where idProgrammer ='"+id+"';";
            s=con.createStatement();
            s.execute(query);
            System.out.println("Updated");
        } catch(SQLException se){
            System.out.println("Updating error");
        }
    }
    
    public void updateClient(String id, String nama){
        try{
            String query="update client set namaClient ='"+nama+"' where idClient ='"+id+"';";
            Statement s=con.createStatement();
            s.execute(query);
            System.out.println("Updated");
        } catch(SQLException se){
            System.out.println("Updating error");
        }
    }
    
    public void updateProject(String id, Date deadline, String idManager){
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String date = format.format(deadline);
            String query="update project set deadline ='"+date+"' where idProject ='"+id+"';";
            Statement s=con.createStatement();
            s.execute(query);
            query="update project set FK_IdManager ='"+idManager+"' where idProject ='"+id+"';";
            s=con.createStatement();
            s.execute(query);
            System.out.println("Updated");
        } catch(SQLException se){
            System.out.println("Updating error");
        }
    }
    
    public void updateTugas(String id, String status, String idProgrammer){
        try{
            String query="update tugas set status ='"+status+"' where idTugas ='"+id+"';";
            Statement s=con.createStatement();
            s.execute(query);
            query="update tugas set FK_idProgrammer ='"+idProgrammer+"' where idTugas ='"+id+"';";
            s=con.createStatement();
            s.execute(query);
            System.out.println("Updated");
        } catch(SQLException se){
            System.out.println("Updating error");
        }
    }
    
    
    
    public void deleteManager(String id){
        try{
            String query="select idProject from project where FK_idManager='"+id+"';";
            Statement s=con.createStatement();
            s.execute(query);
            ResultSet rs=s.executeQuery(query);
            while(rs.next()){
                String idProject = rs.getString(1);
                query="delete from tugas where FK_idProject ='"+idProject+"';";
                s=con.createStatement();
                s.execute(query);
                query="select FK_idClient from project where idProject='"+idProject+"';";
                s=con.createStatement();
                s.execute(query);
                ResultSet rs1=s.executeQuery(query);
                while(rs1.next()){
                    String idClient = rs.getString(1);
                    query="delete from client where idClient='"+idClient+"';";
                    s=con.createStatement();
                    s.execute(query);
                }
                query="delete from project where idProject ='"+idProject+"';";
                s=con.createStatement();
                s.execute(query);
            }
            query="delete from manager where idManager='"+id+"';";
            s=con.createStatement();
            s.execute(query);
            System.out.println("Deleted");
        }catch(SQLException se){
            System.out.println("Deleting error");
        }
    }
    
    public void deleteProgrammer(String id){
        try{
            String query="select idTugas from tugas where FK_idProgrammer='"+id+"';";
            Statement s=con.createStatement();
            s.execute(query);
            ResultSet rs=s.executeQuery(query);
            while(rs.next()){
                String idTugas = rs.getString(1);
                query="update tugas set FK_idProgrammer =null where idTugas ='"+idTugas+"';";
                s=con.createStatement();
                s.execute(query);
            }
            query="delete from programmer where idProgrammer='"+id+"';";
            s=con.createStatement();
            s.execute(query);
            System.out.println("Deleted");
        }catch(SQLException se){
            System.out.println("Deleting error");
        }
    }
    
    public void deleteClient(String id){
        try{
            String query="select idProject from project where FK_idClient='"+id+"';";
            Statement s=con.createStatement();
            s.execute(query);
            ResultSet rs=s.executeQuery(query);
            while(rs.next()){
                String idProject = rs.getString(1);
                deleteProject(idProject);
            }
            query="delete from client where idClient='"+id+"';";
            s=con.createStatement();
            s.execute(query); 
            System.out.println("Deleted");
        }catch(SQLException se){
            System.out.println("Deleting error");
        }
    }
    
    public void deleteProject(String id){
        try{
            String query="select idProgrammer from programmer where FK_idProject='"+id+"';";
            Statement s=con.createStatement();
            s.execute(query);
            ResultSet rs=s.executeQuery(query);
            while(rs.next()){
                String idProgrammer = rs.getString(1);
                query="update programmer set FK_idProject ='null' where idProgrammer='"+idProgrammer+"';";
                s=con.createStatement();
                s.execute(query);
            }
            query="delete from tugas where FK_idProject='"+id+"';";
            s=con.createStatement();
            s.execute(query); 
            query="delete from project where idProject='"+id+"';";
            s=con.createStatement();
            s.execute(query); 
            System.out.println("Deleted");
        }catch(SQLException se){
            System.out.println("Deleting error");
        }
    }
    
    public void deleteTugas(String id){
        try{
            String query="delete from tugas where idTugas='"+id+"';";
            Statement s=con.createStatement();
            s.execute(query);
            System.out.println("Deleted");
        }catch(SQLException se){
            System.out.println("Deleting error");
        }
    }
    
    public void removeProgrammerProject(String id){
        try{
            String query="update programmer set FK_idProject ='null' where idProgrammer='"+id+"';";
            Statement s=con.createStatement();
            s.execute(query);
            System.out.println("Removed");
        }catch(SQLException se){
            System.out.println("Removing error");
        }
    }
    
    public void changeStatus(String id){
        try{
            String query="update tugas set status='finished' where idTugas='"+id+"';";
            Statement s=con.createStatement();
            s.execute(query);
            System.out.println("Updated");
        }catch(SQLException se){
            System.out.println("Updating error");
        }
    }
    
    public ArrayList<Manager>loadAllManagers() {
        try{
            ArrayList<Manager>managers = new ArrayList();
            String query="select * from manager";
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery(query);
            Manager t;
            while(rs.next()){
                String id = rs.getString(1);
                String nama=rs.getString(2);
                double gaji=Double.parseDouble(rs.getString(3));
                t=new Manager(nama,gaji,id);
                managers.add(t);
            }
            return managers;
        }catch(SQLException se){
            return null;
        }
    }
    
    public ArrayList<Programmer>loadAllProgrammers() {
        try{
            ArrayList<Programmer>programmers = new ArrayList();
            String query="select * from programmer";
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery(query);
            Programmer t;
            while(rs.next()){
                String id = rs.getString(1);
                String nama=rs.getString(2);
                double gaji=Double.parseDouble(rs.getString(3));
                String idProject = rs.getString(4);
                t=new Programmer(nama,gaji,id,idProject);
                programmers.add(t);
            }
            return programmers;
        }catch(SQLException se){
            return null;
        }
    }
    
    public ArrayList<Client>loadAllClients() {
        try{
            ArrayList<Client>clients = new ArrayList();
            String query="select * from client";
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery(query);
            Client t;
            while(rs.next()){
                String id = rs.getString(1);
                String nama=rs.getString(2);
                t=new Client(nama,id);
                clients.add(t);
            }
            return clients;
        }catch(SQLException se){
            return null;
        }
    }
    
    public ArrayList<Project>loadAllProjects() {
        try{
            ArrayList<Project>projects = new ArrayList();
            String query="select * from project";
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery(query);
            Project t;
            while(rs.next()){
                String id = rs.getString(1);
                Date deadline = rs.getDate(2);
                String idManager = rs.getString(3);
                String idClient = rs.getString(4);
                t=new Project(deadline,loadOneManagerById(idManager),id,idClient);
                projects.add(t);
            }
            return projects;
        }catch(SQLException se){
            return null;
        }
    }
    
    public ArrayList<Tugas>loadAllTugas(){
        try{
            ArrayList<Tugas>tugas = new ArrayList();
            String query="select * from tugas";
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery(query);
            Tugas t;
            while(rs.next()){
                String id = rs.getString(1);
                String status = rs.getString(2);
                String idProgrammer = rs.getString(3);
                String idProject = rs.getString(4);
                t=new Tugas(loadOneProgrammerById(idProgrammer),status,id,idProject);
                tugas.add(t);
            }
            return tugas;
        }catch(SQLException se){
            return null;
        }
    }
    
    public Manager loadOneManagerById(String id){
        try{
            String query="select * from manager where idManager ='"+id+"'";
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery(query);
            Manager t=null;
            while(rs.next()){
                String nama=rs.getString(2);
                double gaji=Double.parseDouble(rs.getString(3));
                t=new Manager(nama,gaji,id);
            }
            return t;
        }catch(SQLException se){
            return null;
        }
    }
    
    public Programmer loadOneProgrammerById(String id){
        try{
            String query="select * from programmer where idProgrammer ='"+id+"'";
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery(query);
            Programmer t=null;
            while(rs.next()){
                String nama=rs.getString(2);
                double gaji=Double.parseDouble(rs.getString(3));
                String idProject = rs.getString(4);
                t=new Programmer(nama,gaji,id,idProject);
            }
            return t;
        }catch(SQLException se){
            return null;
        }
    }
    
    public Client loadOneClientById(String id){
        try{
            String query="select * from client where idClient ='"+id+"'";
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery(query);
            Client t=null;
            while(rs.next()){
                String nama=rs.getString(2);
                t=new Client(nama,id);
            }
            return t;
        }catch(SQLException se){
            return null;
        }
    }
    
    public Project loadOneProjectById(String id){
        try{
            String query="select * from project where idProject ='"+id+"'";
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery(query);
            Project t=null;
            while(rs.next()){
                Date deadline = rs.getDate(2);
                String idManager = rs.getString(3);
                String idClient = rs.getString(4);
                t=new Project(deadline,loadOneManagerById(idManager),id,idClient);
            }
            return t;
        }catch(SQLException se){
            return null;
        }
    }
    
    public Tugas loadOneTugasById(String id){
        try{
            String query="select * from tugas where idTugas ='"+id+"'";
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery(query);
            Tugas t=null;
            while(rs.next()){
                String status = rs.getString(2);
                String idProgrammer = rs.getString(3);
                String idProject = rs.getString(4);
                t=new Tugas(loadOneProgrammerById(idProgrammer),status,id,idProject);
            }
            return t;
        }catch(SQLException se){
            return null;
        }
    }
    
    public void addAvailable(String idProject,String idProgrammer){
        try{
            String query="update programmer set FK_idProject='"+idProject+"' where idProgrammer='"+idProgrammer+"';";
            Statement s=con.createStatement();
            s.execute(query);
            System.out.println("Updated");
        } catch(SQLException es){
            System.out.println("Updating error");
        }
    }
}
