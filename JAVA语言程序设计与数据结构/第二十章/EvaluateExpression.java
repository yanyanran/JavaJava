import java.util.Stack;

public class EvaluateExpression {
    public static void main(String[] args){
        if(args.length != 1){
            System.out.println("Usage: java EvaluateExpression \"expression \"");
            System.exit(1);
        }
        try{
            System.out.println(evaluateExpression(args[0]));
        }
        catch(Exception e){
            System.out.println("Wrong expression: " + args[0]);
        }
    }

    public static int evaluateExpression(String expression){
        Stack<Integer> operandStack = new Stack<>();    // new Stack1
        Stack<Character> operatorStack = new Stack<>();    // new Stack2

        //提取被空格分隔的操作符、操作数和括号
        expression = insertBlanks(expression);    //保证操作符、操作数、括号至少被一个空格分隔
        String[] tokens = expression.split("");

        for(String token: tokens){
            if(token.length() == 0)
                continue;   //标记为空，跳过
            else if(token.charAt(0) == '+' || token.charAt(0) == '-'){  // + or -
                while(!operandStack.isEmpty() &&
                        (operatorStack.peek() == '+' ||
                         operatorStack.peek() == '-' ||
                         operatorStack.peek() == '*' ||
                         operatorStack.peek() == '/')){
                    processAnOperator(operandStack,operatorStack);
                }
                operatorStack.push(token.charAt(0));    //压入operatorStack
            }
            else if(token.charAt(0) == '*' || token.charAt(0) == '/'){  // * or /
                while(!operatorStack.isEmpty() &&
                      (operatorStack.peek() == '*' ||
                       operatorStack.peek() == '/')){
                    processAnOperator(operandStack,operatorStack);
                }
                operatorStack.push(token.charAt(0));    //压入operatorStack
            }
            else if(token.trim().charAt(0) == '('){     // (
                operatorStack.push('(');    ////压入operatorStack
            }else if(token.trim().charAt(0) == ')'){    // )
                while(operatorStack.peek() != '('){     //处理栈顶的所有操作符直到看到）符号为止
                    processAnOperator(operandStack, operatorStack);
                }
                operatorStack.pop(); // 然后从栈中弹出）
            }
            else{
                operandStack.push(new Integer(token));  //标记是操作数，压入operandStack
            }
        }
        while(!operatorStack.isEmpty()){    //处理剩余的操作符
            processAnOperator(operandStack, operatorStack);
        }
        return operandStack.pop();
    }

    //处理一个操作符
    //从operatorStack中弹出一个操作符，从operandStack弹出两个操作数。然后完成对应的操作后，将结果压回operandStack中
    public static void processAnOperator(Stack<Integer> operandStack,Stack<Character> operatorStack){
        char op = operatorStack.pop();
        int op1 = operandStack.pop();
        int op2 = operandStack.pop();
        if(op == '+')
            operandStack.push(op2 + op1);
        else if(op == '-')
            operandStack.push(op2 - op1);
        else if(op == '*')
            operandStack.push(op2 * op1);
        else if(op == '/')
            operandStack.push(op2 / op1);
    }

    //保证操作符、操作数、括号至少被一个空格分隔
    public static String insertBlanks(String s){
        String result = "";

        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(' || s.charAt(i) == ')'||
               s.charAt(i) == '+' || s.charAt(i) == '-'||
               s.charAt(i) == '*' || s.charAt(i) == '/')
                result += " " + s.charAt(i) + " ";
            else
                result += s.charAt(i);
        }
        return result;
    }
}