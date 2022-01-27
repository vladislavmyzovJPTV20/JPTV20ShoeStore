/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.components;

import entity.Product;
import entity.Size;
import facade.ProductFacade;
import facade.SizeFacade;
import gui.GuiApp;
import gui.components.renderers.ListProductsCellRenderer;
import gui.components.renderers.ListSizesCellRenderer;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Влад
 */
public class ComboBoxSizesComponent extends JPanel{
    private JLabel title;
    private JComboBox<Size> comboBox;
    
    public ComboBoxSizesComponent(String text, int left, int heightPanel, int widthEditor) {
        initComponents(text, left, heightPanel,widthEditor);
    }

       
    private void initComponents(String text, int left, int heightPanel,int widthEditor) {
       
       this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
//       this.setBorder(BorderFactory.createLineBorder(Color.yellow));
       this.setPreferredSize(new Dimension(GuiApp.WIDTH_WINDOW,heightPanel));
       this.setMinimumSize(this.getPreferredSize());
       this.setMaximumSize(this.getPreferredSize());
       title = new JLabel(text);
       title.setPreferredSize(new Dimension(left,heightPanel));
       title.setMinimumSize(title.getPreferredSize());
       title.setMaximumSize(title.getPreferredSize());
       title.setHorizontalAlignment(JLabel.RIGHT);
       comboBox = new JComboBox<>();
//       title.setAlignmentY(TOP_ALIGNMENT);
       title.setFont(new Font("Tahoma",0,12));
       this.add(title);
       this.add(Box.createRigidArea(new Dimension(5,0)));
       comboBox.setModel(getComboBoxModel());
       comboBox.setRenderer(new ListSizesCellRenderer());
       comboBox.setSelectedIndex(-1);
       comboBox.setPreferredSize(new Dimension(widthEditor,heightPanel));
       comboBox.setMinimumSize(comboBox.getPreferredSize());
       comboBox.setMaximumSize(comboBox.getPreferredSize());
       this.add(comboBox);
    }

    public ComboBoxModel<Size> getComboBoxModel() {
        SizeFacade sizeFacade = new SizeFacade(Size.class);
        List<Size> sizes = sizeFacade.findAll();
        
        DefaultComboBoxModel<Size> defaultComboBoxModel = new DefaultComboBoxModel<>();
        for (Size size : sizes) {
            defaultComboBoxModel.addElement(size);
        }
        return defaultComboBoxModel;
    }

    public JComboBox<Size> getComboBox() {
        return comboBox;
    }

    
    
}

