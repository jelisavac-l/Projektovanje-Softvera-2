package transfer;

import domain.DomainObject;

import java.io.Serializable;
import java.util.List;

public class TransferObject implements Serializable {

    private DomainObject domainObject;
    private List<DomainObject> domainObjectList;
    private String message;
    private Boolean signal;
    private Integer currentRecord;
    private String systemOperationName;
    private String whereClause;
    private String query;

    public DomainObject getDomainObject() {
        return domainObject;
    }

    public void setDomainObject(DomainObject domainObject) {
        this.domainObject = domainObject;
    }

    public List<DomainObject> getDomainObjectList() {
        return domainObjectList;
    }

    public void setDomainObjectList(List<DomainObject> domainObjectList) {
        this.domainObjectList = domainObjectList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSignal() {
        return signal;
    }

    public void setSignal(Boolean signal) {
        this.signal = signal;
    }

    public Integer getCurrentRecord() {
        return currentRecord;
    }

    public void setCurrentRecord(Integer currentRecord) {
        this.currentRecord = currentRecord;
    }

    public String getSystemOperationName() {
        return systemOperationName;
    }

    public void setSystemOperationName(String systemOperationName) {
        this.systemOperationName = systemOperationName;
    }

    public String getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
