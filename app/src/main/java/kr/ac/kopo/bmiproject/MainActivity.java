package kr.ac.kopo.bmiproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText user_name, user_weight, user_height;
    Button btn_calculation;
    TextView result;
    ImageView img_bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_name = findViewById(R.id.user_name);
        user_weight = findViewById(R.id.user_weight);
        user_height = findViewById(R.id.user_height);
        btn_calculation = findViewById(R.id.btn_calculation);
        result = findViewById(R.id.bmi_result);
        img_bmi = findViewById(R.id.img_bmi);

        btn_calculation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String name = user_name.getText().toString();
        String weightStr = user_weight.getText().toString();
        String heightStr = user_height.getText().toString();

        if (name.isEmpty() || weightStr.isEmpty() || heightStr.isEmpty()) {
            Toast.makeText(this, "모든 항목을 입력하세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            float weight = Float.parseFloat(weightStr);
            float heightCm = Float.parseFloat(heightStr);
            float heightM = heightCm / 100;
            float bmi = weight / (heightM * heightM);
            String bmiResult = String.format("%.1f", bmi);

            String category = "";
            int imageResId; // 기본 이미지

            if (bmi < 18.5) {
                category = "저체중";
                imageResId = R.drawable.underweight;
            } else if (bmi < 23) {
                category = "정상체중";
                imageResId = R.drawable.normal;
            } else if (bmi < 25) {
                category = "과체중";
                imageResId = R.drawable.overweight;
            } else if (bmi < 30) {
                category = "비만체중";
                imageResId = R.drawable.obese;
            } else {
                category = "고도비만체중";
                imageResId = R.drawable.extremely_obese;
            }

            String resultText = name + "님의 체중: " + weight + "kg, 키: " + heightCm + "cm\n"
                    + "BMI 지수: " + bmiResult + " ㎏/㎡ 입니다.\n결과: " + category;

            result.setText(resultText);
            img_bmi.setImageResource(imageResId);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "숫자를 올바르게 입력하세요.", Toast.LENGTH_SHORT).show();
        }
    }
}