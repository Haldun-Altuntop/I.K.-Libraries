package arc.haldun.ik.applicationform;

import arc.haldun.ik.applicationform.elements.*;
import arc.haldun.ik.applicationform.info.AdditionalInfo;
import arc.haldun.ik.applicationform.info.MilitaryState;
import arc.haldun.ik.applicationform.info.PersonalInformation;
import arc.haldun.ik.applicationform.info.academicstate.AcademicState;

import java.io.Serializable;

public class Application implements Serializable {

    private int id;
    private PersonalInformation personalInformation;
    private MilitaryState militaryState;
    private AcademicState academicState;
    private Language[] languages;
    private AdditionalInfo additionalInfo;
    private Experience[] experiences;
    private Reference[] references;

    public Application() {
        this.id = 0;
    }

    public Application(int id) {
        this.id = id;
    }

    public Application(int id, PersonalInformation personalInformation, MilitaryState militaryState,
                       AcademicState academicState, Language[] languages, AdditionalInfo additionalInfo,
                       Experience[] experiences, Reference[] references) {
        this.id = id;
        this.personalInformation = personalInformation;
        this.militaryState = militaryState;
        this.academicState = academicState;
        this.languages = languages;
        this.additionalInfo = additionalInfo;
        this.experiences = experiences;
        this.references = references;
    }

    public Application(PersonalInformation personalInformation, MilitaryState militaryState,
                       AcademicState academicState, Language[] languages, AdditionalInfo additionalInfo,
                       Experience[] experiences, Reference[] references) {

        this.id = 0;
        this.personalInformation = personalInformation;
        this.militaryState = militaryState;
        this.academicState = academicState;
        this.languages = languages;
        this.additionalInfo = additionalInfo;
        this.experiences = experiences;
        this.references = references;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(getPersonalInformation().toString()).append("\n");

        stringBuilder.append("\n").append(getMilitaryState().toString()).append("\n");

        stringBuilder.append("\n").append(getAcademicState().toString()).append("\n");

        stringBuilder.append("\n").append("Diller:").append("\n");
        for (Language language : getLanguages()) {
            stringBuilder.append(language.toString()).append("\n");
        }

        stringBuilder.append("\n").append(getAdditionalInfo().toString()).append("\n");

        stringBuilder.append("\n").append("Deneyimler:").append("\n");
        for (Experience experience : getExperiences()) {
            stringBuilder.append(ApplicationSerializer.indentString(experience.toString()));
        }

        stringBuilder.append("\n").append("Referanslar:").append("\n");
        for (Reference reference : getReferences()) {
            stringBuilder.append(ApplicationSerializer.indentString(reference.toString()));
        }

        return stringBuilder.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;

        //
        // Update parent application id
        //

        for (Language language : this.languages) {
            language.setParentApplicationId(id);
        }

        for (Experience experience : this.experiences) {
            experience.setParentApplicationId(id);
        }

        for (Reference reference : this.references) {
            reference.setParentApplicationId(id);
        }
    }

    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    public void setPersonalInformation(PersonalInformation personalInformation) {
        this.personalInformation = personalInformation;
    }

    public MilitaryState getMilitaryState() {
        return militaryState;
    }

    public void setMilitaryState(MilitaryState militaryState) {
        this.militaryState = militaryState;
    }

    public AcademicState getAcademicState() {
        return academicState;
    }

    public void setAcademicState(AcademicState academicState) {
        this.academicState = academicState;
    }

    public Language[] getLanguages() {
        return languages;
    }

    public void setLanguages(Language[] languages) {

        for (Language language : languages) {
            language.setParentApplicationId(getId());
        }

        this.languages = languages;
    }

    public AdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(AdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Experience[] getExperiences() {
        return experiences;
    }

    public void setExperiences(Experience[] experiences) {

        for (Experience experience : experiences) {
            experience.setParentApplicationId(getId());
        }

        this.experiences = experiences;
    }

    public Reference[] getReferences() {
        return references;
    }

    public void setReferences(Reference[] references) {

        for (Reference reference : references) {
            reference.setParentApplicationId(getId());
        }

        this.references = references;
    }
}
