
import java.util.Date;

class Application {

    private short AppID, ToWhereID, PersonID, HowToResponseID;
    private String Details;

    Application() {

    }

    public Application(short AppID, short ToWhereID, short PersonID, short HowToResponseID,String Details) {
        this.AppID = AppID;
        this.ToWhereID = ToWhereID;
        this.PersonID = PersonID;
        this.HowToResponseID = HowToResponseID;
        this.Details = Details;
    }

    public Application(short ToWhereID, short PersonID, short HowToResponseID, String Details) {
        this.ToWhereID = ToWhereID;
        this.PersonID = PersonID;
        this.HowToResponseID = HowToResponseID;
        this.Details = Details;
    }
    

    public short getToWhereID() {
        return ToWhereID;
    }

    public short getPersonID() {
        return PersonID;
    }

    public short getHowToResponseID() {
        return HowToResponseID;
    }

    public String getDetails() {
        return Details;
    }

    public void setToWhereID(short toWhereID) {
        this.ToWhereID = toWhereID;
    }

    public void setPersonID(short PersonID) {
        this.PersonID = PersonID;
    }

    public void setHowToResponseID(short HowToResponseID) {
        this.HowToResponseID = HowToResponseID;
    }

    
    public void setDetails(String Details) {
        this.Details = Details;
    }

    public short getAppID() {
        return AppID;
    }

    public void setAppID(short AppID) {
        this.AppID = AppID;
    }

   
    public Object getByName(String attributeName) {
        switch (attributeName) {
            case "AppID":
                return AppID;
            case "PersonID":
                return PersonID;
            case "ToWhereID":
                return ToWhereID;
            case "HowToResponseID":
                return HowToResponseID;
            case "Details":
                return Details;
            default:
                return null;
        }

    }

    @Override
    public String toString() {
        return "Application{" + "AppID=" + AppID + ", toWhereID=" + ToWhereID + ", PersonID=" + PersonID + ", HowToResponseID=" + HowToResponseID + ", Details=" + Details + '}';
    }

}
