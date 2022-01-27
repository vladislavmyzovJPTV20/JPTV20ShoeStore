/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.components.manager;

import entity.Size;
import facade.SizeFacade;
import gui.GuiApp;
import gui.components.ButtonComponent;
import gui.components.CaptionComponent;
import gui.components.ComboBoxSizesComponent;
import gui.components.EditComponent;
import gui.components.InfoComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class EditSizeComponent extends JPanel{
    private CaptionComponent captionComponent;
    private InfoComponent infoComponent;
    private EditComponent shoeSize;
    private ButtonComponent buttonComponent;
    
    private ComboBoxSizesComponent comboBoxSizesComponent;
    private Size editSize;
    private SizeFacade sizeFacade;
    
    public EditSizeComponent() {
        sizeFacade = new SizeFacade(Size.class);
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0,25)));
        captionComponent = new CaptionComponent("Редактирование размера обуви", GuiApp.WIDTH_WINDOW, 30);
        this.add(captionComponent);
        infoComponent = new InfoComponent("", GuiApp.WIDTH_WINDOW,27);
        this.add(infoComponent);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        comboBoxSizesComponent = new ComboBoxSizesComponent("Размеры", 240, 30, 300);
        this.add(comboBoxSizesComponent);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        shoeSize = new EditComponent("Размер обуви:", 240, 30, 300);
        this.add(shoeSize);
        buttonComponent = new ButtonComponent("Изменить размер",GuiApp.WIDTH_WINDOW, 30, 350, 150);
        this.add(buttonComponent);
        buttonComponent.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Size updateSize = sizeFacade.find(editSize.getId());
                
                try {
                    updateSize.setShoesSize(Integer.parseInt(shoeSize.getEditor().getText()));
                } catch (Exception ex) {
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите размер обуви (цифрами)");
                    return;
                }
                SizeFacade sizeFacade = new SizeFacade(Size.class);
                try {
                    sizeFacade.create(updateSize);
                    infoComponent.getInfo().setForeground(Color.BLUE);
                    infoComponent.getInfo().setText("Размер обуви успешно изменён");
                    comboBoxSizesComponent.getComboBox().setModel(comboBoxSizesComponent.getComboBoxModel());
                    comboBoxSizesComponent.getComboBox().setSelectedIndex(-1);
                } catch (Exception eq) {
                    infoComponent.getInfo().setForeground(Color.RED);
                    infoComponent.getInfo().setText("Размер обуви изменить не удалось");
               }
            }
        });
        comboBoxSizesComponent.getComboBox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                if (comboBox.getSelectedIndex() == -1) {
                    shoeSize.getEditor().setText("");
                }else{
                    editSize = (Size) e.getItem();
                    shoeSize.getEditor().setText(((Integer)editSize.getShoesSize()).toString());
                }
            }
        
        });
    
    }   
}
    

