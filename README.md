# InformationRightConsoleAutamation (Bilgi Edinme Hakkı Kanunu Projesi)

DATABASE DIAGRAM AND EXPLANATION 

![diagram](https://github.com/sumeyrayanardag/RightToInformationAct/assets/45365584/40fc3fd9-c81e-47b8-ab58-f2679d9ca5f3)



Firstly we create Person table is for holding common properties in “Employee” and “LegalEntity”. Person table holds 
FullNameAndTitle, eMail, TelephoneNo, Gender, PersonID for indexing each person, Address, City, District, FaxNo, PersonTypeID to decide person is “Gerçek kişi” or “Tüzel kişi” (we take this information from PersonType table with foreign key),  NationalityID to decide if person is Turkish or not (we take this information from NationalityType table with foreign key), EducationStatus, Password and UserName for entering the system, and TCNumber.

Secondly we create PersonType table with columns PersonTypeID and PersonTypeDetail (1->ActualPerson, 2->LegalPerson).

We create Employee table is inherited from Person table. 
	PersonID (we take this information from Person table with foreign key), InstitutionID for where the person work (we take this information from Institution table with foreign key), EmployeeTypeID to decide normal or manager (we take this information from EmployeeType table with foreign key), PermissionID to know the which pages employee can reach (we take this information from Permissions table with foreign key).

Then we create EmployeeType table for deciding employee is “regular” or “manager” (1->Regular, 2->Manager).

We create Permissions table to hold which employee has which permissions. It have PermissionID and Detail columns.

We create Institution table to hold institution data.
	InstitutionName, InstitutionID, Address, UpperID for deciding under which institution this institution works (we connected UpperID with InstitutionID), InstitutionTypeID for deciding “kamu kurum ve kuruluşları” or “kamu kurumu niteliğindeki meslek kuruluşları”. 

We create InstitutionType table with columns InstitutionTypeID and InstitutionDetail ( 1-> public institutions and organizations, 2-> professional organizations that qualify as public institutions).

We create Application table for holding application information. 
	AppID, Details for explanation of application(which data people want), ToWhereID for sending application to which institution (we take this information from Institution table with foreign key), PersonID (we take this information from Person table with foreign key), HowToResponseID (we take this information from ResponseType table with foreign key).

We create Response table for holding response information.
	AppID, StatusID (we take this information from Status table with foreign key), RejectedOrNot (we take this information from RejectedOrNot table with foreign key), ReasonForRejection (we take this information from ReasonForRejection table with foreign key), ResponseDate, ResponseDetail, WhereToTransferID for transferring application to which institution (we take this information from Institution table with foreign key).

We create Status table for holding status of the application. We add columns StatusID and StatusDetail (1->Accepted, 2->Rejected, 3->Transferred, 4->Partial Answer, 5->Received, 6->In Action, 7->Rejected but later accepted).

We create ResponseType table with columns ResponseTypeID and ResponseTypeDetail (1->eMail, 2->Cargo, 3->Fax).
We create RejectedOrNot table with columns RejectedOrNotID and RejectedOrNotDetail (1->Rejected, 2->Not Rejected).
We create ReasonForRejection table with columns ReasonID and Detail (reasons for refusal under the law given in project detail).
We create LegalEntity table with columns AuthorizedPersonName, PersonTypeID (we take this information from PersonType table with foreign key), PersonID (we take this information from Person table with foreign key). 
We create AppCost table with columns AppID, CostClaimDate, Cost, PaidOrNot (we take this information from PaidOrNot (1->Paid, 2->NotPaid) table with foreign key).
