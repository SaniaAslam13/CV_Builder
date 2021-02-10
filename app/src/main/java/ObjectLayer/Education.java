package ObjectLayer;


public class Education {

    int id;
    String year_Start;
    String year_End;
    String institute;
    String degree;

    public Education() {
        this.year_Start = null;
        this.year_End = null;
        this.institute = "";
        this.degree = "";
    }

    public Education(String year_Start, String year_End, String institute, String degree) {
        this.id= 0;
        this.year_Start = year_Start;
        this.year_End = year_End;
        this.institute = institute;
        this.degree = degree;
    }

    public Education(int id, String year_Start, String year_End, String institute, String degree) {
        this.id = id;
        this.year_Start = year_Start;
        this.year_End = year_End;
        this.institute = institute;
        this.degree = degree;
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

    public String getYear_End() {
        return year_End;
    }

    public void setYear_End(String year_End) {
        this.year_End = year_End;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getDegree() {
        return this.degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String education() {

        StringBuilder sb = new StringBuilder();

        sb.append("( " + this.year_Start + " - " + this.year_End + " ) ");
        sb.append(" | ");
        sb.append(this.institute);
        sb.append(" | ");
        sb.append(this.degree);

        return sb.toString();
    }
}
