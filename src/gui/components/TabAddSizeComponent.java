/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.components;

import entity.Size;
import facade.SizeFacade;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class TabAddSizeComponent extends JPanel{
    private CaptionComponent captionComponent;
    private InfoComponent infoComponent;
    private EditComponent shoeSize;
    private ButtonComponent buttonComponent;
    
    public TabAddSizeComponent(int widthPanel) {
        initComponents(widthPanel);
    }

    private void initComponents(int widthPanel) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0,25)));
        captionComponent = new CaptionComponent("Добавление размеров для обуви", widthPanel, 30);
        this.add(captionComponent);
        infoComponent = new InfoComponent("", widthPanel,27);
        this.add(infoComponent);
        this.add(Box.createRigidArea(new Dimension(0,10)));  
        shoeSize = new EditComponent("Размер обуви:", widthPanel, 30, 300);
        this.add(shoeSize);
        buttonComponent.getButton().addActionListener(ButtonAddReader());
    }
    private ActionListener ButtonAddReader(){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ex) {
                Size sizes = new Size();
                try {
                    sizes.setShoesSize(Integer.parseInt(shoeSize.getEditor().getText()));
                } catch (Exception e) {
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите размер обуви (цифрами)");
                    return;
                }
                SizeFacade sizeFacade = new SizeFacade(Size.class);
                try {
                    sizeFacade.create(sizes);
                    infoComponent.getInfo().setForeground(Color.BLUE);
                    infoComponent.getInfo().setText("Размер обуви успешно добавлен");
                    shoeSize.getEditor().setText("");
                } catch (Exception e) {
                    infoComponent.getInfo().setForeground(Color.RED);
                    infoComponent.getInfo().setText("Размер обуви добавить не удалось");
               }
            }
        };
    }
}
