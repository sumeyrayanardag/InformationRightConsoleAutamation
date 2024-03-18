


class Person {

	private short PersonID, PersonTypeID, NationalityID;
        private String FullNameAndTitle, eMail, TelephoneNo, Gender, Address, City, District, FaxNo, EducationStatus, Password, UserName;
        long TCNumber;
		
	
	Person() {
		
	}
	

	Person(short PersonID, String Password) {
		this.PersonID = PersonID;
		this.Password = Password;
	}
        
        Person(short PersonTypeID) {
                this.PersonTypeID = PersonTypeID;
	}
        
        Person(short PersonID, String Password, short PersonTypeID) {
		this.PersonID = PersonID;
		this.Password = Password;
                this.PersonTypeID = PersonTypeID;
	}

    public Person(short PersonTypeID, short NationalityID, String FullNameAndTitle, String eMail, String TelephoneNo, String Gender, String Address, String City, String District, String FaxNo, String EducationStatus, String Password, String UserName, long TCNumber) {
        this.PersonTypeID = PersonTypeID;
        this.NationalityID = NationalityID;
        this.FullNameAndTitle = FullNameAndTitle;
        this.eMail = eMail;
        this.TelephoneNo = TelephoneNo;
        this.Gender = Gender;
        this.Address = Address;
        this.City = City;
        this.District = District;
        this.FaxNo = FaxNo;
        this.EducationStatus = EducationStatus;
        this.Password = Password;
        this.UserName = UserName;
        this.TCNumber = TCNumber;
    }
        
        

    public Person(short PersonID, short PersonTypeID, short NationalityID, String FullNameAndTitle, String eMail, String TelephoneNo, String Gender, String Address, String City, String District, String FaxNo, String EducationStatus, String Password, String UserName, long TCNumber) {
        this.PersonID = PersonID;
        this.PersonTypeID = PersonTypeID;
        this.NationalityID = NationalityID;
        this.FullNameAndTitle = FullNameAndTitle;
        this.eMail = eMail;
        this.TelephoneNo = TelephoneNo;
        this.Gender = Gender;
        this.Address = Address;
        this.City = City;
        this.District = District;
        this.FaxNo = FaxNo;
        this.EducationStatus = EducationStatus;
        this.Password = Password;
        this.UserName = UserName;
        this.TCNumber = TCNumber;
    }
        
        
	
	public short getPersonID() { return PersonID; }
	
	public void setPersonID(short PersonID) { this.PersonID = PersonID; }
        
        public String getPassword() { return Password; }
	
	public void setPassword(short PersonID) { this.Password = Password; }
        
        public short getPersonTypeID() { return PersonTypeID; }
	
	public void setPersonTypeID(short PersonTypeID) { this.PersonTypeID = PersonTypeID; }

    public short getNationalityID() {
        return NationalityID;
    }

    public String getFullNameAndTitle() {
        return FullNameAndTitle;
    }

    public String geteMail() {
        return eMail;
    }

    public String getTelephoneNo() {
        return TelephoneNo;
    }

    public String getGender() {
        return Gender;
    }

    public String getAddress() {
        return Address;
    }

    public String getCity() {
        return City;
    }

    public String getDistrict() {
        return District;
    }

    public String getFaxNo() {
        return FaxNo;
    }

    public String getEducationStatus() {
        return EducationStatus;
    }

    public String getUserName() {
        return UserName;
    }

    public void setNationalityID(short NationalityID) {
        this.NationalityID = NationalityID;
    }

    public void setFullNameAndTitle(String FullNameAndTitle) {
        this.FullNameAndTitle = FullNameAndTitle;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public void setTelephoneNo(String TelephoneNo) {
        this.TelephoneNo = TelephoneNo;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public void setDistrict(String District) {
        this.District = District;
    }

    public void setFaxNo(String FaxNo) {
        this.FaxNo = FaxNo;
    }

    public void setEducationStatus(String EducationStatus) {
        this.EducationStatus = EducationStatus;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public long getTCNumber() {
        return TCNumber;
    }

    public void setTCNumber(long TCNumber) {
        this.TCNumber = TCNumber;
    }

    
	public Object getByName(String attributeName) {
		switch (attributeName) {
		case "PersonID": return PersonID;
		case "Password": return Password;/*
		case "GroupName": return groupName;
		case "ModifiedDate": return modifiedDate;*/
                case "PersonTypeID": return PersonTypeID;
                case "NationalityID": return NationalityID;
                case "FullNameAndTitle": return FullNameAndTitle;
                case "eMail": return eMail;
                case "TelephoneNo": return TelephoneNo;
                case "Gender": return Gender;
                case "Address": return Address;
                case "City": return City;
                case "District": return District;
                case "FaxNo": return FaxNo;
                case "EducationStatus": return EducationStatus;
                case "UserName": return UserName;
                case "TCNumber": return TCNumber;
               
		default: return null;
		}
	}


    @Override
    public String toString() {
        return "Person{" + "PersonID=" + PersonID + ", PersonTypeID=" + PersonTypeID + ", NationalityID=" + NationalityID + ", FullNameAndTitle=" + FullNameAndTitle + ", eMail=" + eMail + ", TelephoneNo=" + TelephoneNo + ", Gender=" + Gender + ", Address=" + Address + ", City=" + City + ", District=" + District + ", FaxNo=" + FaxNo + ", EducationStatus=" + EducationStatus + ", Password=" + Password + ", UserName=" + UserName + ", TCNumber=" + TCNumber + '}';
    }

    
        
}
