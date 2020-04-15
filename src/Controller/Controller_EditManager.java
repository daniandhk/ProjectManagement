/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Application;
import Model.Manager;
import View.EditManager;
import View.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author dania
 */
public class Controller_EditManager implements ActionListener {
    Application model;
    EditManager view;
    Manager m;
    
    public Controller_EditManager(Application model, Manager m){
        this.model = model;
        this.m = m;
        view = new EditManager();
        view.setVisible(true);
        view.addListener(this);
        view.setTfNama(m.getNama());
        view.setTfGaji(Double.toString(m.getGaji()));
        view.setId(m.getId());
        model.loadManagers();
    }
    
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        if (source.equals(view.getBtnDone())){
            String nama = view.getTfNama();
            if((!"".equals(nama))&&!"".equals(view.getTfGaji())){
                Double gaji = Double.parseDouble(view.getTfGaji());
                model.updateManager(m.getId(), nama, gaji);
                JOptionPane.showMessageDialog(view, "Edited");
                new Controller_DataManager(model);
                view.dispose();
            }
            else{
                JOptionPane.showMessageDialog(view, "Form Tidak Boleh Kosong!");
            }
        } else if(source.equals(view.getBtnCancel())){
            new Controller_DataManager(model);
            view.dispose();
        }
    }
}
