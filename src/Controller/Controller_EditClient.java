/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Application;
import Model.Client;
import Model.Manager;
import Model.Project;
import View.EditClient;
import View.EditManager;
import View.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author dania
 */
public class Controller_EditClient implements ActionListener {
    Application model;
    EditClient view;
    Client c;
    
    public Controller_EditClient(Application model, Client c){
        this.model = model;
        this.c = c;
        view = new EditClient();
        view.setVisible(true);
        view.addListener(this);
        view.setTfNama(c.getNama());
        view.setId(c.getId());
        model.loadManagers();
        model.loadClients();
        model.loadProgrammers();
        model.loadProjects();
        model.loadTugas();
        model.loadClientListProject();
        model.loadProjectListTim();
        model.loadProjectListTugas();
        view.setId1(model.newProjectId());
        view.setListManager(model.getManagerListId());
    }
    
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        if (source.equals(view.getBtnDone())){
            String nama = view.getTfNama();
            if((!"".equals(nama))){
                model.updateClient(c.getId(), nama);
                JOptionPane.showMessageDialog(view, "Edited");
                new Controller_DataClient(model);
                view.dispose();
            }
            else{
                JOptionPane.showMessageDialog(view, "Form Tidak Boleh Kosong!");
            }
        } else if(source.equals(view.getBtnCancel())){
            new Controller_DataClient(model);
            view.dispose();
        } else if(source.equals(view.getBtnAdd())){
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String d = view.getTfDeadline().getText();
            if(!d.equals("")){
                try{
                    Date date = format.parse(d);
                    String idc = view.getId();
                    String id = view.getId1();
                    Manager manager = model.getManager(view.getSelectedManagerId());
                    model.setListProject(new Project(date,manager,id,idc));
                    JOptionPane.showMessageDialog(view, "Added");
                    new Controller_EditClient(model, c);
                    view.dispose();
                } catch(Exception ex){
                        JOptionPane.showMessageDialog(view, "Input Salah!");
                    }
            }
        }
    }
}
