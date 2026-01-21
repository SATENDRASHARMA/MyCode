package hello_world;

import java.util.ArrayList;
import java.util.List;

import java.util.*;
import java.util.stream.*;

public class Java8Program {
	public static void main(String[] args) {
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
		employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
		employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
		employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
		employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
		employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
		employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
		employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
		employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
		employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
		employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
		employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
		employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
		employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
		employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
		employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
		employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));

		// 1. Count employees by gender.
//		    Groups employees by gender and counts how many are in each group.
		System.out.println("Q1 "
				+ employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting())));

		// 2. List all unique departments.
//		    Extracts and prints all distinct department names.
		System.out.println(
				"Q2 " + employeeList.stream().map(Employee::getDepartment).distinct().collect(Collectors.toList()));

		// 3. Average age of employees by gender.
//		    Calculates the average age for each gender group.
		System.out.println("Q3 " + employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge))));

		// 4. Find the highest paid employee.
//		    Selects the employee with the maximum salary.
		System.out.println("Q4 " + employeeList.stream().max(Comparator.comparingDouble(Employee::getSalary)).get());

		// 5. List names of employees who joined after 2015.
//		    Filters employees by year of joining and lists their names.
		System.out.println("Q5 " + employeeList.stream().filter(e -> e.getYearOfJoining() > 2015).map(Employee::getName)
				.collect(Collectors.toList()));

		// 6. Count employees in each department.
//		    Groups employees by department and counts the number in each.
		System.out.println("Q6 "
				+ employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting())));

		// 7. Average salary by department.
//		    Calculates the average salary for each department.
		System.out.println("Q7 " + employeeList.stream().collect(
				Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary))));

		// 8. Youngest male employee in Product Development.
//		    Finds the youngest male employee in the Product Development department.
		System.out.println("Q8 " + employeeList.stream()
				.filter(e -> e.getGender().equals("Male") && e.getDepartment().equals("Product Development"))
				.min(Comparator.comparingInt(Employee::getAge)).get());

		// 9. Employee with earliest joining year.
//		    Finds the employee who joined the company first.
		System.out
				.println("Q9 " + employeeList.stream().min(Comparator.comparingInt(Employee::getYearOfJoining)).get());

		// 10. Count male and female employees in Sales And Marketing.
//		     Groups employees in Sales And Marketing by gender and counts each group.
		System.out.println("Q10 " + employeeList.stream().filter(e -> e.getDepartment().equals("Sales And Marketing"))
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.counting())));

		// 11. Average salary by gender.
//		     Calculates the average salary for male and female employees.
		System.out.println("Q11 " + employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary))));

		// 12. List employee names by department.
//		     Groups employees by department and lists their names.
		System.out.println("Q12 " + employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment,
				Collectors.mapping(Employee::getName, Collectors.toList()))));

		// 13. Overall salary statistics (average and total).
//		     Summarizes salary data for all employees.
		DoubleSummaryStatistics stats = employeeList.stream()
				.collect(Collectors.summarizingDouble(Employee::getSalary));
		System.out.println("Q13 Avg=" + stats.getAverage() + ", Total=" + stats.getSum());

		// 14. Partition employees by age (<=25 and >25).
//		     Splits employees into two groups based on age.
		Map<Boolean, List<Employee>> part = employeeList.stream()
				.collect(Collectors.partitioningBy(e -> e.getAge() <= 25));
		System.out.println("Q14 <=25=" + part.get(true));
		System.out.println("Q14 >25=" + part.get(false));

		// 15. Find the oldest employee and print details.
//		     Selects the oldest employee and displays their name, age, and department.
		Employee oldest = employeeList.stream().max(Comparator.comparingInt(Employee::getAge)).get();
		System.out.println("Q15 " + oldest.getName() + ", Age=" + oldest.getAge() + ", Dept=" + oldest.getDepartment());

		System.out.println("############################MAP#############################");

		// 16. Create a map of employees by ID.
//		     Maps employee ID to Employee object for fast lookup.
		Map<Integer, Employee> empById = employeeList.stream().collect(Collectors.toMap(Employee::getId, e -> e));
		System.out.println("MAP1 EmpById=" + empById);

		// 17. Group employees by department.
//		     Maps each department to a list of its employees.
		Map<String, List<Employee>> empByDept = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment));
		System.out.println("MAP2 EmpByDept=" + empByDept);

		// 18. Calculate total salary by department.
//		     Sums salaries for each department.
		Map<String, Double> totalSalaryByDept = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.summingDouble(Employee::getSalary)));
		System.out.println("MAP3 TotalSalaryByDept=" + totalSalaryByDept);

		// 19. Average age by gender.
//		     Calculates average age for each gender.
		Map<String, Double> avgAgeByGender = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
		System.out.println("MAP4 AvgAgeByGender=" + avgAgeByGender);

		// 20. Map employee name to salary.
//		     Creates a map from employee name to their salary.
		Map<String, Double> salaryByName = employeeList.stream()
				.collect(Collectors.toMap(Employee::getName, Employee::getSalary));
		System.out.println("MAP5 SalaryByName=" + salaryByName);

		// 21. Find highest paid employee by department.
//		     For each department, finds the employee with the highest salary.
		Map<String, Optional<Employee>> highestPaidByDept = employeeList.stream().collect(Collectors.groupingBy(
				Employee::getDepartment, Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));
		System.out.println("MAP6 HighestPaidByDept=" + highestPaidByDept);

		// 22. Group employees by year of joining.
//		     Maps each joining year to a list of employees who joined that year.
		Map<Integer, List<Employee>> empByJoinYear = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getYearOfJoining));
		System.out.println("MAP7 EmpByJoiningYear=" + empByJoinYear);

		// 23. Count employees by department.
//		     Counts how many employees are in each department.
		Map<String, Long> countByDept = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
		System.out.println("MAP8 CountByDept=" + countByDept);

	}
}
