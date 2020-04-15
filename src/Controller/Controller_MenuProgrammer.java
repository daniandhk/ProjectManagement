/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Application;
import Model.Manager;
import Model.Programmer;
import View.MenuManager;
import View.MenuProgrammer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author dania
 */
public class Controller_MenuProgrammer extends MouseAdapter implements ActionListener {
    Application model;
    MenuProgrammer view;
    Programmer p;
    
    public Controller_MenuProgrammer(Application model,Programmer p){
        this.model=model;
        this.p=p;
        view = new MenuProgrammer();
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
        view.setListTugas(model.getTugasListId(p.getId()));
    }
    
    public void mousePressed(MouseEvent me){
        Object source=me.getSource();
        if(source.equals(view.getListTugas())){
            try{
                view.setTugasString(model.getTugas(view.getSelectedTugasId()).toString());
                
            }catch(Exception ex){
                System.out.println("kosong");
            }
        }
    }
    
    public void actionPerformed(ActionEvent ae){
        Object source = ae.getSource();
        if(source.equals(view.getBtnLogout())){
            new Controller_Login(model);
            view.dispose();
        }
        else if(source.equals(view.getBtnStatus())){
            if(view.getSelectedTugasId()!=null){
                model.changeStatus(view.getSelectedTugasId());
                JOptionPane.showMessageDialog(view,"Status Berubah");
                new Controller_MenuProgrammer(model, p);
                view.dispose();
            }
            else{
                JOptionPane.showMessageDialog(view,"Pilih Tugas yang Ingin diubah!");
            }
        }
    }
}
