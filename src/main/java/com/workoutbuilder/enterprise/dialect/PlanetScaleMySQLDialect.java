package com.workoutbuilder.enterprise.dialect;
import org.hibernate.dialect.MySQLDialect;

public class PlanetScaleMySQLDialect extends MySQLDialect {

    @Override
    public String getAddForeignKeyConstraintString(
            String constraintName,
            String[] foreignKey,
            String referencedTable,
            String[] primaryKey,
            boolean referencesPrimaryKey) {
        // PlanetScale does not support foreign keys, by returning an empty string we prevent Hibernate from creating them
        return "";
    }
}