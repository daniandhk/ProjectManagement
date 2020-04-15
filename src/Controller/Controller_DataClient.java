/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Application;
import View.DataClient;
import View.DataManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author dania
 */
public class Controller_DataClient extends MouseAdapter implements ActionListener{
    DataClient view;
    Application model;
    
    public Controller_DataClient(Application model){
        this.model = model;
        view = new DataClient();
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
        view.setListClient(model.getClientListId());
        view.getBtnEdit1().setVisible(false);
    }
    
    public void actionPerformed(ActionEvent ae){
        Object source = ae.getSource();
        if(source.equals(view.getBtnBack())){
            new Controller_Admin(model);
            view.dispose();
        }
        else if(source.equals(view.getBtnDelete())){
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog (null, "Yakin delete?","Warning",dialogButton);
            if(dialogResult == JOptionPane.YES_OPTION){
                model.deleteClient(model.getClient(view.getSelectedClientId()));
                JOptionPane.showMessageDialog(view,"Deleted");
                new Controller_DataClient(model);
                view.dispose();
            }
        }
        else if(source.equals(view.getBtnEdit())){
            try{
                new Controller_EditClient(model, model.getClient(view.getSelectedClientId()));
                view.dispose();
            } catch(Exception e){
                System.out.println("pilih");
            }
        }
        else if(source.equals(view.getBtnEdit1())){
            new Controller_EditProject(model, model.getProject(view.getSelectedProjectId()));
            view.dispose();
        }
        else if(source.equals(view.getBtnDelete1())){
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog (null, "Yakin delete?","Warning",dialogButton);
            if(dialogResult == JOptionPane.YES_OPTION){
                model.deleteProject(model.getProject(view.getSelectedProjectId()));
                JOptionPane.showMessageDialog(view,"Deleted");
                new Controller_DataClient(model);
                view.dispose();
            }
        }
    }
    
    public void mousePressed(MouseEvent me){
        Object source=me.getSource();
        if(source.equals(view.getListClient())){
            view.getBtnEdit1().setVisible(false);
            String id = view.getSelectedClientId();
            view.setTaClient("");
            view.setTaProject("");
            String[] z = new String[1];
            z[0]="";
            view.setClientString(model.getClient(id).toString());
            view.setListProject(model.getProjectListId1(id));
        }
        else if(source.equals(view.getListProject())){
            try{
                String id1 = view.getSelectedProjectId();
                view.setProjectString(model.getProject(id1).toString());
                view.getBtnEdit1().setVisible(true);
            }catch(Exception e){
                System.out.println("salah click");
            }
        }
    }
}
