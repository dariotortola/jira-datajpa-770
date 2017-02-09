package jira;

import java.io.Serializable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.data.rest.webmvc.spi.BackendIdConverter;

import jira.model.EmployeeDepartment;
import jira.model.EmployeeDepartmentPK;
import jira.repository.EmployeeDepartmentRepository;

@Configuration
public class FixConfig extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.withEntityLookup().forRepository(EmployeeDepartmentRepository.class, (EmployeeDepartment ed) -> {
            EmployeeDepartmentPK pk = new EmployeeDepartmentPK();
            pk.setDepartmentId(ed.getDepartmentId());
            pk.setEmployeeId(ed.getEmployeeId());
            return pk;
        }, EmployeeDepartmentRepository::findOne);
    }

    @Bean
    public BackendIdConverter employeeDepartmentIdConverter() {
        return new BackendIdConverter() {

            @Override
            public boolean supports(Class<?> delimiter) {
                return EmployeeDepartment.class.equals(delimiter);
            }

            @Override
            public String toRequestId(Serializable id, Class<?> entityType) {
                EmployeeDepartmentPK pk = (EmployeeDepartmentPK) id;
                return String.format("%s_%s", pk.getEmployeeId(), pk.getDepartmentId());
            }

            @Override
            public Serializable fromRequestId(String id, Class<?> entityType) {
                if (id == null){
                    return null;
                }
                String[] parts = id.split("_");
                EmployeeDepartmentPK pk = new EmployeeDepartmentPK();
                pk.setEmployeeId(parts[0]);
                pk.setDepartmentId(parts[1]);
                return pk;
            }
        };
    }
}
