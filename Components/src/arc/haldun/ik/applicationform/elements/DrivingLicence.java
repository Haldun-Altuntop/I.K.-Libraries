package arc.haldun.ik.applicationform.elements;

import arc.haldun.ik.exceptions.MissingInformationException;

import java.io.Serializable;

public class DrivingLicence implements Serializable {

    private boolean hasLicence;
    private String receiptDate, licenceClass;

    public DrivingLicence() {
        this.hasLicence = false;
    }

    public DrivingLicence(String receiptDate, String licenceClass) throws MissingInformationException {

        if (receiptDate == null || receiptDate.isEmpty()) throw new MissingInformationException("Ehliyet Veriliş tarihi");
        if (licenceClass == null || licenceClass.isEmpty()) throw new MissingInformationException("Ehliyet Sınıfı");

        this.receiptDate = receiptDate;
        this.licenceClass = licenceClass;
        this.hasLicence = true;
    }

    public boolean hasLicence() {
        return hasLicence;
    }

    public String getReceiptDate() {
        return this.receiptDate;
    }

    public String getLicenceClass() {
        return licenceClass;
    }

    @Override
    public String toString() {

        String classString;

        if (hasLicence) {

            classString = "Veriliş Tarihi: " + receiptDate + "\n" +
                        "Sınıfı: " + licenceClass;
        } else {
            classString = "Yok";
        }

        return classString;
    }

    public boolean isValid() {

        if (hasLicence) {

            return !receiptDate.isEmpty() && !licenceClass.isEmpty();

        } else {
            return true;
        }

    }
}
