package applications.exampleapplication;

import Roman.Roman;
import applications.exampleapplication.pages.ExamplePage;

public class ExampleApplication {

    public ExamplePage examplePage;

    public ExampleApplication(Roman roman)
    {
        examplePage = new ExamplePage(roman);
    }
}
