import java.util.*;

class Student {
    private int id;
    private String name;
    private double cgpa;

    public Student(int id, String name, double cgpa) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCGPA() {
        return cgpa;
    }
}

class Priorities {
    public List<Student> getStudents(List<String> events) {
        PriorityQueue<Student> priorityQueue = new PriorityQueue<>(
            Comparator.comparing(Student::getCGPA).reversed()
                .thenComparing(Student::getName).thenComparing(Student::getID)
        );

        List<Student> studentsYetToBeServed = new ArrayList<>();

        for (String event : events) {
            String[] parts = event.split(" ");
            if (parts[0].equals("ENTER")) {
                String name = parts[1];
                double cgpa = Double.parseDouble(parts[2]);
                int id = Integer.parseInt(parts[3]);
                priorityQueue.offer(new Student(id, name, cgpa));
            } else if (parts[0].equals("SERVED")) {
                priorityQueue.poll();
            }
        }

        while (!priorityQueue.isEmpty()) {
            studentsYetToBeServed.add(priorityQueue.poll());
        }

        return studentsYetToBeServed;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of events: ");
        int n = Integer.parseInt(scanner.nextLine());

        List<String> events = new ArrayList<>();

        System.out.print("\nEnter the events in one of the following formats:\nENTER name CGPA id\nSERVED\n\n");
        for (int i = 0; i < n; i++) {
            events.add(scanner.nextLine());
        }

        Priorities priorities = new Priorities();
        List<Student> studentsYetToBeServed = priorities.getStudents(events);

        if (studentsYetToBeServed.isEmpty()) {
            System.out.println("\nNo results found\n");
        } else {
            System.out.println("\nList of students:\n");
            for (Student student : studentsYetToBeServed) {
                System.out.println(student.getName());
            }
        }
    }
}
