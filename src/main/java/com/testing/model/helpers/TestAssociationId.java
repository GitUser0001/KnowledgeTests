package com.testing.model.helpers;

import java.io.Serializable;

/**
 * Created by Study on 17.05.2016.
 */

public class TestAssociationId implements Serializable {

    private int userId;
    private int testId;

    public int hashCode() {
        return userId + testId;
    }

    public boolean equals(Object object) {
        if (object instanceof TestAssociationId) {
            TestAssociationId otherId = (TestAssociationId) object;
            return (otherId.userId == this.userId) && (otherId.testId == this.testId);
        }
        return false;
    }
}
