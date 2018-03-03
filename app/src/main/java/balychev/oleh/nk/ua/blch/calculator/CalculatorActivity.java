package balychev.oleh.nk.ua.blch.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class CalculatorActivity extends AppCompatActivity {
    private AdView mAdView;
    private EditText mEditText;
    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        MobileAds.initialize(getApplicationContext(),
                getString(R.string.YOUR_ADMOB_APP_ID));

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mEditText = findViewById(R.id.et_value);
        controller = new Controller();
        display();
    }

    public void onNumberBtnClick(View view) {
        Button button = (Button) view;
        controller.number(button.getText().toString().charAt(0));
        display();
    }

    public void onCleanBtnClick(View view) {
        controller.clear();
        display();
    }

    public void onEqualBtnClick(View view) {
    }

    private void display(){
        mEditText.setText(controller.getNumber());
    }

    public void onOperationBtnClick(View view) {
        Button button = (Button) view;
        controller.operation(button.getText().toString().charAt(0));

    }
}
