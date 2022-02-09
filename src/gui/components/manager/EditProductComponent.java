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
import gui.components.ComboBoxProductsComponent;
import gui.components.EditComponent;
import gui.components.InfoComponent;
import gui.components.SizesComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.ListModel;


public class EditProductComponent extends JPanel{
    public EditProductComponent editProductComponent = this;
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
    private ComboBoxProductsComponent comboBoxProductsComponent;
    
    private ProductFacade productFacade;
    private Product editProduct;
    
    public EditProductComponent() {
        productFacade = new ProductFacade(Product.class);
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0,25)));
        captionComponent = new CaptionComponent("Редактирование обуви в магазине", GuiApp.WIDTH_WINDOW, 30);
        this.add(captionComponent);
        infoComponent = new InfoComponent("", GuiApp.WIDTH_WINDOW,27);
        this.add(infoComponent);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        comboBoxProductsComponent = new ComboBoxProductsComponent("Обувь", 240, 30, 300);
        this.add(comboBoxProductsComponent);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        nameComponent = new EditComponent("Название обуви:", 240, 30, 300);
        this.add(nameComponent);
        shoesModel = new EditComponent("Модель обуви:", 240, 30, 300);
        this.add(shoesModel);
        shoesColor = new EditComponent("Цвет обуви:", 240, 30, 300);
        this.add(shoesColor);
        manufacturerComponent = new EditComponent("Производитель обуви:", 240, 30, 300);
        this.add(manufacturerComponent);
        shoesPrice = new EditComponent("Цена обуви:", 240, 30, 300);
        this.add(shoesPrice);
        listSizesComponent = new SizesComponent("Размеры:", 720, 120, 300);
        this.add(listSizesComponent);
        quantityComponent = new EditComponent("Количество экземпляров:", 240, 30, 50);
        this.add(quantityComponent);
        buttonComponent = new ButtonComponent("Изменить обувь", GuiApp.WIDTH_WINDOW, 30, 350, 150);
        this.add(buttonComponent);
        buttonComponent.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product updateProduct = productFacade.find(editProduct.getId());
                        
                if(nameComponent.getEditor().getText().isEmpty()){
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите название обуви");
                    return;
                }
                updateProduct.setProductname(nameComponent.getEditor().getText());
                
                if(shoesModel.getEditor().getText().isEmpty()){
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите модель обуви");
                    return;
                }
                updateProduct.setModel(shoesModel.getEditor().getText());
                
                if(shoesColor.getEditor().getText().isEmpty()){
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите цвет обуви");
                    return;
                }
                updateProduct.setColor(shoesColor.getEditor().getText());
                
                if(manufacturerComponent.getEditor().getText().isEmpty()){
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите производителя обуви");
                    return;
                }
                updateProduct.setManufacturer(manufacturerComponent.getEditor().getText());
                
                try {
                    updateProduct.setPrice(Double.parseDouble(shoesPrice.getEditor().getText()));
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
                updateProduct.setSize(sizesShoes);
                try {
                    updateProduct.setQuantity(Integer.parseInt(quantityComponent.getEditor().getText()));
                    updateProduct.setCount(updateProduct.getQuantity());
                } catch (Exception ex) {
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите количество обуви (цифрами)");
                    return;
                }
                ProductFacade productFacade = new ProductFacade(Product.class);
                try {
                    productFacade.edit(updateProduct);
                    infoComponent.getInfo().setForeground(Color.BLUE);
                    infoComponent.getInfo().setText("Обувь успешно изменена");
                    comboBoxProductsComponent.getComboBox().setModel(comboBoxProductsComponent.getComboBoxModel());
                    comboBoxProductsComponent.getComboBox().setSelectedIndex(-1);
                    
//                    nameComponent.getEditor().setText("");
//                    shoesModel.getEditor().setText("");
//                    shoesColor.getEditor().setText("");
//                    manufacturerComponent.getEditor().setText("");
//                    shoesPrice.getEditor().setText("");
//                    quantityComponent.getEditor().setText("");
//                    listSizesComponent.getList().clearSelection();
                } catch (Exception ex) {
                    infoComponent.getInfo().setForeground(Color.RED);
                    infoComponent.getInfo().setText("Обувь изменить не удалось");
                }
            }
        });
        comboBoxProductsComponent.getComboBox().addItemListener((ItemEvent e) -> {
           JComboBox comboBox = (JComboBox) e.getSource();
           if(comboBox.getSelectedIndex() == -1){
                nameComponent.getEditor().setText("");
                shoesModel.getEditor().setText("");
                shoesColor.getEditor().setText("");
                manufacturerComponent.getEditor().setText("");
                shoesPrice.getEditor().setText("");
                quantityComponent.getEditor().setText("");
                listSizesComponent.getList().clearSelection();
           }else{
                editProduct = (Product) e.getItem();
                nameComponent.getEditor().setText(editProduct.getProductname());
                shoesModel.getEditor().setText(editProduct.getModel());
                shoesColor.getEditor().setText(editProduct.getColor());
                manufacturerComponent.getEditor().setText(editProduct.getManufacturer());
                shoesPrice.getEditor().setText(((Double)editProduct.getPrice()).toString());
                quantityComponent.getEditor().setText(((Integer)editProduct.getQuantity()).toString());
                listSizesComponent.getList().clearSelection();
                ListModel<Size> listModel = listSizesComponent.getList().getModel();
                for (int i=0;i<listModel.getSize();i++) {
                    if(editProduct.getSize().contains(listModel.getElementAt(i))){
                        listSizesComponent.getList().getSelectionModel().addSelectionInterval(i, i);
                    }
                }
           }
        });
}
       
    
    
}
