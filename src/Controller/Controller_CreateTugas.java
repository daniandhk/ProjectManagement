/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Application;
import Model.Manager;
import Model.Project;
import Model.Tugas;
import View.CreateTugas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JOptionPane;

/**
 *
 * @author dania
 */
public class Controller_CreateTugas extends MouseAdapter implements ActionListener{
    Application model;
    CreateTugas view;
    Project p;
    
    public Controller_CreateTugas(Application model,Project p){
       this.model = model;
       this.p = p;
       view = new CreateTugas();
       view.setVisible(true);
       view.addActionListener(this);
       model.loadManagers();
       model.loadClients();
       model.loadProgrammers();
       model.loadProjects();
       model.loadTugas();
       model.loadClientListProject();
       model.loadProjectListTim();
       model.loadProjectListTugas();
       try{
           for (int i = 0; i < model.getListProgrammer().size(); i++) {
               view.getNamaProgrammer().addItem(model.getProgrammerListId()[i]);
           }
       }catch(Exception ex){
           System.out.println("kosong");
       }
       view.setIdTugas(model.newTugasId());
    }
    
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        if(source.equals(view.getBtnCreate())){
            String pelaksana = view.getNamaProgrammer().getSelectedItem().toString();
            String id = view.getId();
            model.setListTugas(new Tugas(model.getProgrammer(pelaksana),"Processing",id,p.getId()));
            JOptionPane.showMessageDialog(view,"Created");
            new Controller_MenuManager(model,this.p.getManager());
            view.dispose();
        }
        else if(source.equals(view.getBtnCancel())){
            new Controller_MenuManager(model,this.p.getManager());
            view.dispose();
        }
    }
}
