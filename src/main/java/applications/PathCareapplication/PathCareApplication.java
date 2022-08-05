package applications.PathCareapplication;

import Roman.Roman;
import applications.PathCareapplication.pages.PathCareDashboardPage;
import applications.PathCareapplication.pages.InterSystemloginPage;
import applications.PathCareapplication.pages.PathCareLabSpecimenReception;
import applications.PathCareapplication.pages.PathCareScratch;
import applications.PathCareapplication.widget.Pre_Analytical;

public class PathCareApplication
{

    public PathCareDashboardPage pathCareDashboardPage;

    public PathCareLabSpecimenReception pathCareLabSpecimenReception;
    public InterSystemloginPage interSystemloginPage;
    public PathCareScratch pathCareScratch;

    public Pre_Analytical pre_analytical;

    public PathCareApplication(Roman roman)
    {
        //pathCareDashboardPage = new PathCareDashboardPage(roman);
        pathCareLabSpecimenReception = new PathCareLabSpecimenReception(roman);
        pathCareScratch = new PathCareScratch(roman);
        interSystemloginPage = new InterSystemloginPage(roman);
        pre_analytical = new Pre_Analytical(roman);


    }
}
