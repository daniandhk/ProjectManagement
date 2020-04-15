/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.Application;
import View.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
/**
 *
 * @author dania
 */
public class Controller_Login implements ActionListener{
    Login view;
    Application model;
    
    public Controller_Login(Application model){
        this.model = model;
        view = new Login();
        view.setVisible(true);
        view.addListener(this);
        model.loadManagers();
        model.loadClients();
        model.loadProgrammers();
    }
    
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        if (source.equals(view.getBtnLogin())){
            String id = view.getTfID();
            if(view.getTfID().equalsIgnoreCase("admin")){
                if(view.getTfPass().equalsIgnoreCase("admin")){
                    new Controller_Admin(model);
                    view.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(view, "ID/Password Salah!");
                }
            }
            else if(model.getManager(id)!=null){
                if(view.getTfPass().equals("manager")){
                    new Controller_MenuManager(model,model.getManager(id));
                    view.dispose();
                } else{
                    JOptionPane.showMessageDialog(view, "ID/Password Salah!");
                }
            }
            else if(model.getProgrammer(id)!=null){
                if(view.getTfPass().equals("programmer")){
                    new Controller_MenuProgrammer(model, model.getProgrammer(id));
                    view.dispose();
                } else{
                    JOptionPane.showMessageDialog(view, "ID/Password Salah!");
                }
            }
            else{
                JOptionPane.showMessageDialog(view, "ID/Password Salah!");
            }
        }
        else if(source.equals(view.getBtnExit())){
            view.dispose();
        }
    }
}
