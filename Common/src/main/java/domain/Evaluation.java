package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Evaluation extends ADomainClass implements Serializable {

    private Long id;
    private LocalDate evaluationDate;
    private String conditions;
    private Double athleteWeight;
    private Evaluator evaluator;
    private Athlete athlete;
    private List<EvaluationItem> items;

    @Override
    public String getDBClassName() {
        return "Evaluations";
    }

    @Override
    public String getAttributeNames() {
        return "id, evaluationDate, conditions, athleteWeight, evaluator, athlete";
    }

    public Evaluation(Long id, LocalDate evaluationDate, String conditions, Double athleteWeight, Evaluator evaluator, Athlete athlete, List<EvaluationItem> items) {
        this.id = id;
        this.evaluationDate = evaluationDate;
        this.conditions = conditions;
        this.athleteWeight = athleteWeight;
        this.evaluator = evaluator;
        this.athlete = athlete;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(LocalDate evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public Double getAthleteWeight() {
        return athleteWeight;
    }

    public void setAthleteWeight(Double athleteWeight) {
        this.athleteWeight = athleteWeight;
    }

    public Evaluator getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(Evaluator evaluator) {
        this.evaluator = evaluator;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }

    public List<EvaluationItem> getItems() {
        return items;
    }

    public void setItems(List<EvaluationItem> items) {
        this.items = items;
    }
}
