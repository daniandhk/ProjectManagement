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
import View.Admin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author dania
 */
public class Controller_Admin implements ActionListener{
    Admin view;
    Application model;
    
    public Controller_Admin(Application model){
        this.model = model;
        view = new Admin();
        view.setVisible(true);
        view.addListener(this);
        view.setTfGaji("0");
        model.loadManagers();
        model.loadClients();
        model.loadProgrammers();
        model.loadProjects();
        model.loadClientListProject();
        view.setListManager(model.getManagerListId());
    }
    
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        if(source.equals(view.getBtnAdd())){
            String nama = view.getTfNama();
            double gaji = view.getTfGaji();
            if(!nama.equals("")){
                if(view.getCbStatus()==0){
                    if(gaji==0){
                        int dialogButton = JOptionPane.YES_NO_OPTION;
                        int dialogResult = JOptionPane.showConfirmDialog (null, "Gaji = Rp. 0, Lanjutkan?","Warning",dialogButton);
                        if(dialogResult == JOptionPane.YES_OPTION){
                            view.setId(model.newManagerId());
                            String id = view.getId();
                            model.setListManager(new Manager(nama,gaji,id));
                            JOptionPane.showMessageDialog(view,"ID "+id+" Added");
                            new Controller_Admin(model);
                            view.dispose();
                        }
                    } else{
                        view.setId(model.newManagerId());
                        String id = view.getId();
                        model.setListManager(new Manager(nama,gaji,id));
                        JOptionPane.showMessageDialog(view,"ID "+id+" Added");
                        new Controller_Admin(model);
                        view.dispose();
                    }
                } else if(view.getCbStatus()==1){
                    if(gaji==0){
                        int dialogButton = JOptionPane.YES_NO_OPTION;
                        int dialogResult = JOptionPane.showConfirmDialog (null, "Gaji = Rp. 0, Lanjutkan?","Warning",dialogButton);
                        if(dialogResult == JOptionPane.YES_OPTION){
                            view.setId(model.newProgrammerId());
                            String id = view.getId();
                            model.setListProgrammer(new Programmer(nama,gaji,id));
                            JOptionPane.showMessageDialog(view,"ID "+id+" Added");
                            new Controller_Admin(model);
                            view.dispose();
                        }
                    } else{
                        view.setId(model.newProgrammerId());
                        String id = view.getId();
                        model.setListProgrammer(new Programmer(nama,gaji,id,""));
                        JOptionPane.showMessageDialog(view,"ID "+id+" Added");
                        new Controller_Admin(model);
                        view.dispose();
                    }
                } 
            } else{
                JOptionPane.showMessageDialog(view, "Form Tidak Boleh Kosong!");
            }
        } else if(source.equals(view.getBtnAdd1())){
            if(!model.getListManager().isEmpty()){
                String nama = view.getTfNamaClient();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                String d = view.getTfDeadline().getText();
                if(!nama.equals("")&&!d.equals("")){
                    try{
                        Date date = format.parse(d);
                        view.setIdClient(model.newClientId());
                        String id = view.getId();
                        String idc = id;
                        model.setListClient(new Client(nama,id));
                        Manager manager = model.getManager(view.getSelectedManagerId());
                        view.setIdProject(model.newProjectId());
                        id = view.getId();
                        model.setListProject(new Project(date,manager,id,idc));
                        JOptionPane.showMessageDialog(view,"ID "+idc+" & "+id+" Added");
                        new Controller_Admin(model);
                        view.dispose();
                    } catch(Exception ex){
                        JOptionPane.showMessageDialog(view, "Input Salah!");
                    }
                } else{
                    JOptionPane.showMessageDialog(view, "Form Tidak Boleh Kosong!");
                }
            } else{
                JOptionPane.showMessageDialog(view, "Belum Ada Project Manager!");
            }
        } else if(source.equals(view.getBtnBack())){
            new Controller_Login(model);
            view.dispose();
        } else if(source.equals(view.getBtnToManager())){
            new Controller_DataManager(model);
            view.dispose();
        } else if(source.equals(view.getBtnToProgrammer())){
            new Controller_DataProgrammer(model);
            view.dispose();
        } else if(source.equals(view.getBtnToClient())){
            new Controller_DataClient(model);
            view.dispose();
        }
    }
}
