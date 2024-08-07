package arc.haldun.ik.applicationform.info.academicstate;

import java.io.Serializable;

/**
 * This class represents high school, university and master
 */

public class High extends First implements Serializable {

    private String branch;
    private String degree;

    public static High EMPTY = new High("", "", "", "", "","");

    public High(String startDate, String endDate, String name, String region, String branch, String degree) {
        super(startDate, endDate, name, region);

        this.branch = branch;
        this.degree = degree;

        checkFields();
    }

    @Override
    protected void checkFields() {
        //super.checkFields();

        if (branch == null) branch = "";
        if (degree == null) degree = "";
    }

    public String getBranch() {
        return branch;
    }

    public String getDegree() {
        return degree;
    }
}
