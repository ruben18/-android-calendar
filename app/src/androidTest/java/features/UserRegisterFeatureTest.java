package features;

import com.example.myapplication.MainActivity;
import com.mauriciotogneri.greencoffee.GreenCoffeeConfig;
import com.mauriciotogneri.greencoffee.GreenCoffeeTest;
import com.mauriciotogneri.greencoffee.ScenarioConfig;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;

import androidx.test.rule.ActivityTestRule;
import gherkin.cli.Main;
import steps.UserRegisterSteps;

@RunWith(Parameterized.class)
public class UserRegisterFeatureTest extends GreenCoffeeTest {
    @Rule
    public ActivityTestRule<MainActivity> activity=new ActivityTestRule<>(MainActivity.class);

    public UserRegisterFeatureTest(ScenarioConfig scenario) {
        super(scenario);
    }

    @Parameterized.Parameters(name="{0}")
    public static Iterable<ScenarioConfig> data() throws IOException{
        return new GreenCoffeeConfig().withFeatureFromAssets("assets/features/userRegister.feater").scenarios();
    }

    @Test
    public void test(){
        start(new UserRegisterSteps());
    }
}
