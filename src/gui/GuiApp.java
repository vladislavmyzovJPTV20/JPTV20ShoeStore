/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Product;
import entity.Size;
import facade.ProductFacade;
import gui.components.ButtonComponent;
import gui.components.CaptionComponent;
import gui.components.EditComponent;
import gui.components.InfoComponent;
import gui.components.SizesComponent;
import gui.components.TabAddCustomerComponent;
import gui.components.TabAddProductComponent;
import gui.components.TabAddSizeComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class GuiApp extends JFrame{

    public GuiApp() {
        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        this.setPreferredSize(new Dimension(600,600));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        JTabbedPane managerTabbed = new JTabbedPane();
        managerTabbed.setPreferredSize(new Dimension(600,600));
        managerTabbed.setMinimumSize(managerTabbed.getPreferredSize());
        managerTabbed.setMaximumSize(managerTabbed.getPreferredSize());
        this.add(managerTabbed);
        TabAddProductComponent tabAddProductComponent = new TabAddProductComponent(this.getWidth());
        managerTabbed.addTab("Добавить обувь", tabAddProductComponent);
//        TabAddSizeComponent tabAddSizeComponent = new TabAddSizeComponent(this.getWidth());
//        managerTabbed.addTab("Добавить размер обуви", tabAddSizeComponent);
        TabAddCustomerComponent tabAddCustomerComponent = new TabAddCustomerComponent(this.getWidth());
        managerTabbed.addTab("Добавить покупателя", tabAddCustomerComponent);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GuiApp().setVisible(true);
            }
        });
    }

}
