/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.components.director;

import entity.Customer;
import facade.CustomerFacade;
import gui.GuiApp;
import gui.components.ButtonComponent;
import gui.components.CaptionComponent;
import gui.components.EditComponent;
import gui.components.InfoComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class TabAddCustomerComponent extends JPanel{
    private CaptionComponent captionComponent;
    private InfoComponent infoComponent;
    private EditComponent nameCustomer;
    private EditComponent lastnameCustomer;
    private EditComponent phoneCustomer;
    private EditComponent moneyCustomer;
    private ButtonComponent buttonComponent;

    public TabAddCustomerComponent() {
        initComponents();
    }    

    private void initComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0,25)));
        captionComponent = new CaptionComponent("Добавление нового покупателя", GuiApp.WIDTH_WINDOW, 31);
        this.add(captionComponent); 
        infoComponent = new InfoComponent("", GuiApp.WIDTH_WINDOW, 30);
        this.add(infoComponent);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        nameCustomer = new EditComponent("Имя:", GuiApp.WIDTH_WINDOW, 30, 300);
        this.add(nameCustomer);
        lastnameCustomer = new EditComponent("Фамилия:", GuiApp.WIDTH_WINDOW, 30, 300);
        this.add(lastnameCustomer);
        phoneCustomer = new EditComponent("Телефон:", GuiApp.WIDTH_WINDOW, 30, 200);
        this.add(phoneCustomer);
        moneyCustomer = new EditComponent("Деньги покупателя:", GuiApp.WIDTH_WINDOW, 30, 200);
        this.add(moneyCustomer);
        buttonComponent = new ButtonComponent("Добавить покупателя", GuiApp.WIDTH_WINDOW, 30, 350, 150);
        this.add(buttonComponent);
        buttonComponent.getButton().addActionListener(ButtonAddReader());
    }
    private ActionListener ButtonAddReader(){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                Customer customer = new Customer();
                if(nameCustomer.getEditor().getText().isEmpty()){
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите имя покупателя");
                    return;
                }
                customer.setFirstname(nameCustomer.getEditor().getText());
                
                if(lastnameCustomer.getEditor().getText().isEmpty()){
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите фамилию покупателя");
                    return;
                }
                customer.setLastname(lastnameCustomer.getEditor().getText());
                
                if(phoneCustomer.getEditor().getText().isEmpty()){
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите телефон покупателя");
                    return;
                }
                customer.setPhone(phoneCustomer.getEditor().getText());               
               
                try {
                    customer.setMoney(Integer.parseInt(moneyCustomer.getEditor().getText()));
                } catch (Exception ex) {
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите деньги покупателя (цифрами)");
                    return;
                }
                
                CustomerFacade customerFacade = new CustomerFacade(Customer.class);
                
                try {
                    customerFacade.create(customer);
                    infoComponent.getInfo().setText("Покупатель успешно добавлен");
                    infoComponent.getInfo().setForeground(Color.BLUE);
                    phoneCustomer.getEditor().setText("");
                    lastnameCustomer.getEditor().setText("");
                    nameCustomer.getEditor().setText("");
                    moneyCustomer.getEditor().setText("");
                } catch (Exception e) {
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Покупателя добавить не удалось");
                }
            }
        };
    }
}
