import java.util.Scanner;
class Course{
    private String courseName;
    private String[] students = new String[100];
    private int numberOfStudent;

    public Course(String courseName){
        this.courseName = courseName;
    }
    public String[] getStudent(){  //返回选这门课的所有学生
        return students;
    }
    public int getNumberOfStudent(){  //返回学生个数
        return numberOfStudent;
    }
    public String getCourseName(){  //返回课程名字
        return courseName;
    }
    public void addStudent(String student){  //从某门课添加一个学生
        students[numberOfStudent] = student;
        numberOfStudent++;
    }
    public void dropStudent(String student){  //从某门课删除一个学生
        //Programming
        //遍历数组找名为student的学生
        for(int i = 0;i < numberOfStudent;i++){
            if(students[i] == student)
            {
                for(int j = i + 1;j < numberOfStudent;i++,j++)
                {
                    //将后边的学生向前移动一位
                    students[i] = students[j];
                }
                break;
            }
        }
        numberOfStudent--;
    }
}

public class TestCourse { //测试类
    public static void main(String[] args){
        Course course1 = new Course("Data structures");
        Course course2 = new Course("Database Systems");

        course1.addStudent("Peter");
        course1.addStudent("Jim");
        course1.addStudent("Anne");

        course2.addStudent("Peter");
        course2.addStudent("Steve");
        course2.addStudent("Meredith");
        course2.addStudent("Mike");

        //1 第一门课程
        System.out.println("The course1's name is: " + course1.getCourseName());
        System.out.println("Number of students in course1: " + course1.getNumberOfStudent());
        //getStudent course1
        String[] students = course1.getStudent();
        for(int i = 0; i < course1.getNumberOfStudent(); i++){
            System.out.print(students[i]);
            if(i!=course1.getNumberOfStudent()-1)
            {
                System.out.print(", ");
            }
        }

        System.out.println( );

        //2 第二门课程
        System.out.println("The course2's name is: " + course2.getCourseName());
        System.out.println("Number of students in course2: " + course2.getNumberOfStudent());
        //getStudent course2
        String[] studentss = course2.getStudent();
        for(int i = 0; i < course2.getNumberOfStudent(); i++){
            System.out.print(students[i]);
            if(i!=course2.getNumberOfStudent()-1)
            {
                System.out.print(", ");
            }
        }

        System.out.println( );

        //3 删除学生
        System.out.println("delete someone now ? (1:yes  0:no)");
        Scanner input = new Scanner(System.in);
        int INPUT = input.nextInt();
        if(INPUT == 1)
        {
            course2.dropStudent("Meredith");
            System.out.println("The course2's name is: " + course2.getCourseName());
            System.out.println("Number of students in course2: " + course2.getNumberOfStudent());
            //getStudent course2
            String[] studentsss = course2.getStudent();
            for(int i = 0; i < course2.getNumberOfStudent(); i++){
                System.out.print(students[i]);
                if(i!=course2.getNumberOfStudent()-1)
                {
                    System.out.print(", ");
                }
            }
        }

    }
}

/*
The course1's name is: Data structures
Number of students in course1: 3
Peter, Jim, Anne
The course2's name is: Database Systems
Number of students in course2: 4
Peter, Jim, Anne, null
delete someone now ? (1:yes  0:no)
1
The course2's name is: Database Systems
Number of students in course2: 3
Peter, Jim, Anne
*/