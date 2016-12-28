package com.github.sah4ez.exdb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Class for extract data safety from ResultSet
 *
 * @author sah4ez
 */
public class Extractor {
    private ResultSet rs = null;

    public Extractor(ResultSet rs) {
        this.rs = rs;
    }

    public Integer getInt(String columnName) {
        Integer i = 0;
        try {
            i = rs.getInt(columnName);
        } catch (SQLException ignored) {
        }
        return i;
    }

    public Integer getIntNull(String columnName) {
        Integer i = 0;
        try {
            i = rs.getInt(columnName);
            if (rs.wasNull())
                return null;
        } catch (SQLException ignored) {
        }
        return i;
    }

    public String getString(String columnName) {
        String s = "";
        try {
            s = rs.getString(columnName);
            if (s == null)
                return "";
        } catch (SQLException ignored) {
        }
        return s;
    }

    public String getStringNull(String columnName) {
        String s = "";
        try {
            s = rs.getString(columnName);
            if (rs.wasNull())
                return null;
        } catch (SQLException ignored) {
        }
        return s;
    }

    public LocalDateTime getLocalDateTime(String columnName) {
        Timestamp s = Timestamp.valueOf(LocalDateTime.MIN);
        try {
            s = rs.getTimestamp(columnName);
            if (s == null)
                return Timestamp.valueOf(LocalDateTime.MIN).toLocalDateTime();
        } catch (SQLException ignored) {
        }
        return s.toLocalDateTime();
    }

    public LocalDateTime getLocalDateTimeNull(String columnName) {
        Timestamp s = Timestamp.valueOf(LocalDateTime.MIN);
        try {
            s = rs.getTimestamp(columnName);
            if (rs.wasNull())
                return null;
        } catch (SQLException ignored) {
        }
        return s.toLocalDateTime();
    }

    public String getValue() {
        return getString("valuetext");
    }

    public Integer getVariableId() {
        return getInt("variableid");
    }

    public Float getFloat(String columnName) {
        Float f = 0.0F;
        try {
            f = rs.getFloat(columnName);
        } catch (SQLException ignored) {
        }
        return f;
    }

    public Float getFloatNull(String columnName) {
        Float f = 0.0F;
        try {
            f = rs.getFloat(columnName);
            if (rs.wasNull())
                return null;
        } catch (SQLException ignored) {
        }
        return f;
    }

    public Boolean getBoolean(String columnName) {
        boolean b = false;
        try {
            b = rs.getBoolean(columnName);
        } catch (SQLException ignored) {
        }
        return b;
    }

    public Boolean getBooleanNull(String columnName) {
        boolean b = false;
        try {
            b = rs.getBoolean(columnName);
            if (rs.wasNull())
                return null;
        } catch (SQLException ignored) {
        }
        return b;
    }

    public void check(String name) throws NotFoundColumnResultSetException {
        try {
            if (rs.findColumn(name) > 0) {
                return;
            }
        } catch (SQLException ignored) {
        }
        throw new NotFoundColumnResultSetException(name);
    }

    static class NotFoundColumnResultSetException extends Exception {
        private String message = "";

        public NotFoundColumnResultSetException(String columnName) {
            this.message = columnName;
        }

        @Override
        public String getMessage() {
            return String.format("Column with name '%s' not exist in ResultSet.", message);
        }
    }
}
