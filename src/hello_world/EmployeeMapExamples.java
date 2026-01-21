package hello_world;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeMapExamples {
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

		// 1. Convert the List of employees to a Map with employee ID as the key.
//         This enables fast lookup by employee ID.
		Map<Integer, Employee> empMap = employeeList.stream().collect(Collectors.toMap(Employee::getId, e -> e));
		System.out.println("MAP: Key = EmpId, Value = Employee\n" + empMap);

		// 2. Filter employees with salary greater than 30,000.
//         Creates a new map containing only high-earning employees.
		Map<Integer, Employee> highSalaryMap = empMap.entrySet().stream().filter(e -> e.getValue().getSalary() > 30000)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		System.out.println("\nHigh Salary Map (>30000):\n" + highSalaryMap);

		// 3. Group employees by department.
//         Produces a map where each department maps to a list of its employees.
		Map<String, List<Employee>> deptGrouping = empMap.values().stream()
				.collect(Collectors.groupingBy(Employee::getDepartment));
		System.out.println("\nGroup By Department:\n" + deptGrouping);

		// 4. Calculate total salary paid per department.
//         Sums the salaries of all employees in each department.
		Map<String, Double> totalSalaryByDept = empMap.values().stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.summingDouble(Employee::getSalary)));
		System.out.println("\nTotal Salary by Department:\n" + totalSalaryByDept);

		// 5. Find the highest paid employee in each department.
//         Uses maxBy to select the employee with the highest salary per department.
		Map<String, Optional<Employee>> highestPaidByDept = empMap.values().stream().collect(Collectors.groupingBy(
				Employee::getDepartment, Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));
		System.out.println("\nHighest Paid Employee by Department:\n" + highestPaidByDept);

		// 6. Sort the employee map by salary in descending order.
//         Uses LinkedHashMap to maintain the sorted order for display.
		Map<Integer, Employee> sortedDesc = empMap.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.comparingDouble(Employee::getSalary).reversed()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));
		System.out.println("\nSorted by Salary DESC:\n" + sortedDesc);

		// 7. Convert the employee map back to a list.
//         Useful for operations that require sequential access.
		List<Employee> convertedList = new ArrayList<>(empMap.values());
		System.out.println("\nMAP -> LIST Conversion:\n" + convertedList);

		// 8. Create nested salary buckets within each department.
//         Groups employees by department, then by salary range ("HIGH" or "LOW").
		Map<String, Map<String, List<Employee>>> bucketMap = empMap.values().stream().collect(Collectors.groupingBy(
				Employee::getDepartment, Collectors.groupingBy(e -> (e.getSalary() > 30000) ? "HIGH" : "LOW")));
		System.out.println("\nDept -> (HIGH/LOW salary bucket):\n" + bucketMap);

	}
}
