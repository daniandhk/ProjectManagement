/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Application;
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
public class Controller_DataManager extends MouseAdapter implements ActionListener {
    DataManager view;
    Application model;
    
    public Controller_DataManager(Application model){
        this.model = model;
        view = new DataManager();
        view.addActionListener(this);
	view.addMouseAdapter(this);
	view.setVisible(true);
        model.loadManagers();
        model.loadClients();
        model.loadProgrammers();
        model.loadProjects();
        model.loadClientListProject();
        view.setListManager(model.getManagerListId());
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
                model.deleteManager(model.getManager(view.getSelectedManagerId()));
                JOptionPane.showMessageDialog(view,"Deleted");
                new Controller_DataManager(model);
                view.dispose();
            }
        }
        else if(source.equals(view.getBtnEdit())){
            new Controller_EditManager(model, model.getManager(view.getSelectedManagerId()));
            view.dispose();
        }
    }
    
    public void mousePressed(MouseEvent me){
        Object source=me.getSource();
        if(source.equals(view.getListManager())){
            String id = view.getSelectedManagerId();
            view.setTaProject("");
            view.setManagerString(model.getManager(id).toString());
            int i = 0;
            String s = "";
            while(i<model.getListProject().size()){
                try{
                    if(model.getProjectListId(id)[i]!=null){
                        s=s+model.getProjectListId(id)[i]+" by "+model.getListProject().get(i).getIdClient()+"\n";
                    }
                } catch(NullPointerException e){
                    System.out.println("Belum Punya Project");
                }
                i++;
            }
            view.setTaProject(s);
        }
    }
}
