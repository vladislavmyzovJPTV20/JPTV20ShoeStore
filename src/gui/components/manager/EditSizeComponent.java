/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.components.manager;

import entity.Product;
import entity.Size;
import facade.ProductFacade;
import gui.GuiApp;
import gui.components.ButtonComponent;
import gui.components.CaptionComponent;
import gui.components.EditComponent;
import gui.components.InfoComponent;
import gui.components.SizesComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;


public class EditSizeComponent extends JPanel{
    private CaptionComponent captionComponent;
    private InfoComponent infoComponent;
    private EditComponent nameComponent;
    private EditComponent shoesModel;
    private EditComponent shoesColor;
    private EditComponent manufacturerComponent;
    private EditComponent shoesPrice;
    private EditComponent quantityComponent;
    private ButtonComponent buttonComponent;
    private SizesComponent listSizesComponent;
    
    public EditSizeComponent() {
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0,25)));
        captionComponent = new CaptionComponent("Добавление обуви в магазин", GuiApp.WIDTH_WINDOW, 30);
        this.add(captionComponent);
        infoComponent = new InfoComponent("", GuiApp.WIDTH_WINDOW,27);
        this.add(infoComponent);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        nameComponent = new EditComponent("Название обуви:", GuiApp.WIDTH_WINDOW, 30, 300);
        this.add(nameComponent);
        shoesModel = new EditComponent("Модель обуви:", GuiApp.WIDTH_WINDOW, 30, 300);
        this.add(shoesModel);
        shoesColor = new EditComponent("Цвет обуви:", GuiApp.WIDTH_WINDOW, 30, 300);
        this.add(shoesColor);
        manufacturerComponent = new EditComponent("Производитель обуви:", GuiApp.WIDTH_WINDOW, 30, 300);
        this.add(manufacturerComponent);
        shoesPrice = new EditComponent("Цена обуви:", GuiApp.WIDTH_WINDOW, 30, 300);
        this.add(shoesPrice);
        listSizesComponent = new SizesComponent("Размеры:", GuiApp.WIDTH_WINDOW, 120, 300);
        this.add(listSizesComponent);
        quantityComponent = new EditComponent("Количество экземпляров:", GuiApp.WIDTH_WINDOW, 30, 50);
        this.add(quantityComponent);
        buttonComponent = new ButtonComponent("Добавить обувь", GuiApp.WIDTH_WINDOW, 30, 350, 150);
        this.add(buttonComponent);
        buttonComponent.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product product = new Product();
                if(nameComponent.getEditor().getText().isEmpty()){
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите название обуви");
                    return;
                }
                product.setProductname(nameComponent.getEditor().getText());
                
                if(shoesModel.getEditor().getText().isEmpty()){
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите модель обуви");
                    return;
                }
                product.setModel(shoesModel.getEditor().getText());
                
                if(shoesColor.getEditor().getText().isEmpty()){
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите цвет обуви");
                    return;
                }
                product.setColor(shoesColor.getEditor().getText());
                
                if(manufacturerComponent.getEditor().getText().isEmpty()){
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите производителя обуви");
                    return;
                }
                product.setManufacturer(manufacturerComponent.getEditor().getText());
                
                try {
                    product.setPrice(Double.parseDouble(shoesPrice.getEditor().getText()));
                } catch (Exception ex) {
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите цену обуви");
                    return;
                }
                List<Size> sizesShoes = listSizesComponent.getList().getSelectedValuesList();
                if(sizesShoes.isEmpty()){
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Выберите размеры обуви");
                    return;
                }
                product.setSize(sizesShoes);
                try {
                    product.setQuantity(Integer.parseInt(quantityComponent.getEditor().getText()));
                    product.setCount(product.getQuantity());
                } catch (Exception ex) {
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите количество обуви (цифрами)");
                    return;
                }
                ProductFacade productFacade = new ProductFacade(Product.class);
                try {
                    productFacade.create(product);
                    infoComponent.getInfo().setForeground(Color.BLUE);
                    infoComponent.getInfo().setText("Обувь успешно добавлена");
                    nameComponent.getEditor().setText("");
                    shoesModel.getEditor().setText("");
                    shoesColor.getEditor().setText("");
                    manufacturerComponent.getEditor().setText("");
                    shoesPrice.getEditor().setText("");
                    quantityComponent.getEditor().setText("");
                    listSizesComponent.getList().clearSelection();
                } catch (Exception ex) {
                    infoComponent.getInfo().setForeground(Color.RED);
                    infoComponent.getInfo().setText("Обувь добавить не удалось");
                }
            }
        });
    }
}
