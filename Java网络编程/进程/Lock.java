public class Lock {
    public static void main(String[] args) throws Exception {
        var ts = new Thread[] {
                new AddStudentThread(), new DecStudentThread(), new AddTeacherThread(), new DecTeacherThread()
        };
        for (var t : ts) {
            t.start();
        }
        for (var t : ts) {
            t.join();
        }
        System.out.println(Counter1.studentCount);
        System.out.println(Counter1.teacherCount);
    }
}

class Counter1 {
    public static final Object lock = new Object();
    public static int studentCount = 0;
    public static int teacherCount = 0;
}

class AddStudentThread extends Thread {
    public void run() {
        for (int i=0; i<10000; i++) {
            synchronized(Counter1.lock) {
                Counter1.studentCount += 1;
            }
        }
    }
}

class DecStudentThread extends Thread {
    public void run() {
        for (int i=0; i<10000; i++) {
            synchronized(Counter1.lock) {
                Counter1.studentCount -= 1;
            }
        }
    }
}

class AddTeacherThread extends Thread {
    public void run() {
        for (int i=0; i<10000; i++) {
            synchronized(Counter1.lock) {
                Counter1.teacherCount += 1;
            }
        }
    }
}

class DecTeacherThread extends Thread {
    public void run() {
        for (int i=0; i<10000; i++) {
            synchronized(Counter1.lock) {
                Counter1.teacherCount -= 1;
            }
        }
    }
}
