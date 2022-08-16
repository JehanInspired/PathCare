package applications.PathCareapplication;

import Roman.Roman;
import applications.PathCareapplication.pages.*;
import applications.PathCareapplication.widget.Pre_Analytical;

public class PathCareApplication
{

    public WorkAreaReceptionPage workAreaReceptionPage;
    public TransferLogistics transferLogistics;

    public PathCareLabTransferList pathCareLabTransferList;

    public PathCareDashboardPage pathCareDashboardPage;

    public PathCareLabSpecimenReception pathCareLabSpecimenReception;
    public InterSystemloginPage interSystemloginPage;

    public PathCareScratch pathCareScratch;

    public Pre_Analytical pre_analytical;

    public PathCareApplication(Roman roman)
    {
        workAreaReceptionPage = new WorkAreaReceptionPage(roman);
        pathCareLabTransferList = new PathCareLabTransferList(roman);
        pathCareDashboardPage = new PathCareDashboardPage(roman);
        transferLogistics = new TransferLogistics(roman);
        pathCareLabSpecimenReception = new PathCareLabSpecimenReception(roman);
        pathCareScratch = new PathCareScratch(roman);
        interSystemloginPage = new InterSystemloginPage(roman);
        pre_analytical = new Pre_Analytical(roman);

    }
}
