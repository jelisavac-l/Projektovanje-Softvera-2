package domain;

import java.io.Serializable;

public class EvaluationItem extends ADomainClass implements Serializable {

    private Evaluation evaluation;
    private Integer serial;
    private Long result;
    private Activity activity;


    @Override
    public String getDBClassName() {
        return "EvaluationItems";
    }

    @Override
    public String getAttributeNames() {
        return "evaluation, serial, result, activity";
    }

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
}
