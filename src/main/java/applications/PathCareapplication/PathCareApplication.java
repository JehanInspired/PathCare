package applications.PathCareapplication;

import Roman.Roman;
import applications.PathCareapplication.pages.PathCareDashboardPage;
import applications.PathCareapplication.pages.InterSystemloginPage;
import applications.PathCareapplication.pages.PathCareScratch;

public class PathCareApplication
{

    public PathCareDashboardPage pathCareDashboardPage;
    public InterSystemloginPage interSystemloginPage;
    public PathCareScratch pathCareScratch;

    public PathCareApplication(Roman roman)
    {
        pathCareDashboardPage = new PathCareDashboardPage(roman);
        pathCareScratch = new PathCareScratch(roman);
        interSystemloginPage = new InterSystemloginPage(roman);

    }
}
