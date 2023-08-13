package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTv, solutionTV;

    MaterialButton buttonC, buttonBracOpen,buttonBracClose;
    MaterialButton buttonDivide, buttonMultiply, buttonMinus, buttonPlus, buttonEquals;
    MaterialButton button7, button8, button9, button4, button5,button6,button1,button2,button3,button0;
    MaterialButton buttonAC,buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTV=findViewById(R.id.solution_tv);

        assignId(buttonC,R.id.c_button);
        assignId(buttonBracOpen,R.id.open_bracket);
        assignId(buttonBracClose,R.id.close_bracket);
        assignId(buttonDivide,R.id.divide);
        assignId(buttonMultiply,R.id.multiply);
        assignId(buttonMinus,R.id.minus);
        assignId(buttonPlus,R.id.plus);
        assignId(buttonEquals,R.id.equals);
        assignId(button7,R.id.seven_button);
        assignId(button8,R.id.eight_button);
        assignId(button9,R.id.nine_button);
        assignId(button4,R.id.four_button);
        assignId(button5,R.id.five_button);
        assignId(button6,R.id.six_button);
        assignId(button1,R.id.one_button);
        assignId(button2,R.id.two_button);
        assignId(button3,R.id.three_button);
        assignId(button0,R.id.zero_button);
        assignId(buttonDot,R.id.dot_button);
        assignId(buttonAC,R.id.AC_button);
    }

    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText=button.getText().toString();
        String dataToCalculate = solutionTV.getText().toString();

        if(buttonText.equals("AC"))
        {
            solutionTV.setText("");
            resultTv.setText("0");
            return;
        }

        if(buttonText.equals("="))
        {
            solutionTV.setText(resultTv.getText());
            return;
        }
        
        if(buttonText.equals("C"))
        {
            dataToCalculate=dataToCalculate.substring(0,dataToCalculate.length()-1);
        }
        else {
            dataToCalculate=dataToCalculate+buttonText;
        }
        solutionTV.setText(dataToCalculate);

        String finalResult=getResult(dataToCalculate);

        if(!finalResult.equals("err"))
        {
            resultTv.setText(finalResult);
        }
    }
    String getResult(String data)
    {
        try {
            Context context= Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable =context.initStandardObjects();
            String finalResult= context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0"))
            {
                finalResult=finalResult.replace(".0","");
            }
            return finalResult;
        }
        catch (Exception e)
        {
            return "err";
        }
    }
}
