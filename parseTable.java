import java.util.*;
import java.io.*;

public class parseTable {

    public static void bottomUP() {

        System.out.println("Please enter a sentential form leaving a space in between tokens.  Your sentence should end with the character '$'.");
        System.out.println("Example: ( id * id ) $");
        System.out.println("Enter: ");
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        String [] grammar = input.split(" ");
        grammar = Arrays.stream(grammar).toArray(String[] ::new );
        List <String> grammarList = Arrays.asList(grammar);

        Stack <String> stack1 = new Stack <String>();
        Stack <String> stack2 = new Stack <String>();
        Stack <String> stack3 = new Stack <String>();

        stack1.push("0");

        while (true) {

            stack2.push(stack1.peek() + grammarList.get(0));

            if (stack2.peek().equals("0id")) {
                stack1.push("id");
                stack1.push("5");
                stack2.pop();
                grammarList = grammarList.subList(1, grammarList.size());
                continue;

            }else if (stack2.peek().equals("0(")) {
                stack1.push("(");
                stack1.push("4");
                stack2.pop();
                grammarList = grammarList.subList(1, grammarList.size());
                continue;

            }else if (stack2.peek().equals("1+")) {
                stack1.push("+");
                stack1.push("6");
                stack2.pop();
                grammarList = grammarList.subList(1, grammarList.size());
                continue;

            }else if (stack2.peek().equals("1$")) {
                System.out.println("accept");
                break;

            }else if (stack2.peek().equals("2+") || stack2.peek().equals("2)") || stack2.peek().equals("2$")) {
                //rule 2
                stack1.pop();
                stack1.pop();
                stack2.pop();
                stack1.push("E");
                //get first and second top elements of stack1
                stack3.push(stack1.get(stack1.size()-2) + stack1.peek());
                //goto rules
                if (stack3.peek().equals("0E")) {
                    stack1.push("1");
                    stack3.pop();
                    continue;

                }else if (stack3.peek().equals("4E")) {
                    stack1.push("8");
                    stack3.pop();
                    continue;

                }else
                    System.out.println("error");
                    break;

            }else if (stack2.peek().equals("2*")) {
                stack1.push("*");
                stack1.push("7");
                stack2.pop();
                grammarList = grammarList.subList(1, grammarList.size());
                continue;

            }else if (stack2.peek().equals("3+") || stack2.peek().equals("3*") || stack2.peek().equals("3)") || stack2.peek().equals("3$" )) {
                //rule 4
                stack1.pop();
                stack1.pop();
                stack2.pop();
                stack1.push("T");
                stack3.push(stack1.get(stack1.size()-2) + stack1.peek());

                if (stack3.peek().equals("0T")) {
                    stack1.push("2");
                    stack3.pop();
                    continue;

                }else if (stack3.peek().equals("4T")) {
                    stack1.push("2");
                    stack3.pop();
                    continue;

                }else if (stack3.peek().equals("6T")) {
                    stack1.push("9");
                    stack3.pop();
                    continue;

                }else
                    System.out.println("error");
                    break;

            }else if (stack2.peek().equals("4id")) {
                stack1.push("id");
                stack1.push("5");
                stack2.pop();
                grammarList = grammarList.subList(1, grammarList.size());
                continue;

            }else if (stack2.peek().equals("4(")) {
                stack1.push("(");
                stack1.push("4");
                stack2.pop();
                grammarList = grammarList.subList(1, grammarList.size());
                continue;

            }else if (stack2.peek().equals("5+") || stack2.peek().equals("5*") || stack2.peek().equals("5)") || stack2.peek().equals("5$")) {
                //rule 6
                stack1.pop();
                stack1.pop();
                stack2.pop();
                stack1.push("F");
                stack3.push(stack1.get(stack1.size()-2) + stack1.peek());

                if (stack3.peek().equals("0F")) {
                    stack1.push("3");
                    stack3.pop();
                    continue;

                }else if (stack3.peek().equals("4F")) {
                    stack1.push("3");
                    stack3.pop();
                    continue;

                }else if (stack3.peek().equals("6F")) {
                    stack1.push("3");
                    stack3.pop();
                    continue;

                }else if (stack3.peek().equals("7F")) {
                    stack1.push("10");
                    stack3.pop();
                    continue;

                }else
                    System.out.println("error");
                    break;

            }else if (stack2.peek().equals("6id") || stack2.peek().equals("7id")) {
                stack1.push("id");
                stack1.push("5");
                stack2.pop();
                grammarList = grammarList.subList(1, grammarList.size());
                continue;

            }else if (stack2.peek().equals("6(") || stack2.peek().equals("7(")) {
                stack1.push("(");
                stack1.push("4");
                stack2.pop();
                grammarList = grammarList.subList(1, grammarList.size());
                continue;

            }else if (stack2.peek().equals("8+")) {
                stack1.push("+");
                stack1.push("6");
                stack2.pop();
                grammarList = grammarList.subList(1, grammarList.size());
                continue;

            }else if (stack2.peek().equals("8)")) {
                stack1.push(")");
                stack1.push("11");
                stack2.pop();
                grammarList = grammarList.subList(1, grammarList.size());
                continue;

            }else if (stack2.peek().equals("9+") || stack2.peek().equals("9)") || stack2.peek().equals("9$")) {
                //enter rule 1
                //test in driver class
                while (!stack1.peek().equals("+")) {
                    stack1.pop();
                }
                stack1.pop();
                stack2.pop();
                continue;

            }else if (stack2.peek().equals("9*")) {
                stack1.push("*");
                stack1.push("7");
                stack2.pop();
                grammarList = grammarList.subList(1, grammarList.size());
                continue;

            }else if (stack2.peek().equals("10+") || stack2.peek().equals("10*") || stack2.peek().equals("10)") || stack2.peek().equals("10$")) {
                //rule 3
                while (!stack1.peek().equals("*")) {
                    stack1.pop();
                }
                stack1.pop();
                stack2.pop();
                continue;

            }else if (stack2.peek().equals("11+") || stack2.peek().equals("11*") || stack2.peek().equals("11)") || stack2.peek().equals("11$")) {
                //rule 5
                while (!stack1.peek().equals("(")) {
                    stack1.pop();
                }
                stack1.pop();
                stack2.pop();
                stack1.push("F");
                stack3.push(stack1.get(stack1.size()-2) + stack1.peek());

                if (stack3.peek().equals("0F")) {
                    stack1.push("3");
                    stack3.pop();
                    continue;

                }else if (stack3.peek().equals("4F")) {
                    stack1.push("3");
                    stack3.pop();
                    continue;

                }else if (stack3.peek().equals("6F")) {
                    stack1.push("3");
                    stack3.pop();
                    continue;

                }else if (stack3.peek().equals("7F")) {
                    stack1.push("10");
                    stack3.pop();
                    continue;

                }else
                    System.out.println("error");
                break;

            }else {
                System.out.println("error");
                break;
            }
        }
    }

    public static void main (String [] args) {

        bottomUP();

    }
}
