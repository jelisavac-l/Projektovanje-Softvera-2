package ui.models;

import domain.Evaluator;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author luka
 */
public class EvaluatorTableModel extends AbstractTableModel {
    
    public List<Evaluator> evaluators;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd. MMMM. yyyy");
    private String[] columnNames = {"Ime", "Preizime", "Username", "Email", "Zvanje", "Roles", "Datum zaposlenja", "Aktivan"};
    public EvaluatorTableModel(List<Evaluator> evaluators) {
        this.evaluators = evaluators;
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public int getRowCount() {
        return evaluators.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Evaluator eval = evaluators.get(i); 

        switch (i1) {
            case 0 -> {
                return eval.getFirstName();
            }
            case 1 -> {
                return eval.getLastName();
            }
            case 2 -> {
                return eval.getUsername();
            }
            case 3 -> {
                return eval.getEmail();
            }
            case 4 -> {
                return eval.getTitle();
            }
            case 5 -> {
                return "NA";
            }
            case 6 -> {
                return eval.getHireDate().format(formatter);
            }
            case 7 -> {
                if(eval.getActive()) return "DA";
                else return "NE";
            }
            default -> {
                return null;
            }
        }
    }
    
    
}
