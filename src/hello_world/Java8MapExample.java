package hello_world;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Java8MapExample {
	public static void main(String[] args) {
		// 1. Create and populate the employee map with sample Employee objects.
        // This map uses employee ID as the key and Employee object as the value.
		Map<Integer, Employee> employeeMap = new HashMap<>();
		employeeMap.put(111, new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
		employeeMap.put(122, new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
		employeeMap.put(133, new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
		employeeMap.put(144, new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
		employeeMap.put(155, new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));

		// 2. Print the original employee map to show all entries.
		System.out.println("ORIGINAL MAP:");
		employeeMap.forEach((k, v) -> System.out.println(k + " => " + v));

		// 3. Filter employees with salary greater than 25000.
//      Collects and prints only those employees whose salary exceeds 25,000.
		Map<Integer, Employee> highSalary = employeeMap.entrySet().stream().filter(e -> e.getValue().salary > 25000)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		System.out.println("\nFILTER SALARY > 25000:");
		highSalary.forEach((k, v) -> System.out.println(k + " => " + v));

		// 4. Group employees by their department.
//      Shows which employees belong to each department.
		Map<String, List<Employee>> groupByDept = employeeMap.values().stream()
				.collect(Collectors.groupingBy(e -> e.department));
		System.out.println("\nGROUP BY DEPARTMENT:");
		groupByDept.forEach((k, v) -> System.out.println(k + " => " + v));

		// 5. Calculate and display the total salary paid in each department.
//      Sums up the salaries of employees grouped by department.
		Map<String, Double> totalSalaryByDept = employeeMap.values().stream()
				.collect(Collectors.groupingBy(e -> e.department, Collectors.summingDouble(e -> e.salary)));
		System.out.println("\nTOTAL SALARY BY DEPARTMENT:");
		System.out.println(totalSalaryByDept);

		// 6. Sort employees by salary in descending order and display the sorted map.
//      Uses a LinkedHashMap to preserve the sorted order.
		Map<Integer, Employee> sortedBySalary = employeeMap.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.comparingDouble(Employee::getSalary).reversed()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));
		System.out.println("\nSORTED BY SALARY DESC:");
		sortedBySalary.forEach((k, v) -> System.out.println(k + " => " + v));

		// 7. Get the top 3 employees by salary and print their details.
//      Useful for finding the highest earners.
		List<Employee> top3 = employeeMap.values().stream().sorted((a, b) -> Double.compare(b.salary, a.salary))
				.limit(3).collect(Collectors.toList());
		System.out.println("\nTOP 3 SALARY:");
		System.out.println(top3);

		// 8. Create a reverse lookup map from employee name to ID.
//      Allows quick access to an employee's ID using their name.
		Map<String, Integer> reverseLookup = employeeMap.values().stream()
				.collect(Collectors.toMap(e -> e.name, e -> e.id));
		System.out.println("\nREVERSE LOOKUP (NAME -> ID):");
		System.out.println(reverseLookup);

		// 9. Update the salary for the employee with ID 144 by adding 5000.
//      Demonstrates how to modify an entry in the map.
		employeeMap.computeIfPresent(144, (id, emp) -> {
			emp.salary += 5000;
			return emp;
		});
		System.out.println("\nUPDATED SALARY FOR 144:");
		System.out.println(employeeMap.get(144));
	}
}
