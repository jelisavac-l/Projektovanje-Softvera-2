/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.models;

import domain.Activity;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author luka
 */
public class ActivityTableModel extends AbstractTableModel {
    
    private final List<Activity> activities;
    private final String[] columnNames = {"ID", "Naziv aktivnosti", "Merna jedinica"};

    public ActivityTableModel(List<Activity> activities) {
        this.activities = activities;
    }
    

    @Override
    public int getRowCount() {
        return activities.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Activity activity = activities.get(row);
        switch (col) {
            case 0: return activity.getId();
            case 1: return activity.getName(); 
            case 2: return activity.getUnit(); 
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    
}
