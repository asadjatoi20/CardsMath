package com.example.cardsmath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Stack;

public class Game extends AppCompatActivity {
    String[] files;
    String[] fileValue;
    String expr; // expressionString
    String exprWithSpace;
    int score; // sum of three cards
    ImageView card1;
    ImageView card2;
    ImageView card3;
    int c1Value, c2Value, c3Value; //score on every card
    EditText expression;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // to hide the top screen
        setContentView(R.layout.activity_game);
        files = getResources().getStringArray(R.array.fileNames); // obtaining static array
        fileValue = getResources().getStringArray(R.array.fileScore); // obtaining scores on cards
        card1 = findViewById(R.id.crd1);
        card2 = findViewById(R.id.crd2);
        card3 = findViewById(R.id.crd3);
        randomGenerate();
        //Toast.makeText(this, c1Value+","+c2Value+","+c3Value, Toast.LENGTH_LONG).show();
        //expression = findViewById(R.id.expression);


    }
    public void answerCheck(View v){
        expression = findViewById(R.id.expression);
        expr = expression.getText().toString();

        if(checkExpression(expr)){ // if expression is right check win condition
            //Toast.makeText(this, "Expression is well", Toast.LENGTH_LONG).show();

            //evaluate the expression
            ///evaluate(expr);
            if(evaluate(exprWithSpace)==score) {

                Intent intent = new Intent(Game.this, Win.class);
                startActivity(intent);
                finish();
            }
            else{
                Toast.makeText(this, "Expression was near", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Game.this, Loss.class);
                startActivity(intent);
                finish();
            }
        }
        else{
            Toast.makeText(this, "Bad Expression", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Game.this, Loss.class);
            startActivity(intent);
            finish();
        }
    }
    public boolean checkExpression(String exp) {
        // first get all operands not operations
        int index = 0;
        exprWithSpace="";
        // finding total number of operands in order to create array
        for (int k = 0; k < exp.length(); k++) {
            if (Character.isDigit(exp.charAt(k))) {

                exprWithSpace = exprWithSpace+""+exp.charAt(k);
                if (k != exp.length() - 1) {
                    if (Character.isDigit(exp.charAt(k + 1))) {// since we have only max 2 figure score
                        k++;
                        exprWithSpace = exprWithSpace+""+exp.charAt(k);
                    }
                }
                index++;
            }
            else{
                exprWithSpace = exprWithSpace+" "+exp.charAt(k)+" ";
            }
        }
        String[] operands = new String[index];
        int newIndex = 0;
        boolean occupied = false; // true if the next digit is a number
        String extra = "";
        for (int k = 0; k < exp.length(); k++) {
            extra = "";
            if (Character.isDigit(exp.charAt(k))) {
                if (k != exp.length() - 1) {
                    if (Character.isDigit(exp.charAt(k + 1))) {
                        extra = exp.charAt(k + 1) + ""; // extra num saved here
                        occupied = true;
                    }
                }
                operands[newIndex] = exp.charAt(k) + "";
                if (occupied) {
                    operands[newIndex] = operands[newIndex] + "" + extra;
                    k++;
                }
                newIndex++;

            }
        }
        // operands obtained
        // now comparing the operands
        for (int i = 0; i < operands.length; i++) {
            for (int j = i + 1; j <= operands.length - 1; j++) {
                if (operands[i].equalsIgnoreCase(operands[j])) {
                    Toast.makeText(this, "Operands used more than once. . .", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }
        // match operands with card values
        boolean condition1 = false, condition2 = false, condition3 = false; // 3condtion for 3 cards similiarity
        if(operands.length==3){ // if three operands
            for (int i = 0; i < operands.length; i++) { // since all operands are unequal therefore the execution arrived here . . .
                if (operands[i].equalsIgnoreCase(c1Value + "")) {
                    condition1 = true;
                } else if (operands[i].equalsIgnoreCase(c2Value + "")) {
                    condition2 = true;
                } else if (operands[i].equalsIgnoreCase(c3Value + "")) {
                    condition3 = true;
                }
            }
            if (condition1 == true && condition2 == true && condition3 == true)
                return true; // if all conditions match
        }
        else if(operands.length==2){ // if 2 operands
            for (int i = 0; i < operands.length; i++) { // since all operands are unequal therefore the execution arrived here . . .
                if (operands[i].equalsIgnoreCase(c1Value + "")) {
                    condition1 = true;
                } else if (operands[i].equalsIgnoreCase(c2Value + "")) {
                    condition2 = true;
                } else if (operands[i].equalsIgnoreCase(c3Value + "")) {
                    condition3 = true;
                }
            }
            if ((condition1 == true && condition2 == true ) || (condition1 == true && condition3==true) || (condition2==true && condition3==true))
                return true; // if all conditions match
        }
        return false;
    }
    public void scrambleBtn(View v){ // generates new randoms

        randomGenerate();
    }
    public void randomGenerate(){
        // 3 cards = 3 random variables
        Random rn = new Random();
        int c1 = rn.nextInt(26);
        int c2 = rn.nextInt(26);
        int c3 = rn.nextInt(26);
        c1Value = Integer.parseInt(fileValue[c1]); // obtaining score of random card
        c2Value = Integer.parseInt(fileValue[c2]);
        c3Value = Integer.parseInt(fileValue[c3]);
        this.score = c1Value+c2Value+c3Value;
        String s1,s2,s3;
        s1 = files[c1];
        s2 = files[c2];
        s3 = files[c3];

        //card1.setImageDrawable();

        int i = getResources().getIdentifier("com.example.cardsmath:drawable/" + s1, null, null);
        int j = getResources().getIdentifier("com.example.cardsmath:drawable/" + s2, null, null);
        int k = getResources().getIdentifier("com.example.cardsmath:drawable/" + s3, null, null);
        card1.setImageResource(i);
        card2.setImageResource(j);
        card3.setImageResource(k);
    }
    ///////////////////////////////////////////Data Structure part
    public static int evaluate(String expression)
    {
        char[] tokens = expression.toCharArray();
        // split by 'space'
        // Stack for numbers: 'values'
        Stack<Integer> values = new Stack<Integer>();

        // Stack for Operators: 'ops'
        Stack<Character> ops = new Stack<Character>();

        for (int i = 0; i < tokens.length; i++)
        {
            // Current token is a whitespace, skip it
            if (tokens[i] == ' ')
                continue;

            // Current token is a number, push it to stack for numbers
            if (tokens[i] >= '0' && tokens[i] <= '9')
            {
                StringBuffer sbuf = new StringBuffer();
                // There may be more than one digits in number
                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
                    sbuf.append(tokens[i++]);
                values.push(Integer.parseInt(sbuf.toString()));
            }

            // Current token is an opening brace, push it to 'ops'
            else if (tokens[i] == '(')
                ops.push(tokens[i]);

                // Closing brace encountered, solve entire brace
            else if (tokens[i] == ')')
            {
                while (ops.peek() != '(')
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            }

            // Current token is an operator.
            else if (tokens[i] == '+' || tokens[i] == '-' ||
                    tokens[i] == '*' || tokens[i] == '/')
            {
                // While top of 'ops' has same or greater precedence to current
                // token, which is an operator. Apply operator on top of 'ops'
                // to top two elements in values stack
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));

                // Push current token to 'ops'.
                ops.push(tokens[i]);
            }
        }

        // Entire expression has been parsed at this point, apply remaining
        // ops to remaining values
        while (!ops.empty())
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));

        // Top of 'values' contains result, return it
        return values.pop();
    }

    // Returns true if 'op2' has higher or same precedence as 'op1',
    // otherwise returns false.
    public static boolean hasPrecedence(char op1, char op2)
    {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

    // A utility method to apply an operator 'op' on operands 'a'
    // and 'b'. Return the result.
    public static int applyOp(char op, int b, int a)
    {
        switch (op)
        {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new
                            UnsupportedOperationException("Cannot divide by zero");
                return a / b;
        }
        return 0;
    }
}