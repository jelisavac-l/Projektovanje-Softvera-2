package operations;

import domain.*;
import domain.DomainObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListRetrieverTest {

    @Test
    @DisplayName("All clubs list")
    void clubs() {
        List<DomainObject> la = ListRetriever.retrieveByClass(Club.class);
        assertNotNull(la);
        System.out.println("Found: " + la.size());
    }

    @Test
    @DisplayName("All activities list")
    void activity() {
        List<DomainObject> la = ListRetriever.retrieveByClass(Activity.class);
        assertNotNull(la);
        System.out.println("Found: " + la.size());
    }

    @Test
    @DisplayName("All athletes list")
    void athletes() {
        List<DomainObject> la = ListRetriever.retrieveByClass(Athlete.class);
        assertNotNull(la);
        System.out.println("Found: " + la.size());
    }

    @Test
    @DisplayName("All evaluations list")
    void evaluations() {
        List<DomainObject> la = ListRetriever.retrieveByClass(Evaluation.class);
        assertNotNull(la);
        System.out.println("Found: " + la.size());
    }

    @Test
    @DisplayName("All evaluators list")
    void evaluators() {
        List<DomainObject> la = ListRetriever.retrieveByClass(Evaluator.class);
        assertNotNull(la);
        System.out.println("Found: " + la.size());
    }

    @Test
    @DisplayName("All roles list")
    void roles() {
        List<DomainObject> la = ListRetriever.retrieveByClass(Role.class);
        assertNotNull(la);
        System.out.println("Found: " + la.size());
    }

    @Test
    @DisplayName("All ER list")
    void ERs() {
        List<DomainObject> la = ListRetriever.retrieveByClass(ER.class);
        assertNotNull(la);
        System.out.println("Found: " + la.size());
    }

    @Test
    @DisplayName("All items list")
    void items() {
        List<DomainObject> la = ListRetriever.retrieveByClass(EvaluationItem.class);
        assertNotNull(la);
        System.out.println("Found: " + la.size());
    }

    @Test
    @DisplayName("Retrieve with WHERE condition")
    void getWithWhere() {
        Club c = new Club(2L, null, null);
        List<DomainObject> la = ListRetriever.retrieveByClass(Athlete.class, c);
        System.out.println("Found: " + la.size());
    }
}