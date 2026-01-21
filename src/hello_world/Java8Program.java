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

		System.out.println("Q1 "
				+ employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting())));
		System.out.println(
				"Q2 " + employeeList.stream().map(Employee::getDepartment).distinct().collect(Collectors.toList()));
		System.out.println("Q3 " + employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge))));
		System.out.println("Q4 " + employeeList.stream().max(Comparator.comparingDouble(Employee::getSalary)).get());
		System.out.println("Q5 " + employeeList.stream().filter(e -> e.getYearOfJoining() > 2015).map(Employee::getName)
				.collect(Collectors.toList()));
		System.out.println("Q6 "
				+ employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting())));
		System.out.println("Q7 " + employeeList.stream().collect(
				Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary))));
		System.out.println("Q8 " + employeeList.stream()
				.filter(e -> e.getGender().equals("Male") && e.getDepartment().equals("Product Development"))
				.min(Comparator.comparingInt(Employee::getAge)).get());
		System.out
				.println("Q9 " + employeeList.stream().min(Comparator.comparingInt(Employee::getYearOfJoining)).get());
		System.out.println("Q10 " + employeeList.stream().filter(e -> e.getDepartment().equals("Sales And Marketing"))
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.counting())));
		System.out.println("Q11 " + employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary))));
		System.out.println("Q12 " + employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment,
				Collectors.mapping(Employee::getName, Collectors.toList()))));
		DoubleSummaryStatistics stats = employeeList.stream()
				.collect(Collectors.summarizingDouble(Employee::getSalary));
		System.out.println("Q13 Avg=" + stats.getAverage() + ", Total=" + stats.getSum());
		Map<Boolean, List<Employee>> part = employeeList.stream()
				.collect(Collectors.partitioningBy(e -> e.getAge() <= 25));
		System.out.println("Q14 <=25=" + part.get(true));
		System.out.println("Q14 >25=" + part.get(false));
		Employee oldest = employeeList.stream().max(Comparator.comparingInt(Employee::getAge)).get();
		System.out.println("Q15 " + oldest.getName() + ", Age=" + oldest.getAge() + ", Dept=" + oldest.getDepartment());
	}
}
