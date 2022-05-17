
public class AddPill{

    private PillAccumulator accumulator = PillAccumulator.getAccumulator();
    private Pill p ;
    
    @Given("^$")
    public void nada() {
        //No es necesario nada
    }

    @When("^I enter the necessary fields$")
    public void enterfields(){
        PillCreator pc = new PillCreator("Ibuprofeno", "Antiflamatorio", 2, "08:00",new boolean[]{true,true,true,true,true,true,true}, PillType.Capsula );
        p=pc.createElement();
    }

    @When("^I click add button$")
    public void button(){
        accumulator.saveElement(p);
    }

    @Then("^I should see the main page$")
    public void ishouldseethemainpage() {

        Assert.assertEquals(1,accumulator.getAllElements().size());
    } 
    


}