/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Application;
import Model.Manager;
import View.MenuManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author dania
 */
public class Controller_MenuManager extends MouseAdapter implements ActionListener{
    Application model;
    MenuManager view;
    Manager m;
    
    public Controller_MenuManager(Application model,Manager m){
        this.model=model;
        this.m=m;
        view = new MenuManager();
        view.addActionListener(this);
	view.addMouseAdapter(this);
	view.setVisible(true);
        model.loadManagers();
        model.loadClients();
        model.loadProgrammers();
        model.loadProjects();
        model.loadTugas();
        model.loadClientListProject();
        model.loadProjectListTim();
        model.loadProjectListTugas();
        view.setListProject(model.getProjectListId(m.getId()));
        view.setListProgrammer1(model.getProgrammerListIdNot(view.getSelectedProjectId()));
        view.getBtnAdd2().setVisible(false);
    }
    
    public void mousePressed(MouseEvent me){
        Object source=me.getSource();
        if(source.equals(view.getListProject())){
            view.setListProgrammer(model.getProgrammerListId(view.getSelectedProjectId()));
            view.setListTugas(model.getTugasListId1(view.getSelectedProjectId()));
            view.setProjectString(model.getProject(view.getSelectedProjectId()).toString());
            view.setListAvailable(model.getProgrammerListIdNot(view.getSelectedProjectId()));
            view.setListProgrammer1(model.getProgrammerListIdNot(view.getSelectedProjectId()));
            String zz="";
            for(int j=0;j<model.getTugasListId1(view.getSelectedProjectId()).length;j++){
                zz=zz+model.getTugas(model.getTugasListId1(view.getSelectedProjectId())[j]).getIdProgrammer()+" di "+model.getTugasListId1(view.getSelectedProjectId())[j]+"\n";
            }
            view.setTaProgTug(zz);
            view.getBtnAdd2().setVisible(true);
        } else if(source.equals(view.getListProgrammer())){
            if(model.getProgrammer(view.getSelectedProgrammerId()).getIdProject()!="null"){
                view.getBtnDelete1().setVisible(true);
            }
        }
    }
    
    public void actionPerformed(ActionEvent ae){
        Object source = ae.getSource();
        if(source.equals(view.getBtnLogout())){
            new Controller_Login(model);
            view.dispose();
        } else if(source.equals(view.getBtnAdd2())){
            new Controller_CreateTugas(model, model.getProject(view.getSelectedProjectId()));
            view.dispose();
        }    
//         else if(source.equals(view.getBtnAssign())){
//            try{
//                String idProgrammer = view.getSelectedAvailableId();
//                String idTugas = view.getSelectedTugas2Id();
//                if(!idProgrammer.equals("")&&!idTugas.equals("")){
//                    model.updateTugas(idTugas, model.getTugas(idTugas).getNamaTugas(), idProgrammer);
//                } else{
//                    JOptionPane.showMessageDialog(view,"Pilih Programmer dan Tugas yang Tersedia!");
//                }
//            }catch(Exception e){
//                JOptionPane.showMessageDialog(view,"Pilih Programmer dan Tugas yang Tersedia!");
//            }
//            
//        } 
        else if(source.equals(view.getBtnDelete1())){
            try{
                model.removeProgrammer(view.getSelectedProgrammerId());
                JOptionPane.showMessageDialog(view,"Removed");
                new Controller_MenuManager(model,m);
                view.dispose();
            }catch(Exception e){
                System.out.println("pilih");
            }
        } else if(source.equals(view.getBtnDelete2())){
             try{
                model.deleteTugas(model.getTugas(view.getSelectedTugasId()));
                JOptionPane.showMessageDialog(view,"Deleted");
                new Controller_MenuManager(model,m);
                view.dispose();
             } catch(Exception e){
                JOptionPane.showMessageDialog(view,"Pilih Tugas yang Ingin di Delete!");
            }
        } else if(source.equals(view.getBtnAdd1())){
            try{
                model.addAvailabe(model.getProject(view.getSelectedProjectId()), model.getProgrammer(view.getSelectedProgrammer1Id()));
                JOptionPane.showMessageDialog(view,"Updated");
                new Controller_MenuManager(model,m);
                view.dispose();
            } catch(Exception e){
                JOptionPane.showMessageDialog(view,"Pilih Project& Programmer yang Tersedia!");
            }
        }
    }
}
