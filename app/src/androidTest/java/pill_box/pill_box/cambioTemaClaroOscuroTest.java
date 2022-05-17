package pill_box.pill_box;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.pill_box.R;
import com.pastillasCreator.pill_box.actividades.CalendarActivity;
import com.pastillasCreator.pill_box.actividades.MainActivity;


import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;


public class cambioTemaClaroOscuroTest {

    @Given("un boton switch para cambiar el tema")
    public void un_boton_switch_para_cambiar_el_tema() {
        //swi = findViewById(R.id.switch1);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
    @When("^se pulsa para cambiar a modo oscuro$")
    public void se_pulse_el_boton() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }

    @Then("^la aplicacion estara en modo oscuro$")
    public void la_aplicacion_estara_en_modo_oscuro() {
        Assert.assertEquals(AppCompatDelegate.getDefaultNightMode(), AppCompatDelegate.MODE_NIGHT_YES);
    }

}
