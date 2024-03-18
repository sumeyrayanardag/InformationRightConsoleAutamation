
import java.time.LocalDate;
import java.util.Date;


class Response {

	private short AppID, StatusID, RejectedOrNot, ReasonForRejectionID, WhereToTransferID ;
      //  Date ResponseDate;
        private String ResponseDetail ;
        
	Response() {
		
	}

    public Response(short AppID, short StatusID, short RejectedOrNot, short ReasonForRejectionID, short WhereToTransferID/*, Date ResponseDate,*/, String ResponseDetail) {
        this.AppID = AppID;
        this.StatusID = StatusID;
        this.RejectedOrNot = RejectedOrNot;
        this.ReasonForRejectionID = ReasonForRejectionID;
        this.WhereToTransferID = WhereToTransferID;
    //    this.ResponseDate = ResponseDate;
        this.ResponseDetail = ResponseDetail;
    }

    public Response(short AppID, short StatusID, short RejectedOrNot, short WhereToTransferID, String ResponseDetail) {
        this.AppID = AppID;
        this.StatusID = StatusID;
        this.RejectedOrNot = RejectedOrNot;
        this.WhereToTransferID = WhereToTransferID;
        this.ResponseDetail = ResponseDetail;
    }

    
    
    public short getAppID() {
        return AppID;
    }

    public short getStatusID() {
        return StatusID;
    }

    public short getRejectedOrNot() {
        return RejectedOrNot;
    }

    public short getReasonForRejectionID() {
        return ReasonForRejectionID;
    }

    public short getWhereToTransferID() {
        return WhereToTransferID;
    }

  
    public String getResponseDetail() {
        return ResponseDetail;
    }

    public void setAppID(short AppID) {
        this.AppID = AppID;
    }

    public void setStatusID(short StatusID) {
        this.StatusID = StatusID;
    }

    public void setRejectedOrNot(short RejectedOrNot) {
        this.RejectedOrNot = RejectedOrNot;
    }

    public void setReasonForRejectionID(short ReasonForRejectionID) {
        this.ReasonForRejectionID = ReasonForRejectionID;
    }

    public void setWhereToTransferID(short WhereToTransferID) {
        this.WhereToTransferID = WhereToTransferID;
    }

  

    public void setResponseDetail(String ResponseDetail) {
        this.ResponseDetail = ResponseDetail;
    }

	
	public Object getByName(String attributeName) {
		switch (attributeName) {
                case "AppID": return AppID;
		case "StatusID": return StatusID;
		case "RejectedOrNot": return RejectedOrNot;
                case "WhereToTransferID": return WhereToTransferID;
                case "ReasonForRejectionID": return ReasonForRejectionID;
                case "ResponseDetail": return ResponseDetail;
		default: return null;
		}
                
              
	}

    @Override
    public String toString() {
        return "Response{" + "AppID=" + AppID + ", StatusID=" + StatusID + ", RejectedOrNot=" + RejectedOrNot + ", ReasonForRejectionID=" + ReasonForRejectionID + ", WhereToTransferID=" + WhereToTransferID /*+ ", ResponseDate=" + ResponseDate */+ ", ResponseDetail=" + ResponseDetail + '}';
    }
	

    
        
	
}

