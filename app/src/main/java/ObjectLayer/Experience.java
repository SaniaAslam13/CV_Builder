package ObjectLayer;


public class Experience {

    int id;
    String year_Start;
    String year_end;
    String institute;
    String job;

    public Experience() {
        this.year_Start = null;
        this.year_end = null;
        this.institute = "";
        this.job = "";
    }


    public Experience(String year_Start, String year_end, String institute, String job) {
        this.id= 0;
        this.year_Start = year_Start;
        this.year_end = year_end;
        this.institute = institute;
        this.job = job;
    }

    public Experience(int id, String year_Start, String year_end, String institute, String job) {
        this.id = id;
        this.year_Start = year_Start;
        this.year_end = year_end;
        this.institute = institute;
        this.job = job;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYear_Start() {
        return year_Start;
    }

    public void setYear_Start(String year_Start) {
        this.year_Start = year_Start;
    }

    public String getYear_end() {
        return year_end;
    }

    public void setYear_end(String year_end) {
        this.year_end = year_end;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String experience() {

        StringBuilder sb = new StringBuilder();

        sb.append("( " + this.year_Start + " - " + this.year_end + " ) ");
        sb.append(" | ");
        sb.append(this.institute.toString());
        sb.append(" | ");
        sb.append(this.job.toString());


        return sb.toString();
    }
}
