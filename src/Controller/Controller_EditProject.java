/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Application;
import Model.Client;
import Model.Manager;
import Model.Programmer;
import Model.Project;
import View.DataProgrammer;
import View.EditManager;
import View.EditProject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author dania
 */
public class Controller_EditProject extends MouseAdapter implements ActionListener {
    Application model;
    EditProject view;
    Project c;
    
    public Controller_EditProject(Application model, Project c){
        this.model=model;
        this.c=c;
        view = new EditProject();
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
        view.getjPanel3().setVisible(false);
        view.getBtnAddTugas().setVisible(false);
        try{
            view.setTfDeadline(c.getDeadline().toString());
            view.setListProgrammer(model.getProgrammerListIdNot(c.getIdManager()));
            view.setId(c.getId());
        }catch(Exception ex){
            String[] uwu = new String[1];
            uwu[0]="";
            view.setTfDeadline("");
            view.setListProgrammer(uwu);
            view.setId("Proj-"+model.newProjectId());
        }
        
        view.setId1(model.newProjectId());
        view.setListManager(model.getManagerListId());
        
    }
    
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        if(source.equals(view.getBtnDone())){
            String d = view.getTfDeadline().getText();
            if(!d.equals("")){
                try{
                    String id = view.getId();
                    Manager manager = model.getManager(view.getSelectedManagerId());
                    model.updateProject(id, d, manager.getId());
                    JOptionPane.showMessageDialog(view,"Edited");
                    new Controller_EditClient(model, model.getClient(c.getIdClient()));
                    view.dispose();
                } catch(Exception ex){
                        JOptionPane.showMessageDialog(view, "Input Salah!");
                    }
            } else{
                JOptionPane.showMessageDialog(view, "Form Jangan Kosong!");
            }
        } else if(source.equals(view.getBtnAdd())){
            if(view.getSelectedProgrammerId()!=null){
                String idProgrammer = view.getSelectedProgrammerId();
                model.updateProgrammer(idProgrammer, model.getProgrammer(idProgrammer).getNama(), model.getProgrammer(idProgrammer).getGaji(), c.getIdManager());
                JOptionPane.showMessageDialog(view,"Added");
                new Controller_DataClient(model);
                view.dispose();
            } else{
                JOptionPane.showMessageDialog(view, "Pilih Programmer!");
            }
        } else if(source.equals(view.getBtnCancel())){
            new Controller_DataClient(model);
            view.dispose();
        } else if(source.equals(view.getBtnAddTugas())){
            new Controller_CreateTugas1(model, c,model.getClient(c.getIdClient()));
            view.dispose();
        }
    }
}
