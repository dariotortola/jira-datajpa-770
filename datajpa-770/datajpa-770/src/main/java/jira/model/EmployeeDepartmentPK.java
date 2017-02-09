package jira.model;

import java.io.Serializable;
import java.util.Objects;

public class EmployeeDepartmentPK implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5706550869252818839L;
    private String departmentId;
    private String employeeId;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EmployeeDepartmentPK other = (EmployeeDepartmentPK) obj;
        return Objects.equals(departmentId, other.departmentId) && Objects.equals(employeeId, other.employeeId);
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId, employeeId);
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
