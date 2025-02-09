package ui.models;

import domain.Athlete;
import domain.Evaluator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author luka
 */
public class AthleteTableModel extends AbstractTableModel {

    private final List<Athlete> athletes;
    private final String[] columnNames = {"ID", "Ime", "Prezime", "God. Rođenja", "Pol", "Visina", "P. I. Težina", "Klub", "Kategorija"};

    public AthleteTableModel(List<Athlete> athletes) {
        this.athletes = athletes;
    }

    @Override
    public int getRowCount() {
        return athletes.size();
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
        Athlete athlete = athletes.get(rowIndex);
        switch (columnIndex) {
            case 0: return athlete.getId();
            case 1: return athlete.getFirstName();
            case 2: return athlete.getLastName();
            case 3: return athlete.getBirthday().format(DateTimeFormatter.ofPattern("yyyy"));
            case 4: return athlete.getGender() ? "Ž" : "M";
            case 5: return athlete.getHeight();
            case 6: return athlete.getCurrentWeight();
            case 7: return athlete.getClub() != null ? athlete.getClub().getName() : "";
            case 8: return getCategory(athlete.getBirthday()); // Empty column
            default: return null;
        }
        
        
    }
    
    private String getCategory(LocalDate birthday) {
            int age = LocalDate.now().getYear() - birthday.getYear();
            if(age <= 10) return "DECA (U10)";
            if(age <= 12) return "DECA (U12)";
            if(age <= 14) return "PIONIRI (U14)";
            if(age <= 16) return "KADETI (U16)";
            if(age <= 18) return "JUNIORI (U18)";
            if(age <= 23) return "ML. SENIORI (U23)";
            if(age > 23) return "ST. SENIORI";
            return "N/A";
    }   
    
}
