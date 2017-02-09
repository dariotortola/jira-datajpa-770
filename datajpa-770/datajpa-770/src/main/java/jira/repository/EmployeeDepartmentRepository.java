package jira.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import jira.model.EmployeeDepartment;
import jira.model.EmployeeDepartmentPK;

public interface EmployeeDepartmentRepository
        extends PagingAndSortingRepository<EmployeeDepartment, EmployeeDepartmentPK> {

}
