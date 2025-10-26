

import java.util.Scanner;
public class Task1_StudentGradeTracker {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int totalStudents, marksSum=0, maximum = 0, minimum= 1000;
        Double marksAverage = 0.0;
        String studentMax = null, studentMin= null;
        System.out.println("\n-----------------------------");
        System.out.println("===Students Grade Tracker===");
        System.out.println("-----------------------------");
        System.out.print("Enter the total number of students: ");
        totalStudents = input.nextInt();
        String[] studentNames = new String[totalStudents];
        Integer[] studentMarks = new Integer[totalStudents];

        for(int i=1; i<=totalStudents; i++){
            System.out.print("\n"+i+". Student Name: ");
            studentNames[i]=input.next();
            
            System.out.print("Student Marks: ");
            studentMarks[i]=input.nextInt();
        }
        for(int i = 0; i< totalStudents; i++){
            marksSum += studentMarks[i];
            if (minimum > studentMarks[i]) {
                minimum = studentMarks[i];
                studentMin = studentNames[i];
            }
            if (maximum < studentMarks[i])
            {
                maximum = studentMarks[i];
                studentMax = studentNames[i];
            }
        }
        marksAverage= (double) (marksSum/totalStudents);
        System.out.println("\n===Average Scores===\n");
        System.out.println("The average score of class is :"+ marksAverage);
        System.out.println("\n===Highest Scores===\n");
        System.out.println("Student with the highest scores :");
        System.out.println("Student Name: "+studentMax+"\nStudent Marks: "+maximum);
        System.out.println("\n===Lowest Scores===\n");
        System.out.println("Student with the lowest scores :");
        System.out.println("Student Name: "+studentMin+"\nStudent Marks: "+minimum);
        input.close();
    }
}