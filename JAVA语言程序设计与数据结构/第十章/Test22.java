class MyString{
    private char[] s;
    public MyString(char[] chars){
        this.s = chars;
    }
    public char charAt(int index){
        if(index < s.length){
            return s[index];
        }else{
            System.out.println("no found this char!");
            return '@';
        }
    }
    public int length(){
        return s.length;
    }

    // 截取父字符串的某一部分
    public MyString substring(int begin, int end){
        char[] s1 = new char[end - begin + 1];
        int b = 0;
        for(int a = begin -1; a < end ;a++,b++){
            s[b] = s[a];
        }
        MyString newString = new MyString(s1);
        return newString;
    }

    // 该字符串中的字母被转换成小写字母
    public MyString toLowerCase(){
        String str = String.valueOf(s);
        str.toLowerCase();
        char ss[] = str.toCharArray(); // 将字符串转为新的字符数组
        MyString newString = new MyString(ss);
        return newString;
    }
    public boolean equals(MyString s){
        return this.s.equals(s);
    }
    public static MyString valueOf(int i){
        char[] c = new char[1];
        c[0] = (char) (i + (int)'0');
        MyString newString = new MyString(c);
        return newString;
    }
}
