package arc.haldun.ik.applicationform.info;

import arc.haldun.ik.applicationform.elements.Currency;
import arc.haldun.ik.applicationform.elements.Residence;
import arc.haldun.ik.applicationform.elements.SocialAssurance;

import java.io.Serializable;

public class AdditionalInfo implements Serializable {

    private String incomeAmount;
    private Currency currency;

    private Residence currentResidence;
    private SocialAssurance currentSocialAssurance;

    public AdditionalInfo(String incomeAmount, Currency currency,
                          Residence currentResidence, SocialAssurance currentSocialAssurance) {

        this.incomeAmount = incomeAmount;
        this.currency = currency;
        this.currentResidence = currentResidence;
        this.currentSocialAssurance = currentSocialAssurance;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Ek Bilgiler: ").append("\n");
        stringBuilder.append("\t");
        stringBuilder.append("Aylık Geliri: ").append(incomeAmount).append(" ");
        stringBuilder.append(currency);
        stringBuilder.append("\n");

        stringBuilder.append("\t");
        stringBuilder.append("Mevcut İkametgâh Yeri:");
        stringBuilder.append(currentResidence);
        stringBuilder.append("\n");

        stringBuilder.append("\t");
        stringBuilder.append("Mevcut Sosyal Güvence:");
        stringBuilder.append(currentSocialAssurance);
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }

    public String getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(String incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Residence getCurrentResidence() {
        return currentResidence;
    }

    public void setCurrentResidence(Residence currentResidence) {
        this.currentResidence = currentResidence;
    }

    public SocialAssurance getCurrentSocialAssurance() {
        return currentSocialAssurance;
    }

    public void setCurrentSocialAssurance(SocialAssurance currentSocialAssurance) {
        this.currentSocialAssurance = currentSocialAssurance;
    }
}
