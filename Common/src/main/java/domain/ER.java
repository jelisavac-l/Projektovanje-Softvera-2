package domain;

import java.io.Serializable;
import java.time.LocalDate;

public class ER extends ADomainClass implements Serializable {

    private Evaluator evaluator;
    private Role role;
    private LocalDate startDate;
    private LocalDate endDate;

    @Override
    public String getDBClassName() {
        return "ER";
    }

    @Override
    public String getAttributeNames() {
        return "evaluator, role, startDate, endDate";
    }

    public ER(Evaluator evaluator, Role role, LocalDate startDate, LocalDate endDate) {
        this.evaluator = evaluator;
        this.role = role;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Evaluator getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(Evaluator evaluator) {
        this.evaluator = evaluator;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
