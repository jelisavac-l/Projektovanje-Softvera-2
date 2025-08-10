package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Evaluation implements DomainObject {

    private Long id;
    private LocalDate evaluationDate;
    private String conditions;
    private Double athleteWeight;
    private Evaluator evaluator;
    private Athlete athlete;
    private Boolean valid;

    private List<EvaluationItem> items;

    public Evaluation(Long id, LocalDate evaluationDate, String conditions, Double athleteWeight, Evaluator evaluator, Athlete athlete, List<EvaluationItem> items) {
        this.id = id;
        this.evaluationDate = evaluationDate;
        this.conditions = conditions;
        this.athleteWeight = athleteWeight;
        this.evaluator = evaluator;
        this.athlete = athlete;
        this.items = items;
    }

    public Evaluation(Long id, LocalDate evaluationDate, String conditions, Double athleteWeight, Evaluator evaluator, Athlete athlete, List<EvaluationItem> items, Boolean valid) {
        this.id = id;
        this.evaluationDate = evaluationDate;
        this.conditions = conditions;
        this.athleteWeight = athleteWeight;
        this.evaluator = evaluator;
        this.athlete = athlete;
        this.items = items;
        this.valid = valid;
    }

    public Evaluation() {}

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

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public void resolveNullPointers() {
        if(this.evaluator == null) this.evaluator = new Evaluator();
        if(this.athlete == null) this.athlete = new Athlete();
    }

    @Override
    public String getAttributeValuesForInsert() {
        return "'" + evaluationDate + "', '" + conditions + "', " + athleteWeight + ", " +
            evaluator.getId() + ", " + athlete.getId() + ", " + valid;
    }

    @Override
    public String getUpdatedAttributeValues() {
        return "evaluationDate='" + evaluationDate + "', conditions='" + conditions +
            "', athleteWeight=" + athleteWeight + ", evaluator=" + evaluator.getId() +
            ", athlete=" + athlete.getId() + ", valid=" + valid;
    }

    @Override
    public String getColumnNames() {
        return "evaluationDate, conditions, athleteWeight, evaluator, athlete, valid";
    }

    @Override
    public String getTableName() {
        return "Evaluations";
    }

    @Override
    public String getWhereCondition() {
        return "id=" + id;
    }

    @Override
    public DomainObject getNewRecord(ResultSet rs) throws SQLException {
        resolveNullPointers();
        return new Evaluation(
            rs.getLong(this.getAlias() + ".id"),
            rs.getObject(this.getAlias() + ".evaluationDate", java.time.LocalDate.class),
            rs.getString(this.getAlias() + ".conditions"),
            rs.getDouble(this.getAlias() + ".athleteWeight"),
            new Evaluator(
                rs.getLong(this.evaluator.getAlias() + ".id"),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null),
            new Athlete(
                rs.getLong(this.athlete.getAlias() + ".id"),
                null,
                null,
                null,
                null,
                null,
                null,
                null),
            null,
            rs.getBoolean(this.getAlias() + ".valid")
        );
    }

    @Override
    public String getPrimaryKey() {
        return "id=" + id;
    }

    @Override
    public String getJoinClause() {
        resolveNullPointers();
        return "JOIN " +
            this.athlete.getTableName() +
            " " +
            this.athlete.getAlias() +
            " ON " +
            this.athlete.getAlias() +
            ".id = " +
            this.getAlias() +
            ".athlete JOIN " +
            this.evaluator.getTableName() +
            " " +
            this.evaluator.getAlias() +
            " ON " +
            this.evaluator.getAlias() +
            ".id=" +
            this.getAlias() +
            ".evaluator";
    }

    @Override
    public String getAlias() {
        return "el";
    }

    @Override
    public String getColumnNameByIndex(int i) {
        String[] cols = {"id", "evaluationDate", "conditions", "athleteWeight", "evaluator", "athlete", "valid"};
        return cols[i];
    }

}
