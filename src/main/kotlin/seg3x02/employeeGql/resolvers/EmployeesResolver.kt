package seg3x02.employeeGql.resolvers
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.stereotype.Controller
import seg3x02.employeeGql.repository.EmployeesRepository
import seg3x02.employeeGql.resolvers.types.CreateEmployeeInput
import seg3x02.employeeGql.entity.Employee
import java.util.*
@Controller
class EmployeesResolver(private val employeesRepository: EmployeesRepository) {
    @MutationMapping
    fun newEmployee(@Argument createEmployeeInput: CreateEmployeeInput): Employee {
        val employee = Employee(
            name = createEmployeeInput.name ?: "",
            city = createEmployeeInput.city ?: "",
            dateOfBirth = createEmployeeInput.dateOfBirth ?: "",
            salary = createEmployeeInput.salary ?: 0.0f,
            email = createEmployeeInput.email ?: "",
            gender = createEmployeeInput.gender ?: ""
        )
        employee.id = UUID.randomUUID().toString()
        employeesRepository.save(employee)
        return employee
    }
    @QueryMapping
    fun employees(): List<Employee> = employeesRepository.findAll()
}
