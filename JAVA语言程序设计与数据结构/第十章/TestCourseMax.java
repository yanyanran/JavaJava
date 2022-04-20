import java.util.Scanner;
class NewCourse{
    private String courseName;
    private String[] students = new String[100];
    private int numberOfStudent;

    public  NewCourse(String courseName){
        this.courseName = courseName;
    }

    //newMax改进：返回一个数组，该数组长度和课程中的选课学生人数一样（创建一个新数组把学生拷贝进去）
    public String[] getStudent(){  //返回选这门课的所有学生
        String[] newStudent = new String[numberOfStudent];
        for(int i = 0; i <= numberOfStudent; i++){
            newStudent[i] = this.students[i];
        }
        return newStudent;
    }

    public int getNumberOfStudent(){  //返回学生个数
        return numberOfStudent;
    }
    public String getCourseName(){  //返回课程名字
        return courseName;
    }

    //newMax改进：在数组没有更多空间添加更多学生时可以自动增加数组大小（创建一个新的更大的数组并复制当前数组内容来实现）
    public void addStudent(String student){  //从某门课添加一个学生
        if(numberOfStudent < students.length){
            students[numberOfStudent] = student;numberOfStudent++;
        }else{
            String[] students = new String[numberOfStudent * 2];
            for(int i = 0; i< numberOfStudent; i++){
                students[i] = this.students[i];
            }
            students[numberOfStudent] = student;
            numberOfStudent++;
            this.students = students;  // 覆盖
        }
    }

    public void dropStudent(String student){  //从某门课删除一个学生
        //Programming
        //遍历数组找名为student的学生
        for(int i = 0; i < numberOfStudent; i++){
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

    //newMax 清除选某门课程的所有学生
    public void clear(){
        this.students = new String[100];
        this.numberOfStudent = 0;
    }
}

public class TestCourseMax { //测试类
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
