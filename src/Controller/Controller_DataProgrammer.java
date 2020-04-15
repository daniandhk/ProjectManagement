/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Application;
import View.DataProgrammer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author dania
 */
public class Controller_DataProgrammer extends MouseAdapter implements ActionListener {
    DataProgrammer view;
    Application model;
    
    public Controller_DataProgrammer(Application model){
        this.model = model;
        view = new DataProgrammer();
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
        view.setListProgrammer(model.getProgrammerListId());
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
                model.deleteProgrammer(model.getProgrammer(view.getSelectedProgrammerId()));
                JOptionPane.showMessageDialog(view,"Deleted");
                new Controller_DataProgrammer(model);
                view.dispose();
            }
        }
        else if(source.equals(view.getBtnEdit())){
            new Controller_EditProgrammer(model, model.getProgrammer(view.getSelectedProgrammerId()));
            view.dispose();
        }
    }
    
    public void mousePressed(MouseEvent me){
        Object source=me.getSource();
        if(source.equals(view.getListProgrammer())){
            String id = view.getSelectedProgrammerId();
            view.setTaListTugas("");
            view.setProgrammerString(model.getProgrammer(id).toString());
            int i = 0;
            String s = "";
            while(i<model.getListTugas().size()){
                try{
                    if(model.getTugasListId(id)[i]!=null){
                        s=s+model.getTugasListId(id)[i]+" di "+model.getListTugas().get(i).getIdProject()+"\n";
                    }
                } catch(NullPointerException e){
                    System.out.println("Belum Punya Tugas");
                }
                i++;
            }
            view.setTaListTugas(s);
        }
    }
}
