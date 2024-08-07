package arc.haldun.ik.applicationform.info;

import java.io.Serializable;
import java.util.ArrayList;

import arc.haldun.ik.exceptions.MissingInformationException;

public class MilitaryState implements Serializable {

    private boolean hasDone;

    private String entryDate;
    private String dischargeDate;
    private String dutyArea;
    private String rank;

    public MilitaryState(boolean hasDone) {

        if (!hasDone) {
            this.hasDone = false;
        }

        entryDate = "";
        dischargeDate = "";
        dutyArea = "";
        rank = "";
    }

    public MilitaryState(String entryDate, String dischargeDate, String dutyArea, String rank) {
        this.hasDone = true;
        this.entryDate = entryDate;
        this.dischargeDate = dischargeDate;
        this.dutyArea = dutyArea;
        this.rank = rank;
    }

    public boolean isHasDone() {
        return hasDone;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public String getDischargeDate() {
        return dischargeDate;
    }

    public String getDutyArea() {
        return dutyArea;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public String toString() {

        String classString = "Askerlik Durumu:\n";

        if (hasDone) {


            classString += "\t";
            classString += "Yaptı";
            classString += "\n";

            classString += "\t";
            classString += "Giriş Tarihi: " + entryDate;
            classString += "\n";

            classString += "\t";
            classString += "Terhis Tarihi: " + dischargeDate;
            classString += "\n";

            classString += "\t";
            classString += "Görev Yeri: " + dutyArea;
            classString += "\n";

            classString += "\t";
            classString += "Rütbesi: " + rank;

        } else {

            classString += "\t";
            classString += "Yapmadı";
        }

        return classString;
    }

    /**
     * Bilginin geçerliliği kontrol edilir.
     * Eğer geçerli değilse, eksik bilgi varsa MissingInformationException fırlatılır.
     * Eğer bilgiler tamsa, geçerliyse kod devam eder.
     * @throws MissingInformationException
     */
    public void checkValidity() throws MissingInformationException {

        ArrayList<String> missingFields = new ArrayList<>();

        if (entryDate.isEmpty()) missingFields.add("Giriş Tarihi");
        if (dischargeDate.isEmpty()) missingFields.add("Terhis tarihi");
        if (dutyArea.isEmpty()) missingFields.add("Görev Yeri");
        if (rank.isEmpty()) missingFields.add("Rütbe");

        // Check missing fields.
        if (missingFields.size() > 0) {
            throw new MissingInformationException(missingFields.toArray(new String[]{}));
        }
    }

    @Deprecated
    public boolean isValid() {

        if (hasDone) {
            return !entryDate.isEmpty() && !dischargeDate.isEmpty()
                    && dutyArea.isEmpty() && !rank.isEmpty();
        } else {
            return true;
        }

    }
}
