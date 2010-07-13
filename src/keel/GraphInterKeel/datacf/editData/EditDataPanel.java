/***********************************************************************

	This file is part of KEEL-software, the Data Mining tool for regression, 
	classification, clustering, pattern mining and so on.

	Copyright (C) 2004-2010
	
	F. Herrera (herrera@decsai.ugr.es)
    L. S�nchez (luciano@uniovi.es)
    J. Alcal�-Fdez (jalcala@decsai.ugr.es)
    S. Garc�a (sglopez@ujaen.es)
    A. Fern�ndez (alberto.fernandez@ujaen.es)
    J. Luengo (julianlm@decsai.ugr.es)

	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.

	You should have received a copy of the GNU General Public License
	along with this program.  If not, see http://www.gnu.org/licenses/
  
**********************************************************************/

package keel.GraphInterKeel.datacf.editData;

import keel.GraphInterKeel.datacf.util.Dataset;
import keel.GraphInterKeel.datacf.util.DatasetTable;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * <p>
 * @author Written by Juan Carlos Fernández and Pedro Antonio Gutiérrez (University of Córdoba) 23/10/2008
 * @version 1.0
 * @since JDK1.5
 * </p>
 */
public class EditDataPanel extends javax.swing.JPanel {

    /**
     * <p>
     * Panel for editing datasets
     * </p>
     */
    
    /**
     * <p>
     * Constructor that initializes the panel
     * </p>
     */
    public EditDataPanel() {
        initComponents();
    }

    /**
     * <p>
     * This method is called from within the constructor to
     * initialize the form.
     * </p>
     * <p>
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     * </p>
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        datajScrollPane = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buttonsjPanel = new javax.swing.JPanel();
        addjButton = new javax.swing.JButton();
        deletejButton = new javax.swing.JButton();
        rankjPanel = new javax.swing.JPanel();
        rankjLabel1 = new javax.swing.JLabel();
        leftRankjLabel = new javax.swing.JLabel();
        comajLabel = new javax.swing.JLabel();
        rightRankjLabel = new javax.swing.JLabel();
        bracketRightjLabel = new javax.swing.JLabel();
        nominalValuesjLabel = new javax.swing.JLabel();
        nominalValuesjScrollPane = new javax.swing.JScrollPane();
        nominalValuesjTextPane = new javax.swing.JTextPane();

        setName("Form"); // NOI18N

        datajScrollPane.setName("datajScrollPane"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setName("jTable1"); // NOI18N
        datajScrollPane.setViewportView(jTable1);

        buttonsjPanel.setName("buttonsjPanel"); // NOI18N
        buttonsjPanel.setLayout(new java.awt.GridBagLayout());

        addjButton.setText("Add Instance");
        addjButton.setToolTipText("Add new instance");
        addjButton.setMaximumSize(new java.awt.Dimension(112, 29));
        addjButton.setMinimumSize(new java.awt.Dimension(112, 29));
        addjButton.setName("addjButton"); // NOI18N
        addjButton.setPreferredSize(new java.awt.Dimension(109, 23));
        addjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addjButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        buttonsjPanel.add(addjButton, gridBagConstraints);

        deletejButton.setText("Delete Instance");
        deletejButton.setToolTipText("Delete instance");
        deletejButton.setName("deletejButton"); // NOI18N
        addjButton.setEnabled(false);
        deletejButton.setEnabled(false);
        deletejButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletejButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        buttonsjPanel.add(deletejButton, gridBagConstraints);

        rankjPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("View Information"));
        rankjPanel.setName("rankjPanel"); // NOI18N
        nominalValuesjLabel.setEnabled(false);
        nominalValuesjTextPane.setText("");
        nominalValuesjScrollPane.setEnabled(false);
        nominalValuesjTextPane.setEnabled(false);

        rankjLabel1.setText("Value Rank: [");
        rankjLabel1.setEnabled(false);
        rankjLabel1.setName("rankjLabel1"); // NOI18N

        leftRankjLabel.setName("leftRankjLabel"); // NOI18N

        comajLabel.setText(",");
        comajLabel.setEnabled(false);
        comajLabel.setName("comajLabel"); // NOI18N

        rightRankjLabel.setName("rightRankjLabel"); // NOI18N

        bracketRightjLabel.setText("]");
        bracketRightjLabel.setEnabled(false);
        bracketRightjLabel.setName("bracketRightjLabel"); // NOI18N

        nominalValuesjLabel.setText("Nominal Values:");
        nominalValuesjLabel.setEnabled(false);
        nominalValuesjLabel.setName("nominalValuesjLabel"); // NOI18N

        nominalValuesjScrollPane.setName("nominalValuesjScrollPane"); // NOI18N

        nominalValuesjTextPane.setEditable(false);
        nominalValuesjTextPane.setEnabled(false);
        nominalValuesjTextPane.setName("nominalValuesjTextPane"); // NOI18N
        nominalValuesjTextPane.setOpaque(false);
        nominalValuesjScrollPane.setViewportView(nominalValuesjTextPane);

        javax.swing.GroupLayout rankjPanelLayout = new javax.swing.GroupLayout(rankjPanel);
        rankjPanel.setLayout(rankjPanelLayout);
        rankjPanelLayout.setHorizontalGroup(
            rankjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rankjPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rankjLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(leftRankjLabel)
                .addGap(18, 18, 18)
                .addComponent(comajLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rightRankjLabel)
                .addGap(18, 18, 18)
                .addComponent(bracketRightjLabel)
                .addGap(82, 82, 82)
                .addComponent(nominalValuesjLabel)
                .addGap(18, 18, 18)
                .addComponent(nominalValuesjScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
        );
        rankjPanelLayout.setVerticalGroup(
            rankjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rankjPanelLayout.createSequentialGroup()
                .addGroup(rankjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rankjPanelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(rankjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rankjLabel1)
                            .addComponent(nominalValuesjLabel)
                            .addComponent(comajLabel)
                            .addComponent(leftRankjLabel)
                            .addComponent(rightRankjLabel)
                            .addComponent(bracketRightjLabel)))
                    .addComponent(nominalValuesjScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonsjPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
            .addComponent(datajScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
            .addComponent(rankjPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(datajScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rankjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonsjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void addjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addjButtonActionPerformed
    int fil, i, j;
    int temp = 0;
    Object nuevo[][];
    boolean flagEnd = false;

    fil = this.jTable1.getSelectedRow();
    if (fil == -1) {
        flagEnd = true;
        fil = this.tablaDataset.getData().length;
    } else {
        this.jTable1.removeRowSelectionInterval(fil, fil);
    }
    nuevo = new Object[this.tablaDataset.getData().length + 1][this.tablaDataset.getData()[0].length];
    for (i = 0; i < nuevo.length; i++) {
        for (j = 0; j < nuevo[i].length; j++) {
            if (i == fil && temp == 0) {
                temp++;
            }
            if (i == fil) {
                if (this.data.getAttributeTypeIndex(j).equalsIgnoreCase("nominal")) {
                    nuevo[i][j] = new String("<null>");
                } else {
                    nuevo[i][j] = null;
                }
            } else {
                nuevo[i][j] = this.tablaDataset.getData()[i - temp][j];
            }
        }
    }
    //Refresh
    this.tablaDataset.setData(nuevo);

    this.jTable1.setModel(this.tablaDataset);
    datajScrollPane.getViewport().remove(jTable1);
    datajScrollPane.getViewport().add(jTable1);

    if (flagEnd == true) {
        JScrollBar scroll = this.datajScrollPane.getVerticalScrollBar();
        scroll.setValue(scroll.getMaximum());
    }

    //Posible change in Table
    this.editVariablePanel.refreshVariablePanel(this.data);

}//GEN-LAST:event_addjButtonActionPerformed

private void deletejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletejButtonActionPerformed
    int fil, i, j;
    int temp = 0;
    Object nuevo[][];

    fil = this.jTable1.getSelectedRow();
    this.jTable1.removeRowSelectionInterval(fil, fil);
    nuevo = new Object[this.tablaDataset.getData().length - 1][this.tablaDataset.getData()[0].length];
    for (i = 0; i < nuevo.length; i++) {
        for (j = 0; j < nuevo[i].length; j++) {
            if (i == fil && temp == 0) {
                temp++;
            }
            nuevo[i][j] = this.tablaDataset.getData()[i + temp][j];
        }
    }
    this.tablaDataset.setData(nuevo);

    this.jTable1.setModel(this.tablaDataset);
    datajScrollPane.getViewport().remove(jTable1);
    datajScrollPane.getViewport().add(jTable1);

    //Posible change in Table
    this.editVariablePanel.refreshVariablePanel(this.data);
}//GEN-LAST:event_deletejButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addjButton;
    private javax.swing.JLabel bracketRightjLabel;
    private javax.swing.JPanel buttonsjPanel;
    private javax.swing.JLabel comajLabel;
    private javax.swing.JScrollPane datajScrollPane;
    private javax.swing.JButton deletejButton;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel leftRankjLabel;
    private javax.swing.JLabel nominalValuesjLabel;
    private javax.swing.JScrollPane nominalValuesjScrollPane;
    private javax.swing.JTextPane nominalValuesjTextPane;
    private javax.swing.JLabel rankjLabel1;
    private javax.swing.JPanel rankjPanel;
    private javax.swing.JLabel rightRankjLabel;
    // End of variables declaration//GEN-END:variables

    /* Dataset */
    protected Dataset data = null;

    /* Dataset Table */
    protected DatasetTable tablaDataset = null;
    
    /** Edit Variable Panel */
    protected EditVariablePanel editVariablePanel = null;

    /**
     * <p>
     * Set a Dataset
     * </p>
     * @param data Dataset to be used
     */
    public void setData(Dataset data) {
        this.data = data;
        //Posible change in Table
        //this.editVariablePanel.refreshVariablePanel();
    }

    /**
     * <p>
     * Get Dataset
     * </p>
     * @return The edited Dataset
     */
    public Dataset getData() {
        return this.data;
    }

    /**
     * <p>
     * Set DatasetTable
     * </p>
     * @param tablaDataset Table for storing the dataset
     */
    public void setTablaDataset(DatasetTable tablaDataset) {
        this.tablaDataset = tablaDataset;
        //Posible change in Table
        //this.editVariablePanel.refreshVariablePanel();
    }

    /**
     * <p>
     * Return tablaDataset
     * </p>
     * @return DatasetTable Table for storing the dataset
     */
    public DatasetTable getTablaDataset() {
        return this.tablaDataset;
    }

    /**
     * <p>
     * Set JTable of Data
     * </p>
     * @param jTable1 JTable for the dataset
     */
    public void setJTable1(JTable jTable1) {
        this.jTable1 = jTable1;
    }

    /**
     * <p>
     * Get JTable of the dataSet
     * </p>
     * @return JTable JTable of the dataset
     */
    public JTable getJTable1() {
        return this.jTable1;
    }

    /**
     * <p>
     * Return datajScrollPane
     * </p>
     * @return JScrollPane JScrollPane for the dataset
     */
    public JScrollPane getScrollPane() {
        return this.datajScrollPane;
    }

    /**
     * <p>
     * Set the JScrollPane
     * </p>
     * @param scroll JScrollPane for the dataset
     */
    public void setScrollPane(JScrollPane scroll) {
        this.datajScrollPane = scroll;
    }

    /**
     * <p>
     * Set the dataset by using its name
     * </p>
     * @param nameDataset Dataset Name
     */
    public void setDataSet(String nameDataset) {
        Dataset aux = new Dataset(nameDataset);
        this.refreshDataPanel(aux);

        //Posible change in Table
        this.editVariablePanel.refreshVariablePanel(this.data);
    }

    /**
     * Change in EditVariablePanel
     * @param dataSet New dataset
     */
    public void refreshDataPanel(Dataset dataSet) {
        Dataset aux = this.data;
        this.data = dataSet;
        if (this.data.getDataVector().size() > 0) {
            try {
                this.tablaDataset = new DatasetTable(this.data, this);
            } catch (ArrayIndexOutOfBoundsException exp) {
                if (this.data.getNVariables() > (this.data.getNInputs() + this.data.getNOutputs())) {
                    JOptionPane.showMessageDialog(this,
                            "There are neither input nor output attributes", "Error", 2);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "The data set contains some errors. It can not be edited.", "Error", 2);
                }
                return;
            }

            this.jTable1.setModel(this.tablaDataset);
            this.jTable1.setFont(new java.awt.Font("Arial", 0, 11));
            this.jTable1.getTableHeader().setReorderingAllowed(false);
            this.jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            this.jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            this.jTable1.setCellSelectionEnabled(true);
            this.jTable1.setColumnSelectionAllowed(true);

            TableColumn combos;
            for (int i = 0; i < this.data.getNVariables(); i++) {
                if (this.data.getAttributeTypeIndex(i).equalsIgnoreCase("nominal")) {
                    combos = this.jTable1.getColumnModel().getColumn(i);
                    JComboBox comboBox = new JComboBox();
                    for (int j = 0; j < this.data.getRange(i).size(); j++) {
                        comboBox.addItem(new String(this.data.getRangesEnum(i, j)));
                    }
                    comboBox.addItem(new String("<null>"));
                    combos.setCellEditor(new DefaultCellEditor(comboBox));
                }
            }

            TableColumnModel colSM = jTable1.getColumnModel();
            colSM.addColumnModelListener(new TableColumnModelListener() {

                @Override
                public void columnAdded(TableColumnModelEvent e) {
                }

                @Override
                public void columnRemoved(TableColumnModelEvent e) {
                }

                @Override
                public void columnMoved(TableColumnModelEvent e) {
                }

                @Override
                public void columnMarginChanged(ChangeEvent e) {
                }

                @Override
                public void columnSelectionChanged(ListSelectionEvent e) {
                    ListSelectionModel csm = (ListSelectionModel) e.getSource();
                    int column = csm.getMinSelectionIndex();
                    if (column >= 0) {
                        if (data.getAttributeTypeIndex(column).equalsIgnoreCase("nominal")) {
                            nominalValuesjLabel.setEnabled(true);
                            nominalValuesjTextPane.setEnabled(true);
                            nominalValuesjScrollPane.setEnabled(true);

                            rankjLabel1.setEnabled(false);
                            leftRankjLabel.setEnabled(false);
                            comajLabel.setEnabled(false);
                            rightRankjLabel.setEnabled(false);
                            bracketRightjLabel.setEnabled(false);

                            leftRankjLabel.setText("");
                            rightRankjLabel.setText("");
                            String text = "";
                            for (int i = 0; i < data.getRange(column).size(); i++) {
                                text = text + data.getRangesEnum(column, i) + "\n";
                                nominalValuesjTextPane.setText(text);
                            }
                        } else {
                            rankjLabel1.setEnabled(true);
                            leftRankjLabel.setEnabled(true);
                            comajLabel.setEnabled(true);
                            rightRankjLabel.setEnabled(true);
                            bracketRightjLabel.setEnabled(true);
                            leftRankjLabel.setText("");
                            rightRankjLabel.setText("");

                            nominalValuesjLabel.setEnabled(false);
                            nominalValuesjTextPane.setEnabled(false);
                            nominalValuesjTextPane.setText("");
                            nominalValuesjScrollPane.setEnabled(false);

                            if (data.getAttributeTypeIndex(column).equalsIgnoreCase("integer")) {
                                leftRankjLabel.setText(data.getRangesInt(column, 0).toString());
                                rightRankjLabel.setText(data.getRangesInt(column, 1).toString());
                            } else {
                                leftRankjLabel.setText(data.getRangesReal(column, 0).toString());
                                rightRankjLabel.setText(data.getRangesReal(column, 1).toString());
                            }
                        }
                    }
                }
            }); // end of "colSM.addColumnModelListener"

            ListSelectionModel rowSM = jTable1.getSelectionModel();
            rowSM.addListSelectionListener(new ListSelectionListener() {

                @Override
                public void valueChanged(ListSelectionEvent e) {
                    ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                    if (lsm.isSelectionEmpty()) {
                        deletejButton.setEnabled(false);

                        leftRankjLabel.setText("");
                        rightRankjLabel.setText("");

                        nominalValuesjLabel.setEnabled(false);
                        nominalValuesjTextPane.setText("");
                        nominalValuesjScrollPane.setEnabled(false);
                        nominalValuesjTextPane.setEnabled(false);

                    } else {
                        deletejButton.setEnabled(true);

                        int column = jTable1.getSelectedColumn();

                        if (data.getAttributeTypeIndex(column).equalsIgnoreCase("nominal")) {
                            nominalValuesjLabel.setEnabled(true);
                            nominalValuesjTextPane.setEnabled(true);
                            nominalValuesjScrollPane.setEnabled(true);

                            leftRankjLabel.setText("");
                            rightRankjLabel.setText("");
                            nominalValuesjTextPane.setText("");
                            String text = "";
                            for (int i = 0; i < data.getRange(column).size(); i++) {
                                text = text + data.getRangesEnum(column, i) + "\n";
                                nominalValuesjTextPane.setText(text);
                            }
                        } else {
                            nominalValuesjLabel.setEnabled(false);
                            nominalValuesjTextPane.setText("");
                            nominalValuesjScrollPane.setEnabled(false);
                            nominalValuesjTextPane.setEnabled(false);
                            leftRankjLabel.setText("");
                            rightRankjLabel.setText("");

                            if (data.getAttributeTypeIndex(column).equalsIgnoreCase("integer")) {
                                leftRankjLabel.setText(data.getRangesInt(column, 0).toString());
                                rightRankjLabel.setText(data.getRangesInt(column, 1).toString());
                            } else {
                                leftRankjLabel.setText(data.getRangesReal(column, 0).toString());
                                rightRankjLabel.setText(data.getRangesReal(column, 1).toString());
                            }
                        }
                    }
                }
            });

            this.jTable1.setModel(this.tablaDataset);
            this.datajScrollPane.getViewport().remove(this.jTable1);
            this.datajScrollPane.getViewport().add(this.jTable1);
        } else {
            JOptionPane.showMessageDialog(this, "The file is not a KEEL data format file or it contains errors", "Error", 2);
            this.data = aux;
            return;
        }

        //Thre is not variables in the table. All have been deleted
        if (this.tablaDataset.getColumnCount() == 0) {
            this.deletejButton.setEnabled(false);
            this.addjButton.setEnabled(false);
        }
    }

    /**
     * <p>
     * Enables or disables Add Button
     * </p>
     * @param state State of Add Button
     */
    public void setStateAddButton(boolean state) {
        this.addjButton.setEnabled(state);
    }

    /**
     * <p>
     * Enables or disables Delete Button
     * </p>
     * @param state Stated for Delete Button
     */
    public void setStateDeleteButton(boolean state) {
        this.deletejButton.setEnabled(state);
    }

    /**
     * <p>
     * Gets the state of Add Button
     * </p>
     * @return boolean State of Add Button
     */
    public boolean getStateAddButton() {
        if (this.addjButton.isEnabled() == true) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * <p>
     * Gets the state of Delete Button
     * </p>
     * @return boolean State of Delete Button
     */
    public boolean getStateDeleteButton() {
        if (this.deletejButton.isEnabled() == true) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * <p>
     *  Set the EditVariablePanel
     * </p>
     * @param editVariablePanel Panel for editing variables
     */
    public void setEditVariablePanel(EditVariablePanel editVariablePanel) {
        this.editVariablePanel = editVariablePanel;
    }
}//Class


