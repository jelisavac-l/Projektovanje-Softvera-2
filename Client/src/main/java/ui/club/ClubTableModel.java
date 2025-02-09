package ui.club;

import domain.Club;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author luka
 */
public class ClubTableModel extends AbstractTableModel {

    private final String[] columnNames = {"Name", "Address"};
    public List<Club> clubs;

    public ClubTableModel(List<Club> clubs) {
        this.clubs = clubs;
    }

    @Override
    public int getRowCount() {
        return clubs.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Club club = clubs.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return club.getName();
            case 1:
                return club.getAddress();
            default:
                return null;
        }
    }
    
}
