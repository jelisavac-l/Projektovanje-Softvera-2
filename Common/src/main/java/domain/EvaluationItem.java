package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EvaluationItem implements DomainObject {

    private Evaluation evaluation;
    private Integer serial;
    private Long result;
    private Activity activity;

    public EvaluationItem(Evaluation evaluation, Integer serial, Long result, Activity activity) {
        this.evaluation = evaluation;
        this.serial = serial;
        this.result = result;
        this.activity = activity;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public String getAttributeValuesForInsert() {
        return "'" + evaluation + "', " + serial + ", '" + result + "', " + activity.getId();
    }

    @Override
    public String getUpdatedAttributeValues() {
        return "evaluation='" + evaluation + "', serial=" + serial +
            ", result='" + result + "', activity=" + activity.getId();
    }

    @Override
    public String getColumnNames() {
        return "evaluation, serial, result, activityId";
    }

    @Override
    public String getTableName() {
        return "EvaluationItems";
    }

    @Override
    public String getWhereCondition() {
        return "evaluation = " + evaluation.getId() + " AND serial = " + serial;
    }

    @Override
    public DomainObject getNewRecord(ResultSet rs) throws SQLException {
        return new EvaluationItem(
            new Evaluation(
                rs.getLong(this.evaluation.getAlias() + ".id"),
                rs.getObject(this.evaluation.getAlias() + ".evaluationDate", java.time.LocalDate.class),
                rs.getString(this.evaluation.getAlias() + ".conditions"),
                rs.getDouble(this.evaluation.getAlias() + ".athleteWeight"),
                null,
                null,
                null
            ),
            rs.getInt(getAlias() + ".serial"),
            rs.getLong(getAlias() + ".result"),
            new Activity(
                rs.getLong(this.activity.getAlias() + ".id"),
                rs.getString(this.activity.getAlias() + ".name"),
                rs.getString(this.activity.getAlias() + ".unit")
            )
        );
    }


    @Override
    public String getPrimaryKey() {
        return "evaluation = " + evaluation.getId() + " AND serial = " + serial;
    }

    @Override
    public String getJoinClause() {
        return "JOIN " +
            this.activity.getTableName() +
            " " +
            this.activity.getAlias() +
            " ON " +
            this.activity.getAlias() +
            ".id = " +
            this.getAlias() +
            ".activity " +
            "JOIN " +
            this.evaluation.getTableName() +
            " " +
            this.evaluation.getAlias() +
            " ON " +
            this.evaluation.getAlias() +
            ".id = " +
            this.getAlias() +
            ".evaluation";
    }


    @Override
    public String getAlias() {
        return "ei";
    }

    @Override
    public String getColumnNameByIndex(int i) {
        String[] cols = {"evaluation", "serial", "result", "activity"};
        return cols[i];
    }

}
