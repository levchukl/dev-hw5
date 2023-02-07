package org.example.model;

public class LongestProject {
    private int name;
    private int month_count;

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getMonth_count() {
        return month_count;
    }

    public void setMonth_count(int month_count) {
        this.month_count = month_count;
    }

    @Override
    public String toString() {
        return "LongestProject{" +
                "name='" + name + '\'' +
                ", month_count=" + month_count +
                '}';
    }
}
