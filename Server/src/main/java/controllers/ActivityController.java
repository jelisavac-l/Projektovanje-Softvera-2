package controllers;

import domain.Activity;
import domain.DomainObject;
import operations.ListRetriever;
import operations.activity.ActivityCreation;
import operations.activity.ActivityDeletion;
import operations.activity.ActivityUpdate;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ActivityController {

    public static List<Activity> getList() {
        List<Activity> activities = new LinkedList<>();
        List<DomainObject> ldo = ListRetriever.retrieveByClass(Activity.class);
        if(ldo != null)
            ldo.forEach(d -> activities.add((Activity) d));
        return activities;
    }

    public static Activity getById(Long activityId) {
        Activity activity = new Activity(activityId, null, null);
        var list = ListRetriever.retrieveByClass(Activity.class, activity);
        if(list != null)
            return (Activity) list.get(0);
        else return null;
    }

    public static void add(Activity activity) throws SQLException {
        new ActivityCreation().commonExecution(activity);
    }

    public static void update(Activity activity) throws SQLException {
        new ActivityUpdate().commonExecution(activity);
    }

    public static void delete(Activity activity) throws SQLException {
        new ActivityDeletion().commonExecution(activity);
    }

}
